(function () {
    'use strict';

    angular
        .module('app')
        .factory('MapService', MapService);

    MapService.$inject = ['$http'];
    function MapService($http) {
        var service = {};

        service.getPartie = getPartie;
        service.getPoint = getPoint;
        service.getIndice = getIndice;
        service.postDestination = postDestination;
        service.getScore = getScore;

        return service;

        function getPartie(){
          // return $http.get('').then(handleSuccess, handleError('Error get Partie'));

        }

        function getPoint(){
          // return $http.get('').then(handleSuccess, handleError('Error get Partie'));

        }

        function getIndice(){
          // return $http.get('').then(handleSuccess, handleError('Error get Partie'));

        }

        function postDestination(){
          // return $http.get('').then(handleSuccess, handleError('Error get Partie'));

        }

        function getScore(){
          // return $http.get('').then(handleSuccess, handleError('Error get Partie'));

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
