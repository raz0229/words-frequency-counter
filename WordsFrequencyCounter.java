import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WordsFrequencyCounter {
	
	public static int frequency = 0;
	public static String FILE = "/home/raz0229/testinput.txt"; //Input file
	public static String TARGET = "/home/raz0229/file.txt"; //Output destination
	
	WordsFrequencyCounter() throws IOException {
		
		// clears text if output file already exists to prevent overwriting
		FileOutputStream fout=new FileOutputStream(TARGET);
        fout.write("".getBytes());   
        fout.close();
	}

	public String[] makeWords(ArrayList<Character> arr) {
		 
		String temp = "";
		for (char c : arr) {
			temp += c;
		}

		temp = temp.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase(); //removes special characters from String
		return temp.split("\\s+"); //returns array of all the words
	}
	
	public void writeToFile(String[] list) {
		
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < list.length; i++) {
			try{    
	             FileOutputStream fout=new FileOutputStream(TARGET, true);  //appends  
	             //String str = getWordFrequency(list[i], list) + "\n";
	             String str = list[i] + " - " + getWordFrequency(list[i], list) + " \n";
	             
	             // Skip if the word appears more than once
	             if (!temp.contains(list[i])) {
		             fout.write(str.getBytes());   
		             fout.close();  
	             } 
	             temp.add(list[i]);
	             
	            } catch(Exception e){
	            		System.out.println(e);
					}
		}
		System.out.println("Successfully written to " + TARGET);
	}
	
	// This method is used to find occurence of a given word in a given list
	public static int getWordFrequency(String word, ArrayList<String> arr) {
		
		if (arr.contains(word)) {
			frequency++;
			arr.remove(word);
			getWordFrequency(word, arr);
		}
		
		return frequency;
	}
	
	// This method is used to find occurence of a given word in a given list
	public static int getWordFrequency(String word, String[] list) {
		frequency = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i].contentEquals(word)) {
				frequency++;
			}
		}
		return frequency;
	}
	 
	
	public static void main(String[] args) {
		 try{    
			    ArrayList<Character> arr = new ArrayList<>();
				
			    FileInputStream fin=new FileInputStream(FILE);    
			    BufferedInputStream bin=new BufferedInputStream(fin);    
			    int i;
			    while((i=bin.read())!=-1){ 
			     arr.add((char)i);
			    }    
			    bin.close();    
			    fin.close();    
				 
			    WordsFrequencyCounter wc = new WordsFrequencyCounter();
			    String[] list = wc.makeWords(arr); //returns array of strings separated by space
			    //getWordFrequency(String word, String[] list); //used by other methods
			    wc.writeToFile(list); // writes output
			    
			    
			  }catch(Exception e){System.out.println(e);}    

		 
		}    
	
}
