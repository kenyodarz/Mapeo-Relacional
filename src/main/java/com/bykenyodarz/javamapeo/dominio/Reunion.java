package com.bykenyodarz.javamapeo.dominio;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "personas")
@Entity
@Table(name = "reuniones")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    @NotNull
    private LocalDateTime fecha;

    @Column(name = "asunto")
    @NotBlank
    private String asunto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sala sala;

    @OneToOne(mappedBy = "reunion")
    private Acta acta;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "personas_reuniones" ,
            joinColumns = @JoinColumn(name = "reuniones_id"),
            inverseJoinColumns = @JoinColumn(name = "personas_id")
    )
    private Set<Persona> personas;

    public Reunion() {
        personas = new HashSet<>();
    }

    public Reunion(LocalDateTime fecha, String asunto) {
        this.fecha = fecha;
        this.asunto = asunto;
        personas = new HashSet<>();
    }

    public void addPersona(Persona persona) {
        personas.add(persona);
    }

    public void removePersona(Persona persona) {
        personas.remove(persona);
    }

    @Override
    public String toString() {
        return "Reunion {" +
                "id=" + id +
                ", fecha=" + fecha +
                ", asunto='" + asunto + '\'' +
                '}';
    }
}
