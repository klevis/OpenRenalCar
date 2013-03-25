package ramo.klevis.national.ibuisnesslogic;

import ramo.klevis.openrental.entity.User;

public interface ILoginDao {

	User login(String username, String pass);

	User changePasswordUser(String username, String oldPassword, String password);

	User existUser(String username, String password);

	User existUsername(String username);

}
