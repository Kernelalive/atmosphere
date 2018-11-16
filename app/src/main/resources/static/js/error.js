/**
 * Errors
 */
$(document).ready(function() {
  logger.d('Loaded user.js');
});

/*
 * Controllers
 */
var error = {};

error.page404 = function page404(ctx) {
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
  });
};

error.page403 =function page403(ctx) {
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
  });
};

error.page500 =function page500(ctx) {
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
  });
};
