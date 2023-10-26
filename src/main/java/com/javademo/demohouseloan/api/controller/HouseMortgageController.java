package com.javademo.demohouseloan.api.controller;

import com.javademo.demohouseloan.api.model.Mortgage;
import com.javademo.demohouseloan.services.HouseMortgageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("v1.0")
public class HouseMortgageController {
    private HouseMortgageService mortgageService;

    @Autowired
    public HouseMortgageController(HouseMortgageService mortgageService){
        this.mortgageService = mortgageService;
    }

    @Operation(summary = "Get mortgage from uuid", description = "testing...", tags = "HouseMortgage")
    @GetMapping("/mortgage")
    public CompletableFuture<ResponseEntity<?>> getMortgageById(@RequestParam UUID id){
        return CompletableFuture.supplyAsync(() -> {
            //TODO: actual task
            Optional<Mortgage> result =  mortgageService.getMortgageById(id);
            if(result.isPresent()){
                //return ResponseEntity.ok(result);
                return ResponseEntity.status(HttpStatus.OK).body("Mottat");
            }
            else{
                //result.complete(Optional.empty());
                //logger
                System.out.println("Finner ikke Lonsøknad for id:" + id );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ukjent");
            }
        });
    }

    @Operation(summary = "Get all active mortgages", description = "testing...", tags = "HouseMortgage")
    @GetMapping("/mortgages")
    public CompletableFuture<ResponseEntity<?>> getMortgages(){
        return CompletableFuture.supplyAsync(() -> {
            //TODO: actual task
            List<Mortgage> result =  mortgageService.getActiveMortgages();
            if(!result.isEmpty()){
                return ResponseEntity.ok(result);
            }
            else{;
                //TODO: use logger
                System.out.println("Finner ingen Lonsøknad");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Finner ingen Lonsøknad");
            }
        });
    }

    @Operation(summary = "Creates new mortgage", description = "testing...", tags = "HouseMortgage")
    @PostMapping("/mortgage/add")
    public CompletableFuture<ResponseEntity<?>> createMortgage(@RequestBody Mortgage mortgage){
        return CompletableFuture.supplyAsync(() -> {
            Optional<UUID> result =  mortgageService.createMortgage(mortgage);
            if(result.isPresent()){
                System.out.println("Lagt til ny lånsøknad:" + result.get());
                //var dbMortgage = mortgageService.getMortgageById(result.get());
                return ResponseEntity.ok(mortgage);
            }
            else{
                //TODO: logger
                System.out.println("Feil ved lagring av ny lånsøknad");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Feil ved lagring av ny lånsøknad");
            }
        });
    }

    @Operation(summary = "Updates existing mortgage", description = "testing...", tags = "HouseMortgage")
    @PutMapping("/mortgage/{id}/update")
    public CompletableFuture<ResponseEntity<?>> updateMortgage(@PathVariable String id, @RequestBody Mortgage mortgage){
        return CompletableFuture.supplyAsync(() -> {
            Optional<Mortgage> result =  mortgageService.updateMortgage(id, mortgage);
            if(result.isPresent()){
                System.out.println("Oppdatert lånsøknad: " + id);
                //var dbMortgage = mortgageService.getMortgageById(result.get(UUID.fromString(id)));
                return ResponseEntity.status(HttpStatus.OK).body("Oppdatert lånsøknad: " + id);
            }
            else{
                //TODO: logger
                System.out.println("Feil ved oppdatering av lånsøknad");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Feil ved oppdatering av lånsøknad");
            }
        });
    }

    @Operation(summary = "Deletes existing mortgage", description = "testing...", tags = "HouseMortgage")
    @PutMapping("/mortgage/{id}/delete")
    public CompletableFuture<ResponseEntity<?>> deleteMortgage(@PathVariable String id){
        return CompletableFuture.supplyAsync(() -> {
            Optional<Boolean> result =  mortgageService.deleteMortgage(UUID.fromString(id));
            if(result.isPresent()){
                System.out.println("Slettet lånsøknad: " + id);
                return ResponseEntity.status(HttpStatus.OK).body("Slettet lånsøknad: " + id);
            }
            else{
                //TODO: logger
                System.out.println("Feil ved sletting av lånsøknad");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Feil ved sletting av lånsøknad");
            }
        });
    }
}
