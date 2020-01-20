
'use strict';

define(['app'], function (app) {
	
	var appHeaderController = function ($rootScope, $scope, $window, authorizationService, saveDataService, constantService, 
			signInService, navigationService, localStorageService ) {
		
	    $scope.menuToggle = function () {
        	if ($window.innerWidth <= 992) {
                $('.row-offcanvas').toggleClass('active', 500);
                $('.left-side').removeClass("collapse-left");
                $(".right-side").removeClass("strech");
                $('.row-offcanvas').toggleClass("relative", 500);
            } else {
                $(".right-side").toggleClass("strech", 500);
                $('.left-side').toggleClass("collapse-left", 500);
            }
        };
        
        $scope.userToggle = function () {
        	$("#userToggle").addClass('open', 500);
        	$("#userToggle").toggleClass('open', 500);
        };
        
        $scope.gotoChangePassword = function () {
        	navigationService.menuNavigation('changePassword');
        };
        
        $scope.gotoProfile = function () {
			if($scope.userInfo.role == 'tutor'){
				navigationService.menuNavigation('tutorprofile');
			}        	
        };
        
		$scope.logout = function () {
			authorizationService.signOut();
		};

        var init = function () {
        	$scope.userInfo = authorizationService.getUserInfo();
            var secondaryUserInfo = localStorageService.getValue(constantService.secondaryInfoCookieStoreKey);
            $scope.isSecondaryUser = false;
            if(secondaryUserInfo){
                $scope.isSecondaryUser = true;
                var primaryUser = localStorageService.getValue(constantService.userInfoCookieStoreKey);
                $scope.primaryUser = primaryUser.fullName+" ("+primaryUser.role+")";
            }
	    }; 
	    
	    init();
		 
	};
    
	app.controller('appHeaderController', ['$rootScope', '$scope', '$window', 'authorizationService', 'apiService', 'constantService',
    'signInService','navigationService', 'localStorageService', appHeaderController]);
	
});














