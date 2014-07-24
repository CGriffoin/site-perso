function api_test(url, type, data, callback) {
    $.ajax(
        {
            url: url,
            type: type,
            processData: false,
            contentType: 'application/json; charset=utf-8',
            data: data ? JSON.stringify(data) : data,
            dataType: 'json',
            async: false,
            complete: function (result) {
                callback(result.responseText, result.status);
            }
        });
}
function api_test_form(url, type, title, data, callback) {
    $.ajax(
        {
            url: url,
            type: type,
            processData: false,
            data: title + "=" + data,
            dataType: 'json',
            async: false,
            complete: function (result) {
                callback(result.responseText, result.status);
            }
        });
}