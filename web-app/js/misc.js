// Some stuff for geotag
module.service('timeAgoService', function($timeout) {
    var ref;
    return {
        nowTime: 0,
        initted: false,
        settings: {
            refreshMillis: 60000,
            allowFuture: false,
            strings: {
                prefixAgo: " ",
                prefixFromNow: " ",
                suffixAgo: "",
                suffixFromNow: "",
                seconds: "less than 1 minute",
                minute: "1 minute",
                minutes: "%d minutes",
                hour: "1 hour",
                hours: "%d hours",
                day: "1 day",
                days: "%d days",
                month: "1 month",
                months: "%d months",
                year: "1 year",
                years: "%d years",
                numbers: []
            }
        },
        doTimeout: function() {
            ref.nowTime = (new Date()).getTime();
            $timeout(ref.doTimeout, ref.settings.refreshMillis);
        },
        init: function() {
            if (this.initted == false) {
                this.initted = true;
                this.nowTime = (new Date()).getTime();
                ref = this;
                this.doTimeout();
                this.initted = true;
            }
        },
        inWords: function(distanceMillis) {
            var $l = this.settings.strings;
            var prefix = $l.prefixAgo;
            var suffix = $l.suffixAgo;
            if (this.settings.allowFuture) {
                if (distanceMillis < 0) {
                    prefix = $l.prefixFromNow;
                    suffix = $l.suffixFromNow;
                }
            }

            var seconds = Math.abs(distanceMillis) / 1000;
            var minutes = seconds / 60;
            var hours = minutes / 60;
            var days = hours / 24;
            var years = days / 365;

            function substitute(stringOrFunction, number) {
                var string = $.isFunction(stringOrFunction) ? stringOrFunction(number, distanceMillis) : stringOrFunction;
                var value = ($l.numbers && $l.numbers[number]) || number;
                return string.replace(/%d/i, value);
            }

            var words = seconds < 45 && substitute($l.seconds, Math.round(seconds)) ||
                seconds < 90 && substitute($l.minute, 1) ||
                minutes < 45 && substitute($l.minutes, Math.round(minutes)) ||
                minutes < 90 && substitute($l.hour, 1) ||
                hours < 24 && substitute($l.hours, Math.round(hours)) ||
                hours < 42 && substitute($l.day, 1) ||
                days < 30 && substitute($l.days, Math.round(days)) ||
                days < 45 && substitute($l.month, 1) ||
                days < 365 && substitute($l.months, Math.round(days / 30)) ||
                years < 1.5 && substitute($l.year, 1) ||
                substitute($l.years, Math.round(years));

            var separator = $l.wordSeparator === undefined ?  " " : $l.wordSeparator;
            return $.trim([prefix, words, suffix].join(separator));
        }
    }
});

module.directive('timeAgo', ['timeAgoService', function(timeago) {
    return {
        replace: true,
        restrict: 'EA',
        scope: {
            "fromTime":"@"
        },
        link: {
            post: function(scope, linkElement, attrs) {
                scope.timeago = timeago;
                scope.timeago.init();
                scope.$watch("timeago.nowTime-Date.parse(fromTime)",function(value) {
                    if (scope.timeago.nowTime != undefined) {
                        if(isNaN(scope.fromTime)) {
                            scope.fromTime = Date.parse(scope.fromTime);
                        }
                        value = scope.timeago.nowTime-scope.fromTime;
                        $(linkElement).text(scope.timeago.inWords(value));
                    }
                });
            }
        }
    }
}]);
