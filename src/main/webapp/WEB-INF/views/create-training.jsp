<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

   <jsp:include page="menu.jsp" />
    
    <h1>Create a training</h1>
    <form:form action="create-training" modelAttribute="training">
        <p>Id:
            <form:input path="id" placeholder="Id" required="required" />
            <form:errors path="id" cssClass="error" />
        </p>
        <p>Start Date:
            <form:input path="startDate" placeholder="dd/MM/yyyy HH:mm:ss" required="required" />
            <form:errors path="startDate" cssClass="error" />
        </p>
        <p>Finish Date:
            <form:input path="finishDate" placeholder="dd/MM/yyyy HH:mm:ss" required="required" />
            <form:errors path="finishDate" cssClass="error" />
        </p>
        <p>Court:
            <form:input path="courtId" placeholder="Court Id" required="required" />
            <form:errors path="courtId" cssClass="error" />
        </p>
        <p>Trainer:
            <form:input path="trainerId" placeholder="Trainer Id" required="required" />
            <form:errors path="trainerId" cssClass="error" />
        </p>
        <p><input type="submit" value="Create"></p>
    </form:form>

<jsp:include page="footer.jsp" />