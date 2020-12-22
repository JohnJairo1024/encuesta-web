package co.com.encuesta.controller;


import co.com.encuesta.exception.ResourceNotFoundException;
import co.com.encuesta.model.Encuesta;
import co.com.encuesta.repository.EncuestaRepository;
import co.com.encuesta.security.services.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EncuestaController {
    @Autowired
    private EncuestaRepository encuestaRepository;

    @Autowired
    private EncuestaService encuestaService;

    @GetMapping("/encuesta")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public List<Encuesta> getAllEncuestas() {
        return encuestaRepository.findAll();
    }

    @GetMapping("/encuesta/{id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<Encuesta> getEncuestaById(@PathVariable(value = "id") Long encuestaId)
            throws ResourceNotFoundException {
        Encuesta encuesta = encuestaRepository.findById(encuestaId)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta not found for this id :: " + encuestaId));
        return ResponseEntity.ok().body(encuesta);
    }

    @PostMapping("/encuesta")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Encuesta createEncuesta(@Valid @RequestBody Encuesta encuesta) {
        Encuesta encuestaDB = new Encuesta();
        encuestaDB.setNroDocumento(encuesta.getNroDocumento());
        encuestaDB.setEmail(encuesta.getEmail());
        encuestaDB.setComentarios(encuesta.getComentarios());
        encuestaDB.setMarca(encuesta.getMarca());
        encuestaDB.setFechaRespuesta(new Date());
        return encuestaRepository.save(encuestaDB);
    }

    @PutMapping("/encuesta/{id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<Encuesta> updateEncuesta(@PathVariable(value = "id") Long encuestaId,
                                                   @Valid @RequestBody Encuesta encuestadetalle) throws ResourceNotFoundException {
        Encuesta encuesta = encuestaRepository.findById(encuestaId)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta not found for this id :: " + encuestaId));

        encuesta.setIdEncuesta(encuestadetalle.getIdEncuesta());
        encuesta.setNroDocumento(encuestadetalle.getNroDocumento());
        encuesta.setEmail(encuestadetalle.getEmail());
        encuesta.setComentarios(encuestadetalle.getComentarios());
        encuesta.setMarca(encuestadetalle.getMarca());
        encuesta.setFechaRespuesta(encuestadetalle.getFechaRespuesta());
        final Encuesta updatedEncuesta = encuestaRepository.save(encuesta);
        return ResponseEntity.ok(updatedEncuesta);
    }

    @DeleteMapping("/encuesta/{id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Map<String, Boolean> deleteEncuesta(@PathVariable(value = "id") Long encuestaId)
            throws ResourceNotFoundException {
        Encuesta Encuesta = encuestaRepository.findById(encuestaId)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta not found for this id :: " + encuestaId));

        encuestaRepository.delete(Encuesta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
