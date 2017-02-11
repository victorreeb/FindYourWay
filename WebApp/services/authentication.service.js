(function () {
    'use strict';

    angular
        .module('app')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$cookies', '$rootScope', '$timeout', 'UserService'];
    function AuthenticationService($http, $cookies, $rootScope, $timeout, UserService) {
        var service = {};


        


        service.Login = Login;
        service.Logout = Logout;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;
        service.getUserInfo = getUserInfo;
        service.isLogged = isLogged;


        return service;


        function Logout()
        {
            UserService.SignOutUser();
            $rootScope.globals = {};
            $cookies.remove('globals');
        }


        function Login(user, callback) {

           $timeout(function () {
                var res;
                UserService.GetUser(user)
                    .then(function (response) {

                    if (response.success) {

                        res = { success: true , message : response.message};
                    } else {
                        res = { success: false, message: 'Username or password is incorrect' };
                    }
                        callback(res);
                    });
            }, 500);

       }


        function SetCredentials(email,token) {

            //var authdata = Base64.encode(username + ':' + password);

            $rootScope.globals = {
                currentUser: {
                    email: email,
                    token: token
                }
            };


            // store user details in globals cookie that keeps user logged in for 1 week (or until they logout)
            var cookieExp = new Date();
            cookieExp.setDate(cookieExp.getDate() + 7);
            $cookies.putObject('globals', $rootScope.globals, { expires: cookieExp });
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookies.remove('globals');
          //  $http.defaults.headers.common.Authorization = 'Basic';
        }


        function isLogged()
        {
            if ($cookies.get('globals')) {
               return true ;
            }
            return  false;

        }

        function getUserInfo()
        {

           // $rootScope.globals = $cookies.get('globals') || {};
            if ($cookies.get('globals')) {
               return $rootScope.globals.currentUser.email ;
            }
            return  "";
        }


    };

})();
