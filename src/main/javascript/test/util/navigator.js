/*global ROOT_URL*/
/*global element*/

var selfBrowser = {};

selfBrowser.navigateTo = function (location) {
    switch (location) {
        case '': // when empty go to home
            element('#G_header-home').click();
            break;
        case '/examples':
            element('#G_header-home').click();
            break;
        case '/example/12':
            element('#G_header-home').click();
            element('#G_example-id-1').click();
            break;
        case '/example/35':
            element('#G_header-home').click();
            element('#G_example-id-2').click();
            break;
        case '/example/37':
            element('#G_header-home').click();
            element('#G_example-id-3').click();
            break;
        case '/example/new':
            element('#G_header-home').click();
            element('#G_examples-new-btn').click();
            break;
        default:
            element('G_header-home').click();
            break;
    }
};