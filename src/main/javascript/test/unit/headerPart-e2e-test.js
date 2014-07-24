
/*global describe*/
/*global it*/
/*global browser*/
/*global expect*/
/*global repeater*/
/*global element*/
/*global ROOT_URL*/

'use strict';

//already refactored
describe('Header Part', function () {

    describe('Data displayed', function () {
        it('Should contain "Home" part', function () {
            selfBrowser.navigateTo('/examples');
            expect(repeater('#G_header').count()).toEqual(1);
            expect(repeater('#G_header-home').count()).toEqual(1);
        });
        it('Should display column "Name"', function () {
            expect(repeater('#G_header-title').count()).toEqual(1);
        });
    });
});