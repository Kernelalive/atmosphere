package eubrazil.atmosphere.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.security.token.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

  private final TokenAuthenticationService tokenAuthenticationService;

  public StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
                              AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(urlMapping));
    this.tokenAuthenticationService = tokenAuthenticationService;
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException {
    final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
    final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
            user.getUsername(), user.getPassword());
    return getAuthenticationManager().authenticate(loginToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication authentication) throws IOException, ServletException {
    // Add the custom token as HTTP header to the response
    tokenAuthenticationService.addAuthentication(response, (UserDetails) authentication.getPrincipal());
    // Add the authentication to the Security context
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
