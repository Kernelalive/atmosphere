function header(ctx, next) {
  $.post('/header', function(data) {
    $('#header-wrapper').html(data);
    next();
  });
}

function noHeader(ctx, next) {
  $('#header-wrapper').html('');
  next();
}