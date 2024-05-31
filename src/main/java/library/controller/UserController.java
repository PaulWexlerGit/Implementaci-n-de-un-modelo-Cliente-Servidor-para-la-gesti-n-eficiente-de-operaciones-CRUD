package library.controller;

import java.util.List;

import cliente.ClientDAO;
import model.User;

public class UserController {

	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers() throws Exception {
		return (List<User>) ClientDAO.readList("From User");
	}

	public static User createUser(User user) throws Exception {
		return (User) ClientDAO.create(user);
	}

	public static User updateUser(User user) throws Exception {
		return (User) ClientDAO.update(user);
	}

	public static void removeUser(User user) throws Exception {
		ClientDAO.delete(user);
	}

}
