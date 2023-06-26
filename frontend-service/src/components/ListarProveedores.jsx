import { Component } from "react";
import ProvService from "../services/ProvService";
import NavbarComponent from "./NavbarComponent";
import styled from "styled-components";


class ListarProveedores extends Component {

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
                <NavbarComponent />
                <Styles>
                    <h1 className="text-center"><b>Proveedores</b> </h1>
                    <div className = "row">
                        <button className="btn btn-primary" onClick="">Agregar Proveedor</button>
                    </div>
                    <br></br>
                    <div className = "f">
                        <table border="1" class="content-table">
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
                </Styles>
            </div>
        );
    }
}
export default ListarProveedores;

const Styles = styled.div`

.text-center {
    text-align: center;
    justify-content: center;
    padding-top: 8px;
    font-family: "Arial Black", Gadget, sans-serif;
    font-size: 30px;
    letter-spacing: 0px;
    word-spacing: 2px;
    color: #000000;
    font-weight: 700;
    text-decoration: none solid rgb(68, 68, 68);
    font-style: normal;
    font-variant: normal;
    text-transform: uppercase;
}

.f{
    justify-content: center;
    align-items: center;
    display: flex;
}
*{
    font-family: sans-serif;
    box-sizing: content-box;
    margin: 0;
    padding: 0;
}
.content-table{
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.8em;
    min-width: 200px;
    border-radius: 5px 5px 0 0;
    overflow: hidden;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    margin-left: 4%;
    margin-right: 4%;
}
.content-table thead tr{
    background-color: #009879;
    color: #ffffff;
    text-align: center;
    font-weight: bold;
}
.content-table th,
.content-table td{
    padding: 12px 15px;
    text-align: center;
}
.content-table tbody tr{
    border-bottom: 1px solid #dddddd;
}
.content-table tbody tr:nth-of-type(even){
    background-color: #f3f3f3;
}
.content-table tbody tr:last-of-type{
    border-bottom: 2px solid #009879;
}
.content-table tbody tr.active-row{
    font-weight: bold;
    color: #009879;
}
`