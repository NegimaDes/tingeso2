import { Component } from "react";
import ProvService from "../services/ProvService";


class ListProv extends Component {

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

    render(){
        return (
            <div>
                <h2 className="text-center">Proveedores</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick="">Agregar Proveedor</button>
                </div>
                <br></br>
                <div className = "row">
                    <table className = "table table-striped table-bordered">

                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Nombre</th>
                                <th>Categoria</th>
                                <th>Retencion</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.proveedores.map(
                                    proveedor =>
                                    <tr key = {proveedor.id}>
                                        <td> {proveedor.codigo} </td>
                                        <td> {proveedor.nombre} </td>
                                        <td> {proveedor.categoria} </td>
                                        <td> {proveedor.retencion} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}
export default ListProv