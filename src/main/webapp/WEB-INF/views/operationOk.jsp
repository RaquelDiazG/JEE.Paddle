<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC.RegistrationSuccess</title>
</head>

<body>
    <h1>Operation Ok</h1>

    <h3>Se ha creado satisfactoriamente el siguiente elemento:</h3>
    
    <p>(${elemento})</p>

    <p><a href="<c:url value='/home' />">Volver</a></p>

</body>
</html>