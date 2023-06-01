package helperFiles;

import java.util.Random;

public class WorldCreator {

    public boolean inBounds(int x, int y, int width, int height){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Location[] makeLocations(int width, int height, Random rand){
        Location[][] locations = new Location[width][height];
        Location[] flatLocations = new Location[width*height];

        int counter = 0;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                locations[i][j] = new Location(counter+1);
                flatLocations[counter++] = locations[i][j];
            }
        }

        int pathLength;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){

                if (inBounds(i-1, j, width, height)){
                    pathLength = 1 + rand.nextInt(10);
                    locations[i][j].addNeighbour(locations[i-1][j], pathLength);
                    locations[i-1][j].addNeighbour(locations[i][j], pathLength);
                }
                if (inBounds(i, j-1, width, height)){
                    pathLength = 1 + rand.nextInt(10);
                    locations[i][j].addNeighbour(locations[i][j-1], pathLength);
                    locations[i][j-1].addNeighbour(locations[i][j], pathLength);
                }
            }
        }
        return flatLocations;
    }

}
