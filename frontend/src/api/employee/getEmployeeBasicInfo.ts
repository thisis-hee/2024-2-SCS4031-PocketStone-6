import axios from 'axios';
import { tokenAxios } from '../tokenAPI';
import { API_URL } from '../../constants/envText';
import { headers } from '../../constants/headers';

export const getEmployeeBasicInfo = async (employeeId: number) => {
  try {
    const response = await tokenAxios.get(`${API_URL}/api/employee/${employeeId}/info`, {
      headers: headers,
    });
    console.log(response);
    return response.data;
  } catch (error) {
    console.error(error);
    if (axios.isAxiosError(error)) {
      return error.response?.data;
    }
  }
};