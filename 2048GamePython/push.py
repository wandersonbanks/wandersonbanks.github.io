
def push_up (grid):
    for i in range(3, 0, -1):
        for j in range(4):
            if grid[i-1][j] == 0:
                grid[i-1][j] = grid[i][j]
                grid[i][j] = 0
            elif grid[i-1][j] == grid[i][j]:
                grid[i-1][j] = grid[i-1][j]+grid[i][j]
                grid[i][j] = 0
            elif grid[i-1][j] != grid[i][j]:
                continue
    return grid
                
def push_down (grid):
    for i in range(0, 3):
        for j in range(4):
            if grid[i+1][j] == 0:
                grid[i+1][j] = grid[i][j]
                grid[i][j] = 0
            elif grid[i+1][j] == grid[i][j]:
                grid[i+1][j] = grid[i+1][j]+grid[i][j]
                grid[i][j] = 0
            elif grid[i+1][j] != grid[i][j]:
                continue
    return grid
                
def push_left (grid):
    for i in range(3, 0, -1):
        for j in range(4):
            if grid[j][i-1] == 0:
                grid[j][i-1] = grid[j][i]
                grid[j][i] = 0
            elif grid[j][i-1] == grid[j][i]:
                grid[j][i-1] = grid[j][i-1]+grid[j][i]
                grid[j][i] = 0
            elif grid[j][i-1] != grid[j][i]:
                continue
    return grid
            
def push_right (grid):
    for i in range(0, 3):
        for j in range(4):
            if grid[j][i+1] == 0:
                grid[j][i+1] = grid[j][i]
                grid[j][i] = 0
            elif grid[j][i+1] == grid[j][i]:
                grid[j][i+1] = grid[j][i+1]+grid[j][i]
                grid[j][i] = 0
            elif grid[j][i+1] != grid[j][i]:
                continue
    return grid            
                
                