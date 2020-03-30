import java.io.*;
import java.util.*;

public class DuplicateCounter {
	
	/**
	 * Stores all the unique words and their quantities from {@link #count(String)}
	 * This variable is final because it should never be edited (in order to make a new Map, just use clear())
	 */
	private final Map<String,Integer> wordCounter = new HashMap<String,Integer>();
	
	/**
	 * Reads a data file from the given location and stores all unique words into {@link #wordCounter}
	 * @param dataFile specifies the path to the data file that should be read
	 */
	public void count(String dataFile) {
		//Reset the uniqueWords Map:
		wordCounter.clear();
		//Read unique words from the file:
		InputStream fin = null;
		try {
			fin = new FileInputStream(dataFile);
			int x;
			String s = "";
			while(true) {
				x = fin.read();
				//-1 indicates end of file, so add last word and quit
				if(x<0) {
					countWord(s);
					break;
				}
				//If not at end of file, proceed normally:
				try {
					char c = (char)x;
					//If c is a space or a newline, add the previous word and reset 's'
					if(c==' '||c=='\n') {
						if(s.length()>0) countWord(s);
						s = "";
					}
					//If c is not a space or a newline, then add it onto the current word
					else s+=c;
					
				} catch(ClassCastException e) {
					continue;//If for some reason the read value is not a char, go to next value
				}
			}
			//clean up:
			fin.close();
		}
		catch(IOException e) {
			//If error, try to clean up resources:
			try {
				if(fin!=null) fin.close();
			} catch(IOException ignored) {}
			//Then show error message:
			System.out.println("IOException at DuplicateRemover.remove()! Error message as follows:\n"+e.getMessage());
			//Then quit the program:
			System.exit(0);
		}
	}
	
	/**
	 * A small support method that prevents duplicating code in {@link #count(String)}
	 * by adding the given String into {@link #wordCounter}, and increasing the word's count if it already exists in the map
	 * @param word specifies the word to add
	 */
	private void countWord(String word) {
		if(word.length()>0) {
			if(wordCounter.containsKey(word)) wordCounter.put(word, wordCounter.get(word)+1);
			else wordCounter.put(word, 1);
		}
	}
	
	
	/**
	 * Writes the contents of {@link #wordCounter} into the data file at the specified path
	 * @param outputFile specifies the path of the file to write to
	 */
	public void write(String outputFile) {
		OutputStream fout = null;
		try {
			fout = new FileOutputStream(outputFile);
			String output = wordCounter.toString();
			for(int i=0; i<output.length(); i++) {
				fout.write(output.charAt(i));
			}
			
			//clean up:
			fout.close();
		}
		catch(IOException e) {
			//If error, try to clean up resources:
			try {
				if(fout!=null) fout.close();
			} catch(IOException ignored) {}
			//Then show error message:
			System.out.println("IOException at DuplicateRemover.write()! Error message as follows:\n"+e.getMessage());
			//Then quit the program:
			System.exit(0);
		}
	}



}
