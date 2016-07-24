var app = angular.module('groceryApp', []); 

app.controller('GroceryCtrl', function($scope) {
  
  $scope.groceryList = [];
  
  $scope.addGroceryItem = function() 
  {
    var groceryItem = {
      name: $scope.itemName,
      qty: $scope.quantity
    };
    if(isNaN(groceryItem.name))
    {
      var found = false;
      var tempQty = 0;
      var position= 0;
      for(var i=0; i<($scope.groceryList).length;i++)
      {
        if($scope.itemName.toLowerCase()===$scope.groceryList[i].name.toLowerCase())
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
    }
    else
    {
      alert("You entered an invalid entry");
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
    else
    {
       $scope.groceryList.splice(index, 1); 
    }
  };

  $scope.delete = function(index)
  {
    $scope.groceryList.splice(index, 1);
  };

  $scope.emptyList = function()
  {
    $scope.groceryList.splice(0, $scope.groceryList.length); 
  };

});