package co.com.encuesta;

import co.com.encuesta.model.Encuesta;
import co.com.encuesta.model.MarcaPC;
import co.com.encuesta.model.Role;
import co.com.encuesta.model.RoleName;
import co.com.encuesta.repository.EncuestaRepository;
import co.com.encuesta.repository.MarcaPCRepository;
import co.com.encuesta.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private MarcaPCRepository marcaPCRepository;

    @Autowired
    private EncuestaRepository encuestaRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {

            //encuesta
            encuestaRepository.save(new Encuesta("1024L", "1024.gmail.com", "Buen estado...", "Azus", new Date()));
            encuestaRepository.save(new Encuesta("1025L", "1025.gmail.com", "Buen estado...", "Huawei", new Date()));
            encuestaRepository.save(new Encuesta("1026L", "1026.gmail.com", "Buen estado...", "Compaq", new Date()));
            encuestaRepository.save(new Encuesta("1027L", "1027.gmail.com", "Buen estado...", "Lenovo", new Date()));


//            // pc
//            marcaPCRepository.save(new MarcaPC(1L, "Azus", new Encuesta(1L, "1024", "1024.gmail.com", "Buen estado...", new Date())));
//            marcaPCRepository.save(new MarcaPC(1L, "Lenovo", new Encuesta(1L, "1024", "1024.gmail.com", "Buen estado...", new Date())));
//            marcaPCRepository.save(new MarcaPC(5L, "Huawei", new Encuesta(1L, "1024", "1024.gmail.com", "Buen estado...", new Date())));

//            roleRepository.save(new Role(RoleName.valueOf("ROLE_USER")));
//            roleRepository.save(new Role(RoleName.valueOf("ROLE_PM")));
//            roleRepository.save(new Role(RoleName.valueOf("ROLE_ADMIN")));



        };
    }

}
