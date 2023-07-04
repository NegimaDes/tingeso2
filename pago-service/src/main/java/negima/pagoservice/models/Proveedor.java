package negima.pagoservice.models;

import lombok.Data;

@Data
public class Proveedor {
    private Integer codigo;

    private String nombre;

    private String categoria;

    private boolean retencion;

    public boolean getRetencion(){
        return this.retencion;
    }
}
