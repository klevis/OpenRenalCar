package ramo.klevis.national.ibuisnesslogic;

import ramo.klevis.national.ibuisnesslogic.ICustomerDao;
import ramo.klevis.national.ibuisnesslogic.IStatistiks;
import ramo.klevis.openrental.entity.Rental;

public interface ICustomerPiketLLogjic {

	public abstract Rental llogAtCheckIn(Rental rental);

	public abstract Rental llogAtCheckOut(Rental rental);

	public abstract int getSjelljaEMire();

	public abstract void setSjelljaEMire(int sjelljaEMire);

	public abstract int getFaceBookLike();

	public abstract void setFaceBookLike(int faceBookLike);

	public abstract ICustomerDao getCustomerDao();

	public abstract void setCustomerDao(ICustomerDao customerDao);

	public abstract int getPagesaPerDite();

	public abstract void setPagesaPerDite(int pagesaPerDite);

	public abstract int getDemiPerDite();

	public abstract void setDemiPerDite(int demiPerDite);

	public abstract int getAfatiRespekt();

	public abstract void setAfatiRespekt(int afatiRespekt);

	public abstract int getKmLim();

	public abstract void setKmLim(int kmLim);

	public abstract int getPiket();

	public abstract void setPiket(int piket);

	public abstract IStatistiks getStatistiks();

	public abstract void setStatistiks(IStatistiks statistiks);

	public abstract int getDemiGjithesejMonedh();

	public abstract void setDemiGjithesejMonedh(int demiGjithesejMonedh);

	public abstract int getNrDiteve();

	public abstract void setNrDiteve(int nrDiteve);

	public abstract int getSaKmKaKlauar();

	public abstract void setSaKmKaKlauar(int saKmKaKlauar);

	public abstract int getNrDiteveTesjella();

	public abstract void setNrDiteveTesjella(int nrDiteveTesjella);

	public abstract int getKmGjithesej();

	public abstract void setKmGjithesej(int kmGjithesej);

	public abstract int getKmEBera();

	public abstract void setKmEBera(int tejkalimiKm);

}