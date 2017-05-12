
<%
	session.invalidate();
%>
You are now logged out!!
<html>
<head>
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Asap">
<link href="/MovieRecommonder/resources/css/bootstrap.min.css"
	rel="stylesheet">
<script src="/MovieRecommonder/resources/js/bootstrap.min.js"></script>
<style type="text/css">
* {
	font-family: 'Asap';
}
</style>
</head>
<body>
	<div style="margin-top: 16px;" class="container">
		<a href="${pageContext.request.contextPath}/login">go back</a>
	</div>
</body>
</html>