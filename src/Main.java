import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            final int COLS = 67;
            final int ROWS = 24;
            String[] codes = new String[ROWS];
            KeyArray ka = KeyArray.getInstance(COLS,ROWS);
            Scanner file = new Scanner(new File("C:\\develop\\git-source\\decodeIR\\src\\data.txt"));


            while(file.hasNextLine()){
                String token = file.nextLine();
                Scanner in = new Scanner(token);
                if(in.findInLine("name KEY_") != null){
                    int[] data = new int[67];
                    int i = 0;
                    String name = in.next();
                    while(file.hasNextInt()){
                        data[i++] = file.nextInt();
                    }
                    ka.setKey(name,data);
                }
            }
            Key[] k = ka.getKeys();
            for(int i = 0; i < ROWS; i++){
                codes[i] = decipher(k[i].getCode());
                System.out.println(k[i].getName() + " " + codes[i]);
            }


        } catch (FileNotFoundException e) {
            System.err.println("Error, quitting");
        }
    }
    public static String decipher(int[] code){
        String decipred = "";
        for(int i = 0; i < code.length - 1; i++){
            if(code[i] > 9000 && code[i] < 10000){
                decipred += "+";
            } else if (code[i] > 4000 && code[i] < 5000){
                decipred += "-";
            } else if (code[i + 1] > 500 && code[i + 1] < 700 && i % 2 == 0){
                decipred += "0";
            } else if (code[i + 1] > 1600 && code[i + 1] < 1800 && i % 2 == 0){
                decipred += "1";
            }
        }
        return decipred;
    }

    }

