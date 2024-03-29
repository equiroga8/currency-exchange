package es.upm.dit.isst.webLab.servlets;

import java.io.IOException; 

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.decimal4j.util.DoubleRounder;

import Constants.Constants;
import es.upm.dit.isst.webLab.dao.ClientDAO;
import es.upm.dit.isst.webLab.dao.ClientDAOImplementation;
import es.upm.dit.isst.webLab.dao.TransactionDAO;
import es.upm.dit.isst.webLab.dao.TransactionDAOImplementation;
import es.upm.dit.isst.webLab.dao.WalletDAO;
import es.upm.dit.isst.webLab.dao.WalletDAOImplementation;
import es.upm.dit.isst.webLab.model.Client;
import es.upm.dit.isst.webLab.model.Transaction;
import es.upm.dit.isst.webLab.model.Wallet;

@WebServlet("/UnloadTransactionServlet")
public class UnloadTransactionServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ClientDAO cdao = ClientDAOImplementation.getInstance();
		TransactionDAO tdao = TransactionDAOImplementation.getInstance();
		
		Transaction transaction = new Transaction();
		
		String email = req.getParameter("email");
		String amount = req.getParameter("amount");
		String currencyString = req.getParameter("currency"); 
		int currency=1;
		switch(currencyString) {
		
		case "1":
			currency = 1;
			break;
		case "2":
			currency = 2;
			break;
		case "3":
			currency = 3;			
			break;
		case "4":
			currency = 4;
			break;
		case "5":
			currency = 5;
			break;
		case "6":
			currency = 6;
			break;
		case "7":
			currency = 7;
			break;	
	}
		if (Double.parseDouble(amount) == 0.0) {
			req.getSession().setAttribute( "correcto", true);
			getServletContext().getRequestDispatcher( "/ManageView.jsp" ).forward( req, resp );
			return;
		}
		String transactionType = req.getParameter("transactionType");
		
		Date date = new Date();
		Long timestamp = date.getTime();
		String timeString = timestamp.toString();
		
		Client client = cdao.read(email);
		Integer accountId = client.getAccount().getAccountID();
		String accountString = accountId.toString();
		String transactionId = timeString + accountString;
		
		transaction.setTransactionID(transactionId);
		transaction.setAmount(DoubleRounder.round(Double.parseDouble(amount), 2));
		transaction.setTransactionType(Integer.parseInt(transactionType));
		transaction.setCurrencyType(currency);
		transaction.setTransactionDate(date);
		transaction.setUser(client.getAccount());
				
		WalletDAO wdao = WalletDAOImplementation.getInstance();
		Wallet wallet = transaction.getUser().getWallet();

		if (transactionType.equals("1")) {
			
			Double withdrawAmount = transaction.getAmount();
			req.getSession().setAttribute( "client", client);
			Boolean correcto = true;
			
			switch(currency) {
			
				case Constants.CURRENCY_AUD:
					if (withdrawAmount > wallet.getAud()) {
						correcto = false;
						break;
					}
					tdao.create(transaction);
					wallet.setAud(wallet.getAud() - withdrawAmount);
					break;
				case Constants.CURRENCY_CAD:
					if (withdrawAmount > wallet.getCad()) {
						correcto = false;
						break;
					}
					tdao.create(transaction);
					wallet.setCad(wallet.getCad() - withdrawAmount);
					break;
				case Constants.CURRENCY_EUR:
					if (withdrawAmount > wallet.getEur()) {
						correcto = false;
						break;
					}
					tdao.create(transaction);
					wallet.setEur(wallet.getEur() - withdrawAmount);				
					break;
				case Constants.CURRENCY_GBP:
					if (withdrawAmount > wallet.getGbp()) {
						correcto = false;
						break;
					}
					tdao.create(transaction);
					wallet.setGbp(wallet.getGbp() - withdrawAmount);
					break;
				case Constants.CURRENCY_CHF:
					if (withdrawAmount > wallet.getSfr()) {
						correcto = false;
						break;
					}
					tdao.create(transaction);
					wallet.setSfr(wallet.getSfr() - withdrawAmount);
					break;
				case Constants.CURRENCY_USD:
					if (withdrawAmount > wallet.getUsd()) {
						correcto = false;
						break;
					}
					tdao.create(transaction);
					wallet.setUsd(wallet.getUsd() - withdrawAmount);
					break;
				case Constants.CURRENCY_JPY:
					if (withdrawAmount > wallet.getYen()) {
						correcto = false;
						break;
					}
					tdao.create(transaction);
					wallet.setYen(wallet.getYen() - withdrawAmount);		
					break;		
			}
			
			wdao.update(wallet);
			req.getSession().setAttribute( "correcto", correcto);
			getServletContext().getRequestDispatcher( "/TpvView.jsp" ).forward( req, resp );
		}
	}
	
	
}
