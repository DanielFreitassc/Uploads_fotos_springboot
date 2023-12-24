package com.fotos.fotos.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fotos.fotos.utils.Hd;



@RestController
@RequestMapping("/fotos")
public class FotoController {
    @Autowired
    private Hd hd;
    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping
	public void upload(@RequestParam MultipartFile foto) {
		hd.salvarFoto(foto);
	}
    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getFoto(@PathVariable String filename) {
        Path filePath = hd.obterCaminhoFoto(filename);

        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro ao obter o recurso da foto.", e);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @DeleteMapping("/{filename}")
    public ResponseEntity<Void> deletarFoto(@PathVariable String filename) {
        Path filePath = hd.obterCaminhoFoto(filename);

        if (Files.exists(filePath)) {
            try {
                Files.delete(filePath);
                return ResponseEntity.noContent().build();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao excluir a foto.", e);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}