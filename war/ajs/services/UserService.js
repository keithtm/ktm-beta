// angularjs service
// http://chariotsolutions.com/blog/post/angularjs-corner-using-promises-q-handle-asynchronous-calls/
// http://blog.revolunet.com/blog/2014/02/14/angularjs-services-inheritance/
appModule.factory('UserService', ['$http', '$q', function($http, $q) {

  // apiURL
  var apiURL = "/api/user";

  // the service and its properties
  var UserService = function() {
    this.appUser = null;
    this.screenNameUnique = false;
    this.deleted = false;
  }

  /**
   * getUser function
   * 
   * @return promise that appUser will be updated
   */
  UserService.prototype.getUser = function() {

    var self = this;

    var promise = $http.get(apiURL).then(function(success) {
      self.appUser = success.data;
      return success;
    }, function(error) {
      self.appUser = null;
      return $q.reject(error);
    });

    return promise;
  };

  /**
   * isScreenNameUnique function
   * 
   * @param screenName
   *          the screen name to check
   * @return promise that screenNameUnique will be updated
   */
  UserService.prototype.isScreenNameUnique = function(screenName) {

    var apiUserScreenNameUniqueURL = apiURL + "/screenNameUnique/" + screenName;

    var self = this;

    var promise = $http.get(apiUserScreenNameUniqueURL).then(function(success) {
      self.screenNameUnique = true;
      return success;
    }, function(error) {
      self.screenNameUnique = false;
      return error;
    });

    return promise;
  };

  /**
   * registerUser function
   * 
   * @param screenName
   *          the screen name to register for the user
   * @return promise that screen name will be registered and appUser will be
   *         updated
   */
  UserService.prototype.registerUser = function(screenName) {

    var self = this;

    var postData = {
      screenName: screenName
    };
    var promise = $http.post(apiURL, postData).then(function(success) {
      self.appUser = success.data;
      return success;
    }, function(error) {
      self.appUser = null;
      return $q.reject(error);
    });

    return promise;
  };

  /**
   * updateUser function
   * 
   * @param screenName
   *          the screen name to register for the user
   * @param active
   *          the active flag
   * @return promise that the appUser will be updated
   */
  UserService.prototype.updateUser = function(screenName, active) {

    var self = this;

    var postData = {
      screenName: screenName,
      active: active
    };
    var promise = $http.put(apiURL, postData).then(function(success) {
      self.appUser = success.data;
      return success;
    }, function(error) {
      return $q.reject(error);
    });

    return promise;
  };

  /**
   * deleteUser function
   * 
   * @return promise that user will be deleted
   */
  UserService.prototype.deleteUser = function() {

    var self = this;

    var promise = $http.delete(apiURL).then(function(success) {
      self.deleted = true;
      self.appUser = null;
      return success;
    }, function(error) {
      self.deleted = false;
      return $q.reject(error);
    });

    return promise;
  };

  return UserService;
}]);
