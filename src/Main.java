import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final int COLS = 67;
        final int ROWS = 24;
        String[] codes = new String[ROWS];
        String[] cutCodes = new String[ROWS];
        int[] intCodes = new int[ROWS];
        char[] keyNames = "abcdefghijklmnopqrstuvwx".toCharArray();
        String[] keyOrder = {"red","redorange","orange","yellow","yellowgreen","green","bgreen","aqua","teal","darkteal","blue","indigo","purple","magenta","pink","white",
        "flash","strobe","fade","smooth","brightup","brightdown","Off","On"};
        String[] hexCodes = new String[ROWS];

        KeyArray ka = KeyArray.getInstance(COLS,ROWS);

        try {
            //get file of codes
            Scanner file = new Scanner(new File("C:\\develop\\git-source\\decodeIR\\src\\data.txt"));

            //pass codes into KeyArray to fill it
            populate(file,ka);

            Key[] k = ka.getKeys();
            //create all the arrays
            for(int i = 0; i < ROWS; i++){
                codes[i] = decipher(k[i].getCode());
            }

            trimCodes(cutCodes, codes);
            toInt(cutCodes,intCodes);
            toHex(intCodes,hexCodes);

            sortAll(intCodes,hexCodes,cutCodes,keyNames);
            System.out.println();

            for(int i = 0; i < ROWS; i++){
                System.out.println(keyNames[i] + " " + cutCodes[i] + " " +  intCodes[i] + " " + hexCodes[i]);
            }



        } catch (FileNotFoundException e) {
            System.err.println("Error, quitting");
        }
    }
    //turn codes into binary
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
    //fill Key Array
    public static void populate(Scanner file, KeyArray ka){
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
    }
    //trim off the parts that every code has in common
    public static void trimCodes(String[] cutCodes, String[] codes){
        for(int i = 0; i < cutCodes.length; i++){
            if(codes[i].length() > 29) {
                cutCodes[i] = codes[i].substring(18, 31);
            }
        }
    }
    //turn the codes from binary into integers
    public static void toInt(String[] cutCodes, int[] intCodes){
        for(int i = 0; i < cutCodes.length; i++){
            if(cutCodes[i] != null) {
                intCodes[i] = Integer.parseInt(cutCodes[i],2);
            }
        }
    }
    //turn codes from int into Hex strings
    public static void toHex(int[] binaryCodes, String[] hexCodes){
        for(int i = 0; i < binaryCodes.length; i++){
            if(binaryCodes[i] != 0) {
                hexCodes[i] = Integer.toHexString(binaryCodes[i]);
            }
        }
    }

    //sorts all of the arrays at the same time so they in order
    public static void sortAll(int[] intCodes, String[] hexCodes, String[] cutCodes,char[] keyNames){

        int n = intCodes.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (intCodes[j] > intCodes[j+1])
                {
                    // swap temp and arr[i]
                    int temp = intCodes[j];
                    char kTemp = keyNames[j];
                    String hTemp = hexCodes[j];
                    String cTemp = cutCodes[j];



                    intCodes[j] = intCodes[j+1];
                    keyNames[j] = keyNames[j+1];
                    hexCodes[j] = hexCodes[j+1];
                    cutCodes[j] = cutCodes[j+1];


                    intCodes[j+1] = temp;
                    keyNames[j+1] = kTemp;
                    hexCodes[j+1] = hTemp;
                    cutCodes[j+1] = cTemp;
                }
        }
    }



