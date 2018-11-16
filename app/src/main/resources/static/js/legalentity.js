/**
 * Legal Entities
 */
var LEGALENTITY_REST_URL = '/api/v1/legalentity';

$(document).ready(function() {
  logger.d('Loaded legalentity.js');
});

/*
 * Controllers
 */
var legalentity = {};

legalentity.list = function list(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

legalentity.add = function add(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    $('select').select2();
    next();
  });
};

legalentity.edit = function edit(ctx, next) {
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

function addLegalentityHandler() {
  if (hasEmptyFields()) return;
  let legalentity = $('form').serializeJSON();
  logger.i(JSON.stringify(legalentity));
  $.post({
    data: JSON.stringify(legalentity),
    url: LEGALENTITY_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function() {
    alertify.log('Legal Entity was successfully added');
    page.redirect('/legalentity');
  })
}

function editLegalentityHandler(id) {
  if (hasEmptyFields()) return;
  let legalentity = $('form').serializeJSON();
  legalentity.id = id;
  logger.i(JSON.stringify(legalentity));
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(legalentity),
    url: LEGALENTITY_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Legal Entity was successfully updated');
    page.redirect('/legalentity');
  });
}

function deleteLegalentityHandler(id) {
  $.ajax({
    type: 'DELETE',
    url: LEGALENTITY_REST_URL + '/' + id,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Legal Entity was successfully deleted');
    page.redirect('/legalentity');
  });
}
