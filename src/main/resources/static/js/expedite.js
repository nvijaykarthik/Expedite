app.controller('ConfigController', function($scope,$http,$log) {
	$scope.formData = {};
    $scope.resetPage=function(){
            $scope.showsucess=false;
            $scope.showerror=false;
            $scope.success="";
            $scope.error="";
        }
   
    $scope.currentPage=0;
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
                        $scope.refresh();
                        $scope.showsucess=true;
                        $scope.success=resp.data.message;
                        $scope.formData = {};
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
	        url : url+ "?p="+$scope.currentPage
	    }).then(function success(response) {
	    	$log.log(response.data.content)
             $scope.configurationList = response.data.content;
	    	 $scope.totalItems=response.data.totalElements;
	    	 $scope.currentPage=response.data.number;
	    	 $scope.maxSize = response.data.numberOfElements;
	    }, function failure(response) {
	        $log.error(response.status)
             $scope.showerror=true;
             $scope.error=response.data.message;
	    });
    }

    $scope.pageChanged = function(){
    	$log.log($scope.currentPage);
    }
    $scope.modify=function (config)
    {
        $scope.resetPage();
        $log.log(config);
        $http({
         method  : 'PATCH',
         url     : url,
         data    : config,
         headers : {'Content-Type': 'application/json'}
        }).then(function success(resp){
                $log.info(resp.status)
                $scope.refresh();
                $scope.showsucess=true;
                $scope.success=resp.data.message;
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

