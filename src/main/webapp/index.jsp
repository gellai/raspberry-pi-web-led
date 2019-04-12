<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Raspberry Pi LED Control</title>
        <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.slim.min.js"></script>               
        <script type="text/javascript" src="https://cdn.jsdelivr.net/sockjs/1.1.4/sockjs.min.js"></script>
        <c:url value="/css/style.css" var="cssStyle" />
        <link href="${cssStyle}" rel="stylesheet" />
    </head>
    <body onload="connect();">
	   <div class="header">
		  <h1>Raspberry Pi Web LED</h1>
	   </div>
	   <div class="slider-container">
	       <input type="range" min="0" max="100" value="0" class="slider" id="redSlider">
	       <input type="range" min="0" max="100" value="0" class="slider" id="greenSlider">
           <input type="range" min="0" max="100" value="0" class="slider" id="blueSlider">
	   </div>
	   <div class="footer">RED:<span id="red-value">0</span>%  |  GREEN:<span id="green-value">0</span>%  |  BLUE:<span id="blue-value">0</span>%</div>
	   <c:url value="/js/app.js" var="jsApp" />
       <script type="text/javascript" src="${jsApp}"></script>
    </body>
</html>