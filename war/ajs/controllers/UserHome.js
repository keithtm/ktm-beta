// angularjs controller
appModule.controller('UserHomeController', ['$scope', 'UserService', function($scope, UserService) {

  //initialization in model
  $scope.initialized = false;
  
  // validation feedback in model
  $scope.feedbackClass = alertInfo;
  $scope.feedbackMessage = "empty feedback"; 
  
  // initialize userService
  var userService = new UserService();

  // service call on page load to see if user is already registered
  userService.getUser().then(function(success) {
    $scope.appUser = userService.appUser;
    $scope.initialized=true;
  }, function(error){
    $scope.feedbackClass = alertDanger;
    $scope.feedbackMessage = "User Not Registered";
    $scope.initialized=true;
  });
 
  
  /**
   * toggleActive function
   * 
   * @return updates model
   * @see UserService.js for service call
   */
  $scope.toggleActive = function() {

    var userConfirm = confirm("Update?");

    if (userConfirm) {
      $scope.saving = true;
      $scope.feedbackClass = alertInfo;
      $scope.feedbackMessage = "saving";
      userService.updateUser($scope.appUser.screenName, !$scope.appUser.active).then(function(success) {
        $scope.appUser=userService.appUser;
        $scope.feedbackClass = alertSuccess;
        $scope.feedbackMessage = "updated";
      }, function(error) {
        $scope.feedbackClass = alertDanger;
        $scope.feedbackMessage = error.data.errorMsg;
      });
      $scope.saving = false;
    }
  };
  
  /**
   * deleteUser function
   * 
   * @return updates model
   * @see UserService.js for service call
   */
  $scope.deleteUser = function() {

    var userConfirm = confirm("Delete?");

    if (userConfirm) {
      $scope.saving = true;
      $scope.feedbackClass = alertInfo;
      $scope.feedbackMessage = "deleting";
      userService.deleteUser().then(function(success) {
        $scope.appUser=userService.appUser;
        $scope.feedbackClass = alertSuccess;
        $scope.feedbackMessage = "deleted";
        window.location.assign("/");
      }, function(error) {
        $scope.feedbackClass = alertDanger;
        $scope.feedbackMessage = error.data.errorMsg;
      });
      $scope.saving = false;
    }
  };
  
}]);