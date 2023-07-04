package negima.pagoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idpago;

    private Integer codigo;

    private String nombre;

    private String fecha;

    private Integer kls;

    private Integer diasenvio;

    private float promDiario;

    private Integer grasa;

    private Integer solidos;

    private Integer varl;

    private Integer varg;

    private Integer vars;

    private Integer pleche;

    private Integer pgrasa;

    private Integer psolidos;

    private Integer bonificacion;

    private Integer dvarl;

    private Integer dvarg;

    private Integer dvars;

    private Integer retencion;

    private Integer total;

    private Integer vfinal;

    public void setFecha(int anno, int mes, int quincena){
        this.fecha = anno + "/" + mes + "/" + quincena;
    }
}
