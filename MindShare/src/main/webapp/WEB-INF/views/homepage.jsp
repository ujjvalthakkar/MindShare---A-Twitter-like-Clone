<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Open Sans", sans-serif
}
</style>
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
			</a>
			<!-- 			<a href="userprofile.htm" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="Account Settings" style="height: 50px;"> -->
			<!-- 			<i class="fa fa-user"></i> -->
			<!-- 			</a> -->
			<form action="userprofile.htm" method="post">
				<button type="submit"
					class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white fa fa-user"
					value="User Profile" style="height: 50px;"></button>
			</form>
			<a href="logout.htm"
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
						<p><span class="glyphicon glyphicon-envelope"></span>Mindshare Now Allows to update the profile</p>
						<p><span class="glyphicon glyphicon-envelope"></span>Mindshare is now available back online</p>
						<p><span class="glyphicon glyphicon-envelope"></span>Mindshare now allows to follow multiple users</p>
						<p><span class="glyphicon glyphicon-envelope"></span>Mindshare is now available globally</p>
						<p><span class="glyphicon glyphicon-envelope"></span>Mindshare is now backed up Twitter</p>
						<p><span class="glyphicon glyphicon-envelope"></span>Mindshare is now available on phones</p>
						<p><span class="glyphicon glyphicon-envelope"></span>Mindshare is now available on Tablets</p>
					</div>
				</div>
				<br>
				<!-- End Left Column -->
			</div>

			<!-- Middle Column -->
			<div class="w3-col m7" style="overflow-y: scroll; height: 650px;">
				<div class="row">
					<div class="w3-row-padding">
						<div class="w3-col m12">
							<div class="w3-card-2 w3-round w3-white">
								<div class="w3-container w3-padding">
									<h6 class="w3-opacity">MindShare here:</h6>
									<form name="userForm" action="tweet.htm" method="post">
										<textarea class="w3-border w3-padding" name="tweetMsg"
											rows="2" cols="85" maxlength="140"
											placeholder="Share your mind.." style="resize: none;"
											required></textarea>
										<br> <br>
										<button type="submit" class="w3-button w3-theme">
											<i class="fa fa-pencil"></i> MindShare
										</button>
										<!--<input type="submit" class="w3-button w3-theme"><i class="fa fa-pencil"></i>MindShare</input> -->
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<c:if test="${not empty sessionScope.tweetList}">
						<c:forEach items="${sessionScope.tweetList}" var="tweet">
							<div class="w3-container w3-card-2 w3-white w3-round w3-margin">
								<br> <img src="/images/${tweet.value.getFilename()}"
									alt="Avatar" class="w3-left w3-circle w3-margin-right"
									style="width: 60px"> <span class="w3-right w3-opacity">1
									min</span>
								<h4>${tweet.value.getName()}</h4>
								<br>
								<p>${tweet.key.getTweetMsg()}</p>
								<c:set var="liked" value="false" />
								<c:forEach var="userr" items="${tweet.key.getUserLikeList()}">
									<c:if test="${userr.name eq sessionScope.user.name}">
										<c:set var="liked" value="true" />
									</c:if>
								</c:forEach>
								<c:if test="${liked == 'false'}">
								<p>${tweet.key.getLikeCount()}&nbsp<a
										href="${contextPath}/like.htm?tweetId=${tweet.key.getTweetId()}"
										class="w3-button w3-theme-d1 w3-margin-bottom"><i
										class="fa fa-thumbs-up"></i>&nbsp Like</a>
								</p>
								</c:if>
								<c:if test="${liked == 'true'}">
								<p>${tweet.key.getLikeCount()}&nbsp<a
										href="${contextPath}/unlike.htm?tweetId=${tweet.key.getTweetId()}"
										class="w3-button w3-theme-d1 w3-margin-bottom"><i
										class="fa fa-thumbs-up"></i>&nbsp Unlike</a>
								</p>
								</c:if>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<!-- End Middle Column -->
			</div>

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