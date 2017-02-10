(function () {
    'use strict';

    angular
        .module('app')
        .factory('TokenService', TokenService);

    TokenService.$inject = ['$http', '$rootScope'];
    function TokenService($http, $rootScope) {

        var service = {};

        service.addHeader = addHeader;
        service.setToken = setToken;
        service.getToken = getToken;
        service.deleteToken = deleteToken;

        return service;

        this.token = '';

        /**
        * ajoute le token au header
        */
        function addHeader(ptoken){
          $http.defaults.headers.common['Authorization'] = ptoken;
          console.log($http.defaults.headers.common['Authorization']);
        }

        function setToken(t) {
          if (localStorage.getItem('token') === null) {
            localStorage.setItem('token', t);
          } else {
            this.token = localStorage.getItem('token');
          }
        }

        function getToken() {
            return localStorage.getItem('token');
        }

        function deleteToken() {
            if (localStorage.getItem('token') !== null)
                localStorage.removeItem('token');
        }
    }

})();
