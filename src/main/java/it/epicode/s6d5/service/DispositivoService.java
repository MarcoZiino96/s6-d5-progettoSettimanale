package it.epicode.s6d5.service;

import it.epicode.s6d5.exception.NotFoundException;
import it.epicode.s6d5.model.*;
import it.epicode.s6d5.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class DispositivoService {

    @Autowired
    DispositivoRepository dispositivoRepository;
    @Autowired
    DipendenteService dipendenteService;

    public Page<Dispositivo> getAllDispositivi(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo getDispositivoById(int id){
        return dispositivoRepository.findById(id).orElseThrow(()-> new NotFoundException("Dispositivo con id "+id+" non trovato"));
    }

    public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest){
        Dispositivo dispositivo = new Dispositivo();

        dispositivo.setDisponibilita(dispositivoRequest.getDisponibilita());
        dispositivo.setTipologia(dispositivoRequest.getTipologia());
        dispositivo.setMarca(dispositivoRequest.getMarca());

        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo updateDispositivo(int id, DispositivoRequest dispositivoRequest){
        Dispositivo dispositivo = getDispositivoById(id);

        dispositivo.setDisponibilita(dispositivoRequest.getDisponibilita());
        dispositivo.setTipologia(dispositivoRequest.getTipologia());
        dispositivo.setMarca(dispositivoRequest.getMarca());

        return dispositivoRepository.save(dispositivo);
    }

    public void deleteDispositivo(int id){
        Dispositivo dispositivo = getDispositivoById(id);
        dispositivoRepository.delete(dispositivo);
    }

    public Dispositivo updateDipendenteDispositivo(int idDispositivo, int idDipendente){

        Dispositivo dispositivo = getDispositivoById(idDispositivo);
        Dipendente dipendente = dipendenteService.getDipendenteById(idDipendente);

       if (dispositivo.getDisponibilita() == Disponibilita.ASSEGNATO){
           throw  new RuntimeException("questo dispositivo è gia assegnato ad un utente");
       } else if (dispositivo.getDisponibilita() == Disponibilita.IN_MANUTENZIONE) {
           throw  new RuntimeException("questo dispositivo è in manutenzione");
       }
        dispositivo.setDisponibilita(Disponibilita.ASSEGNATO);
        dispositivo.setDipendente(dipendente);
        return dispositivoRepository.save(dispositivo);

    }
}
