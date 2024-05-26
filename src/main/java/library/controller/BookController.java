package library.controller;

import java.util.List;

import cliente.ClientDAO;
import model.Book;

public class BookController {

	@SuppressWarnings("unchecked")
	public static List<Book> getAllBooks() throws Exception {
		return (List<Book>) ClientDAO.readList("From Book");
	}

}
