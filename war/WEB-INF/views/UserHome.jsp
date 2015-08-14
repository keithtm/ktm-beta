<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" scope="request" value="Home" />
<c:set var="pageActiveHome" scope="request" value="active" />


<jsp:include flush="true" page="includes/header.jsp"></jsp:include>


<!-- use angularJS for client side validation -->
<script src="/ajs/AppCommon.js"></script>
<script src="/ajs/services/UserService.js"></script>
<script src="/ajs/controllers/UserHome.js"></script>
<div ng-app="appModule">
	<div ng-controller="UserHomeController">
		<div ng-show="initialized">
			<div ng-show="appUser">
				<h1>User Home</h1>
				<div ng-show="appUser.active">
					<p>
						user <strong>{{appUser.screenName}}</strong> is active
					</p>
					&nbsp;
					<button type="button" class="btn btn-info" ng-disabled="saving"
						ng-click="toggleActive()">
						<span class='glyphicon glyphicon-minus'></span>de-activate
					</button>
					&nbsp;
					<button type="button" class="btn btn-primary"
						ng-show="!dirty && !invalid" ng-disabled="saving"
						ng-click="deleteUser()">
						<span class='glyphicon glyphicon-remove'></span>delete
					</button>
				</div>
				<div ng-hide="appUser.active">
					<p>
						user <strong>{{appUser.screenName}}</strong> is inactive
					</p>
					&nbsp;
					<button type="button" class="btn btn-info" ng-disabled="saving"
						ng-click="toggleActive()">
						<span class='glyphicon glyphicon-plus'></span>re-activate
					</button>
					&nbsp;
					<button type="button" class="btn btn-primary"
						ng-show="!dirty && !invalid" ng-disabled="saving"
						ng-click="deleteUser()">
						<span class='glyphicon glyphicon-remove'></span>delete
					</button>
				</div>
			</div>
			<br /> <br />
			<div ng-hide="feedbackMessage.length==0"
				class="alert {{feedbackClass}}">{{feedbackMessage}}</div>
			<br /> <br />
		</div>
	</div>
</div>

<jsp:include flush="true" page="includes/footer.jsp"></jsp:include>