<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

	<header class="main-header"> <span class="lang"> <a
		href="?lang=en"><img
			src="<c:url value="/resources/images/gb.png" />" /></a> <a
		href="?lang=ru"><img
			src="<c:url value="/resources/images/ru.png"/>" /></a>
		</span>

		<div class="pages-header">
			<a href="<c:url value='/main'/>"><spring:message
				code="label.HOME" /></a>
			<a href="<c:url value='/books'/>"><spring:message
				code="label.BOOKS" /></a>
			<a href="<c:url value='/notes'/>"><spring:message
				code="label.NOTES" /></a>
			<a href="<c:url value='/calendars'/>"><spring:message
				code="label.CALENDARS" /></a>
			<a href="<c:url value='/games'/>"><spring:message
				code="label.GAMES" /></a>
			<a href="<c:url value='/cards'/>"><spring:message
				code="label.CARDS" /></a>
		</div>
	</header>
</body>
</html>