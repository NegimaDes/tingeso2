import axios from "axios";
const PAGO_URL = "http://localhost:8080/pago";

class PaService {

    getPagos() {
        return axios.get(PAGO_URL);
    }
}
export default new PaService();