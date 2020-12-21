package co.com.encuesta.controller;


import co.com.encuesta.exception.ResourceNotFoundException;
import co.com.encuesta.model.Encuesta;
import co.com.encuesta.repository.EncuestaRepository;
import co.com.encuesta.security.services.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Encuesta> getAllEmployees() {
        return encuestaRepository.findAll();
    }

    @GetMapping("/encuesta/{id}")
    public ResponseEntity<Encuesta> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Encuesta encuesta = encuestaRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(encuesta);
    }

    @PostMapping("/encuesta")
    public Encuesta createEmployee(@Valid @RequestBody Encuesta encuesta) {
        Encuesta encuestaDB = new Encuesta();
        encuestaDB.setNroDocumento(encuesta.getNroDocumento());
        encuestaDB.setEmail(encuesta.getEmail());
        encuestaDB.setComentarios(encuesta.getComentarios());
        encuestaDB.setMarca(encuesta.getMarca());
        encuestaDB.setFechaRespuesta(new Date());
        return encuestaRepository.save(encuestaDB);
    }

    @PutMapping("/encuesta/{id}")
    public ResponseEntity<Encuesta> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Encuesta encuestadetalle) throws ResourceNotFoundException {
        Encuesta encuesta = encuestaRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta not found for this id :: " + employeeId));

        encuesta.setIdEncuesta(encuestadetalle.getIdEncuesta());
        encuesta.setNroDocumento(encuestadetalle.getNroDocumento());
        encuesta.setEmail(encuestadetalle.getEmail());
        encuesta.setComentarios(encuestadetalle.getComentarios());
        encuesta.setMarca(encuestadetalle.getMarca());
        encuesta.setFechaRespuesta(encuestadetalle.getFechaRespuesta());
        final Encuesta updatedEmployee = encuestaRepository.save(encuesta);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/encuesta/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Encuesta Encuesta = encuestaRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta not found for this id :: " + employeeId));

        encuestaRepository.delete(Encuesta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
