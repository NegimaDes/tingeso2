import axios from "axios";
const ACOPIO_URL = "http://localhost:8080/acopio";

class AcopioService {

    uploadData(doc1, doc2) {
        return axios.post(ACOPIO_URL + "/save", doc1, doc2);
    }

}
export default new AcopioService();