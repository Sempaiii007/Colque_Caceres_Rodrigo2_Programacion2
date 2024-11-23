package COVARDES;

import java.awt.*;
import javax.swing.*;

public class LabelConBordes extends JLabel {

    public LabelConBordes(String text) {
        super(text);
        setOpaque(false);  // Asegurarse de que el fondo sea transparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        // No llamar a super.paintComponent(g) para evitar el dibujo predeterminado del texto
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Obtener las métricas del texto para centrarlo
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();

        // Calcular las posiciones X y Y para centrar el texto
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2;

        // Establecer el color para el borde (blanco)
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));  // Grosor del borde

        // Dibujar el borde del texto
        g2d.drawString(getText(), x - 2, y - 2);  // Contorno arriba-izquierda
        g2d.drawString(getText(), x + 2, y - 2);  // Contorno arriba-derecha
        g2d.drawString(getText(), x - 2, y + 2);  // Contorno abajo-izquierda
        g2d.drawString(getText(), x + 2, y + 2);  // Contorno abajo-derecha
        // Ahora dibujar el texto normal encima del borde (color del texto)
        g2d.setColor(getForeground());  // Color del texto original
        g2d.drawString(getText(), x, y);  // Dibujar el texto encima del contorno
    }
}
