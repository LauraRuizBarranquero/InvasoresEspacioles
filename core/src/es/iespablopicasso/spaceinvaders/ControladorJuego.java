package es.iespablopicasso.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Clase que implementa el controlador de nuestro videojuego. Realizará la gestión de la entrada del teclado,
 * la gestión de la inicialización, del control del estado del videojuego
 */
public class ControladorJuego {

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTANTES
    static private final int PANTALLA_INICIO = 0;
    static private final int PANTALLA_JUEGO = 1;

    static private final float INDICE_POSICION_XWING = 10f;
    static private final float TASA_DISPARO_ENEMIGOS = 0.01f;

    //RESTO DEL ESTADO

    //Variable para saber el estado en el que estamos:
    // 0 . Pantalla inicio
    // 1. Jugando
    int estadoJuego;

    //Fondo parallax
    ParallaxEscena escena;

    //Tendremos un SpriteBatch para dibujar en la pantalla
    SpriteBatch batch;

    //Nuestra nave principal...
    NavesAliadas xwing;

    //El estado del teclado
    EstadoTeclado et;

    //Ponemos un batallón
    Batallon empire;

    //Ahora un objeto RafagaAliada para almacenar los disparos aliados
    RafagaAliada disparosAliados;

    //Por último un objeto RafagaEnemiga para almacenar disparos enemigos
    RafagaEnemigos disparosEmpire;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //El constructor creará a su vez: personajes iniciales y fondo
    public ControladorJuego() {



    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //Resto de comportamientos


    //El controlador tendrá que saber que pasa cuando hay que pintarse
    public void render() {
        ///Realizo el control de estado
        this.controlEstado();

        //borramos imagenes previas
        Gdx.gl.glClearColor(v:0, v1:0, v2:0, v3:1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Zona de pruebas, la nave es inestable, revienta. Random r = new Random();
        //if (r.nextInt(100)=1) nave.explota()

        if (estadoJuego == 1) {
            //Escena de fondo
            escena.render(batch);
            //Renderizar imagenes
            xwing.pintarse(batch);
            //Pintar enemigos
            empire.pintarse(batch);
            //Pintar diparos aliados
            disparosAliados.pintarse(batch);
            //Pintar disparos enemigos
            disparosEmpire.pintarse(batch);
        }
        else {
            //Pantalla inicial
            dibujarPantallaInicial();
        }


    }

    //El controlador tendrá que saber como finalizar y cerrar recursos
    public void dispose() {
        //aquí tengo que liberar los objetos que integren texturas y también al batch de dibujo




    }

    //Método de control del estado. Es interno. Para ayudar al método render
    private void controlEstado() {
        if (estadoJuego == 0)
        {
            controlEstadoPantallaInicio();
        }
        else {
            controlEstadoJugando();
        }
    }

    //Método de control del estado cuando jugamos.
    private void controlEstadoJugando() {

    DisparoEnemigo disparo;

    //Actualizo el teclado
        boolean recienTocado;

        recienTocado = Gdx.input.justTouched();
        if (recienTocado) {
            et.simulaTeclado(Gdx.input.getX(), Gdx.input.getY());
            if (et.isTeclaArriba()){
                disparosAliados.crearDisparo(xwing.getPosX(), xwing.getPosY());
            }
        }

        //Animamos el parallax
        escena.animar();

        //Movemos la nave
        xwing.moverse(et); //Segun el teclado

        //Movemos las naves eemigas
        empire.moverseEnArmonia();








    }

    private void controlEstadoPantallaInicio() {

    }

    //Al principio, y cada vez que recomencemos la partida, se reinician los objetos
    private void inicializarObjetos() {



    }

    private void dibujarPantallaInicial() {

    }
}

