/*global rmApp*/
'use strict';

rmApp.controller("HeaderCtrl", function ($scope, $location) {

    $scope.homeLabel = "home";
    $scope.appTitle = "Angélo LIMA | site perso";

    /*
     *
     * Define callbacks
     *
     */
    $scope.goToHome = function () {
        $location.path("/");
    };
});
