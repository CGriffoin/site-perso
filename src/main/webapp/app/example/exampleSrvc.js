/*global rmApp*/
/* global _ */
/* jshint loopfunc: true */
/* jshint forin: false */

"use strict";

rmApp.service("ExampleSrvc", function ($q, $location, $rootScope,
                                       HttpSrvc) {

    var URI_PREFIX = "/rest/example";

    return {

        list: function () {
            var url = URI_PREFIX;
            return HttpSrvc.rmGet(url);
        },
        get: function (exampleId) {
            var url = URI_PREFIX
                .concat("/")
                .concat(exampleId);

            return HttpSrvc.rmGet(url);
        },
        create: function (example) {
            var url = URI_PREFIX;

            return HttpSrvc.rmPost(url, example);
        },
        update: function (example) {
            var url = URI_PREFIX
                .concat("/")
                .concat(example.id);

            return HttpSrvc.rmPut(url, example);
        },
        delete: function (exampleId) {
            var url = URI_PREFIX
                .concat("/")
                .concat(exampleId);

            return HttpSrvc.rmDelete(url);
        }
    };
});