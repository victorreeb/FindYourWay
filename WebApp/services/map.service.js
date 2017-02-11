(function () {
    'use strict';

    angular
        .module('app')
        .factory('MapService', MapService);

    MapService.$inject = ['$http', '$rootScope','API_END_POINT'];
    function MapService($http, $rootScope,API_END_POINT) {
        var service = {};


        service.postPartie = postPartie;
        service.getPoint = getPoint;
        service.postPoint = postPoint;
        service.postDestination = postDestination;

        return service;

        /**
        * récupère token
        */
        function postPartie(pnom, pdescription){
          return $http.post( API_END_POINT + '/parties', {"nom": pnom, "description": pdescription}).then(handleSuccess, handleError('Error get Partie'));

        }

        /**
        * récupère appellation
        */
        function getPoint(){
          return $http.get(API_END_POINT + '/parties/point').then(handleSuccess, handleError('Error get Point'));
        }

        /**
        * recupère un indice final
        */
        function postPoint(lati, Dlng){
          return $http.post(API_END_POINT + '/parties/points', {"lat": lati, "lng": Dlng}).then(handleSuccess, handleError('Error post Point'));
        }

        /**
        * récupère score
        */
        function postDestination(lati, Dlng){
          return $http.post(API_END_POINT + '/parties/destination', {"lat": lati, "lng": Dlng}).then(handleSuccess, handleError('Error post Destination'));

        }

        // private functions

        function handleSuccess(res) {
            return { success: true, message: res.data, headers_content: res.headers()['Authorization'] };
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
