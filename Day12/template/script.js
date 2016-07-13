var app = angular.module('iXChommiesApp', ['ngRoute']); 

app.config(function($routeProvider) {
	$routeProvider.when('/', {
		controller: 'FeedCtrl',
		templateUrl: 'template/home.html',
	})
	$routeProvider.when('/props.html', {
		controller: 'PropCtrl',
		templateUrl: 'template/props.html',
	})
});


app.controller('FeedCtrl', function($scope, $http,$routeParams) 
{
	$scope.datesArray = [];
	$http({
		url: "http://ixchommies.herokuapp.com/props/",
		method: "GET",
		params: {
				/* text:"good news everyday", */
			token:"67d65fbe73ef5ad07cb0b388ea23bd7c",
		}
	}).then(function(response) 
	{
		//console.log(response);
		$scope.props = response.data;
		for(var i=0; i<$scope.props.length;i++)
		{
			$scope.datesArray.push(dateConverter($scope.props[i].created_at));
			$scope.props[i].positivity_score = $scope.props[i].positivity_score.toFixed(2);
		}
	})

	$http({
		url: "http://ixchommies.herokuapp.com/brus/",
		method: "GET",
		params: {
				/* text:"good news everyday", */
			token:"67d65fbe73ef5ad07cb0b388ea23bd7c",
		}
	}).then(function(response) 
	{
		//console.log(response);
		$scope.brus = response.data;
	})

	$scope.sendProps = function()
	{
		console.log("New props: "+ $scope.newPropsValue);
		console.log("Selected bru: "+$scope.selectedBru);
	
		$http({
			url: "http://ixchommies.herokuapp.com/props",
			method: "POST",
			params: {
					/* text:"good news everyday", */
				token:"67d65fbe73ef5ad07cb0b388ea23bd7c",
			},
			data: {
				for: $scope.selectedBru,
				props: $scope.newPropsValue
			}
		}).then(function(response) 
		{
			$scope.props.push(response);
		}).catch(function(response)
		{
			$scope.message=response.data.message;
			alert($scope.message);
		}).finally(function(response)
		{

		});
	};

	var dateConverter = function timeConverter(UNIX_timestamp)
	{
  		var a = new Date(UNIX_timestamp);
  		var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
  		var year = Math.round(a.getFullYear());
  		var month = months[a.getMonth()];
  		var date = a.getDate();
  		var hour = a.getHours();
  		var min = a.getMinutes();
  		var sec = a.getSeconds();
  		var amm = " AM";
  		if(hour>=12)
  		{
  			amm = " PM";
  		}
  		var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min+amm;
  		return time;
	}
}); 

app.controller('PropCtrl', function($scope, $http,$routeParams) 
{
	console.log("Executing my props");
	$scope.myProps = [];
	$scope.datesArray = [];
	$http({
		url: "http://ixchommies.herokuapp.com/props/",
		method: "GET",
		params: {
				/* text:"good news everyday", */
			token:"67d65fbe73ef5ad07cb0b388ea23bd7c",
		}
	}).then(function(response) 
	{
		$scope.props = response.data;
		console.log($scope.props);
		for(var i=0; i<$scope.props.length;i++)
		{
			if($scope.props[i].receiver.first_name=="Wandile")
			{
				console.log("found something");
				$scope.props[i].positivity_score = $scope.props[i].positivity_score.toFixed(2);
				$scope.myProps.push($scope.props[i]);
				$scope.datesArray.push(dateConverter($scope.props[i].created_at));
			}
		}
	})

	var dateConverter = function timeConverter(UNIX_timestamp)
	{
  		var a = new Date(UNIX_timestamp);
  		var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
  		var year = Math.round(a.getFullYear());
  		var month = months[a.getMonth()];
  		var date = a.getDate();
  		var hour = a.getHours();
  		var min = a.getMinutes();
  		var sec = a.getSeconds();
  		var amm = " AM";
  		if(hour>=12)
  		{
  			amm = " PM";
  		}
  		var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min+amm;
  		return time;
	}
}); 