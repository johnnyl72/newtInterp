import java.io.*;
import java.util.*;

public class generatePoints {

		public static int yMAXIMUM = 500;

	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);

		System.out.println("Enter n amount of random of points you would like: ");
		int points = Integer.parseInt(kb.nextLine());
		System.out.println("Enter the file name and extension");
		String fileName = kb.nextLine();

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
		System.out.println(sb1);
		System.out.println(sb2);

		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

			bufferedWriter.write(sb1.toString());
			bufferedWriter.newLine();
			bufferedWriter.write(sb2.toString());
	        bufferedWriter.close();
		}
		catch(IOException e) {
		}
		kb.close();
	}

}
