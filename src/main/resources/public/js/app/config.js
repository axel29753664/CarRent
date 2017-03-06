app = angular.module("mainApp", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "carRent.html",
            controller: "carRentCtrl"
        })
        .otherwise({
            template: "<h1>Wrong url</h1>"
        })
});
app.constant("urls", {
        HOME: "http://localhost:8080/",
        GET_ALL_RENTS: "http://localhost:8080/getAllRents",
        GET_CURRENT_RENTS: "http://localhost:8080/getCurrentRents",
        GET_EMPLOYEES: "http://localhost:8080/getAllEmployees",
        GET_CARS: "http://localhost:8080/getAllCars",
        POST_RENT: "http://localhost:8080/addNewRent",
        DELETE_RENTS: "http://localhost:8080/deleteRents"
    }
);