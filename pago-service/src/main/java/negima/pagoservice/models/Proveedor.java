package negima.pagoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
    private Integer codigo;

    private String nombre;

    private String categoria;

    private boolean retencion;

    public boolean getRetencion(){
        return this.retencion;
    }
}
