_.mixin({
    time: function (s) {
        var ms = s % 1000;
        s = (s - ms) / 1000;
        var secs = s % 60;
        s = (s - secs) / 60;
        var mins = s % 60;
        var hrs = (s - mins) / 60;

        return hrs + 'h:' + mins + 'm:' + secs + 'sec.' + ms;
    }
});

/* commented but do not delete - calculate time spent by tests
 var spentTime = _.chain($('.timer-result'))
 .map(function(e) {return parseInt(e.innerText.replace("ms", ""), 10)})
 .reduce(function(memo, num){ return memo + num; })
 .time()
 .value();*//**
 * Created with IntelliJ IDEA.
 * User: devfront
 * Date: 10/22/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
