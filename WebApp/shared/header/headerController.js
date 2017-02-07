(function () {
    'use strict';

    angular
        .module('app')
        .controller('headerController', headerController);

    headerController.$inject = ['UserService' , 'AuthenticationService', '$location', '$rootScope', 'FlashService','$scope'];
    function headerController(AuthenticationService, $location, $rootScope, FlashService,$scope,UserService) {


        //var loggedIn = $rootScope.globals.currentUser || null;
       // alert(loggedIn);

       //$scope.fullname = $rootScope.globals.currentUser.username || "";
      // $scope.fullname = $rootScope.globals.currentUser.username ? $rootScope.globals.currentUser.username : "";

       // alert($rootScope.globals.currentUser.username);
        $scope.currentUser = function () {
           return $rootScope.globals.currentUser ? $rootScope.globals.currentUser.username : "";
        }

       $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };


          function register() {
            vm.dataLoading = true;
            UserService.Create(vm.user)
                .then(function (response) {

                    if (response.success) {
                        FlashService.Success('Registration successful',true);
                        vm.dataLoading = false;
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }


        $scope.logout = function () {
                UserService.SignOutUser()
                .then(function (response) {

                    alert(response);
                   // AuthenticationService.ClearCredentials();
                    //$location.path('/');

                });
                //AuthenticationService.ClearCredentials();

                //return;
            };

        $scope.isLogged = function(){

            if( $rootScope.globals.currentUser )
                return true ;
            return false;

        };

    }

})();
