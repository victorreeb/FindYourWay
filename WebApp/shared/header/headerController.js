(function () {
    'use strict';

    angular
        .module('app')
        .controller('headerController', headerController);

    headerController.$inject = ['$window','AuthenticationService', '$location','$cookies', '$scope' , 'UserService' , '$rootScope', 'FlashService'];
    function headerController($window,AuthenticationService, $location, $cookies,$scope,UserService,$rootScope, FlashService ) {


   // $scope.msg = true;

    // alert($rootScope.globals);
    setTimeout(function () {
        $scope.$apply(function () {
            $scope.msg = ( AuthenticationService.isLogged() );
        });
    }, 10);

   

        $scope.currentUser = function () {
           return AuthenticationService.getUserInfo() ? AuthenticationService.getUserInfo() : "";
        }

      $scope.isActive = function(route) {
        return route === $location.path();
    }



        $scope.logout = function () {
               // UserService.SignOutUser()
               // .then(function (response) {
                    AuthenticationService.ClearCredentials();
                   // $location.path('/');
                    window.location.replace('#!/login');
                    
                //});

            };

        $scope.isLogged =  function(){

            return AuthenticationService.isLogged(); 
        };






    }

})();
