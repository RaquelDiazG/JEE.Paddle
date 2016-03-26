<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

    <jsp:include page="menu.jsp" />

    <p><a href="<c:url value='/create-court'/>">Create Court</a></p>
    
    <h1>Court List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Active</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courtList}" var="court">
				<tr>
					<td>${court.id}</td>
					<td>${court.active}</td>
					<td>
						<a href="<c:url value='/activate-court/${court.id}' />">Activate</a> | 
						<a href="<c:url value='/deactivate-court/${court.id}' />">Deactivate</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<jsp:include page="footer.jsp" />