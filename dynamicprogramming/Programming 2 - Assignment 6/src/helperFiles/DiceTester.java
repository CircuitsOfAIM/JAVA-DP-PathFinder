package helperFiles;

import assignment6.DiceSolver;

import java.util.Arrays;

public class DiceTester {

    public static int n = 0;
    public static boolean canSlowSolve = true;

    public static int compareSpeeds(int maximumValue, int sides){
        DiceSolver ds = new DiceSolver();

        // We have to set each value than a different to 1 as the probabilities will be between 0 and 1 and otherwise it will mess with conditionals.

        // Gather results from the quick algorithm
        ds.nrCalls = 0;
        double fastDist = ds.getWinChanceDynamic(maximumValue, sides);
        long fastOps = ds.nrCalls;

        if (fastOps > Math.pow(10, 9)){
            System.out.println("Your implementation of path finder is too slow...");
            System.out.println("Are you sure that your algorithm uses dynamic programming correctly?");
            System.exit(1);
        }


        // Gather results from the slow algorithm
        long slowOps = (long) Math.pow(10, 9);
        double slowDist = 0;
        if (canSlowSolve) {
            ds.nrCalls = 0;
            slowDist = ds.getWinChance(0,0, maximumValue, sides);
            slowOps = ds.nrCalls;
        }


        slowDist = (double)Math.round(slowDist*10000)/10000;
        fastDist = (double)Math.round(fastDist*10000)/10000;

        // Output results to console
        String s = "Test " + (++n) + ": " + sides + "-sided die, max value=" + maximumValue + "\n";

        if(slowOps >= Math.pow(10,9)) {
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

        int nrTests = 0;
        int passedTests = 0;

        int[] dice = {4,6,8,10,12};
        for (int maxValue = 10; maxValue <= 21; maxValue++){
            for (int sides : dice) {
                if (canSlowSolve) {
                    passedTests += compareSpeeds(maxValue, sides);
                    nrTests++;
                }
                else
                    compareSpeeds(maxValue, sides);
            }
        }
        System.out.println("Your implementation got (" + passedTests + "/" + nrTests + ") answers correct");
    }

}
