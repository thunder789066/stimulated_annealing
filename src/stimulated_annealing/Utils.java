package stimulated_annealing;
import java.lang.Math;
import java.util.*;

public class Utils {
	public int init_temp = 1000;
	public int reduction_b4_change = init_temp * 2;
	public int reduction_b4_attempt = reduction_b4_change * 10;
	public double reduction_temp = 0.95;
	
	public int ttl_students = 200;
	public int students_per_suite = 4;
	public int ttl_suites = ttl_students / students_per_suite;
	public int ttl_compatibilities = (int) Math.pow(ttl_students, 2);
	
	public boolean active = true;
	public int fitnessScore = 0;
	
	ArrayList<Integer> roommates = new ArrayList<Integer>(students_per_suite);
	
	// Calculates the fitness score for each roommate in every suite/dorm
	public void GetFitnessScore(ArrayList<Integer> roommateSetup) {
		for (int i = 0; i < students_per_suite-1; i++) {
			for (int j = i+1; j < students_per_suite; j++)
				fitnessScore += roommateSetup.get(roommates.get(i)*ttl_students + roommates.get(j));
		}
	}
	
	// Shell sort
	public ArrayList<Integer> sortStudentsBySuite(ArrayList<Integer> arr) {
		int n = students_per_suite;
		
		for (int gap = n/2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i += 1) {
				int temp = arr.get(i);
				int j;
				for (j = i; j >= gap && arr.get(j-gap) > temp; j -= gap)
					arr.set(j, arr.get(j-gap));
				arr.set(j, temp);
			}
		}
		return arr;
	}
	
	public void getOutput() {
		System.out.println("Fitness Score: " + String.valueOf(fitnessScore));
		System.out.println("Students: ");
		for (int i = 0; i < students_per_suite - 1; i++)
			System.out.println(String.valueOf(roommates.get(i)));
		System.out.println(String.valueOf(roommates.get(students_per_suite - 1)));
	}
}
