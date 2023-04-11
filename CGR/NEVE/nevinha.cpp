// g++ nevinha.cpp -lglut -lGL -lGLU -lm -o nevinha && ./nevinha

#include "nevinha.hpp"

#define NUM 10000 // Numero de particulas

Nevinha neve[NUM];


void iniciaFlocos(int part) {
	glColor4f(0.9, 0.9, 0.9, 0.0);
    neve[part].tempoVida = (double) (8 + rand() % 4)/5;
    neve[part].gravidade = -0.5;
    neve[part].pos_x = (double) ((rand() % 100) - 60)/10;
    neve[part].pos_z = (double) ((rand() % 200) - 100)/10;
    neve[part].pos_y = 4.5;
    neve[part].velocidade = -5.0;
    neve[part].vivo = false;
	neve[part].raio=0.005;
}

void nevar() {
	int cont=0;
	glColor4f(0.9, 0.9, 0.9, 1.0);

    for(int i = 0; i < NUM; i++ ){	

        glPushMatrix();
            glTranslatef(neve[i].pos_x, neve[i].pos_y, neve[i].pos_z);
            glutSolidSphere(neve[i].raio, 10, 10);
        glPopMatrix();
		
		// glVertex3f( neve[i].pos_x, neve[i].pos_y, neve[i].pos_z);


        if (neve[i].vivo) {
			cont++;
            //direção de movimento
            double quedax = -5000 + rand() % 2000;
			if(cont % 15 == 0){
				neve[i].pos_x += -neve[i].velocidade /quedax;
			}
			neve[i].pos_x += neve[i].velocidade /quedax;
	    neve[i].velocidade += neve[i].gravidade;         
            neve[i].pos_y += neve[i].velocidade /2000;
            neve[i].tempoVida -= 0.01;

        }
        else {
            if (rand() % 100 < 2) {
                neve[i].vivo = true;
            }
        }

        if (neve[i].tempoVida < 0.0) {
            iniciaFlocos(i);
        }
    }
}

void init(void){
	glClearColor(0.0, 0.0, 0.0, 1.0);
	glOrtho(0, 640, 0, 480, -1, 1);
}


void display(void){
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glLoadIdentity();
    

	glTranslatef(0.0f,0.0f,-6.0f);
	
	nevar();

   	glutSwapBuffers();
}

int main(int argc, char *argv[]){
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH );
	glutInitWindowSize (900, 680);
	glutCreateWindow ("Nevinha");

	glutDisplayFunc(display);
	glutIdleFunc(display);

	    glMatrixMode(GL_PROJECTION);
	    glViewport(0, 0, 800, 600);
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    GLfloat aspect = (GLfloat) 800 / 600;
	    gluPerspective(45, aspect, 2.0f, 15.0f);
	    glMatrixMode(GL_MODELVIEW);
	    glShadeModel( GL_SMOOTH );
	    glClearDepth( 1.0f );
	    glEnable( GL_DEPTH_TEST );
	    glDepthFunc( GL_LEQUAL );
	    glHint( GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST );
	    glClearColor(0.15, 0.15, 0.15, 1.0);

	    GLfloat mat_shininess[] =  { 50.0 };
	    GLfloat mat_specular[] =   { 0.75, 0.75, 0.75, 0.75 };

	    GLfloat light_ambient[] =  {  0.5,  0.5,  0.5, 1.0 };
	    GLfloat light_diffuse[] =  {  1.0,  1.0,  1.0, 1.0 };
	    GLfloat light_specular[] = {  1.0,  1.0,  1.0, 1.0 };
	    GLfloat light_position[] = {  10.0,  2.0,  10.0, 0.0 };

	    glMaterialfv(GL_FRONT, GL_SPECULAR,  mat_specular);
	    glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);

	    glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient);
	    glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse);
	    glLightfv(GL_LIGHT0, GL_SPECULAR, light_specular);

	    glLightfv(GL_LIGHT0, GL_POSITION, light_position);

	    glEnable(GL_LIGHTING);
	    glEnable(GL_LIGHT0);

	    glEnable(GL_COLOR_MATERIAL);

	    glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);

	    glutMainLoop();


	return 0;
}
