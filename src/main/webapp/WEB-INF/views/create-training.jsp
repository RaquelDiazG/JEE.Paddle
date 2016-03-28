<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<h1>Create a training</h1>
<form:form action="create-training" modelAttribute="training"
	role="form" class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-2"> Id:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="id" placeholder="Id"
				required="required" />
			<form:errors path="id" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> Start Date:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="startDate"
				placeholder="dd/MM/yyyy HH:mm:ss" required="required" />
			<form:errors path="startDate" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> Finish Date:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="finishDate"
				placeholder="dd/MM/yyyy HH:mm:ss" required="required" />
			<form:errors path="finishDate" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> Court:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="courtId"
				placeholder="Court Id" required="required" />
			<form:errors path="courtId" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> Trainer:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="trainerId"
				placeholder="Trainer Id" required="required" />
			<form:errors path="trainerId" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input class="btn btn-default" type="submit" value="Create">
		</div>
	</div>
</form:form>

<jsp:include page="footer.jsp" />