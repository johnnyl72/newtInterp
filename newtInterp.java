import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class newtInterp {

	public static void main(String[] args) {
		String fileName = args[0];
		read(fileName);

	}
	static void read(String fileName) {
		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + fileName));

			Scanner kb = new Scanner(System.in);
			String[] inputString;

			//Read in x vector
			String line = bufferedReader.readLine();
			inputString = line.split("\\s+");
			double[] xVector = new double[inputString.length];
			for(int i = 0; i < xVector.length; i++) {
				xVector[i] = Double.parseDouble(inputString[i]);
			}
			System.out.println("X VECTOR: " + Arrays.toString(xVector));

			//Read in y vector
			line = bufferedReader.readLine();
			inputString = line.split("\\s+");
			double[] yVector = new double[inputString.length];
			for(int i = 0; i < yVector.length; i++){
				yVector[i] = Double.parseDouble(inputString[i]);
			}
			System.out.println("Y VECTOR: " + Arrays.toString(yVector));

			String input;
			System.out.println("Enter 'q' to quit");
				do {
					input = kb.nextLine();
					long startTime = System.nanoTime();
					Coeff(xVector, yVector, new double[xVector.length], Double.parseDouble(input));
					long endTime = System.nanoTime();
					long duration = (endTime - startTime);
					System.out.println("Elapsed Time: " + duration + " nanoseconds");
				}while(!input.equalsIgnoreCase("q"));


			bufferedReader.close();
			kb.close();
		}//end try
		catch(FileNotFoundException e) {
		}
		catch(IOException e) {
		}//end catch

	}//end read

	static void Coeff(double[] xs, double[] ys, double[] cs, double z){
		int n = xs.length-1;

		for(int i = 0; i <= n; i++){
			cs[i] = ys[i];
		}

		for(int j = 1; j <= n; j++){
			for(int i = n; i >= j; i--){
				cs[i] = (cs[i] - cs[i-1]) / (xs[i] - xs[i-j]);
			} //end for
		} //end for

		EvalNewton(xs, cs, z);
	} //end Coeff

	static void EvalNewton(double[] xs, double[] cs, double z){
		int n = xs.length-1;
		double result = cs[n];

		for(int i = (n-1); i >= 0; i--){
			result = result * (z - xs[i]) + cs[i];
		} //end for

		System.out.println(result);

	} //end EvalNewton
}
