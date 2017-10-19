//api url from backend server
const api_url = "http://localhost:8088/DemoSpringMVCHibernate/api/";

//declare angular app and inject ngRoute to use angular routing
var app = angular.module('sonarqube-report-app', ["ngRoute"]);


// daily report controller 
app.controller('dailyReportCtrl', function($scope, $http) {
	$http.get(api_url + "allprojects").then(function (response) {
		// json get from backend server http://localhost:9999/DemoSpringMVCHibernate/api/allprojects
		$scope.dailyData = response.data;
	});
	
	$scope.updateSonarQube = function() {
		// insert or update sonarqube http://localhost:9999/DemoSpringMVCHibernate/api/updateSonarQube
		$http.post(api_url + "updateSonarQube").then(function (response) {
			if(response) {
				alert("update successfully");
			} else {
				alert("update false");
			}
		});	
	};
});


// chart controller 
app.controller('chartCtrl', function($scope, $http, $routeParams) {
	$scope.chart = $routeParams.chart;
	$scope.project_name = $routeParams.project_name;
	if($scope.chart === 'all') {
		// draw all chart 
		drawChartByJson('code_smell');
		drawChartByJson('test_case');
		drawChartByJson('coverage');
		drawChartByJson('technical_debt');
		drawChartByJson('vulnerability');
	} else {
		// draw one chart 
		drawChartByJson($scope.chart);
	};
	
	// draw chart by json getted from backend server
	function drawChartByJson(chart) {
		// json get from backend server http://localhost:9999/DemoSpringMVCHibernate/api/{{chart}}/{{projectid}}
		$http.get(api_url + chart + "/" + $routeParams.projectid).then(function (response) {
			drawChart(chart, response.data);
		});	
	}
});


// angular routing config
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "angular/template/daily-report.htm"
    })
    .when("/chart/:chart/:projectid/:project_name", {
        templateUrl : "angular/template/chart.htm"
	// 404 handle
    }).otherwise({
        templateUrl : "angular/template/daily-report.htm"
    });
});


// draw chart function
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