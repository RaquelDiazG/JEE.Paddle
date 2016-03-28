<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<h1>Create a court</h1>
<form:form action="create-court" modelAttribute="court" role="form"
	class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-2"> Id:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="id" placeholder="Id"
				required="required" />
			<form:errors path="id" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> Active:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="active" placeholder="Active"
				required="required" />
			<form:errors path="active" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input class="btn btn-default" type="submit" value="Create">
		</div>
	</div>
</form:form>

<jsp:include page="footer.jsp" />