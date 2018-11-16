/**
 * Processing Type Categories
 */
var PROCESSINGTYPECATEGORY_REST_URL = '/api/v1/processingtypecategory';

$(document).ready(function() {
  logger.d('Loaded processingtypecategory.js');
});

/*
 * Controllers
 */
var processingtypecategory = {};

processingtypecategory.list = function list(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

processingtypecategory.add = function add(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    next();
  });
};

processingtypecategory.edit = function edit(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  var id = ctx.params.id;
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    next();
  });
};

/*
 * Handlers
 */

function addProcessingtypecategoryHandler() {
  if (hasEmptyFields()) return;
  let processingtypecategory = $('form').serializeJSON();
  logger.i(JSON.stringify(processingtypecategory));
  $.post({
    data: JSON.stringify(processingtypecategory),
    url: PROCESSINGTYPECATEGORY_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function() {
    alertify.log('Processing Type Category was successfully added');
    page.redirect('/processingtypecategory');
  })
}

function editProcessingtypecategoryHandler(id) {
  if (hasEmptyFields()) return;
  let processingtypecategory = $('form').serializeJSON();
  processingtypecategory.id = id;
  logger.i(JSON.stringify(processingtypecategory));
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(processingtypecategory),
    url: PROCESSINGTYPECATEGORY_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Processing Type Category was successfully updated');
    page.redirect('/processingtypecategory');
  });
}

function deleteProcessingtypecategoryHandler(id) {
  $.ajax({
    type: 'DELETE',
    url: PROCESSINGTYPECATEGORY_REST_URL + '/' + id,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Processing Type Category was successfully deleted');
    page.redirect('/processingtypecategory');
  });
}
