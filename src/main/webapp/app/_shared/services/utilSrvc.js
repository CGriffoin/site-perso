/*global rmApp*/
/*global _*/
"use strict";


rmApp.service("UtilSrvc", function ($rootScope, $filter, $location, u) {

    var formatDate = function (millis, format) {
        return $filter('date')((new Date(millis)), format);
    };

    var stringToInt = function (string) {
        return parseInt(string, 10);
    };

    return {
        emptyScope: function() {
            return $rootScope.$new(true);
        },
        safeApply: function (scope, fn) {
            var phase = scope.$root.$$phase;
            if (phase === '$apply' || phase === '$digest') {
                scope.$eval(fn);
            }
            else {
                scope.$apply(fn);
            }
        },
        numberOfProperties: function (foo) {
            var count = 0;
            for (var k in foo) {
                if (foo.hasOwnProperty(k)) {
                    count += 1;
                }
            }
            return count;
        },
        stringFormatDate: function (millisString) {
            var date = formatDate(stringToInt(millisString), "dd MMM yyyy");
            return date;
        },
        injectUrlParamsIntoConfig: function () {
            var config = {};
            if (!u.isEmpty($location.search())) {
                config.params = $location.search();
            }
            return config;
        }



    };

});