package dune.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Tank {
    private Vector2 position;
    private Vector2 tmp;
    private TextureRegion[] textures;
    private float angle;
    private float speed;

    private float moveTimer;
    private float timePerFrame;
    private Projectile bullet;
    private boolean bulletActive;

    public Vector2 getPosition() {
        return position;
    }

    public Tank(TextureAtlas atlas, float x, float y) {
        this.position = new Vector2(x, y);
        this.tmp = new Vector2(0, 0);
        this.textures = new TextureRegion(atlas.findRegion("tankanim")).split(64, 64)[0];
        this.speed = 140.0f;
        this.timePerFrame = 0.08f;
        this.bullet = new Projectile(atlas, 0, 0);
        this.bulletActive = false;

    }

    private int getCurrentFrameIndex() {
        return (int) (moveTimer / timePerFrame) % textures.length;
    }

    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angle += 180.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angle -= 180.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.add(speed * MathUtils.cosDeg(angle) * dt, speed * MathUtils.sinDeg(angle) * dt);
            moveTimer += dt;
        } else {
            if (getCurrentFrameIndex() != 0) {
                moveTimer += dt;
            }
        }

        // При нажатии на кнопку К запускаем снаряд, если он был не активен
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            if (!bulletActive) {        // Если снаряд не активен
                Vector2 start = position.cpy().add(40 * MathUtils.cosDeg(angle), 40 * MathUtils.sinDeg(angle));

                bullet.setPosition(start);  // Устанавливаем стартовую позицию перед дулом танка
                bullet.setup(start, angle); // Запускаем
                bulletActive = true;        // активируем снаряд
            }
        }

        if (bulletActive) {
            bullet.update(dt);          // рассчитываем положение снаряда
            checkBulletBounds();        // Проверяем границы снаряда. При выходе за границы экрана, деактивируем снаряд
        }


        checkBounds();
    }

    public void checkBounds() {
        if (position.x < 40) {
            position.x = 40;
        }
        if (position.y < 40) {
            position.y = 40;
        }
        if (position.x > 1240) {
            position.x = 1240;
        }
        if (position.y > 680) {
            position.y = 680;
        }
    }

    public void checkBulletBounds() {
        if ((bullet.getPosition().x < 0) || (bullet.getPosition().x > 1280)
                ||  (bullet.getPosition().y < 0) || (bullet.getPosition().y > 720))
        {
            bulletActive = false;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(textures[getCurrentFrameIndex()], position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle);
        if (bulletActive) {bullet.render(batch); }
    }
}

