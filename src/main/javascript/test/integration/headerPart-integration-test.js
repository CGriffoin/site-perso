/**
 * Created with IntelliJ IDEA.
 * User: vdehorta
 * Date: 06/03/14
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
describe('Header Part', function () {
    describe('Pages', function () {
        it('Should be present on all application page', function () {
            browser().navigateTo(ROOT_URL);
            expect(repeater('#G_header').count()).toEqual(1);

            browser().navigateTo(ROOT_URL + '/examples');
            expect(repeater('#G_header').count()).toEqual(1);

            browser().navigateTo(ROOT_URL + '/example/new');
            expect(repeater('#G_header').count()).toEqual(1);

            browser().navigateTo(ROOT_URL + '/example/1');
            expect(repeater('#G_header').count()).toEqual(1);
        });
    });
});
