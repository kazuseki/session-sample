<html>
<head>
<title>Wizard Form(1/3)</title>
</head>
<body>
    <h1>Wizard Form(1/3)</h1>
    <form:form action="${pageContext.request.contextPath}/wizard/save"
        modelAttribute="wizardForm">
        <form:label path="field1">Field1</form:label> :
        <form:input path="field1" />
        <form:errors path="field1" />
        <div>
            <form:button name="form2">Next</form:button>
        </div>
    </form:form>
</body>
</html>