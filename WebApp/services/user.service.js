(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http','$rootScope','API_END_POINT'];
    function UserService($http,$rootScope,API_END_POINT) {


        var service = {};


        service.Create = Create;
        service.GetUser = GetUser;


        return service;


        function Create(user) {
            return $http.post( API_END_POINT + '/utilisateur', user).then(handleSuccess, handleError('Error creating user'));
        }

        function GetUser(user) {
            return $http.post(API_END_POINT + '/utilisateur/login', user).then(handleSuccess, handleError('Error Singing in user'));
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
