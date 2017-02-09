(function () {
  //  'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies', 'ngSanitize'])
        .config(config)
<<<<<<< HEAD
        // .constant('API_END_POINT','http://localhost/api')
        ;
=======
        // .constant('API_END_POINT','http://localhost/FindYourWay/WebApp');
>>>>>>> 1955e3ca53bda308c4117139fd12292bcbeffbe9

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/play', {
                controller: 'MapController',
                templateUrl: 'components/map/mapView.html',
                controllerAs: 'vm'
            })
<<<<<<< HEAD
            .otherwise({ redirectTo: '/play' });
    }

=======


            .when('/admin', {
                controller: 'AdminController',
                templateUrl: 'components/admin/adminView.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/play' });
    }



>>>>>>> 1955e3ca53bda308c4117139fd12292bcbeffbe9
})();
