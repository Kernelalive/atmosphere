package eubrazil.atmosphere.security.filter;

import eubrazil.atmosphere.security.exception.TokenExpiredException;
import eubrazil.atmosphere.security.token.TokenAuthenticationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatelessAuthenticationFilter extends GenericFilterBean {

  private final TokenAuthenticationService tokenAuthenticationService;

  public StatelessAuthenticationFilter(TokenAuthenticationService taService) {
    this.tokenAuthenticationService = taService;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    try {
      SecurityContextHolder.getContext().setAuthentication(tokenAuthenticationService.getAuthentication((HttpServletRequest) req));
      // always continue
      chain.doFilter(req, res);
    } catch (TokenExpiredException ex) {
      //((HttpServletResponse) res).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }
  }

}
