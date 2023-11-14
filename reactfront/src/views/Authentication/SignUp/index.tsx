import { Box, Button, Card, TextField, Typography, Checkbox } from '@mui/material';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import dayjs, { Dayjs } from 'dayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { useState } from 'react';
import { businessNumberApi, signUpApi } from '../../../apis';
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
    const [userAddress, setUserAddress] = useState({ address: '' });
    const [userDetailAddress, setUserDetailAddress] = useState<string>('');

    const [checked, setChecked] = useState(false);
    const [businessNumber, setBusinessNumber] = useState<string>('');
    const [startDate, setStartDate] = useState<Dayjs | null>(dayjs());

    const convertTextField = () => {
        setChecked((checked) => !checked);
    };

    const { setAuthView } = props;

    const handleInput = (e: any) => {
        setUserAddress({
            ...userAddress,
            [e.target.name]: e.target.value,
        });
    };

    const SignUpHandler = async () => {
        var businessNumberApiResponse;

        let replaceUserPhoneNumber = userPhoneNumber.replaceAll('-', '');
        let userFullAddress = userAddress.address + ' ' + userDetailAddress;

        var data;

        if (checked === true) {
            const dateFormat = dayjs(startDate).format('YYYYMMDD');

            const businessData = {
                b_no: businessNumber,
                start_dt: dateFormat,
                p_nm: userName,
            };

            businessNumberApiResponse = await businessNumberApi(businessData);
            console.log(businessNumberApiResponse);

            data = {
                userId: userId,
                password: userPassword,
                passwordCheck: userPasswordCheck,
                email: userEmail,
                phoneNumber: replaceUserPhoneNumber,
                address: userFullAddress,
                userName: userName,
                //businessNumber: businessNumber,
            };
        } else {
            data = {
                userId: userId,
                password: userPassword,
                passwordCheck: userPasswordCheck,
                email: userEmail,
                phoneNumber: replaceUserPhoneNumber,
                address: userFullAddress,
                userName: userName,
            };
        }

        if (!businessNumberApiResponse && checked === true) {
            alert('사업자 등록 정보가 잘못되었습니다.');
            return;
        }


        const signUpResponse = await signUpApi(data);

        console.log(data);

        if (!signUpResponse) {
            alert('회원가입에 실패하였습니다.');
            return;
        }

        if (!signUpResponse.result) {
            console.log(signUpResponse.result);
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
            <Box height={'50vh'} sx={{ overflowY: 'scroll' }}>
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
                    label="휴대폰 번호 ('-' 는 제외하고 입력해주세요)"
                    variant="standard"
                    onChange={(e) => setUserPhoneNumber(e.target.value)}
                />
                <Box sx={{ display: 'flex', alignItems: 'flex-end' }} component="div">
                    <TextField
                        className="user_enroll_text"
                        sx={{ width: '80%' }}
                        label="주소"
                        variant="standard"
                        onChange={handleInput}
                        value={userAddress.address}
                    />
                    <Post width="20%" company={userAddress} setcompany={setUserAddress} />

                </Box>
                <TextField
                    fullWidth
                    label="상세 주소"
                    variant="standard"
                    onChange={(e) => setUserDetailAddress(e.target.value)}
                />
                <Box mt={2}>
                    <Checkbox onChange={convertTextField} />
                    <Typography sx={{ display: 'inline' }}>사업자 회원가입</Typography>
                    {checked ? (
                        <Box>
                            <TextField
                                fullWidth
                                label="사업자 번호"
                                variant="standard"
                                sx={{ paddingBottom: '20px' }}
                                onChange={(e) => setBusinessNumber(e.target.value)}
                            />
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DatePicker
                                    label="개업연월일"
                                    value={startDate}
                                    format="YYYY/MM/DD"
                                    onChange={(newValue) => setStartDate(newValue)}
                                />
                            </LocalizationProvider>
                        </Box>
                    ) : (
                        <></>
                    )}

                </Box>
                <TextField
                    fullWidth
                    label="상세 주소"
                    variant="standard"
                    onChange={(e) => setUserDetailAddress(e.target.value)}
                />
            </Box>
            <Box component="div" pt={3}>
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
