package it.epicode.s6d5.service;



import it.epicode.s6d5.exception.NotFoundException;
import it.epicode.s6d5.model.Dipendente;
import it.epicode.s6d5.model.DipendenteRequest;
import it.epicode.s6d5.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    DipendenteRepository dipendenteRepository;

    public Page<Dipendente> getAllDipendente(Pageable pageable){return dipendenteRepository.findAll(pageable);}

    public Dipendente getDipendenteById(int id){
        return dipendenteRepository.findById(id).orElseThrow(()-> new NotFoundException("Dipendente con id "+id+" non trovato"));
    }

    public Dipendente saveDipendente(DipendenteRequest dipendenteRequest){

        Dipendente dipendente = new Dipendente();

        dipendente.setNome(dipendenteRequest.getNome());
        dipendente.setCognome(dipendenteRequest.getCognome());
        dipendente.setEmail(dipendenteRequest.getEmail());
        dipendente.setUserName(dipendenteRequest.getUserName());

        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(int id,DipendenteRequest dipendenteRequest){
        Dipendente dipendente = getDipendenteById(id);

        dipendente.setNome(dipendenteRequest.getNome());
        dipendente.setCognome(dipendenteRequest.getCognome());
        dipendente.setEmail(dipendenteRequest.getEmail());
        dipendente.setUserName(dipendenteRequest.getUserName());

        return dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente (int id){
        Dipendente dipendente= getDipendenteById(id);
        dipendenteRepository.delete(dipendente);
    }

    public Dipendente uploadFotoProfilo(int id,String url){
        Dipendente dipendente = getDipendenteById(id);
        dipendente.setFotoProfilo(url);
        return  dipendenteRepository.save(dipendente);
    }
}
