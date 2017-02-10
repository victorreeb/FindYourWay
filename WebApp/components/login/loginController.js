(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$window','$rootScope','$location', 'AuthenticationService', 'FlashService','$route'];
    function LoginController($window,$rootScope,$location, AuthenticationService, FlashService,$route) {
        var vm = this;

        vm.login = login;

        (function initController() {
        })();

      function login() {
            
            AuthenticationService.Login(vm.user, function (response) {
                
                if (response.success) {
                    AuthenticationService.SetCredentials(response.message.email,response.message.token);
                    console.log(response.message);
                    FlashService.Success('Bienvenue ' + response.message.email , true);
                    $location.path('/');
                    $route.reload();
                    //$location.path('/');
                } else {

                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
