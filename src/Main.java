import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Filename: Main.java
 * Author: Joseph Julian
 * Date: 02 April 2023
 * Description: Draws six (6) 3D shapes into the scene using the data from the ThreeDimensionalShapes class.
 */
public class Main extends GLJPanel implements GLEventListener, KeyListener {

    private final JLabel label;

    private double rotateX = 30; // Rotation along the X axis
    private double rotateY = 30; // Rotation along the Y axis
    private double rotateZ = 0; // Rotation along the Z axis
    private double translateX = 0; // Translation along the X axis
    private double translateY = 0; // Translation along the Y axis
    private double translateZ = -2.0; // Translation along the Z axis
    private double scale = 0.75; // Scale or size of the shape

    /**
     * Main constructor
     */
    private Main() {
        super(new GLCapabilities(null));
        setPreferredSize(new Dimension(640, 480)); // Minimum size requirement
        addGLEventListener(this);
        addKeyListener(this);

        // Create the label and set its properties
        Font font = new Font("Roboto", Font.PLAIN, 14);
        label = new JLabel("", SwingConstants.CENTER);
        label.setFont(font);
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the GLJPanel and label to the panel
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Shape Shifter 3D - CMSC 405 - Joseph Julian");
        Main panel = new Main();

        // Create a new JLabel object and set its text
        Font font = new Font("Roboto", Font.PLAIN, 16);
        JLabel label = new JLabel("<html><center>ROTATION (&uarr;&darr;&larr;&rarr;,&nbsp;PgUp/PgDn) &emsp; TRANSLATION (WASD, Q/E) &emsp; SCALE (+/-)</center></html>", SwingConstants.CENTER);
        label.setFont(font);

        // Add the label to the content pane of the JFrame
        window.getContentPane().add(label, BorderLayout.NORTH);
        window.getContentPane().add(panel, BorderLayout.CENTER);

        window.pack();
        window.setLocation(50, 50);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        panel.requestFocusInWindow();
    }

    /**
     * Draws a 3D shape with color and translation.
     *
     * @param gl2        Graphics interface.
     * @param shape      The 3D shape to draw.
     * @param scale      The scale of the shape.
     * @param translateX Translation along the x-axis.
     * @param translateY Translation along the y-axis.
     * @param translateZ Translation along the z-axis.
     */
    private void drawShape(GL2 gl2, ThreeDimensionalShapes shape, double scale, double translateX, double translateY, double translateZ) {
        gl2.glPushMatrix();
        gl2.glScaled(scale, scale, scale); // Scale unit to desired size
        gl2.glTranslated(translateX, translateY, translateZ); // Translate unit to desired position

        for (int i = 0; i < shape.faces.length; i++) {
            gl2.glPushMatrix();
            double red = shape.rgb[i][0];
            double green = shape.rgb[i][1];
            double blue = shape.rgb[i][2];

            // Draws shape faces with appropriate color
            gl2.glColor3d(red, green, blue);
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < shape.faces[i].length; j++) {
                int vertex = shape.faces[i][j];
                gl2.glVertex3dv(shape.vertices[vertex], 0);
            }
            gl2.glEnd();
            gl2.glPopMatrix();
        }

        // Draw shape outlines
        gl2.glColor3d(0, 0, 0);
        for (int i = 0; i < shape.faces.length; i++) {
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < shape.faces[i].length; j++) {
                int vertex = shape.faces[i][j];
                gl2.glVertex3dv(shape.vertices[vertex], 0);
            }
            gl2.glEnd();
        }

        gl2.glPopMatrix();
    }

    /**
     * The display method is called when the panel needs to be redrawn.
     *
     * @param drawable display for rendering
     */
    public void display(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        GLU glu = new GLU();

        // Set the viewport and clear the screen
        gl2.glViewport(0, 0, getWidth(), getHeight());
        gl2.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Set background color to white
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        // Set the projection matrix
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        double aspectRatio = (double) getWidth() / (double) getHeight();
        double fov = 45.0;
        double near = 0.1;
        double far = 100.0;
        glu.gluPerspective(fov, aspectRatio, near, far);

        // Set the model-view matrix
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();

        // Calculate bounds for translateX, translateY, and translateZ
        double maxTranslate = 1.5 - scale * 0.5;
        double minTranslate = -1.5 + scale * 0.5;
        double maxTranslateZ = 1.5 - scale * 0.125;
        double minTranslateZ = -1.5 + scale * 0.125;

        // Clamp translateX, translateY, and translateZ to bounds
        translateX = Math.min(maxTranslate, Math.max(minTranslate, translateX));
        translateY = Math.min(maxTranslate, Math.max(minTranslate, translateY));
        translateZ = Math.min(maxTranslateZ, Math.max(minTranslateZ, translateZ));

        // Bound scale to the window size
        double maxScale = Math.min(640.0 / getWidth(), 480.0 / getHeight());
        scale = Math.max(0.1, Math.min(maxScale, scale));

        // Ensure that rotateX, rotateY, and rotateZ are in the range of 0 to 360 degrees
        while (rotateX >= 360) {
            rotateX -= 360;
        }
        while (rotateX < 0) {
            rotateX += 360;
        }
        while (rotateY >= 360) {
            rotateY -= 360;
        }
        while (rotateY < 0) {
            rotateY += 360;
        }
        while (rotateZ >= 360) {
            rotateZ -= 360;
        }
        while (rotateZ < 0) {
            rotateZ += 360;
        }

        gl2.glTranslated(translateX, translateY, translateZ); // Translate unit to desired position
        gl2.glRotated(rotateX, 1, 0, 0); // Rotate along X-axis
        gl2.glRotated(rotateY, 0, 1, 0); // Rotate along Y-axis
        gl2.glRotated(rotateZ, 0, 0, 1); // Rotate along Z-axis
        gl2.glScaled(scale, scale, scale); // Scale unit to desired size

        // Draw shapes
        drawShape(gl2, ThreeDimensionalShapes.SURFACE, 0.5, 0, -0.05, 0);
        drawShape(gl2, ThreeDimensionalShapes.OCTAHEDRON, 0.125, 0, 1.5, 0);
        drawShape(gl2, ThreeDimensionalShapes.CUBE, 0.125, 1.5, 1.5, 1.5);
        drawShape(gl2, ThreeDimensionalShapes.RECTANGULAR_PRISM, 0.125, -1.5, 1.5, -1.5);
        drawShape(gl2, ThreeDimensionalShapes.TETRAHEDRON, 0.125, 1.5, 1.5, -1.5);
        drawShape(gl2, ThreeDimensionalShapes.PYRAMID, 0.125, -1.5, 1.5, 1.5);

        // Update the label with the current values
        label.setText(String.format("<html><center>Rotation: (%.2f, %.2f, %.2f) &emsp; Translation: (%.2f, %.2f, %.2f) &emsp; Scale: %.2f<html><center>", rotateX, rotateY, rotateZ, translateX, translateY, translateZ, scale));

        gl2.glFlush();
    }

    /**
     * GLEventListener interface method, called when the window is initialized
     *
     * @param drawable display for rendering
     */
    public void init(GLAutoDrawable drawable) {
        try {
            GL2 gl2 = drawable.getGL().getGL2();
            GLU glu = new GLU();

            // Set up projection matrix stack
            gl2.glMatrixMode(GL2.GL_PROJECTION);
            gl2.glLoadIdentity();
            glu.gluOrtho2D(-1, 1, -1, 1);
            gl2.glPushMatrix();

            // Set up view matrix
            gl2.glMatrixMode(GL2.GL_MODELVIEW);
            gl2.glLoadIdentity();
            glu.gluLookAt(0, 0, 1, 0, 0, 0, 0, 1, 0);
            gl2.glPushMatrix();

            // Set up other OpenGL state
            gl2.glClearColor(0, 0, 0, 1);
            gl2.glEnable(GL2.GL_DEPTH_TEST);

            final float LINE_WIDTH = 2.0f;
            gl2.glLineWidth(LINE_WIDTH);

        } catch (Exception e) {
            System.err.println("Error during initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * GLEventListener interface method, called when the window is resized by the user
     *
     * @param drawable display for rendering
     * @param x        X position of the window
     * @param y        Y position of the window
     * @param width    Width of the window
     * @param height   Height of the window
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    /**
     * GLEventListener interface method, called when the window is disposed
     *
     * @param drawable display for rendering
     */
    public void dispose(GLAutoDrawable drawable) {
    }

    /**
     * KeyListener interface method, called when a key is pressed
     *
     * @param e key pressed
     */
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            // Rotation
            case KeyEvent.VK_UP -> rotateX += 5;
            case KeyEvent.VK_DOWN -> rotateX -= 5;
            case KeyEvent.VK_LEFT -> rotateY -= 5;
            case KeyEvent.VK_RIGHT -> rotateY += 5;
            case KeyEvent.VK_PAGE_UP -> rotateZ += 5;
            case KeyEvent.VK_PAGE_DOWN -> rotateZ -= 5;

            // Translation
            case KeyEvent.VK_W -> translateY += 0.1;
            case KeyEvent.VK_S -> translateY -= 0.1;
            case KeyEvent.VK_A -> translateX -= 0.1;
            case KeyEvent.VK_D -> translateX += 0.1;
            case KeyEvent.VK_Q -> translateZ += 0.1;
            case KeyEvent.VK_E -> translateZ -= 0.1;

            // Scale
            case KeyEvent.VK_PLUS, KeyEvent.VK_ADD, KeyEvent.VK_EQUALS -> scale += 0.1;
            case KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT -> scale -= 0.1;

            // Reset
            case KeyEvent.VK_ESCAPE -> {
                rotateX = 30;
                rotateY = 30;
                rotateZ = 0;
                translateX = 0;
                translateY = 0;
                translateZ = -2.0;
                scale = 0.75;
            }
        }
        repaint();
    }

    /**
     * KeyListener interface method, called when a key is released
     *
     * @param evt key released
     */
    public void keyReleased(KeyEvent evt) {
    }

    /**
     * KeyListener interface method, called when a key is typed
     *
     * @param evt key typed
     */
    public void keyTyped(KeyEvent evt) {
    }
}