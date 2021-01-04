<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo Create Complete</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">

</head>
<body>
    <h1>Todo 新規作成 完了画面</h1>
    <!-- 結果メッセージを表示する  -->
    <t:messagesPanel />
    
    <div id="completeCreateTodo">
        <h3>以下のTodoが登録されました。</h3>
        <ul>
            <!-- 最後に渡されたTodoFormを表示する。 -->
            <li>
                ${f:h(sessionTodoSample2Form.todoTitle)}
            </li>
        </ul>
    </div>
    
    <hr />
    
    <div>
        <ul>
            <li>
                <a href="<c:url value='/todo/sample2/list2' />">一覧表示へ戻る</a>
            </li>
            <li>
                <a href="<c:url value='/' />">TOPページに戻る</a>
            </li>
        </ul>
    </div>
    
</body>
</html>