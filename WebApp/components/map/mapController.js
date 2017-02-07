(function () {
    'use strict';

    angular
        .module('app')
        .controller('MapController', MapController);

    MapController.$inject = ['$rootScope'];

    function MapController($rootScope) {
        var vm = this;

        vm.markers = [];
        vm.appellations = [];
        vm.indices = [];

        vm.initMap = (function initMap(){
          vm.map = L.map('mapid').setView([48.866, 2.333], 5);
          L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(vm.map);

        })();

        vm.refreshAppellation = (function refreshAppellation(){

        })();

        vm.refreshIndice = (function refreshIndice(){

        })();

        vm.refreshScore = (function refreshScore(){

        })();

        vm.map.on('click', function(e){
          addMarker(e);
        });

        function addMarker(coordonnees){
          var marker = L.marker([coordonnees.latlng.lat, coordonnees.latlng.lng]).addTo(vm.map);
          vm.markers.push(marker);
          drawLinesMarkers();
        }

        function drawLinesMarkers(){
            var lines = [];
            for(var i in vm.markers){
              var x = vm.markers[i]._latlng.lat;
              var y = vm.markers[i]._latlng.lng;
              lines.push([x, y]);
            }
            L.polyline(lines).addTo(vm.map);
        }

    }

})();
