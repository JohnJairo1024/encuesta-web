package co.com.encuesta.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "marcapc")
@Data
public class MarcaPC {

    @Id
    @Column(name = "idMarcaPC")
    private Long idMarcaPC;

    @Column(name = "marca")
    private String marca;

    @ManyToOne
    @JoinColumn(name="idEncuesta", nullable=false)
    private Encuesta encuesta;

    public MarcaPC() {
    }

    public MarcaPC(Long idMarcaPC, String marca, Encuesta encuesta) {
        this.idMarcaPC = idMarcaPC;
        this.marca = marca;
        this.encuesta = encuesta;
    }
}
