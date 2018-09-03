package com.cg.plp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {

	public static EntityManager getEntitiManager() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		return emf.createEntityManager();
	}

}
