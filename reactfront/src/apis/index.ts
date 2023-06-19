import axios from 'axios';

export const signInApi = async (data: any) => {
    const response = await axios.post('http://localhost:8080/api/auth/signIn', data).catch((e) => null);

    if (!response) return null;

    const result = response.data;
    return result;
};

export const signUpApi = async (data: any) => {
    const response = await axios.post('http://localhost:8080/api/auth/signUp', data).catch((e) => null);

    if (!response) return null;

    const result = response.data;
    return result;
};
