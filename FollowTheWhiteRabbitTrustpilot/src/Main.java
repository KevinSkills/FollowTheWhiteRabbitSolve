import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.ArrayList;

public class Main {
	static String anagram = "poultry outwits ants";
	static int aLength = 20;
	static String md1 = "23170acc097c24edb98fc5488ab033fe";
	static int[] letters;
	
	public static void main(String[] args) {
			

			letters = stringToIntArray(anagram);
			
			
			ArrayList<String> words = readViableWordsFromFile();
			
			System.out.println(words.size());
			
			isAnagram("poultryout");
			
			
			
			long time = System.nanoTime();
			getSentence(words);
			System.out.println((System.nanoTime() - time)/1000000000);

			
			
			
	}
	
	
	public static ArrayList<String> readViableWordsFromFile(){
		ArrayList<String> viableWords = new ArrayList<String>();
		String filename = "C:\\Users\\kevin\\Documents\\wordlist.txt";
		try {
			FileInputStream fileInput = new FileInputStream(filename);
			DataInputStream dataInput = new DataInputStream(fileInput);
			BufferedReader input = new BufferedReader( new InputStreamReader(dataInput));
			String line; 
			
			
			while((line = input.readLine())!= null) {

				if(lettersAvailable(line) && !viableWords.contains(line)) {
					viableWords.add(line);
				}

			}
			
			input.close();

		} catch (Exception e) {
			System.out.println("notin: " + e.getMessage());
		}
		
		return viableWords;
	}
	
	
	
	
	
	
	public static void getSentence(ArrayList<String> words){
		int length = words.size();
		String w1;
		String w2;
		String message;
		
		
		for(int i = 0; i < length; i++) {
			w1 = words.get(i);
			System.out.println(w1);
			for (int j = 0; j < length; j++) {
				w2 = words.get(j);
				if(lettersAvailable(w1 + w2))
				for (int k = 0; k < length; k++) {
					message = w1+" " + w2 +" " + words.get(k);
					if(isAnagram(message)) {
						if(testMD5(message)) {
							System.out.println(message);
							return;
						}
					}
				}
			}
		}
	}
	

	public static int[] stringToIntArray(String s) {
		
		
		
		int[] in = new int[26];
		char[] arr = s.toCharArray();
		for(char c : arr) {
			if(c != ' ') in[c-'a']++;
		}
		
		return in;
		
	}
	
	public static boolean lettersAvailable(String w) {
		try {
			
			
			int[] in = stringToIntArray(w); // 100 nano
			int l = in.length;
			
			for(int i = 0; i < l; i++) {
				
				if(in[i] > letters[i]) return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
	public static boolean isAnagram(String w) {
		int[] in = stringToIntArray(w);
		int l = in.length;
		
		for(int i = 0; i < l; i++) {
			if(in[i] != letters[i]) return false;
		}
		return true;
		
	}
	
	public static boolean empty(int[] let) {
		for(int in : let) {
			if(in > 0) return false;
		}
		
		return true;
	}
	
	public static boolean testMD5(String password) {
		
		try {
			//Get the messageDigest object
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			
			//Convert to bytes
		    byte[] bytesForHash = md.digest(password.getBytes());
		    
		    //convert bytes using BigInteger and return
		    return md1.equals(new BigInteger(1, bytesForHash).toString(16));
		    
			
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
	    
		return false;
	}
	

}
