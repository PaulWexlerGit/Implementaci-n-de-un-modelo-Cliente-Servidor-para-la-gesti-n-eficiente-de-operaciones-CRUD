package library.controller;

import cliente.ClientDAO;
import model.Book;
import model.Loan;

import java.sql.Date;
import java.util.List;

public class LoanController {

	@SuppressWarnings("unchecked")
	public static List<Loan> getAllLoans() throws Exception {
		return (List<Loan>) ClientDAO.readList("From Loan");
	}

	public static Loan create(Loan loan) throws Exception {
		return (Loan) ClientDAO.create(loan);
	}

	public static Loan getLoanByBook(Book book) throws Exception {
		return (Loan) ClientDAO
				.readObject("FROM Loan l where l.book.isbn ='" + book.getIsbn() + "' AND returnDate IS NULL");
	}

	public static Loan updateLoan(Loan loan) throws Exception {
		return (Loan) ClientDAO.update(loan);
	}

	public static void doLoan(Loan loan) throws Exception {
		ClientDAO.transaction();
		loan = LoanController.create(loan);
		loan.getBook().setLent(true);
		BookController.updateBook(loan.getBook());
		ClientDAO.commit();
	}

	public static void doReturn(Book book) throws Exception {
		Loan loan = getLoanByBook(book);
		ClientDAO.transaction();
		loan.setReturnDate(new Date(System.currentTimeMillis()));
		updateLoan(loan);
		loan.getBook().setLent(false);
		BookController.updateBook(loan.getBook());
		ClientDAO.commit();

	}
}
