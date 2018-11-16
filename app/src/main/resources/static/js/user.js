/**
 * Users
 */
var USER_REST_URL = '/api/v1/users';

$(document).ready(function() {
  logger.d('Loaded user.js');
});

/*
 * Controllers
 */
var user = {};

user.list = function list(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

/*
 * Handlers
 */
function editUserRoleHandler(ctrl, username) {
  let role = $(ctrl).val();
  $.ajax({
   type: 'PUT',
   url: USER_REST_URL + '/' + username + '/role/' + role,
   contentType: 'application/json; charset=utf-8'
  }).done(function() {
   alertify.log('Role was successfully changed');
  })
}

function editUserStatusHandler(ctrl, username) {
  if (ctrl.checked) {
    // Enable User
    $.ajax({
      type: 'PUT',
      url: USER_REST_URL + '/' + username + '/enable',
      contentType: 'application/json; charset=utf-8'
    }).done(function() {
      alertify.log('User was successfully enabled');
    })
  } else {
    // Disable User
    $.ajax({
      type: 'PUT',
      url: USER_REST_URL + '/' + username + '/disable',
      contentType: 'application/json; charset=utf-8'
    }).done(function() {
      alertify.log('User was successfully disabled');
    })
  }
}
