<html>
<head>
<title>Wizard Form(2/3)</title>
</head>
<body>
    <h1>Wizard Form(2/3)</h1>
    <%-- (31) --%>
    <form:form action="${pageContext.request.contextPath}/wizard/save"
        modelAttribute="wizardForm">
        <form:label path="field2">Field2</form:label> :
        <form:input path="field2" />
        <form:errors path="field2" />
        <div>
            <form:button name="redoForm1">Back</form:button>
            <form:button name="form3">Next</form:button>
        </div>
    </form:form>
</body>
</html>