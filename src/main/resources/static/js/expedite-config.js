var app = angular.module('myApp', ['ngRoute','ui.bootstrap']);

app.config(function($routeProvider) {
  $routeProvider
  .when('/', {
    templateUrl : 'pages/Configuration.html',
  })
  .otherwise({redirectTo: '/'});
});