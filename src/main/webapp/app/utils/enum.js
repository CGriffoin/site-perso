function Enum() {
    if (!(this && this instanceof Enum)) {
        throw {message: "Enum can only be instantiated"};
    }
    var that = this;
    that.enums = [].slice.call(arguments);
    // shortcut to get a property by its name
    that.getByName = function (name) {
        return that[name];
    };

    // get all enum names in an array
    // does not return the actual reference
    that.getNames = function () {
        return that.enums;
    };
    that.enums.forEach(function (enumName) {
        that[enumName] = { toString: that.toString, name: enumName };
    });
    // freeze the object to ensure type safety
    if (Object.freeze) {
        Object.freeze(that);
    } else  {
        console.log('Enum.js: Finalizing objects not supported.');
    }
};