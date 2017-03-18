package com.minicubic.infoguiacore.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author xergio
 */
public enum PersistenceManager {
    INSTANCE;

    private EntityManagerFactory emFactory;

    private PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("infoGuiaPU");
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }
}
