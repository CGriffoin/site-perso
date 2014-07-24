/**
 * Created with IntelliJ IDEA.
 * User: vdehorta
 * Date: 06/03/14
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
describe('Example Page', function () {
    describe('Data access', function () {
        it('Should access DIRECTLY to example page', function () {
            browser().navigateTo(ROOT_URL);
            expect(repeater('#G_example').count()).toEqual(0);
            browser().navigateTo(ROOT_URL + '/example/1');
            expect(repeater('#G_example').count()).toEqual(1);
        });
        it('Should access INDIRECTLY to example page', function () {
            browser().navigateTo(ROOT_URL);
            expect(repeater('#G_example').count()).toEqual(0);
            element("#G_example-id-1").click();
            expect(repeater('#G_example').count()).toEqual(1);
        });
    });
});
