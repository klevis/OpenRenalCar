package ramo.klevis.openrental.utils;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class QueryBuilder {

	public static String createWhereString1(List<Expression> expressions
			){
		String sql = "";
		int size = 0;

		for (Expression expression : expressions) {
			size += 1;

			if (expression.getValue() instanceof List) {
				List<Object> list = (List<Object>) expression.getValue();
				sql = sql + " (";

				int i = 0;
				for (Object object : list) {
					sql +=  expression.getColumn() + " "
							+ expression.getOperator().getValue() + " :" +

							expression.getColumn().replace(".", "_") + "_"
							+ expressions.indexOf(expression) + "_" + i;
					i += 1;
					if (i < list.size()) {
						sql += " OR ";
					}
				}
				sql += " ) ";
			}

			else
				sql = sql  + expression.getColumn() + " "
						+ expression.getOperator().getValue() + " :"
						+ expression.getColumn().replace(".", "_") + "_"
						+ expressions.indexOf(expression);

			if (size < expressions.size()) {
				sql = sql + " and ";
			}

		}
		
		return sql;
		
	}
	
	
	public static String createWhereString(List<Expression> expressions
			){
		String sql = "";
		int size = 0;

		for (Expression expression : expressions) {
			size += 1;

			if (expression.getValue() instanceof List) {
				List<Object> list = (List<Object>) expression.getValue();
				sql = sql + " (";

				int i = 0;
				for (Object object : list) {
					sql += "o." + expression.getColumn() + " "
							+ expression.getOperator().getValue() + " :" +

							expression.getColumn().replace(".", "_") + "_"
							+ expressions.indexOf(expression) + "_" + i;
					i += 1;
					if (i < list.size()) {
						sql += " OR ";
					}
				}
				sql += " ) ";
			}

			else
				sql = sql + "o." + expression.getColumn() + " "
						+ expression.getOperator().getValue() + " :"
						+ expression.getColumn().replace(".", "_") + "_"
						+ expressions.indexOf(expression);

			if (size < expressions.size()) {
				sql = sql + " and ";
			}

		}
		
		return sql;
		
	}
	
	
	
	public static String createQueryStringWithSelectPart(List<Expression> expressions, String selectPart, String groupByFields,
			String sortFields,  String entity){
		
		String sql = createWhereString(expressions);
		
		if (sql.equals("")) return sql;
		sql =  "Select " + selectPart + " from  " + entity + " o where " + sql;
		
		if(groupByFields != null && !"".equals(groupByFields))  sql = sql + " GROUP BY " + groupByFields;
		
		if(sortFields != null && !"".equals(sortFields))  sql = sql + " ORDER BY " + sortFields;
       	
		
  
		return sql;
	}
	
	
	public static String createQueryString(List<Expression> expressions,
			String groupByFields, String sortFields, String entityName ) {

		String sql = createWhereString(expressions);
		

		if (sql.equals("")) return sql;
		sql =  "Select o from  " + entityName + " o where " + sql;
		
		if(groupByFields != null && !"".equals(groupByFields))  sql = sql + " GROUP BY " + groupByFields;
		
		if(sortFields != null && !"".equals(sortFields))  sql = sql + " ORDER BY " + sortFields;
       		 
  
	
		return sql;

	}
	
	
	public static Query createQueryWithSelectPart(EntityManager em, List<Expression> expressions, String selectPart,
			String groupByFields, String sortFields,  String entity){
		
		String sql = createQueryStringWithSelectPart(expressions, selectPart, groupByFields, sortFields,  entity);
		
		if ("".equals(sql))return null;
		
		
		Query query = em.createQuery(sql);
		
		for (Expression expression : expressions) {

       	 if (expression.getValue() instanceof List){
       		 List<Object> list = (List<Object>) expression.getValue();
       		 
       		 int i = 0;
       		 for (Object object : list) {
       			 query.setParameter(expression.getColumn().replace(".", "_") + "_" + expressions.indexOf(expression) + "_" + i, object);
       			 i+=1;
       		 }
       	 }
       	 else{
       		
       		query.setParameter(expression.getColumn().replace(".", "_") + "_" + expressions.indexOf(expression), expression.getValue());
       	 }
       		 

		}
		
		return query;
		
	}
	
	
	public static Query addParametersToQuery(Query query, List<Expression> expressions){
		for (Expression expression : expressions) {

	       	 if (expression.getValue() instanceof List){
	       		 List<Object> list = (List<Object>) expression.getValue();
	       		 
	       		 int i = 0;
	       		 for (Object object : list) {
	       			 query.setParameter(expression.getColumn().replace(".", "_") + "_" + expressions.indexOf(expression) + "_" + i, object);
	       			 i+=1;
	       		 }
	       	 }
	       	 else{
	       		
	       		query.setParameter(expression.getColumn().replace(".", "_") + "_" + expressions.indexOf(expression), expression.getValue());
	       	 }
	       		 

			}
			
			return query;
	}
	
	
	public static Query createQuery(EntityManager em, List<Expression> expressions,
			String groupByFields, String sortFields, String entityName){
		
		String sql = createQueryString(expressions, groupByFields, sortFields, entityName);
		
		System.out.println(sql);
		
		if ("".equals(sql))return null;
		
		
		Query query = em.createQuery(sql);
		
		for (Expression expression : expressions) {

       	 if (expression.getValue() instanceof List){
       		 List<Object> list = (List<Object>) expression.getValue();
       		 
       		 int i = 0;
       		 for (Object object : list) {
       			 query.setParameter(expression.getColumn().replace(".", "_") + "_" + expressions.indexOf(expression) + "_" + i, object);
       			 i+=1;
       		 }
       	 }
       	 else{
       		
       		query.setParameter(expression.getColumn().replace(".", "_") + "_" + expressions.indexOf(expression), expression.getValue());
       	 }
       		 

		}
		
		return query;
	}
}
