package ramo.klevis.national.buisnesslogic;

import javax.persistence.EntityManager;

import ramo.klevis.national.ibuisnesslogic.IAtomicChooserCar;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.EntityManagerQuery;

public class AtomicChooserCar implements IAtomicChooserCar {

	@Override
	public void unlockCar(Car car) {
		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}

		car.setChoosen("" + car.getId());
		entityManager.merge(car);
		entityManager.getTransaction().commit();

	}

	public boolean lockCar(Car car) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		if (entityManager.getTransaction().isActive() == false) {

			entityManager.getTransaction().begin();
		}

		String choosen = car.getChoosen();
		if (choosen != null) {
			if (choosen.equals(""+car.getId()) == false) {

				return false;

			} else {

//				car.setChoosen(National.user.getUsername() + car.getId());
				entityManager.merge(car);
				entityManager.getTransaction().commit();

				return true;
			}
		} else {
			// rasti kur makina seshte perdorur ndonjehere
//			car.setChoosen(National.user.getUsername() + car.getId());
			entityManager.merge(car);
			entityManager.getTransaction().commit();
			return true;

		}

		// TODO: handle exception

	}
}
