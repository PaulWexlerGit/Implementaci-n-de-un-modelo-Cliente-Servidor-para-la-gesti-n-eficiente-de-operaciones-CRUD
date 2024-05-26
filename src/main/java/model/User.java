package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // primary key
	@Column(name = "name")
	private String name; // name
	@Column(name = "surname")
	private String surname; // surname
	@Column(name = "email", unique = true)
	private String email; // email de usuario para iniciar sesion
	@Column(name = "password")
	private String password; // contraseña para iniciar sesion
	@Column(name = "admin")
	private boolean admin; // true si es admin, false si no es admin

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // uno a muchos con User
	private List<Loan> loans = new ArrayList<>();

	public User() {
	}

	public User(String name, String surname, String email, String password, boolean admin, List<Loan> loans) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.admin = admin;
		this.loans = loans;
	}

	public User(String name, String surname, String email, String password, boolean admin) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", email='" + email
				+ '\'' + ", password='" + password + '\'' + ", admin=" + admin + ", loans=" + loans + '}';
	}
}