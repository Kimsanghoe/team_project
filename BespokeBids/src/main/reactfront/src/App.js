import React, {Component} from "react";
import {BrowserRouter, Routes, Route} from "react-router-dom";

import Login from "./LoginComponent/Login";
import LandingPage from "./LoginComponent/LandingPage";
import Toolbar from "./LoginComponent/Toolbar";
import NotFound from "./LoginComponent/NotFound";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login />}></Route>
                    <Route path="/main" element={<LandingPage />}></Route>
                    <Route path="*" element={<NotFound />}></Route>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
