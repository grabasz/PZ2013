/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$('document').ready(function(){
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
  
  var makeSortedUniqeArray=function(arr){
      
    arr = arr.sort(function (a, b) { return a[0] - b[0]; });
    var ret = [arr[0]];
    for (var i = 1; i < arr.length; i++) { // start loop at 1 as element 0 can never be a duplicate
        if (arr[i-1][0] !== arr[i][0]) {
            ret.push(arr[i]);
        }
    }
    return ret;    
  }
  
  setInterval(function(){
  $.ajax({
  url: 'http://localhost:8080/GUI/GUIEngine',
  //data: data,
  success: function(dt){
      console.log("aaa")
      console.log(dt)
  },
  error: function(resp){
      new_values=JSON.parse(resp.responseText)
      now =new Date();
      
      
      
      data=makeSortedUniqeArray(data.concat(new_values)
      ).filter(function(elem, index, array){
         return (now.valueOf()-120*1000<elem[0] );
      });//.slice(1);
      
      
      plot=$.plot(plot_container, [data], config);
      //plot.setData([data]);
      //plot.draw();
      //console.log(data)
      
      
      
  },
  //crossDomain: true,
  dataType: 'application/json'
});
  }, 100);
  
    
})