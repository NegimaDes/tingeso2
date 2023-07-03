import { Component } from "react";
import PaService from "../services/PaService";
import '../styles/Index.css'
import NavbarAll from './Navbar';

class IndexPago extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pagos: [],
        };
    }

    componentDidMount() {
        PaService.getPagos().then((res) => {
            this.setState({ pagos: res.data });
        });
    }

    render() {
        return (
            <div>
                <NavbarAll />
                <div>
                    <div style={{ textAlign: 'center' }} class="container my-2">
                        <h1 class="h1"><b>Listado de Pagos</b></h1>
                        <table style={{ border: '1px solid black' }} class="content-table">
                            <thead>
                                <tr>
                                    <th>Quincena</th>
                                    <th>Codigo proveedor</th>
                                    <th>Nombre proveedor</th>
                                    <th>Total KLS leche</th>
                                    <th>N° dias envio de leche</th>
                                    <th>Prom diario KLS leche</th>
                                    <th>%Variacion Leche</th>
                                    <th>%Grasa</th>
                                    <th>%Variacion Grasa</th>
                                    <th>%Solidos Totales</th>
                                    <th>%Variacion ST</th>
                                    <th>Pago Leche</th>
                                    <th>Pago Grasa</th>
                                    <th>Pago ST</th>
                                    <th>Bonificacion Frecuencia</th>
                                    <th>Dcto. Variacion Leche</th>
                                    <th>Dcto. Variacion Grasa</th>
                                    <th>Dcto. Variacion ST</th>
                                    <th>Pago Total</th>
                                    <th>Monto Retencion</th>
                                    <th>Monto Final</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.pagos.map(pago => (
                                    <tr key={pago.id_pago}>
                                        <td>{pago.quincena}</td>
                                        <td>{pago.codigo}</td>
                                        <td>{pago.nombre}</td>
                                        <td>{pago.kls}</td>
                                        <td>{pago.diasenvio}</td>
                                        <td>{pago.promdiario}</td>
                                        <td>{pago.varl}</td>
                                        <td>{pago.grasa}</td>
                                        <td>{pago.varg}</td>
                                        <td>{pago.solidos}</td>
                                        <td>{pago.vars}</td>
                                        <td>{pago.pagol}</td>
                                        <td>{pago.pagog}</td>
                                        <td>{pago.pagos}</td>
                                        <td>{pago.bonif}</td>
                                        <td>{pago.dvarl}</td>
                                        <td>{pago.dvarg}</td>
                                        <td>{pago.dvars}</td>
                                        <td>{pago.total}</td>
                                        <td>{pago.retencion}</td>
                                        <td>{pago.mfinal}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}
export default IndexPago;