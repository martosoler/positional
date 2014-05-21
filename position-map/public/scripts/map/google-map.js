/**
 * Created by papa on 18/05/14.
 */
define('google-map', ['async!http://maps.google.com/maps/api/js?v=3&sensor=false'],
    function(){
        // return the gmaps namespace for brevity
        return window.google.maps;
    }
);