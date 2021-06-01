package com.bykenyodarz.javamapeo.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="salas")
@AllArgsConstructor
@NoArgsConstructor
public class Sala {

    @Id
    @Column(length = 20)
    private String id;

    @Column
    @NotBlank
    private String descripcion;

    @NotNull
    @Column
    private int capacidad;

    @OneToMany(mappedBy = "sala")
    private List<Reunion> reuniones;

    public Sala(String id, String descripcion, int capacidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }
}
