// angularjs controller
appModule.controller('UserRegisterController', ['$scope', 'UserService', function($scope, UserService) {
  
  //initialization in model
  $scope.initialized = false;
  
  // properties in model and view
  $scope.screenName = "";

  // validation feedback in model
  $scope.feedbackClass = alertInfo;
  $scope.feedbackMessage = "empty feedback";

  // validation feedback for each property in model
  $scope.screenNameClass = "";

  // form watch variables
  $scope.dirty = false;
  $scope.invalid = true;

  // watch form elements
  $scope.$watch('screenName', function() {
    $scope.dirty = true;
  });

  // initialize userService
  var userService = new UserService();

  // service call on page load to see if user is already registered
  userService.getUser().then(function(success) {
    $scope.appUser = userService.appUser;
    $scope.initialized=true;
  }, function(error){
    $scope.initialized=true;
  });

  /**
   * validate function
   * 
   * @return updates model
   * @see AppCommon.js validate property function and ScreenName validation spec
   *      for client side validation
   * @see UserService.js for server side validation
   */
  $scope.validate = function() {

    $scope.invalid = true;

    $scope.feedbackClass = alertInfo;
    $scope.feedbackMessage = "";
    $scope.screenNameClass = "";

    // client side validation
    screenNameValidation = new validateProperty(screenNameSpec, $scope.screenName);
    if (!screenNameValidation.valid) {
      $scope.feedbackClass = alertDanger;
      $scope.feedbackMessage += "screen name " + screenNameValidation.message;
      $scope.screenNameClass = hasError;
    }

    // if still validating, check key uniqueness
    if ($scope.feedbackMessage == "") {
      // lookup for uniqueness check
      userService.isScreenNameUnique($scope.screenName).then(function() {
        if (userService.screenNameUnique) {
          $scope.invalid = false;
          $scope.feedbackClass = alertInfo;
          $scope.feedbackMessage = "valid";
          $scope.dirty = false;
        } else {
          $scope.feedbackClass = alertDanger;
          $scope.feedbackMessage += "'" + $scope.screenName + "' already exists";
          $scope.screenNameClass = hasError;
          $scope.dirty = false;
        }
      });
    }

  };

  /**
   * save function
   * 
   * @return if successful, redirects user to home page. if not, updates model.
   * @see UserService.js for service call
   */
  $scope.save = function() {

    var userConfirm = confirm("Sure?");

    if (userConfirm) {
      $scope.saving = true;
      $scope.feedbackClass = alertInfo;
      $scope.feedbackMessage = "saving";

      // register user
      userService.registerUser($scope.screenName).then(function(success) {
        // redirect browser to user home
        window.location.assign("/web/home");
      }, function(error) {
        $scope.invalid = true;
        $scope.feedbackClass = alertDanger;
        $scope.feedbackMessage = error.data.errorMsg;
        $scope.saving = false;
      });
    }

  };

}]);