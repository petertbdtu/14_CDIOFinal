/**
 * Predefines requests with JSON stringification.
 */

var Agent = {}
Agent.getJson = function(url, success, error){
	$.ajax({
		url:url,
		method: 'GET',
		contentType:'application/json',
		success:success,
		error:error
	})
}
Agent.postJson = function(url, data, success, error){
	$.ajax({
		url:url,
		method: 'POST',
		data: JSON.stringify(data),
		success: success,
		contentType: 'application/json',
		error: error
	})
}
Agent.putJson = function(url, data, success, error){
    $.ajax({
        url:url,
        method: 'PUT',
        data: JSON.stringify(data),
        success: success,
        contentType: 'application/json',
        error: error
    })
}
Agent.delete = function(url, success, error){
	$.ajax({
		url:url,
		method: 'DELETE',
		success:success,
		error:error
	})
}