package ramo.klevis.national.buisnesslogic;

import java.util.Calendar;
import java.util.Date;

import ramo.klevis.national.beans.StatisticsBean;
import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.national.ibuisnesslogic.IStatistiks;
import ramo.klevis.openrental.entity.Car;
import ramo.klevis.openrental.entity.Customer;
import ramo.klevis.openrental.entity.Rental;
import ramo.klevis.openrental.utils.Money;

public class CustomerPiketLLogic implements ICustomerPiketLLogjic {

	ICustomerDao customerDao = new CustomerDao();

	int pagesaPerDite = 0;// check-out
	int demiPerDite = 0;// check-in
	private int sjelljaEMire = 0;// check-in
	private int faceBookLike = 0;// check-in
	int afatiRespekt = 0;// check-in
	int kmLim = 0;// check-in

	int piket = 0;

	IStatistiks statistiks = new Statistiks();

	private int demiGjithesejMonedh = 0;
	private int nrDiteve = 0;
	private int nrDiteveTesjella;
	private int saKmKaKlauar = 0;
	private int kmGjithesej;
	private int kmEBera = 0;

	private static int limitiIKm = 200;

	private void definePayForDay(int rentdays, Car car) {

		setNrDiteve(rentdays);
		if (rentdays >= 1 && rentdays <= 2) {

			limitiIKm = car.getLimKm1();

		} else if (rentdays >= 3 && rentdays <= 6) {
			limitiIKm = car.getLimKm2();
		} else if (rentdays >= 7 && rentdays <= 11) {
			limitiIKm = car.getLimKm3();
		} else if (rentdays >= 12 && rentdays <= 25) {
			limitiIKm = car.getLimKm4();
		} else if (rentdays >= 26 && rentdays <= 35) {
			limitiIKm = car.getLimKm5();
		}

	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#llogAtCheckIn(ramo.klevis.openrental.entity.Rental)
	 */
	@Override
	public Rental llogAtCheckIn(Rental rental) {

		StatisticsBean statisticsBean = new StatisticsBean();

		System.out.println(rental.getId());
		// demi per dite
		int llogSumTeTjera = statistiks.llogSumTeTjera(statisticsBean, rental);

		setDemiGjithesejMonedh(llogSumTeTjera);
		int dite = rental.getRentdays();

		int intValue = llogSumTeTjera;
		System.out.println("Shuma e demit " + intValue);
		demiPerDite = (intValue / dite);

		demiPerDite = demiPerDite * (-1);

		// respektimi afatit
		Calendar instance = Calendar.getInstance();

		instance.setTime(new Date());

		Calendar instance2 = Calendar.getInstance();
		instance2.setTime(rental.getEndtime());

		Calendar instance3 = Calendar.getInstance();
		instance3.setTime(rental.getStarttime());
		int diffInDays = (int) ((instance.getTime().getTime() - instance2
				.getTime().getTime()) / (1000 * 60 * 60 * 24));
		int diffInDays2 = (int) ((instance2.getTime().getTime() - instance3
				.getTime().getTime()) / (1000 * 60 * 60 * 24));

		setNrDiteveTesjella(diffInDays);
		System.out.println("Nr diteve" + diffInDays);
		System.out.println("Duhet te sillte per " + diffInDays2);
		if (diffInDays > 0) {

			afatiRespekt = diffInDays * (-5);
		}

		// kalimi nga km
		int km = rental.getKmin() - rental.getKmout();

		System.out.println("Km e ardhur  " + km);

		definePayForDay(rental.getRentdays(), rental.getCar());
		int i = limitiIKm * rental.getRentdays();

		setKmGjithesej(i);
		setKmEBera(km);
		int saKmKaKlauar2 = i - km;
		if (saKmKaKlauar2 > 0)
			setSaKmKaKlauar(-saKmKaKlauar2);
		else {
			saKmKaKlauar2 = km - i;
			setSaKmKaKlauar(saKmKaKlauar2);

		}
		System.out.println("Shumezimi i  km " + i);
		
		kmLim = i / km;
		if (kmLim > 1)
			kmLim = kmLim * 5;
		else {
			kmLim = 0;
		}

		Customer customer = rental.getCustomer();

		customer.setNrPikeve(customer.getNrPikeve() + getFaceBookLike()
				+ getSjelljaEMire() + demiPerDite + afatiRespekt + kmLim);

		if (rental.getAgents() != null) {
			rental.getAgents().setNrPikeve(rental.getCustomer().getNrPikeve());
		}

		return rental;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#llogAtCheckOut(ramo.klevis.openrental.entity.Rental)
	 */
	@Override
	public Rental llogAtCheckOut(Rental rental) {
		// Customer exitCustomer =
		// customerDao.exitCustomer(rental.getCustomer());
		// if (exitCustomer != null) {
		// rental.setCustomer(exitCustomer);
		//
		// }

		int shumaPaguar = rental.getAmount().intValue();
		int ditet = rental.getRentdays();

		if (rental.getCurency().equals(Money.LEK.getType())) {

			shumaPaguar = (int) (shumaPaguar * 0.007);
		} else if (rental.getCurency().equals(Money.DOLLAR)) {

			shumaPaguar = (int) (shumaPaguar * 0.7);
		}
		pagesaPerDite = shumaPaguar / ditet;

		rental.getCustomer().setNrPikeve(
				rental.getCustomer().getNrPikeve() + 10 + pagesaPerDite);

		if (rental.getAgents() != null) {

			rental.getAgents().setNrPikeve(
					rental.getAgents().getNrPikeve()
							+ rental.getCustomer().getNrPikeve());
		}
		return rental;

	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getSjelljaEMire()
	 */
	@Override
	public int getSjelljaEMire() {
		return sjelljaEMire;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setSjelljaEMire(int)
	 */
	@Override
	public void setSjelljaEMire(int sjelljaEMire) {
		this.sjelljaEMire = sjelljaEMire;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getFaceBookLike()
	 */
	@Override
	public int getFaceBookLike() {
		return faceBookLike;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setFaceBookLike(int)
	 */
	@Override
	public void setFaceBookLike(int faceBookLike) {
		this.faceBookLike = faceBookLike;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getCustomerDao()
	 */
	@Override
	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setCustomerDao(ramo.klevis.national.ibuisnesslogic.ICustomerDao)
	 */
	@Override
	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getPagesaPerDite()
	 */
	@Override
	public int getPagesaPerDite() {
		return pagesaPerDite;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setPagesaPerDite(int)
	 */
	@Override
	public void setPagesaPerDite(int pagesaPerDite) {
		this.pagesaPerDite = pagesaPerDite;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getDemiPerDite()
	 */
	@Override
	public int getDemiPerDite() {
		return demiPerDite;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setDemiPerDite(int)
	 */
	@Override
	public void setDemiPerDite(int demiPerDite) {
		this.demiPerDite = demiPerDite;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getAfatiRespekt()
	 */
	@Override
	public int getAfatiRespekt() {
		return afatiRespekt;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setAfatiRespekt(int)
	 */
	@Override
	public void setAfatiRespekt(int afatiRespekt) {
		this.afatiRespekt = afatiRespekt;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getKmLim()
	 */
	@Override
	public int getKmLim() {
		return kmLim;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setKmLim(int)
	 */
	@Override
	public void setKmLim(int kmLim) {
		this.kmLim = kmLim;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getPiket()
	 */
	@Override
	public int getPiket() {
		return piket;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setPiket(int)
	 */
	@Override
	public void setPiket(int piket) {
		this.piket = piket;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getStatistiks()
	 */
	@Override
	public IStatistiks getStatistiks() {
		return statistiks;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setStatistiks(ramo.klevis.national.ibuisnesslogic.IStatistiks)
	 */
	@Override
	public void setStatistiks(IStatistiks statistiks) {
		this.statistiks = statistiks;
	}

	public static int getLimitiikm() {
		return limitiIKm;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getDemiGjithesejMonedh()
	 */
	@Override
	public int getDemiGjithesejMonedh() {
		return demiGjithesejMonedh;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setDemiGjithesejMonedh(int)
	 */
	@Override
	public void setDemiGjithesejMonedh(int demiGjithesejMonedh) {
		this.demiGjithesejMonedh = demiGjithesejMonedh;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getNrDiteve()
	 */
	@Override
	public int getNrDiteve() {
		return nrDiteve;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setNrDiteve(int)
	 */
	@Override
	public void setNrDiteve(int nrDiteve) {
		this.nrDiteve = nrDiteve;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getSaKmKaKlauar()
	 */
	@Override
	public int getSaKmKaKlauar() {
		return saKmKaKlauar;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setSaKmKaKlauar(int)
	 */
	@Override
	public void setSaKmKaKlauar(int saKmKaKlauar) {
		this.saKmKaKlauar = saKmKaKlauar;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getNrDiteveTesjella()
	 */
	@Override
	public int getNrDiteveTesjella() {
		return nrDiteveTesjella;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setNrDiteveTesjella(int)
	 */
	@Override
	public void setNrDiteveTesjella(int nrDiteveTesjella) {
		this.nrDiteveTesjella = nrDiteveTesjella;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getKmGjithesej()
	 */
	@Override
	public int getKmGjithesej() {
		return kmGjithesej;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setKmGjithesej(int)
	 */
	@Override
	public void setKmGjithesej(int kmGjithesej) {
		this.kmGjithesej = kmGjithesej;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#getKmEBera()
	 */
	@Override
	public int getKmEBera() {
		return kmEBera;
	}

	/* (non-Javadoc)
	 * @see ramo.klevis.national.buisnesslogic.ICustomerPiketLLogjic#setKmEBera(int)
	 */
	@Override
	public void setKmEBera(int tejkalimiKm) {
		this.kmEBera = tejkalimiKm;
	}

}
