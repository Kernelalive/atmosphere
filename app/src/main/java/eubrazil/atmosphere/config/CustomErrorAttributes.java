package eubrazil.atmosphere.config;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * Created by kontoe on 27/2/2018.
 * The component adds an extra attribute to error serialization
 * i18n field is used for multilingual messages (used in front end mainly)
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
    Throwable throwable = getError(requestAttributes);
    if (throwable == null) {
      return errorAttributes;
    }
    // Get value for i18n field from getLocalizedMessage (override this method to contain a code f.e. 'auth.exception.badCredentials')
    String i18n = throwable.getLocalizedMessage();
    if (i18n != null) {
      errorAttributes.put("i18n", i18n);
    }
    return errorAttributes;
  }

}