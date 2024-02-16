package it.epicode.s6d5.controller;


import it.epicode.s6d5.exception.BadRequestException;
import it.epicode.s6d5.exception.CustomResponse;
import it.epicode.s6d5.model.Dipendente;
import it.epicode.s6d5.model.Dispositivo;
import it.epicode.s6d5.model.DispositivoRequest;
import it.epicode.s6d5.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;


@RestController
public class DispositivoController {

    @Autowired
    DispositivoService dispositivoService;


    @GetMapping("/dispositivo")
    public ResponseEntity<CustomResponse> getAllDispositivi(Pageable pageable){
        return CustomResponse.success(HttpStatus.OK.toString(),dispositivoService.getAllDispositivi(pageable), HttpStatus.OK);
    }


    @GetMapping("/dispositivo/{id}")
    public ResponseEntity<CustomResponse> getDispositivo(@PathVariable int id){
        return CustomResponse.success(HttpStatus.OK.toString(),dispositivoService.getDispositivoById(id),HttpStatus.OK);
    }

    @PostMapping("/dispositivo")
    public  ResponseEntity<CustomResponse> saveDispositivo(@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        return  CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.saveDispositivo(dispositivoRequest), HttpStatus.OK);
    }

    @PutMapping("/dispositivo/{id}")
    public  ResponseEntity<CustomResponse> updateDispositivo(@PathVariable int id,@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        return  CustomResponse.success(HttpStatus.OK.toString(), dispositivoService.updateDispositivo(id, dispositivoRequest), HttpStatus.OK);
    }

    @DeleteMapping("/dispositivo/{id}")
    public ResponseEntity<CustomResponse> deleteDispositivo(@PathVariable int id){
        dispositivoService.deleteDispositivo(id);
        return  CustomResponse.emptyResponse("Dispositivo con id "+id+" è stata cancellato", HttpStatus.OK);
    }

    @PatchMapping("/dispositivo/{id}")
    public ResponseEntity<CustomResponse> setDipendenteInDispositivo(@PathVariable int id, @RequestParam("idDipendente") int idDipendente){

        Dispositivo dispositivo = dispositivoService.updateDipendenteDispositivo(id, idDipendente);

        return CustomResponse.success(HttpStatus.OK.toString(), dispositivo, HttpStatus.OK);
    }
}
