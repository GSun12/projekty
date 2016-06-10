/**
 * Created by gsun12 on 14.05.2016.
 */
public class KMP {
    int[] prefix_fun(char wzorzec[],int dlTab){
        int pi[]=new int[dlTab];
        pi[0]=0;
        int k=0;
        int index=0;
        for(index=1;index<dlTab;index++){
            while((k>0)&&(wzorzec[k]!=wzorzec[index]))
                k=pi[k-1];
            if(wzorzec[k]==wzorzec[index])
                k+=1;
            pi[index]=k;
        }
//        for (int a:pi
//             ) {
//            System.out.println(a);
//        }
        return pi;
    }
    void kmp(char tekst[],char wzorzec[]){
        int pi[]=prefix_fun(wzorzec,wzorzec.length);
        int q=0;
        int pom=0;
        for(int i=0; i<tekst.length; i++){
            while(q>0 && tekst[i] != wzorzec[q])
                q = pi[q-1];
            if (tekst[i] == wzorzec[q])
                q+=1;
            if(q == wzorzec.length){

                System.out.println("znaleziono wzorzec z przesunieciem : "+(i-wzorzec.length + 2));
                q = pi[q-1];
            }

        }
        if(q!=pom)
            System.out.println("nie znaleziono wzorca");
    }



}
