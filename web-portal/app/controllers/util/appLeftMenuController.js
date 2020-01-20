
'use strict';

define(['app'], function (app) {
    
	 var appLeftMenuController = function ($rootScope, $scope, navigationService, configurationService, 
		localStorageService, constantService, authorizationService) {
		
		
		// $scope.clickChildItem = function(childItem){
		// 	$("#"+childItem.id).parent().find('li').each(function () {
		// 	    $(this).removeClass('active');
		// 	});
		// 	$("#"+childItem.id).removeClass('active').addClass('active');
		// 	navigationService.menuNavigation(childItem.url);
		// };
		
		// $scope.clickTopItem = function(item){
		// 	var isActive = $("#"+item.id).hasClass('active');
		// 	if(item.children.length > 0) {
		// 		if (isActive) {
	    //             $("#"+item.id).removeClass('active');
	    //             $("#"+item.id).children('a').children("i.fa-angle-down").first().removeClass("fa-angle-down").addClass("fa-angle-left");
	    //             $("#"+item.id).children('ul.treeview-menu').slideUp("slow","swing");
	    //         } else {
	    //             $("#"+item.id).addClass('active');
	    //             $("#"+item.id).children('a').children("i.fa-angle-left").first().removeClass("fa-angle-left").addClass("fa-angle-down");
	    //             $("#"+item.id).children('ul.treeview-menu').slideDown("slow","swing");
	    //         }
		// 	} else {
		// 		angular.forEach($scope.menu, function(itm, index) {
		// 			if(itm.children.length == 0) {
		// 				$("#"+itm.id).removeClass('active');
		// 			}
	    //         });
		// 		$("#"+item.id).addClass('active');
		// 		navigationService.menuNavigation(item.url);
		// 	}
		// };
		
		var clearMenu = function(){
			angular.forEach($scope.menu, function(value, key) {
				if(!angular.isNull(value.children) && value.children.length > 0){
					angular.forEach(value.children, function(childValue, key) {
						if(!angular.isNull(childValue.children) && childValue.children.length == 0){
							childValue.active = false;
						}
					});
				} else {
					value.active = false;
				}
			});
		};
		
		var selectChildMenu = function(url){
			angular.forEach($scope.menu, function(value, key) {
				if(!angular.isNull(value.children) && value.children.length > 0){
					angular.forEach(value.children, function(childValue, key) {
						if(!angular.isNull(childValue.children) && childValue.children.length == 0 && childValue.url == url){
							childValue.active = true;
						}
					});
				} else {
					if(value.url == url){
						value.active = true;
					}
				}
			});
		};
		
		var selectTopMenu = function(url){
			angular.forEach($scope.menu, function(value, key) {
				if(!angular.isNull(value.children) && value.children.length == 0 && value.url == url){
					value.active = true;
				}
			});
		};
		
		$scope.clickChildItem = function(array, item, index){
        	clearMenu();
        	selectChildMenu(item.url);
        	authorizationService.setSelectedMenu(item.url);
			navigationService.menuNavigation(item.url);
		};
		
		$scope.clickTopItem = function(item){
			var isActive = $("#"+item.id).hasClass('active');
			if(!angular.isNull(item.children) && item.children.length > 0) {
				$("#"+item.id).removeClass('active');
	            $("#"+item.id).children('a').children("i.fa-angle-down").first().removeClass("fa-angle-down").addClass("fa-angle-left");
	            $("#"+item.id).children('ul.treeview-menu').slideUp("slow","swing");
				if (isActive) {
					$("#"+item.id).removeClass('active');
		            $("#"+item.id).children('a').children("i.fa-angle-down").first().removeClass("fa-angle-down").addClass("fa-angle-left");
		            $("#"+item.id).children('ul.treeview-menu').slideUp("slow","swing");
				} else {
					$("#"+item.id).addClass('active');
		            $("#"+item.id).children('a').children("i.fa-angle-left").first().removeClass("fa-angle-left").addClass("fa-angle-down");
		            $("#"+item.id).children('ul.treeview-menu').slideDown("slow","swing");
				}
			} else {
	        	clearMenu();
	        	selectTopMenu(item.url);
				navigationService.menuNavigation(item.url);
			}
		};
		
		
		var init = function () {
			$scope.userInfo = authorizationService.getUserInfo();
        	$scope.menu = authorizationService.getMenu();
	    }; 
	    
	    
	    init();
		 
	 };    
	 
	 app.controller('appLeftMenuController', ['$rootScope', '$scope', 'navigationService', 'configurationService', 
     'localStorageService','constantService', 'authorizationService', appLeftMenuController]);
	
});

