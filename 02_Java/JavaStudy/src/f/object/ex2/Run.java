package f.object.ex2;

public class Run {
	public static void main(String[] args) {
		/*
		BankAccount ref2 = ref1;
		
		ref1.deposit(3000);
		ref2.deposit(2000);
		
		ref1.checkMybalance();
		ref2.checkMybalance();
		*/
		
		BankAccount ref1= new BankAccount();
		BankAccount ref2= new BankAccount();
		
		ref1.deposit(10000);
		ref1.withdraw(2000);
		
		ref2.checkMyBalance(ref1);
		
	}
}
