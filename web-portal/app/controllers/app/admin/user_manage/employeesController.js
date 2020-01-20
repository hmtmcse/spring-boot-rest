'use strict';

define(['app'], function (app) {

	var employeesController = function (growl, $rootScope, $scope, _, constantService, $filter, navigationService, authorizationService, localStorageService,
										  configurationService, ngProgress, apiService, modalService, loadService) {

		var entryPage = 'employee';
		var module = 'employee';

		var promis;
		$scope.dataList = [];
		$scope.displayedCollection = [];
		$scope.currentPage = 1;
		$scope.pageDataBegin = 0;
		$scope.pageDataEnd = 0;
		$scope.pageDataTotal = 0;
		$scope.maxPaginationSize = 5;
		$scope.itemsPerPage = 2;

		var loadEmployees = function () {
			loadService.showDialog();
			promis = apiService.get(module+'/all');
			promis.then(function (data) {
				loadService.hideDialog();
				if(data.status !== "success"){
					growl.error(data.message, {ttl: 3000});
				}
				$scope.dataList = data.data;
				$scope.rowCollection = $scope.dataList;
				$scope.totalData = $scope.rowCollection.length;
				createWatches($scope.dataList);
			});
		};

		var doPagination = function (filteredResult) {

			$scope.rowCollection = filteredResult;
			$scope.pageDataTotal = filteredResult.length;
			if ($scope.pageDataTotal == 0) {
				$scope.pageDataBegin = 0;
				$scope.pageDataEnd = 0;
			} else {
				$scope.pageDataBegin = (($scope.currentPage - 1) * $scope.itemsPerPage) + 1;
				$scope.pageDataEnd = $scope.pageDataBegin + $scope.itemsPerPage - 1;
			}

			if ($scope.pageDataTotal != 0 && $scope.pageDataEnd > $scope.pageDataTotal) {
				$scope.pageDataEnd = $scope.pageDataTotal
			}

			$scope.pageItemText = constantService.getPageItemText($scope.pageDataBegin, $scope.pageDataEnd,
				$scope.pageDataTotal, "Employees", 'English');
		};

		var createWatches = function (data) {
			$scope.$watch("searchText", function (filterText) {
				$scope.currentPage = 1;
			});

			$scope.$watch('currentPage + itemsPerPage', function () {
				var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
					end = begin + ($scope.itemsPerPage - 0);
				$scope.rowCollection = data.slice(begin, end);
				$scope.pageDataTotal = $scope.totalData;

				if ($scope.pageDataTotal == 0) {
					$scope.pageDataBegin = 0;
					$scope.pageDataEnd = 0;
				} else {
					$scope.pageDataBegin = begin + 1;
					$scope.pageDataEnd = end;
				}
				if ($scope.pageDataTotal != 0 && $scope.pageDataEnd > $scope.pageDataTotal) {
					$scope.pageDataEnd = $scope.pageDataTotal
				}
				$scope.pageItemText = constantService.getPageItemText($scope.pageDataBegin, $scope.pageDataEnd,
					$scope.pageDataTotal, "Employees", "English");
			});
		};

		var doPagination = function(filteredResult){
			$scope.departmentList = filteredResult;
			$scope.pageDataTotal = filteredResult.length;
			if($scope.pageDataTotal === 0){
				$scope.pageDataBegin = 0;
				$scope.pageDataEnd = 0;
			} else {
				$scope.pageDataBegin = (($scope.currentPage - 1) * $scope.itemsPerPage) + 1;
				$scope.pageDataEnd = $scope.pageDataBegin + $scope.itemsPerPage - 1;
			}

			if($scope.pageDataTotal !== 0 && $scope.pageDataEnd > $scope.pageDataTotal) {
				$scope.pageDataEnd = $scope.pageDataTotal
			}

			$scope.pageItemText = constantService.getPageItemText($scope.pageDataBegin, $scope.pageDataEnd,
				$scope.pageDataTotal, "Employees", 'English');
		};

		var createWatches = function (data) {
			$scope.$watch("searchText", function (filterText) {
				$scope.currentPage = 1;
			});

			$scope.$watch('currentPage + itemsPerPage', function () {
				var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
					end = begin + ($scope.itemsPerPage - 0);
				$scope.rowCollection = data.slice(begin, end);
				$scope.pageDataTotal = $scope.totalData;

				if ($scope.pageDataTotal == 0) {
					$scope.pageDataBegin = 0;
					$scope.pageDataEnd = 0;
				} else {
					$scope.pageDataBegin = begin + 1;
					$scope.pageDataEnd = end;
				}
				if ($scope.pageDataTotal != 0 && $scope.pageDataEnd > $scope.pageDataTotal) {
					$scope.pageDataEnd = $scope.pageDataTotal
				}
				$scope.pageItemText = constantService.getPageItemText($scope.pageDataBegin, $scope.pageDataEnd,
					$scope.pageDataTotal, "Employees", "English");
			});
		};

		$scope.add = function(){
			navigationService.menuNavigation(entryPage);
		};
		$scope.editObj = function(id){
			navigationService.showPageWithData(entryPage, id);
		};
		$scope.deleteObj = function (id) {
			var modalOptions = {
				closeButtonText: 'No',
				actionButtonText: 'Yes',
				headerText: ' Confirmation',
				bodyText: ' Are you sure to delete?'
			};
			var modalDefaults = {
				templateUrl: 'app/partials/confirmation.html'
			};
			modalService.showModal(modalDefaults, modalOptions).then(function (result) {
				if(result === 'cancel'){
					return;
				}
				loadService.showDialog();
				promis = apiService.delete(module+'/delete/'+ parseInt(id));
				promis.then(function (data) {
					loadService.hideDialog();
					if(data.status !== "success"){
						growl.error(data.message, {ttl: 3000});
						return;
					}
					growl.success(data.message, {ttl: 3000});
					init ();
				});
			});
		};

		var init = function () {
			ngProgress.start();
			loadEmployees();
			ngProgress.complete();
		};

		init();

	};

	app.register.controller('employeesController', ['growl', '$rootScope', '$scope', '_', 'constantService', '$filter', 'navigationService', 'authorizationService',
		'localStorageService', 'configurationService', 'ngProgress', 'apiService', 'modalService', 'loadService',
		employeesController]);


});

