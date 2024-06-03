package exec;

import cliente.ClientDAO;
import model.Book;
import model.Loan;
import model.User;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("hi world");
//        Book book1 = new Book("book1", "author1", "editorial1", "isbn1"); // crea un libro
//        try {
//            book1= (Book) ClienteDAO.create(book1); //crea el libro
//            book1.setEditorial("editorial 11");
//            ClienteDAO.update(book1); //no puede crear el mismo isbn dos veces
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        User user1 = new User("user1", "user1", "user1@user1", "user1", false); // crea un usuario
//        try {
//            user1=(User)ClienteDAO.create(user1); //crea el usuario
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
        User user1 = new User("user1", "user1", "user1@user1", "user1", false); // crea un usuario
        Book book1 = new Book("book1", "author1", "editorial1", "isbn1"); // crea un libro
        try {
            user1 = (User) ClientDAO.create(user1);
            book1 = (Book) ClientDAO.create(book1);
            Loan loan1 = new Loan(new java.sql.Date(System.currentTimeMillis()), null, "test", book1, user1); // crea un
            // prestamo
            ClientDAO.create(loan1); // crea el prestamo
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        User user2 = new User("test2", "test2", "test2", "test2", false); // crea otro usuario
//        Book book2 = new Book("book2", "author2", "editorial2", "isbn2"); // crea otro libro
//        Book book3 = new Book("book3", "author3", "editorial3", "isbn3"); // crea otro libro
//        Loan loan3 = new Loan(new java.sql.Date(System.currentTimeMillis()), null, "test", book3, user2); // crea otro prestamo con usuario y con libro
//        book3.getLoans().add(loan3);
//        user2.getLoans().add(loan2); //agrego el prestamo
//        user2.getLoans().add(loan3);
//        try {
//            ClienteDAO.create(user2); //crea el usuario
//            ClienteDAO.create(book2); //crea el libro
//            ClienteDAO.transaction(); //comienzo una transacción
//            User u2 = (User) ClienteDAO.readObject("FROM User WHERE email = '" + user2.getEmail() + "'"); //recupero el usuario
//            System.out.println(u2+"primero");
//            Book b2 = (Book) ClienteDAO.readObject("FROM Book WHERE isbn = '" + book2.getIsbn() + "'"); //recupero el libro
//            System.out.println(b2+"segundo");
//            Loan loan2 = new Loan(new java.sql.Date(System.currentTimeMillis()), null, "test", b2, u2); // crea otro prestamo con usuario y con libro
//            ClienteDAO.create(loan2); //crea el prestamo
//            System.out.println(loan2+"tercero");

//            ClienteDAO.create(book3); //crea el libro
//            ClienteDAO.create(user2); //crea el usuario
//            ClienteDAO.commit(); //termino la transacción
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        try {
//            ClienteDAO.create(user2); //crea el usuario
////            ClienteDAO.create(book2); //crea el libro
//            ClienteDAO.transaction(); //comienzo una transacción
//            User u2 = (User) ClienteDAO.readObject("FROM User WHERE email = '" + user2.getEmail() + "'"); //recupero el usuario
//            System.out.println(u2+"primero");
////            Book b2 = (Book) ClienteDAO.readObject("FROM Book WHERE isbn = '" + book2.getIsbn() + "'"); //recupero el libro
////            System.out.println(b2+"segundo");
//            Loan loan2 = new Loan(new java.sql.Date(System.currentTimeMillis()), null, "test"); // crea otro prestamo sin usuario y sin libro
//            loan2.setUser(u2);
////            ClienteDAO.create(loan2); //crea el prestamo
////            System.out.println(loan2+"tercero");
//            book2.getLoans().add(loan2);
//            ClienteDAO.create(book2); //crea el libro
//            ClienteDAO.commit(); //termino la transacción
//
//
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
    }
}
