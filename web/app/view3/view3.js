'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view3', {
    templateUrl: 'app/view3/view3.html',
    controller: 'View3Ctrl'
  });
}])






.controller('View3Ctrl', ["$scope", "$http",function($scope,$http) {
            $http.get('api/demouser')
            .success(function (data, status, headers, config) {
              $scope.data = data;
            })
            .error(function (data, status, headers, config) {
              
             });

     $scope.getCompany = function(){
                $http({method: 'GET', url: 'http://cvrapi.dk/api?search='+$scope.cvr+'&country='+$scope.country, 
                   'User-Agent':"CVR API - CA3 – alex - cph-ag78@cphbusiness.dk",
                    contentType: "application/json"}).
                        success(function (data, status, headers, config) {
                            alert("in");
                            $scope.actualData=data;
                            alert(data.length);

                        }).
                        error(function (data, status, headers, config) {

                        })};
     
                
                
 
}])
    



