<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

    <h1>Operation Ok</h1>

    <h3>Se ha creado satisfactoriamente el siguiente elemento:</h3>
    
    <p>(${elemento})</p>

    <p><a href="<c:url value='/home' />">Volver</a></p>

<jsp:include page="footer.jsp" />