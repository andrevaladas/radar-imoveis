<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	    //$("#contentFrame").attr("src","/page/combo");
		$("#content").load("/component/filter", function () {
	        alert('Load was performed.');
	    });
	})
	</script>
</head>
<body>
	<!--iframe id="contentFrame"></iframe-->
	<div id="content"></div>
</body>
</html>