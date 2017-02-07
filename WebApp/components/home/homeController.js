(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope'];

    function HomeController(UserService, $rootScope) {
        var vm = this;
        vm.map = '';

        function initMap(){
          vm.map = L.map('map').setView([48.866, 2.333], 13);
        };

    }

})();
