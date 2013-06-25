/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var onSiteLoggerString = "<p><strong>== Logger Initialization ==</strong></p>";

var actualIntervalData;

function onSiteLogger(info) {
    var d=new Date();
    var time = "^"+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+"."+d.getMilliseconds()+">>"
    onSiteLoggerString="<p>" + time+ info +"</p>"+onSiteLoggerString;
    $("#commandStatus").html(
        onSiteLoggerString
        );
}


$('document').ready(function(){
 
    RefreshMesurmentsButtons (this) ;
})

function RefreshMesurmentsButtons (form) {
    $.ajax({
    url: baseMonitorURL,
    success: function(dt){
        onSiteLogger("OnSucces refreshbutton recive data! Something goes realy bad ... "+dt);
    },
    error: function(resp){
        onSiteLogger("OnError refreshbutton recive data! "+resp);
        
        var allLinks = $(resp.responseText).filter('a');

        var buttonsHtml = "";
        allLinks.each(function (entry) {
          buttonsHtml = buttonsHtml + '<form action="" onsubmit="return PlotSelected(this);">' ;
          buttonsHtml = buttonsHtml +
                  '<button type="submit" class="groovybutton" >' + allLinks[entry].text +  '</button>';

          buttonsHtml = buttonsHtml + '</form>';        
        });
        $('#MesurmentsButtons').html(buttonsHtml);
        onSiteLogger("OnError refreshbutton refreshing compleat :D");
    },
    dataType: 'text/html'
  });
    return false;
}


function PlotSelected(form) {

 var data=[];
  var plot_container = $("#Plot");
  var limit=100;
//  var data = new Array(limit);
//  for(var i=0; i<limit; i++){
//     data[i]=[i, 0];//[i+1-this.plot_limit, 0];
//  }
/**
 * %a: weekday name (customizable)
%b: month name (customizable)
%d: day of month, zero-padded (01-31)
%e: day of month, space-padded ( 1-31)
%H: hours, 24-hour time, zero-padded (00-23)
%I: hours, 12-hour time, zero-padded (01-12)
%m: month, zero-padded (01-12)
%M: minutes, zero-padded (00-59)
%q: quarter (1-4)
%S: seconds, zero-padded (00-59)
%y: year (two digits)
%Y: year (four digits)
%p: am/pm
%P: AM/PM (uppercase version of %p)
%w: weekday as number (0-6, 0 being Sunday)
 */
  var config = {
            yaxis: {
                min: 0,
                max: 1
            },
            xaxis: {
                mode: "time",
                timeformat: "%M:%S"
            }};
  var plot=$.plot(plot_container, [data], config);
  
  
  clearInterval(actualIntervalData);
  actualIntervalData = setInterval(function(){
  $.ajax({
  url: baseMonitorURL+'?id='+form[0].textContent,
  success: function(dt){
      onSiteLogger("OnSucces timer recive data! Something goes realy bad ... "+dt);
  },
  error: function(resp){
        onSiteLogger("OnError timer recive data! "+resp);
      new_values=JSON.parse(resp.responseText);
      $('#PlotName').html(new_values.Name);
      
      var plotData = new_values.data;
      var toPlot = [];
      $(plotData).each( function (entry) {
          var formatedTime = new Date(0);
          formatedTime.setUTCMilliseconds( plotData[entry].timestamp );
          var timeToPlot = formatedTime.getMinutes() + ':' + formatedTime.getSeconds();
          toPlot[entry]=[timeToPlot , plotData[entry].data ] ;
      }
          );
     var plot_container = $("#Plot");
      plot=$.plot(plot_container, toPlot  , config);
      onSiteLogger("OnError timer refreshing compleat :D");
    },
  dataType: 'application/json'
});
  }, 100);
  
return false;
}