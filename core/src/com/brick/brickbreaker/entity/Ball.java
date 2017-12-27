package com.brick.brickbreaker.entity;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by bikash on 12/26/17.
 */

public class Ball extends Circle {
    float xSpeed,ySpeed;
//    float direction;

    public Ball() {
    }

    public Ball(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Ball(float x, float y, float radius, float xSpeed, float ySpeed) {
        super(x, y, radius);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Ball(Vector2 position, float radius, float xSpeed, float ySpeed) {
        super(position, radius);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Ball(Circle circle, float xSpeed, float ySpeed) {
        super(circle);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Ball(Vector2 center, Vector2 edge, float xSpeed, float ySpeed) {
        super(center, edge);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
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

    public void move(){
        x+=xSpeed;
        y+=ySpeed;
    }
}
