import java.awt.*;
import javax.swing.*;

/**
 * Gradient Panel Class
 *
 * Used to create a gradient panel which is mixture of two colors
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class GradientPanel extends JPanel {

    private Color startColor;
    private Color endColor;

    /**
     * Constructor
     *
     * Initialises the two color fields
     * @param startColor Start Color
     * @param endColor End Color
     */
    public GradientPanel(Color startColor , Color endColor ) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
    }


    /**
     * @param g Graphics object
     */
    @Override protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        GradientPaint gradientPaint = new GradientPaint( panelWidth / 2 , 0 , startColor , panelWidth / 2 , panelHeight , endColor );
        if( g instanceof Graphics2D ) {
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.setPaint( gradientPaint );
            graphics2D.fillRect( 0 , 0 , panelWidth , panelHeight );
        }
    }

}