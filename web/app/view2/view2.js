'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2', {
    templateUrl: 'app/view2/view2.html',
    controller: 'View1Ctrl',
    controllerAs : 'ctrl'
  });
}])

.controller('View2Ctrl', ["InfoFactory","InfoService",function(InfoFactory,InfoService) {
        
}]);