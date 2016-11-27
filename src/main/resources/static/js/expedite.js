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
//"last":false,"totalPages":2,"totalElements":50,"sort":null,"first":true,"numberOfElements":25,"size":25,"number":0
//http://jsfiddle.net/api/post/library/pure/


app.filter('offset', function() {
	  return function(input, start) {
	    start = parseInt(start, 10);
	    return input.slice(start);
	  };
	});

	app.controller("PaginationCtrl", function($scope) {

	  $scope.itemsPerPage = 5;
	  $scope.currentPage = 0;
	  $scope.items = [];

	  for (var i=0; i<50; i++) {
	    $scope.items.push({ id: i, name: "name "+ i, description: "description " + i });
	  }

	  $scope.range = function() {
	    var rangeSize = 5;
	    var ret = [];
	    var start;

	    start = $scope.currentPage;
	    if ( start > $scope.pageCount()-rangeSize ) {
	      start = $scope.pageCount()-rangeSize+1;
	    }

	    for (var i=start; i<start+rangeSize; i++) {
	      ret.push(i);
	    }
	    return ret;
	  };

	  $scope.prevPage = function() {
	    if ($scope.currentPage > 0) {
	      $scope.currentPage--;
	    }
	  };

	  $scope.prevPageDisabled = function() {
	    return $scope.currentPage === 0 ? "disabled" : "";
	  };

	  $scope.pageCount = function() {
	    return Math.ceil($scope.items.length/$scope.itemsPerPage)-1;
	  };

	  $scope.nextPage = function() {
	    if ($scope.currentPage < $scope.pageCount()) {
	      $scope.currentPage++;
	    }
	  };

	  $scope.nextPageDisabled = function() {
	    return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
	  };

	  $scope.setPage = function(n) {
	    $scope.currentPage = n;
	  };

	});
