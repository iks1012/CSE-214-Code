import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Ishan Sethi and Tiffany He on 10/12/2017.
 */
public class Graph {
    int V;
    LinkedList<Integer> adjListArray[];
    static boolean[] discovered;


    public Graph(int V) {
        this.V = V;
        adjListArray = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjListArray[i] = new LinkedList<>();
        }
        discovered = new boolean[V];
    }


    public void addEdge(int src, int dest) {
        src--;
        dest--;
        this.adjListArray[src].addFirst(dest);
        this.adjListArray[dest].addFirst(src);
    }

    public void printGraph() {
        for (int v = 0; v < V; v++) {
            System.out.print((v + 1) + "| Head");
            for (Integer pCrawl : adjListArray[v]) {
                System.out.print(" -> " + (pCrawl + 1));
            }
            System.out.println(" -> null");
        }
    }























    public void BFS(int s, boolean print, boolean notLookingForComponents) {
        if(notLookingForComponents){
            initSearch();
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();

        discovered[s] = true;
        queue.add(s);

        while (queue.size() != 0) {

            s = queue.poll();

            if(print){
                System.out.print((s+1) + " ");
            }

            Iterator<Integer> i = adjListArray[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!discovered[n]) {
                    discovered[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public void initSearch(){
        discovered = new boolean[V];
    }


    public void connectedComponents(boolean print){
        initSearch();
        int c = 0;
        for(int i = 0; i < V;i++){
            if(discovered[i]==false){
                c++;
                if(print){
                    System.out.printf("Component %d: ", c);
                }
                BFS(i, print, false);
                if(print){
                    System.out.println();
                }
            }
        }
    }

}
