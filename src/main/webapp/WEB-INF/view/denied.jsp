<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1 id="banner">Unauthorized Access !!</h1>

		<hr />

		<c:if test="${not empty error}">
			<div style="color: red">
				Your fake login attempt was bursted, dare again !!<br /> Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>

		<p class="message">Access denied!</p>
		<a href="/MovieRecommonder/login">Go back to login page</a>
	</div>
</body>
</html>