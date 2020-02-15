'use strict';

define(['app'], function (app) {
	
	var employeeController = function ($upload, growl, $routeParams, $scope, _, constantService, $filter, navigationService, authorizationService, localStorageService,
		configurationService, ngProgress, apiService, loadService ) {
		
		var promis, departmentList = [];
		var parentPage = "employees";
		var objectId = null;
		var module = "employee";

		var loadEmployee = function (id) {
			promis = apiService.get(module+'/'+parseInt(id));
			promis.then(function (data) {
				if(data.status !== "success"){
					growl.error("No data found", {ttl: 3000});
					return;
				}
				$scope.obj = data.data;
				$scope.departmentList = angular.copy(departmentList);
			});
		};


		$scope.saveOrUpdate = function (obj) {
			if(obj.id){
				promis = apiService.put(module+'/update/'+parseInt(obj.id), obj);
			}else{
				promis = apiService.post(module+'/add', obj);
			}
			promis.then(function (data) {
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

		var loadDepartments = function () {
			loadService.showDialog();
			promis = apiService.get('department/all');
			promis.then(function (data) {
				loadService.hideDialog();
				if(data.status !== "success"){
					growl.error(data.message, {ttl: 3000});
				}
				departmentList = data.data;
				if(!angular.isNull($routeParams.id)){
					$scope.btnName = "Update";
					objectId = $routeParams.id;
					loadEmployee(objectId);
				}else{
					$scope.obj = {};
					$scope.departmentList = angular.copy(departmentList);
				}
			});
		};

		$scope.uploadFile = function (files) {
			$scope.message = "";
			loadService.showDialog();
			for (var i = 0; i < files.length; i++) {
				var file = files[i];
				$scope.upload = $upload.upload({
					url: apiService.restPath("fileUploader/employee-photo"),
					method: 'POST',
					file: file,
				}).success(function(data) {
					loadService.hideDialog();
					if(data.status == "success"){
						$scope.obj.photo = data.data.path;
					}else {
						growl.error(data.message, {ttl: 3000});
					}
				}).error(function() {
					loadService.hideDialog();
					growl.error("Something went wrong!", {ttl: 3000});
				});
			};
		};

	 	var init = function () {
			ngProgress.start();
			$scope.apiRootPath = apiService.restPath();
			$scope.userInfo = authorizationService.getUserInfo();
			$scope.btnName = "Save";
			loadDepartments();
			ngProgress.complete();
	 	};

	 	init();
	 	
	 };
	 
    app.register.controller('employeeController', ['$upload', 'growl', '$routeParams', '$scope', '_', 'constantService', '$filter', 'navigationService', 'authorizationService',
	'localStorageService', 'configurationService', 'ngProgress', 'apiService', 'loadService',
		employeeController]);
   
	
});

