var desiresOnPage = 20;

errorHandler = function(data, status, headers, config) {
    // called asynchronously if an error occurs
    // or server returns response with status
    // code outside of the <200, 400) range
    alert("Error #" + status + " with message '" + data + "'")
}

function DesireListController( $scope, $routeParams, $http ) {

    $scope.loadtime = (new Date()).getTime();

    // description for a new desire
    $scope.description = ""

    // bindable list of desires
    $scope.wants = []
    $scope.cans = []

    wantPageId = 0;
    canPageId = 0;

    $scope.hasMoreWant = true;
    $scope.hasMoreCan = true;

    // save a new desire, based on the "description" property
    $scope.createWant = function(desireDescription) {
        $http.put(
            "want/create",
            {
                description: desireDescription
            }
        ).success( function( data ) {
                $scope.wants = data
                $scope.description = ""
        }).error(errorHandler);
    }

    $scope.createCan = function(desireDescription) {
        $http.put(
            "can/create",
            {
                description: desireDescription
            }
        ).success( function( data ) {
                $scope.cans = data
                $scope.description = ""
            }).error(errorHandler);
    }

    $scope.loadWants = function() {
        $http.get("want/list/" + wantPageId++).success( function( data ) {
            if (data.length < desiresOnPage) {
                $scope.hasMoreWant = false;
            }
            $scope.wants = $scope.wants.concat(data)
        }).error(errorHandler);
    }
    $scope.loadCans = function() {
        $http.get("can/list/" + canPageId++).success( function( data ) {
            if (data.length < desiresOnPage) {
                $scope.hasMoreCan = false;
            }
            $scope.cans = $scope.cans.concat(data)
        }).error(errorHandler);
    }

    // when we first stat up, load all desires
    $scope.loadWants();
    $scope.loadCans();
};

function DesireDetailsController( $scope, $routeParams, $http ) {
    $scope.desire = null
    $scope.loadtime = (new Date()).getTime()

    $scope.loadDesire = function() {
        $http.get(
            "desire/show/" + $routeParams.desireId
        ).success( function( data ) {
                $scope.desire = data
        }).error(errorHandler);
    }

    $scope.addComment = function(desireId, commentDescription) {
        $http.put(
            "desire/addComment",
            {
                id: desireId,
                description: commentDescription
            }
        ).success( function( data ) {
                $scope.desire = data
                $scope.description = ""
        }).error(errorHandler);
    }

    // when we first stat up, load desire
    $scope.loadDesire()
}

var module = angular.module('desireApp', []);

module.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/desire', {templateUrl: 'chunks/desires/list.html',   controller: DesireListController}).
        when('/desire/:desireId', {templateUrl: 'chunks/desires/show.html', controller: DesireDetailsController}).
        otherwise({redirectTo: '/desire'});
}]);
