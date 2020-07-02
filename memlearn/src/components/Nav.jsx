import React, {useContext} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';

import {Link} from "react-router-dom";
import {UserDispatchContext, UserStateContext} from "../context/UserContext";
import {LOGOUT} from "../context/UserContextProvider";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
    link: {
        textDecoration: "none",
        color: "white",
    }
}));

export default function ButtonAppBar() {
    const classes = useStyles();
    const {authStatus} = useContext(UserStateContext)
    const dispatch = useContext(UserDispatchContext)
    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>


                    <Typography variant="h6" className={classes.title}>
                        <Link className={classes.link} to="/">Home</Link>
                    </Typography>
                    <Typography variant="h6" className={classes.title}>
                        <Link className={classes.link} to="/learn">MemGame</Link>
                    </Typography>
                    <Typography variant="h6" className={classes.title}>
                        <Link className={classes.link} to="/about">About</Link>
                    </Typography
                    ><Typography variant="h6" className={classes.title}>
                        <Link className={classes.link} to="/login">LogIn</Link>
                    </Typography>
                    {authStatus === "SUCCESS" && <Typography variant="h6" className={classes.title}>
                        <Button className={classes.link} onClick={()=>{
                            dispatch({type: LOGOUT});
                            localStorage.setItem("planning-user-token", undefined)
                        }}>LogOut</Button>
                    </Typography> }

                </Toolbar>
            </AppBar>
        </div>
    );
}
