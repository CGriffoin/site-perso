/* global angular */
/* global $ */
/* global S */
/* global update_timer_display */

"use strict";

/*
 * Global error handler
 */
angular
    .module('globalErrors', [])
    .config(function ($provide, $httpProvider) {

        var showMessage = function (content) {
            $.pnotify({
                title: 'Success',
                text: content,
                delay: 3000,
                opacity: 0.8,
                type: "success"
            });
        };

        var showError = function (content) {
            $.pnotify({
                title: 'Error',
                text: content,
                opacity: 0.8,
                type: "error",
                delay: 5000
            });
        };

        var getFormattedErrorMsg = function (errorResponse) {
            if (S(errorResponse.data).startsWith("<html>")) {
                return "Error";
            } else {
                return errorResponse.data.replace(/<(?:.|\n)*?>/gm, '');
            }
        };

        $httpProvider.responseInterceptors.push(function ($timeout, $location, $q) {
            return function (promise) {
                return promise.then(function (successResponse) {
                    if (successResponse.config.method.toUpperCase() !== 'GET') {
                        var verb = "";
                        switch (successResponse.config.method.toUpperCase()) {
                            case 'POST':
                                verb = "create";
                                break;
                            case 'PUT':
                                verb = "update";
                                break;
                            case 'DELETE':
                                verb = "delete";
                                break;
                            case 'HEAD':
                                verb = "head";
                                break;
                            default:
                                verb = successResponse.config.method.toUpperCase();
                                break;
                        }
                        // show message only if not HEAD
                        if (verb !== "head") {
                            showMessage(verb + ' successful');
                        }
                    }
                    return successResponse;
                }, function (errorResponse) {
                    if (errorResponse.status === 403) {
                        $location.path("/error");
                        $location.replace();
                    }

                    // show message only if not HEAD
                    if (errorResponse.config.method.toUpperCase() !== "HEAD") {
                        try {
                            var theMessage = getFormattedErrorMsg(errorResponse);
                            showError(errorResponse.config.method.toUpperCase() + ' in error' + '\n' + theMessage);
                        } catch (e) {
                            showError('error ' + errorResponse.status);
                        }
                    }

                    return $q.reject(errorResponse);
                });
            };
        });
    });