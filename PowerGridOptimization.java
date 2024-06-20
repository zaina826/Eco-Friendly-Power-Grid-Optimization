import java.util.ArrayList;
import java.util.*;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {

    //This is basically the input list
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;
    private Integer numOfHours;


    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
        this.numOfHours=amountOfEnergyDemandsArrivingPerHour.size();
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalPowerGridSolution
     */

    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP(){
        int[] SOL = new int[numOfHours+1];
        int[][] Hours = new int[numOfHours+1][];

        SOL[0] = 0;
        Hours[0] = new int[0];
        int globalMax=0;
        int maxJ=0;
        ArrayList<Integer> maxHours = new ArrayList<Integer>();
        //Iterate over the hours:
        for (int j=1; j<=numOfHours; j++){
            //We are also considering solution 0 here
            int max=0;
            for (int i=0; i<j; i++){
                int currentSol = SOL[i] + Math.min(amountOfEnergyDemandsArrivingPerHour.get(j-1), (j-i)*(j-i));
                if (max<currentSol){
                    maxJ=j;
                    max= currentSol;
                    Hours[j]=Arrays.copyOf(Hours[i], Hours[i].length+1);
                    Hours[j][Hours[i].length]=j;

                }
            }
            SOL[j]= max;
            maxHours.clear();

            for(int num : Hours[j]) {
                maxHours.add(num);
            }
            globalMax= Math.max(max,globalMax);
        }


        OptimalPowerGridSolution solution= new OptimalPowerGridSolution(globalMax,maxHours);
        return solution;
    }

    public int getSum(){
        int sum = 0;
        for(int i = 0; i < this.amountOfEnergyDemandsArrivingPerHour.size(); i++){
            sum += this.amountOfEnergyDemandsArrivingPerHour.get(i);}
        return sum;
    }
}
