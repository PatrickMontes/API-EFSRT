package pe.edu.cibertec.massapi.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import pe.edu.cibertec.massapi.persistence.enums.UsuarioRole;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Se necesita nombre")
    private String nombre;

    @Column(unique = true)
    @NotBlank(message = "Se necesita email")
    private String email;

    @NotBlank(message = "Se necesita contraseña")
    private String contrasena;

    @Column(name = "numero_telefono")
    @NotBlank(message = "Se necesita número de teléfono")
    private String numeroTelefono;
    private UsuarioRole rol;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemPedido> listaItemsPedido;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Direccion direccion;

    @Column(name = "creado_en")
    private final LocalDateTime creadoEn = LocalDateTime.now();

}
