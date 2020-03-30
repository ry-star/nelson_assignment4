

public class Application {

	/**
	 * The constant path to the data file we read input from
	 */
	public static final String readPath = "problem2.txt";
	
	/**
	 * The constant path to the data file we print output into
	 */
	public static final String writePath = "unique_word_counts.txt";
	
	/**
	 * The main class for assignment3, problem 1
	 * @param args ignored
	 */
	public static void main(String[] args) {
		DuplicateCounter duplicateCounter = new DuplicateCounter();
		duplicateCounter.count(readPath);
		duplicateCounter.write(writePath);
	}
}
