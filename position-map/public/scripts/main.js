require.config({
    "baseUrl" : "scripts",
    "paths"   : {
        "react" : "react/react",
        "text": "requirejs-text/text",
        "jsx": "jsx-requirejs-plugin/js/jsx",
        "JSXTransformer": "jsx-requirejs-plugin/js/JSXTransformer-0.10.0",
        "goog": "requirejs-plugins/src/goog",
        "async": "requirejs-plugins/src/async",
        "propertyParser" : 'requirejs-plugins/src/propertyParser',
        "google-map": "map/google-map"
    },
    "jsx" : {
        "fileExtension": ".jsx"
    }
});

require(
    ['react', 'jsx!map/map'],
    function(React, Map){
        console.log('Mounting map into div');

        React.renderComponent(
            Map({
                points:[{"latitude":"-31.216979", "longitude":"-64.315231", "label":"Mi casa"}]
            }),
            //<Map points={[{"latitude":"-31.216979", "longitude":"-64.315231", "label":"Mi casa"}]}/>,
            document.getElementById('container'));
    }
);
