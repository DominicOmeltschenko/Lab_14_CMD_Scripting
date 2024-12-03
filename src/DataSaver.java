import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {


        public static void main(String[] args)
        {
            Scanner pipe = new Scanner(System.in);
            ArrayList<String> recs = new ArrayList<>();
            String fileName = "";
            boolean finished = false;
            String firstName = "";
            String lastName = "";
            String idNumber = "";
            String yourEmail = "";
            String yearOfBirth = "";

            do {
                firstName = SafeInput.getNonZeroLenString(pipe, "What is your first name");
                lastName = SafeInput.getNonZeroLenString(pipe, "What is your last name");
                idNumber = SafeInput.getRegExString(pipe, "What is your ID number (000001, 000002, etc.)","^\\d{6}$");
                yourEmail = SafeInput.getRegExString(pipe, "What is your email", "^[^@]+@[^@]+\\.[^@]+$");
                yearOfBirth = SafeInput.getRegExString(pipe, "What is your birth year (write 4 digits, ie 1888, 0057)", "^\\d{4}$" );
                recs.add(firstName + ", " + lastName + ", " + idNumber + ", " + yourEmail + ", " + yearOfBirth);
                finished = SafeInput.getYNConfirm(pipe, "Would you like to finish entering values (y/n)");

            }while (!finished);

            fileName = SafeInput.getNonZeroLenString(pipe, "What would you like to name your file");

            File workingDirectory = new File(System.getProperty("user.dir"));
            Path file = Paths.get(workingDirectory.getPath() + "\\src" + "\\" + fileName + ".csv");

            try
            {

                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));


                for(String rec : recs)
                {
                    writer.write(rec, 0, rec.length());
                    writer.newLine();

                }
                writer.close();
                System.out.println("Data file written!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }


}
