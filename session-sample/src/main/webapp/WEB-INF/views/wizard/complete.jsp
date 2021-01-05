<html>
<head>
<title>Complete</title>
</head>
<body>
    <h1>Complete</h1>
    <div>
        <div>
            ID : ${f:h(entity.wizardId)}
        </div>
        <div>
            Field1 : ${f:h(entity.field1)}
        </div>
        <div>
            Field2 : ${f:h(entity.field2)}
        </div>
        <div>
            Field3 : ${f:h(entity.field3)}
        </div>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/wizard/create">
            Continue operation of Create
        </a>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/wizard/${entity.wizardId}/update">
            Continue operation of Update
        </a>
    </div>
    <div>
      <a href="<c:url value='/' />">プロジェクトのTOPページへ戻る</a>
    </div>
</body>
</html>