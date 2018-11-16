/**
 * GDPR
 */

$(document).ready(function() {
  logger.d('Loaded gdpr.js');
});

/*
 * Controllers
 */
var dataflow = {};

dataflow.show = function show(ctx, next) {
  logger.i('Current page set to: ' + ctx.pathname + '?' + ctx.querystring);
  $.post(ctx.canonicalPath, function(data) {
    $('#content').html(data);
    gdprDataFlows();
    next();
  });
};


function gdprDataFlows(){
  $.post({
    url: '/api/v1/gdpr/data-flow',
    contentType: "application/json; charset=utf-8"
  }, function(data) {
      logger.i(JSON.stringify(data.data));
      if (data.data.length != 0) {

        Highcharts.chart('chart-gdpr-data-flows', {

          title: {
            text: 'Data Flows'
          },
          subtitle: {
            text: 'Personally Identifiable Information -> Data Subject -> Purpose of Processing -> Lawfulness of Processing'
          },
          credits: {
            enabled: false
          },
          chart: {
            events: {
              load: function() {
                setTimeout(function(){$("tspan").each(function() {
                  if (this.innerHTML.indexOf(":") > 0){
                    this.innerHTML = this.innerHTML.substring(this.innerHTML.indexOf(":")+1);
                  }
                })}, 10);
              }
            }
          },
          plotOptions: {
            series: {
              cursor: 'pointer',
              point: {
                events: {
                  click: function() {
                    console.log(this);
                  }
                }
              }
            }
          },
          series: [{
            colors: ['#6287e8', '#328432', '#6fa86f', '#98c198', '#999999', '#111111'],
            keys: ['from', 'to', 'weight'],
            data: data.data,
            type: 'sankey',
            name: 'Personally Identifiable Information -> Data Subject -> Purpose of Processing -> Lawfulness of Processing'
          }]

        });

        $("#chart-gdpr-data-flows").highcharts().reflow();
      } else {

      }

  });
}
