package library.controller;

import java.util.List;

import cliente.ClientDAO;
import model.Book;

public class BookController {

	@SuppressWarnings("unchecked")
	public static List<Book> getAllBooks() throws Exception {
		return (List<Book>) ClientDAO.readList("From Book");
	}

	public static void removeBook(Book book) throws Exception {
		ClientDAO.delete(book);
	}

	public static Book updateBook(Book book) throws Exception {
		return (Book) ClientDAO.update(book);
	}

}
