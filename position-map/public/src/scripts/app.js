/** @jsx React.DOM */

var React = require('react');
var Map = require('./map.js')

var PositionalApp = React.createClass({
		render: function () {
			return ( <Map 
					latitude={60.170833} 
					longitude={24.9375} 
					zoom={5} 
					width={window.screen.availWidth} 
					height={window.screen.availHeight}
					points={[
						{latitude:60.1876172,longitude:24.815366,title:"HIIT Open Innovation House"},
						{latitude:60.185478,longitude:24.812257,title:"HIIT Otaniemi"},
						{latitude:60.2049747,longitude:24.9634712,title:"HIIT Kumpula"}]
					} />
			);
		}
    }
);

module.exports = PositionalApp;