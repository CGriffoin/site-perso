/*global exports*/
/*global _*/
'use strict';

var _ = require('underscore');

var _ = require('underscore'),
utils = require('../utils/utils');

var oneExample = {
    "id": "22255331467753984",
    "name": "Example 1"
};

var createdExample = {
    "id": "5632323233",
    "name": "Example created"
};

var updatedExample = {
    "id": "22255331467753984",
    "name": "Example updated"
};

var allExamples = [
    {
        id: "12",
        name: "Example 12"
    },
    {
        id: "35",
        name: "Example 35"
    },
    {
        id: "37",
        name: "Example 37"
    }
];

exports.list = function (req, res) {
    res.json(200, allExamples);
};

exports.get = function (req, res) {
    res.json(200, oneExample);
};

exports.create = function (req, res) {
    res.send(200, createdExample);
};

exports.update = function (req, res) {
    res.send(200, updatedExample);
};

exports.delete = function (req, res) {
    res.json(200, "");
};