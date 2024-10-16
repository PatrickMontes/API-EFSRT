package pe.edu.cibertec.massapi.presentation.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {

    private final String directorio = "imagenes/";

    @GetMapping("/{nombreImagen}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreImagen) {
        try {
            Path rutaArchivo = Paths.get(directorio).resolve(nombreImagen);
            Resource recurso = new UrlResource(rutaArchivo.toUri());

            if (recurso.exists() || recurso.isReadable()) {
                // Detecta el tipo de contenido de la imagen.
                String tipoContenido = MediaType.IMAGE_JPEG_VALUE; // Valor por defecto
                if (nombreImagen.endsWith(".png")) {
                    tipoContenido = MediaType.IMAGE_PNG_VALUE;
                } else if (nombreImagen.endsWith(".webp")) {
                    tipoContenido = "image/webp";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(tipoContenido))
                        .body(recurso);
            } else {
                throw new RuntimeException("No se puede leer el archivo: " + nombreImagen);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
