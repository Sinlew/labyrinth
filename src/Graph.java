import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
public class Graph {
    private int maxN = 100;
    private int[][] mas;
    Vertex[] vertexlist;
    private int curN;
    private MySteck stack = new MySteck();

    private MyQueue queue = new MyQueue();

    private int[] puti = new int[100];
    private int[][] maswer ;

    private static char[][] answer;

    private int ver2; private int gor2;

    public Graph(int ver,int gor){
        vertexlist = new Vertex[maxN];
        mas = new int[maxN][maxN];
        curN = 0;
        maswer = new int[ver*gor][2];
        answer = new char[ver][gor];
        ver2 = ver; gor2 = gor;
        for(int i = 0;i<ver;i++){
            for(int y = 0;y<gor;y++){
                maswer[ver*i+y][0]=i;
                maswer[ver*i+y][1]=y;
                answer[i][y]='X';
            }
        }
    }

    public void newmax(int b){
        maxN=b;
    }

    public void masskord(int ver,int gor){
        int[][] maswer = new int[ver*gor][2];
        for(int i = 0;i<ver;i++){
            for(int y = 0;y<gor;y++){
                maswer[ver*i+y][0]=i;
                maswer[ver*i+y][1]=y;

            }
        }
    }
    public void addVertex(char name){
        vertexlist[curN++] = new Vertex(name);
    }

    public void addEdge(int start, int end, int val){
        mas[start][end]=1;
        mas[end][start]=val;
    }

    public int check(int v){
        for (int i = 0; i<curN; i++){
            if(mas[v][i] == 1 && vertexlist[i].isVisited == false){
                puti[i] = v;
                return i;
            }
        }
        return -1;
    }

    public void passInDeep(int index){
        System.out.println(vertexlist[index].name);
        vertexlist[index].isVisited = true;
        stack.push(index);

        while (!stack.isempty()){
            int neigh = check(stack.peek());

            if(neigh== -1){
                neigh = stack.pop();
            }
            else{
                System.out.println(vertexlist[neigh].name);
                vertexlist[neigh].isVisited = true;
                stack.push(neigh);

            }
        }

        for (int i = 0; i<curN;i++){
            vertexlist[i].isVisited = false;
        }


    }

    public void passInWidth(int indexstart){
        for(int i=0;i<maxN;i++){puti[i]=-1;}
//        System.out.println(vertexlist[indexstart].name);
        vertexlist[indexstart].isVisited = true;
        queue.insert(indexstart);

        int vertex;

        while (!queue.isEmpty()){
            int temp = queue.remove();

            while ((vertex = check(temp))!=-1){
//                System.out.println(vertexlist[vertex].name);
                vertexlist[vertex].isVisited = true;
                queue.insert(vertex);
            }
        }
        for (int i = 0; i<curN;i++){
            vertexlist[i].isVisited = false;
        }

    }
    public void searchInWidth(int indexstart){
        for(int i=0;i<maxN;i++){puti[i]=-1;}
//        System.out.println(vertexlist[indexstart].name);
        vertexlist[indexstart].isVisited = true;
        queue.insert(indexstart);

        int vertex;

        while (!queue.isEmpty()){
            int temp = queue.remove();

            while ((vertex = check(temp))!=-1){
//                System.out.println(vertexlist[vertex].name);
                vertexlist[vertex].isVisited = true;
                queue.insert(vertex);
            }
        }
        for (int i = 0; i<curN;i++){
            vertexlist[i].isVisited = false;
        }

    }
    public void reserchputi(){
        for(int i=0;i<100;i++){System.out.println(puti[i]+"- из какой точки пришли "+i+" -точка ");}
    }

    public void naitiputi(int start, int ends){
        int chel = ends;
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(start+" ("+ maswer[start][0]+" - по вертикали "+maswer[start][1] + " - По горизонтали"+")"+" - точка старта");
        System.out.println(ends+" ("+ maswer[ends][0]+" - по вертикали "+maswer[ends][1] + " - По горизонтали"+")"+" - точка конца");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("");
        while (chel!=start){
            System.out.println(chel+" ("+maswer[chel][0]+" - по вертикали "+maswer[chel][1]+ " - По горизонтали"+")");
            answer[maswer[chel][0]][maswer[chel][1]] = 'o';
            chel = puti[chel];
            if(chel == -1){
                System.out.println("Пути нет");
                break;
            }
        }
        System.out.println(start+" ("+maswer[start][0]+" - по вертикали "+maswer[start][1]+ " - По горизонтали"+") - начальная точка");
        answer[maswer[start][0]][maswer[start][1]] = 'a';
        answer[maswer[ends][0]][maswer[ends][1]] = 'b';
    }
    public void writeAnswer(){
        try{
            File rw = new File("Answer.txt");
            if(!rw.exists()){
                rw.createNewFile();
            }
            PrintWriter pw = new PrintWriter(rw);
            for (int i = 0; i<ver2; i++){
                for (int y = 0; y<gor2; y++){
                    pw.print(answer[i][y]);

                }
                pw.println("");

            }
            pw.close();

        } catch (IOException e){
            System.out.println("error:" + e );
        }

    }

}
