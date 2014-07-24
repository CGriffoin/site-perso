/*global rmApp*/
/*global _*/
'use strict';
'use $';

rmApp.controller("ExampleNewCtrl", function ($scope, $location,
                                          ExampleSrvc) {

    $scope.example = {
        name : ""
    };

    var goToExampleList = function () {
        $location.path("/example");
    };

    $scope.createExample = function () {
        ExampleSrvc.create($scope.example).then(goToExampleList);
    };

});
