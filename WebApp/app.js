(function () {
  //  'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .constant('API_END_POINT','http://localhost/FindYourWay/WebApp');

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/play', {
                controller: 'HomeController',
                templateUrl: 'components/home/homeView.html',
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
