<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="head" />

    <p><a href="<c:url value='/create-user'/>">Create User</a></p>
    
    <p><a href="<c:url value='/home'/>">Home</a></p>
    
    <h1>User List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Username</th>
				<th>Email</th>
				<th>Password</th>
				<th>BirthDate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td>${user.birthDate}</td>
					<td><a href="<c:url value='/delete-user/${user.username}' />">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<c:import url="footer" />