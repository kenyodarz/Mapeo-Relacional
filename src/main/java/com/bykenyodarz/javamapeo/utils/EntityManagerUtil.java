package com.bykenyodarz.javamapeo.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    public static EntityManager getEntityManager(){
        var factory = Persistence.createEntityManagerFactory("Reunion");
        return factory.createEntityManager();
    }

    public static void main(String[] args) {
        var manager = EntityManagerUtil.getEntityManager();
        System.out.println("EntityManager class ==> " + manager.getClass().getCanonicalName());
    }
}
