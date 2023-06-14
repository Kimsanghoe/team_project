import { Box, Button, Card, TextField, Typography } from '@mui/material';
import { useState } from 'react';
import { signUpApi } from '../../../apis';
import Post from '../../../components/post';

interface Props {
    setAuthView: (authView: boolean) => void;
}

export default function SignUp(props: Props) {
    const [userId, setUserId] = useState<string>('');
    const [userEmail, setUserEmail] = useState<string>('');
    const [userPassword, setUserPassword] = useState<string>('');
    const [userPasswordCheck, setUserPasswordCheck] = useState<string>('');
    const [userName, setUserName] = useState<string>('');
    const [userPhoneNumber, setUserPhoneNumber] = useState<string>('');
    const [userAddress, setUserAddress] = useState({
        address: '',
    });

    const { setAuthView } = props;

    const handleInput = (e: any) => {
        setUserAddress({
            ...userAddress,
            [e.target.name]: e.target.value,
        });
    };

    const SignUpHandler = async () => {
        const data = {
            userId: userId,
            password: userPassword,
            passwordCheck: userPasswordCheck,
            email: userEmail,
            phoneNumber: userPhoneNumber,
            address: userAddress.address,
            userName: userName,
        };

        const signUpResponse = await signUpApi(data);

        if (!signUpResponse) {
            alert('회원가입에 실패하였습니다.');
            return;
        }

        if (!signUpResponse.result) {
            alert('회원가입에 실패하였습니다.');
            return;
        }

        alert('회원가입에 성공하였습니다.');

        setAuthView(false);
    };

    return (
        <Card sx={{ minWidth: 275, maxWidth: '50vw', padding: 5 }}>
            <Box>
                <Typography variant="h5">회원가입</Typography>
            </Box>
            <Box height={'50vh'}>
                <TextField
                    fullWidth
                    label="아이디"
                    type="text"
                    variant="standard"
                    onChange={(e) => setUserId(e.target.value)}
                />
                <TextField
                    fullWidth
                    label="비밀번호"
                    type="password"
                    variant="standard"
                    onChange={(e) => setUserPassword(e.target.value)}
                />
                <TextField
                    fullWidth
                    label="비밀번호 확인"
                    type="password"
                    variant="standard"
                    onChange={(e) => setUserPasswordCheck(e.target.value)}
                />
                <TextField
                    fullWidth
                    label="이메일 주소"
                    type="email"
                    variant="standard"
                    onChange={(e) => setUserEmail(e.target.value)}
                />
                <TextField fullWidth label="이름" variant="standard" onChange={(e) => setUserName(e.target.value)} />
                <TextField
                    fullWidth
                    label="휴대폰 번호"
                    variant="standard"
                    onChange={(e) => setUserPhoneNumber(e.target.value)}
                />
                <Box sx={{ display: 'flex', alignItems: 'center' }} component="div">
                    <TextField
                        className="user_enroll_text"
                        sx={{ width: '70%' }}
                        label="주소"
                        variant="standard"
                        onChange={handleInput}
                        value={userAddress.address}
                    />
                    <Post company={userAddress} setcompany={setUserAddress} />
                </Box>
            </Box>
            <Box component="div">
                <Button fullWidth onClick={() => SignUpHandler()} variant="contained">
                    회원가입
                </Button>
            </Box>
            <Box component="div" display="flex" mt={2}>
                <Typography>이미 계정이 있으신가요?</Typography>
                <Typography fontWeight={800} ml={1} onClick={() => setAuthView(false)}>
                    로그인
                </Typography>
            </Box>
        </Card>
    );
}
