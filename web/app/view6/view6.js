'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'app/view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])



        .controller('View6Ctrl', ['$http', '$scope', function ($http, $scope) {
//                $http.get('api/demoadmin')
//                        .success(function (data, status, headers, config) {
//                            $scope.data = data;
//                        })
//                        .error(function (data, status, headers, config) {
//
//                        });

                $scope.users = [];
//             var getUsers = function(){
                $http.get('api/admin/users').success(function (data, status, headers, config) {
                    $scope.users = data;
                })
                        .error(function (data, status, headers, config) {

                        });
//             };
                
                $scope.deleteUser = function (id) {
                    
                    $http.delete('api/admin/users/'+ id)
                            .success(function (data, status, headers, config) {
                                for(var i = 0; i <$scope.users.length;i++){
                                    if(id === $scope.users[i].id){
                                        $scope.users.splice(i,1);
                                        
                                    }
                                }
                            })
                            .error(function (data, status, headers, config) {

                            });
                };




            }]);