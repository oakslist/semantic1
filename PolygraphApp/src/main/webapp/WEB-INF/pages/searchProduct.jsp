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
		<c:choose>
			<c:when test="${not empty productsList}">
				<h2>Found:</h2>
				<div class="items">
					<c:forEach items="${productsList}" var="product">
						<li><a
							href="<c:url value='/${product.category}/${product.shortName}'/>">${product.shortName}</a>
							<p>
								<img src="${product.link}" width="50" height="50">
							</p>
							<p>price: ${product.price}$</p>
							<p>quantity: ${product.quantity}</p></li>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<h2>Nothing was not found, try one more time :)</h2>
				<p>or use a category bar...</p>

			</c:otherwise>
		</c:choose>




	</div>
	<div class="main-aside">
		<jsp:include page="/WEB-INF/pages/include/aside.jsp" />
	</div>
	<div class="main-footer">
		<jsp:include page="/WEB-INF/pages/include/footer.jsp" />
	</div>

</body>
</html>