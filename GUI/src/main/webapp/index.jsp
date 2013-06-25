<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    
<html>
    <head>
        <script type="text/javascript" src="jquery-1.8.2.min.js" ></script>
        <script type="text/javascript" src="jquery.utils.js" ></script>
        <script type="text/javascript" src="jquery.flot.min.js" ></script>
        
        <script type="text/javascript">
            // base url to monitor servlet
            var baseMonitorURL = "http://localhost:8080/Monitor/Mesurments";
        </script>
        
        <script type="text/javascript" src="main.js" ></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
        <style type="text/css">
            #Plot{
                width: 400px;
                height: 300px;
            }
            
            body {
                font-size: 14px;
                color: #FF0099;  
                margin: 0px;
                padding: 0px;
                font-family: 'Consolas','Courier New',Courier,monospace;
            }
            
            div.header {
                background-color: #000000;
                background-image: url("http://placekitten.com/850/350");
                background-repeat: no-repeat;
                background-size: auto;
                background-position: 50% 00%;
                text-align:center;
                float:none;
                top:0px;
                height:350px;
                width:850px;
                margin-left:auto;
                margin-right:auto;
                margin-bottom: auto;
            }
            
            div.mainContent { 
                float:none;            
                margin-left:auto;
                margin-right:auto;
                margin-bottom: auto;
                position:relative;
                top:80px;
                width: 850px;
                padding: 5px;
            }

            button.groovybutton
            {
               font-size:24px;
               font-weight:bold;
               color:#FF0099;
               background-color:#FFFFFF;
               border-style:dashed;
               border-width:3px;
            }


        </style>
        <title>Monitor GUI</title>
    </head>
    <body>
        <div class="header"> </div>
        
        <div id="commandStatus" class="mainContent" ></div>
        
        <div class="mainContent"> 
            <h1>Monitor GUI</h1>
            <h3 id="PlotName">Please select plot</h3>
            <div id="Plot" class="mainContent"></div>
            <form action="" onsubmit="return RefreshMesurmentsButtons(this);">
                <button type="submit" class="groovybutton" >Refresh mesurements list!</button>
            </form>
        </div>
        <div id="MesurmentsButtons"> </div>
    </body>
</html>

