<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="main-header">
		<jsp:include page="/WEB-INF/pages/include/header.jsp" />
	</div>
	<div class="filter-bar">
		<jsp:include page="/WEB-INF/pages/include/filterBar.jsp" />
	</div>
	<div class="main">
		<h2>${product.shortName}</h2>
		<div class="items">
			<p><img src="${product.link}" width="200" height="200"></p>
			<p>price: ${product.price}$</p>
			<p>quantity: ${product.quantity}</p>
			<input type="submit" value="Buy Now">
		</div>
		<h3>See Also:</h3>
		<div class="also">
			<ul>
				<c:forEach items="${seeAlsoList}" var="link">
					<li><a href="${link.subject}">${link.subjectShort}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="main-aside">
		<jsp:include page="/WEB-INF/pages/include/aside.jsp" />
	</div>
	<div class="main-footer">
		<jsp:include page="/WEB-INF/pages/include/footer.jsp" />
	</div>

</body>
</html>