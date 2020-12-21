package co.com.encuesta.repository;

import co.com.encuesta.model.Encuesta;
import co.com.encuesta.model.MarcaPC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {

}