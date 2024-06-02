package library.controller;

import java.util.List;

import cliente.ClientDAO;
import model.Book;
import model.Loan;

public class LoanController {

	@SuppressWarnings("unchecked")
	public static List<Loan> getAllLoans() throws Exception {
		return (List<Loan>) ClientDAO.readList("From Loan");
	}

	public static Loan create(Loan loan) throws Exception {
		return (Loan) ClientDAO.create(loan);
	}

	public static Loan getLoanByBook(Book book) throws Exception {
		return (Loan) ClientDAO.readObject("FROM Loan l where l.book.isbn ='" + book.getIsbn() + "' AND returnDate IS NULL");
	}

	public static Loan updateLoan(Loan loan) throws Exception {
		return (Loan) ClientDAO.update(loan);
	}

}
