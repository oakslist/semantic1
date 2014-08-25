<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<H1>Hello, my friednd!</H1>
	<div id="nav">
		<!-- <ul>
			<li><a href="#">Books</a></li>
			<li><a href="#">Cards</a></li>
			<li><a href="#">Games</a></li>
		</ul> -->
		<ul>
			<li><a href="<c:url value='/friends'/>">Friends</a></li>
			<li><a href="<c:url value='/classes'/>">Classes</a></li>
			<li><a href="<c:url value='/all'/>">All</a></li>
		</ul>
	</div>
	<br>
	<table class="users-table">
		<thead>
			<tr>
				<th>s</th>
				<th>p</th>
				<th>o</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fullTripleList}" var="triple">
				<tr>
					<td><c:out value="${triple.subjectShort}" /></td>
					<td><c:out value="${triple.predicateShort}" /></td>
					<td><c:out value="${triple.objectShort}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<table class="users-table">
		<thead>
			<tr>
				<th>s</th>
				<th>p</th>
				<th>o</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fullTripleList}" var="triple">
				<tr>
					<td><c:out value="${triple.subject}" /></td>
					<td><c:out value="${triple.predicate}" /></td>
					<td><c:out value="${triple.object}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>