/*
Student Name - Priyanka Penikalapati, Student ID- 999991075
Assignment No - 4, Course Section - 030 
To Draw Random Triangles and Set Random colors 
*/
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;

 class RandomTriangles extends JPanel {
     public void paint(Graphics g) {
         super.paintComponent(g);
         Graphics2D draw = (Graphics2D) g;
         float strokeThickness = 3.0f;

        BasicStroke stroke = new BasicStroke(strokeThickness,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        draw.setStroke(stroke);
        //To Generate Random Numbers 
        Random randGen = new Random();
        int curHeight = this.getHeight();
        int curWidth = this.getWidth();

        int [] x = new int [3];
        int [] y = new int [3];
    
        // To repeat the Loop 500 times
        for(int t = 0; t <= 500; t++){
            for(int i = 0; i < 3; i++) {
               x[i] = randGen(curWidth);
               y[i] = randGen(curHeight);
           }
            // To Draw a Triangle 
            draw.fillPolygon(x, y, 3);
            // To Set Color Code Randomly 
            draw.setColor(new Color(randGen.nextInt(256),randGen.nextInt(256), randGen.nextInt(256)));
        }
    }
    private int randGen(int num) {
        Random rand = new Random();
        int  n = rand.nextInt(num);
        return n;
    }
}

class Triangles {
     public static void main(String[] args) {
         Triangles triangles = new Triangles();
    }
        Triangles() {
        JFrame frame = new JFrame("Random Triangles");
        frame.add(new RandomTriangles());
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

}