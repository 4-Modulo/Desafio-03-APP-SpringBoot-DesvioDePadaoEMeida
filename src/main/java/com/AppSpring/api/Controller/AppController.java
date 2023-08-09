package com.AppSpring.api.Controller;


import com.AppSpring.api.Entity.App;
import com.AppSpring.api.Services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AppService appService;
    @PostMapping
    public ResponseEntity<?> newMarca(@RequestBody final App app) {
        try {
            App calculatedApp = this.appService.create(app);

            String message = "Marca cadastrada com sucesso";
            BigDecimal media = calculatedApp.getMedia();
            BigDecimal desvioPadrao = calculatedApp.getDesvioPadrao();
            String returnMesage = String.format("%s\nMédia: %s\nDesvio Padrão: %s", message, media, desvioPadrao);

            return ResponseEntity.ok(returnMesage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }



}
