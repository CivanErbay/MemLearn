import * as React from "react";
import {UserDispatchContext, UserStateContext} from "./UserContext";
import {useReducer} from "react";

export const LOGIN = "LOGIN"
export const LOGIN_SUCCESS = "LOGIN_SUCCESS"
export const LOGIN_FAILED = "LOGIN_FAILED"
export const LOGOUT = "LOGOUT"

const initialStatus = {
    authStatus: undefined
}

    function reducerFunction (state, action) {
        switch (action.type) {
            case LOGIN:
                return {...state, authStatus: 'PENDING'}
            case LOGIN_SUCCESS:
                return {...state, authStatus: 'SUCCESS'}
            case LOGIN_FAILED:
                return {...state, authStatus: 'FAILED'}
            case LOGOUT:
                return {... initialStatus}
        }

        return state;
    }

export function UserContextProvider ({children}) {


    const [state, dispatch] = useReducer(reducerFunction, initialStatus)
    return <UserStateContext.Provider value={state}>
        <UserDispatchContext.Provider value={dispatch}>
            {children}
        </UserDispatchContext.Provider>
    </UserStateContext.Provider>

}