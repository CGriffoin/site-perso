/*global describe*/
/*global it*/
/*global browser*/
/*global expect*/
/*global input*/
/*global repeater*/
/*global element*/
/*global ROOT_URL*/

'use strict';

//already refactored
describe('Example Page', function () {
    describe('Edit example name', function () {
        it('Should display correct example name', function () {
            browser().navigateTo(ROOT_URL + '/example/12');
            expect(element('#G_example-name-editable-span').text()).toMatch('Example 1');
        });
        it('Should be able to enter new example name', function () {
            element('#G_example-name-editable-span').click();
            element('#G_example-name-editable-input').click();
            input('text').enter('newValForExample');
            expect(element('#G_example-name-editable-span').text()).toMatch('newValForExample');

            //Cannot test update request because blur event cannot be reproduced in Angular e2e tests

        });
    });
});