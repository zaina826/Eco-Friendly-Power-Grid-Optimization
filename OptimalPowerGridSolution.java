import java.util.ArrayList;
/**
 * A class that represents the optimal solution for the Power Grid optimization scenario (Dynamic Programming)
 */

public class OptimalPowerGridSolution {
    // This is basically the number we are trying to maximize:
    private int maxNumberOfSatisfiedDemands;
    //These are the hours that we need to discharge at to actually get that max number
    private ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency;

    //Important to remember: In the DP approach, our possible solutions don't represent hours.
    //Because a past solution could have more than one hour.
    //The point is that we look at all possible solutions from the past, and we try including it
    //Then the max of that would be the solution with that hour, it may include multiple past hours

    //Constructor
    public OptimalPowerGridSolution(int maxNumberOfSatisfiedDemands, ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency) {
        this.maxNumberOfSatisfiedDemands = maxNumberOfSatisfiedDemands;
        this.hoursToDischargeBatteriesForMaxEfficiency = hoursToDischargeBatteriesForMaxEfficiency;
    }

    public OptimalPowerGridSolution() {
       //Contructor with no parameters
    }

    public int getmaxNumberOfSatisfiedDemands() {
        return maxNumberOfSatisfiedDemands;
    }

    public ArrayList<Integer> getHoursToDischargeBatteriesForMaxEfficiency() {
        return hoursToDischargeBatteriesForMaxEfficiency;
    }



}
