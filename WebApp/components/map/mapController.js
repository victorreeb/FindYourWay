(function () {
    'use strict';

    angular
        .module('app')
        .controller('MapController', MapController);

    MapController.$inject = ['$scope', 'MapService', 'FlashService', 'TokenService'];

    function MapController($scope, MapService, FlashService, TokenService) {
        var vm = this;

        vm.token = {};
        vm.iteration = 1;
        vm.score = 0;
        vm.markers = [];
        vm.appellation = '';
        vm.indices = [];
        vm.afficher_map = afficher_map ;
        $scope.booleanMap = false;


        function afficher_map()
        {
          $scope.booleanMap = ! $scope.booleanMap;
          initMap();
        }

        function play(){
          var max_points = 5;
          if(vm.iteration > 0 && vm.iteration < max_points){
            vm.indices.push(MapService.postPoint(vm.markers[vm.iteration-1]._latlng.lat, vm.markers[vm.iteration-1]._latlng.lng));
            refreshIndice();
            vm.appellation = MapService.getPoint();
            refreshAppellation(vm.iteration+1);
          }
          else if(vm.iteration === 5){
            refreshAppellation(vm.iteration+1);
            vm.indices.push(MapService.postPoint(vm.markers[vm.iteration-1]._latlng.lat, vm.markers[vm.iteration-1]._latlng.lng));
            refreshIndice();
            vm.appellation = '';
          }
          else if(vm.iteration === 6){
            vm.score = MapService.postDestination(vm.markers[vm.iteration-1]._latlng.lat, vm.markers[vm.iteration-1]._latlng.lng);
            vm.map.off('click');
            refreshScore();
          }
        }

        function initMap(){
          //ajouter les params des champs lors de la création d'une partie

            MapService.postPartie(vm.partie.nom, vm.partie.description).then(function(response){
              console.log(response.message.token);
              TokenService.addHeader(response.message.token);
            });

          vm.appellation = MapService.getPoint();
          refreshAppellation(vm.iteration);
          vm.map = L.map('mapid').setView([48.866, 2.333], 5);
          L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(vm.map);


             /**
          * Listener on click on Map to add new marker
          */
          vm.map.on('click', function(e){
            addMarker(e);
            play();
            if(vm.iteration < 6){
              vm.iteration += 1;
            }
          });
        }

        function refreshAppellation(i){
          var title = '<h4>Placer le point (' + i + ')</h4>';
          var indice = '<br><p>Question : ' + vm.appellation + '</p>';
          if(i === 6){
            title = '<h4>Placer la destination (' + i + ')</h4>';
            indice = '<br><p>Servez-vous des indices trouvés ci-dessous pour retrouver la destination !</p>';
          }
          vm.appellation_print = title + indice;
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
          });
        }



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
