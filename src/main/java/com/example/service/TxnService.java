package com.example.service;

public interface TxnService {


	void transfer(double amount, String fromAcc, String toAcc);
}