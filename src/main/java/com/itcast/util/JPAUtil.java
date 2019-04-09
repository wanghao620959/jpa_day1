package com.itcast.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;

public final class JPAUtil {
    private static EntityManagerFactory em;

    static {
             em= Persistence.createEntityManagerFactory("myJpa");
    }

    //使用管理器工厂生产一个管理器对象
    public static EntityManager getEntityManager(){

        return em.createEntityManager();
    }

}
