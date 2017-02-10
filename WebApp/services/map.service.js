(function () {
    'use strict';

    angular
        .module('app')
        .factory('MapService', MapService);

    MapService.$inject = ['$http', '$rootScope'];
    function MapService($http, $rootScope) {
        var service = {};

        service.postPartie = postPartie;
        service.getPoint = getPoint;
        service.postPoint = postPoint;
        service.postDestination = postDestination;

        return service;

        /**
        * récupère token
        */
        function postPartie(nom, description){
          return $http.post('/findyourway/api/parties', {nom: nom, description: description}).then(handleSuccess, handleError('Error get Partie'));

        }

        /**
        * récupère appellation
        */
        function getPoint(){
          return $http.get(':8080/findyourway/api/parties/point').then(handleSuccess, handleError('Error get Point'));
        }

        /**
        * recupère un indice final
        */
        function postPoint(lat, lng){
          return $http.post('/findyourway/api/parties/point', {latitude: lat, longitude: lng}).then(handleSuccess, handleError('Error post Point'));
        }

        /**
        * récupère score
        */
        function postDestination(lat, lng){
          return $http.post('/findyourway/api/parties/destination', {latitude: lat, longitude: lng}).then(handleSuccess, handleError('Error post Destination'));

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
