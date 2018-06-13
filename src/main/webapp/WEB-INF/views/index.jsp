<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<script src="mdb/js/jquery-3.3.1.min.js"></script>
		<script src="mdb/js/bootstrap.min.js"></script>
		<script src="mdb/js/popper.min.js"></script>
		<script src="mdb/js/mdb.min.js"></script>
		<link href="mdb/css/bootstrap.min.css" rel="stylesheet">
		<link href="mdb/css/mdb.min.css" rel="stylesheet">
		<link href="mdb/css/style.min.css" rel="stylesheet">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<title>Demo</title>
	</head>
	<body>

		<nav class="navbar navbar-expand-lg navbar-dark primary-color">
		    <a class="navbar-brand" href="#">Demo Post Application</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav" aria-controls="basicExampleNav"
		        aria-expanded="false" aria-label="Toggle navigation">
		        <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="basicExampleNav">
		        <ul class="navbar-nav mr-auto">
		            <li class="nav-item active">
		                <a class="nav-link" href="#">Home
		                    <span class="sr-only">(current)</span>
		                </a>
		            </li>
		        </ul>
		    </div>
		</nav>
		
		<br/>
		
		<form action="/postar" method="POST">
			<div class="container-fluid row">
				<div class="form-group col-md-11">
				    <i class="material-icons">edit</i>
				    <label for="textarea">Escreva seu post</label>
				    <textarea id="textarea" name="content" class="form-control" rows="3"></textarea>
				</div>
				<div class="form-group col-md-1">
					<button type="submit" class="btn btn-primary">Enviar</button>
				</div>
			</div>
		</form>
		<div class="container-fluid">
			<ul class="list-group">
				<c:forEach items="${posts}" var="post">
					<li class="list-group-item d-flex justify-content-between align-items-center">
						<input type="hidden" id="postid" value="${post.id}"></input>
						${post.conteudo}
						<div>
							<span class="badge badge-primary badge-pill upvote" style="cursor:pointer">
								<i class="material-icons">thumb_up</i>
							</span>
							<span class="badge badge-primary badge-pill">${post.upvotes}</span>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		
	</body>
</html>

<script>

$(()=>{
	
	$('.upvote').click((e)=>{

		const postid = $(e.target).closest('li').find('#postid').val();
		
		$.ajax({
			url: '/upvote',
			method: 'POST',
			dataType: 'html',
			data: {
				id: postid
			}
		})
		.done(()=>{
			window.location.reload();
		})
		.fail(()=>{
			console.log("Ajax /upvote falhou!");
		});
	});
});

</script>
