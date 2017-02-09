(function () {
    'use strict';

    angular
        .module('app')
        .controller('headerController', headerController);

    headerController.$inject = ['$window','AuthenticationService', '$location','$cookies', '$scope' , 'UserService' , '$rootScope', 'FlashService'];
    function headerController($window,AuthenticationService, $location, $cookies,$scope,UserService,$rootScope, FlashService) {


  
      function TestCtrl($scope) {
    $scope.loading = true;
    setTimeout(function () {
        $scope.$apply(function(){
            $scope.loading = false;
        });
    }, 1000);
    }

    


    setTimeout(function () {
        $scope.$apply(function () {
            $scope.msg = ( AuthenticationService.isLogged() );
        });
    }, 10);

   

        $scope.currentUser = function () {
           return logged ? AuthenticationService.getUserInfo() : "";
        }

      $scope.isActive = function(route) {
        return route === $location.path();
    }



        $scope.logout = function () {
                UserService.SignOutUser()
                .then(function (response) {
                    AuthenticationService.ClearCredentials();
                    $location.path('/');
                    
                });

            };

        $scope.isLogged =  function(){

          if( !$rootScope.globals.currentUser )
                return true ;
            return false;

        };






    }

})();
