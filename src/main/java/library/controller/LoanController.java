package library.controller;

import java.util.List;

import cliente.ClientDAO;
import model.Loan;

public class LoanController {

	@SuppressWarnings("unchecked")
	public static List<Loan> getAllLoans() throws Exception {
		return (List<Loan>) ClientDAO.readList("From Loan");
	}

}
