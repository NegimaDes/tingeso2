package negima.pagoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Acopio {

    private Integer id;

    private Integer codigo;

    private Integer totalkls;

    private Integer manana;

    private Integer tarde;

    private Integer entregas;

    private Integer anno;

    private Integer mes;

    private Integer quincena;
}
