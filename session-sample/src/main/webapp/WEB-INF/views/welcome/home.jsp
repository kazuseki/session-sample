<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
  <div id="wrapper">
    <h1>Hello world!</h1>
    <p>The time on the server is ${serverTime}.</p>
  </div>
  <div>
    <ul>
      <li><a href="<c:url value='/todo/sample1/list1' />">sample1 のURL /todo/sample1/list1 へ (TodoSample1Controllerを利用)</a></li>
      <li><a href="<c:url value='/todo/sample2/list2' />">sample2 のURL /todo/sample2/list2 へ (TodoSample2Controllerを利用)</a></li>
      <li><a href="<c:url value='/todo/sample3/list3' />">sample3 のURL /todo/sample3/list3 へ (TodoSample3Controllerを利用)</a></li>
    </ul>
  </div>
</body>
</html>
