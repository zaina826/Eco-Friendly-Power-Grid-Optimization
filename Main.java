import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/
        String filename = args[0];
        String filename2 = args[1];

        ArrayList<Integer> hours = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            if ((line = reader.readLine()) != null) {
                String[] numberStrings = line.split(" ");

                for (String numberString : numberStrings) {
                    hours.add(Integer.parseInt(numberString));
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> tasks = new ArrayList<>();
        int numTrucks=0, capacityOfEachTruck=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename2));

            String firstLine = reader.readLine();
            if (firstLine != null) {
                String[] parts = firstLine.split(" ");
                numTrucks = Integer.parseInt(parts[0]);
                capacityOfEachTruck = Integer.parseInt(parts[1]);
            }

            String secondLine = reader.readLine();
            if (secondLine != null) {
                String[] numberStrings = secondLine.split(" ");
                for (String numberString : numberStrings) {
                    tasks.add(Integer.parseInt(numberString));
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("##MISSION POWER GRID OPTIMIZATION##");
        PowerGridOptimization optimzation= new PowerGridOptimization(hours);
        OptimalPowerGridSolution solution= optimzation.getOptimalPowerGridSolutionDP();
        //Now we have the solution, I guess we need to pass it into an object of type OPGS
//        OptimalPowerGridSolution OPGS = new OptimalPowerGridSolution(optimzation.maxNum,optimzation.hours);
        System.out.println("The total number of demanded gigawatts: " + optimzation.getSum());
        System.out.println("Maximum number of satisfied gigawatts: " + solution.getmaxNumberOfSatisfiedDemands());
        System.out.print("Hours at which the battery bank should be discharged: ");
        int i;
        for (i = 0; i <solution.getHoursToDischargeBatteriesForMaxEfficiency().size()-1; i++) {
            System.out.print(solution.getHoursToDischargeBatteriesForMaxEfficiency().get(i)+ ", ");
        }
        System.out.println(solution.getHoursToDischargeBatteriesForMaxEfficiency().get(i));
        System.out.println("The number of unsatisfied gigawatts: "+ (optimzation.getSum()-solution.getmaxNumberOfSatisfiedDemands()));
        // TODO: Your code goes here
        // You are expected to read the file given as the first command-line argument to read 
        // the energy demands arriving per hour. Then, use this data to instantiate a 
        // PowerGridOptimization object. You need to call getOptimalPowerGridSolutionDP() method
        // of your PowerGridOptimization object to get the solution, and finally print it to STDOUT.
        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");

        /** MISSION ECO-MAINTENANCE BELOW **/

        System.out.println("##MISSION ECO-MAINTENANCE##");
        OptimalESVDeploymentGP esv = new OptimalESVDeploymentGP(tasks);
        int numOfTrucks= esv.getMinNumESVsToDeploy(numTrucks,capacityOfEachTruck);
        if (numOfTrucks==-1){
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        }else{
            System.out.println("The minimum number of ESVs to deploy: "+ numOfTrucks);
            for (int k = 0; k < esv.getMaintenanceTasksAssignedToESVs().size(); k++) {
                System.out.print("ESV " + (k+1) + " tasks: [");
                ArrayList<ArrayList<Integer>> maintenanceTasks = esv.getMaintenanceTasksAssignedToESVs();
                ArrayList<Integer> taskList = maintenanceTasks.get(k);
                for (int j = 0; j < taskList.size(); j++) {
                    if (j < taskList.size() - 1) {
                        System.out.print(taskList.get(j) + ", ");
                    } else {
                        System.out.print(taskList.get(j));
                    }
                }
                System.out.print("]");
                System.out.println();
            }
        }
        // TODO: Your code goes here
        // You are expected to read the file given as the second command-line argument to read
        // the number of available ESVs, the capacity of each available ESV, and the energy requirements 
        // of the maintenance tasks. Then, use this data to instantiate an OptimalESVDeploymentGP object.
        // You need to call getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) method
        // of your OptimalESVDeploymentGP object to get the solution, and finally print it to STDOUT.
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
