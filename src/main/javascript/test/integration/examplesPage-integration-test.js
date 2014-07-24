/**
 * Created with IntelliJ IDEA.
 * User: vdehorta
 * Date: 06/03/14
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
describe('Examples Page', function () {
    describe('Data access', function () {
        it('Should be redirected to examples page when requesting /', function () {
            browser().navigateTo(ROOT_URL);
            expect(repeater('#G_examples').count()).toEqual(1);
        });
        it('Should access DIRECTLY to examples page', function () {
            browser().navigateTo(ROOT_URL + '/examples');
            expect(repeater('#G_examples').count()).toEqual(1);
        });
    });
});
