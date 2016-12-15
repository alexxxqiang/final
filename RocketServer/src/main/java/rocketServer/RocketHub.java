package rocketServer;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.HibernateException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketBase.RateDAL;
import rocketCode.Action;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			try{
			
				lq.setdRate( _RateBLL.getRate(lq.getiCreditScore()));
			}catch(RateException e){
				sendToAll(e);
				return;
			}
			try{
				 lq.getIncome();
			}catch(Exception e){
				sendToAll(lq);
				return;
			}
			try{
				_RateBLL.getPayment(lq.getdAmount(), lq.getiTerm(), lq.getdAmount(), 0.00, false);
			}catch(Exception e){
				return;
			}
			
			//	TODO - RocketHub.messageReceived

			//	You will have to:
			//	Determine the rate with the given credit score (call RateBLL.getRate)
			//		If exception, show error message, stop processing
			//		If no exception, continue
			//	Determine if payment, call RateBLL.getPayment
			//	
			//	you should update lq, and then send lq back to the caller(s)
			
			
			sendToAll(lq);
		}
	}
}
