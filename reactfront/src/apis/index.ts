import axios from 'axios';

export const signInApi = async (data: any) => {
    const response = await axios.post('http://localhost:8080/api/auth/signIn', data).catch((e) => null);

    if (!response) return null;

    const result = response.data;
    return result;
};

export const signUpApi = async (data: any) => {
    const response = await axios
        .post('http://localhost:8080/api/auth/signUp', data, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        })
        .catch((e) => null);

    if (!response) return null;

    const result = response.data;
    return result;
};

export const businessNumberApi = async (data: any) => {
    var API_KEY = 'AIVBRhClaPpS%2BzStSWnDavc8Fia9BoV0WaliLOFYu4us0Ja3jx3qXuZESqTcNwNMxTa6I9di%2FWvWUeMbf0I2WA%3D%3D';

    var apidata = {
        businesses: [
            {
                b_no: data.b_no,
                start_dt: data.start_dt,
                p_nm: data.p_nm,
            },
        ],
    };

    const response = await axios
        .post(`https://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=${API_KEY}`, apidata)
        .catch((e) => null);

    if (!response) return null;

    if (!response.data.data[0].status) return null;

    const result = response.data;
    return result;
};
