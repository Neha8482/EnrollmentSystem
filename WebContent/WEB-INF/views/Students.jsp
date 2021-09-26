<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel List</title>
</head>
<body>

<table border="2" width="70%" cellpadding="2">
	<thead>
	<th>NAME</th>
	<th>CITY</th>
	<th>COST FOR 2</th>
	<th>BOOK</th>
	</thead>
	
	<tbody>
	<c:forEach var="stud" items="${studentsList}">
	<tr>
		<td>${stud.firstName}</td>
		<td>${stud.lastname}</td>
		<td>${stud.enrollment_number}</td>
	</tr>
	</c:forEach>
	</tbody>
	
</table>
</body>
</html>