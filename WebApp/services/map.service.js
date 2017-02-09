(function () {
    'use strict';

    angular
        .module('app')
        .factory('MapService', MapService);

    MapService.$inject = ['$http', '$rootScope'];
    function MapService($http, $rootScope) {
        var service = {};

        service.getPartie = getPartie;
        service.getPoint = getPoint;
        service.postPoint = postPoint;
        service.getIndice = getIndice;
        service.getDestination = getDestination;
        service.postDestination = postDestination;
        service.getScore = getScore;

        return service;

        function getPartie(){
          return $http.get('/api/parties').then(handleSuccess, handleError('Error get Partie'));

        }

        function getPoint(){ // get appellation
          return $http.get('/api/points').then(handleSuccess, handleError('Error get Point'));
        }

        function postPoint(lat, lng){
          return $http.post('/api/points', {latitude: lat, longitude: lng}).then(handleSuccess, handleError('Error post Point'));
        }

        function getIndice(){
          return $http.get('/api/indices').then(handleSuccess, handleError('Error get Indice'));
        }

        function getDestination(){
          return $http.get('/api/destinations').then(handleSuccess, handleError('Error get Destination'));
        }

        function postDestination(lat, lng){
          return $http.post('/api/destinations', {latitude: lat, longitude: lng}).then(handleSuccess, handleError('Error post Destination'));

        }

        function getScore(){
          return $http.get('/api/score').then(handleSuccess, handleError('Error get Partie'));

        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
