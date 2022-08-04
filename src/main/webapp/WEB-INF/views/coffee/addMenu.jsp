<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Add Coffee Menu</title>
<!-- 0804 -->
</head>
<body>

	<h3>Ŀ�� �޴� �߰��ϱ�</h3>
	
	<!--  <form action="<c:url value="/hello/coffee/add"/>" method="POST">-->
	<form action="" id="form1" method="POST">
		name : <input type="text" name="name" value="Americano" /> <br />
		price : <input type="text" name="price" value="1700" /> <br />
		hot : <input type="radio" name="hot" value="true" />
		<input type="radio" name="hot" value="false" /><br />
		<input type="submit" id="submit1" value="submit!" />
		
	</form>
	<button id="btn1">add1</button>
	<button id="btn2" form="form1" formaction="<c:url value="/hello/coffee/add2"/>">add2</button>
	<button id="btn3" form="form1" formaction="<c:url value="/hello/coffee/add3"/>">add3</button>
	
	<script>
	// ���� (1) add1�� ������ /springmvc/hello/coffee/add1�� submit
	const btn1 = document.getElementById('btn1');
	add = document.getElementById('form1');
	submit = document.getElementById('submit1');
	
	
	btn1.addEventListener('click', (e) => {
		// location.href = '/springmvc/hello/coffee/add1'; // �޴°� ����Ʈ�ε� �����°� ���̶� �����߻�
		// add.setAttribute('action', '/springmvc/hello/coffee/add1'); �̰͵� �ǰ� 
		// �̷��Ե� �ȴ�
		add.action='/springmvc/hello/coffee/add1';
		add.submit();
	});
	
	// (2) add2�� ������ /springmvc/hello/coffee/add2�� submit
//	const btn2 = document.getElementById('btn2');
//	btn2.addEventListener('click', () => {
//		//location.href = '/springmvc/hello/coffee/add2';
//		add.setAttribute('action', '/springmvc/hello/coffee/add2');
//		add.submit();
//	});
	
	
	</script>

</body>
</html>