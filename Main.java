import processing.core.PApplet;

/**
 * Snowfall game - When all 3 lives are lost, the game ends 
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