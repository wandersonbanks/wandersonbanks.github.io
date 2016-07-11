var app = angular.module('movieSearch', ['ngRoute']); 
var movieTitle = "";
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		controller: 'MainCtrl',
		templateUrl: 'templates/home.html',
	})
	$routeProvider.when('/movie/:movieId', {
		controller: 'MoviesCtrl',
		templateUrl: 'templates/moviesPage.html',
	})
});

app.controller('MainCtrl', function($scope, $http) {

	$scope.search = function(title)
	{
		movieTitle = title;
		console.log(movieTitle);

		$http({
			url: "http://www.omdbapi.com/?",
			method: "GET",
			params: {
			s: movieTitle
			}
		}).then(function(response) {
			$scope.moviesArray = response.data.Search;
			//console.log($scope.moviesArray);
		})
	};
});

app.controller('MoviesCtrl', function($scope, $http, $routeParams) {
	$http({
		url: "http://www.omdbapi.com/?",
		method: "GET",
		params: {
			i: $routeParams.movieId
			}
	}).then(function(response) {
		//console.log(response);
		$scope.movie = response.data;
		$http({
		url: "http://api.giphy.com/v1/gifs/search?q=funny+cat&api_key=dc6zaTOxFJmzC",
		method: "GET",
		params: {
			q: movieTitle
		}
		}).then(function(response) {
		console.log(response);
		$scope.gifs = response.data.data;
	})
})

});
