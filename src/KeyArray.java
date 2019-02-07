/**
 * Created by Tyler on 2/7/2019.
 */
public class KeyArray {
    private static KeyArray instance;
    private Key[] keys;
    private int index;

    private KeyArray(int rows,int cols){
        index = 0;
        this.keys = new Key[rows];
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
    public void setKey(String name, String data){

    }
}
