/**
 * Piis
 */
var PII_REST_URL = '/api/v1/pii';

$(document).ready(function() {
  logger.d('Loaded pii.js');
});

/*
 * Controllers
 */
var pii = {};

pii.list = function list(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

pii.add = function add(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    $('select').select2();
    next();
  });
};

pii.edit = function edit(ctx, next) {
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

function addPiiHandler() {
  if (hasEmptyFields()) return;
  let pii = $('form').serializeJSON();
  logger.i(JSON.stringify(pii));
  $.post({
    data: JSON.stringify(pii),
    url: PII_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function() {
    alertify.log('Pii was successfully added');
    page.redirect('/pii');
  })
}

function editPiiHandler(id) {
  if (hasEmptyFields()) return;
  let pii = $('form').serializeJSON();
  pii.id = id;
  logger.i(JSON.stringify(pii));
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(pii),
    url: PII_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Pii was successfully updated');
    page.redirect('/pii');
  });
}

function deletePiiHandler(id) {
  $.ajax({
    type: 'DELETE',
    url: PII_REST_URL + '/' + id,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Pii was successfully deleted');
    page.redirect('/pii');
  });
}
