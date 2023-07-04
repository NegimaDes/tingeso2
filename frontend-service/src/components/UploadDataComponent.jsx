import React, { Component } from 'react';
import AcoService from '../services/AcoService';
import swal from 'sweetalert';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import '../styles/Upload.css';
import NavbarAll from './Navbar';

class Uploaddocs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      doc1: null,
      doc2: null,
    };
    this.onFileChange = this.onFileChange.bind(this);
  }

  onFileChange(event) {
    this.setState({ file: event.target.files[0] });
  }

  onFileUpload = () => {
    swal({
      title: '¿Está seguro/a de subir estos archivos?',
    }).then((res) => {
      const formData = new FormData();
      formData.append('file', this.state.file);
      AcoService.uploadData(formData)
        .then(() => {
          swal('¡Archivo guardado con éxito!', '', 'success').then(() => {
            window.location.href = '/calidad'; // Redireccionar utilizando window.location.href
          });
        })
        .catch(() => {
          swal('Error al guardar el archivo', '', 'error');
        });
    });
  };

  render() {
    return (
      <div>
        <NavbarAll />
        <div className="upload-page-contain">
          <div className="upload-data-card">
            <h1 className="upload-h1">
              Suba los archivos en su zona correspondiente
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
  }
}

export default Uploaddocs;