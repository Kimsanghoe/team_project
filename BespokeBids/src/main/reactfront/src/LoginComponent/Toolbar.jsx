import React from 'react'
import styled from "styled-components";
import Session from "react-session-api/src";

const StyledWrapper = styled.div`
  width: 100%;
  padding: 16px;
  display: flex;
  flex-direction: row;
  border-bottom: 1px solid #808080;
`;

const StyledGreeting = styled.span`
  margin-right: 8px;
`;

function Toolbar(props) {
    const {isLoggedIn, onClickLogin, onClickLogout} = props;
    let session = Session.get("userId");

    return (
        <StyledWrapper>
            {isLoggedIn && <StyledGreeting>환영합니다! {session}</StyledGreeting>}

            {isLoggedIn ? (
                <button onClick={onClickLogout}>로그아웃</button>
            ) : (
                <button onClick={onClickLogin}>로그인</button>
            )}
        </StyledWrapper>
    );
}

export default Toolbar;