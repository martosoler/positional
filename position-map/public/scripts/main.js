require.config({
    "baseUrl" : "scripts",
    "paths"   : {
        "react" : "vendors/react/react",
        "text": "vendors/requirejs-text/text",
        "jsx": "vendors/jsx-requirejs-plugin/js/jsx",
        "JSXTransformer": "vendors/jsx-requirejs-plugin/js/JSXTransformer-0.10.0",
        "goog": "vendors/requirejs-plugins/src/goog",
        "async": "vendors/requirejs-plugins/src/async",
        "propertyParser" : 'vendors/requirejs-plugins/src/propertyParser',
        "google-map": "map/google-map",
        "superagent": "vendors/superagent/superagent"
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
