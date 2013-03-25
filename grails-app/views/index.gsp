<!doctype html>

<!-- Adding 'ng-app' tells Angular to start its magic at this point in the DOM -->
<html ng-app="desireApp">
<head>
    <title>Desire</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/desire.css" type="text/css">
    <script src="http://code.jquery.com/jquery-1.7.2.js"></script>
    <script src="js/angular.js"></script>
    <script src="js/angular-resource.js"></script>
    <script src="js/resources.js"></script>
    <script src="js/controllers.js"></script>
    <script src="js/misc.js"></script>
</head>

<body>
    <div ng-view id="main"></div>
    <g:render template='/includes/ajaxLogin'/>
</body>
</html>