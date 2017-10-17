
var app = angular.module('my-chart', []);
app.controller('chartCtrl', function($scope, $http, $location) {
	$scope.chart = $location.search().chart;
	if($scope.chart === 'all') {
		drawChartByJson('codesmell');
		drawChartByJson('testcase');
		drawChartByJson('coverage');
		drawChartByJson('techDebt');
		drawChartByJson('vulnerability');
	} else {
		drawChartByJson($scope.chart);
	};

	function drawChartByJson(chart) {
		$http.get("json/" + chart + ".json").then(function (response) {
			$scope.myData = response.data.records;
			drawChart(chart, $scope.myData);
		});	
	}
});

function drawChart(chart, data) {
	var chart = AmCharts.makeChart( "chart-" + chart, {
          "type": "serial",
          "theme": "light",
          "dataProvider": data,
          "valueAxes": [ {
            "gridColor": "#FFFFFF",
            "gridAlpha": 0.2,
            "dashLength": 0
          } ],
          "gridAboveGraphs": true,
          "startDuration": 1,
          "graphs": [ {
            "balloonText": "[[category]]: <b>[[value]]</b>",
            "fillAlphas": 0.8,
            "lineAlpha": 0.2,
            "type": "column",
            "valueField": "value"
          } ],
          "chartCursor": {
            "categoryBalloonEnabled": false,
            "cursorAlpha": 0,
            "zoomable": false
          },
          "categoryField": "date",
          "categoryAxis": {
            "gridPosition": "start",
            "gridAlpha": 0,
            "tickPosition": "start",
            "tickLength": 20
          },
          "export": {
            "enabled": true
          }

     });
}