<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css">
</head>
<body>
  <h1>Todo List</h1>
  <div>
    <a href="<c:url value='/todo/sample2/create2' />">新規Todo作成へ (URL /todo/sample2/create2)</a>
  </div>
  
  <!-- 結果メッセージを表示する -->
  <t:messagesPanel />
        
  <hr />
  
  <div id="todoList">
    <ul>
      <!-- c:forEachタグを用いて、Todoのリストを箇条書きで全て表示する -->
      <c:forEach items="${todos}" var="todo">
        <!-- 完了なら取り消し線を装飾する。未完了には何もしない。 -->
        <li><c:choose>
            <c:when test="${todo.finished}">
              <span class="strike"> ${f:h(todo.todoTitle)} </span>
            </c:when>
            <c:otherwise>
                                    ${f:h(todo.todoTitle)}
            </c:otherwise>
          </c:choose>
          <!-- 編集用のボタンを表示してformを送る -->
            <form:form
              action="${pageContext.request.contextPath}/todo/sample2/edit2"
              method="post"
              modelAttribute="sessionTodoSample2Form"
              cssClass="inline">  <!-- cssにより、Todoの横に表示させる -->
              <!-- リクエストパラメータで、表示しているtodoIdを送信する -->
                <form:hidden path="todoId"
                   value="${f:h(todo.todoId)}" />
                   <form:button>edit</form:button>
                </form:form>
        </li>
      </c:forEach>
    </ul>
  </div>
  
  <hr />
  <div>
      <a href="<c:url value='/' />">プロジェクトのTOPページへ戻る</a>
  </div>
</body>