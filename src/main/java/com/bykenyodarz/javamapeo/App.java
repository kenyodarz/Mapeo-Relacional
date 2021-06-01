package com.bykenyodarz.javamapeo;

import com.bykenyodarz.javamapeo.dao.ActaDao;
import com.bykenyodarz.javamapeo.dao.ReunionDao;
import com.bykenyodarz.javamapeo.dao.SalaDao;
import com.bykenyodarz.javamapeo.dominio.Acta;
import com.bykenyodarz.javamapeo.dominio.Persona;
import com.bykenyodarz.javamapeo.dominio.Reunion;
import com.bykenyodarz.javamapeo.dominio.Sala;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {

        //DAO
        var reunionDao = new ReunionDao();
        var actaDao = new ActaDao();
        var salaDao = new SalaDao();

        //Creacion de Salas
        var s099 = new Sala("S099", "Trastero", 1);
        var s101 = new Sala("S101", "Reunion primera planta", 10);
        var s109 = new Sala("S109", "Entrevistas primera planta", 3);
        var s203 = new Sala("S203", "Sala grande", 25);

        salaDao.save(s099);
        salaDao.save(s101);
        salaDao.save(s109);
        salaDao.save(s203);

        var marta = new Persona("E001", "Marta", "Garcia Lopez");
        var pedro = new Persona("E002", "Pedro", "Gomez Fernandez");
        var santi = new Persona("E003", "Santiago", "Perez Perez");
        var luisa = new Persona("E004", "Luisa", "Gutierrez Gonzalez");

        var r0 = new Reunion(LocalDateTime.now(), "Reunion de Test");
        var r1 = new Reunion(LocalDateTime.now().plusHours(2), "Otra Reunion de Test");
        var r2 = new Reunion(LocalDateTime.now().plusDays(2), "Reunion de Pasado Mañana");
        var r3 = new Reunion(LocalDateTime.now().plusDays(1), "Reunion de Mañana");
        var r4 = new Reunion(LocalDateTime.now().minusDays(1), "Reunion de Ayer");

        r0.addPersona(marta);
        r0.setSala(s099);
        reunionDao.save(r0);
        var a0 = new Acta("Marta se reune sola, solo para descansar un rato", r0);
        actaDao.save(a0);
        reunionDao.updateOne(r0);

        r1.addPersona(marta);
        r1.addPersona(pedro);
        r1.addPersona(santi);
        r1.addPersona(luisa);
        r1.setSala(s101);
        reunionDao.save(r1);

        r2.addPersona(santi);
        r2.addPersona(santi);
        r2.setSala(s109);
        reunionDao.save(r2);

        r3.addPersona(marta);
        r3.addPersona(luisa);
        r3.setSala(s109);
        reunionDao.save(r3);

        r4.addPersona(marta);
        r4.addPersona(pedro);
        r4.addPersona(santi);
        r4.addPersona(luisa);
        r4.setSala(s203);
        reunionDao.save(r4);

        Acta a4 = new Acta("Participantes: M. Garcia, P. Gómez, S. Pérez y L. Gutiérrez. " +
                "Duracion: 1H. Se reunioeron el la sala 203 para preparar el lanzamiento de la aplicacion" +
                "\"Otra Reunion Mas\"", r4);
        actaDao.save(a4);
        reunionDao.updateOne(r4);

        // Recuperacion de datos
        var reuniones = reunionDao.getAll();
        reuniones.forEach(System.out::println);

        try {
            System.out.println("Tu proxima reunion es: " + reunionDao.proximaReunion());
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
            System.out.println("No tienes ninguna Reunion a la vista");
        }
        var reunionesManana = reunionDao.reunionesMananas();
        System.out.println("Para mañana: ");
        reunionesManana.forEach(System.out::println);
    }
}
