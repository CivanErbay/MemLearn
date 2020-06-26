import React, {useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

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
});

export default function SimpleCard({vocab,handleFlip}) { //einzelne Vocab und handleFlip-Methode werden von oben gepassed!

    const classes = useStyles();


    return (
        <Card className={classes.root}>
            <CardContent>
                <Typography className={classes.title}>
                    {vocab.flipState? vocab.name : "???" }
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
