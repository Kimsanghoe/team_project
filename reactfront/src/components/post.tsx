import React from 'react';
import { useDaumPostcodePopup } from 'react-daum-postcode';
import { Button } from '@mui/material';

const Post = (props: any) => {
    const open = useDaumPostcodePopup('https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js');

    const handleComplete = (data: any) => {
        let fullAddress = data.address;
        let extraAddress = '';

        if (data.addressType === 'R') {
            if (data.bname !== '') {
                extraAddress += data.bname;
            }
            if (data.buildingName !== '') {
                extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
            }
            fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
        }

        console.log(fullAddress);

        props.setcompany({
            ...props.userAddress,
            address: fullAddress,
        });
    };

    const handleClick = () => {
        open({ onComplete: handleComplete });
    };

    return (
        <Button sx={{ width: props.width }} variant="contained" onClick={handleClick}>
            주소 찾기
        </Button>
    );
};

export default Post;
