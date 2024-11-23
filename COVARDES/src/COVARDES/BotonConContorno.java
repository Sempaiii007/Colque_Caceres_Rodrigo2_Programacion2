package COVARDES;

import java.awt.*;
import javax.swing.*;

public class BotonConContorno extends JButton {

    public BotonConContorno(String texto) {
        super(texto);
        setOpaque(false);  // Asegura que el botón tenga un fondo transparente
        setContentAreaFilled(false);  // Eliminar área de contenido del fondo
        setBorderPainted(false);  // Eliminar el borde 
    }

    @Override
    protected void paintComponent(Graphics g) {
       
        // Usar Graphics2D para mayor control sobre la edicion de texto
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // Suavizado de letras 

        // Establecer color para el contorno (bordes) del texto
        g2d.setColor(Color.BLACK);  // El color del contorno 
        g2d.setFont(getFont());  // Usar la misma fuente del botón

        // Calcular posición para centrar el texto
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2;

        // Grosor del contorno
        g2d.setStroke(new BasicStroke(4));  // Contorno de grosor 

        // Dibujar el texto con el contorno
        g2d.drawString(getText(), x - 2, y - 2);  // Contorno arriba-izquierda
        g2d.drawString(getText(), x + 2, y -2);  // Contorno arriba-derecha
        g2d.drawString(getText(), x - 2, y + 2);  // Contorno abajo-izquierda
        g2d.drawString(getText(), x + 2, y + 2);  // Contorno abajo-derecha

        // Establecer color del texto normal 
        g2d.setColor(getForeground());  // Color del texto original
        g2d.drawString(getText(), x, y);  // Dibujar el texto encima del contorno
    }
}
