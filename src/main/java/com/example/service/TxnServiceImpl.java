package com.example.service;


import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.example.entity.Account;
import com.example.entity.Txn;
import com.example.entity.TxnType;
import com.example.repository.AccountRepository;

public class TxnServiceImpl implements TxnService {

	private static Logger logger = Logger.getLogger("txr-system");

	private AccountRepository accountRepository;

	public TxnServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void transfer(double amount, String fromAcc, String toAcc) {

		logger.info("transaction intiated...");

		Account fromAccount = accountRepository.load(fromAcc);

		if (fromAccount == null) {
			logger.error("account not found " + fromAcc);
			throw new AccountNotFoundException(fromAcc);
		}

		Account toAccount = accountRepository.load(toAcc);

		if (toAccount == null) {
			logger.error("account not found " + toAcc);
			throw new AccountNotFoundException(toAcc);
		}

		if (fromAccount.getBalance() < amount) {
			logger.error("no enough balance " + fromAccount.getBalance());
			throw new AccountBalanceException(fromAccount.getBalance());
		}

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		accountRepository.update(fromAccount);
		accountRepository.update(toAccount);
		accountRepository.insert(fromAccount.getNum(),toAccount.getNum(),String.valueOf(amount));

		Txn debitTxn = new Txn(amount, LocalDateTime.now(), TxnType.DEBIT);
		debitTxn.setAccount(fromAccount);

		Txn creditTxn = new Txn(amount, LocalDateTime.now(), TxnType.CREDIT);
		creditTxn.setAccount(toAccount);

		// txnRepository.save(debitTxn)
		// txnRepository.save(creditTxn)

		logger.info("transaction success...");

	}

}