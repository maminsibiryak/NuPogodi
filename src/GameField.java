import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GameField extends JPanel implements ActionListener {
    private static final int DOT_SIZE = 32;
    private static final int SIZE = 640;
    private Image wolf;
    private Image bunny;
    private boolean inGame = true;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean wolfActive = false;
    private int bunnyX;
    private int bunnyY;
    private int wolfX;
    private int wolfY;
    private Timer timer;
    private int count = 0;


    public GameField() {
        setBackground(Color.BLACK);
        loadImage();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    private void initGame() {
        createWolf();
        createBunny();
        timer = new Timer(300, this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            g.drawImage(wolf, wolfX, wolfY, this);
            g.drawImage(bunny, bunnyX, bunnyY, this);
            String result = "Ты набрал " + count;
            count++;
            g.setColor(Color.white);
            g.drawString(result, SIZE / 2, SIZE / 2);
        }
        else{
            String str = "Game over";
            Font f = new Font("Arial", 14, Font.BOLD);
            g.setColor(Color.white);

            g.drawString(str, 125, SIZE / 2);


        }
    }

    private void createWolf() {
        wolfX = new Random().nextInt(5) * DOT_SIZE;
        wolfY = new Random().nextInt(5) * DOT_SIZE;
    }

    private void createBunny() {
        bunnyX = new Random().nextInt(10) * DOT_SIZE;
        bunnyY = new Random().nextInt(10) * DOT_SIZE;
    }


    private void loadImage() {
        ImageIcon iconWolf = new ImageIcon("wolf.png");
        wolf = iconWolf.getImage();

        ImageIcon iconBunny = new ImageIcon("bunny.png");
        bunny = iconBunny.getImage();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkCollisions();
            move();
            moveWolf();
            checkWolf();
        }
        repaint();
    }

    private void checkWolf() {
        if (bunnyX==wolfX && bunnyY==wolfY){
            inGame=false;
        }
    }

    private void moveWolf() {
        if (wolfActive) {
            if (bunnyY > wolfY) {
                wolfY += DOT_SIZE;
            }
            if (bunnyY < wolfY) {
                wolfY -= DOT_SIZE;
            }


            if (bunnyX > wolfX) {
                wolfX += DOT_SIZE;
            }
            if (bunnyX < wolfX) {
                wolfX -= DOT_SIZE;
            }

        }
    }

    private void move() {

        if (left) {
            bunnyX -= DOT_SIZE;

        }
        if (right) {
            bunnyX += DOT_SIZE;

        }
        if (up) {
            bunnyY -= DOT_SIZE;

        }
        if (down) {
            bunnyY += DOT_SIZE;

        }
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == 0) {
                left = false;
                right = false;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_SPACE) {
                right = false;
                left = false;
                up = false;
                down = false;
                wolfActive=false;

            }
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
                wolfActive=true;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
                wolfActive=true;
            }
            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                left = false;
                up = true;
                wolfActive=true;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                left = false;
                down = true;
                wolfActive=true;
            }
        }
    }

    private void checkCollisions() {

        if (bunnyX > SIZE) {
            inGame = false;
        }
        if (bunnyX < 0) {
            inGame = false;
        }
        if (bunnyY > SIZE) {
            inGame = false;
        }
        if (bunnyY < 0) {
            inGame = false;
        }
    }
}
