package file.handlers.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	
	private Scanner input;

	public FileReader() {
		
	}


	public int[] readKeys(File file) {
		int[] keys = null;
		try {
			input = new Scanner(file);
			String keysLine = input.nextLine();
			String[] splittedKeys = keysLine.split(",");
			keys = new int[splittedKeys.length];
			for (int i = 0; i < splittedKeys.length; i++) {
				keys[i] = Integer.parseInt(splittedKeys[i]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return keys;
	}

	public static void main(String[] args) {
		FileReader reader = new FileReader();
		int[] keys = reader.readKeys(new File("keys"));
		for (int key : keys) {
			System.out.println(key);
		}
	}

}
