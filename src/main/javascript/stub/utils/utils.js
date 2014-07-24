var _ = require('underscore');


exports.deepClone = function (obj) {
    return deepClone(obj);
};

function existy(x) {
    return x != null;
}


function deepClone(obj) {
    if (!existy(obj) || !_.isObject(obj))
        return obj;
    var temp = new obj.constructor();
    for (var key in obj)
        if (obj.hasOwnProperty(key)) {
            temp[key] = deepClone(obj[key]);
        }
    return temp;
}