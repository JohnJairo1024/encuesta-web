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
    @Column(name = "idEncuesta")
    private Long idEncuesta;

    @Column(name = "nroDocumento")
    private String nroDocumento;

    @Column(name = "email")
    private String email;

    @Column(name = "comentarios")
    private String comentarios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encuesta")
    private List<MarcaPC> marcaPCS;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaRespuesta")
    private Date fechaRespuesta;

    public Encuesta() {
    }

    public Encuesta(Long idEncuesta, String nroDocumento, String email, String comentarios, Date fechaRespuesta) {
        this.idEncuesta = idEncuesta;
        this.nroDocumento = nroDocumento;
        this.email = email;
        this.comentarios = comentarios;
        this.fechaRespuesta = fechaRespuesta;
    }
}