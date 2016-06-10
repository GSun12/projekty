package projektJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Switch {
	Switch() throws IOException{
		String name="daneJava.csv";
		int exit=1,war,war2,id;
		String country,category,condition;
		double value;
		Scanner get_user = new Scanner(System.in);
		BufferedReader stri = new BufferedReader(new InputStreamReader(System.in));

		while(exit==1){
			System.out.println("\nCo chcesz zrobic?");
			System.out.println("\nWczytaj dane do bazy i oblicz bilans wciśnij 1");
			System.out.println("\nWyswietl interesjące cię dane wciśnij 2");
			System.out.println("\nUsuń interesjące cię dane wciśnij 3");
			System.out.println("\nChcesz zakończyć program wciśnij 4");
			System.out.println("\nTwój wybór to : ");

			war=get_user.nextByte();
			switch(war){
				case 1:{
					read r=new read();
					count_balance cb=new count_balance();
					r.openFile(name);
					cb.count();
					break;
				}
				case 2:{
					System.out.println("\nWyswietl wszystkie dane z bazy wcisnij 1");
					System.out.println("\nWyswietl dla :");
					System.out.println("\nkonkretnego id wcisnij 2");
					System.out.println("\nkonkretnego kraju 3");
					System.out.println("\nkonkretnej produkcji 4");
					System.out.println("\nkonkretnej konsumpcji 5");
					System.out.println("\nkonkretnego bilansu 6");
					System.out.println("\nSortuj dane 7");
					System.out.println("\npowrot 8");
					System.out.println("\nTwoj wybor to : ");
					war2=get_user.nextInt();
					switch(war2){
						case 1:{
							dbcon sel=new dbcon();
							sel.selectLista();
							sel.closeConnection();
							continue;
							}
						case 2:{
							System.out.println("\npodaj id");
							id=get_user.nextInt();
							dbcon sel=new dbcon();
							sel.selectFromId(id);
							sel.closeConnection();
							continue;
						}
						case 3:{
							System.out.println("\npodaj nazwe kraju zaczynajc od duzej litery");
							country= stri.readLine();
							dbcon sel=new dbcon();
							sel.selectFromCou(country);
							sel.closeConnection();
							continue;
						}
						case 4:{
							System.out.println("\npodaj liczbe wyprodukowanych kWh");
							value=get_user.nextDouble();
							dbcon sel=new dbcon();
							sel.selectFromProdConsBal(value,"Production");
							sel.closeConnection();
							continue;
						}
						case 5:{
							System.out.println("\npodaj liczbe zuzytych kWh");
							value=get_user.nextDouble();
							dbcon sel=new dbcon();
							sel.selectFromProdConsBal(value,"Consumption");
							sel.closeConnection();
							continue;
						}
						case 6:{
							System.out.println("\npodaj liczbe bilans kWh");
							value=get_user.nextDouble();
							dbcon sel=new dbcon();
							sel.selectFromProdConsBal(value,"Balance");
							sel.closeConnection();
							continue;
						}
						case 7:{
							System.out.println("Jeśli chcesz posortować dane wpisz jedną z kategorii :");
							System.out.println("Country,Production,Consumption,Balance");
							category=stri.readLine();
							System.out.println("Dane sortujemy rosnąco wpisz r malejąco m :");
							condition=stri.readLine();
							if(condition=="r"){
								condition="ASC";
								dbcon sel=new dbcon();
								sel.selectFromDESCASC(condition,category);
								sel.closeConnection();

							}
							else{
								condition="DESC";
								dbcon sel=new dbcon();
								sel.selectFromDESCASC(condition,category);
								sel.closeConnection();

							}
							continue;
						}
						case 8:{
							continue;
						}
						default:{
							System.out.println("\nWyswietl wszystkie dane z bazy wcisnij 1");
							System.out.println("\nWyswietl dla :");
							System.out.println("\nkonkretnego id wcisnij 2");
							System.out.println("\nkonkretnego kraju 3");
							System.out.println("\nkonkretnej produkcji 4");
							System.out.println("\nkonkretnej konsumpcji 5");
							System.out.println("\nkonkretnego bilansu 6");
							war2=get_user.nextInt();
						}
					}
				}
				case 3:{
					System.out.println("\nUsuń wszystkie dane wraz z bazą danych wciśnij 1");
					System.out.println("\nUsuń konkretny rekord po id wcisnij 2");
					System.out.println("\npowrot 3");
					war2=get_user.nextInt();
					switch(war2){
						case 1:{
							deleteFile del =new deleteFile();
							del.delete();
							continue;
						}
						case 2:{
							System.out.println("\nPodaj ID kraju :");
							id=get_user.nextInt();
							dbcon del=new dbcon();
							del.deleteRecord(id);
							del.closeConnection();
							continue;
						}
						case 3:{
							continue;
						}
						default:{
							System.out.println("\nUsuń wszystkie dane wraz z bazą danych wciśnij 1");
							System.out.println("\nUsuń konkretny rekord po id wcisnij 2");
							System.out.println("\npowrot 7");
							war2=get_user.nextInt();
						}
					}

				}
				case 4:{
					exit=0;
					break;
				}
				default:{
					System.out.println("\nWyswietl wszystkie dane z bazy wcisnij 1");
					System.out.println("\nWyswietl dla :");
					System.out.println("\nkonkretnego id wcisnij 2");
					System.out.println("\nkonkretnego kraju 3");
					System.out.println("\nkonkretnej produkcji 4");
					System.out.println("\nkonkretnej konsumpcji 5");
					System.out.println("\nkonkretnego bilansu 6");
					war2=get_user.nextInt();
				}

			}
		}
		System.out.println("Do widzenia!");
		get_user.close();
		stri.close();
	}
}

