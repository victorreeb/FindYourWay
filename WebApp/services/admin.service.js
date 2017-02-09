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
          return $http.get('/findyourway/api/privee/points').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetAllDestination() {
          return $http.get('/findyourway/api/privee/destinations').then(handleSuccess, handleError('Error getting all users'));
        }

        function CreerPoint(lat, lng, appellation) {
            return $http.post('/findyourway/api/privee/points', {latitude: lat, longitude: lng, appellation: appellation}).then(handleSuccess, handleError('Error creating point'));
        }

        //indices is a array of indices
        function CreerDestination(lat, lng, desc, lieu, indices) {
            return $http.post('/findyourway/api/privee/destinations', {latitude: lat, longitude: lng, description: desc, lieu: lieu, indices: indices}).then(handleSuccess, handleError('Error creating destination'));
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
