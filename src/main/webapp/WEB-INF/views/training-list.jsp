<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" />

<h1>Trainings</h1>

<p>
	<a href="<c:url value='/create-training'/>">Create Training</a>
</p>

<div class="table-responsive">
	<table class="table">
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
					<td><fmt:formatDate value="${training.startDate.time}"
							type="both" pattern="dd/MM/yyyy HH:mm:ss" /></td>
					<td><fmt:formatDate value="${training.finishDate.time}"
							type="both" pattern="dd/MM/yyyy HH:mm:ss" /></td>
					<td>${training.courtId}</td>
					<td>${training.trainerId}</td>
					<td><a
						href="<c:url value='/delete-training/${training.id}' />">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<jsp:include page="footer.jsp" />