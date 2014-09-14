var React = require('react');
var app = require('./app.js');

React.renderComponent(app({
    name: 'Nanci'
}), document.getElementById('container'));