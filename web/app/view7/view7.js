'use strict';

angular.module('myApp.view7', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view7', {
    templateUrl: 'app/view7/view7.html',
    controller: 'View7Ctrl'
  });
}])
        
        
       .controller('View7Ctrl', function ($http, $scope) {
    var person = '{"username" : "","password" : "","role" : "user"}';
   $http({
        type: 'POST',
        url: "api/demouser",
        contentType: 'application/json',
        data: person,
        success: function (data) {
            refreshTable();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + " on POST");
        }
    });
});