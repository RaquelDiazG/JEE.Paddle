<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="head.jsp" />

    <jsp:include page="menu.jsp" />
    
    <p><a href="<c:url value='/create-user'/>">Create User</a></p>
    
    <h1>User List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Username</th>
				<th>Email</th>
				<th>Password</th>
				<th>Birth Date</th>
				<th>Rol</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td><fmt:formatDate value="${user.birthDate.time}" type="date" pattern="dd/MM/yyyy" /></td>
					<td>
						<c:forEach items="${authorizationList}" var="rol">
						   <c:if test="${rol.user.username == user.username}">
						       ${rol.role}
						   </c:if>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<jsp:include page="footer.jsp" />