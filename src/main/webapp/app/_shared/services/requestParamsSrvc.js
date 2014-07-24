/*global rmApp*/
/*global _*/
"use strict";

rmApp.service("RequestParamsSrvc", function ($location) {

    return {

        resetParams: function () {
            $location.search({});
        },
        setParam: function (name, value) {
            $location.search(name, value);
        },
        setParams: function (params) {
            $location.search(params);
        }

    };

});