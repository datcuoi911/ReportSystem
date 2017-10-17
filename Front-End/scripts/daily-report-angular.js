var app = angular.module('my-daily-report', []);
app.controller('dailyReportCtrl', function($scope, $http) {
	$http.get("json/daily-report.json").then(function (response) {
		$scope.myData = response.data.records;
	});
	
    $scope.viewChart = function(chartType, project) {
		location.href = 'chart.html#!/?chart=' + chartType + '&project=' + project;
    };	
});