angular.module("mainApp")
    .factory("carRentService", function ($http, $q, urls) {
        return {
            getRents: function (showAllRents) {
                var deferred = $q.defer();
                var url = urls.GET_CURRENT_RENTS;
                if (showAllRents) {
                    url = urls.GET_ALL_RENTS;
                }
                $http.get(url).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    deferred.reject(err);
                }

                return deferred.promise;
            },
            sendRentToServer: function (rent) {
                var deferred = $q.defer();

                $http.post(urls.POST_RENT, rent).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    deferred.reject(err);
                }

                return deferred.promise;
            }
            ,
            convertToArray: function (rents) {
                var rentArray = [];
                angular.forEach(rents, function (rent) {
                    rentArray.push(rent);
                });
                return rentArray;
            }
            ,

            deleteRents: function (rents) {

                var deferred = $q.defer();
                if (!angular.equals(rents, {})) {
                    var rentArr = this.convertToArray(rents);
                    $http.post(urls.DELETE_RENTS, rentArr).then(success, error);
                    function success(response) {
                        deferred.resolve(response.data);
                    }

                    function error(err) {
                        deferred.reject(err);
                    }
                }
                return deferred.promise;
            }
            ,
            getAllEmployees: function () {
                var deferred = $q.defer();

                $http.get(urls.GET_EMPLOYEES).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    deferred.reject(err);
                }

                return deferred.promise;
            }
            ,
            getAllCars: function () {
                var deferred = $q.defer();

                $http.get(urls.GET_CARS).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    deferred.reject(err);
                }

                return deferred.promise;
            }


        }
    })
;