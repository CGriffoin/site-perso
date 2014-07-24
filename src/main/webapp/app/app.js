/*global angular*/
'use strict';

var rmApp = angular.module('geminicode', ['globalErrors',
                                    'gUtils',
                                    '$strap.directives',
                                    'angularFileUpload']);

rmApp.config(function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'app/loading/loading.html'
        })
        .when('/examples', {
            templateUrl: 'app/example/examples/examples.html',
            controller: 'ExamplesCtrl',
            resolve: {
                loadedExamples: function ($q, $location, $route, ExampleSrvc) {

                    var deferred = $q.defer();

                    return ExampleSrvc.list();
                }
            }
        })
        .when('/example/new', {
            templateUrl: 'app/example/examplenew/examplenew.html',
            controller: 'ExampleNewCtrl'
        })
        .when('/example/:exampleId', {
            templateUrl: 'app/example/example/example.html',
            controller: 'ExampleCtrl',
            resolve: {
                loadedExample: function ($q, $route, ExampleSrvc) {
                    var deferred = $q.defer();

                    return ExampleSrvc.get($route.current.params.exampleId);
                }
            }
        })
        .when('/error', {
            templateUrl: 'app/_shared/partials/error.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});



