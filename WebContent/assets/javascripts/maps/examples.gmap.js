/*
Name: 			Maps / Basic - Examples
Written by: 	Okler Themes - (http://www.okler.net)
Theme Version: 	1.7.0
*/

(function($) {

	'use strict';

	var initBasic = function() {
		var map = new GMaps({
			div: '#gmap-basic-marker',
			lat: -23.5640803,
			lng: -46.6524362,
			markers: [{
				lat: -23.5640803,
				lng: -46.6524362
			}]
		});

		map.addMarker({
			lat: -23.5640803,
			lng: -46.6524362
		});
	};

	var initBasicWithMarkers = function() {
		var map = new GMaps({
			div: '#gmap-basic',
			lat: -23.5641085,
			lng: -46.65240890000001,
			markers: [{
				lat: -23.5641085,
				lng: -46.65240890000001,
				infoWindow: {
					content: '<p>Basic</p>'
				}
			}]
		});

		map.addMarker({
			lat: -23.5641085,
			lng: -46.65240890000001,
			infoWindow: {
				content: '<p>Example</p>'
			}
		});
	};

	var initStatic = function() {
		var url = GMaps.staticMapURL({
			size: [725, 500],
			lat: -12.043333,
			lng: -77.028333,
			scale: 1
		});

		$('#gmap-static')
			.css({
				backgroundImage: 'url(' + url + ')',
				backgroundSize: 'cover'
			});
	};

	var initContextMenu = function() {
		var map = new GMaps({
			div: '#gmap-context-menu',
			lat: -23.5641085,
			lng: -46.65240890000001
		});

		map.setContextMenu({
			control: 'map',
			options: [
				{
					title: 'Add marker',
					name: 'add_marker',
					action: function(e) {
						this.addMarker({
							lat: e.latLng.lat(),
							lng: e.latLng.lng(),
							title: 'New marker'
						});
					}
				},
				{
					title: 'Center here',
					name: 'center_here',
					action: function(e) {
						this.setCenter(e.latLng.lat(), e.latLng.lng());
					}
				}
			]
		});
	};

	var initStreetView = function() {
		var gmap = GMaps.createPanorama({
			el: '#gmap-street-view',
			lat : 48.85844,
			lng : 2.294514
		});

		$(window).on( 'sidebar-left-toggle', function() {
			google.maps.event.trigger( gmap, 'resize' );
		});
	};

	// auto initialize
	$(function() {

		initBasic();
		initBasicWithMarkers();
		initStatic();
		initContextMenu();
		initStreetView();

	});

}).apply(this, [jQuery]);
