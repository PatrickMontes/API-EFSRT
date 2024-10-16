package pe.edu.cibertec.massapi.service.LocalStorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class LocalStorageService {

    private final String directorio = "imagenes/";

    public String guardarImagenLocalmente(MultipartFile imagen) {
        try {
            Path rutaDirectorio = Paths.get(directorio);

            if (!Files.exists(rutaDirectorio)) {
                Files.createDirectories(rutaDirectorio);
            }

            String extensionArchivo = obtenerExtensionArchivo(imagen.getOriginalFilename());
            String nombreUnico = UUID.randomUUID().toString() + "." + extensionArchivo;

            Path rutaArchivo = rutaDirectorio.resolve(nombreUnico);
            Files.copy(imagen.getInputStream(), rutaArchivo);

            return "http://localhost:8080/" + directorio + nombreUnico;

        } catch (IOException e) {
            log.error("Error guardando la imagen localmente", e);
            throw new RuntimeException("Error guardando la imagen localmente: " + e.getMessage());
        }
    }


    private String obtenerExtensionArchivo(String nombreArchivo) {
        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    }

}