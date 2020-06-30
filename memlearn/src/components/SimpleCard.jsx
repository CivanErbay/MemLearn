import React, {useContext, useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {ThemeContext} from "../App";

const useStyles = makeStyles({
    root: {
        minWidth: 275,
        display: "flex",
        alignItems: 'center',
        justifyContent: 'center',
        margin: "2em"
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
    light: {
        backgroundColor: "rgba(255,255,255,1)"
    },
    dark: {
        backgroundColor: "rgba(0,5,23,1)"
    }
});

export default function SimpleCard({vocab,handleFlip}) { //einzelne Vocab und handleFlip-Methode werden von oben gepassed!

    const classes = useStyles();
    const theme = useContext(ThemeContext)
    const beispiel = 'beispiel';
    const redColor = {backgroundColor: "red", padding: "2em"}
    const blueColor = {backgroundColor: "blue"}
    const newStyle = {...redColor, ...blueColor}
    const themeStyles = {
        dark: {backgroundColor: 'darkgreen', color: "white"},
        light: {backgroundColor: 'lightblue', color: "black" }
    }

    return (
        <Card className={classes.root + " " + classes[theme]} style={themeStyles[theme]}>
            <CardContent>
                <Typography className={classes.title + " " + beispiel}>
                    {vocab.flipState? vocab.description : "???" }
                </Typography>
            </CardContent>
            <CardActions>
                <Button size="small" onClick={() => handleFlip(vocab.id)}>
                    {vocab.flipState ? <p>Hide</p> : <p>Show</p>}
                </Button>
            </CardActions>
        </Card>
    );
}
