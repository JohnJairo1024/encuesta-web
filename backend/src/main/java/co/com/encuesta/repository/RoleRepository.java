package co.com.encuesta.repository;

import java.util.Optional;

import co.com.encuesta.model.Role;
import co.com.encuesta.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}