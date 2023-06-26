import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ListProv from './components/ListProv';

function App() {
  return (
    <div>
        <BrowserRouter>
          <Routes>
            <Route path= "/proveedores" element={<ListProv />} />
          </Routes>
        </BrowserRouter>
  </div>
  );
}
export default App;