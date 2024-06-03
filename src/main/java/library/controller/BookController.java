package library.controller;

import cliente.ClientDAO;
import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookController {

    @SuppressWarnings("unchecked")
    public static List<Book> getAllBooks() throws Exception {
        return (List<Book>) ClientDAO.readList("FROM Book");
    }

    public static void removeBook(Book book) throws Exception {
        ClientDAO.delete(book);
    }

    public static Book updateBook(Book book) throws Exception {
        return (Book) ClientDAO.update(book);
    }

    public static Book createBook(Book book) throws Exception {
        return (Book) ClientDAO.create(book);
    }

    public static ArrayList<Book> getAllFreeBooks() throws Exception {
        return (ArrayList<Book>) ClientDAO.readList("FROM Book WHERE lent = false");
    }

    public static void transaction() throws Exception {
        ClientDAO.transaction();
    }

    public static void commit() throws Exception {
        ClientDAO.commit();
    }

    public static ArrayList<Book> getAllLoanBooks() throws Exception {
        return (ArrayList<Book>) ClientDAO.readList("FROM Book WHERE lent = true");
    }

}
