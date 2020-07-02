import React, {useContext, useState} from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import {UserDispatchContext, UserStateContext} from "../context/UserContext";
import {LOGIN, LOGIN_FAILED, LOGIN_SUCCESS} from "../context/UserContextProvider";
import {performLogin} from "../utils/api";
import Redirect from "react-router-dom/es/Redirect";

export default function LoginPage() {

    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const dispatch = useContext(UserDispatchContext);

    function login() {
        dispatch({type:LOGIN})
        performLogin(username, password)  //performLogin loggt uns per Post-Fetch im Backend ein und gibt uns den Token zurück
            .then(response => {            //diesen Token speichern wir im localStorage
                localStorage.setItem("planning-user-token", response);
                console.log(response) //response ist in diesem Fall der Token
                dispatch({type: LOGIN_SUCCESS})     //wenn alles klappt Login = Success
            }).catch(() => {
                dispatch({type: LOGIN_FAILED})
        })
    }


    const {authStatus} = useContext(UserStateContext);
    if(authStatus === 'SUCCESS') {
        return  <Redirect to={"/"}/>
    }

    return (
        <div>
            <TextField
                label="Username"
                value={username}
                onChange={(event) => setUserName(event.target.value)}
            />
            <TextField
                label="Password"
                type="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
            />
            <Button onClick={login}>Login
            </Button>
        </div>
    )
}