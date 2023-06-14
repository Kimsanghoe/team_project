import { useEffect, useState } from 'react';
import Navigation from '../../Navigation';
import Authentication from '../../Authentication';
import BoardMain from '../../BoardMain';
import { useMemberStore } from '../../../stores';
import { useCookies } from 'react-cookie';
import axios from 'axios';

export default function MainLayout() {
    const [boardResponse, setBoardResponse] = useState<string>('');
    const [cookies] = useCookies();
    const { member } = useMemberStore();

    const getBoard = async (token: string) => {
        const requestOption = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        await axios
            .get('http://localhost:8080/api/bids/', requestOption)
            .then((response) => {
                setBoardResponse(response.data);
                console.log(requestOption);
            })
            .catch((e) => '');
    };

    useEffect(() => {
        const token = cookies.token;

        if (token) {
            getBoard(token);
        }
    }, [cookies.token]);

    return (
        <div>
            <Navigation />
            {member ? <BoardMain /> : <Authentication />}
        </div>
    );
}
