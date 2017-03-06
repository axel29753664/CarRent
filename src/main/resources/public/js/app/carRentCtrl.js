angular.module("mainApp")
    .controller("carRentCtrl", function ($scope, carRentService) {

        var showAllRents = false;
        init();
        $scope.addNewRentBtn = "default";
        $scope.deleteRentBtn = "default";


        function init() {
            carRentService.getRents(showAllRents).then(function success(rents) {
                    $scope.rents = rents;
                },
                function error(error) {
                    alert(error)
                });
        }
        $scope.changeRentList = function () {
            showAllRents = !showAllRents;
            init();
        };

        function getEmployeesFromServer() {
            carRentService.getAllEmployees().then(function success(employees) {
                    $scope.employees = employees;
                },
                function error(error) {
                    alert(error)
                });
        }

        function getCarsFromServer() {
            carRentService.getAllCars().then(function success(cars) {
                    $scope.cars = cars;
                },
                function error(error) {
                    alert(error)
                });
        }

        $scope.addNewRent = function (newRent) {
            if ($scope.addNewRentBtn == "default") {
                $scope.addNewRentBtn = "success";
                $scope.showRentCreationFrom = true;
                $scope.successMessage = null;
                $scope.newRent = {};
                getEmployeesFromServer();
                getCarsFromServer();
            } else {
                carRentService.sendRentToServer(newRent).then(success, error);
                function success() {
                    $scope.showRentCreationFrom = false;
                    $scope.addNewRentBtn = "default";
                    $scope.newRent = {};
                    $scope.errorMessage = null;
                    $scope.successMessage = "Rent created successfully.";
                    init();
                }

                function error(err) {
                    $scope.errorMessage = err.data.message;
                }

            }

        };
        $scope.cancelNewRent = function () {
            $scope.showRentCreationFrom = false;
            $scope.errorMessage = null;
            $scope.newRent = {};
            $scope.addNewRentBtn = "default";
        };
        $scope.deleteRents = function (rents) {

            if ($scope.deleteRentBtn == "default") {
                $scope.deleteRentBtn = "success";
                $scope.deleteRentTrigger = true;
                $scope.successMessage = null;
                $scope.deletedRents = {};

            } else {
                carRentService.deleteRents(rents).then(success, error);
                function success() {
                    $scope.deleteRentBtn = "default";
                    $scope.deleteRentTrigger = false;
                    $scope.deletedRents = null;
                    $scope.errorMessage = null;
                    $scope.successMessage = "Rents deleted successfully.";
                    init();
                }

                function error(err) {
                    $scope.errorMessage = err.data.message;
                }

            }
        };
        $scope.cancelDeleteRents = function () {
            $scope.deleteRentTrigger = false;
            $scope.errorMessage = null;
            $scope.deletedRents = {};
            $scope.deleteRentBtn = "default";

        };
    });
