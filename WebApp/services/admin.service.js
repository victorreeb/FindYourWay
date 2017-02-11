(function () {
    'use strict';

    angular
        .module('app')
        .factory('AdminService', AdminService);

    AdminService.$inject = ['$http', '$rootScope'];
    function AdminService($http, $rootScope) {
        var service = {};

        //Get
        service.GetAllPoint = GetAllPoint;
        service.GetAllDestination = GetAllDestination;

        // Post
        service.CreerPoint = CreerPoint;
        service.CreerDestination = CreerDestination;

        return service;

        function GetAllPoint() {
          return $http.get('http://127.0.0.1:8080/findYourWay/api/privee/points').then(handleSuccess, handleError('Error getting all Points'));
        }

        function GetAllDestination() {
          return $http.get('http://127.0.0.1:8080/findYourWay/api/privee/destinations').then(handleSuccess, handleError('Error getting all Destinations'));
        }

        
        function CreerPoint(lati, lngt, appellations) {
            return $http.post('http://127.0.0.1:8080/findYourWay/api/privee/points', {"lat": lati, "lng": lngt, "appellation": appellations}).then(handleSuccess, handleError('Error creating point'));
        }

        //indices is a array of indices
        function CreerDestination(lati, lngt, desci, dlieu) {
            return $http.post('http://127.0.0.1:8080/findYourWay/api/privee/destinations', {"lat": lati, "lng": lngt, "description": desci, "lieu": dlieu}).then(handleSuccess, handleError('Error creating destination'));
        }


        // private functions

        function handleSuccess(res) {
            return { success: true, message: res.data };
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
