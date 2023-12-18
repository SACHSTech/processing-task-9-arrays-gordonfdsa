import processing.core.PApplet;

/**
 * Snowfall game
 * @author: Gordon Z
 *
 */
class Main {
  public static void main(String[] args) {
    
    String[] processingArgs = {"MySketch"};
	  Sketch mySketch = new Sketch();
	  PApplet.runSketch(processingArgs, mySketch);
    
  }
  
}