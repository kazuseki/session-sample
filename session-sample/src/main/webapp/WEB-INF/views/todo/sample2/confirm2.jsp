<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo Create Confirm</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">

</head>
<body>
    <h1>Todo 新規作成の確認画面</h1>
    
    <div id="createTodo">
        <h3>以下の入力で新規Todoを作成します。よければOKボタンを押してください。</h3>
        <ul>
            <!-- セッションにあるsessionTodoSample2Formを使用して画面に表示する。 -->
            <li>
                ${f:h(sessionTodoSample2Form.todoTitle)}
            </li>
        </ul>
    </div>
    
    <div style="display: inline-flex">
    
        <!-- OKボタンは登録実施して完了画面に遷移する -->
        <form:form method="post"
            action="${pageContext.request.contextPath}/todo/sample2/create2"
            modelAttribute="sessionTodoSample2Form">
            
            <form:button name="create_complete" value="complete">OK</form:button>
            &nbsp;&nbsp;
            <!-- キャンセルボタンはキャンセルして入力画面に戻る -->
            <form:button name="create_cancel" value="cancel">キャンセルして入力画面に戻る</form:button>
        </form:form>
    </div>
    
</body>
</html>