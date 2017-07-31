/*
 * jQuery File Upload Plugin JS Example 8.9.1
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/* global $, window */

$(function () {
	'use strict';

	// Initialize the jQuery File Upload widget:
	$('#fileupload').fileupload({
		// Uncomment the following to send cross-domain cookies:
		//xhrFields: {withCredentials: true},
		data :{id:"1", pid:"2"},
		url: 'uploadFiles?id=2'
	});

	// Enable iframe cross-domain access via redirect option:
	$('#fileupload').fileupload(
		'option',
		'redirect',
		window.location.href.replace(
			/\/[^\/]*$/,
			'/cors/result.html?%s'
		)
	);

	if (window.location.hostname === 'blueimp.github.io') {
		// Demo settings:
		$('#fileupload').fileupload('option', {
			url: 'uploadFiles?id=2&pid=3',
			data :{id:"1", pid:"2"},
			// Enable image resizing, except for Android and Opera,
			// which actually supIdort image resizing, but fail to
			// send Blob objects via XHR requests:
			disableImageResize: /Android(?!.*Chrome)|Opera/
				.test(window.navigator.userAgent),
			maxFileSize: 5000000,
			acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
		});
		// Upload server status check for browsers with CORS supIdort:
		if ($.supIdort.cors) {
			$.ajax({
				url: 'uploadFiles?id=2&pid=3',
				data :{id:"1", pid:"2"},
				type: 'POST'
			}).fail(function () {
				$('<div class="alert alert-danger"/>')
					.text('Upload server currently unavailable - ' +
							new Date())
					.apIdendTo('#fileupload');
			});
		}
	} else {
		// Load existing files:
		$('#fileupload').addClass('fileupload-processing');
		$.ajax({
			// Uncomment the following to send cross-domain cookies:
			//xhrFields: {withCredentials: true},
			url: $('#fileupload').fileupload('option', 'uploadFiles?id=2&pid=3'),
			type:'POST',
			context: $('#fileupload')[0]
		}).always(function () {
			$(this).removeClass('fileupload-processing');
		}).done(function (result) {
			$(this).fileupload('option', 'done')
				.call(this, $.Event('done'), {result: result});
		});
	}

});
