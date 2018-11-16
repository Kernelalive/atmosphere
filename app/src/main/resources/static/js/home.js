/**
 * Home
 */
/**
 * @param {Context} ctx
 */
function home(ctx) {
  logger.i(JSON.stringify(ctx.pathname));
  if (ctx.init) {
    logger.i('Current page set to: ' + ctx.pathname);
    // Urgent bound of Interceptor
    bindAJAXCallInterceptor();
    // Urgent setup of ajax filter
    setupAjaxCallFiltering();
    // Check for possible page redirection
    if (null != redirectToPage) {
      logger.i('Found redirect-to-page : ' + redirectToPage);
      page.redirect(redirectToPage);
      return;
    }
    // Check if user is authenticated immediately redirect to /dashboard
    if (hasAccessToken()) {
      logger.d('User is authenticated, redirecting user to dashboard page');
      goDashboard();
      return;
    } else {
      // Load header
      $.post('/header', function(data) {
          $('#header-wrapper').html(data);
      });
      // Load home page content
      $.post('/home', function(data) {
        $('#content').html(data);
        $('body').addClass('home');
      });
    }
  } else {
    location.reload();
  }
}

