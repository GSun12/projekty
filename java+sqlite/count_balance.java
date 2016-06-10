package projektJava;



public class count_balance {
	void count(){
		int i, max;
		double production=0,consumption=0,balance=0;
		dbcon get_datadb = new dbcon();
		max=get_datadb.max_id();
		System.out.println("licze bilans energetyczny to moze chwile potrwac...");
			for(i=1;i<=max;i++){
				production=get_datadb.get_balance_data_prod(i);
				consumption=get_datadb.get_balance_data_cons(i);
				balance=production-consumption;
				get_datadb.updateBaldb(i, balance);
			}
			System.out.println("bilans policzony");
			get_datadb.closeConnection();
	}

}