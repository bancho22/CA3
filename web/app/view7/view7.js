'use strict';

angular.module('myApp.view7', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view7', {
                    templateUrl: 'app/view7/view7.html',
                    controller: 'View7Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View7Ctrl', ["$scope", "$http", "InfoFactory", "InfoService", function ($scope, $http, InfoFactory, InfoService) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();



                $scope.post = function () {


                    $http({method: 'POST', url: 'api/register',
                        contentType: "application/json", data: JSON.stringify($scope.user)}).
                            success(function (data, status, headers, config) {


                            }).
                            error(function (data, status, headers, config) {

                            });

                };

            }]);














