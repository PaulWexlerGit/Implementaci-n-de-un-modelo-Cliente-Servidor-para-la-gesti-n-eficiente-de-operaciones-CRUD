package library.controller;

import java.util.List;

import cliente.ClientDAO;
import model.User;

public class UserController {

	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers() throws Exception {
		return (List<User>) ClientDAO.readList("From User");
	}

}
