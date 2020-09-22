import axios from 'axios';

class EmployeeDataService {

    getAllEmployees() {
        return axios.get("http://localhost:8080/api/employee");
    }
}

export default new EmployeeDataService()
