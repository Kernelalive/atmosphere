/**
 * Data subjects
 */
var DATASUBJECT_REST_URL = '/api/v1/datasubject';

$(document).ready(function() {
  logger.d('Loaded datasubject.js');
});

/*
 * Controllers
 */
var datasubject = {};

datasubject.list = function list(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

datasubject.add = function add(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    $('select').select2();
    next();
  });
};

datasubject.edit = function edit(ctx, next) {
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

function addDatasubjectHandler() {
  if (hasEmptyFields()) return;
  let datasubject = $('form').serializeJSON();
  let piis = [];
  $.each($('#piis').select2().val(), function(index, value) {
    let pii = {};
    pii.id = value;
    piis.push(pii);
  });
  datasubject.piis = piis;
  $.each($('#piisSpecial').select2().val(), function(index, value) {
    let pii = {};
    pii.id = value;
    datasubject.piis.push(pii);
  });
  logger.i(JSON.stringify(datasubject));
  $.post({
    data: JSON.stringify(datasubject),
    url: DATASUBJECT_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function() {
    alertify.log('Data Subject was successfully added');
    page.redirect('/datasubject');
  })
}

function editDatasubjectHandler(id) {
  if (hasEmptyFields()) return;
  let datasubject = $('form').serializeJSON();
  datasubject.id = id;
  let piis = [];
  $.each($('#piis').select2().val(), function(index, value) {
    let pii = {};
    pii.id = value;
    piis.push(pii);
  });
  datasubject.piis = piis;
  $.each($('#piisSpecial').select2().val(), function(index, value) {
    let pii = {};
    pii.id = value;
    datasubject.piis.push(pii);
  });
  logger.i(JSON.stringify(datasubject));
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(datasubject),
    url: DATASUBJECT_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Data Subject was successfully updated');
    page.redirect('/datasubject');
  });
}

function deleteDatasubjectHandler(id) {
  $.ajax({
    type: 'DELETE',
    url: DATASUBJECT_REST_URL + '/' + id,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Data Subject was successfully deleted');
    page.redirect('/datasubject');
  });
}
