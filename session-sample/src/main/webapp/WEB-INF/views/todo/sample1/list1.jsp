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
  <hr />
  <div id="todoList">
    <ul>
      <!-- c:forEachタグを用いて、Todoのリストを箇条書きで全て表示する -->
      <c:forEach items="${todos}" var="todo">
        <li><c:choose>
            <!-- 完了の場合(finished)、打ち消し線を装飾する -->
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