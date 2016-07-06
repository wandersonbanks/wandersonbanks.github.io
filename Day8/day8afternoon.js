var app = angular.module('groceryApp', []); 

app.controller('GroceryCtrl', function($scope) {
  
  $scope.groceryList = [];
  
  $scope.addGroceryItem = function() {
    var groceryItem = {
      name: $scope.itemName,
      qty: $scope.quantity
    };
    var found = false;
    var tempQty = 0;
    var position= 0;
    for(var i=0; i<($scope.groceryList).length;i++)
    {
       if($scope.itemName===$scope.groceryList[i].name)
       {
          found=true;
          tempQty=$scope.groceryList[i].qty;
          position=i;
          break; 
       }
       else
       {
          continue;
       }
    }
    if(!found)
    {
       $scope.groceryList.push(groceryItem);
    }
    else
    {
      $scope.groceryList[position].qty = parseInt($scope.quantity)+parseInt($scope.groceryList[position].qty);
    }
    found=false;
    var tempQty = 0;
    var position= 0;
    $scope.itemName = "";
    $scope.quantity = "";
  };


  $scope.addOne = function(index)
  {
     $scope.groceryList[index].qty = parseInt($scope.groceryList[index].qty)+1;
  };

  $scope.minusOne = function(index)
  {
    if($scope.groceryList[index].qty != '0')
    {
      $scope.groceryList[index].qty = parseInt($scope.groceryList[index].qty)-1;
    }
  };
});