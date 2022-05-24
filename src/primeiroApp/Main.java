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

        float x = 0;
        float color_red = 1;
        float color_blue = 0;

        //laço que mantém a janela aberta
        while(!glfwWindowShouldClose(janela))
        {
            if(glfwGetKey(janela, GLFW_KEY_D) == GL_TRUE)
            {
                System.out.println(x += 0.001f);
            }
            if(glfwGetKey(janela, GLFW_KEY_A) == GL_TRUE)
            {
                System.out.println(x -= 0.001f);
            }
            if(glfwGetKey(janela, GLFW_KEY_SPACE) == GL_TRUE)
            {
                color_red = 0;
                color_blue = 1;
            } else
            {
                color_red = 1;
                color_blue = 0;
            }

            glfwPollEvents();

            //limpa o contexto
            glClear(GL_COLOR_BUFFER_BIT);

            //desenha um quadrado
            glBegin(GL_QUADS);
                glColor4f(color_red, 1.0f, color_blue,0);
                glVertex2f(-0.5f+x, 0.5f);

                glColor4f(color_red, 0, color_blue,0);
                glVertex2f(0.5f+x, 0.5f);

                glColor4f(color_red, 0, color_blue,0);
                glVertex2f(0.5f+x, -0.5f);

                glColor4f(color_red, 1.0f, color_blue,0);
                glVertex2f(-0.5f+x, -0.5f);
            glEnd();

            //faz a troca de buffers
            glfwSwapBuffers(janela);
        }

        glfwTerminate();
    }

    public static void main(String[] args) {
        new Main();
    }
}
