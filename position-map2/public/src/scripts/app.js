/** @jsx React.DOM */

var React = require('react');
var Map = require('react-map/src/map')

var HelloMessage = React.createClass({
            render: function () {
                return ( <Map latitude={60.170833} longitude={24.9375} zoom={5} width={250} height={250}
  points={[{latitude:60.1876172,longitude:24.815366,title:"HIIT Open Innovation House"},{latitude:60.185478,longitude:24.812257,title:"HIIT Otaniemi"}, {latitude:60.2049747,longitude:24.9634712,title:"HIIT Kumpula"}]} />
                );
            }
    }
);

module.exports = HelloMessage;