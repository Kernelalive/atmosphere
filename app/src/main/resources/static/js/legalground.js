/**
 * Legal grounds
 */
var LEGALGROUND_REST_URL = '/api/v1/legalground';

$(document).ready(function() {
  logger.d('Loaded legalground.js');
});

/*
 * Controllers
 */
var legalground = {};

legalground.list = function list(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

legalground.add = function add(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    $('select').select2();
    next();
  });
};

legalground.edit = function edit(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  var id = ctx.params.id;
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    $('select').select2();
    next();
  });
};

/*
 * Handlers
 */

function addLegalgroundHandler() {
  if (hasEmptyFields()) return;
  let legalground = $('form').serializeJSON();
  logger.i(JSON.stringify(legalground));
  $.post({
    data: JSON.stringify(legalground),
    url: LEGALGROUND_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function() {
    alertify.log('Legal Ground was successfully added');
    page.redirect('/legalground');
  })
}

function editLegalgroundHandler(id) {
  if (hasEmptyFields()) return;
  let legalground = $('form').serializeJSON();
  legalground.id = id;
  logger.i(JSON.stringify(legalground));
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(legalground),
    url: LEGALGROUND_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Legal Ground was successfully updated');
    page.redirect('/legalground');
  });
}

function deleteLegalgroundHandler(id) {
  $.ajax({
    type: 'DELETE',
    url: LEGALGROUND_REST_URL + '/' + id,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Legal Ground was successfully deleted');
    page.redirect('/legalground');
  });
}
