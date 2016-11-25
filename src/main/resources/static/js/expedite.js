app.controller('ConfigController', function($scope,$http,$log) {
	$scope.formData = {};
    $scope.resetPage=function(){
            $scope.showsucess=false;
            $scope.showerror=false;
            $scope.success="";
            $scope.error="";
        }
    $scope.add = function() {
    	$scope.resetPage();
    	 $http({
             method  : 'POST',
             url     : url,
             data    : $scope.formData,
             headers : {'Content-Type': 'application/json'}
            }).then(
                    function success(resp){
                        $log.info(resp.data)
                        $scope.showsucess=true;
                        $scope.success=resp.data.message;
                        $scope.formData = {};
                        $scope.refresh();
                     },
                    function failure(resp){
                    $log.error(resp.status)
                     $scope.showerror=true;
                     $scope.error=resp.data.message;
                    });
    };
    $scope.refresh = function(){
    $scope.resetPage();
	    $http({
	        method : "GET",
	        url : url
	    }).then(function mySuccess(response) {
	    	$log.log(response.data)
	        $scope.configurationList = response.data;
	    }, function myError(response) {
	        $log.error(response.status)
             $scope.showerror=true;
             $scope.error=resp.data.message;
	    });
    }


    $scope.modify=function (config)
    {
        $scope.resetPage();
        $log.log(config);
        $http({
         method  : 'POST',
         url     : url+"/update",
         data    : config,
         headers : {'Content-Type': 'application/json'}
        }).then(function success(resp){
                $log.info(resp.status)
                 $scope.showerror=true;
                 $scope.error=resp.data.message;
                 $scope.refresh();
                },
                function failure(resp){
                $log.error(resp.status)
                 $scope.showerror=true;
                 $scope.error=resp.data.message;
                });
    }
       $scope.resetPage();
       $scope.refresh();
});

