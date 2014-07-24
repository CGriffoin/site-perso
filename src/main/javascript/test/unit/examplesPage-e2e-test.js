
/*global describe*/
/*global it*/
/*global browser*/
/*global expect*/
/*global repeater*/
/*global element*/
/*global ROOT_URL*/

'use strict';

//already refactored
describe('Examples Page', function () {

    describe('Data displayed', function () {
        it('Should display column "Identifier"', function () {
            selfBrowser.navigateTo('/examples');
            expect(repeater('#G_example-id-header').count()).toEqual(1);
            expect(repeater('#G_example-id-1').count()).toEqual(1);
        });
        it('Should display column "Name"', function () {
            expect(repeater('#G_example-name-header').count()).toEqual(1);
            expect(repeater('#G_example-name-1').count()).toEqual(1);
        });
    });
});