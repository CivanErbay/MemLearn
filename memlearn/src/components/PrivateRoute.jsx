import React from "react";
import Route from "react-router-dom/es/Route";

export default function PrivateRoute({component: Component, ...rest}) {
    return <Route {...rest} render={
        props => {
        return <Component {...props}/>
    }}/>
}