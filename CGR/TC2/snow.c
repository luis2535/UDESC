// gcc snow.c -lglut -lGL -lGLU -lm -o snow && ./snow

#include <GL/freeglut.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <unistd.h>
#include<stdbool.h>

#define num 5000
typedef struct create_snow
{
	double life;        // tempo de live
    double pos_x;       // posicao x
    double pos_y;       // posicao y
    double pos_z;       // posicao z
    double vel;         // velocidade
    double gravity;     // gravidade
    double radius;      // raio
    bool alive;         // vida

} Snow;
Snow snow[num];

void init_flakes(int part){
    // posições iniciais do floco de neve são definidas
    glColor4f(1.0, 1.0, 1.0, 0.0);
    snow[part].life = (double) (10 + rand() % 4)/5;
    snow[part].pos_x = (double) ((rand() % 120) - 60)/10;
    snow[part].pos_y = 4.5;
    snow[part].pos_z = (double) ((rand() % 150) - 100)/10;
    snow[part].vel = -2.5;
    snow[part].gravity = -0.5;
    snow[part].radius = 0.02;
    snow[part].alive = false;
}

void init_snow() {
   
    int cont = 0;
    glColor4f(1.0, 1.0, 1.0, 1.0);
    for( int i = 0; i < num; i++){
        //cria e posiciona flocos de neve
        glPushMatrix();
            glTranslatef(snow[i].pos_x, snow[i].pos_y, snow[i].pos_z);
            glutSolidSphere(snow[i].radius, 13, 13);
        glPopMatrix();

    if (snow[i].alive){
        //se queda ja começou, continua a queda e aumenta a velocidade
        cont++;
        double fall = -5000 + rand() % 2000;
        if (cont % 15 == 0){
            //desacelera um pouco a queda para ficar mais suave
            snow[i].pos_x += - snow[i].vel/fall;
        }
        snow[i].pos_x += snow[i].vel/fall;
        snow[i].vel += snow[i].gravity;
        snow[i].pos_y += snow[i].vel/1000;
        snow[i].life -= 0.01; // se chega a 0 a trajetória finaliza
    }
    else{
        //faz um teste para ver se o floco deve ou não começar a cair
        if (rand() % 100 < 2){
            snow[i].alive = true;
        }
    }
    if(snow[i].life < 0.0){
        //se o floco ja chegou ao final da sua trajetoria o, o floco é instanciado novamente 
        init_flakes(i);
    }

    }

}

void init(void){
    glClearColor(1.0, 0.0, 0.0, 1.0);
	glOrtho(0, 640, 0, 480, -1, 1);
}
//iniciar a imagem
void display(void){
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glLoadIdentity();
    	
	glTranslatef(0.0f,0.0f,0.0f);
	init_snow();
 
   	glutSwapBuffers();

}
// posições da janela
void ChangeSize(int w, int h){
    GLfloat fAspect;  
  
    // Prevent a divide by zero  
    if(h == 0)  
        h = 1;  
  
    // Set Viewport to window dimensions  
    glViewport(0, 0, w, h);  
  
    fAspect = (GLfloat)w/(GLfloat)h; 
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, fAspect, 2.0f, 15.0f);
	glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}
// Luz do sistema
void SetupRC(){
	glShadeModel( GL_SMOOTH );
	glClearDepth( 1.0f );
	glEnable( GL_DEPTH_TEST );
	glDepthFunc( GL_LEQUAL );
	glHint( GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST );
	glClearColor(0.3, 0.3, 1.0, 1.0);
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
}

int main(int argc, char *argv[]){
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH );
	glutInitWindowSize (900, 680);
	glutCreateWindow ("Snow - Gabriel Anselmo e Luis Bertelli");
    glutReshapeFunc(ChangeSize);
	glutDisplayFunc(display);
	glutIdleFunc(display);
    SetupRC();
    glutMainLoop();
	    

	    


	return 0;
}