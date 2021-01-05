<html>
<head>
<title>Wizard Form(3/3)</title>
</head>
<body>
    <h1>Wizard Form(3/3)</h1>
    <%-- (32) --%>
    <form:form action="${pageContext.request.contextPath}/wizard/save"
        modelAttribute="wizardForm">
        <form:label path="field3">Field3</form:label> :
        <form:input path="field3" />
        <form:errors path="field3" />
        <div>
            <form:button name="redoForm2">Back</form:button>
            <form:button name="confirm">Confirm</form:button>
        </div>
    </form:form>
</body>
</html>