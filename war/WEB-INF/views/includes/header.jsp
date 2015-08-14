<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="/img/favicon.ico">

<!-- CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/app.css" >
<style type="text/css"></style>
<style id="holderjs-style" type="text/css"></style>

<!-- JavaScript -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title>group-t-h-i-n-k ${pageTitle}</title>
</head>

<body role="document" style="">

	<!-- navbar begin -->

	<div class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<!-- logo -->
				<c:choose>
					<c:when test="${not empty appUser.screenName}">
						<a href="/"><img src="/img/logo-smaller-nowords.png" height="50px" /></a>
					</c:when>
					<c:otherwise>
						<a href="/"><img src="/img/logo-smaller.png" height="50px" /></a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">

					<c:choose>
						<c:when test="${not empty appUser.screenName}">
							<li class="${pageActiveHome}"><a href="/web/home">${appUser.screenName}</a></li>
						</c:when>
						<c:otherwise>
							<li class="${pageActiveRegister}"><a href="/web/register">Register</a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="${logoutURL}">Logout</a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="${pageActiveAbout}"><a href="/web/about">About</a></li>
					<li class="${pageActivePrivacy}"><a href="/web/privacy">Privacy</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- navbar end -->


	<!-- start main content -->
	<div class="container theme-showcase" role="main">