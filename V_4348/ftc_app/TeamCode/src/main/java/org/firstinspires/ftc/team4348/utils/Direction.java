package org.firstinspires.ftc.team4348.utils;


public enum Direction
{

    LEFT(-1), RIGHT(1),

    FORWARD(1), BACKWARD(-1);

    public int v;

    Direction(int v)
    {
        this.v = v;
    }
}
