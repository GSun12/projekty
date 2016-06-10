package projektJava;

import java.io.File;

public class deleteFile {
	void delete(){
		try{

    		File del_file = new File("bazaJava.db");

    		if(del_file.delete()){
    			System.err.println("Bazę danych o nazwie " +del_file.getName() + " usunięto pomyślnie!");
    		}else{
    			System.err.println("Operacja usuwania została przerwana");
    		}

    	}catch(Exception e){

    		e.printStackTrace();

    	}
	}
}
