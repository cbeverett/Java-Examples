/*
 * This class demonstrates Java IO
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	private char type;
	
	public Animal(String name, int age, char type) {
		this.name = name;
		this.age = age;
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public char getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return String.format("Animal [name=%s, age=%d, type=%c]", name, age, type);
	}
}

public class IOStreamExamples {
	
	public static final String fileSep = File.separator;

	public static void main(String[] args) {
		copyFileWithFileInputStream();
		copyFileWithBufferedFileInputStream();
		copyFileWithBufferedReader();
		objectStreamExample();
		consoleExample();
	}

	private static void copyFileWithFileInputStream() {
		File source = new File("./resources/sample.txt".replace("/", fileSep));
		File dest = new File("./samplecopy.txt".replace("/", fileSep));
		
		try (InputStream in = new FileInputStream(source); 
				OutputStream out = new FileOutputStream(dest)) {
			int b;
			while((b=in.read()) != -1) {
				out.write(b);
			}
			
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		System.out.println("Copied file.");
		
		cleanUp();
		
		
	}
	
	private static void cleanUp() {
		File copyFile = new File("./samplecopy.txt".replace("/", fileSep));
		if(copyFile.exists()) {
			copyFile.delete();
		}
		File objFile = new File("animal.data");
		if(objFile.exists()) {
			objFile.delete();
		}
	}
	
	private static void copyFileWithBufferedFileInputStream() {
		File source = new File("./resources/sample.txt".replace("/", fileSep));
		File dest = new File("./samplecopy.txt".replace("/", fileSep));
		
		try (InputStream in = new BufferedInputStream(new FileInputStream(source)); 
				OutputStream out = new BufferedOutputStream(new FileOutputStream(dest))) {
			byte[] buffer = new byte[1024];
			int lengthRead;
			while((lengthRead = in.read(buffer)) > 0) {
				out.write(buffer,0,lengthRead);
				out.flush();
			}
			
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		System.out.println("Copied file.");
		
		cleanUp();
		
	}
	
	private static void copyFileWithBufferedReader() {
		File source = new File("./resources/sample.txt".replace("/", fileSep));
		File dest = new File("./samplecopy.txt".replace("/", fileSep));
		
		List<String> data = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			String s;
			while ((s = reader.readLine())!=null) {
				data.add(s);
			}
			
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
			for(String s : data) {
				writer.write(s);
				writer.newLine();
			}
			
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		System.out.println("Copied file.");
		
		cleanUp();
		
	}
	

	private static void objectStreamExample() {
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal("Tony the Tiger", 5, 'T'));
		animals.add(new Animal("Tux the Pinguin", 17, 'P'));
		File dataFile = new File("animal.data");
		
		// Write the list to disk
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
			for(Animal animal: animals) {
				out.writeObject(animal);
			}
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// Read the objects back
		List<Animal> animalsFromDisk = new ArrayList<>();
		try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
			try {
				while(true) {
					Object object = in.readObject();
					if(object instanceof Animal) {
						animalsFromDisk.add((Animal)object);
					}
				}
			}
			catch(EOFException e) {
				// File end reached
			}
		
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} 

		System.out.println(animalsFromDisk);
		
		cleanUp();
		
	}

	private static void consoleExample() {
		Console console = System.console();
		
		if(console != null) {
			console.writer().println("Type something and press enter...");
			String userInput = console.readLine();
			console.writer().println("You typed: " + userInput);
		}
		else {
			System.out.println("Text interactions not supported.");
		}
		
	}

}
