import java.io.File;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

/* 
 * Example program that shows basic File operations
 */
public class FileExample {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("usage: java FileExample filepath");
			System.exit(0);
		}
		
		File file = new File(args[0]);
		
		boolean fileExists = file.exists();
		
		if(!fileExists) {
			System.out.println("This file doesn't exist.");
		}
		else {
			System.out.println("Absolute Path: " + file.getAbsolutePath());
			System.out.println("Is Directory: " + file.isDirectory());
			System.out.println("Parent Path:" + file.getParent());
			if(file.isFile()) {
				System.out.println("File size: " + file.length());
				// Note that lastModified is number of milliseconds into Epoch
				DateTimeFormatter medF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
				System.out.println("File last modified: " 
				+ medF.format(LocalDateTime.ofEpochSecond(file.lastModified()/1000, 0, OffsetDateTime.now().getOffset())));
				
			}
			else {
				for(File subFile: file.listFiles()) {
					System.out.println(subFile.getName());
				}
			}
		}

	}

}
