<html>
<head>
<title>Confirm</title>
</head>
<body>
    <h1>Confirm</h1>
    <%-- (33) --%>
    <form:form action="${pageContext.request.contextPath}/wizard/save"
        modelAttribute="wizardForm">
        <div>
            Field1 : ${f:h(wizardForm.field1)}
        </div>
        <div>
            Field2 : ${f:h(wizardForm.field2)}
        </div>
        <div>
            Field3 : ${f:h(wizardForm.field3)}
        </div>
        <div>
            <form:button name="redoForm3">Back</form:button>
            <form:button>OK</form:button>
        </div>
    </form:form>
</body>
</html>