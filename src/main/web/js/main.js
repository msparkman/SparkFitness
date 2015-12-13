var app = angular.module('sparkFitness', []);
app.controller('getLastRoutine', function($scope, $http) {
	$http.get("http://localhost:8080/getLastRoutine/1")
    .then(function(response) {
    	window.alert("HEY!");
        //$scope.routine = response;    
	});
});