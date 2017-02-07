(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$rootScope'];

    function HomeController($rootScope) {
        var vm = this;
        vm.initMap = (function initMap(){
          vm.map = L.map('mapid').setView([48.866, 2.333], 5);
          L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png?{foo}', {foo: 'bar'}).addTo(vm.map);

          // L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
          //   maxZoom: 18,
          //   id: 'map.findyourway',
          //   accessToken: 'pk.eyJ1IjoicmVlYjV1IiwiYSI6ImNpeXZqOWo1NTAwMjcycXFhdHZnZ3c5OHYifQ.rNgjQOnrBA0uoasccb5HkA'
          // }).addTo(vm.map);
        })();

    }

})();
