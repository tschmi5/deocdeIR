import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            final int COLS = 67;
            final int ROWS = 24;
            KeyArray ka = KeyArray.getInstance(COLS,ROWS);
            Scanner file = new Scanner(new File("C:\\develop\\git-source\\decodeIR\\src\\data.txt"));


            while(file.hasNextLine()){
                String token = file.nextLine();
                Scanner in = new Scanner(token);
                if(in.findInLine("name KEY_") != null){
                    String data = "";
                    String name = in.next();
                    System.out.println(name);
                    while(file.hasNextInt()){
                        data += file.nextInt() + " ";
                    }
                    ka.setKey(name,data);

                }

            }

        } catch (FileNotFoundException e) {
            System.err.println("Error, quitting");
        }
    }

    }

