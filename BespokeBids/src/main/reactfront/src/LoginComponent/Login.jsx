import React, {useEffect, useState} from 'react'

const DummyUser = {
    id: 'user0823',
    pw: 'test1234@'
}

export default function Login() {
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");

    const [idValid, setIdValid] = useState(false);
    const [pwValid, setPwValid] = useState(false);

    const [notAllow, setNotAllow] = useState(true);

    const handleId = (e) => {
        setId(e.target.value);
        const regex =
            /^[A-Za-z][A-Za-z0-9_]{4,14}$/;

        if(regex.test(e.target.value)) {
            setIdValid(true);
        } else {
            setIdValid(false);
        }
    }

    const handlePw = (e) => {
        setPw(e.target.value);
        const regex =
            /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;

        if(regex.test(e.target.value)) {
            setPwValid(true);
        } else {
            setPwValid(false);
        }
    }

    const onClickConfirmButton = () => {
        if(id === DummyUser.id && pw === DummyUser.pw) {
            alert('로그인 성공');
        } else {
            alert('로그인 실패');
        }
    }

    useEffect(() => {
        if(idValid && pwValid) {
            setNotAllow(false);
            return;
        }
        setNotAllow(true);
    }, [idValid, pwValid])

    return (
        <div className="page">
            <div className="titleWrap">
                아이디와 비밀번호를
                <br/>
                입력해주세요
            </div>

            <div className="contentWrap">
                <div className="inputTitle">아이디</div>
                <div className="inputWrap">
                    <input
                        type="text" className="input"
                        placeholder="test_user0823"
                        value={id}
                        onChange={handleId}
                    />
                </div>
                <div className="errorMsgWrap">
                    {
                        !idValid && id.length > 0 && (
                            <div>올바른 아이디를 입력해주세요.</div>
                        )
                    }
                </div>

                <div style={{marginTop: "26px"}} className="inputTitle">비밀번호</div>
                <div className="inputWrap">
                    <input
                        type="password" className="input"
                        placeholder="영문, 숫자, 특수문자 포함 8자 이상"
                        value={pw}
                        onChange={handlePw}
                    />
                </div>
                <div className="errorMsgWrap">
                    {
                        !pwValid && pw.length > 0 && (
                            <div>영문, 숫자, 특수문자 포함 8자 이상 입력해주세요.</div>
                        )
                    }
                </div>
            </div>
            
            <div>
                <button onClick={onClickConfirmButton} disabled={notAllow} className="bottomButton">확인</button>
            </div>
        </div>
    )
}