(function () {
  //  'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        // .constant('API_END_POINT','http://localhost/api')
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/play', {
                controller: 'HomeController',
                templateUrl: 'components/home/homeView.html',
                controllerAs: 'vm'
            })
            .otherwise({ redirectTo: '/play' });
    }

    run.$inject = ['$rootScope', '$location', '$http','$cookies'];
    function run($rootScope, $location, $http,$cookies) {

        // var groupToken = 'Token token="4bd9188773154f5baac05ed97b082e3c"' ;

        // $http.defaults.headers.common['Authorization'] = groupToken;
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};

          if (!$rootScope.globals.currentUser) {

             $location.path('/');

         //   $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }


        // $rootScope.$on('$locationChangeStart', function (event, next, current) {
        //     // redirect to login page if not logged in and trying to access a restricted page
        //     // var restrictedPage = $.inArray($location.path(), ['/login', '/register','/offre']) === -1;
        //     var loggedIn = $rootScope.globals.currentUser;
        //    // var loggedIn = true;
        //     if (restrictedPage && !loggedIn) {
        //         $location.path('/login');
        //     }
        // });
    }

})();
