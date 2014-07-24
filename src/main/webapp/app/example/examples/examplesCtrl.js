/*global rmApp*/
/*global _*/
'use strict';
'use $';

rmApp.controller("ExamplesCtrl", function ($scope, $location,
                                           ExampleSrvc,
                                           loadedExamples) {

    $scope.examples = loadedExamples; //loaded examples are loaded before ctrl is instantiated

    $scope.exampleSelected = function (exampleId) {
        $location.path("/example/" + exampleId);
    };

    $scope.goToCreateExample = function () {
        $location.path("/example/new");
    };

});
