package negima.provserv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    private Integer codigo;

    private String nombre;

    private String categoria;

    private boolean retencion;

    public boolean getRetencion(){
        return this.retencion;
    }
}