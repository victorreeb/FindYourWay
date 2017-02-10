angular.module("app").directive("ianHeader", function() {
  return {
    restrict: 'E',
    templateUrl: 'shared/header/headerView.html',
     scope: {
     name: '='
   },
    transclude : true,
    controller: 'headerController',

    link: function($scope) {

     $scope.$watch('name', function() {
       // alert($scope.name);
     });
   }


  };
});
