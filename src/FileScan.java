import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileScan {

        public static void main(String[] args) {
            int numberOfLines = 0;
            int numberOfWords = 0;
            int numberOfChars = 0;
            String line = "";

            Scanner inFile;

            if (args.length > 0) {

            File selectedFile = new File("src", args[0]);

            if (!selectedFile.exists() || !selectedFile.isFile()) {

                System.out.println("File not found, or the File does not exist");

            }
            else {
                try {
                    System.out.println("File " + selectedFile.getName() + " found");
                    inFile = new Scanner(selectedFile);
                    while (inFile.hasNextLine()) {
                        line = inFile.nextLine();
                        System.out.println(line);
                        numberOfLines++;
                        for (String words : line.split(" ")) {
                            numberOfWords++;
                        }
                        numberOfChars = numberOfChars + line.length();


                    }
                    inFile.close();
                }
                catch (FileNotFoundException e) {
                    System.out.println("File Not Found Error");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("IOException Error");
                    e.printStackTrace();
                }


            }

            }
            else
            {
                JFileChooser chooser = new JFileChooser();
                Path target = new File(System.getProperty("user.dir")).toPath();
                target = target.resolve("src");
                chooser.setCurrentDirectory(target.toFile());

                try {

                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        target = chooser.getSelectedFile().toPath();
                        inFile = new Scanner(target);

                        while (inFile.hasNextLine()) {
                            line = inFile.nextLine();
                            System.out.println(line);
                            numberOfLines++;
                            for (String words : line.split(" ")) {
                                numberOfWords++;
                            }
                            numberOfChars = numberOfChars + line.length();


                        }
                        System.out.println("File " + target.getFileName() + " successfully read!");
                        inFile.close();

                    } else {
                        System.out.println("You did not select a file, process terminating");
                        System.exit(0);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found Error");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("IOException Error");
                    e.printStackTrace();
                }
            }

            System.out.println("The text file has " + numberOfLines + " lines");
            System.out.println("The text file has " + numberOfWords + " words");
            System.out.println("The text file has " + numberOfChars + " characters");



        }

}