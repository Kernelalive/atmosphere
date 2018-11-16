package eubrazil.atmosphere.util;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public final class Util {

  // TODO rewrite function - do not rely on exception - pattern maybe ?\
  /**
   * Checks if a string is UUID.
   *
   * @param string The string to be checked
   * @return True if it is a UUId
   */
  public static boolean isUuid(String string) {
    try {
      UUID.fromString(string);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  /**
   * Get full uri from http request.
   *
   * @param request Http request from client
   * @return Full path uri + query string
   */
  public static String getFullUri(HttpServletRequest request) {
    String requestUri = request.getRequestURI();
    String queryString = request.getQueryString();
    if (queryString == null) {
      return requestUri;
    } else {
      return requestUri.concat("?").concat(queryString);
    }
  }

}