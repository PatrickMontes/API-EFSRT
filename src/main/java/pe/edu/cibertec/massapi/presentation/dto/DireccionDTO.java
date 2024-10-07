package pe.edu.cibertec.massapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DireccionDTO {
    private Long id;
    private String direccion;
    private String ciudad;
    private String estado;
    private String zipcode;
    private String pais;

    private UsuarioDTO usuario;
    private LocalDateTime creadoEn = LocalDateTime.now();

}
