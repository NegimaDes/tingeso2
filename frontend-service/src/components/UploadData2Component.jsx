import React, { Component } from 'react';
import CalidadService from '../services/CalidadService';
import swal from 'sweetalert';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import '../styles/Upload.css';
import NavbarAll from './Navbar';

class Uploaddoc2 extends Component {
  constructor(props) {
    super(props);
    this.state = {
      file: null,
      redirectToPago: false,
    };
    this.onFileChange = this.onFileChange.bind(this);
  };

  onFileChange(event) {
    this.setState({ file: event.target.files[0] });
  };

  onFileUpload = () => {
    swal({
      title: "¿Está seguro de que desea cargar el archivo de texto?",
      text: "Tenga en cuenta que el archivo solo será cargado si su nombre es 'Calidad.csv' y si su formato es correcto.",
      icon: "warning",
      buttons: ["Cancelar", "Cargar"],
      dangerMode: true
    }).then((respuesta) => {
      if(respuesta){
        swal("Archivo cargado correctamente!", {icon: "success", timer: "3000"});
        const formData = new FormData();
        formData.append('file', this.state.file);
        CalidadService.uploadData(formData).then( () => {window.location.href = '/pagos/calcular'} );
      }
      else{
        swal({text: "Archivo no cargado.", icon: "error"});
      }
    });
  };

  render() {
    return (
      <div>
        <NavbarAll />
        <div className="upload-page-contain">
          <div >
            <h1 className="upload-h1">
              Suba el archivo de Calidades en la casilla
            </h1>
            <Form.Group controlId="formFileLg">
              <Form.Control className="upload-file-upload-btn" type="file" size="lg" onChange={this.onFileChange} />
            </Form.Group>
            <Button className="upload-submit-btn" onClick={this.onFileUpload}>
              Cargar información
            </Button>
          </div>
        </div>
      </div>
    );
  };
}

export default Uploaddoc2;