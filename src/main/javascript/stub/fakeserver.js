/*global app*/
/*global require*/
'use strict';

var express = require('express'),
    example_route = require('./routes/example_route'),
    http = require('http'),
    path = require('path');

var app = express();

var requests = [];

var logger = function (req, res, next) {
    if (req.originalUrl.substring(0, 5) === "/rest") {
        if (req.method !== "GET") {
            requests.unshift({url: req.originalUrl, verb: req.method, body: req.body, code: req.statusCode, timestamp: new Date().getTime()});
        }
    }
    next(); // Passing the request to the next handler in the stack.
};

app.configure(function () {
    app.set('port', process.env.PORT || 3001);
    app.set('views', __dirname + '/public');
    app.engine('.html', require('ejs').renderFile);
    app.set('view engine', 'html');
    app.use(express.favicon());
    app.use(express.logger('dev'));
    app.use(express.bodyParser());
    app.use(logger);
    app.use(express.methodOverride());
    app.use(app.router);
    app.use(express.static(path.join(__dirname, '../../')));
});

app.configure('development', function () {
    app.use(express.errorHandler());
});



app.get('/requests', function (req, res) {
    res.json(200, requests);
});



/*   EXAMPLE REST REQUESTS   */

app.get('/rest/example', example_route.list);
app.get('/rest/example/:exampleId', example_route.get);
app.put('/rest/example/:exampleId', example_route.update);
app.post('/rest/example', example_route.create);
app.delete('/rest/example/:exampleId', example_route.delete);

http.createServer(app).listen(app.get('port'), function () {
    console.log("Express server listening on port " + app.get('port'));
});
