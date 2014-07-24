/*global rmApp*/
/*global _*/
'use strict';
'use $';

rmApp.controller("ExampleCtrl", function ($scope, $location,
                                          ExampleSrvc,
                                          loadedExample) {

    $scope.example = loadedExample; //loaded examples are loaded before ctrl is instantiated

    var goToExampleList = function () {
        $location.path("/example");
    };

    $scope.goToCreateExample = function () {
        $location.path("/example/new");
    };

    $scope.deleteExample = function () {
        ExampleSrvc.delete($scope.example.id).then(goToExampleList);
    };

    $scope.updateExample = function () {
        ExampleSrvc.update($scope.example).then(goToExampleList);
    };

    $scope.$on("EVENT-Changed-example", function () {
        $scope.updateExample();
    });

});
