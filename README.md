# JAVA implementation of path finding probelm using dynamic programming technique

This code is dynamic programming implementation of an  search algorithm that
finds the length of the shortest path from a starting location to the a goal.In other words, it is a customized Dijkstra's Algorithm.

The path finder `getQuickestPath(int position, int goal, int[][] paths, int depth)` has following parameters:

• position is the current position. At the first call of this function, this will be the
starting location.

• goal this is the state we have to find the shortest path to.

• paths is a nrLocations x nrLocations array. If there is a path from a position to
another position, the value paths[x][y] will be greater than 0. Else, it will be zero.

In addition to these parameters, there is also the parameter depth. This integer denotes
which level of recursion we are in. 

**NOTE**: this code is an assignment under Programming course in Radboud University.This assignment was done on a two-individual group format.All rights are reserved.
