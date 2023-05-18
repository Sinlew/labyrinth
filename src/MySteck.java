public class MySteck {
    private int size = 100;
    private int top;
    private int[] array;

    public MySteck() {
        array = new int[size];
        top = -1;
    }

    public MySteck(int size) {
        array = new int[size];
        top = -1;
    }
    public void push(int v){
        array[++top] = v;
    }

    public int pop(){
        return array[top--];
    }

    public int peek(){
        return array[top];
    }

    public boolean isempty(){
        if (top == -1){
            return true;
        }
        else{
            return false;
        }
    }
}
