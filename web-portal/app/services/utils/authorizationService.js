
'use strict';

define(['app', 'services/utils/configurationService', 'services/utils/navigationService',
        'services/utils/languageService', 'services/utils/localStorageService', 'services/utils/constantService'], 
        function (app) {

    var authorizationService = function ($location, $rootScope, $route, $window, $http, $cookieStore,
    		configurationService, navigationService, languageService, localStorageService, constantService, apiService, growl) {
    	
    	var userInfo;

    	this.getUserInfoFromCookies = function (){
			var userInfoCookies = localStorageService.getValue(constantService.secondaryInfoCookieStoreKey);
			if(userInfoCookies === undefined || userInfoCookies === null){
				userInfoCookies = localStorageService.getValue(constantService.userInfoCookieStoreKey);
			}
    		return userInfoCookies
		};

        this.getUserInfo = function () {
        	userInfo = this.getUserInfoFromCookies();
        	if(userInfo == undefined || userInfo == null){
        		return null;
        	}
        	var user = {};
        	user.userName = userInfo.userName;
        	user.id = userInfo.id;
        	user.role = userInfo.role;
        	user.fullName = userInfo.fullName;
        	user.contact = userInfo.contact;
        	user.imagePath = userInfo.imagePath;
        	user.status = userInfo.status;
        	user.role= userInfo.role;
        	user.sessionId = userInfo.sessionId;
        	user.authToken = userInfo.authToken;
            return user;
        };

        this.getCampaignId  = function () {
			return localStorageService.getValue(constantService.campaignKey);
		};

		this.setCampaignId = function (campaignId) {
			localStorageService.setValue(constantService.campaignKey, campaignId);
		};

        this.getMenu = function () {
        	userInfo = this.getUserInfoFromCookies();
        	var webMenu;
        	var menu = $.parseJSON(userInfo.menuJSON);
        	
        	if(menu.children !== undefined){
    			for(var i=0; i< menu.children.length; i++){
    				if(menu.children[i].id === "webMenu"){
    					webMenu = menu.children[i].children;
    				}
    			}
			}
        	
        	var newMenu = [];
        	var child = [];
            angular.forEach(webMenu, function(itm, index) {
            	if(itm.enable === true){
            		if(itm.children.length>0){
            			var newArrayForChild = [];
            			angular.forEach(itm.children, function(cld, index){
                			if(cld.enable === true){
                				newArrayForChild.push(cld);
                			}
                        });
            			itm.children = newArrayForChild;
            		}
            		newMenu.push(itm);
            	}
            });
            return newMenu;
        };

        $rootScope.isLoggedIn = function () {
            return ($rootScope.loginInfo != null);
        };
	   
		this.getSelectedMenu = function () {
        	return localStorageService.getValue(constantService.selectMenuInfoCookieStoreKey);
        };

        this.setSelectedMenu = function (url) {
			localStorageService.setValue(constantService.selectMenuInfoCookieStoreKey, url);
		};
		
       this.signOut = function () {
    		delete $http.defaults.headers.common['X-Auth-Token'];
            localStorageService.setValue(constantService.userInfoCookieStoreKey, null);
            localStorageService.setValue(constantService.secondaryInfoCookieStoreKey, null);
            $location.path('/');
        };
        
        this.authorizeLeftMenu = function (leftMenuId) {
        	var menuJson = localStorageService.getData(configurationService.loginMetaData).data.roleBean.menuJSON;
            for (var topMenuIndex = 0; topMenuIndex < menuJson.length; topMenuIndex++) {
                var leftMenuList = menuJson[topMenuIndex].leftmenuids;
                for (var leftMenuIndex = 0; leftMenuIndex < leftMenuList.length; leftMenuIndex++) {
                    if (leftMenuId === leftMenuList[leftMenuIndex]) {
                        return true;
                    }
                }
            }
            return false;
        };

    };
    
    app.service('authorizationService', ['$location', '$rootScope', '$route', '$window', '$http', '$cookieStore', 
          'configurationService', 'navigationService', 'languageService', 'localStorageService', 'constantService', 'apiService', 'growl',
          authorizationService]);
});
