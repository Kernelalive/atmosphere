package eubrazil.atmosphere.config;

import eubrazil.atmosphere.model.Role;
import eubrazil.atmosphere.security.CustomUserDetailsServiceImpl;
import eubrazil.atmosphere.security.filter.StatelessAuthenticationFilter;
import eubrazil.atmosphere.security.token.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The WebSecurity class is the base for authentication and authorization.
 *
 * @author Chris Paraskeva
 * @author George Tsiolis
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] SWAGGER_WHITELIST = {
      // Swagger ui
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**"
  };
  @Autowired
  private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;

  public WebSecurityConfig() {
    // Disable default settings
    super(true);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.// Ignore Swagger
        ignoring().antMatchers(SWAGGER_WHITELIST)
        // Ignore static files
        .and().ignoring().antMatchers("/images/**", "/webjars/**", "/js/**", "/fonts/**", "/css/**", "/favicon.ico")
        // Ignore GET from UI
        .and().ignoring().antMatchers(HttpMethod.GET, "/", "/home", "/login", "/register", "/account/**",
        "/dashboard", "/users/**" , "/datasubject/**", "/legalground/**", "/legalentity/**", "/processingtypecategory/**",
        "/processingactivity/**", "/gdpr-dataflow", "/pii/**",
        "/error/**")
        .and().ignoring().antMatchers(HttpMethod.POST, "/home", "/error/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // Disable session management
        .sessionManagement()
        .enableSessionUrlRewriting(false)
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()

        // UI
        // All users
        .antMatchers(HttpMethod.POST, "/header").permitAll()
        // Anonymous users (without jwt)
        .antMatchers(HttpMethod.POST, "/login", "/register").anonymous()
        // Authenticated users (with jwt)
        .antMatchers(HttpMethod.POST, "/dashboard", "/account/**", "/datasubject/**", "/legalground/**", "/legalentity/**", "/processingtypecategory/**",
            "/processingactivity/**", "/gdpr-dataflow", "/pii/**").authenticated()
        // Owner users
        .antMatchers(HttpMethod.POST, "/users/**").hasAnyRole(Role.OWNER.name())

        // API
        // Anonymous users
        .antMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/login").anonymous()
        // Authenticated users
        .antMatchers("/api/v1/account/**", "/api/v1/datasubject/**", "/api/v1/legalground/**",
            "/api/v1/legalentity/**", "/api/v1/processingtypecategory/**", "/api/v1/processingactivity/**", "/api/v1/pii/**").authenticated()
        .antMatchers("/api/v1/users/**").hasAnyRole(Role.OWNER.name())

        // All other request need to be authenticated
        .anyRequest().authenticated().and()
        // Custom Token based authentication based on the header previously given to the client
        .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
        .csrf().disable()
        .exceptionHandling().and()
        .anonymous().and()
        .servletApi().and()
        .headers().cacheControl();
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsServiceImpl).passwordEncoder(encoder());
  }

}
