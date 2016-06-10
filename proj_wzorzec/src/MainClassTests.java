public class MainClassTests {

    public static void main(String[] args) {

//        char tekst[]={'a','s','d','a','f','a','f'};
//        char wzorzec[]={'d','a','f'};
//pobranie danych-------------------------------------------
        String FileName="text.txt";
        FileRead fileRead =new FileRead();
        //char wzorzec[]={'z','a','d','a','n'};
        char wzorzec[]={'g','c','a','t','g','g','a','a'};
        fileRead.openFile(FileName);
        char tekst[]=fileRead.readFile();
//dla algorytmy natywnego-------------------------------------
        long start_native=System.nanoTime();
        System.out.println("\tNative");
        Native n =new Native();
        n.Naiwny(wzorzec,tekst);
        long stop_native=System.nanoTime();
//dla KMP-----------------------------------------------------
        long start_kmp=System.nanoTime();
        System.out.println("\tKMP");
        KMP kmp =new KMP();
        kmp.kmp(tekst,wzorzec);
        long stop_kmp=System.nanoTime();
//dla KR------------------------------------------------------
        long start_kr=System.nanoTime();
        System.out.println("\tRK");
        RK kr = new RK();
        kr.rabin_karp(wzorzec,tekst,128,27077);
//------------------------------------------------------------
        long stop_kr=System.nanoTime();
        long time_native=stop_native-start_native;
        long time_kmp=stop_kmp-start_kmp;
        long time_kr=stop_kr-start_kr;
        long pom=Math.min(time_native,time_kmp);
        pom=Math.min(pom,time_kr);
        System.out.println();
        if(pom==time_native)
            System.out.println("\nNajszybszy byl nativ z czasem : "+pom);
        else if(pom==time_kmp)
            System.out.println("\nNajszybszy byl kmp z czasem : "+pom);
        else
            System.out.println("\nNajszybszy byl kr z czasem : "+pom);

        System.out.println("\nCzas dzialania native : "
            +(time_native)
                +"\nCzas dzialania kmp : "
                +(time_kmp)
                    +"\nCzas dzialania kr : "
                    +(time_kr)
        );

    }
}
