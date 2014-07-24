/*global describe*/
/*global it*/
/*global browser*/
/*global expect*/
/*global element*/
/*global input*/
/*global selfBrowser*/

'use strict';

//already refactored
describe('Example-Add Page', function () {
    describe('Input fields', function () {
        it('Should display all necessary fields', function () {
            selfBrowser.navigateTo('/example/new');
            expect(repeater('#G_examplenew').count()).toEqual(1);
            expect(element('#G_examplenew-name-input').count()).toBe(1);
            expect(element('#G_examplenew-create-btn').count()).toBe(1);
        });
        it('Should be able to enter example name', function () {
            expect(element('#G_examplenew-name-input > input').val()).toMatch('');
            input('example.name').enter('exampleName');
            expect(element('#G_examplenew-name-input > input').val()).toMatch('exampleName');
        });
        it('Should be able to create example', function () {
            element('#G_examplenew-create-btn').click();
            expect(element('#G_footer .g-verb').text()).toMatch("POST");
            expect(element('#G_footer .g-path').text()).toMatch("/rest/example");
            expect(element('#G_footer .g-body').text()).toContain('"name":"exampleName"');
        });
    });
});