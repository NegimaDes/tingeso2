package negima.acoserv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "acopio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acopio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer codigo;

    private Integer totalkls;

    private Integer manana;

    private Integer tarde;

    private Integer entregas;

    private Integer anno;

    private Integer mes;

    private Integer quincena;

    public void addManana(){
        this.manana += 1;
        this.entregas += 1;
    }
    public void addTarde(){
        this.tarde += 1;
        this.entregas += 1;
    }

    public void addKls(int kls){
        this.totalkls += kls;
    }

    public void startCounters(){
        this.totalkls = 0;
        this.manana = 0;
        this.tarde = 0;
        this.entregas = 0;
    }

    public void setFecha(int anno, int mes, int quincena){
        this.anno = anno;
        this.mes = mes;
        this.quincena = quincena;
    }
}
