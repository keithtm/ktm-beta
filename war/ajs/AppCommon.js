// angularjs module
var appModule = angular.module('appModule', []);

// bootstrap css constants
var alertInfo = "alert-info";
var alertDanger = "alert-danger";
var alertSuccess = "alert-success";
var hasError = "has-error";

/**
 * validate property function
 * 
 * @param spec
 *          data specification used for validation
 * @param val
 *          value to validate against specification
 * @return valid boolean indicated valid or not
 * @return message string describing validation error
 */
var validateProperty = function(spec, val) {
  this.message = "";
  this.valid = false;
  this.spec = spec;
  this.val = val;

  if (this.spec.type == "string") {
    size = this.val.length;
    if (size < this.spec.minSize)
      this.message = "contains too few characters (less than " + this.spec.minSize + ").  ";
    else if (size > this.spec.maxSize)
      this.message = "contains too many characters (greater than " + this.spec.maxSize + ").  ";
    else if (this.val != this.val.match(this.spec.regExpr))
      this.message = "contains invalid characters (doesn't match regExpr " + this.spec.regExpr + ").  ";
  }

  if (this.message.length == 0) this.valid = true;
};