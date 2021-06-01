package com.bykenyodarz.javamapeo.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "reunion")
@Entity
@Table(name = "actas")
@NoArgsConstructor
@AllArgsConstructor
public class Acta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contenido;

    @OneToOne
    @JoinColumn
    private Reunion reunion;

    public Acta(String contenido, Reunion reunion) {
        this.contenido = contenido;
        this.reunion = reunion;
        reunion.setActa(this);
    }

    @Override
    public String toString() {
        return "Acta {" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
