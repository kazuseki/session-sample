<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo List</title>
<style type="text/css">
.strike {
  text-decoration: line-through;
}
</style>
</head>
<body>
  <h1>Todo List</h1>
  <div id="todoForm">
        <!-- 結果メッセージを表示する -->
        <t:messagesPanel />

        <!-- 新規作成処理用のformを表示する -->
        <form:form
           action="${pageContext.request.contextPath}/todo/sample1/create1"
            method="post" modelAttribute="todoForm">
            <form:input path="todoTitle" /><!-- path属性と、modelAttributeのフォームのプロパティを、一致させてバインドする -->
            <form:errors path="todoTitle" /><!-- 入力エラーがあった場合に表示する。 -->
            <form:button>Create Todo</form:button>
        </form:form>
    </div>
  
  <hr />
  
  <div id="todoList">
    <ul>
      <!-- c:forEachタグを用いて、Todoのリストを箇条書きで全て表示する -->
      <c:forEach items="${todos}" var="todo">
        <li><c:choose>
            <c:when test="${todo.finished}">
              <span class="strike"> ${f:h(todo.todoTitle)} </span>
            </c:when>
            <c:otherwise>
                                    ${f:h(todo.todoTitle)}
            </c:otherwise>
          </c:choose></li>
      </c:forEach>
    </ul>
  </div>
</body>