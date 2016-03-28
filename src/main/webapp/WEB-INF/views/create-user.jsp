<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<h1>Create user</h1>

<form:form action="create-user" modelAttribute="user" role="form"
	class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-2"> Username:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="username"
				placeholder="Username" required="required" />
			<form:errors path="username" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> Email:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="email" placeholder="Email"
				required="required" />
			<form:errors path="email" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> Password:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="password"
				placeholder="Password" required="required" />
			<form:errors path="password" cssClass="error" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"> BirthDate:</label>
		<div class="col-sm-10">
			<form:input class="form-control" path="birthDate"
				placeholder="dd/mm/yyyy" required="required" />
			<form:errors path="birthDate" cssClass="error" />
		</div>
	</div>
	 <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
			<input class="btn btn-default" type="submit" value="Create">
		</div>
	</div>
</form:form>

<jsp:include page="footer.jsp" />