<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo Enter</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">

</head>
<body>
    <h1>Todo 変更画面</h1>
    <div id="todoEditForm">
        <!-- 結果メッセージを表示する。 -->
        <t:messagesPanel />

        <!-- 新規作成用のformを表示する。 -->
        <form:form
            action="${pageContext.request.contextPath}/todo/sample2/edit2"
            method="post"
            modelAttribute="sessionTodoSample2Form" > <!-- セッションにある TodoSample2Formの指定した属性名をバインド -->
           
          <c:choose>
            <c:when test="${!empty editTodoSample2Form}">
                <!-- form:inputタグでフォームのプロパティにバインドする。 -->
                <form:input path="todoTitle" value="${f:h(editTodoSample2Form.todoTitle)}" />
            </c:when>
            <c:otherwise>
                <form:input path="todoTitle" value="${f:h(sessionTodoSample2Form.todoTitle)}" />
            </c:otherwise>
          </c:choose>
          
            <!-- form:errorsタグで入力エラーがあった場合に表示する -->
            <form:errors path="todoTitle" cssClass="text-error" />
            
            <!-- form:buttonタグでボタンに設定。name属性に値を設定して、@RequestMappingのparams属性で取得できるようにする。 -->
            <form:button name="event_proceed" value="proceed">次へ</form:button>
        </form:form>
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