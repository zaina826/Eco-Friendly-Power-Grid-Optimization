import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Eco-Maintenance
 */
public class OptimalESVDeploymentGP
{
    private ArrayList<Integer> maintenanceTaskEnergyDemands;

    /*
     * Should include tasks assigned to ESVs.
     * For the sample input:
     * 8 100
     * 20 50 40 70 10 30 80 100 10
     * 
     * The list should look like this:
     * [[100], [80, 20], [70, 30], [50, 40, 10], [10]]
     * 
     * It is expected to be filled after getMinNumESVsToDeploy() is called.
     */
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<Integer> getMaintenanceTaskEnergyDemands() {
        return maintenanceTaskEnergyDemands;
    }

    /**
     *
     * @param maxNumberOfAvailableESVs the maximum number of available ESVs to be deployed
     * @param maxESVCapacity the maximum capacity of ESVs
     * @return the minimum number of ESVs required using first fit approach over reversely sorted items.
     * Must return -1 if all tasks can't be satisfied by the available ESVs
     */
    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity)
    {
        // TODO: Your code goes here

        Collections.sort(maintenanceTaskEnergyDemands);
        Collections.reverse(maintenanceTaskEnergyDemands);

        int[] CapacityArray= new int[maxNumberOfAvailableESVs];
        for (int i = 0; i < CapacityArray.length; i++) {
            CapacityArray[i] = maxESVCapacity;
        }
        ArrayList<Integer>[] ESVArray = (ArrayList<Integer>[]) new ArrayList[maxNumberOfAvailableESVs];
        for (int i = 0; i < ESVArray.length; i++) {
            ESVArray[i] = new ArrayList<Integer>();
        }

        int UsedTrucks=0;
        //Now we iterate over the tasks:
        for (int i = 0; i < this.maintenanceTaskEnergyDemands.size(); i++) {
            //Now iterate over the trucks till we find a truck that can take that demand:
            int j=0;
            while(CapacityArray[j]<this.maintenanceTaskEnergyDemands.get(i)){
                j++;
                if (j>=maxNumberOfAvailableESVs){
                    return -1;
                }
            }
            CapacityArray[j]=CapacityArray[j]- this.maintenanceTaskEnergyDemands.get(i);
            ESVArray[j].add(this.maintenanceTaskEnergyDemands.get(i));
            UsedTrucks= Math.max(UsedTrucks, j);
        }
        //Because we started our index at zero
        UsedTrucks=UsedTrucks+1;
        for (ArrayList<Integer> esv : ESVArray) {
            if (!esv.isEmpty()){
            maintenanceTasksAssignedToESVs.add(esv);}
        }
        return UsedTrucks;
    }

}
