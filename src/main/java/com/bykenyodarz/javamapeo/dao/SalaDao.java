package com.bykenyodarz.javamapeo.dao;

import com.bykenyodarz.javamapeo.dominio.Sala;

public class SalaDao extends AbstracDao<Sala, String>{

    public SalaDao() {
        setClazz(Sala.class);
    }
}
