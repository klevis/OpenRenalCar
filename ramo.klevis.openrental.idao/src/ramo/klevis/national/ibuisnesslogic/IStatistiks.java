package ramo.klevis.national.ibuisnesslogic;

import java.util.Date;
import java.util.List;

import ramo.klevis.national.beans.StatisticsBean;
import ramo.klevis.national.beans.ValutaBean;
import ramo.klevis.openrental.entity.Rental;

public interface IStatistiks {


	List<Rental> getRentalsGroupByCars(Date d2, Date d1);

	List<StatisticsBean> convertToChartBean(Date d2, Date d1,ValutaBean valutaBean,boolean eficenca);

	int llogSumTeTjera(StatisticsBean statisticsBean, Rental rental);

	List<Rental> getRentalsGroupByCarsForEficenca(Date d2, Date d1);

}
