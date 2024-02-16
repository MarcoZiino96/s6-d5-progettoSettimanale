package it.epicode.s6d5.controller;

import it.epicode.s6d5.exception.BadRequestException;
import it.epicode.s6d5.model.Dipendente;
import it.epicode.s6d5.model.DipendenteRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import com.cloudinary.Cloudinary;
import it.epicode.s6d5.exception.CustomResponse;
import it.epicode.s6d5.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;


@RestController
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

    @Autowired
    Cloudinary cloudinary;


    @GetMapping("/dipendente")
    public ResponseEntity<CustomResponse> getAllDipendenti(Pageable pageable){
        return CustomResponse.success(HttpStatus.OK.toString(),dipendenteService.getAllDipendente(pageable),HttpStatus.OK);
    }

    @GetMapping("/dipendente/{id}")
    public ResponseEntity<CustomResponse> getDipendente(@PathVariable int id){
        return CustomResponse.success(HttpStatus.OK.toString(),dipendenteService.getDipendenteById(id), HttpStatus.OK);
    }


    @PostMapping("/dipendente")
    public ResponseEntity<CustomResponse> saveDipendente(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        return CustomResponse.success(HttpStatus.OK.toString(),dipendenteService.saveDipendente(dipendenteRequest), HttpStatus.OK);
    }

    @PutMapping("/dipendente/{id}")
    public ResponseEntity<CustomResponse> saveDipendente(@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        return CustomResponse.success(HttpStatus.OK.toString(),dipendenteService.updateDipendente(id,dipendenteRequest), HttpStatus.OK);
    }

    @DeleteMapping("/dipendente/{id}")
    public ResponseEntity<CustomResponse> updateDipendente(@PathVariable int id){
        dipendenteService.deleteDipendente(id);
        return  CustomResponse.emptyResponse("Dipendente con id "+id+" è stata cancellato", HttpStatus.OK);
    }

    @PatchMapping("/dipendente/{id}/upload")
    public ResponseEntity<CustomResponse> uploadAvatar(@PathVariable int id, @RequestParam("uploadDipendente") MultipartFile file) throws IOException {

        Dipendente dipendente = dipendenteService.uploadFotoProfilo(id, (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));

        return CustomResponse.success(HttpStatus.OK.toString(), dipendente, HttpStatus.OK);
    }
}
