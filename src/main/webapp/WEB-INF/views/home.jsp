<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="header.jsp" />

<h2>Práctica para la asignatura "Desarrollo de Aplicaciones Web
	Distribuidas de Código Abierto" (JEE)</h2>
	
<br/>

<h4>Diagramas</h4>
<p>API</p>
<img src="<c:url value='/static/images/business.gif'/>" class="img-responsive"/>
<p>Daos</p>
<img src="<c:url value='/static/images/daos.gif'/>" class="img-responsive"/>
<p>Exceptions</p>
<img src="<c:url value='/static/images/exceptions.gif'/>" class="img-responsive"/>

<br/>

<h4>Test</h4>
<img src="<c:url value='/static/images/test_api.PNG'/>" class="img-responsive"/>
<img src="<c:url value='/static/images/test_daos.PNG'/>" class="img-responsive"/>
<img src="<c:url value='/static/images/test_entity.PNG'/>" class="img-responsive"/>
<br/>

<c:import url="footer.jsp" />