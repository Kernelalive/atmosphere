/**
 * Router ( client-side router )
 *
 * Handles all the requests for each client-side '/path'
 *
 * For more information about using router please
 * refer to https://github.com/visionmedia/page.js
 */
// Document ready
$(document).ready(function() {
  logger.d('Loaded router.js');
});
/*
 * Front end routing
 */
page.base('/');
page('/', home);
page('login', noHeader, auth.login);
page('register', noHeader, auth.register);
page('dashboard', header, dashboard.load, setActiveMenu(['#menu-dashboard', '#side-menu-dashboard']));
page('pii', header, pii.list, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-piis']));
page('pii/add', header, pii.add, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-piis']));
page('pii/:id', header, pii.edit, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-piis']));
page('datasubject', header, datasubject.list, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-data-subjects']));
page('datasubject/add', header, datasubject.add, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-data-subjects']));
page('datasubject/:id', header, datasubject.edit, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-data-subjects']));
page('legalground', header, legalground.list, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-legal-grounds']));
page('legalground/add', header, legalground.add, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-legal-grounds']));
page('legalground/:id', header, legalground.edit, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-legal-grounds']));
page('legalentity', header, legalentity.list, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-legal-entities']));
page('legalentity/add', header, legalentity.add, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-legal-entities']));
page('legalentity/:id', header, legalentity.edit, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-legal-entities']));
page('processingtypecategory', header, processingtypecategory.list, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-processing-type-categories']));
page('processingtypecategory/add', header, processingtypecategory.add, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-processing-type-categories']));
page('processingtypecategory/:id', header, processingtypecategory.edit, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-processing-type-categories']));
page('processingactivity', header, processingactivity.list, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-processing-activities']));
page('processingactivity/add', header, processingactivity.add, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-processing-activities']));
page('processingactivity/:id', header, processingactivity.edit, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-processing-activities']));
page('gdpr-dataflow', header, dataflow.show, setActiveMenu(['#menu-dashboard', '#side-menu-privacy-assessment', '#side-sub-menu-data-flows']));
page('account/profile/edit', header, account.profile, setActiveMenu(['#side-menu-profile']));
page('account/password/edit', header, account.password, setActiveMenu(['#side-menu-password']));
page('users', header, user.list, setActiveMenu(['#menu-admin', '#side-menu-user']));
page('error/404', noHeader, error.page404);
page('error/403', noHeader, error.page403);
page('error/500', noHeader, error.page500);
page('*', noHeader, error.page404);
page();

/*
 * Go somewhere (helpers)
 */
function goHome() {
  page.redirect('/');
}

function goDashboard() {
  page.redirect('/dashboard');
}

function goLogin() {
  page.redirect('/login');
}

function goError404() {
  page.redirect('/error/404');
}

function goError403() {
  page.redirect('/error/403');
}

function goError500() {
  page.redirect('/error/500');
}
