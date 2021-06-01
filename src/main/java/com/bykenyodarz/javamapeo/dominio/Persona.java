package com.bykenyodarz.javamapeo.dominio;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude="personas")
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_empleado", unique = true)
    private String numeroEmpleado;

    @Column
    @NotBlank
    private String nombre;

    @Column
    @NotBlank
    private String apellido;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personas_reuniones" ,
            joinColumns = @JoinColumn(name = "personas_id"),
            inverseJoinColumns = @JoinColumn(name = "reuniones_id")
    )
    private Set<Reunion> reuniones;

    public Persona() {
        reuniones = new HashSet<>();
    }

    public Persona(String numeroEmpleado, String nombre, String apellido) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        reuniones = new HashSet<>();
    }

    public void addReunion(Reunion r){
        this.reuniones.add(r);
    }

    public void removeReunion(Reunion r){
        this.reuniones.remove(r);
    }
}
