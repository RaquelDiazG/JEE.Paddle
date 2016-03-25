<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC. </title>
<style>.error { color: red;}</style>
</head>
<body>
    <a href="<c:url value="/home"/>">Home</a>
    <h1>Create a training</h1>
    <form:form action="create-training" modelAttribute="training">
        <p>Id:
            <form:input path="id" placeholder="Id" required="required" />
            <form:errors path="id" cssClass="error" />
        </p>
        <p>Start Date:
            <form:input path="startDate" placeholder="Start Date" required="required" />
            <form:errors path="startDate" cssClass="error" />
        </p>
        <p>Finish Date:
            <form:input path="finishDate" placeholder="Finish Date" required="required" />
            <form:errors path="finishDate" cssClass="error" />
        </p>
        <p>Court:
            <form:input path="court" placeholder="Court" required="required" />
            <form:errors path="court" cssClass="error" />
        </p>
        <p>Trainer:
            <form:input path="trainer" placeholder="Trainer" required="required" />
            <form:errors path="trainer" cssClass="error" />
        </p>
        <p><input type="submit" value="Create"></p>
    </form:form>



</body>
</html>