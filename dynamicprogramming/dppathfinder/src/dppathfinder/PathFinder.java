package dppathfinder;

import helperFiles.Location;

import java.util.Arrays;



public class PathFinder {

    public static long nrCalls = 0;

    /**
     * This is a helper function for the getQuickestPathDynamic function, where we generate a table to make it dynamic.
     * @param location A value representing the starting location of the person.
     * @param goal A value representing the target location.
     * @param nrOfLocations The amount of locations we can go to at each position.
     * @return The quickest path towards the target goal.
     */
    public int getQuickestPathDynamic(Location location, Location goal,
                                     int nrOfLocations){
        int[] table = new int [nrOfLocations];
        for(int i = 0; i < nrOfLocations; i++){
         table[i] = Integer.MAX_VALUE;
        }
        return getQuickestPathDynamic(location,goal,0,table);
    }

    /**
     * A dynamic recursive program, that calculates the shortest path from a starting location towards a goal.
     * @param location A value representing the starting location of the person.
     * @param goal A value representing the target location.
     * @param currentPath The length of the path we are currently on.
     * @param table A table to store and access the calculations made during the recursive calls.
     * @return The shortest path.
     */
    public int getQuickestPathDynamic(Location location, Location goal,
                                      int currentPath, int [] table){
        ///////////////////////////////////////
        // WRITE YOUR CODE FOR PART 6.1 HERE //
        ///////////////////////////////////////


        // Whenever we are going to this function
        // we increment the number of calls by 1
        nrCalls++;

        // If our current position is the goal
        // return the length of the path walked so far
        if (location.id == goal.id)
            return currentPath;
        if(currentPath > table[location.id])
            return Integer.MAX_VALUE;

        else{
            table[location.id] = currentPath;
            // Assume that shortest distance is going to be infinite
            // (no path exists)
            int shortestPath = Integer.MAX_VALUE;

            // Loop through each location connected to the current location
            // keep track of which path leads to the shortest solution
            int nextPath, distance;
            for (Location newLocation : location.getNeighbours()) {
                // Update the length of the path travelled so far
                nextPath = currentPath + location.distanceTo(newLocation);

                // Determine the shortest path from here recursively
                distance = getQuickestPathDynamic(newLocation, goal, nextPath, table);

                // If this path is the current shortest, overwrite
                // the previous shortest path
                if (distance < shortestPath)
                    shortestPath = distance;
            }
            // Return the shortest path from this point
            return shortestPath;
        }
    }

    //Function was given. I assume we don't need to doc this.

    public int getQuickestPath(Location location, Location goal,
                               int currentPath, int depth){
        // Whenever we are going to this function
        // we increment the number of calls by 1
        nrCalls++;

        // If our current position is the goal
        // return the length of the path walked so far
        if (location.id == goal.id)
            return currentPath;

        // Break out of the recursion if the number of
        // calls or recursion depth is too high
        else if (depth > goal.id || nrCalls >= Math.pow(10, 8)){
            return Integer.MAX_VALUE;
        }


        else{
            // Assume that shortest distance is going to be infinite
            // (no path exists)
            int shortestPath = Integer.MAX_VALUE;

            // Loop through each location connected to the current location
            // keep track of which path leads to the shortest solution
            int nextPath, distance;
            for (Location newLocation : location.getNeighbours()) {
                // Update the length of the path travelled so far
                nextPath = currentPath + location.distanceTo(newLocation);

                // Determine the shortest path from here recursively
                distance = getQuickestPath(newLocation, goal, nextPath, depth+1);

                // If this path is the current shortest, overwrite
                // the previous shortest path
                if (distance < shortestPath)
                    shortestPath = distance;
            }
            // Return the shortest path from this point
            return shortestPath;
        }
    }
}