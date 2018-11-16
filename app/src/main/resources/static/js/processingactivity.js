/**
 * Processing Activities
 */
var PROCESSINGACTIVITY_REST_URL = '/api/v1/processingactivity';

$(document).ready(function() {
  logger.d('Loaded processingactivity.js');
});

/*
 * Controllers
 */
var processingactivity = {};

processingactivity.list = function list(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    next();
  });
};

processingactivity.add = function add(ctx, next) {
  logger.i("Current page set to: " + ctx.pathname);
  $.post(ctx.canonicalPath, function (data) {
    $("#content").html(data);
    $('select').select2();
    next();
  });
};

processingactivity.edit = function edit(ctx, next) {
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

function addProcessingactivityHandler() {
  if (hasEmptyFields()) return;
  let processingactivity = $('form').serializeJSON();
  logger.i(JSON.stringify(processingactivity));
  $.post({
    data: JSON.stringify(processingactivity),
    url: PROCESSINGACTIVITY_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function() {
    alertify.log('Processing Activity was successfully added');
    page.redirect('/processingactivity');
  })
}

function editProcessingactivityHandler(id) {
  if (hasEmptyFields()) return;
  let processingactivity = $('form').serializeJSON();
  processingtypecategory.id = id;
  logger.i(JSON.stringify(processingactivity));
  $.ajax({
    type: 'PUT',
    data: JSON.stringify(processingactivity),
    url: PROCESSINGACTIVITY_REST_URL,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Processing Activity was successfully updated');
    page.redirect('/processingactivity');
  });
}

function deleteProcessingactivityHandler(id) {
  $.ajax({
    type: 'DELETE',
    url: PROCESSINGACTIVITY_REST_URL + '/' + id,
    contentType: 'application/json; charset=utf-8'
  }).done(function(data, status, xhr) {
    alertify.log('Processing Activity was successfully deleted');
    page.redirect('/processingactivity');
  });
}

/*
 * Helpers
 */

/*
function addRelationItem(e) {
  var relationshipTypeID = $('#relationship-type').val();
  var relationshipTypeName = $('#relationship-type option:selected').text();
  var assetID = $('#related-asset').val();
  var assetName = $('#related-asset option:selected').text();

  if (relationshipTypeID != "" && relationshipTypeName != "") {
    $('fieldset.relationship-wrapper .body').addClass('hidden');
    $('fieldset.relationship-wrapper .table-wrapper').removeClass('hidden');
  } else {
    alertify.error("Select a Relationship and a Related Asset");
    return;
  }

  addRelationItem.counter === undefined ? addRelationItem.counter = 0 : null;

  $('fieldset.relationship-wrapper .table-wrapper tbody').append(
    '<tr onclick="tableActionsTR(event)">' +
    '<td><input type="hidden" name="assetNodeRelationships[][relationshipType]" value="' + relationshipTypeID + '"/>' + relationshipTypeName + '</td>' +
    '<td><input type="hidden" name="assetNodeRelationships[][relatedAsset]" value="' + assetID + '"/>' + assetName + '</td>' +

    '<td class="click action" onclick="tableActions(event)">' +
    '<div class="button-wrapper">' +
    '<button id="relation-btn-' + (++addRelationItem.counter) + '" class="btn darkblue" onclick="removeRelationshipItem(this)">Remove</button>' +
    '</div>' +
    '</td>' +
    '</tr>'
  );
}
function removeRelationshipItem($this) {
  var $this = $($this);
  var buttonID = $this.attr('id');

  $('td.click #' + buttonID).parents('tr').remove();
  $('#button-wrapper-outer').length > 0 ? removeOuterButtonWrapper() : null;

  if ($('fieldset.relationship-wrapper .table-wrapper tbody tr').length == 0) {
    $('fieldset.relationship-wrapper .body').removeClass('hidden');
    $('fieldset.relationship-wrapper .table-wrapper').addClass('hidden');
  }
}
*/
var informationItemCounter = 0;

function addInformationItem(e) {
  var datasubjectId = $('#datasubject').val();
  var datasubjectName = $('#datasubject option:selected').text();
  var piiId = $('#pii').val();
  var piiName = $('#pii option:selected').text();

  if (datasubjectId != "" && datasubjectName != "") {
    $('fieldset.informationitem-wrapper .body').addClass('hidden');
    $('fieldset.informationitem-wrapper .table-wrapper').removeClass('hidden');
  } else {
    alertify.error("Select a Data Subject and a Personally Identifiable Information");
    return;
  }

  //addInformationItem.counter === undefined ? addRelationItem.counter = 0 : null;

  $('fieldset.informationitem-wrapper .table-wrapper tbody').append(
    '<tr>' +
    '<td><input type="hidden" name="informationitems[][datasubject]" value="' + datasubjectId + '"/>' + datasubjectName + '</td>' +
    '<td><input type="hidden" name="informationitems[][pii]" value="' + piiId + '"/>' + piiName + '</td>' +

    '<td class="click action">' +
    '<div class="button-wrapper">' +
    '<button id="informationitem-btn-' + 'n' + (++informationItemCounter) + '" class="btn darkblue" onclick="removeInformationItem(this)">Remove</button>' +
    '</div>' +
    '</td>' +
    '</tr>'
  );
}
function removeInformationItem($this) {
  var $this = $($this);
  var buttonID = $this.attr('id');

  $('td.click #' + buttonID).parents('tr').remove();
  $('#button-wrapper-outer').length > 0 ? removeOuterButtonWrapper() : null;

  if ($('fieldset.informationitem-wrapper .table-wrapper tbody tr').length == 0) {
    $('fieldset.informationitem-wrapper .body').removeClass('hidden');
    $('fieldset.informationitem-wrapper .table-wrapper').addClass('hidden');
  }
}

// Relationships
function datasubjectOnChange($this) {
  var $this = $($this);
  var $relatedPiiWrapper = $('.pii-item');
  var $relatedPii = $('#pii');

  if ($this.val() == '') {
    return;
  }

  //$relatedPiiWrapper.addClass('loading');
  $relatedPii.empty();

  setTimeout(listDatasubjectPIIs, 700);

  function listDatasubjectPIIs() {
    $.get("/api/v1/datasubject/" + $this.val() + "/piis" , function (data) {
      $.each(data, function (i, v) {
        $relatedPii.append('<option value="' + v.id + '">' + v.name + '</option>');
      });
      $relatedPii.prop('disabled', false);
      //$relatedPiiWrapper.removeClass('loading');
    });
  }
}
