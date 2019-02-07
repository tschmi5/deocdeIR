import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            final int COLS = 67;
            final int ROWS = 24;
            KeyArray ka = KeyArray.getInstance(COLS,ROWS);
            Scanner file = new Scanner(new File("data.txt"));

        } catch (FileNotFoundException e) {

        }
    }

    }

