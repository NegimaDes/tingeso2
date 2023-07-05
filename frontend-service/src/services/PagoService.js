import axios from "axios";

const PAGO_URL = "http://localhost:8080/pago";

class PagoService {

    getPagos() {
        return axios.get(PAGO_URL);
    }

    calcularPagos(anno, mes, quin){
        return axios.post(PAGO_URL + "/calcular/"+anno+"/"+mes+"/"+quin);
    }
}
export default new PagoService();