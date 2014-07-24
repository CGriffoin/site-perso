/*global rmApp*/
'use strict';

rmApp.controller("ErrorCtrl", function (HeaderDynamicPartSrvc) {
    var pageTitle = "Error";
    HeaderDynamicPartSrvc.notifyNewDynamicPart(pageTitle, "", [], {});
});