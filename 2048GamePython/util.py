def create_grid(grid):
    for i in range(4):
        grid.append([0]*4)
    return grid

def print_grid(grid):
    for i in range(6):
        if i == 0 or i == 5:
            print("+"+("-"*20)+"+")
        elif i > 0 and i <5:
            print("|", end="")
            for p in range(4):
                if grid[i-1][p] != 0:
                    print(grid[i-1][p],' '*(5-(len(str(grid[i-1][p])))), sep="", end="")
                else:
                    print(" "*5, end="")
            print("|")           


def check_lost(grid):
    count = 0
    lost = 1
    for i in range(4):
        if lost == False:
            break
        for j in range(4):
            if lost == False:
                break
        
            if j != 3 and i != 3:
                if grid[i][j] != grid[i][j+1] and grid[i][j] != grid[i+1][j] :
                    lost = True
                else:
                    lost = False
                    break 
               
                if grid[i][j] != 0:
                        count += 1
                        
            elif i == 3 and j != 3:
                if grid[i][j] != grid[i][j+1]:
                    lost = True    
                    
                if grid[i][j] != 0 :
                    count += 1
                    
            elif j == 3 and i != 3:
                if grid[i][j] == grid[i+1][j]:
                    lost == True      
                    
                if grid[i][j] != 0 :
                    count += 1      
                    
            elif i == 3 and j == 3:
                if grid[i][j] != 0:
                    count += 1
                        
    if lost == True and count == 16:
        lost == True
    else:
        lost = False
        
    return lost
                    
def check_won (grid):
    won = False
    for i in range(4):
        if won == True:
            break
        for p in range(4):
            if grid[i][p] >= 32:
                won = True
                break
            else:
                continue
    return won

def copy_grid(grid):
    global new_grid
    new_grid = []
    
    for j in range(4):
        new_grid.append([0]*4)
        
    for r in range(4):
        for c in range(4):
            new_grid[r][c] = grid[r][c]
    return new_grid

def grid_equal(grid1, grid2):
    if grid1 == grid2:
        return True
    
    elif grid1 != grid2:
        return False
    
    


        
    
    
    