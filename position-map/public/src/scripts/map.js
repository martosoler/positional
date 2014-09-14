/**
 * @jsx React.DOM
 */
"use strict";

var __in_node = (typeof exports !== 'undefined' && this.exports !== exports);

if( __in_node ) {
  var React = require('react');
}

var Map = React.createClass({

  // initialize local variables
  getInitialState: function() {
    return {
      map : null,
      markers : []
    };
  },

  // set some default values
  getDefaultProps: function() {
    return {
      latitude: 0,
      longitude: 0,
      zoom: 4,
      width: 500,
      height: 500,
      points: [],
      gmaps_api_key: '',
      gmaps_sensor: false
    }
  },

  // update geo-encoded markers
  updateMarkers : function(points) {

    var markers = this.state.markers;
    var map = this.state.map;

    // remove everything
    markers.forEach( function(marker) {
      marker.setMap(null);
    } );

    this.state.markers = [];

    // add new markers
    points.forEach( (function( point ) {

      var location = new google.maps.LatLng( point.latitude , point.longitude );

      var marker = new google.maps.Marker({
        position: location,
        map: map,
        title: point.label
      });

      markers.push( marker );

    }) );

    this.setState( { markers : markers });
  },

  render : function() {

    var style = {
      width: this.props.width,
      height: this.props.height
    }

    return (
      <div style={style}></div>
    );
  },

  componentDidMount : function() {

    window.mapLoaded = (function() {

      var mapOptions = {
        zoom: this.props.zoom,
        center: new google.maps.LatLng( this.props.latitude , this.props.longitude ),
        mapTypeId: google.maps.MapTypeId.ROADMAP
      };
      
      var map = new google.maps.Map( this.getDOMNode(), mapOptions);

      this.setState( { map : map } );
      this.updateMarkers(this.props.points);

    }).bind(this);

    var s =document.createElement('script');
    s.src = 'https://maps.googleapis.com/maps/api/js?key=' + this.props.gmaps_api_key + '&sensor=' + this.props.gmaps_sensor + '&callback=mapLoaded';
    document.head.appendChild( s );

  },

  // update markers if needed
  componentWillReceiveProps : function(props) {
    if( props.points ) this.updateMarkers(props.points);
  }

});

if( __in_node ) {
  module.exports = Map;
}

