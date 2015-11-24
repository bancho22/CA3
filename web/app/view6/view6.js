'use strict';

angular.module('myApp.view6', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view6', {
    templateUrl: 'app/view6/view6.html',
    controller: 'View6Ctrl'
  });
}])



.controller('View6Ctrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/demoadmin'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });

        });