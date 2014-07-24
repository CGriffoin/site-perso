/*global console*/
/*global rmApp*/
/*global angular*/
/*global $*/
/*global S*/

'use strict';

rmApp.filter('capitalizeFormat', function (u) {
    return function (arg) {
        return u.capitalize(arg);
    };
});