package ramo.klevis.openrental.entity;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.osgi.PersistenceProvider;

public class EntityManagerQuery {

	private static EntityManager entityManager;
	static EntityManagerFactory createEntityManagerFactory;

	
	public ClassLoader getMe(){
		return  EntityManagerQuery.this.getClass().getClassLoader();
	}
 	public static EntityManager getEntityManager() {

		if (entityManager == null) {
			String string = "NationalRentCar";
			HashMap hashMap = new HashMap();
//			hashMap.put(PersistenceUnitProperties.JDBC_DRIVER,
//					"com.mysql.jdbc.Driver");
//			hashMap.put(PersistenceUnitProperties.JDBC_USER, "root");
//			hashMap.put(PersistenceUnitProperties.JDBC_PASSWORD, "kl");
//			hashMap.put(PersistenceUnitProperties.JDBC_URL,
//					"jdbc:mysql://localhost:3306/openrental");
//			// hashMap.put(PersistenceUnitProperties.ec, "");
//			hashMap.put(TRANSACTION_TYPE,
//					PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
//			hashMap.put(LOGGING_LEVEL, "FINE");
//			hashMap.put(LOGGING_TIMESTAMP, "false");
//			hashMap.put(LOGGING_THREAD, "false");
//			hashMap.put(LOGGING_SESSION, "false");
//
//			// Ensure that no server-platform is configured
//			hashMap.put(TARGET_SERVER, TargetServer.None);
			
			hashMap.put(PersistenceUnitProperties.CLASSLOADER, new EntityManagerQuery().getMe());
			createEntityManagerFactory = new PersistenceProvider()
					.createEntityManagerFactory(string, hashMap);

			entityManager = createEntityManagerFactory.createEntityManager();

		}
		boolean open = entityManager.isOpen();
		if (open == false) {
			entityManager = createEntityManagerFactory.createEntityManager();
			System.err.println("I mbyllur");
		}
		return entityManager;
		

	}

}
