<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="head.jsp" />

   <jsp:include page="menu.jsp" />
    
    <p><a href="<c:url value='/create-training'/>">Create Training</a></p>
    
    <h1>Training List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Start Date</th>
				<th>Finish Date</th>
				<th>Court</th>
				<th>Trainer</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${trainingList}" var="training">
				<tr>
					<td><fmt:formatDate value="${training.startDate.time}" type="date" dateStyle="short" /></td>
					<td><fmt:formatDate value="${training.finishDate.time}" type="date" dateStyle="short" /></td>
					<td>${training.court.courtId}</td>
					<td>${training.trainer.username}</td>
					<td><a href="<c:url value='/delete-training/${training.id}' />">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<jsp:include page="footer.jsp" />