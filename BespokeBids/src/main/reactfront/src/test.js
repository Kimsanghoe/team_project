import React, { useState, useEffect } from 'react';
import axios from 'axios';

function TestComponent() {
    const baseUrl = "http://localhost:8080";

    const [ data, setData ] = useState();

    useEffect(() => {
        putSpringData();
    },[])

    async function putSpringData() {
        await axios
            .get(baseUrl + "/test")
            .then((res)=>{
                console.log(res.data);
                setData(res.data);
            })
            .catch((err)=>{
                console.log(err);
            })
    }

    return (
        <div className="App">
            <h2>data</h2>
            <div>
                {data ? data.map((datas)=>(
                    <div>
                        <div>번호: {datas.id}</div>
                        <div>타입: {datas.user_id}</div>
                    </div>
                )) : ''}
            </div>
        </div>
    );

}

export default TestComponent;