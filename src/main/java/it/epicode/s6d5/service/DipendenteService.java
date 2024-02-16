package it.epicode.s6d5.service;



import it.epicode.s6d5.exception.NotFoundException;
import it.epicode.s6d5.model.Dipendente;
import it.epicode.s6d5.model.DipendenteRequest;
import it.epicode.s6d5.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    DipendenteRepository dipendenteRepository;

    @Autowired
    private JavaMailSenderImpl javaMailSender;


    public Page<Dipendente> getAllDipendente(Pageable pageable){return dipendenteRepository.findAll(pageable);}

    public Dipendente getDipendenteById(int id){
        return dipendenteRepository.findById(id).orElseThrow(()-> new NotFoundException("Dipendente con id "+id+" non trovato"));
    }

    public Dipendente saveDipendente(DipendenteRequest dipendenteRequest){

        Dipendente dipendente = new Dipendente();

        dipendente.setNome(dipendenteRequest.getNome());
        dipendente.setCognome(dipendenteRequest.getCognome());
        dipendente.setUserName(dipendenteRequest.getUserName());
        dipendente.setEmail(dipendenteRequest.getEmail());
        sendMail(dipendenteRequest.getEmail());

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

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }
}
