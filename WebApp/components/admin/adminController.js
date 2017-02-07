(function () {
    'use strict';

    angular
        .module('app')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['$rootScope'];


    function AdminController($rootScope) {
        var vm = this;


        vm.addPoint = (function addPoint(){



          console.log(vm.point_longitude);

        });

        vm.addDestination = (function addDestination() {

        })();

    }

})();
