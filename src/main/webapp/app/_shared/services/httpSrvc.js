/*global rmApp*/
/*global $*/
"use strict";

rmApp.service("HttpSrvc", function ($http, $q, u) {


    return {

        rmGet: function (url, config) {
            var deferred = $q.defer();
            if (u.str.contains(url, "search")) {
                $("#G_footer .g-verb").text('GET');
                $("#G_footer .g-path").text(url);
                $("#G_footer .g-body").text('');
            }
            $http.get(url, config).then(
                function (result) {
                    deferred.resolve(result.data);
                },
                function (reason) {
                    deferred.reject(reason);
                }
            );
            return deferred.promise;
        },
        rmPut: function (url, payload) {
            var deferred = $q.defer();
            $("#G_footer .g-verb").text('PUT');
            $("#G_footer .g-path").text(url);
            if (payload) {
                $("#G_footer .g-body").text(JSON.stringify(payload));
            } else {
                $("#G_footer .g-body").text('');
            }
            $http.put(url, payload).then(
                function (result) {
                    deferred.resolve(result.data);
                },
                function (bad) {
                    deferred.reject(bad);
                }
            );
            return deferred.promise;
        },

        rmDelete: function (url) {
            var deferred = $q.defer();
            $("#G_footer .g-verb").text('DELETE');
            $("#G_footer .g-path").text(url);
            $("#G_footer .g-body").text('');
            $http.delete(url).then(
                function (result) {
                    deferred.resolve(result.data);
                },
                function (reason) {
                    deferred.reject(reason);
                }
            );
            return deferred.promise;
        },

        rmPost: function (url, payload, config) {
            var deferred = $q.defer();
            $("#G_footer .g-verb").text('POST');
            $("#G_footer .g-path").text(url);
            if (payload) {
                $("#G_footer .g-body").text(JSON.stringify(payload));
            } else {
                $("#G_footer .g-body").text('');
            }

            $http.post(url, payload, config).then(
                function (good) {
                    deferred.resolve(good.data);
                },
                function (bad) {
                    deferred.reject(bad);
                }
            );
            return deferred.promise;
        },

        rmHead: function (url) {
            var deferred = $q.defer();

            $http.head(url).then(
                function (good) {
                    deferred.resolve(good.data);
                },
                function (bad) {
                    deferred.reject(bad);
                }
            );

            return deferred.promise;
        }

    };
});