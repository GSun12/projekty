import java.io.IOException;

public class Main {
    private boolean is_visited[];
    private int road[];
    private int top;
    private int graph[][];
    
    public void Initialize(int data[][],int top) {
        this.graph = data;
        is_visited = new boolean[data.length];
        road = new int[data.length];
        this.top=top;
        for(int i=0;i<data.length;i++)
            is_visited[i]=false;
        DFS(top);
    }
    
    private void DFS(int v) {
        is_visited[v] = true;
        road[top] = v + 1;
        top++;

        for (int i = 0; i < graph.length; i++)
            if (graph[v][i] !=0 && !is_visited[i])
                this.DFS(i);
    }
    private void ShowResult(int graph[][],int top){
        Initialize(graph,top);

        for (int a : road)
            System.out.print(a + " ");
        System.out.println();
    }
    public static void main(String agrs[]) throws IOException {
        Main m = new Main();
        int [][]g1={   //1  2  3  4  5
                        {0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 1},
                        {1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0},
                        {1, 0, 0, 1, 0},

        };
        int [][]g2={  //1  2  3  4  5  6
                       {0, 1, 1, 0, 0, 0},
                       {0, 0, 0, 1, 1, 0},
                       {0, 0, 0, 1, 0, 1},
                       {1, 0, 0, 0, 0, 0},
                       {0, 0, 0, 1, 0, 0},
                       {0, 1, 1, 0, 0, 0},

        };
        int [][]g3={   //1  2  3  4  5  6
                        {0, 1, 0, 0, 1, 0},
                        {0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 1, 0, 0},

        };

        m.ShowResult(g1,0);
        m.ShowResult(g2,0);
        m.ShowResult(g3,0);
    }
}
