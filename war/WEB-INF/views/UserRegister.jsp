<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" scope="request" value="Register" />
<c:set var="pageActiveRegister" scope="request" value="active" />


<jsp:include flush="true" page="includes/header.jsp"></jsp:include>


<!-- use angularJS for client side validation -->
<script src="/ajs/AppCommon.js"></script>
<script src="/ajs/validationSpecs/screenNameSpec.js"></script>
<script src="/ajs/services/UserService.js"></script>
<script src="/ajs/controllers/UserRegister.js"></script>
<div ng-app="appModule">
	<div ng-controller="UserRegisterController">
		<div ng-show="initialized">
			<h1>User Registration</h1>
			<div ng-show="appUser">
				<p>Already Registered as {{appUser.screenName}}</p>
			</div>
			<div ng-hide="appUser">
				<p>
					The screen name you enter below will be your identity on this site.
					<br /> <br /> You won't be able to change it later, so give it
					some thought. <br /> <br /> It must be unique, between 3 and 20
					characters in length, and not contain any spaces or special
					characters.
				</p>
				<form role="form">
					<div class="form-group {{screenNameClass}}">
						<label for="screenName" class="control-label"> screen name
						</label> <input type="text" size="20" maxsize="20" class="form-control"
							id="screenName" name="screenName" ng-model="screenName" />
					</div>
				</form>
				&nbsp;
				<button type="button" class="btn btn-info" ng-disabled="!dirty"
					ng-click="validate()">
					<span class='glyphicon glyphicon-cog'></span> Validate
				</button>
				&nbsp;
				<button type="button" class="btn btn-primary"
					ng-show="!dirty && !invalid" ng-disabled="saving" ng-click="save()">
					<span class='glyphicon glyphicon-save'></span> Save
				</button>
				<br /> <br />
				<div ng-hide="feedbackMessage.length==0"
					class="alert {{feedbackClass}}">{{feedbackMessage}}</div>
			</div>
		</div>
	</div>
</div>


<jsp:include flush="true" page="includes/footer.jsp"></jsp:include>