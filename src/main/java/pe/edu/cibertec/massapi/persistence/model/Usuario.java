package pe.edu.cibertec.massapi.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @NotBlank(message = "Se requiere un nombre")
    private String nombre;

    @Email
    @Column(unique = true)
    @NotBlank(message = "Se requiere un email")
    private String email;

    @NotBlank(message = "Se requiere una contraseña")
    private String password;

    @Column(name = "numero_telefono")
    @NotBlank(message = "Se requiere un número de teléfono")
    private String numeroTelefono;

    private UsuarioRole role;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PedidoItem> pedidoItemList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Direccion direccion;

    @Column(name = "creado_en")
    private final LocalDateTime creadoEn = LocalDateTime.now();

}
