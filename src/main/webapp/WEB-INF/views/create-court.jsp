<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

    <jsp:include page="menu.jsp" />
    
    <h1>Create a court</h1>
    <form:form action="create-court" modelAttribute="court">
        <p>Id:
            <form:input path="id" placeholder="Id" required="required" />
            <form:errors path="id" cssClass="error" />
        </p>
        <p>Active:
            <form:input path="active" placeholder="Active" required="required" />
            <form:errors path="active" cssClass="error" />
        </p>
        <p><input type="submit" value="Create"></p>
    </form:form>

<jsp:include page="footer.jsp" />