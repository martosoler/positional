define(['react', 'google-map', 'superagent'], function (React, GMaps, Request) {
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
                zoom: 2,
                width: 1100,
                height: 600,
                points: [],
                gmaps_api_key: '',
                gmaps_sensor: false
            }
        }/*,

        componentWillMount: function() {
            this.fetchMarkers();
            setInterval(this.fetchMarkers, 2000);
        }*/,

        fetchMarkers: function() {
            Request
                .get('scripts/map/model/markers.json')
                .set('X-Requested-With', 'XMLHttpRequest')
                .set('Cache-Control', 'no-cache,no-store,must-revalidate,max-age=-1')
                .end(function(res){
                    this.updateMarkers(res.body.points);
                }.bind(this));
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

                var location = new GMaps.LatLng( point.latitude , point.longitude );

                var marker = new GMaps.Marker({
                    position: location,
                    map: map,
                    title: point.label
                });

                markers.push( marker );

            }) );

            this.setState( { markers : markers });
        },

        render: function () {
            var style = {
                width: this.props.width,
                height: this.props.height
            }

            return (
                <div style={style}></div>
                );
        },

        componentDidMount: function() {
            var mapOptions = {
                zoom: this.props.zoom,
                center: new GMaps.LatLng( this.props.latitude , this.props.longitude ),
                mapTypeId: GMaps.MapTypeId.ROADMAP
            };

            var map = new GMaps.Map( this.getDOMNode(), mapOptions);

            this.setState( { map : map } );

            this.updateMarkers(this.props.points);
            setInterval(this.fetchMarkers, 2000);
        }
    });

    return Map;
});