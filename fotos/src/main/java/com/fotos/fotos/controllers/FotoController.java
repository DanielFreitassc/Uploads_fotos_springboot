package com.fotos.fotos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.fotos.fotos.utils.UploadUtils;



@Controller
@RequestMapping("/foto")
public class FotoController {

    @RequestMapping("/uploadForm")
    public String uploadForm() {
        return "uploadForm"; // Se precisar de uma página HTML, mantenha essa parte
    }

    @PostMapping("/upload")
    public ResponseEntity<String> singleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Por favor, selecione um arquivo para fazer upload.");
            }

            boolean uploadSuccess = UploadUtils.fazerUploadImagem(file);

            if (uploadSuccess) {
                return ResponseEntity.ok("Arquivo carregado com sucesso.");
            } else {
                return ResponseEntity.status(500).body("Falha ao carregar o arquivo.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro durante o upload do arquivo: " + e.getMessage());
        }
    }

    // Exemplo de outro método usando UploadUtils (adapte conforme necessário)
    @PostMapping("/outraRotaDeUpload")
    public ResponseEntity<String> outraRotaDeUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Por favor, selecione um arquivo para fazer upload.");
            }

            boolean uploadSuccess = UploadUtils.fazerOutraOperacao(file);

            if (uploadSuccess) {
                return ResponseEntity.ok("Arquivo carregado com sucesso usando outra operação.");
            } else {
                return ResponseEntity.status(500).body("Falha ao carregar o arquivo usando outra operação.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro durante o upload do arquivo: " + e.getMessage());
        }
    }
}