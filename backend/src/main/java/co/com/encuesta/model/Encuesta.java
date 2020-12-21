package co.com.encuesta.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "encuesta")
@Data
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEncuesta")
    private Long idEncuesta;

    @Column(name = "nroDocumento")
    private String nroDocumento;

    @Column(name = "email")
    private String email;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "marca")
    private String marca;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaRespuesta")
    private Date fechaRespuesta;

    public Encuesta() {
    }

    public Encuesta(String nroDocumento, String email, String comentarios, String marca, Date fechaRespuesta) {
        this.nroDocumento = nroDocumento;
        this.email = email;
        this.comentarios = comentarios;
        this.marca = marca;
        this.fechaRespuesta = fechaRespuesta;
    }
}