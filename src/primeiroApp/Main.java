package primeiroApp;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;


public class Main
{

    public static void tiro(float centroVerticeEsquerdoX, float centroVerticeDireitoX, boolean inicia)
    {
        float y = 0;

       while(inicia)
        {
            glBegin(GL_QUADS);
            glColor4f(0, 1.0f, 0,0);
            glVertex2f(centroVerticeEsquerdoX, -0.6f + y);

            glColor4f(1.0f, 0, 0,0);
            glVertex2f(centroVerticeDireitoX, -0.6f + y);

            glColor4f(1.0f, 0, 0,0);
            glVertex2f(centroVerticeDireitoX, -0.5f + y);

            glColor4f(0, 1.0f, 0,0);
            glVertex2f(centroVerticeEsquerdoX, -0.5f + y);
            glEnd();

            y = y + 0.1f;

            if(y >= 2) break;
        }

    }
    public Main()
    {
        //inicializa o gl e testa
        if(!glfwInit())
        {
            //lança uma exceção, caso ocorra algum erro na inicialização
            throw new IllegalStateException("Houve uma falha ao iniciar o GLFW");
        }

        //cria uma janela
        long janela = glfwCreateWindow(640, 480, "Janela", 0, 0);

        //mostra a janela
        glfwShowWindow(janela);

        //diz que a janela passa a precisar de um contexto
        glfwMakeContextCurrent(janela);

        //cria o contexto
        GL.createCapabilities();

        //coordenadas
        float x = 0;
        float y = 0;
        //variaveis de controle de cor
        float color_red = 1;
        float color_blue = 0;

        //laço que mantém a janela aberta
        while(!glfwWindowShouldClose(janela))
        {
            if(glfwGetKey(janela, GLFW_KEY_D) == GL_TRUE)
            {
                x += 0.001f;
                if(y >= 0 && y <= 1)
                {
                    y += 0.001f;
                } else
                {
                    y -= 0.001f;
                }


            }
            if(glfwGetKey(janela, GLFW_KEY_A) == GL_TRUE)
            {
                x -= 0.001f;
                y -= 0.001f;
            }


            glfwPollEvents();

            //variáveis que serão passadas como coordenadas para os parametros do tiro
            float centroVerticeEsquerdoX = -0.1f + x;
            float centroVerticeDireitoX = 0.1f + x;


            //limpa o contexto
            glClear(GL_COLOR_BUFFER_BIT);

            //desenha um quadrado
            glBegin(GL_QUADS);
                glColor4f(color_red, 1.0f, color_blue,0);
                glVertex2f(centroVerticeEsquerdoX, -0.8f);

                glColor4f(color_red, 0, color_blue,0);
                glVertex2f(centroVerticeDireitoX, -0.8f);

                glColor4f(color_red, 0, color_blue,0);
                glVertex2f(centroVerticeDireitoX, -0.6f);

                glColor4f(color_red, 1.0f, color_blue,0);
                glVertex2f(centroVerticeEsquerdoX, -0.6f);
            glEnd();

            if(glfwGetKey(janela, GLFW_KEY_SPACE) == GL_TRUE)
            {
               /* color_red = 0;
                color_blue = 1;*/
                tiro(centroVerticeEsquerdoX, centroVerticeDireitoX, true);

            }

            //tiro(centroVerticeEsquerdoX, centroVerticeDireitoX);

            //faz a troca de buffers
            glfwSwapBuffers(janela);
        }


        glfwTerminate();
    }

    public static void main(String[] args)
    {

        new Main();

    }
}
