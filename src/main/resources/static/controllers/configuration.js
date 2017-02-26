app.controller('ConfigController', function($scope,$http,$log,$httpParamSerializerJQLike) {
	$scope.formData = {};
	$scope.search={};
    $scope.resetPage=function(){
            $scope.showsucess=false;
            $scope.showerror=false;
            $scope.success="";
            $scope.error="";
        }
    $scope.reset=function(){
    	$scope.search={};
    	$scope.refresh();
    }
    $scope.currentPage = 0;
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
	        url : url+ "?p="+$scope.currentPage+"&"+$httpParamSerializerJQLike($scope.search)
	    }).then(function success(response) {
	    	$log.log(response.data.content)
             $scope.configurationList = response.data.content;
	    	 $scope.itemsPerPage = response.data.size;
	    	 $scope.totalPages=response.data.totalPages
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
       /** Pagination Starts**/

       $scope.range = function() {
    	   
 	    var rangeSize = 5;
 	    var ret = [];
 	    var start;

 	    start = $scope.currentPage;
 	    if($scope.totalPages < rangeSize){
 	    	rangeSize= $scope.totalPages;
 	    }else if ( start > $scope.pageCount()-rangeSize ) {
 	      start = $scope.totalPages-rangeSize+1;
 	    }
 	    

 		if(start+rangeSize>=$scope.totalPages){
 			start=$scope.totalPages-rangeSize;
 		}
 		for (var i=start; i<start+rangeSize; i++) {
 	 	    ret.push(i);
 	 	}
 	    return ret;
 	  };

 	  $scope.prevPage = function() {
 	    if ($scope.currentPage > 0) {
 	      $scope.currentPage--;
 	     $scope.refresh();
 	    }
 	  };

 	  $scope.prevPageDisabled = function() {
 	    return $scope.currentPage === 0 ? "disabled" : "";
 	  };

 	  $scope.pageCount = function() {
 	    return $scope.totalPages;
 	  };

 	  $scope.nextPage = function() {
 	    if ($scope.currentPage < $scope.pageCount()) {
 	      $scope.currentPage++;
 	     $scope.refresh();
 	    }
 	  };

 	  $scope.nextPageDisabled = function() {
 	    return $scope.currentPage === ($scope.totalPages-1) ? "disabled" : "";
 	  };

 	  $scope.setPage = function(n) {
 	    $scope.currentPage = n;
 	    $scope.refresh();
 	  };
 	 /** Pagination Ends**/
});