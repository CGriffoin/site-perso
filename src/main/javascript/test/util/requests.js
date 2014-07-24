"use strict";

function IntegrationTestCtrl ($scope, $http) {

    $http.get("/requests").success(function (res) {
        $scope.requests = res;
    });
}