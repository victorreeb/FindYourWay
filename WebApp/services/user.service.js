(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http','$rootScope'];
    function UserService($http,$rootScope) {


        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.GetUser = GetUser;
        service.SignOutUser = SignOutUser;
        service.GetUserInfo = GetUserInfo;

        return service;

        function GetAll() {
            return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get('/api/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            return $http.post('http://localhost:8080/restsandwich/api/utilisateur/', user).then(handleSuccess, handleError('Error creating user'));
        }

        function GetUser(user) {
            return $http.post('http://localhost:8080/restsandwich/api/utilisateur/login', user).then(handleSuccess, handleError('Error Singing in user'));
        }

         function GetUserInfo(userId) {
            return $http.get('http://coop.api.netlor.fr/api/members/' + userId  + '/signedin?token=' + myToken).then(handleSuccess, handleError('Error getting user data'));
        }


        function SignOutUser() {
            return $http.delete('http://coop.api.netlor.fr/api/members/signout?token=' + myToken ).then(handleSuccess, handleError('Error Singin Out user'));
        }

        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
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
