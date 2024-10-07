package pe.edu.cibertec.massapi.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private String ciudad;
    private String estado;
    private String zipcode;
    private String pais;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column(name = "creado_en")
    private final LocalDateTime creadoEn = LocalDateTime.now();

}