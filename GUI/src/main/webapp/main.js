/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$('document').ready(function(){
  //var data=[];
  var plot_container = $("#Plot");
  var limit=100;
  var data = new Array(limit);
  for(var i=0; i<limit; i++){
     data[i]=[i, 0];//[i+1-this.plot_limit, 0];
  }
  var config = {
            yaxis: {
                min: 0,
                max: 1
            },
            xaxis: {
                mode: "time",
    timeformat: "%Y/%m/%d"
            }};
  var plot=$.plot(plot_container, [data], config);
   
  
  setInterval(function(){
  $.ajax({
  url: 'http://localhost:8080/GUI/GUIEngine',
  //data: data,
  success: function(dt){
      console.log("aaa")
      console.log(dt)
  },
  error: function(resp){
      //console.log("data errror")
      console.log(resp.responseText)
      data=data.concat(JSON.parse(resp.responseText)).slice(1);
    //  plot.de
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