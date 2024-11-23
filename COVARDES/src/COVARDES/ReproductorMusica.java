package COVARDES;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.*;

public class ReproductorMusica {

    private Clip clip;
    private final List<String> canciones;
    private int indiceActual;
    private FloatControl controlVolumen; // Control de volumen

    public ReproductorMusica(List<String> listaCanciones) {
        this.canciones = listaCanciones;
        this.indiceActual = 0;
    }

    // Reproducir la canción actual
    public void reproducirActual() {
        detenerMusica(); // Detener cualquier reproducción actual
        try {
            String rutaCancion = canciones.get(indiceActual);
            File archivo = new File(rutaCancion);
            if (!archivo.exists()) {
                System.out.println("El archivo no se encuentra: " + archivo.getAbsolutePath());
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            controlVolumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);  // Obtener el control de volumen
            clip.start(); // Reproducir la canción una sola vez
            System.out.println("Reproduciendo: " + rutaCancion);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Formato de archivo no soportado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.out.println("No se pudo abrir la línea de audio: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // Detener la música actual
    public void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    // Saltar a la siguiente canción
    public void siguienteCancion() {
        indiceActual = (indiceActual + 1) % canciones.size(); // Rotar dentro de la lista
        reproducirActual(); // Reproducir la nueva canción
    }

    // Ajustar el volumen (valor entre 0.0f y 1.0f)
    public void ajustarVolumen(float volumen) {
        if (controlVolumen != null) {
            // Convertir el volumen al rango adecuado
            float volumenDb = (float) (Math.log(volumen) / Math.log(3) * 3);  // Convierte el volumen (0-1) a decibelios
            controlVolumen.setValue(volumenDb);  // Establece el volumen en decibelios
        }
    }
}
