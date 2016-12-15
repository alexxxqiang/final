package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;
import org.hibernate.HibernateException;

import exceptions.RateException;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;


public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
ArrayList<RateDomainModel> alRates = new ArrayList<RateDomainModel>();
		
		double bestRate = 0;
		 for (RateDomainModel rat : alRates) {
			 if(GivenCreditScore > rat.getiMinCreditScore()){
				 bestRate = rat.getdInterestRate();
			 }
			 else{
				 return bestRate;
			 }
				if (bestRate < 0.0) {
					throw new RateException(rat);
				} else {
					return bestRate;
				}
	      }
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return Rate;
		
		
	}
	public boolean incomecheck(double income){
		LoanRequest lq = new LoanRequest();
		
		double PMT = Math.abs(lq.getdPayment());
		if ((PMT * 12 * 30 < (income - lq.getExpenses() * 12) * 30)
				|| PMT * 12 * 15 < (income - lq.getExpenses() * 12) * 15) {
			return true;
		}
		return false; 
	}

	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
