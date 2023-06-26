package negima.calserv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "calidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calidad")
    private Integer idcalidad;

    private Integer codigo;

    private Integer grasa;

    private Integer solido;

    private Integer anno;

    private Integer mes;

    private Integer quincena;
}
