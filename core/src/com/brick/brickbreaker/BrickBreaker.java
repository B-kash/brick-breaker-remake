package com.brick.brickbreaker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.brick.brickbreaker.entity.Ball;
import com.brick.brickbreaker.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class BrickBreaker extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ballImg;
	List<Ball> balls;
	int gameState;
	ShapeRenderer shapeRenderer;
	@Override
	public void create () {
		batch = new SpriteBatch();
		ballImg = new Texture("ball.png");
		balls = new ArrayList<Ball>();
		for(int i =0; i < 5 ; i++){
//		int i = 0;
			Ball ball = new Ball(Gdx.graphics.getWidth()/2-Constants.DEFAULT_BALL_RADIUS,i > 0 ? (balls.get(i-1).y-Constants.DEFAULT_BALL_RADIUS) : 0f,Constants.DEFAULT_BALL_RADIUS, Constants.DEFAULT_BALL_SPEED,Constants.DEFAULT_BALL_SPEED,Constants.BALL_STATUS_MOVING);
			balls.add(ball);
//			ball = new Ball(0+65/2,0f,65/2, Constants.DEFAULT_BALL_SPEED,Constants.DEFAULT_BALL_SPEED);
//			balls.add(ball);
//			ball = new Ball(65+65/2,0f,65/2, Constants.DEFAULT_BALL_SPEED,Constants.DEFAULT_BALL_SPEED);
//			balls.add(ball);
//			ball = new Ball(90+65/2,0f,65/2, Constants.DEFAULT_BALL_SPEED,Constants.DEFAULT_BALL_SPEED);
//			balls.add(ball);
//			ball = new Ball(200+65/2,0f,65/2, Constants.DEFAULT_BALL_SPEED,Constants.DEFAULT_BALL_SPEED);
//			balls.add(ball);

		}
		shapeRenderer = new ShapeRenderer();
		gameState = 0;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		if(gameState == 0)
		{
			Gdx.app.log("GameState 0",gameState+"");
			for(Ball ball: balls){
				batch.draw(ballImg, Gdx.graphics.getWidth()/2-Constants.DEFAULT_BALL_RADIUS, 0,Constants.DEFAULT_BALL_RADIUS*2,70);
//			shapeRenderer.circle(balls.get(0).x,balls.get(0).y,balls.get(0).radius);
				ball.move();
				if(ball.y>0){
					ball.setState(Constants.BALL_STATUS_MOVING);
//					gameState = 1;
				}else{
//					ball.setState(Constants.BALL_STATUS_PAUSED);
//					gameState = 0;
				}
			}
			for(Ball ball:balls){
				if(ball.y>0)  gameState=1 ; else gameState=0;
			}

		}
		else if(gameState == 1) {
			Gdx.app.log("GameState 1",gameState+"");
			for(int i = 0 ; i<balls.size() ; i++){
				Ball ball = balls.get(i);
				batch.draw(ballImg, ball.x, ball.y,ball.radius*2,ball.radius*2);
//				shapeRenderer.circle(ball.x, ball.y,ball.radius);
				switch (wallCollision(ball)){
					case Constants.WALL_TOP :
						ball.setySpeed(-Constants.DEFAULT_BALL_SPEED);
						break;
					case Constants.WALL_LEFT :
						ball.setxSpeed(Constants.DEFAULT_BALL_SPEED);

						break;
					case Constants.WALL_RIGHT :
						ball.setxSpeed(-Constants.DEFAULT_BALL_SPEED);
						break;
					case Constants.WALL_BOTTOM :
						ball.setySpeed(Constants.DEFAULT_BALL_SPEED);
						if(ball.getState()==Constants.BALL_STATUS_MOVING)
							ball.setState(Constants.BALL_STATUS_PAUSED);
//						else ball.setState(Constants.BALL_STATUS_MOVING);
						break;
					default:
				}

				ball.move();
			}
			for(Ball ball: balls){
				if(gameState==1 && ball.getState() == Constants.BALL_STATUS_PAUSED){
					gameState = 2;
				}
			}
		}else if(gameState==2){
//			Gdx.app.log("Game is paused in game State",gameState+" ");
			if(Gdx.input.justTouched()) {
				for(Ball ball:balls){
					ball.setySpeed(1*ball.getySpeed());
					ball.setxSpeed(-1*ball.getxSpeed());
					ball.setState(Constants.BALL_STATUS_MOVING);
					ball.move();

				}
			}

		}
		shapeRenderer.end();
		batch.end();

	}

	public int wallCollision(Ball ball) {
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
