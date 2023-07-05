package negima.calidadservice.entity;

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
    private Integer id;

    private Integer codigo;

    private Integer grasas;

    private Integer solidos;

    private Integer anno;

    private Integer mes;

    private Integer quincena;
}
