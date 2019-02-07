/**
 * Created by Tyler on 2/7/2019.
 */
public class Key {
    public String name;
    public int[] code;
    public int length;

    public Key(int cols){
        code = new int[cols];
        name = "";
        length = cols;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int[] code) {
        this.code = code;
    }

    public int[] getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void printCode(){
        for (int i : code){
            System.out.print(i + " ");
        }
    }
}
