/**
 * Created with IntelliJ IDEA.
 * User: vdehorta
 * Date: 06/03/14
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
describe('Example New Page', function () {
    describe('Data access', function () {
        it('Should access DIRECTLY to examplenew page', function () {
            browser().navigateTo(ROOT_URL);
            expect(repeater('#G_examplenew').count()).toEqual(0);
            browser().navigateTo(ROOT_URL + '/example/new');
            expect(repeater('#G_examplenew').count()).toEqual(1);
        });
        it('Should access INDIRECTLY to examplenew page', function () {
            browser().navigateTo(ROOT_URL);
            expect(repeater('#G_examplenew').count()).toEqual(0);
            element("#G_examples-new-btn").click();
            expect(repeater('#G_examplenew').count()).toEqual(1);
        });
    });
});
