import java.io.IOException;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("В файле должен содержаться массив а*b символов с крайними символами X, где X - стены, o - пустота(по ним можно ходить)" );
        char[][] data = readData("lybrint.txt",8,8);

        int gor = 8;
        int ver = 8;
        int tochkastarta = 0;
        int tochkakonca = 0;

        boolean[][] visited = new boolean[ver][gor];

        Graph graph1 = new Graph(ver,gor);
        for (int b = 0; b<ver;b++){
            for (int a = 0; a<gor; a++){
                graph1.addVertex(data[b][a]);
                visited[b][a]=false;
                if (data[b][a] == 'b'){
                    tochkakonca = ver*b+a;
                }
                if (data[b][a] == 'a'){
                    tochkastarta = ver*b+a;
                }
            }
        }
        //все вершины
//        for (int i = 0; i<(gor*ver);i++){
//                System.out.print(graph1.vertexlist[i].name +""+ i);
//            System.out.println(" ");
//        }

//        for (int i = 0;i<ver;i++){
//            for (int y = 0;y<ver;y++){
//                System.out.println(data[i][y]+" "+ i + " " + y);
//
//            }
//        }



        for (int i = 1 ; i<(ver-1);i++){
            for (int y = 1; y<(gor-1);y++){
                int numver = ver*i+y;
                visited[i][y] = true;
                //проверка ребер между собой
                if (data[i][y]=='o' && data[i-1][y]=='o' && visited[i-1][y]==false){
                    graph1.addEdge(numver,(ver*(i-1)+y),1);
                }
                if (data[i][y]=='o' && data[i+1][y]=='o' && visited[i+1][y]==false){
                    graph1.addEdge(numver,(ver*(i+1)+y),1);}

                if (data[i][y]=='o' && data[i][y-1]=='o' && visited[i][y-1]==false){
                    graph1.addEdge(numver,(ver*i+y-1),1);}

                if (data[i][y]=='o' && data[i][y+1]=='o' && visited[i][y+1]==false){
                    graph1.addEdge(numver,(ver*i+y+1),1);}
                //---------------------------------------------------------------------
                //проверка на точку начала рядом
                if (data[i][y]=='a' && data[i-1][y]=='o'){
                    graph1.addEdge(numver,(ver*(i-1)+y),1);
                }
                if (data[i][y]=='a' && data[i+1][y]=='o'){
                    graph1.addEdge(numver,(ver*(i+1)+y),1);
                }

                if (data[i][y]=='a' && data[i][y-1]=='o'){
                    graph1.addEdge(numver,(ver*i+y-1),1);}

                if (data[i][y]=='a' && data[i][y+1]=='o'){
                    graph1.addEdge(numver,(ver*i+y+1),1);}

                //---------------------------------
                if (data[i][y]=='b' && data[i-1][y]=='o'){
                    graph1.addEdge(numver,(ver*(i-1)+y),1);
                }
                if (data[i][y]=='b' && data[i+1][y]=='o'){
                    graph1.addEdge(numver,(ver*(i+1)+y),1);
                }

                if (data[i][y]=='b' && data[i][y-1]=='o'){
                    graph1.addEdge(numver,(ver*i+y-1),1);

                }

                if (data[i][y]=='b' && data[i][y+1]=='o'){
                    graph1.addEdge(numver,(ver*i+y+1),1);
                 }
            }

            }
        graph1.passInWidth(tochkastarta);
//        graph1.reserchputi(); // Узнать состояние пройденных путей (-1 - в точку не приходили)
        graph1.naitiputi(tochkastarta,tochkakonca);
        graph1.writeAnswer();

        }



    // Создание массива на основе полученого файла(входной файл 8х8 символов)
    public static char[][] readData (String FileName, int a, int b) {
        char[][] backer = new char[a][b];
        BufferedReader lybrith = null;

        try {

            lybrith = new BufferedReader(new FileReader(FileName));
            String Line;
            for (int i = 0; i<a; i++){
                Line = lybrith.readLine();
                String br = Line;
                char[] srt = br.toCharArray();

                backer[i] = srt;
            }
                }
        catch (IOException e){
            System.out.println("Error:" + e);
        } finally {
            try{lybrith.close();}
            catch (IOException e){
                System.out.println("Error:" + e);
            }
        }
        return backer;
    }
}