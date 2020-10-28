package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandling {

	public static int a[] = new int[12];

	public static void main(String args[]) {

		try {
			// enter the path and dirname from keyboard
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter dirpath:");
			String dirpath = br.readLine();
			System.out.println("Enter the dirname");
			String dname;

			dname = br.readLine();

			// create File object with dirpath and dname
			File f = new File(dirpath, dname);

			// if directory exists,then
			if (f.exists()) {

				countFilesPerMonth(f);
			} else {
				System.out.println("Kuch galat h bhai");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void countFilesPerMonth(File directory) {

		File listOfFiles[] = directory.listFiles();

		for (File file : listOfFiles) {

			createdMonth(file);
			System.out.println("after calling created month method");
		}
		displayOutput();
	}

	public static void createdMonth(File file) {

		try {

			Path file1 = Paths.get(file.getPath());

			BasicFileAttributes attr = Files.readAttributes(file1, BasicFileAttributes.class);

			// System.out.println("creationTime: " + attr.creationTime());

			long time = attr.creationTime().toMillis();

			ZonedDateTime t = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"));
			String dateCreated = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(t);
			// System.out.println(dateCreated);

			String s = dateCreated.substring(0, 2);

			// System.out.println(s);

			Integer temp = new Integer(s);

			int index = temp.intValue() - 1;

			a[index] = a[index] + 1;

			// System.out.println(a[index]);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public static void displayOutput() {
		for (int i = 0; i <= 11; i++) {
			System.out.println((i+1)+" -> "+a[i]);
		}
	}

}
