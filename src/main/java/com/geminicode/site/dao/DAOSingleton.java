package com.geminicode.site.dao;

import com.geminicode.site.dao.impl.ObjectifyExampleDAO;

public class DAOSingleton {

	private static final DAOSingleton INSTANCE = new DAOSingleton();

	private ExampleDAO exampleDAO = null;

	public static ExampleDAO getExampleDao() {
		if (INSTANCE.exampleDAO == null) {
			INSTANCE.exampleDAO = new ObjectifyExampleDAO();
		}
		return INSTANCE.exampleDAO;
	}
}
