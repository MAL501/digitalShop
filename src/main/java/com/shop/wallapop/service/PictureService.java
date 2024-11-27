package com.shop.wallapop.service;

import com.shop.wallapop.entity.Advertisement;
import com.shop.wallapop.entity.Picture;
import com.shop.wallapop.repository.AdvertRepository;
import com.shop.wallapop.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PictureService {
    private static final List<String> PERMITTED_TYPES = List.of("image/jpeg", "image/png", "image/gif", "image/avif", "image/webp");
    private static final long MAX_FILE_SIZE = 10000000;
    private static final String UPLOADS_DIRECTORY = "uploads/imagesProductos";

    @Autowired
    PictureRepository pictureRepository;
    @Autowired
    AdvertRepository advertRepository;
    public List<Picture> guardarFotos(List<MultipartFile> pictures, Advertisement advertisement) {
        List<Picture> fotos = new ArrayList<>();
        for(MultipartFile picture : pictures) {
            if(!picture.isEmpty()) {
                validarArchivo(picture);
                String pictureName= picture.getOriginalFilename();
                guardarArchivo(picture, pictureName);

                Picture foto = Picture.builder()
                        .name(pictureName)
                        .advertisement(advertisement).build();
                fotos.add(foto);
            }
        }
        advertisement.setAdsPics(fotos);
        return fotos;
    }
    /*Vale para todos cp cv*/
    public void validarArchivo(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Archivo no seleccionado");
        }
        if (!PERMITTED_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("El archivo seleccionado no es una imagen.");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Archivo demasiado grande. Sólo se admiten archivos < 10MB");
        }

    }
    public String generarNombreUnico(MultipartFile file) {
        UUID nombreUnico = UUID.randomUUID();
        String extension;
        if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
            extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        } else {
            throw new IllegalArgumentException("El archivo seleccionado no es una imagen.");
        }
        return nombreUnico + extension;
    }
    public void guardarArchivo(MultipartFile file, String nuevoNombreFoto) {
        Path ruta = Paths.get(UPLOADS_DIRECTORY + File.separator + nuevoNombreFoto);
        //Movemos el archivo a la carpeta y guardamos su nombre en el objeto catgoría
        try {
            byte[] contenido = file.getBytes();
            Files.write(ruta, contenido);
        } catch (
                IOException e) {
            throw new RuntimeException("Error al guardar archivo", e);
        }
    }
    public void deletePicture(Long idFoto) {
        Optional<Picture> fotoProductoOptional = pictureRepository.findById(idFoto);
        if(fotoProductoOptional.isPresent()){
            Picture picture  = fotoProductoOptional.get();
            Path archivoFoto = Paths.get(picture.getName());
            try {
                Files.deleteIfExists(archivoFoto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pictureRepository.deleteById(idFoto);
        }
    }


}
