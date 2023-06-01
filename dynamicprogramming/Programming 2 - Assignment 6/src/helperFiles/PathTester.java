package helperFiles;

import assignment6.PathFinder;
import helperFiles.Location;
import helperFiles.WorldCreator;

import java.util.Random;




public class PathTester {
    static int n = 0;
    static boolean canSlowSolve = true;

    public static int compareSpeeds(int width, int height, Random random){
        WorldCreator wc = new WorldCreator();

        // Generate path based on arguments
        Location[] locations = wc.makeLocations(width, height, random);

        PathFinder pf = new PathFinder();

        // Gather results from the quick algorithm
        pf.nrCalls = 0;
        int fastDist = pf.getQuickestPathDynamic(locations[0], locations[locations.length-1], locations.length);
        long fastOps = pf.nrCalls;

        if (fastOps > Math.pow(10, 9)){
            System.out.println("Your implementation of path finder is too slow...");
            System.out.println("Are you sure that your algorithm uses dynamic programming correctly?");
            System.exit(1);
        }


        // Gather results from the slow algorithm
        long slowOps = (long) Math.pow(10, 9);
        int slowDist = 0;
        if (canSlowSolve) {
            pf.nrCalls = 0;
            slowDist = pf.getQuickestPath(locations[0], locations[locations.length - 1], 0, 0);
            slowOps = pf.nrCalls;
        }

        // Output results to console
        String s = "Test " + (++n) + ": (" + width + "x" + height + ") world\n";

        if(slowOps >= Math.pow(10,8)) {
            s += "\tDynamic algorithm: solution = " + fastDist + " (took " + fastOps + " function calls)\n";
            System.out.println(s);
            canSlowSolve = false;
        }
        else {
            s += "\tSlow algorithm: solution = " + slowDist + " (took " + slowOps + " function calls)\n";
            s += "\tDynamic algorithm: solution = " + fastDist + " (took " + fastOps + " function calls)\n";
            if (slowOps > fastOps)
                s += "\tThe dynamic algorithm is " + (slowOps / fastOps) + " times faster\n";
            else
                s += "\tThe dynamic algorithm is not slower: something might be wrong...\n";
            System.out.println(s);
        }

       if (fastDist == slowDist)
            return 1;
        else
            return 0;
    }

    public static void main (String[] args){
        Random random = new Random(414141);

        int nrTests = 0;
        int passedTests = 0;

        for (int i = 2; i <= 3; i++) {
            for (int n = 0; n < 10; n++) {
                if (canSlowSolve) {
                    passedTests += compareSpeeds(i, i, random);
                    nrTests++;
                }
            }
        }

        canSlowSolve = false;
        for (int i = 4; i <= 50; i++) {
            compareSpeeds(i, i, random);
        }

        System.out.println("Your implementation got (" + passedTests + "/" + nrTests + ") answers correct");
    }
}
