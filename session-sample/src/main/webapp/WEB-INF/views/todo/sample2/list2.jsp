<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css">
</head>
<body>
  <h1>Todo List</h1>
  
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
        </li>
      </c:forEach>
    </ul>
  </div>
  
  <hr />
  <div>
      <a href="<c:url value='/' />">プロジェクトのTOPページへ戻る</a>
  </div>
</body>