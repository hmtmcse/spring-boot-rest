'use strict';

define(['app'], function (app) {

    var bootstrapDatepicker = function () {
     return {
            require: 'ngModel',
            link: function (scope, el, attr, ngModel) {
                $(el).datepicker({
                     
                }).on("changeDate", function (ev) {
                 scope.$apply(function () {
                      //  ngModel.$setViewValue(moment(ev.date));
                        var x = moment(ev.date).format('YYYY-MM-DD').toString();
                        ngModel.$setViewValue(x);
                    });
                    $(el).datepicker('hide');
                });
            }
        };
    	
    };

    app.directive('bootstrapDatepicker', [ bootstrapDatepicker ]);


});

