package com.brick.brickbreaker.entity;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.brick.brickbreaker.constants.Constants;

/**
 * Created by bikash on 12/26/17.
 */

public class Ball extends Circle {
    float xSpeed,ySpeed;
    int state;
//    float direction;

    public Ball() {
    }

    public Ball(float xSpeed, float ySpeed, int state) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.state = state;
    }


    public Ball(float x, float y, float radius, float xSpeed, float ySpeed, int state) {
        super(x, y, radius);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.state = state;
    }

    public Ball(Vector2 position, float radius, float xSpeed, float ySpeed, int state) {
        super(position, radius);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.state = state;
    }

    public Ball(Circle circle, float xSpeed, float ySpeed, int state) {
        super(circle);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.state = state;
    }

    public Ball(Vector2 center, Vector2 edge, float xSpeed, float ySpeed, int state) {
        super(center, edge);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.state = state;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void move(){
        if(state != Constants.BALL_STATUS_PAUSED){
            x+=xSpeed;
            y+=ySpeed;
        }
    }
}
