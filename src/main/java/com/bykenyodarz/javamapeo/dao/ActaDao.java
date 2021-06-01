package com.bykenyodarz.javamapeo.dao;

import com.bykenyodarz.javamapeo.dominio.Acta;

public class ActaDao extends AbstracDao<Acta, Long> {
    public ActaDao() {
        setClazz(Acta.class);
    }
}
