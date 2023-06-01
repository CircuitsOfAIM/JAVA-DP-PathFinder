package helperFiles;

import java.util.ArrayList;

public class Location {
    public int id;
    private ArrayList<Location> neighbours;
    private ArrayList<Integer> pathLengths;

    public Location(int id) {
        this.id = id;
        this.neighbours = new ArrayList<>();
        this.pathLengths = new ArrayList<>();
    }

    public void addNeighbour(Location neighbour, int pathLength){
        neighbours.add(0, neighbour);
        pathLengths.add(0, pathLength);
    }

    public ArrayList<Location> getNeighbours(){
        return neighbours;
    }

    public int distanceTo(Location location){
        for (int i = 0; i < neighbours.size(); i++)
            if (neighbours.get(i) == location)
                return pathLengths.get(i);
        return -1;
    }
}
