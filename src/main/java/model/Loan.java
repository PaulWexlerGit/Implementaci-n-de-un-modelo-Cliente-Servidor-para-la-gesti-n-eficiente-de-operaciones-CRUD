package model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
public class Loan implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // clave primaria
	@Column(name = "date")
	private Date date; // fecha de prestamo
	@Column(name = "returnDate")
	private Date returnDate; // fecha de devolución
	@Column(name = "comment")
	private String comment; // comentario

	@ManyToOne
	@JoinColumn(name = "bookID")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	public Loan() {
	}

	public Loan(Date date, Date returnDate, String comment, Book book, User user) {
		this.date = date;
		this.returnDate = returnDate;
		this.comment = comment;
		this.book = book;
		this.user = user;
		this.book.setLent(true);
	}

	public Loan(Date date, Date returnDate, String comment) {
		this.date = date;
		this.returnDate = returnDate;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Loan{" + "id=" + id + ", date='" + date + '\'' + ", returnDate='" + returnDate + '\'' + ", comment='"
				+ comment + '\'' + ", book=" + book + ", user=" + user + '}';
	}
}
