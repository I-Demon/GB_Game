package dune.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DuneGame extends ApplicationAdapter {
    private static class Tank {
        private Vector2 position;
        private Texture texture;
        private float angle;
        private float speed;
        private float tempX;
        private float tempY;
        private int screenWidth = 1200;
        private int screenHeight = 720;
        private Ball ball;

        public Tank(float x, float y) {
            this.position = new Vector2(x, y);
            this.texture = new Texture("tank.png");
            this.speed = 200.0f;
            tempX = position.x;
            tempY = position.y;


            float ballX = (float)(Math.random() * (screenWidth - 50) + 30);
            float ballY = (float)(Math.random() * (screenHeight - 50) + 30);
            ball = new Ball( ballX, ballY);
        }

        public void update(float dt) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                angle += 180.0f * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                angle -= 180.0f * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                tempX +=  speed * MathUtils.cosDeg(angle) * dt;
                tempY +=  speed * MathUtils.sinDeg(angle) * dt;
                if (tempX > 30 && tempX < (screenWidth - 30) )	position.x += speed * MathUtils.cosDeg(angle) * dt;
                if (tempY > 30 && tempY < (screenHeight - 30) )	position.y += speed * MathUtils.sinDeg(angle) * dt;
            }


            // Р•СЃР»Рё С‚Р°РЅРє РїСЂРёР±Р»РёР·РёР»Р·СЏ Рє РјСЏС‡РёРєСѓ РЅР° СЂР°СЃСЃС‚РѕСЏРЅРёРµ СѓРґР°СЂР°, С‚Рѕ РјРµРЅСЏРµРј РїРѕР»РѕР¶РµРЅРёРµ РјСЏС‡Р°
            if ((Math.abs(ball.position.x - position.x) < 50) && (Math.abs(ball.position.y - position.y) < 50)) {
                ball.position.x = (float)(Math.random() * (screenWidth - 50) + 30);
                ball.position.y = (float)(Math.random() * (screenHeight - 50) + 30);
            }
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle, 0, 0, 80, 80, false, false);
            ball.render(batch);
        }

        public void dispose() {
            texture.dispose();
            ball.dispose();
        }
    }

    private static class Ball {
        private Vector2 position;
        private Texture texture;

        public Ball(float x, float y) {
            this.position = new Vector2(x, y);
            this.texture = new Texture("Ball.png");
        }


        public void render(SpriteBatch batch) {
            batch.draw(texture, position.x - 25, position.y - 25, 50, 50);
        }

        public void dispose() {
            texture.dispose();
        }
    }

    private SpriteBatch batch;
    private Tank tank;


    @Override
    public void create() {
        batch = new SpriteBatch();
        tank = new Tank(200, 200);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0.6f, 0.4f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        tank.render(batch);
        batch.end();
    }

    public void update(float dt) {
        tank.update(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
        tank.dispose();
    }
}
