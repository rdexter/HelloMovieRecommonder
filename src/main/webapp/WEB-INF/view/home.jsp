<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@taglib prefix="sec" --%>
<%-- 	uri="http://www.springframework.org/security/tags"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userName" value="${pageContext.request.userPrincipal.name}" />
<c:set var="like" value="${1 }" scope="page"></c:set>
<c:set var="dislike" value="${0 }" scope="page"></c:set>
<html>
<head>
<meta charset="utf-8">
<title>Recommendation Engine</title>
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Asap">
<link href="/MovieRecommonder/resources/css/bootstrap.min.css"
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
				<h2>
					<div class="pull-right">
						<a href="<c:url value='j_spring_security_logout'/>"><button
								class="btn btn-primary">logout</button></a>
					</div>
				</h2>
				<div class="page-header">
					<h1>Hello, ${userName}!</h1>
				</div>
				<h2>
					<div class="pull-right">
						<form
							action="${contextPath }/getUserRecommondation?user=${userName}&amp;pageNumber=${pageNumber}"
							method="post">
							<button class="btn btn-primary">Refresh</button>
						</form>
					</div>
					Recommended Movies
				</h2>
				<div class="row">
					<c:set var="i" value="0" scope="page"></c:set>
					<c:forEach items="${recommondedMovieList}" var="movie">
						<c:set var="i" value="${i+1 }" scope="page"></c:set>
						<c:if test="${i<=4 }">
							<div class="col-sm-3">
								<div class="thumbnail">
									<img src="${movie.url}">
									<div class="caption">
										<h4
											style="margin-bottom: 24px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
											<c:out value="${movie.title}" />
										</h4>
										<div class="row">
											<div class="col-xs-6">
												<form
													action="${contextPath }/updateMovieRating?user=${userName}&amp;movieId=<c:out value="${movie.id}"/>&amp;like=<c:out value="${like}"/>"
													method="post" class="text-center">
													<button class="btn btn-default">
														<span class="glyphicon glyphicon-thumbs-up"></span>
													</button>
												</form>
											</div>
											<div class="col-xs-6">
												<form
													action="${contextPath }/updateMovieRating?user=${userName}&amp;movieId=<c:out value="${movie.id}"/>&amp;like=<c:out value="${dislike}"/>"
													method="post" class="text-center">
													<button class="btn btn-default">
														<span class="glyphicon glyphicon-thumbs-down"></span>
													</button>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>

					</c:forEach>

				</div>
				<h2>
					<div class="pull-right">
						<form
							action="${contextPath }/next?user=${userName} &amp;pageNumber=${pageNumber}"
							method="post">
							<button class="btn btn-primary">nextPage</button>
						</form>
					</div>
					All Movies
				</h2>
				<div class="row">
					<c:set var="i" value="0" scope="page"></c:set>
					<c:forEach items="${movieList}" var="movie">
						<c:set var="i" value="${i+1 }" scope="page"></c:set>
						<c:if test="${i<=4 }">
							<div class="col-sm-3">
								<div class="thumbnail">
									<img src="${movie.url}">
									<div class="caption">
										<h4
											style="margin-bottom: 24px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
											<c:out value="${movie.title}" />
										</h4>
										<div class="row">
											<div class="col-xs-6">
												<form
													action="${contextPath }/updateMovieRating?user=${userName}&amp;movieId=<c:out value="${movie.id}"/>&amp;like=<c:out value="${like}"/>"
													method="post" class="text-center">
													<button class="btn btn-default">
														<span class="glyphicon glyphicon-thumbs-up"></span>
													</button>
												</form>
											</div>
											<div class="col-xs-6">
												<form
													action="${contextPath }/updateMovieRating?user=${userName}&amp;movieId=<c:out value="${movie.id}"/>&amp;like=<c:out value="${dislike}"/>"
													method="post" class="text-center">
													<button class="btn btn-default">
														<span class="glyphicon glyphicon-thumbs-down"></span>
													</button>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>

					</c:forEach>

					<h2>
						<div class="pull-right">
							<form
								action="${contextPath }/back?user=${userName} &amp;pageNumber=${pageNumber}"
								method="post">
								<button class="btn btn-primary">previousPage</button>
							</form>
						</div>
					</h2>

				</div>


			</div>
		</div>
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/MovieRecommonder/resources/js/bootstrap.min.js"></script>
</body>
</html>