/*global test*/
/*global ok*/
/*global api_test*/
/*global $*/


"use strict";

initDeleteRequest();
setTimeout("initInsert()", 5000);

var initInsert = function () {
    initInsertRequest();
    setTimeout("allRequests()", 10000);
};

var allRequests = function () {
    exampleRequests();
};