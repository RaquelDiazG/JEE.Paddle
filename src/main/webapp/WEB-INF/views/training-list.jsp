<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="head" />

    <p><a href="<c:url value='/create-training'/>">Create Training</a></p>
    <p><a href="<c:url value='/home'/>">Home</a></p>
    
    <h1>Training List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Start Date</th>
				<th>Finish Date</th>
				<th>Court</th>
				<th>Trainer</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${trainingList}" var="training">
				<tr>
					<td>${training.startDate}</td>
					<td>${training.finishDate}</td>
					<td>${training.court}</td>
					<td>${training.trainer}</td>
					<td><a href="<c:url value='/delete-training/${training}' />">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<c:import url="footer" />