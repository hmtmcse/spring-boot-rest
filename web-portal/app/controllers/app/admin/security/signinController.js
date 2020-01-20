
'use strict';

define(['app'], function (app) {
	
	var signinController = function ( $scope, $routeParams, signInService, navigationService, localStorageService, 
		configurationService, constantService) {

		$scope.login = { email : 'mahfuzcmt@gmail.com', password : '!Mahfuz', msg : 'Df1000' };

		$scope.email ;
		$scope.success = false;
		$scope.part1 = true;
		$scope.obj = {};


		$scope.signIn = function () {
			var userInfo = {
				menuJSON: "{\"children\":[{\"id\":\"webMenu\",\"url\":\"webMenu\",\"text\":\"Web Menu\",\"class\":\"fa fa-dashboard\",\"enable\":true,\"children\":[{\"id\":\"dashboard\",\"url\":\"dashboard\",\"text\":\"Dashboard\",\"class\":\"fa fa-dashboard\",\"enable\":true,\"children\":[]},{\"id\":\"departments\",\"url\":\"departments\",\"text\":\"Department\",\"class\":\"fa fa-building-o\",\"enable\":true,\"children\":[]},{\"id\":\"employees\",\"url\":\"employees\",\"text\":\"Employees\",\"class\":\"fa fa-user-plus\",\"enable\":true,\"children\":[]}]}]}",
				role: "Admin",
				fullName: "Best Sotabdi Transport Ltd. A/C",
				userName: "admin",
				contact: "01558305350",
				authToken: "VNiaqWJVMRnZOQ0gZzT1AvqOi8cpbiHb",
				selectedLeftMenu: "dashboard",
			};


			localStorageService.setValue(constantService.userInfoCookieStoreKey, userInfo);
			navigationService.menuNavigation("dashboard");
		};

	 	var init = function () {
	 		$(".right-side").addClass("strech");
			$('.left-side').addClass("collapse-left");
	 	};

	 	init();
		 
 	};
 	
    app.register.controller('signinController', ['$scope', '$routeParams', 'signInService', 'navigationService', 
	'localStorageService', 'configurationService','constantService',
    signinController]);
   
	
});














