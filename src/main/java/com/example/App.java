package com.example;


import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
import com.example.service.TxnService;
import com.example.service.TxnServiceImpl;
import java.util.Scanner; 

public class App {

	public static void main(String[] args) {

		
		
     AccountRepository  accountRepository = new JdbcAccountRepository();
		TxnService t = new TxnServiceImpl(accountRepository);
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter from account number");
		String account1=sc.nextLine();
		
		System.out.println("enter to account number");
		String account2=sc.nextLine();
		
		System.out.println("enter amount you want to transfer:");
		double amt=sc.nextDouble();

		t.transfer(amt, account1, account2);
		
	}
}