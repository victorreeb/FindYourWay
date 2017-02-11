(function () {
    'use strict';

    angular
        .module('app')
        .factory('AdminService', AdminService);

    AdminService.$inject = ['$http', '$rootScope','API_END_POINT'];
    function AdminService($http, $rootScope,API_END_POINT) {
        var service = {};

        //Get
        service.GetAllPoint = GetAllPoint;
        service.GetAllDestination = GetAllDestination;

        // Post
        service.CreerPoint = CreerPoint;
        service.CreerDestination = CreerDestination;

        return service;

        function GetAllPoint() {

          return $http.get( API_END_POINT + '/privee/points').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetAllDestination() {
          return $http.get(API_END_POINT + '/privee/destinations').then(handleSuccess, handleError('Error getting all users'));
        }
        
        function CreerPoint(lati, lngt, appellations) {
            return $http.post(API_END_POINT + '/privee/points', {"lat": lati, "lng": lngt, "appellation": appellations}).then(handleSuccess, handleError('Error creating point'));
        }

        //indices is a array of indices
        function CreerDestination(lati, lngt, desci, dlieu) {
            return $http.post(API_END_POINT + '/destinations', {"lat": lati, "lng": lngt, "description": desci, "lieu": dlieu}).then(handleSuccess, handleError('Error creating destination'));
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
