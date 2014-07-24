"use strict";

function SendRequestCtrl ($scope, $location, $http) {
    $http.get($location.url());
}