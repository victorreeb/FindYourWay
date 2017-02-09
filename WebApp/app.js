(function () {
  //  'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies', 'ngSanitize'])
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

            .when('/admin', {
                controller: 'AdminController',
                templateUrl: 'components/admin/adminView.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/play' });
    }
})();
