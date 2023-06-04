import React, {useState} from 'react'
import Toolbar from "./Toolbar";
import Session from "react-session-api/src";

function LandingPage(props) {
    const [isLoggedIn, setIsLoggedIn] = useState(true);

    const onClickLogin = () => {
        setIsLoggedIn(true);
    };

    const onClickLogout = () => {
        setIsLoggedIn(false);
    };

    return (
        <div>
            <Toolbar
                isLoggedIn={isLoggedIn}
                onClickLogin={onClickLogin}
                onClickLogout={onClickLogout}
            />
            <div style={{padding: 16}}>메인 페이지</div>
        </div>
    );
}

export default LandingPage;