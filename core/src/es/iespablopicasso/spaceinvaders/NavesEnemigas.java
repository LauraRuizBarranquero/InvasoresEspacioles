package es.iespablopicasso.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase NavesEnemigas. Representa a una nave enemiga. Estas naves pueden disparar
 * y también explotan si colisionan, y son manejadas por nuestra simple y azarosa IA
 * Esta clase hereda de la clase NaveEspacial
 */
public class NavesEnemigas extends NaveEspacial {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Definimos una constante para el nombre del fichero que contiene el sprite principal
    //y otra para el sprite secundario
    static private final String NOMBRE_SPRITE = "enemigo.png";
    static private final String NOMBRE_SPRITE_2 = "enemigo2.png";

    //Definimos una constante para el nombre del fichero que contiene el sprite explosión
    static private final String NOMBRE_SPRITE_EXP = "explosion.png";
    static private final float VELOCIDAD_INICIAL_Y = -3f;
    static private final float VELOCIDAD_INICIAL_X = 1.0f;
    static private final short MAX_PASOS = 200;
    static private final short TASA_CAMBIO_SPRITE = 40;

    //Imagen que contiene la segunda textura
    protected Texture img2;
    protected short iPasos;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTORES
    public NavesEnemigas() {


    }

    public NavesEnemigas(float nuevaPosX,float nuevaPosY) {

        nuevaPosX= this.posX;
        nuevaPosY= this.posY;
    }


    //Resto de comportamiento

    //vamos a movernos automático

    public void moverseauto() {

        if(iPasos <= MAX_PASOS){
            iPasos++;
        }else{

            posY = posY - VELOCIDAD_INICIAL_Y;
            iPasos = 0;
        }

    }

    //Con estos dos movimientos, las naves no son independientes, se dejan "ordenar" por su escuadrón
    //orden de movimiento horizontal

    public void desplazarnos(boolean bDerecha) {

        posX+=VELOCIDAD_INICIAL_X;
    }


    //orden de movimiento vertical

    public void avanzar() {

        posY+=VELOCIDAD_INICIAL_Y;

    }


    //Sobreescribimos pintarse para que maneje dos sprites en vez de uno
    @Override
    public void pintarse(SpriteBatch miSB) {

        Texture temporal = img;

        float tempo = 0;

        if (tempo <= TASA_CAMBIO_SPRITE) {

            temporal = img;
            miSB.begin();
            miSB.draw(temporal, posX, posY);
            miSB.end();
            tempo++;

        } else {

            temporal = img2;
            miSB.begin();
            miSB.draw(temporal, posX, posY);
            miSB.end();
            tempo = 0;

        }
    }




    //Sobreescribimos dispose para eliminar nuestro segundo sprite/texture
    @Override
    public void dispose(){
        if (img != null){
            img.dispose();
        } else if (img2 != null){
            img2.dispose();
        }


    }

}
