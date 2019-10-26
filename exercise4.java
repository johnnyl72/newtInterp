import java.io.*;
import java.util.*;

public class exercise4 {

	static int yMAXIMUM = 500;
	static String file;
	//static Scanner keyboard;

	public static void main(String[] args) {
			genPts();
			read(file);
	}
	static void genPts() {

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter n amount of random of points you would like: ");
		int points = Integer.parseInt(keyboard.nextLine());

		System.out.println("Enter the file name and extension");
		String fileName = keyboard.nextLine();
		file = fileName;
		System.out.println("Created file: " + file);
		int[] xVector = new int[points];
		int[] yVector = new int[points];

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = -(points/2); i <= (points/2); i++){
		numbers.add(i);
		}
		Collections.shuffle(numbers);
		for(int i = 0; i < points; i++) {
			xVector[i] = (int) numbers.get(i);

			Random rand = new Random();
			int point = rand.nextInt(yMAXIMUM) - rand.nextInt(yMAXIMUM);
			yVector[i] = point;
		}

		StringBuilder sb1 = new StringBuilder();
		for(int i = 0; i < xVector.length; i++) {
			sb1.append(xVector[i]).append(" ");
		}
		StringBuilder sb2 = new StringBuilder();
		for(int i = 0; i < xVector.length; i++) {
			sb2.append(yVector[i]).append(" ");
		}

		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

			bufferedWriter.write(sb1.toString());
			bufferedWriter.newLine();
			bufferedWriter.write(sb2.toString());
	        bufferedWriter.close();
		}
		catch(IOException e) {
		}

	}

	static void read(String fileName) {
		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + fileName));

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

			Scanner keyboard = new Scanner(System.in);
			input = keyboard.nextLine();

			while(!input.equalsIgnoreCase("q")) {

				long startTime = System.nanoTime();
				Coeff(xVector, yVector, new double[xVector.length], Double.parseDouble(input));
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				System.out.println("Elapsed Time: " + duration + " nanoseconds");
				input = keyboard.nextLine();
			}


			bufferedReader.close();
			keyboard.close();
			System.exit(0);
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
