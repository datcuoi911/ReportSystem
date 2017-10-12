<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<div align="center">
		<h1>User List</h1>
		<table border="1">
			<th>No</th>
			<th>Username</th>
			<th>Password</th>

			<c:forEach items="${list}" var="u" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${u.username}</td>
					<td>${u.password}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="_footer.jsp" />

</body>
</html>