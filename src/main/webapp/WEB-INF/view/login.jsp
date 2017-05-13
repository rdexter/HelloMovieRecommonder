<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Recommendation Engine</title>
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Asap">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<style type="text/css">
* {
	font-family: 'Asap';
}
</style>
</head>
<body>
	<div style="margin-top: 16px;" class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="row">
					<div class="col-xs-4 col-xs-offset-4">
						<h1>Hello, stranger!</h1>
						<p>Enter username and password to get recommondation; this is
							utopia.</p>
						<form name="f" action="<c:url value='j_spring_security_check'/>"
							method="POST">
							<div class="form-group">
								<input type='text' name='j_username' placeholder="Username"
									class="form-control"> <input type='password'
									name='j_password' placeholder="Password" class="form-control">
							</div>
							<!-- 							<input name="submit" type="submit">&nbsp;<input
										name="reset" type="reset"> -->
							<button class="btn btn-primary">Login</button>
						</form>
						<a href="<c:url value='/registration'/>"><button
									class="btn btn-primary">register your self</button></a>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>