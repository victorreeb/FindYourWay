(function () {
  //  'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        // .constant('API_END_POINT','http://localhost/api')
        ;

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/play', {
                controller: 'MapController',
                templateUrl: 'components/map/mapView.html',
                controllerAs: 'vm'
            })
            .otherwise({ redirectTo: '/play' });
    }

})();
