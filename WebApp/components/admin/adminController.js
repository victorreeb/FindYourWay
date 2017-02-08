(function () {
    'use strict';

    angular
        .module('app')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['$rootScope', 'AdminService'];


    function AdminController($rootScope, AdminService) {
        var vm = this;


        vm.addPoint = (function addPoint(){
          vm.dataLoading = true;


          AdminService.CreerPoint(vm.point_latitude, vm.point_longitude, vm.point_appellation).then(function(response){
            if (response.success) {
                console.log(response.message);
                $location.path('/admin');
            } else {

              vm.dataLoading = false;
            }
          });
        });


        vm.addDestination = (function addDestination() {

          vm.dataLoading = true;

          var indices = {indice1: vm.indice1, indice2: vm.indice2, indice3: vm.indice3, indice4: vm.indice4, indice5: vm.indice5};

          console.log(indices);

          AdminService.CreerDestination(vm.dest_latitude, vm.dest_longitude, vm.dest_description, vm.dest_lieu, indices).then(function(response) {
            if (response.success) {
                console.log(response.message);
                $location.path('/admin');
            } else {
              vm.dataLoading = false;
            }
          console.log(response);
          console.log(vm.dataLoading);
          });
        });

    }

})();
