import axios from "axios";

const ACOPIO_URL = "http://localhost:8080/calidad";

class CalidadService {

    uploadData(file) {
        return axios.post(ACOPIO_URL + "/doc", file);
    }

}
export default new CalidadService();