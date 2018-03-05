<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MindShare</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Open Sans", sans-serif
}
</style>
<script type="text/javascript">
	function ValidateEmail(inputText) {
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if (inputText.value.match(mailformat)) {
			document.form1.email.focus();
			return true;
		} else {
			alert("You have entered an invalid email address!");
			document.form1.email.focus();
			return false;
		}
	}
</script>
</head>
<body class="w3-theme-l5">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<!-- Navbar -->
	<div class="w3-top">
		<div class="w3-bar w3-theme-d2 w3-left-align w3-large">
			<a
				class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-theme-d2"
				href="javascript:void(0);" onclick="openNav()"><i
				class="fa fa-bars"></i></a> <a href="#"
				class="w3-bar-item w3-button w3-padding-large w3-theme-d4"> <i
				class="fa fa-home w3-margin-right"></i>Logo
			</a> <a href="#"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="Account Settings" style="height: 50px;"> <i
				class="fa fa-user"></i>
			</a> <a href="logout.htm"
				class="w3-bar-item w3-button w3-hide-small w3-right w3-padding-large w3-hover-white"
				title="Logout">Logout</a>
			<!-- 			<form action="logout.htm" method="post"><input type="submit" value="Logout" /></form> -->
		</div>
	</div>

	<!-- Navbar on small screens -->
	<div id="navDemo"
		class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
		<a href="#" class="w3-bar-item w3-button w3-padding-large">Link 1</a>
		<a href="#" class="w3-bar-item w3-button w3-padding-large">Link 2</a>
		<a href="#" class="w3-bar-item w3-button w3-padding-large">Link 3</a>
		<a href="#" class="w3-bar-item w3-button w3-padding-large">My
			Profile</a>
	</div>

	<!-- Page Container -->
	<div class="w3-container w3-content"
		style="max-width: 1400px; margin-top: 80px">
		<!-- The Grid -->
		<div class="w3-row">
			<!-- Left Column -->
			<div class="w3-col m3">
				<!-- Profile -->
				<div class="w3-card-2 w3-round w3-white">
					<div class="w3-container">
						<h4 class="w3-center">User Profile</h4>
						<p class="w3-center">
							<img src="/images/${sessionScope.user.getFilename()}"
								class="w3-circle" style="height: 106px; width: 106px"
								alt="${sessionScope.user.getName()} UserPic">
						</p>
						<hr>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							${sessionScope.user.getName()}
						</p>
						<p>
							<i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i>
							TweetCount: ${sessionScope.user.getTweetCount()}
						</p>
					</div>
				</div>
				<br>

				<!-- MindShare News -->
				<div class="w3-card-2 w3-round w3-white w3-hide-small">
					<div class="w3-container">
						<h2>MindShare News</h2>
						<p><span class="glyphicon glyphicon-hand-right"></span>&nbsp&nbsp Mindshare Now Allows to update the profile</p>
						<p><span class="glyphicon glyphicon-hand-right"></span>&nbsp&nbsp Mindshare is now available back online</p>
						<p><span class="glyphicon glyphicon-hand-right"></span>&nbsp&nbsp Mindshare now allows to follow multiple users</p>
						<p><span class="glyphicon glyphicon-hand-right"></span>&nbsp&nbsp Mindshare is now available globally</p>
						<p><span class="glyphicon glyphicon-hand-right"></span>&nbsp&nbsp Mindshare is now backed up Twitter</p>
						<p><span class="glyphicon glyphicon-hand-right"></span>&nbsp&nbsp Mindshare is now available on phones</p>
						<p><span class="glyphicon glyphicon-hand-right"></span>&nbsp&nbsp Mindshare is now available on Tablets</p>
					</div>
				</div>
				<br>
				<!-- End Left Column -->
			</div>

			<!-- Middle Column -->
			<div class="w3-col m1">.</div>
			<div class="w3-col m5">
				<div class="row">
					<div class="w3-row-padding">
						<div class="w3-col m12">
							<div class="w3-card-2 w3-round w3-white">
								<div class="w3-container w3-padding">
								<br>
								<h2>User Information</h2>
									<form:form id="signupform" name="form1" class="form-horizontal"
										role="form" commandName="user" method="post"
										action="update.htm" enctype="multipart/form-data">
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">Email</label>
											<div class="col-md-9">
												<form:input path="email" type="text" class="form-control"
													name="email" placeholder="${sessionScope.user.getEmail()}" value="${sessionScope.user.getEmail()}"
													/>
												<form:errors path="email" />
											</div>
										</div>

										<div class="form-group">
											<label for="firstname" class="col-md-3 control-label">Name</label>
											<div class="col-md-9">
												<form:input path="name" type="text" class="form-control"
													name="name" placeholder="${sessionScope.user.getName()}" value="${sessionScope.user.getName()}"/>
												<form:errors path="name" />
											</div>
										</div>
										<div class="form-group">
											<label for="photo" class="col-md-3 control-label">Profile
												Picture</label>
											<div class="col-md-9">
												<input type="file" class="form-control" name="photo">
												<input type="hidden" name="userid" value="${sessionScope.user.getUserId()}"/> 
											</div>
										</div>
										<div class="form-group">
											<!-- Button -->
											<div class="col-md-offset-3 col-md-9">
												<button id="btn-signup" type="submit" class="btn btn-info"
													onclick="ValidateEmail(document.form1.email)">
													<i class="icon-hand-right"></i> &nbsp Update
												</button>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End Middle Column -->
			</div>
			<div class="w3-col m1">.</div>
			<!-- Right Column -->
			<div class="w3-col m2 abc" style="overflow-y: scroll; height: 650px;">
				<div class="w3-card-2 w3-round w3-white w3-center">
					<div class="w3-container">
						<h4>User you may know:</h4>
						<c:if test="${not empty sessionScope.usertoFollowList}">
							<c:forEach items="${sessionScope.usertoFollowList}" var="user">
								<form action="follow.htm" method="post">
									<img src="/images/${user.getFilename()}"
										alt="${user.getName()} UserPic" style="width: 50%"><br>
									<span>${user.getName()}</span> <input type="hidden"
										name="followee_userid" value="${user.getUserId()}" /> <input
										type="hidden" name="follower_userid"
										value="${sessionScope.user.getUserId()}" />
									<div class="w3-row w3-opacity">
										<div class="w3-half">
											<button type="submit"
												class="w3-button w3-block w3-blue w3-section" title="Accept"
												style="margin-left: 45px;">Follow</button>
										</div>
									</div>
									<!--<input type="submit" value="Follow" /> -->
								</form>
							</c:forEach>
						</c:if>
						<c:if test="${empty sessionScope.usertoFollowList}">
							<p>No current suggestions!</p>
						</c:if>
					</div>
				</div>
				<br>
				<!-- End Right Column -->
			</div>
			<!-- End Grid -->
		</div>

		<!-- End Page Container -->
	</div>
	<br>

	<script>
		// Used to toggle the menu on smaller screens when clicking on the menu button
		function openNav() {
			var x = document.getElementById("navDemo");
			if (x.className.indexOf("w3-show") == -1) {
				x.className += " w3-show";
			} else {
				x.className = x.className.replace(" w3-show", "");
			}
		}
	</script>
</body>
</html>