(function () {
  //  'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);
        // .constant('API_END_POINT','http://localhost/FindYourWay/WebApp');

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider

            .when('/', {
                controller: 'HomeController',
                templateUrl: 'components/home/homeView.html',
                controllerAs: 'vm'
            })

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

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'components/login/loginView.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'components/register/registerView.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/admin' });
    }



       run.$inject = ['$rootScope', '$location', '$http','$cookies'];
        function run($rootScope, $location, $http,$cookies) {


        if(  $rootScope[''] = $http.defaults.headers.common['Authorization']  )


      //  $http.defaults.headers.common['Authorization'] = "Bearer  " + ;
       // $http.defaults.headers.common["Access-Control-Allow-Headers"] =  "Origin, X-Requested-With, Content-Type, Accept";
        $http.defaults.headers.common["Access-Control-Allow-Origin"] =  "*";

        $http.defaults.headers.common['Accept'] = "application/json";
        $http.defaults.headers.common['Content-Type'] = "application/json";


        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};


        $rootScope.$on('$locationChangeStart', function (event, next, current) {



            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register','/play']) === -1;
            var loggedIn = $rootScope.globals || {};
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }





})();
