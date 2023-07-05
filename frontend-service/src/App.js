import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";

import IndexProvComponent from './components/IndexProvComponent';
import NewProvComponent from './components/NewProvComponent';
import UploadDataComponent from './components/UploadDataComponent';
import UploadData2Component from './components/UploadData2Component';
import IndexPagoComponent from './components/IndexPagoComponent';
import HomeComponent from './components/HomeComponent';
import CalcularPago from './components/CalcularPago';

function App() {
  return (
    <div>
        <BrowserRouter>
          <Routes>
            <Route path= "/" element={<HomeComponent />} />
            <Route path= "/proveedores/nuevo" element={<NewProvComponent />} />
            <Route path= "/proveedores/index" element={<IndexProvComponent />} />
            <Route path= "/subirAcopio" element={<UploadDataComponent />} />
            <Route path= "/subirCalidad" element={<UploadData2Component />} />
            <Route path= "/pagos/calcular" element={<CalcularPago />} />
            <Route path= "/pagos/index" element={<IndexPagoComponent />} />
          </Routes>
        </BrowserRouter>
  </div>
  );
}
export default App;