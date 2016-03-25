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
    
    <h1>Create user</h1>
    
    <form:form action="create-user" modelAttribute="user">
        <p>Username:
            <form:input path="username" placeholder="Username" required="required" />
            <form:errors path="username" cssClass="error" />
        </p>
        <p>Email:
            <form:input path="email" placeholder="Email" required="required" />
            <form:errors path="email" cssClass="error" />
        </p>
         <p>Password:
            <form:input path="password" placeholder="Password" required="required" />
            <form:errors path="password" cssClass="error" />
        </p>
         <p>BirthDate:
            <form:input path="birthDate" placeholder="BirthDate"/>
            <form:errors path="birthDate" cssClass="error" />
        </p>
        <p><input type="submit" value="Create"></p>
    </form:form>

</body>
</html>