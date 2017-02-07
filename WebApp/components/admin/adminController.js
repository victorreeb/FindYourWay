(function () {
    'use strict';

    angular
        .module('app')
        .controller('AdminController', AdminController);

    HomeController.$inject = ['UserService', '$rootScope'];

    function AdminController(UserService, $rootScope) {
        var vm = this;
        vm.map = '';



    }

})();
