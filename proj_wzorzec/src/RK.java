/**
 * Created by gsun12 on 15.05.2016.
 */
public class RK {

    public static int modulo(int rozmiar_alpha, int rozmiar_wzorca, int lpierw) {
        int i;
        int result = 1;
        long x = rozmiar_alpha%lpierw;
        for (i=1; i<=rozmiar_wzorca; i<<=1) {
            x %= lpierw;
            if ((rozmiar_wzorca&i) != 0) {
                result *= x;
                result %= lpierw;
            }
            x *= x;
        }

        return result%lpierw;
    }



    public void rabin_karp(char wzorzec[],char tekst[],int rozmiar_alpha,int lpierw){
        int i,j,rm;
        int hash_tekst=0;
        int hash_wzorzec=0;
        System.out.print("Znaleziono wzorzec z przesunieciem : ");


        for (i=0; i<wzorzec.length; i++){
            hash_tekst=((hash_tekst*rozmiar_alpha) + tekst[i]);
            hash_tekst%= lpierw;
        }
        for (i=0; i<wzorzec.length; i++){
            hash_wzorzec=((hash_wzorzec*rozmiar_alpha) + wzorzec[i]);
            hash_wzorzec%= lpierw;
        }
        rm=modulo(rozmiar_alpha, wzorzec.length-1, lpierw);
        i=0;
        while (i<tekst.length-wzorzec.length){
            j=0;
            if (hash_wzorzec==hash_tekst)
                while ((j<wzorzec.length)&&(wzorzec[j]==tekst[i+j]))
                    j++;
            if (j==wzorzec.length)
                System.out.print(i+1);
            hash_tekst=hash_tekst-tekst[i]*rm;
            hash_tekst=hash_tekst*rozmiar_alpha;
            hash_tekst=hash_tekst+tekst[i+wzorzec.length];
            hash_tekst%= lpierw;
            if (hash_tekst<0)
                hash_tekst+= lpierw;
            i++;
        }
        j=0;
        if (hash_wzorzec==hash_tekst)
            while ((j<wzorzec.length)&&(wzorzec[j]==tekst[i+j]))
                j++;
        if (j==wzorzec.length)
            System.out.print(i+1);
    }

}
