
'use strict';

define(['app'], function (app) {

    var constantService = function ($rootScope, $cookieStore) {

        this.uniqueColList = [0];
        this.anchorColList  = [0, 10];
        this.anchorColListOnlyLink  = [0];
        this.urlColList = [0];
        this.TF = 3;
        this.CF = 4;
        this.RD = 5;
        this.RW = 6;
        this.RF = 7;
        this.isLiveColNo = 13;

        this.getAppLayout = function () {
            var layout = {
                header: { location: 'app/views/layout/app/Header.html', isVisible: true },
                leftPanel: { location: 'app/views/layout/app/LeftPanel.html', isVisible: true},
            };
            return layout;
        };

        this.getWebLayout = function () {
            var layout = {
                header: { location: 'app/views/layout/web/Header.html', isVisible: false },
                leftPanel: { location: 'app/views/layout/web/LeftPanel.html', isVisible: false },
            };
            return layout;
        };

        this.userInfoCookieStoreKey = 'user.info.cookie.store.key';
        this.secondaryInfoCookieStoreKey = 'secondary.user.info.cookie.store.key';
        this.campaignKey = 'campaignKey.cookie.store.key';
        this.AlertMessage = 'AlertMessage';
        
        this.Login = 'Login';
        this.Logout = 'Logout';
        
        this.Active = 'A';
        this.Inactive = 'I';
        
        this.Save = 'Save';
        this.Update = 'Update';
        this.GetAll = 'GetAll';
        this.GetAllRole = 'GetAllRole';
        this.GetUserByLoginID = 'GetUserByLoginID';
        this.GetAllUser = 'GetAllUser';
        this.GetAllSubscriber = 'GetAllSubscriber';
        this.ChangePassword = 'ChangePassword';
        this.ResetPassword = 'ResetPassword';
        this.GetDataByOid = 'GetDataByOid';
        this.reRegister = 'reRegister';
        this.deRegister = 'deRegister';
        this.simReplace = 'simReplace';
        this.getDeviceByIMEI = 'getDeviceByIMEI';  
        this.getECConnection = 'getECConnection';
        this.changeStatus = 'changeStatus';
        this.filterSubscriber = 'filterSubscriber';
        this.filterSubscriberByNID = 'filterSubscriberByNID';
        this.getAllSubscriberWithStatus = 'getAllSubscriberWithStatus';
        this.getSubscriberTransactionLogDetail = 'getSubscriberTransactionLogDetail';
        this.filterSubscriberByToken = 'filterSubscriberByToken';
        this.getSubscriberByToken = 'getSubscriberByToken';
        this.getAllSubscriberWithToken = 'getAllSubscriberWithToken';
        this.getVidWiseTransactionLog = 'getVidWiseTransactionLog';
        this.getMsisdnHistory = 'getMsisdnHistory';
        this.getDeviceJSON = 'getDeviceJSON';
        this.resetUser = 'resetUser';
        this.getAllMetadata = 'getAllMetadata';
        this.updateByOid = 'updateByOid';
        this.filterByTokenForUrfReport = 'filterByTokenForUrfReport';
        this.getServiceDashboardData = 'getServiceDashboardData';
        this.getNIDWiseCount = 'getNIDWiseCount';
        this.getNIDWiseCountDetail = 'getNIDWiseCountDetail';
        this.getCustomerByMsisdn = 'getCustomerByMsisdn';
        this.getCustomerByID = 'getCustomerByID';
        this.getMSISDNHistoryDetail = 'getMSISDNHistoryDetail';
        this.getMsisdnHistoryByMsisdn = 'getMsisdnHistoryByMsisdn';

        this.removeTransaction = 'removeTransaction';
        this.getRetailers = 'getRetailers';
        
        
        this.Danger = 'danger';
        this.Success = 'success';
        this.Info = 'info';
        this.Warning = 'warning';


        this.defaultColor = '';
        this.liveColor = 'rgb(198, 239, 206)';
        this.errorColor = 'rgb(236, 182, 182)';



        this.getPageItemText = function(pageDataBegin, pageDataEnd, pageDataTotal, recordTypeText, language) {
        	var pageItemText = "Showing "+pageDataBegin+
			" to "+pageDataEnd+
			" of "+pageDataTotal+
			" total "+recordTypeText+".";
			
			return pageItemText;       	
        };
    };
    
    app.service('constantService', ['$rootScope', '$cookieStore',  constantService]);

});
