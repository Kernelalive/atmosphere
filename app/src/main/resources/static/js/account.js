/**
 * Account
 */
$(document).ready(function() {
  logger.d('Loaded account.js');
});

// Static variables
const ACCOUNT_REST_URL = '/api/v1/account/';

/*
 * Controllers
 */
var account = {};

account.profile = function profile(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

account.password = function password(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

/*
 *  Handlers
 */
function editAccountProfileHandler() {
  // Create User object
  let user = $('form').serializeJSON();
  console.log(JSON.stringify(user));
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(user),
    url: ACCOUNT_REST_URL + 'profile',
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    logger.i('Profile was successfully updated');
    alertify.log('Profile was successfully updated');
  });
}

function editAccountPasswordHandler() {
  let passwordDto = $('form').serializeJSON();
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(passwordDto),
    url: ACCOUNT_REST_URL + 'password',
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    logger.i('Password was successfully updated');
    alertify.log('Password was successfully updated');
  });
}
