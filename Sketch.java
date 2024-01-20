import processing.core.PApplet;
  import processing.core.PImage;

public class Sketch extends PApplet {

  // initialization
  PImage imgBackground;
  PImage imgSnowflake;

  float fltBlueCircleX = 500;
  float fltBlueCircleY = 650;

  int lives = 3;
  float fltDistance = 100;

  // time
  long longLastHitTime = 0;

  // movement
  boolean boolUp = false;
  boolean boolLeft = false;
  boolean boolDown = false;
  boolean boolRight = false;

  float[] circleY = new float[50];
  boolean[] hideStatus = new boolean[50];
  boolean boolFastFall = false;
  boolean boolSlowFall = false;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(1000, 700);

  }

  /**
   * Called once at the beginning of execution. Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < circleY.length; i++) {

      circleY[i] = random(height);
      if (i >= 20 && i <= 30)
        circleY[i] -= 400;
    }
    noStroke();
    imgBackground = loadImage("SnowBackground.png");
    imgSnowflake = loadImage("snowFlake.png");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   * 
   * @author: Gordon Z
   */
  public void draw() {

    image(imgBackground, 0, 0);

    // to draw snowflakes
    movement();
    for (int i = 0; i < circleY.length; i++) {
      float circleX = width * i / circleY.length;

      if (boolFastFall) {
        circleY[i] += 2;
      } else if (boolSlowFall) {
        circleY[i] += 0.5;
      } else {
        circleY[i]++;
      }

      // check if it should hide snowflake
      if (mousePressed) {
        fltDistance = (float) Math.sqrt(Math.pow(mouseX - circleX, 2) + Math.pow(mouseY - circleY[i], 2)); 
        if (fltDistance < 30)
          hideStatus[i] = true;
      }
     

      if(!hideStatus[i])
      image(imgSnowflake, circleX - 15, circleY[i] - 16);

      fill(25, 110, 247);
      ellipse(fltBlueCircleX, fltBlueCircleY, 30, 30);

      // calculate hitbox
      fltDistance = (float) Math.sqrt(Math.pow(fltBlueCircleX - circleX, 2) + Math.pow(fltBlueCircleY - circleY[i], 2));

      // subtract lives [1 second i-frame after getting hit]
      if (fltDistance < 30 && System.currentTimeMillis() - longLastHitTime > 1000&&!hideStatus[i]) {
        lives--;
        longLastHitTime = System.currentTimeMillis();
      }

      if (circleY[i] > height) {
        circleY[i] = 0;
        hideStatus[i] = false; // so that the game can continiously run
      }
    }

    // display lives
    fill(242, 10, 25);
    if (lives >= 1)
      rect(950, 20, 30, 30);
    if (lives >= 2)
      rect(900, 20, 30, 30);
    if (lives >= 3)
      rect(850, 20, 30, 30);

    // game end
    if (lives <= 0) {
      fill(255);
      rect(0, 0, width, height);
    }

  }


  /**
   * Description: detects movement from keys to move the blue circle.
   * 
   * No param
   * No return
   * 
   * @author: Gordon Z
   */
  public void movement() {

    if (boolUp) {
      fltBlueCircleY -= 3;
      fltBlueCircleY = Math.max(0, fltBlueCircleY);
    }
    if (boolLeft) {
      fltBlueCircleX -= 3;
      fltBlueCircleX = Math.max(0, fltBlueCircleX);
    }

    if (boolDown) {
      fltBlueCircleY += 3;
      fltBlueCircleY = Math.min(height, fltBlueCircleY);
    }

    if (boolRight) {
      fltBlueCircleX += 3;
      fltBlueCircleX = Math.min(width, fltBlueCircleX);
    }
  }

  /**
   * Description: when keys are pressed, respective keys will have their
   * associated movement boolean changed to true
   * 
   * No param
   * No return
   * 
   * @author: Gordon Z
   */
  public void keyPressed() {
    if (keyPressed) {
      if (key == 'w') {
        boolUp = true;
      }
      if (key == 'a') {
        boolLeft = true;
      }
      if (key == 's') {
        boolDown = true;
      }
      if (key == 'd') {
        boolRight = true;
      }
      if (keyCode == UP)
        boolSlowFall = true;
      if (keyCode == DOWN)
        boolFastFall = true;
    }
  }

  /**
   * Description: when key is released, change movement boolean to false.
   * 
   * No param
   * No return
   * 
   * @author: Gordon Z
   */
  public void keyReleased() {
    if (key == 'w') {
      boolUp = false;
    }
    if (key == 'a') {
      boolLeft = false;
    }
    if (key == 's') {
      boolDown = false;
    }
    if (key == 'd') {
      boolRight = false;
    }
    if (keyCode == UP)
      boolSlowFall = false;
    if (keyCode == DOWN)
      boolFastFall = false;
  }

}