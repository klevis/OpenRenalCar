package ramo.klevis.national.ibuisnesslogic;

import java.util.List;

import ramo.klevis.openrental.entity.User;

public interface IUserDao {

	User craeteUser(User user);

	User searchUser(String username);

	User modifyUser(User user);

	List<User> getAllUsers();

}
