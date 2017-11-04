import java.io.*;
import java.util.ArrayList;
/**
 * Created by Ishan Sethi and Tiffany He on 10/12/2017.
 */
public class Driver {


    static String fileName = "file4.txt";
    static int numberOfEdges = -1;
    static int numberOfVertices = -1;
    static ArrayList vertices = new ArrayList();

    public static void main(String[] args){

        examineFile("file1.txt");
        examineFile("file2.txt");
        examineFile("file3.txt");
        examineFile("file4.txt");
    }

    public static void examineFile(String fileName){
        Graph graph = null;
        File file = new File(fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            int i = 0;
            for(String line; (line = br.readLine()) != null; i++) {
                //It is the First line
                if(i == 0){
                    numberOfEdges = Integer.parseInt(line);
                }
                else if(i == 1){
                    numberOfVertices = Integer.parseInt(line);
                    graph = new Graph(numberOfVertices);
                }
                //Add Edge
                else{
                    int src = Integer.parseInt(line.substring(0,line.indexOf(" ")));
                    int dest = Integer.parseInt(line.substring(line.indexOf(" ")+1));
                    graph.addEdge(src, dest);
                }
            }
            // line is not visible here.
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
        catch(IOException ioe){
            System.out.println("File Not Found");
        }

        System.out.println("File: "+fileName);
        graph.connectedComponents(true);
    }
}
