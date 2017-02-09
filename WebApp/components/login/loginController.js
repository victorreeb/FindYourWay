(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$window','$rootScope','$location', 'AuthenticationService', 'FlashService'];
    function LoginController($window,$rootScope,$location, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
        })();

      function login() {
            
            vm.dataLoading = true;
            AuthenticationService.Login(vm.user, function (response) {
                
                if (response.success) {
                    AuthenticationService.SetCredentials(response.message.fullname,response.message.token,response.message._id);
                    FlashService.Success('Bienvenue ' + response.message.fullname , true);
                    $location.path('/');
                } else {

                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
