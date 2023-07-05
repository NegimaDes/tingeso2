package negima.pagoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calidad {
    private Integer id;

    private Integer codigo;

    private Integer grasas;

    private Integer solidos;

    private Integer anno;

    private Integer mes;

    private Integer quincena;
}
