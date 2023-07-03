import { Component } from "react";
import ProvService from "../services/ProvService";
import '../styles/Index.css'
import NavbarAll from './Navbar';

class IndexProv extends Component {
    constructor(props) {
        super(props);
        this.state = {
            proveedores: [],
        };
    }

    componentDidMount() {
        ProvService.getProveedores().then((res) => {
            this.setState({ proveedores: res.data });
        });
    }
    render() {
        return (
            <div>
                <NavbarAll />
                <div>
                    <div style={{ textAlign: 'center' }} class="container my-2">

                        <h1 class="h1"><b> Listado de proveedores</b></h1>
                        <table style={{ border: '1px solid black' }} class="content-table">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Nombre</th>
                                    <th>Categoria</th>
                                    <th>Retencion del ultimo pago</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.proveedores.map(
                                        proveedor =>
                                            <tr key={proveedor.id}>
                                                <td> {proveedor.id_proveedor} </td>
                                                <td> {proveedor.nombre} </td>
                                                <td> {proveedor.categoria} </td>
                                                <td>" . ({proveedor.retencion}==true ? 'Si' : 'No') . "</td>
                                            </tr>
                                    )
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}
export default IndexProv;