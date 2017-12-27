package com.brick.brickbreaker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brick.brickbreaker.constants.Constants;
import com.brick.brickbreaker.entity.Ball;

import java.util.ArrayList;
import java.util.List;

public class BrickBreaker extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ballImg;
	List<Ball> balls;
	@Override
	public void create () {
		batch = new SpriteBatch();
		ballImg = new Texture("ball.png");
		balls = new ArrayList<Ball>();
		balls.add(new Ball(Gdx.graphics.getWidth()/2, Constants.DEFAULT_BAT_HEIGHT+Constants.DEFAULT_BALL_RADIUS,Constants.DEFAULT_BALL_RADIUS,Constants.DEFAULT_BALL_SPEED,Constants.DEFAULT_BALL_SPEED));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(Ball ball:balls){
			batch.draw(ballImg, ball.x, ball.y,Constants.DEFAULT_BALL_RADIUS*2,70);

			int collisionSpace = checkCollision(ball);
			afterCollisionAction(collisionSpace,ball);
			ball.move();

		}
		batch.end();
	}

	private void afterCollisionAction(int collisionSpace, Ball ball) {
		switch (collisionSpace) {
			case Constants.WALL_TOP:
				ball.setySpeed(-Constants.DEFAULT_BALL_SPEED);
				break;
			case Constants.WALL_LEFT:
				ball.setxSpeed(Constants.DEFAULT_BALL_SPEED);

				break;
			case Constants.WALL_RIGHT:
				ball.setxSpeed(-Constants.DEFAULT_BALL_SPEED);
				break;
			case Constants.WALL_BOTTOM:
				ball.setySpeed(Constants.DEFAULT_BALL_SPEED);
				break;
			default:
		}
	}

	private int checkCollision(Ball ball) {
		if(ball.x < ball.radius)
			return Constants.WALL_LEFT;
		if(ball.x > Gdx.graphics.getWidth()-ball.radius)
			return Constants.WALL_RIGHT;
		if(ball.y > Gdx.graphics.getHeight()-ball.radius)
			return Constants.WALL_TOP;
		if(ball.y < 0)
			return Constants.WALL_BOTTOM;
		return Constants.WALL_NONE;
	}

	@Override
	public void dispose () {
		batch.dispose();
		ballImg.dispose();
	}
}
