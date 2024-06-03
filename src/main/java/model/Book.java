package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // clave primaria
    @Column(name = "title")
    private String title; // nombre del libro
    @Column(name = "author")
    private String author; // autor
    @Column(name = "editorial")
    private String editorial; // editorial
    @Column(name = "isbn", unique = true)
    private String isbn; // ISBN del libro
    @Column(name = "lent", columnDefinition = "boolean default false")
    private boolean lent; // true si se ha prestado, false si no

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Loan> loans = new ArrayList<>();

    public Book() {
    }

    public Book(String title, String author, String editorial, String isbn, List<Loan> loans) {
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.isbn = isbn;
        this.loans = loans;
        this.lent = false;
    }

    public Book(String title, String author, String editorial, String isbn) {
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.isbn = isbn;
        this.lent = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public boolean isLent() {
        return lent;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + ", editorial='"
                + editorial + '\'' + ", isbn='" + isbn + '\'' + ", lent=" + lent + ", loans=" + loans + '}';
    }
}