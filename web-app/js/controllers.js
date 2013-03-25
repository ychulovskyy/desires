var desiresOnPage = 20;
userName = "";

errorHandler = function(data, status, headers, config) {
    // called asynchronously if an error occurs
    // or server returns response with status
    // code outside of the <200, 400) range
    if (status == 401) {
        showLogin();
    } else {
        alert("Error #" + status + " with message '" + data + "'");
    }
}

function DesireListController($scope, $routeParams, $http) {
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

    $scope.loadWants = function() {
        $http.get("search?type=want").success( function( data ) {
            $scope.wants = data
        }).error(errorHandler);
    }
    $scope.loadCans = function() {
        $http.get("search?type=can").success( function( data ) {
            $scope.cans = data
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
        $scope.desire = $scope.Desire.get({'id': $routeParams.desireId});
    }

    $scope.loadDesire();
}

var module = angular.module('desireApp', ['ngResource']);

module.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/desire', {templateUrl: 'chunks/desires/list.html',   controller: DesireListController}).
        when('/desire/:desireId', {templateUrl: 'chunks/desires/show.html', controller: DesireDetailsController}).
        otherwise({redirectTo: '/desire'});
}]);

module.factory('sharedResources',
    function($resource) {
        var resources = {}
        resources.Want = $resource('/desire/want/:id', {id:'@id'});
        resources.Can = $resource('/desire/can/:id', {id:'@id'});
        resources.Desire = $resource('/desire/desire/:id', {id:'@id'});
        resources.Comment = $resource('/desire/desire/:desireId/comment/:commentId',
            {desireId:'@desireId', commentId:'@commentId'});
        return resources;
    }
)

module.factory('search',
    function($http) {
        return function (fillData, params) {
            $http.get("search?" + params).success( function( data ) {
                fillData = data
            }).error(errorHandler);
        }
    }
)

module.run(function($rootScope, search, sharedResources, $timeout) {
    $rootScope.search = search;
    $rootScope.timeout = $timeout;
    $rootScope.Want = sharedResources.Want;
    $rootScope.Can = sharedResources.Can;
    $rootScope.Desire = sharedResources.Desire;
    $rootScope.Comment = sharedResources.Comment;
});