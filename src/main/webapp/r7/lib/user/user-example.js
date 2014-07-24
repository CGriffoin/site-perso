/*global test*/
/*global ok*/
/*global api_test*/
/*global $*/
/*global _*/

"use strict";

var exampleRequests = function() {

    var createdExampleId;

    test("EXAMPLE | List Example - GET /example | returns 200 and example list", function () {
        api_test("/rest/example", "GET", null, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
            var examples = $.parseJSON(responseBody);
            ok(examples.length === 4, 'examples.length === 4 and was: ' + examples.length);
        });
    });

    test("EXAMPLE | Get Example 1 - GET /example/1 | returns 200 and example data", function () {
        api_test("/rest/example/1", "GET", null, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
            var example = $.parseJSON(responseBody);
            ok(example.id === "1", 'example.id === "1"');
            ok(example.name === "Example 1", 'example.name === "Example 1"');
        });
    });

    test("EXAMPLE | Create Example - POST /example | returns 200", function () {
        api_test("/rest/example", "POST", {"name":"newExample"}, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
            var newExample = $.parseJSON(responseBody);
            createdExampleId = newExample.id;
        });
        api_test("/rest/example/" + createdExampleId, "GET", null, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
        });
    });

    test("EXAMPLE | Update Example - PUT /example/1 | returns 200", function () {
        api_test("/rest/example/1", "PUT", {"name":"exampleChanged", "id" : 1}, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
            var example = $.parseJSON(responseBody);
            ok(example.id === "1", 'example.id === "1"');
            ok(example.name === "exampleChanged", 'example.name === "exampleChanged"');
        });
    });

    test("EXAMPLE | Delete Example  - DELETE /example/:exampleId | returns 200", function () {
        api_test("/rest/example/" + createdExampleId, "DELETE", null, function (responseBody, responseCode) {
            ok(responseCode === 200, "200 OK" + JSON.stringify(responseBody, null, 4));
        });
    });
};

