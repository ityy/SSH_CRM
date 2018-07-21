<%--
  Created by IntelliJ IDEA.
  User: yanga
  Date: 2018/5/1
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<jsp:forward page="${pageContext.request.contextPath}/IndexAction"/>--%>

<%
    response.sendRedirect(request.getContextPath() + "/IndexAction");
%>

</body>
</html>
