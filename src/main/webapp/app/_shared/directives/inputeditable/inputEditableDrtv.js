/*global rmApp*/
'use strict';

rmApp.directive('inputeditable', function () {

    return {
        restrict: 'E',
        replace: true,
        templateUrl: "app/_shared/directives/inputeditable/inputeditable.html",
        scope: {
            identifier: '@',
            label: '@',
            text: '=',
            type: '@'
        },
        link: function (scope, element, $timeout) {

            scope.editMode = (scope.text === "");

            // find the input elemnt of this directive ...
            var input = element.find('input');

            // and listen for blur event
            input.bind('blur', function () {
                // since blur event occured ouside the angular execution context
                // we need to call scope.$apply to tell angularjs about the changes
                scope.$apply(function () {
                    // the change is to disable the editMode
                    scope.editMode = (scope.text === "");
                    if (!scope.type) {
                        scope.type = "";
                    }
                    scope.$emit("EVENT-Changed-" + scope.type);
                });

            });
            input.bind('click', function () {
                scope.$apply(function () {
                    input.focus();
                    input.select();
                });

            });
        }
    };

});


