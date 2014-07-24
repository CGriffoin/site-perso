/*global test*/
/*global ok*/
/*global api_test*/
/*global $*/

var initDeleteRequest = function( ){
    test("CLEAR  /init | returns 200", function () {
        api_test("/initLight", "DELETE", null, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
        });
    });
};

var initInsertRequest = function() {
    test("INIT  /init | returns 200", function () {
        api_test("/initLight", "POST", null, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
        });
    });
};

