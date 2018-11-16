/**
 * Auth
 */
// Document ready
$(document).ready(function() {
  logger.d('Loaded auth.js');
});

// Static variables
const LOGIN_REST_URL = '/api/v1/auth/login';
const REGISTER_REST_URL = '/api/v1/auth/register';

/*
 * Controllers
 */
var auth = {};

auth.login = function login(ctx) {
  logger.i('Current page set to: ' + ctx.pathname);
  // Check if user is authenticated immediately redirect to /dashboard
  if (hasAccessToken()) {
    logger.d('User is authenticated, redirecting user to dashboard page');
    goDashboard();
    return;
  } else {
    $.post(ctx.canonicalPath, function(data) {
      $('#content').html(data);
      $('body').removeClass('home');
    });
  }
};

auth.register = function register(ctx) {
  logger.i('Current page set to: ' + ctx.pathname);
  // Check if user is authenticated immediately redirect to /dashboard
  if (hasAccessToken()) {
    logger.d('User is authenticated, redirecting user to dashboard page');
    goDashboard();
    return;
  } else {
    $.post(ctx.canonicalPath, function(data) {
      $('#content').html(data);
      $('body').removeClass('home');
    });
  }
};

/*
 * Handlers
 */
// Handle logout
function logout() {
  logger.d('Logout requested, removing token from localstorage...');
  removeAccessToken();
  goHome();
};

// Handle login
function loginHandler() {
  let credentials = $('form').serializeJSON();
  $.post({
    data: JSON.stringify(credentials),
    url: LOGIN_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    logger.i('Signed in successfully');
    alertify.log(message.auth.login.success);
    addAccessToken(xhr.getResponseHeader(AUTHORIZATION_HEADER));
    goHome();
  })
}

// Handle register
function registerHandler() {
  let user = $('form').serializeJSON();
  console.log(JSON.stringify(user));
  // Make the register call
  $.post({
    data: JSON.stringify(user),
    url: REGISTER_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function() {
    alertify.log('Registration successful');
    goLogin();
  })
}

/*
 *  Actions
 */
/**
 * Function to trigger Log In when a user press enter on password text field
 *
 * @param {type} e
 * @return {undefined}
 */
function keyPressedOnLoginPasswordField(e) {
  let key = e.keyCode || e.which;
  // On enter pressed send login request
  if (key === 13) {
    loginHandler();
  }
}
