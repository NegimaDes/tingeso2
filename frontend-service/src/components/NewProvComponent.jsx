import { Component } from "react";
import '../styles/NewProv.css'
import swal from 'sweetalert';
import Axios from "axios";
import NavbarAll from './Navbar';

class NewProv extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id_proveedor: '',
            nombre: '',
            categoria: '',
            retencion: ''
        }
        this.changeIdProveedorHandler = this.changeIdProveedorHandler.bind(this);
        this.changeNombreHandler = this.changeNombreHandler.bind(this);
        this.changeCategoriaHandler = this.changeCategoriaHandler.bind(this);
        this.changeRetencionHandler = this.changeRetencionHandler.bind(this);
        this.saveProv = this.saveProv.bind(this);
    }

    changeIdProveedorHandler = (event) => {
        this.setState({ id_proveedor: event.target.value });
    }

    changeNombreHandler = (event) => {
        this.setState({ nombre: event.target.value });
    }

    changeCategoriaHandler = (event) => {
        this.setState({ categoria: event.target.value });
    }
    
    changeRetencionHandler = (event) => {
        this.setState({ retencion: event.target.value });
    }

    saveProv = (e) => {
        e.preventDefault();
        let proveedor = {
            "codigo": this.state.id_proveedor,
            "nombre": this.state.nombre,
            "categoria": this.state.categoria,
            "retencion": this.state.retencion
        };
        let axiosc = {
            headers: {
                'Access-Control-Allow-Origin': '*',
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-type, Accept",
                'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE'
            }
        }

        Axios.post("http://localhost:8080/proveedor", proveedor, axiosc).then(res => {
            swal("Proveedor registrado", "El proveedor se ha registrado exitosamente.", "success").then(() => {
                window.location.href = "/proveedores/index";
            });
        });
    }

    

    render() {
        return (
            <div>
                <NavbarAll />
                <div>
                    <div className="mainclass" >
                        <form>
                            <h1><b>Registrar un nuevo Proveedor</b></h1>
                            <div className="formcontainer">
                                <hr />
                                <div className="container">
                                    <label><strong>Código:</strong></label>
                                    <input type="text" placeholder="Codigo" name="id_proveedor" value={this.state.id_proveedor} onChange={this.changeIdProveedorHandler} />
                                    <label><strong>Nombre: </strong></label>
                                    <input type="text" placeholder="Nombre" name="nombre" value={this.state.nombre} onChange={this.changeNombreHandler} />
                                    <label><strong>Categoría del proveedor</strong></label>
                                    <input type="text" placeholder="A/B/C/D" name="categoria" value={this.state.categoria} onChange={this.changeCategoriaHandler} />
                                    <label><strong>¿El proveedor tiene retención?</strong></label>
                                    <select className="select" name="categoria" value={this.state.retencion} onChange={this.changeRetencionHandler}>
                                        <option value="" disabled selected>Ingresa la categoría</option>
                                        <option value="true">Si</option>
                                        <option value="false">No</option>
                                    </select>
                                </div>
                                <button className="btn2" onClick={this.saveProv}>Registrar Proveedor</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}
export default NewProv;