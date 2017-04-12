//Department , Team , Team member
app.controller('departmentsController', function($scope,$http,$log,$httpParamSerializerJQLike) {
	$scope.formData = {};
	
	var filter=function(){
		
	}
	var clearData=function(){
		
	}
	var edit=function(){
		
	}
	var save=function(){
		
	}
	
	
	 /** Pagination Starts and list **/

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
	$scope.refresh = function(){
	    $scope.resetPage();
		    $http({
		        method : "GET",
		        url : url
		    }).then(function success(response) {
		    	$log.log(response.data.content)
	             $scope.contentList = response.data;
		    }, function failure(response) {
		        $log.error(response.status)
	             $scope.showerror=true;
	             $scope.error=response.data.message;
		    });
	    }
	
	$scope.resetPage();
    $scope.refresh();
    
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