<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title></title>
<!-- 0808 수업 -->
</head>
<body>

	<h3>/fileupload/form</h3>

<!-- 
	파일을 보내기 위해서는 enctype을 multipart/form-data로 설정해야 한다.
	파일을 보낼때는 무조건 post 방식 사용  
-->
<form action="./" method="post" enctype="multipart/form-data"> 
	원하는 파일 선택 : <input type="file" name="file"/>
	<input type="submit" value="등록" />
</form>

</body>
</html>