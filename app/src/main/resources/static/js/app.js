/**
 * App
 *
 * The current file is used to initialize
 * and setup the basic configuration of the
 * the application
 *
 * PLEASE DO NOT MAKE ANY MODIFICATIONS TO THIS FILE
 *
 * For any comments or suggestions please contact:
 *
 * ch.paraskeva at gmail dot com
 * tsiolis.g at gmail dot com
 * -----------------------------------------------------------------------------
 *
 * Logging Mechanism
 * -----------------
 *
 * Arcadia framework supports a javascript logger(console appender). There are
 * currently three type of message levels (info, debug and error). You can use
 * the logger in any .js by using the following command :
 *
 * logger.x('message') where x is one of the following log levels :
 *        i for INFO
 *        d for DEBUG
 *        e for ERROR
 *
 * Notification Mechanism
 * -----------------------
 *
 * For any kind of client notifications you are suggested to use alertify.js.
 * You can use alertify to show information messages or error messages:
 *
 * alertify.log("Message");
 * alertify.error("Message");
 *
 */
$(document).ready(function() {
  logger.d('Loaded app.js');
});

// Static variables
const DEBUG_PROMPT = '[DEBUG] => ';
const INFO_PROMPT = '[INFO] => ';
const ERROR_PROMPT = '[ERROR] => ';
const ENV_MODE = 'DEV';
const AUTHORIZATION_HEADER = 'Authorization';
const PRE_AUTHORIZATION_HEADER = 'Pre-Authorization';
const TOKEN_KEY_NAME = 'auth_token';

var debugLogger = function logger(message) {
  if (isDevMode()) {
    console.log(DEBUG_PROMPT + message);
  }
};

var infoLogger = function logger(message) {
  if (isDevMode()) {
    console.log(INFO_PROMPT + message);
  }
};

var errorLogger = function logger(message) {
  if (isDevMode()) {
    console.log(ERROR_PROMPT + message);
  }
};

/**
 * Definition of Logger class
 */
function Logger() {
  this.i = infoLogger;
  this.d = debugLogger;
  this.e = errorLogger;
}

/**
 * Return true if current mode is set to development
 * @return {Boolean}
 */
function isDevMode() {
  return ENV_MODE === 'DEV';
}

/**
 * Returns true if user has an access token store in local storage
 * @return {Boolean}
 */
function hasAccessToken() {
  return null !== localStorage.getItem(TOKEN_KEY_NAME);
}

function removeAccessToken() {
  localStorage.removeItem(TOKEN_KEY_NAME);
}

function addAccessToken(accessToken) {
  localStorage.auth_token = accessToken;
}

function bindAJAXCallInterceptor() {
  $.ajaxSetup({
    beforeSend: function(xhr) {
      xhr.setRequestHeader(AUTHORIZATION_HEADER, localStorage.auth_token);
    },
    complete: function() {
      // Scroll to top after post load
      //window.scrollTo(0, 0);
    },
    error: function(jqXHR, textStatus, errorThrown) {
      if (jqXHR.status === 403) {
        goError403();
      } else if (jqXHR.status === 404) {
        goError404();
      } else if (jqXHR.status === 500) {
        alertify.error('Error');
        //goError500();
      } else if (jqXHR.status === 401) {
        removeAccessToken();
        goLogin();
      } else {
        alertify.error(getErrorMessage(jqXHR.responseJSON) + ' [' + jqXHR.status + ']');
      }
    }
  });
  logger.d('AJAX call interceptor has been bound');
}

// Setup JQuery Prefilter
function setupAjaxCallFiltering() {
  $.ajaxPrefilter(function(options, originalOptions, jqXHR) {
    options.async = true;
  });
  logger.d('AJAX call filtering has been setup');
}

// Define a global logger
var logger = new Logger();

// Define redirect page
var redirectToPage = $('#redirect-to-page').val();

/*
 * Multilingual
 */
// Set locale in thymeleaf. Refresh ui to the respective language
function setLocale() {
  $.post('/home?lang=' + document.getElementById('lang').value, function(data) {
    goHome();
  });
}

// Set active menus
function setActiveMenu(menus) {
 return function(ctx, next) {
    menus.forEach(function(menu) {
      $(menu).addClass('active');
    });
  }
}

/*
 * GetErrorMessage function returns the error message from the body of the http response
 * Returns the translated message if exists else returns the response message of the response as is
 * This function is used for multilingual error messages in alerts
 */
function getErrorMessage(response) {
  if (response == undefined) {
    return '';
  }
  // i18n (error code) from response e.g. 'auth.exception.badCredentials'
  let i18n = response.i18n;
  // Check if i18n exists in the response
  if (i18n == undefined) {
    // Return response message as is if i18n does not exist
    return response.message;
  }
  // Check if const message exists (see messages/messages_en_US.js)
  if (message == undefined) {
    // Return response message as is if const message does not exist
    return response.message;
  }
  // Search if respective object exists in the message object (see messages/messages_en_US.js) based on the i18n code
  let parent = message;
  let codes = i18n.split('.');
  while (codes.length) {
    parent = parent[codes.shift()];
    if (parent == undefined) {
      return response.message;
    }
  }
  return parent;
}

/*
 * Animate scroll to a specific element
 *
 * @param element - The dom element path
 * @param offset - If you need extra offset for scroll movement
 * @param animateSpeed - Set the animation speed. Set 0 if you don't want animation
 * @param calcElementHeight - Boolean value. Set to true only if you need to calculate also the extra height of the element that you want to scrool to
 */
function scrollToSpecificElement(element, offset, animateSpeed, calcElementHeight) {
  var elementTop = $(element).offset().top;
  if (calcElementHeight) {
    elementTop = elementTop - $(element).outerHeight() - offset;
  }

  $("html, body").animate({ scrollTop: elementTop }, animateSpeed);
};

/* Check required fields */
function hasEmptyFields() {
  var isFieldEmpty = false;
  $('.form-group.required:not(.hidden) .form-control:not([disabled])').each(function(index) {
    var $this = $(this);
    $this.removeClass('error');
    if ($this.val() == '' || $this.val() == 'noneselected' || $this.hasClass('empty')) {
      // if is chosen container check parent select value
      if ($(this).hasClass("chosen-container")) {
        if ($('#' + this.id.split("_")[0]).val() != '') {
          return true;
        }
      }
      isFieldEmpty = true;
      $this.addClass('error');
    }
  });
  // Show a generic message
  if (isFieldEmpty) {
    alertify.error("Please fill in all the required fields");
    // Scroll user to the required empty field
    var offset = $('#header').outerHeight();
    scrollToSpecificElement('.error', offset, 500, true);

    return true;
  }
  return false;
};
