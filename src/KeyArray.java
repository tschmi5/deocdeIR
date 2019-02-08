/**
 * Created by Tyler on 2/7/2019.
 */
public class KeyArray {
    private static KeyArray instance;
    private Key[] keys;
    private Key[] sortedKeys;

    private int index;

    private KeyArray(int rows,int cols){
        index = 0;
        this.keys = new Key[rows];
        this.sortedKeys = new Key[rows];
        for(int i = 0; i < keys.length; i++){
            keys[i] = new Key(cols);
        }
        System.out.println("Key Array initialized");
    }
    public static synchronized KeyArray getInstance(int rows,int cols){
        if(instance == null){
            instance = new KeyArray(rows,cols);
        }
        return instance;
    }
    public void setKey(String name, int[] data){
        keys[index].setName(name);
        keys[index].setCode(data);
        index++;
    }

    public Key[] getKeys() {
        return keys;
    }


    public void printKeys(){
        for(int i = 0; i < keys[0].length; i++){
            System.out.println(keys[i].name);
            keys[i].printCode();
            System.out.println();
        }
    }
}
