package com.bykenyodarz.javamapeo.dao;

import com.bykenyodarz.javamapeo.dominio.Reunion;

import java.time.LocalDate;
import java.util.List;

public class ReunionDao extends AbstracDao<Reunion, Long> {
    public ReunionDao() {
        setClazz(Reunion.class);
    }

    public Reunion proximaReunion(){
        var qlString = new StringBuilder()
                .append("FROM ")
                .append(Reunion.class.getName())
                .append(" WHERE fecha > now() order by fecha")
                .toString();
        var query = getEntityManager().createQuery(qlString).setMaxResults(1);
        return (Reunion) query.getSingleResult();
    }

    public List<Reunion> reunionesMananas() {
        var qlString = new StringBuilder()
                .append("FROM ")
                .append(Reunion.class.getName())
                .append(" WHERE fecha between ?1 and ?2")
                .toString();
        var query = getEntityManager().createQuery(qlString);
        var manana = LocalDate.now().plusDays(1);
        query.setParameter(1, manana.atStartOfDay());
        query.setParameter(2, manana.plusDays(1).atStartOfDay());
        return query.getResultList();
    }
}
