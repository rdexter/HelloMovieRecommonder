<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@taglib prefix="sec" --%>
<%-- 	uri="http://www.springframework.org/security/tags"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<title>Recommendation Engine</title>
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Asap">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.0.min.js"></script>
<style type="text/css">
* {
	font-family: 'Asap';
}
</style>
</head>
<body>
	<h6>
		<a href="<c:url value='j_spring_security_logout'/>">Click here to
			logout</a>
	</h6>
	<div style="margin-top: 16px;" class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="page-header">
					<h1>Hello, ${msg}!</h1>
				</div>
				<h2>
					<div class="pull-right">
						<form action="/refresh?user=priya" method="post">
							<button class="btn btn-primary">Refresh</button>
						</form>
					</div>
					Recommended Movies
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
													action="/like?movie=<c:out value="${movie.id}"/>&amp;user=<c:out value="${msg}"/>"
													method="post" class="text-center">
													<button class="btn btn-default">
														<span class="glyphicon glyphicon-thumbs-up"></span>
													</button>
												</form>
											</div>
											<div class="col-xs-6">
												<form
													action="/dislike?movie=<c:out value="${movie.id}"/>&amp;user=<c:out value="${msg}"/>"
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
				<!-- 		<div class="row">
					<div class="col-xs-12">
						<h2>All Movies</h2>
					</div>
					<div class="row" id="all">
					
				</div>
				<ul class="pagination text-center">
  <li><a onclick="event.preventDefault(); movies(12,1);">1</a></li>
  <li><a onclick="event.preventDefault(); movies(12,2);">2</a></li>
  <li><a onclick="event.preventDefault(); movies(12,3);">3</a></li>
  <li><a onclick="event.preventDefault(); movies(12,4);">4</a></li>
  <li><a onclick="event.preventDefault(); movies(12,5);">5</a></li>
  <li><a onclick="event.preventDefault(); movies(12,6);">6</a></li>
  <li><a onclick="event.preventDefault(); movies(12,7);">7</a></li>
  <li><a onclick="event.preventDefault(); movies(12,8);">8</a></li>
  <li><a onclick="event.preventDefault(); movies(12,9);">9</a></li>
  <li><a onclick="event.preventDefault(); movies(12,10);">10</a></li>
</ul>
				</div> -->
			</div>
		</div>
	</div>
</body>
</html>
<!-- <script>
$(document).ready(function(){
	var userid=12;
	movies(userid,1);
    
});
function movies(userid, offset){	
	  var dataArray = "http://localhost:8080/ajay-movie/api/home1?userId="+userid+"&offset="+offset+"";  
	$.ajax({
      type: "GET",
      url: dataArray,
      dataType: 'json',
      success: function(data) {
          print(data,userid,offset);
          //x=console.log(dataArray);
          
      }
  });
	
	return dataArray;
}

function print(dataArray,userid,offset){
	  $("#all").html('');
	  for(var i = 0; i < dataArray.length; i += 1) 
	        console.log(dataArray[i].id);
		for(var d in dataArray){
			var a=1;
			//console.log(dataArray[0].id);
			$("#all").append('<div class="col-sm-3"><div class="thumbnail"><img src="'+dataArray[d].url+'"><div class="caption"><h4 style="margin-bottom: 24px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">'+dataArray[d].title+'</h4><div class="row"><div class="col-xs-6"><form action="/like?movie='+dataArray[d].id+'&amp;user='+userid+'" method="post" class="text-center"><button class="btn btn-default"><span class="glyphicon glyphicon-thumbs-up"></span></button></form></div><div class="col-xs-6"><form action="/dislike?movie='+dataArray[d].id+'&amp;user='+userid+'" method="post" class="text-center"><button class="btn btn-default"><span class="glyphicon glyphicon-thumbs-down"></span></button></form></div></div></div></div></div>');
		}
}
</script> -->