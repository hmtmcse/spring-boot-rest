'use strict';

define(['app'], function (app) {
	
	var departmentController = function (growl, $routeParams, $scope, _, constantService, $filter, navigationService, authorizationService, localStorageService,
		configurationService, ngProgress, apiService, loadService) {
		
		var promis, roleList = [];
		var parentPage = "departments";
		var objectId = null;
		var module = "department";

		var statusList = [
			{key: true, displayText: "Active"},
			{key: false, displayText: "Inactive"}
		];

		var loadDepartment = function (id) {
			loadService.showDialog();
			promis = apiService.get(module+'/'+parseInt(id));
			promis.then(function (data) {
				loadService.hideDialog();
				if(data.status !== "success"){
					growl.error("No data found", {ttl: 3000});
					return;
				}
				$scope.obj = data.data;
				$scope.statusList = angular.copy(statusList);
				$scope.roleList = angular.copy(roleList);
			});
		};

		$scope.saveOrUpdate = function (obj) {
			if(obj.id){
				promis = apiService.put(module+'/update/'+parseInt(obj.id), obj);
			}else{
				promis = apiService.post(module+'/add', obj);
			}
			loadService.showDialog();
			promis.then(function (data) {
				loadService.hideDialog();
				if(data.status !== "success"){
					growl.error(data.message, {ttl: 3000});
					return;
				}
				growl.success(data.message, {ttl: 3000});
				$scope.obj = {};
				if(obj.id){
					$scope.backToPrevious();
				}
			});
		};

		$scope.backToPrevious = function(){
			navigationService.menuNavigation(parentPage);
		};

	 	var init = function () {
			ngProgress.start();
			$scope.userInfo = authorizationService.getUserInfo();
			$scope.btnName = "Save";
			$scope.obj = { active : true};
			if(!angular.isNull($routeParams.id)){
				$scope.btnName = "Update";
				objectId = $routeParams.id;
				loadDepartment(objectId);
			}else{
				$scope.statusList = angular.copy(statusList);
				$scope.roleList = angular.copy(roleList);
			}
			ngProgress.complete();
	 	};

	 	init();
	 	
	 };
	 
    app.register.controller('departmentController', ['growl', '$routeParams', '$scope', '_', 'constantService', '$filter', 'navigationService', 'authorizationService',
	'localStorageService', 'configurationService', 'ngProgress', 'apiService', 'loadService',
		departmentController]);
   
	
});

