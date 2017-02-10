(function () {
    'use strict';

    angular
        .module('app')
        .factory('TokenService', TokenService);

    TokenService.$inject = ['$http', '$rootScope'];
    function TokenService($http, $rootScope) {
        var token = {};

        token.addHeader = addHeader;

        return token;

        /**
        * ajoute le token au header
        */
        function addHeader(ptoken){
          $http.defaults.headers.common['Authorization'] = ptoken;
          console.log($http.defaults.headers.common['Authorization']);
        }
    }

})();
