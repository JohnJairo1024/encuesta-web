package co.com.encuesta.repository;

import co.com.encuesta.model.MarcaPC;
import co.com.encuesta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaPCRepository extends JpaRepository<MarcaPC, Long> {

}