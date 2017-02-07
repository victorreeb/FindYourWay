(function () {
    'use strict';

    angular
        .module('app')
        .factory('AdminService', AdminService);

    UserService.$inject = ['$http'];
    function AdminService($http) {
        var service = {};

        service.CreerPoint = CreerPoint;
        service.CreerDestination = CreerDestination;

        return service;

        function CreerPoint(lat, lng, appellation) {
            return $http.post('/api/points', {latitude: lat, longitude: lng, appellation: appellation}).then(handleSuccess, handleError('Error creating point'));
        }

        function CreerDestination(lat, lng, desc, lieu, indices) {
            return $http.put('/api/destination', {latitude: lat, longitude: lng, description: desc, lieu: lieu, indices: indices}).then(handleSuccess, handleError('Error creating destination'));
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
