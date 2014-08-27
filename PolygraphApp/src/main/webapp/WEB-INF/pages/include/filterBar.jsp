<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value="/resources/js/script.js" />"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="filterId" method="POST" action="<c:url value='/filterQuery/'/>">
		<p><b>Filters:</b>&nbsp;&nbsp;&nbsp;Item name: <input id="textId" type="text" name="itemName"> 
		<input type="submit" value="Search"></p>
	</form>
	<script>
		myFilterFormAction("textId", "filterId");
	</script>
</body>
</html>