package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Class RectangleMove | Task Solution: Ping-pong [#1016]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.11.2018
 */
public class RectangleMove implements Runnable {

    private final Rectangle rect;

    /**
     * Constructor.
     */
    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        boolean x = false;
        boolean y = false;
        while (true) {
            if (x) {
                this.rect.setX(this.rect.getX() - 2);
                if (this.rect.getX() <= 0) {
                    x = false;
                }
            } else {
                this.rect.setX(this.rect.getX() + 2);
                if (this.rect.getX() >= 300) {
                    x = true;
                }
            }

            if (y) {
                this.rect.setY(this.rect.getY() - 3);
                if (this.rect.getY() <= 0) {
                    y = false;
                }
            } else {
                this.rect.setY(this.rect.getY() + 3);
                if (this.rect.getY() >= 300) {
                    y = true;
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}