import { Box, Button, Card, TextField, Typography } from '@mui/material';
import { useState } from 'react';
import useCookies from 'react-cookie/cjs/useCookies';
import { useMemberStore } from '../../../stores';
import { signInApi } from '../../../apis';

interface Props {
    setAuthView: (authView: boolean) => void;
}

export default function SignIn(props: Props) {
    const [userId, setUserId] = useState<string>('');
    const [userPassword, setUserPassword] = useState<string>('');

    const [cookies, setCookies] = useCookies();

    const { member, setMember } = useMemberStore(); // zustand 전역 변수

    const { setAuthView } = props;

    const SignInHandler = async () => {
        if (userId.length === 0 || userPassword.length === 0) {
            alert('아이디와 비밀번호를 입력해주세요.');
            return;
        }

        const data = {
            userId: userId,
            password: userPassword,
        };

        const signInResponse = await signInApi(data);

        if (!signInResponse) {
            alert('로그인에 실패하였습니다.');
            return;
        }

        if (!signInResponse.result) {
            alert('로그인에 실패하였습니다.');
            return;
        }

        const { token, exprTime, member } = signInResponse.data;
        const expires = new Date();
        expires.setMilliseconds(expires.getMilliseconds() + exprTime);

        setCookies('token', token, { expires });
        setMember(member);
    };

    return (
        <Card sx={{ minWidth: 275, maxWidth: '50vw', padding: 5 }}>
            <Box>
                <Typography variant="h5">로그인</Typography>
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
            </Box>
            <Box component="div">
                <Button fullWidth onClick={() => SignInHandler()} variant="contained">
                    로그인
                </Button>
            </Box>
            <Box component="div" display="flex" mt={2}>
                <Typography>신규 회원이신가요?</Typography>
                <Typography fontWeight={800} ml={1} onClick={() => setAuthView(true)}>
                    회원가입
                </Typography>
            </Box>
        </Card>
    );
}
