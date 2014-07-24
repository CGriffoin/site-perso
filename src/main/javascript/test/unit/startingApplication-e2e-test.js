/*global describe*/
/*global it*/
/*global browser*/
/*global expect*/
/*global repeater*/
'use strict';


describe('Starting application', function () {
    it('Should be redirected to example list', function () {
        browser().navigateTo(ROOT_URL);
        expect(repeater('#G_examples').count()).toEqual(1);
    });
});