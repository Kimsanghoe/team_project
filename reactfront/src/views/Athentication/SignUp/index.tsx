import axios from 'axios'
import React, { useState } from 'react'
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import TextField from '@mui/material/TextField';

export default function SignUp() {
    const [userId, setUserId] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [phoneNumber, setPhoneNumber] = useState<string>('');
    const [address, setAddress] = useState<string>('');
    const [userName, setUserName] = useState<string>('');

    const SignUpHandler = () => {
        const data = {
            userId,
            password,
            email,
            phoneNumber,
            address,
            userName,
        };

        axios.post('http://localhost:8080/user/join_our', data)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {

            });
    }

  return (
    <Card sx={{minWidth: 275, maxWidth: "50vw"}}>
        <CardContent>
            <Box>
                <TextField fullWidth label="아이디" variant="standard" onChange={(e) => setUserId(e.target.value)}/>
                <TextField fullWidth label="비밀번호" type="password" variant="standard" onChange={(e) => setPassword(e.target.value)}/>
                <TextField fullWidth label="이메일" type="email" variant="standard" onChange={(e) => setEmail(e.target.value)}/>
                <TextField fullWidth label="휴대폰 번호" variant="standard" onChange={(e) => setPhoneNumber(e.target.value)}/>
                <TextField fullWidth label="주소" variant="standard" onChange={(e) => setAddress(e.target.value)}/>
                <TextField fullWidth label="이름" variant="standard" onChange={(e) => setUserName(e.target.value)}/>
            </Box>
        </CardContent>
        <CardActions>
            <Button fullWidth onClick={() => SignUpHandler()} variant="contained">회원가입</Button>
        </CardActions>
    </Card>
  )
}
