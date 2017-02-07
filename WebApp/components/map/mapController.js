(function () {
    'use strict';

    angular
        .module('app')
        .controller('MapController', MapController);

    MapController.$inject = ['$rootScope'];

    function MapController($rootScope) {
        var vm = this;

        vm.marqueurs = [];
        vm.appellations = [];
        vm.indices = [];

        vm.initMap = (function initMap(){
          vm.map = L.map('mapid').setView([48.866, 2.333], 5);
          L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png?{foo}', {foo: 'bar'}).addTo(vm.map);

        })();

        vm.refreshAppellation = (function refreshAppellation(){

        })();

        vm.refreshIndice = (function refreshIndice(){

        })();

        vm.refreshScore = (function refreshScore(){

        })();

        vm.clickMarqueur = (function clickMarqueur(){

        })();

        vm.printPath = (function printPath(){

        })();

    }

})();
