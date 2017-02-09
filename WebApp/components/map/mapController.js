(function () {
    'use strict';

    angular
        .module('app')
        .controller('MapController', MapController);

    MapController.$inject = ['$scope', 'MapService', 'FlashService'];

    function MapController($scope, MapService, FlashService) {
        var vm = this;

        vm.token = '';
        vm.iteration = 1;
        vm.score = 5;
        vm.markers = [];
        vm.appellation = 'exemple La ville est la capitale européenne.';
        vm.indices = [];
        // exemple
        vm.indices.push("test 1");
        vm.indices.push("test 2");
        vm.indices.push(null); // pas affiché
        vm.indices.push("Tour Eiffel");


        function play(){
          var max_points = 5; // points à jouer
          if(vm.iteration > 0 && vm.iteration < max_points){
            refreshAppellation(vm.iteration+1);
            // vm.indices.push(MapService.postPoint(vm.markers[vm.iteration-1].latlng.lat, vm.markers[vm.iteration-1].latlng.lng));
            refreshIndice();
            vm.appellation = MapService.getPoint(); //get next Point
          }
          else if(vm.iteration === 5){
            refreshAppellation(vm.iteration+1);
            // vm.indices.push(MapService.postPoint(vm.markers[vm.iteration-1].latlng.lat, vm.markers[vm.iteration-1].latlng.lng));
            refreshIndice();
            vm.appellation = MapService.getDestination();
          }
          else if(vm.iteration === 6){
            // vm.score = MapService.getScore();
            vm.map.off('click');
            refreshScore();
          }
        }

        vm.initMap = (function initMap(){
          var token = MapService.getPartie();
          vm.appellation = MapService.getPoint(); //getPoint 1
          refreshAppellation(vm.iteration);
          vm.map = L.map('mapid').setView([48.866, 2.333], 5);
          L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(vm.map);
        })();

        function refreshAppellation(i){
          var title = '<h4>Placer le point (' + i + ')</h4>';
          if(i === 6){
            title = '<h4>Placer la destination (' + i + ')</h4>';
          }
          vm.appellation_print = title + '<br><p>Indice : ' + vm.appellation + '</p>';
        }

        function refreshIndice(){
          var title = '';
          if(vm.indices.length === 1){
            title = "<h4>Indice disponible pour trouver la destination finale</h4>";
          }
          else if(vm.indices.length > 1){
            title = "<h4>Indices disponibles pour trouver la destination finale</h4>";
          }
          else{
            title = "<h4>Aucun indice disponible pour trouver la destination finale</h4>";
          }
          vm.indices_print = title + '<ul>';
          for(var i = 0; i < vm.indices.length ; i++){
            if(vm.indices[i] !== null){
              vm.indices_print += "<li>" + vm.indices[i] + "</li>";
            }
          }
          vm.indices_print += '</ul>';
        }

        function refreshScore(){
          $scope.$apply(function(){
            FlashService.Success('Partie terminée ! Vous avez obtenu ' + vm.score + ' points', true);
            // vm.score_print = "<p class='text-center'>Part terminée ! Vous avez obtenu " + vm.score + " points</p>";
          });
        }

        /**
        * Listener on click on Map to add new marker
        */
        vm.map.on('click', function(e){
          addMarker(e);
          play();
          vm.iteration += 1;
        });

        function addMarker(coordonnees){
          vm.markers.push(L.marker([coordonnees.latlng.lat, coordonnees.latlng.lng]).addTo(vm.map));
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
