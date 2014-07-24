
"use strict";
angular
    .module('gUtils', [])
    .factory("u", function ($window) {
        $window._.mixin($window._.str.exports());
        return $window._;
    });