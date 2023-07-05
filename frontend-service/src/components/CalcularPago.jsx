import { Component } from "react";
import '../styles/NewProv.css'
import NavbarAll from './Navbar';
import swal from 'sweetalert';
import Axios from "axios";
import PagoService from "../services/PagoService";

class CalcularPago extends Component {
    constructor(props) {
        super(props);
        this.state = {
            anno: '',
            mes: '',
            quin: '',
        }
        this.changeAnnoHandler = this.changeAnnoHandler.bind(this);
        this.changeMesHandler = this.changeMesHandler.bind(this);
        this.changeQuinHandler = this.changeQuinHandler.bind(this);
    }

    changeAnnoHandler = (event) => {
        this.setState({ anno: event.target.value});
    }

    changeMesHandler = (event) => {
        this.setState({ mes: event.target.value});
    }

    changeQuinHandler = (event) => {
        this.setState({ quin: event.target.value});
    }

    calcularPagos = (e) => {
        e.preventDefault();
        PagoService.calcularPagos(this.state.anno, this.state.mes, this.state.quin).then(res => {
            swal({title:"Se calcularon los pagos"}).then(resp => {window.location.href = "/pagos/index";});
        });
    }

    render() {
        return (
            <div>
                <NavbarAll />
                <div>
                    <div className="mainclass">
                        <form>
                            <h1><b>Ingrese Fecha de la quincena a calcular los Pagos</b></h1>
                            <div className="formcontainer">
                                <hr />
                                <div className="container">
                                    <label><strong>Año:</strong></label>
                                    <input type="text" placeholder="AAAA" name="anno" value={this.state.anno} onChange={this.changeAnnoHandler} />
                                    <label><strong>Mes:</strong></label>
                                    <input type="text" placeholder="MM" name="mes" value={this.state.mes} onChange={this.changeMesHandler} />
                                    <label><strong>Quincena:</strong></label>
                                    <input type="text" placeholder="Q" name="quin" value={this.state.quin} onChange={this.changeQuinHandler} />
                                </div>
                                <button className="btn2" onClick={this.calcularPagos}>Registrar Proveedor</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}
export default CalcularPago;