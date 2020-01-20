
'use strict';

define(['app'], function (app) {

    var apiService = function ($http, $q, $location) {

        var serviceBase = 'http://localhost:8080/';
        ///var serviceBase = 'http://104.154.171.213:8080/rsos/';
        //var serviceBase = 'http://2.56.116.214:8080/rsos/';
		var imageUploader = 'imageUploader/upload';


		this.post = function (q, obj) {
			return $http.post(serviceBase + q, obj).then(function (results) {
				return results.data;
            });
        };

		this.put = function (q, obj) {
			return $http.put(serviceBase + q, obj).then(function (results) {
				return results.data;
			});
		};

		this.get = function (q) {
			return $http.get(serviceBase + q).then(function (results) {
				return results.data;
			});
		};

		this.delete = function (q) {
			return $http.delete(serviceBase + q).then(function (results) {
				return results.data;
			});
		};

		this.fileUploader = function (subPath) {
			if(angular.isUndefined(subPath)){
				subPath = imageUploader
			}
			return serviceBase+"/"+subPath;
		};

	};

    app.service('apiService', ['$http', '$q', '$location', apiService]);

});