<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
</head>
<body>
  <p><strong>${formData.name}  <!-- JSP 폼 에서 커맨드 객체 사용 -->
    회원 가입을 완료했습니다.</strong></p>
<p><a href="<c:url value='/main'/>">[첫 화면 이동]</a></p>
</body>
</html>