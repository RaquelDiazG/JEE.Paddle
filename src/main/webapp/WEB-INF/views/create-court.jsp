<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC. CreateUser</title>
<style>.error { color: red;}</style>
</head>
<body>
    <a href="<c:url value="/home"/>">Home</a>
    <h1>Create a court</h1>
    <form:form action="create-court" modelAttribute="court">
        <p>Id:
            <form:input path="courtId" placeholder="Id" required="required" />
            <form:errors path="courtId" cssClass="error" />
        </p>
        <p>Active:
            <form:input path="active" placeholder="Active" required="required" />
            <form:errors path="active" cssClass="error" />
        </p>
        <p><input type="submit" value="Create"></p>
    </form:form>



</body>
</html>