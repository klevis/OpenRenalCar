package ramo.klevis.national.buisnesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import ramo.klevis.national.beans.StatisticsBean;
import ramo.klevis.national.beans.ValutaBean;
import ramo.klevis.national.ibuisnesslogic.IStatistiks;
import ramo.klevis.openrental.entity.EntityManagerQuery;
import ramo.klevis.openrental.entity.Investime;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.utils.RentalStatus;

public class Statistiks implements IStatistiks {

	@Override
	public List<Rental> getRentalsGroupByCars(Date d2, Date d1) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();
		Query createQuery = entityManager
				.createQuery("Select r from Rental r Join r.car c where r.starttime>=:d1 and r.starttime<=:d2 and r.rentalStatus=:status group by r.car order by r.kmin ");

		createQuery.setParameter("d1", d2, TemporalType.TIMESTAMP);
		createQuery.setParameter("d2", d1, TemporalType.TIMESTAMP);
		createQuery.setParameter("status", RentalStatus.CHECK_IN);
		List<Rental> resultList = createQuery.getResultList();

		return resultList;
	}

	@Override
	public List<Rental> getRentalsGroupByCarsForEficenca(Date d2, Date d1) {

		EntityManager entityManager = EntityManagerQuery.getEntityManager();

		if (entityManager.getTransaction().isActive() == false)
			entityManager.getTransaction().begin();
		Query createQuery = entityManager
				.createQuery("Select r from Rental r Join r.car c where r.endtime>=:d1 and r.endtime<=:d2 and r.rentalStatus=:status group by r.car order by r.kmin,r.starttime ");

		createQuery.setParameter("d1", d2, TemporalType.TIMESTAMP);
		createQuery.setParameter("d2", d1, TemporalType.TIMESTAMP);
		createQuery.setParameter("status", RentalStatus.CHECK_IN);
		List<Rental> resultList = createQuery.getResultList();

		return resultList;
	}

	private double llogInvestime(StatisticsBean statisticsBean,
			Investime investime, ValutaBean bean) {
		double sum = statisticsBean.getSumInvestime();

		BigDecimal amount = BigDecimal.valueOf(investime.getAmount());

		if (amount != null) {
			double d = amount.doubleValue();
			double sum2 = 0.0;
			if (bean != null) {

				String string = bean.getFirstMoney().toString();
				if (string.equals("EURO")) {

					string = "EURO";
				} else if (string.equals("DOLLAR")) {
					string = "DOLLAR";

				} else if (string.equals("LEK")) {
					string = "LEK";
				}

				if (investime.getMoney().toString().equals(string)) {
					Double double1 = bean.getFirst().get(bean.getFirstMoney());

					if (double1 != null)
						sum2 = d * double1;

				} else {
					String string2 = bean.getSecondMoney().toString();
					if (string.equals("EURO")) {

						string = "EURO";
					} else if (string.equals("DOLLAR")) {
						string = "DOLLAR";

					} else if (string.equals("LEK")) {
						string = "LEK";
					}

					if (investime.getMoney().toString().equals(string2)) {

						Double double1 = bean.getSecond().get(
								bean.getSecondMoney());

						if (double1 != null)
							sum2 = d * double1;

					}
				}

			}
			if (sum2 != 0.0)
				sum = sum + sum2;
			else {
				sum = sum + d;
			}
		}
		return sum;

	}

	@Override
	public int llogSumTeTjera(StatisticsBean statisticsBean, Rental rental) {

		int parseInt = Integer.parseInt(rental.getxClean());
		int parseInt2 = Integer.parseInt(rental.getxDamage());
		int parseInt3 = Integer.parseInt(rental.getxKarburant());
		int parseInt4 = Integer.parseInt(rental.getxKm());
		int parseInt5 = Integer.parseInt(rental.getxLate());
		int sum = parseInt + parseInt2 + parseInt3 + parseInt4 + parseInt5;

		statisticsBean.setSumTeTjera(statisticsBean.getSumTeTjera()
				+ (double) sum);

		return sum;

	}

	private int llogNrDiteve(StatisticsBean statisticsBean, Rental rental) {

		statisticsBean.setNrDiteve(statisticsBean.getNrDiteve()
				+ rental.getRentdays());

		return 0;
	}

	private int llogKm(StatisticsBean statisticsBean, Rental rental) {

		if (statisticsBean.getTempKm() == 0) {

			int sumKm = rental.getKmin() - rental.getKmout();

			System.err.println("dIFERENCE   " + sumKm + "   Statusi "
					+ rental.getRentalStatus() + "  Vlera out "
					+ rental.getKmout() + "  Vlera in " + rental.getKmin());
			if (rental.getKmin() > 0)
				statisticsBean.setSumKm(sumKm);
			statisticsBean.setTempKm(rental.getKmin());
		} else {
			int diference = 0;
			diference = rental.getKmin() - statisticsBean.getTempKm();

			statisticsBean.setSumKm(statisticsBean.getSumKm() + diference);
			statisticsBean.setTempKm(rental.getKmin());
		}

		return 0;
	}

	@Override
	public List<StatisticsBean> convertToChartBean(Date d2, Date d1,
			ValutaBean bean, boolean eficenca) {

		List<Rental> rentalsGroupByCars = null;
		if (eficenca == false)
			rentalsGroupByCars = getRentalsGroupByCars(d2, d1);
		else
			rentalsGroupByCars = getRentalsGroupByCarsForEficenca(d2, d1);

		List<StatisticsBean> listReturn = new ArrayList<StatisticsBean>();
		if (rentalsGroupByCars.size() > 0) {
			Rental first = rentalsGroupByCars.get(0);
			boolean firstTime = false;
			StatisticsBean statisticsBean = new StatisticsBean();

			for (Rental rental : rentalsGroupByCars) {

				if (firstTime == false) {
					firstTime = true;
					statisticsBean = new StatisticsBean();
					statisticsBean.setStartime(rental.getStarttime());
				}

				String license = rental.getCar().getLicense();
				if (license.equals(first.getCar().getLicense())) {

					llogKm(statisticsBean, rental);
					llogNrDiteve(statisticsBean, rental);
					llogSumTeTjera(statisticsBean, rental);
					double sum = convertToRightMOney(bean, statisticsBean,
							rental);
					statisticsBean.setSum(sum);

					List<Investime> investimet = rental.getCar()
							.getInvestimet();
					for (Investime investime : investimet) {
						statisticsBean
								.setSumInvestime(statisticsBean
										.getSumInvestime()
										+ llogInvestime(statisticsBean,
												investime, bean));
					}

					// convertimi i lekut ne lek tjeter :)

					String description = rental.getCar().getDescription();
					statisticsBean.setDesc(description);
					statisticsBean.setLicenseCar(license);
					String vin = rental.getCar().getVin();
					statisticsBean.setVin(vin);
					statisticsBean.getListRentals().add(rental);

					System.out.println(rental.getCurency() + " >>>> "
							+ rental.getCar().getDescription() + " >>>>>  "
							+ rental.getRentdays() + "  >>>>> "
							+ rental.getAmount());
				} else {

					statisticsBean.setDrate1(first.getCar().get_drate2()
							.intValue());
					statisticsBean.setDrate2(first.getCar().get_drate3()
							.intValue());
					statisticsBean.setDrate3(first.getCar().get_drate4()
							.intValue());
					statisticsBean.setDrate4(first.getCar().get_drate5()
							.intValue());
					statisticsBean.setDrate5(first.getCar().get_drate6()
							.intValue());

					ramo.klevis.openrental.entity.Class classes = first.getCar().getClasses();
					if (classes != null)
						statisticsBean.setClazzi(classes.getClass_());
					else
						statisticsBean.setClazzi("Pa percaktuar");

					listReturn.add(statisticsBean);
					statisticsBean = new StatisticsBean();
					statisticsBean.setStartime(rental.getStarttime());
					llogKm(statisticsBean, rental);
					llogNrDiteve(statisticsBean, rental);
					llogSumTeTjera(statisticsBean, rental);
					double sum = convertToRightMOney(bean, statisticsBean,
							rental);
					llogKm(statisticsBean, rental);

					List<Investime> investimet = rental.getCar()
							.getInvestimet();
					for (Investime investime : investimet) {

						statisticsBean
								.setSumInvestime(statisticsBean
										.getSumInvestime()
										+ llogInvestime(statisticsBean,
												investime, bean));
					}

					statisticsBean.setSum(sum);
					String description = rental.getCar().getDescription();
					statisticsBean.setDesc(description);
					statisticsBean.setLicenseCar(license);
					String vin = rental.getCar().getVin();
					statisticsBean.setVin(vin);
					statisticsBean.getListRentals().add(rental);
					System.out.println(rental.getCurency() + " >>>> "
							+ rental.getCar().getDescription() + " >>>>>>> "
							+ rental.getRentdays() + "  >>>>> "
							+ rental.getAmount() + " >>>>>>  ");
					first = rental;

				}

			}

		}
		return listReturn;
	}

	public double convertToRightMOney(ValutaBean bean,
			StatisticsBean statisticsBean, Rental rental) {
		double sum = statisticsBean.getSum();

		BigDecimal amount = rental.getAmount();

		if (amount != null) {
			double d = amount.doubleValue();
			double sum2 = 0.0;
			if (bean != null) {

				String string = bean.getFirstMoney().toString();
				if (string.equals("EURO")) {

					string = "EUR";
				} else if (string.equals("DOLLAR")) {
					string = "USD";

				} else if (string.equals("LEK")) {
					string = "LEK";
				}
				if (rental.getCurency().equals(string)) {
					Double double1 = bean.getFirst().get(bean.getFirstMoney());

					if (double1 != null)
						sum2 = d * double1;

				} else {
					String string2 = bean.getSecondMoney().toString();
					if (string.equals("EURO")) {

						string = "EUR";
					} else if (string.equals("DOLLAR")) {
						string = "USD";

					} else if (string.equals("LEK")) {
						string = "LEK";
					}

					if (rental.getCurency().equals(string2)) {

						Double double1 = bean.getSecond().get(
								bean.getSecondMoney());

						if (double1 != null)
							sum2 = d * double1;

					}
				}

			}
			if (sum2 != 0.0)
				sum = sum + sum2;
			else {
				sum = sum + d;
			}
		}
		return sum;
	}

	public static void main(String[] args) {

		Statistiks statistiks = new Statistiks();
		Date d1 = new Date();
		Date d2 = new Date();
		Calendar instance = Calendar.getInstance();
		instance.set(2012, Calendar.SEPTEMBER, 1);

		d2 = instance.getTime();

		instance.set(2012, Calendar.SEPTEMBER, 30);

		d1 = instance.getTime();

		List<StatisticsBean> convertToChartBean = statistiks
				.convertToChartBean(d2, d1, null, false);

		//
		// // List<StatisticsBean> convertToChartBean = statistiks
		// // .convertToChartBean();
		// for (StatisticsBean statisticsBean : convertToChartBean) {
		//
		// System.out.println(statisticsBean.getSum() + "  "
		// + statisticsBean.getDesc() + " "
		// + statisticsBean.getLicenseCar());
		// }

	}

}
