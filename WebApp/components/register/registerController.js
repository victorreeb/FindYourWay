(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['$scope','UserService', '$location', '$rootScope', 'FlashService'];
    function RegisterController($scope,UserService, $location, $rootScope, FlashService) {
       
        var vm = this;

        vm.register = register;

        function register() {
            vm.dataLoading = true;
            UserService.Create(vm.user)
                .then(function (response) {

                    if (response.success) {
                        FlashService.Success('Registration successful',true);
                        vm.dataLoading = false;
                        $location.path('/');
                    } else {
                        FlashService.Error(response.message);
                        //$rootScope.$broadcast(AUTH_EVENTS.loginSuccess)
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();
