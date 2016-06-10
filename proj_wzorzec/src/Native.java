/**
 * Created by gsun12 on 14.05.2016.
 */
public class Native {
    public void Naiwny(char wzorzec[],char tekst[]){
        int i,a=0;
        for(i=0;i<tekst.length-wzorzec.length;i++){
            a=0;
            while (a<wzorzec.length&&tekst[i+a]==wzorzec[a])
                a++;
            if(a==wzorzec.length)
                System.out.println("znalezione wystapienie wzorca od poz " + (i+1));
        }
        if(i==tekst.length)
            System.out.println("nie znaleziono podanego wzorca w tekscie");


//        for (i=0;i<(tekst.length-wzorzec.length);i++){
//                if (wzorzec[a] == tekst[i]) {
//                   // System.out.println(wzorzec[a]);
//                    a+=1;
//                    if(a==wzorzec.length) {
//                        System.out.println("znalezione wystapienie wzorca od poz " + (i-3));
//                        break;
//                    }
//                }
//                else a=0;
//
//        }
//        if(i==tekst.length)
//            System.out.println("nie znaleziono podanego wzorca w tekscie");
//
    }





}
