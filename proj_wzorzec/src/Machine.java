/**
 * Created by gsun12 on 14.05.2016.
 */
public class Machine {
   public void FA_Matcher(char tekst[],int przejscia[][],int rozmiar_wzorca){
        int stan_aut=0;
        int i;
        for(i=0;i<tekst.length;i++){
            stan_aut=przejscia[stan_aut][tekst[i]];
            if(stan_aut==rozmiar_wzorca)
                System.out.println("znalezione wystapienia wzorca od poz "+(i-rozmiar_wzorca));
        }
    }
    public void Transition(char wzorzec[],char alpha[]){


    }




}
