(function () {
    'use strict';

    angular
        .module('app')
        .factory('TokenService', TokenService);

    TokenService.$inject = ['$http', '$rootScope'];
    function TokenService($http, $rootScope) {
<<<<<<< HEAD
        var token = {};

        token.addHeader = addHeader;

        return token;

        /**
        * ajoute le token au header
        */
        function addHeader(ptoken){
          $http.defaults.headers.common['Authorization'] = ptoken;
          console.log($http.defaults.headers.common['Authorization']);
=======
        var service = {};

        service.setToken = setToken;
        service.getToken = getToken;
        ervice.deleteToken = deleteToken;

        return service;

        this.token = '';
        
        this.setToken = function(t) {
          if (localStorage.getItem('token') === null) {
            localStorage.setItem('token', t);
          } else {
            this.token = localStorage.getItem('token');
          }
        }

        this.getToken = function() {
            return localStorage.getItem('token');
        }

        this.deleteToken = function() {
            if (localStorage.getItem('token') !== null)
                localStorage.removeItem('token');
        }


        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
>>>>>>> master
        }
    }

})();
