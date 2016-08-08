<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List is</title>
</head>
<body>
	<table align="left" border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Age</th>
		</tr>

		<c:forEach items="${EmployeeDto}" var="lists">
			<tr>

				<td><c:out value="${lists.id}" /></td>
				<td><c:out value="${lists.name}" /></td>
				<td><c:out value="${lists.age}" /></td>


			</tr>
		</c:forEach>
	</table>
</body>
</html>