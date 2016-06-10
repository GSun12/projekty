#include <iostream>
#define n 7
using namespace std;
void DFS(int);
  	bool is_visited [n];
	int road [n];
    int top;
//    int graph[n][n]={  //1  2  3  4  5
//                        {0, 1, 0, 0, 0},
//                        {0, 0, 0, 1, 1},
//                        {1, 1, 0, 0, 0},
//                        {0, 0, 1, 0, 0},
//                        {1, 0, 0, 1, 0},
//	};
//    int graph[n][n]={  //1  2  3  4  5  6
//						{0, 1, 1, 0, 0, 0},
//	                    {0, 0, 0, 1, 1, 0},
//	                    {0, 0, 0, 1, 0, 1},
//	                    {1, 0, 0, 0, 0, 0},
//	                    {0, 0, 0, 1, 0, 0},
//	                    {0, 1, 1, 0, 0, 0},
//	};
//	int graph[n][n]={  //1  2  3  4  5  6
//                        {0, 1, 0, 0, 1, 0},
//                        {0, 0, 1, 0, 0, 0},
//                        {0, 0, 0, 1, 0, 1},
//                        {1, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 0, 0, 1},
//                        {1, 0, 0, 1, 0, 0},	
//	};

  	int graph[n][n]={  //1  2  3  4  5  6  7
                        {0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1, 0},
                        {0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 1, 0, 0, 1},
						{0, 0, 1, 0, 0, 0, 0},	
	};  
	void Initialize(int v) {
		top=v;
        for(int i=0;i<n;i++)
            is_visited[i]=false;
        DFS(v);
    }
 	void DFS(int v) {
        is_visited[v] = true;
        road[top] = v + 1;
        top++;

        for (int i = 0; i < n; i++)
            if (graph[v][i] !=0 && !is_visited[i])
                DFS(i);
    }


int main(int argc, char** argv) {
		Initialize(0);
		for(int i=0;i<n;i++)
			cout<<road[i]<<" ";
	return 0;
}
