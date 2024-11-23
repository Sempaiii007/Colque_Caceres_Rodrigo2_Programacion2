package COVARDES;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainGUI {

    private JFrame frame;
    private final JButton[] botonesJugador1 = new JButton[7];
    private final JButton[] botonesJugador2 = new JButton[7];
    private JButton botonAceptar;
    private JButton botonReiniciar;
    private JLabel labelPuntajeJugador1;
    private JLabel labelPuntajeJugador2;
    private Carta cartaSeleccionadaJugador1 = null;
    private Carta cartaSeleccionadaJugador2 = null;
    private final Juego juego;
    private Image imagenFondo; // Imagen de fondo
    private ReproductorMusica reproductorMusica; // Reproductor de música

    public MainGUI() {
        juego = new Juego();
        inicializarMusica();  // Inicializar música
        inicializarInterfaz();
    }

    private void inicializarMusica() {
        // Lista de canciones
        List<String> listaCanciones = new ArrayList<>();

        String[] posiblesCanciones = {
            "src/resources/Audio/Audio.wav",
            "src/resources/Audio/Audio2.wav",
            "src/resources/Audio/Audio1.wav"
        };

        // Agregar solo los archivos existentes
        for (String ruta : posiblesCanciones) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                listaCanciones.add(ruta);
            } else {
                System.out.println("No se pudo encontrar el archivo: " + ruta);
            }
        }

        // Si hay canciones en la lista, inicializamos el reproductor
        if (!listaCanciones.isEmpty()) {
            reproductorMusica = new ReproductorMusica(listaCanciones);
            reproductorMusica.reproducirActual();  // Reproducir la primera canción
        } else {
            System.out.println("No hay canciones disponibles para reproducir.");
        }
    }

    private void inicializarInterfaz() {
        // Configuración principal del frame

        frame = new JFrame("Juego de Cartas");  // Asegúrate de que esto esté aquí
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 700);
        frame.setLayout(new BorderLayout());
        // Cargar imagen de fondo
        URL urlImagenFondo = getClass().getResource("/resources/fondo/fondo.png");
        if (urlImagenFondo != null) {
            imagenFondo = new ImageIcon(urlImagenFondo).getImage();
            System.out.println("Imagen de fondo cargada correctamente.");
        } else {
            System.out.println("No se pudo cargar la imagen de fondo.");
        }
        // Crear el panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Llamada al paintComponent original
                if (imagenFondo != null) {
                    // Dibujar la imagen de fondo ajustada al tamaño del panel
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Fondo de respaldo en caso de que no se cargue la imagen
                    g.setColor(Color.BLUE); // Cambia el color de fondo a azul
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panelPrincipal.setOpaque(false);  // Asegúrate de que el panel sea transparente

        // Panel de las cartas del jugador 1
        JPanel panelJugador1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelJugador1.setOpaque(false);
        for (int i = 0; i < 7; i++) {
            botonesJugador1[i] = crearBotonCarta("/resources/player1/Carta1_" + i + ".png", i, true);
            panelJugador1.add(botonesJugador1[i]);
        }
        panelPrincipal.add(panelJugador1, BorderLayout.NORTH);

        // Panel de las cartas del jugador 2
        JPanel panelJugador2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelJugador2.setOpaque(false);
        for (int i = 0; i < 7; i++) {
            botonesJugador2[i] = crearBotonCarta("/resources/player2/Carta2_" + i + ".png", i, false);
            panelJugador2.add(botonesJugador2[i]);
        }
        panelPrincipal.add(panelJugador2, BorderLayout.SOUTH);

        // Panel central (botón y puntajes)
        JPanel panelCentral = new JPanel(new GridLayout(4, 0, 0, 0));
        panelCentral.setOpaque(false);
        //puntajes 
        // Crear un LabelConBordes para los puntajes
        labelPuntajeJugador1 = new LabelConBordes("Jugador1:    0 Puntos");
        labelPuntajeJugador2 = new LabelConBordes("Jugador2:    0 Puntos");

        // Alineación centrada
        labelPuntajeJugador1.setHorizontalAlignment(SwingConstants.CENTER);
        labelPuntajeJugador2.setHorizontalAlignment(SwingConstants.CENTER);

        // Establecer color azul eléctrico para el texto
        labelPuntajeJugador1.setForeground(new Color(255, 90, 0));  // Azul eléctrico
        labelPuntajeJugador2.setForeground(new Color(255, 90, 0));  // Azul eléctrico

        // Fuente en negrita, estilo medieval y tamaño más grande
        labelPuntajeJugador1.setFont(new Font("Vivaldi", Font.BOLD, 50));  // Fuente estilo medieval en negrita
        labelPuntajeJugador2.setFont(new Font("Vivaldi", Font.BOLD, 50));  // Fuente estilo medieval en negrita

        // Añadir los JLabels al panel
        panelCentral.add(labelPuntajeJugador1);
        panelCentral.add(labelPuntajeJugador2);

        // Crear botón Aceptar con contorno en el texto
        botonAceptar = new BotonConContorno("Luchar");
        botonAceptar.setPreferredSize(new Dimension(200, 80));  // Ajusta el tamaño según sea necesario
        botonAceptar.setForeground(Color.ORANGE);
        botonAceptar.setFont(new Font("Vivaldi", Font.BOLD, 50));  // Fuente negrita y tamaño grande

        botonAceptar.addActionListener(e -> procesarRonda());
        panelCentral.add(botonAceptar);

        // Crear botón Reiniciar con contorno en el texto
        botonReiniciar = new BotonConContorno("Empezar");
        botonReiniciar.setPreferredSize(new Dimension(200, 80));  // Ajusta el tamaño según sea necesario
        botonReiniciar.setForeground(Color.ORANGE);
        botonReiniciar.setFont(new Font("Vivaldi", Font.BOLD, 50));  // Fuente negrita y tamaño grande

        // Puedes agregar un "shadow" usando un renderizado personalizado si lo deseas.
        botonReiniciar.addActionListener(e -> reiniciarJuego());
        panelCentral.add(botonReiniciar);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Establecer el panel principal como el contenido del frame
        frame.setContentPane(panelPrincipal);

        // Mostrar la interfaz
        frame.setVisible(true);
    }

    private JButton crearBotonCarta(String rutaImagen, int valor, boolean esJugador1) {
        JButton boton = new JButton();
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);

        // Cargar la imagen de la carta
        var urlImagen = getClass().getResource(rutaImagen);
        if (urlImagen != null) {
            ImageIcon iconoCarta = new ImageIcon(urlImagen);
            // Redimensionar la imagen al tamaño adecuado del botón
            Image imagenRedimensionada = iconoCarta.getImage().getScaledInstance(140, 220, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenRedimensionada));
        } else {
            System.out.println("No se pudo cargar la imagen: " + rutaImagen);
        }

        boton.addActionListener(e -> {
            if (esJugador1) {
                cartaSeleccionadaJugador1 = new Carta(valor);
                System.out.println("Jugador 1 seleccionó: " + cartaSeleccionadaJugador1.getValor());
            } else {
                cartaSeleccionadaJugador2 = new Carta(valor);
                System.out.println("Jugador 2 seleccionó: " + cartaSeleccionadaJugador2.getValor());
            }
        });

        return boton;
    }

    private void procesarRonda() {
        // Verificar si ambos jugadores han seleccionado una carta
        if (cartaSeleccionadaJugador1 == null || cartaSeleccionadaJugador2 == null) {
            JOptionPane.showMessageDialog(frame, "ELEGID A VUESTRO GUERRERO");
            return;
        }

        // Procesamos la ronda
        String resultado = juego.jugarRonda(cartaSeleccionadaJugador1, cartaSeleccionadaJugador2);

        // Actualización de puntajes
        labelPuntajeJugador1.setText("Jugador 1: " + juego.getPuntosJugador1() + " puntos");
        labelPuntajeJugador2.setText("Jugador 2: " + juego.getPuntosJugador2() + " puntos");

        // Mostrar resultado de la ronda
        JOptionPane.showMessageDialog(frame, resultado);

        // Verificar si el juego ha terminado
        if (juego.haGanadoJuego()) {
            String ganador = juego.getPuntosJugador1() > juego.getPuntosJugador2() ? "JUGADOR 1" : "JUGADOR 2";
            JOptionPane.showMessageDialog(frame, ganador + " SE ALZA ALA VICTORIA!");
            return;
        }

        // Si hubo un empate, bloquear las cartas seleccionadas
        if (resultado.equals("MANDAD A MAS GUERREROS")) {
            bloquearCartas();
        } else {
        // Si hay un ganador, sumar un punto y reiniciar la ronda
            reiniciarRonda();
        }

        // Resetear las cartas seleccionadas para la siguiente ronda
        cartaSeleccionadaJugador1 = null;
        cartaSeleccionadaJugador2 = null;
    }

    private void bloquearCartas() {
        // Bloquear solo las cartas seleccionadas en caso de empate
        if (cartaSeleccionadaJugador1 != null) {
            botonesJugador1[cartaSeleccionadaJugador1.getValor()].setEnabled(false);
        }
        if (cartaSeleccionadaJugador2 != null) {
            botonesJugador2[cartaSeleccionadaJugador2.getValor()].setEnabled(false);
        }

        // Verificar si todas las cartas de ambos jugadores están bloqueadas
        boolean todasCartasJugador1Bloqueadas = true;
        boolean todasCartasJugador2Bloqueadas = true;

        for (int i = 0; i < 7; i++) {
            if (botonesJugador1[i].isEnabled()) {
                todasCartasJugador1Bloqueadas = false;
            }
            if (botonesJugador2[i].isEnabled()) {
                todasCartasJugador2Bloqueadas = false;
            }
        }

        // Reiniciar la ronda si todas las cartas de ambos jugadores están bloqueadas
        if (todasCartasJugador1Bloqueadas && todasCartasJugador2Bloqueadas) {
            JOptionPane.showMessageDialog(frame, "LOS GUERRERES LOGRARON SOBREVIVIR REGRESANDO PARA PROXIMO ENFRENTAMIENTO");
            reiniciarRonda();
        }
    }

    private void reiniciarRonda() {
        // Desbloquear todas las cartas para la nueva ronda
        for (int i = 0; i < 7; i++) {
            botonesJugador1[i].setEnabled(true);
            botonesJugador2[i].setEnabled(true);
        }

        // Reiniciar las cartas de los jugadores
        cartaSeleccionadaJugador1 = null;
        cartaSeleccionadaJugador2 = null;
    }

    private void reiniciarJuego() {
        // Reiniciar el objeto Juego
        juego.reiniciarJuego();

        // Restablecer los puntajes en la interfaz
        labelPuntajeJugador1.setText("Jugador 1: 0 puntos");
        labelPuntajeJugador2.setText("Jugador 2: 0 puntos");

        // Restablecer las cartas seleccionadas
        cartaSeleccionadaJugador1 = null;
        cartaSeleccionadaJugador2 = null;

        // Restablecer las imágenes de las cartas
        for (int i = 0; i < 7; i++) {
            botonesJugador1[i].setEnabled(true);
            botonesJugador2[i].setEnabled(true);
        }

        // Opcional: Puedes agregar algún mensaje o hacer algo adicional si es necesario
        JOptionPane.showMessageDialog(frame, "LA BATALLA COMIENZA DENUEVO");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
