/**
 * Dashboard
 */
$(document).ready(function() {
  logger.d('Loaded dashboard.js');
});

/*
 * Controllers
 */
var dashboard = {};

dashboard.load = function load(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

/*
 *  Handlers
 */

/*
 *  Actions
 */
