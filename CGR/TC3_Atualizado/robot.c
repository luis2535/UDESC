// gcc robot.c -lglut -lGL -lGLU -lm -o robot && ./robot

#include <GL/glut.h>
#include <math.h>

int shoulder = 0, elbow = 0;
int arm1 = 0, arm2= 0, forearm1 = 0, forearm2 =0, leg1 = 0, leg2 = 0, knee1 = 0, knee2 = 0;
int ativa = 0;
int cont = 0, cont2 = 0;

void init(void)
{
   glClearColor(0.0, 0.0, 0.0, 1.0);
   glOrtho (0, 640, 0, 480, -1 ,1);    
}

// Rotation
static GLfloat yRot = 0.0f;
static GLfloat xRot = 0.0f;
GLfloat bra = 0.0f;
GLfloat arm = 90.0f, per = 0.0;

// Change viewing volume and viewport.  Called when window is resized  

void ChangeSize(int w, int h){

    GLfloat fAspect;  
  
    // Prevent a divide by zero  
    if(h == 0)  
        h = 1;  
  
    // Set Viewport to window dimensions  
    glViewport(0, 0, w, h);  
  
    fAspect = (GLfloat)w/(GLfloat)h;  
  
    // Reset coordinate system  
    glMatrixMode(GL_PROJECTION);  
    glLoadIdentity();  
  
    // Produce the perspective projection  
    gluPerspective(50.0f, fAspect, 1.0, 70.0);  
  
    glMatrixMode(GL_MODELVIEW);  
    glLoadIdentity();  

}  
  
  
// This function does any needed initialization on the rendering context.  Here it sets up and initializes the lighting for the scene.  
void SetupRC(){  

    // Light values and coordinates  
    GLfloat  whiteLight[] = { 0.05f, 0.05f, 0.05f, 1.0f };  
    GLfloat  sourceLight[] = { 0.25f, 0.25f, 0.25f, 1.0f };  
    GLfloat  lightPos[] = { -10.f, 5.0f, 5.0f, 1.0f };  
  
    glEnable(GL_DEPTH_TEST);    // Hidden surface removal  
    glFrontFace(GL_CCW);        // Counter clock-wise polygons face out  
    glEnable(GL_CULL_FACE);     // Do not calculate inside  
  
    // Enable lighting  
    glEnable(GL_LIGHTING);  
  
    // Setup and enable light 0  
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT,whiteLight);  
    glLightfv(GL_LIGHT0,GL_AMBIENT,sourceLight);  
    glLightfv(GL_LIGHT0,GL_DIFFUSE,sourceLight);  
    glLightfv(GL_LIGHT0,GL_POSITION,lightPos);  
    glEnable(GL_LIGHT0);  
  
    // Enable color tracking  
    glEnable(GL_COLOR_MATERIAL);  
      
    // Set Material properties to follow glColor values  
    glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);  
  
    // Background  color
    
    glClearColor(0.2f, 0.6f, 0.80f, 1.0f);    

}  
// Respond to arrow keys (rotate castle)
void SpecialKeys(int key, int x, int y){  

    if(key == GLUT_KEY_UP)
        xRot -= 5.0f;
    if(key == GLUT_KEY_DOWN)
        xRot += 5.0f;
    if(key == GLUT_KEY_LEFT)  
        yRot -= 5.0f;
    if(key == GLUT_KEY_RIGHT)  
        yRot += 5.0f;   
                  
    yRot = (GLfloat)((const int)yRot % 360);  
    xRot = (GLfloat)((const int)xRot % 360); 
  
    // Refresh the Window  
    glutPostRedisplay();  
}
void entrada(unsigned char key, int x, int y){
    switch (key) {
        case 's':
        case 'S':
            if(shoulder < 150){
            shoulder = (shoulder + 5) % 180;
            glutPostRedisplay();
            }
            break;
        case 'd':
        case 'D':
            if (shoulder > 0){
            shoulder = (shoulder - 5) % 180;
            glutPostRedisplay();
            }
            break;
        case 'w':
        case 'W':
            if(elbow < 125){
                elbow = (elbow + 5) % 130;
                glutPostRedisplay();
            }
            break;
        case 'e':
        case 'E':
        if(elbow > 0){
            elbow = (elbow - 5) % 130;
            glutPostRedisplay();
        }
            break;
        case 'q':
        case 'Q':
            leg1 = (leg1 - 5) % 90;
            knee1 = (knee1 + 5) % 90;
            leg2 = (leg2 - 5) % 90;
            arm1 = (arm1 - 5) % 90;
            forearm2 = (forearm2 - 5) % 90;
            glutPostRedisplay();
            break;
        case 'r':
        case 'R':
            arm1 = arm2 = forearm1 = forearm2 = leg1 = leg2 = knee1 = knee2 =  shoulder = elbow = 0 ;
            cont = cont2 = 0;
            glutPostRedisplay();
            break;
        case 'z':
        case 'Z':
            if(leg1 > -80){
                leg1 = (leg1 - 5) % 90;
                knee1 = (knee1 + 5) % 90;
                arm1 = (arm1 + 5) % 90;
                arm2 = (arm2 - 5) % 90;
            }
            glutPostRedisplay();
            break;
        case 'x':
        case 'X':
            if(leg1 < 0){
                leg1 = (leg1 + 5) % 90;
                knee1 = (knee1 - 5) % 90;
                arm1 = (arm1 - 5) % 90;
                arm2 = (arm2 + 5) % 90;
            }
            glutPostRedisplay();
            break;
        case 'c':
        case 'C':
            if(leg2 > -80){
                leg2 = (leg2 - 5) % 90;
                knee2 = (knee2 + 5) % 90;
                arm1 = (arm1 + 5) % 90;
                arm2 = (arm2 - 5) % 90;
            }
            glutPostRedisplay();
            break;
        case 'v':
        case 'V':
            if(leg2 < 0){
                leg2 = (leg2 + 5) % 90;
                knee2 = (knee2 - 5) % 90;
                arm1 = (arm1 - 5) % 90;
                arm2 = (arm2 + 5) % 90;
            }
            glutPostRedisplay();
            break;
        case 'k':
        case 'K':
            if(cont >= 0 && cont < 17){
                leg1 = (leg1 - 5) % 90;
                knee1 = (knee1 + 5) % 90;
                arm1 = (arm1 + 5) % 90;
                arm2 = (arm2 - 5) % 90;
                cont++;
            }else if(cont >= 17 && cont < 33){
                leg1 = (leg1 + 5) % 90;
                knee1 = (knee1 - 5) % 90;
                arm1 = (arm1 - 5) % 90;
                arm2 = (arm2 + 5) % 90;
                cont++;
            }else if(cont >= 33 && cont < 49){
                leg2 = (leg2 - 5) % 90;
                knee2 = (knee2 + 5) % 90;
                arm1 = (arm1 - 5) % 90;
                arm2 = (arm2 + 5) % 90;
                cont++;
            }else if(cont >= 49 && cont < 65){
                leg2 = (leg2 + 5) % 90;
                knee2 = (knee2 - 5) % 90;
                arm1 = (arm1 + 5) % 90;
                arm2 = (arm2 - 5) % 90;
                cont++;
            }else{
                arm1 = arm2 = forearm1 = forearm2 = leg1 = leg2 = knee1 = knee2 =  shoulder = elbow = 0 ;
                cont = 0;
            }
            glutPostRedisplay();
            break;
            case 'l':
            case 'L':
                if (cont2 >= 0 && cont2 < 19){
                        shoulder = (shoulder + 5) % 180;
                        cont2++;
                    }else if(cont2 >= 19 && cont2 < 42){
                        elbow = (elbow + 5) % 130;
                        cont2++;
                    }else if(cont2 >= 42 && cont2 < 65){
                        elbow = (elbow - 5) % 130;
                        cont2++;
                    }else{
                        cont2 = 19;
                    } 
            glutPostRedisplay();
            break;
        default:
            break;
        }
        
}


void RenderScene(void){
    GLUquadricObj *pObj;    // Quadric Object  
      
    // Clear the window with current clearing color  
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
  
    // Save the matrix state and do the rotations  
    glPushMatrix();

	// Move object back and do in place rotation  
	glTranslatef(0.0f, -1.0f, -5.0f);  
	glRotatef(yRot, 0.0f, 1.0f, 0.0f);
    glRotatef(xRot, 1.0f, 0.0f, 0.0f);   

	// Draw something  
	pObj = gluNewQuadric();  
	gluQuadricNormals(pObj, GLU_SMOOTH);
    // Ground
    glColor3f(0.0f, 1.0f, 0.0f);
    glPushMatrix();
        glRotatef(90.0f, -1.0f, 0.0f, 0.0f);
        glTranslatef(0.0f, -0.5f, -0.45f);
        gluDisk(pObj, 0.0f, 4.0f, 26, 13);
    glPopMatrix();

    // Right Upper Leg
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.2f, 0.97f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
        glColor3f(0.0f, 0.0f, 1.0f);
        glPushMatrix();
            glTranslatef(0.f, -0.08f, 0.0f);
            glRotatef((GLfloat) leg1, 1.0f, 0.0f, 0.0f);
            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    // Right Knee
            glColor3f(0.0f, 0.0f, 0.0f);
            glPushMatrix();
                glTranslatef(0.0f, 0.0f,0.62f);
                gluSphere(pObj, 0.13f, 26, 13);
    // Right Lower Leg
                glColor3f(0.0f, 0.0f, 1.0f);
                glPushMatrix();
                    glTranslatef(0.0f, 0.0f, 0.08f);
                    glRotatef((GLfloat) knee1, 1.0f, 0.0f, 0.0f);
                    gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    // Right Feet
                    glColor3f(1.0f, 1.0f, 1.0f);
                    glPushMatrix();
                        glScalef(1.0f, 2.0f, 0.4f);
                        glTranslatef(0.0f, 0.045f, 1.45f);
                        glutSolidCube(0.2f);
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();

    // Left Upper Leg
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.2f, 0.97f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
        glColor3f(0.0f, 0.0f, 1.0f);
        glPushMatrix();
            glTranslatef(0.f, -0.08f, 0.0f);
            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            glRotatef((GLfloat) leg2, 1.0f, 0.0f, 0.0f);
            gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    // Left Knee
            glColor3f(0.0f, 0.0f, 0.0f);
            glPushMatrix();
                glTranslatef(0.0f, 0.0f,0.62f);
                gluSphere(pObj, 0.13f, 26, 13);
    // Left Lower Leg
                glColor3f(0.0f, 0.0f, 1.0f);
                glPushMatrix();
                    glTranslatef(0.0f, 0.0f, 0.08f);
                    glRotatef((GLfloat) knee2, 1.0f, 0.0f, 0.0f);
                    gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    // Left Feet
                    glColor3f(1.0f, 1.0f, 1.0f);
                    glPushMatrix();
                        glScalef(1.0f, 2.0f, 0.4f);
                        glTranslatef(0.0f, 0.045f, 1.45f);
                        glutSolidCube(0.2f);
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();

    // Body
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glScalef(1.5f, 2.05f, 0.5f);
        glTranslated(0.0f, 0.75f, -0.2f);
        glutSolidCube(0.5f);
    glPopMatrix();

    // Neck
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.0f,2.1f,-0.1f);
        glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,0.1f, 0.1f, 0.15f, 26, 13);
    glPopMatrix();

    

    // Head
    glColor3f(1.0f, 1.0f, 1.0f);
	glPushMatrix(); // save transform matrix state
		glTranslatef(0.0f, 2.32f, -0.1f);
		gluSphere(pObj, 0.24f, 26, 13);
	glPopMatrix(); // restore transform matrix state
    // Eyes (black)
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.08f, 2.4f, 0.1f);
        gluSphere(pObj,0.02f, 26, 13);
    glPopMatrix();
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.08f, 2.4f, 0.1f);
        gluSphere(pObj,0.02f, 26, 13);
    glPopMatrix();
    // Nose
    

    // Right Arm
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.45f, 1.9f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
        glPushMatrix();
            glTranslatef(0.0f, 0.0f, 0.0f);
            glRotatef((GLfloat) shoulder, 0.0f, 0.0f, 1.0f);
            glRotatef((GLfloat) arm1, 1.0f, 0.0f, 0.0f);
            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
            
    // Right Elbow
            glColor3f(0.0f, 0.0f, 0.0f);
            glPushMatrix();
                glTranslatef(0.0f, 0.0f, 0.5f);
                gluSphere(pObj, 0.13f, 26, 13);
    // Right Forearm
                glColor3f(1.0f, 1.0f, 1.0f);
                glPushMatrix();
                    glTranslatef(0.0f, 0.0f, 0.0f);
                    glRotatef((GLfloat) elbow, 0.0f, 1.0f, 0.0f);
                    glRotatef((GLfloat) forearm1, 1.0f, 0.0f, 0.0f);
                    gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
                   
    // Right Hand
                    glColor3f(1.0f, 1.0f, 1.0f);
                    glPushMatrix();
                        glScalef(1.0f, 1.0f, 1.0f);
                        glTranslatef(0.0f, 0.0f, 0.6f);
                        glutSolidCube(0.2f);
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();
    // Left Arm
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.45f, 1.9f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
        glPushMatrix();
            glTranslatef(0.0f, 0.0f, 0.0f);
            glRotatef((GLfloat) arm2, 1.0f, 0.0f, 0.0f);
            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
    // Left Elbow
            glColor3f(0.0f, 0.0f, 0.0f);
            glPushMatrix();
                glTranslatef(0.0f, 0.0f, 0.5f);
                gluSphere(pObj, 0.13f, 26, 13);
    // Left Forearm
                glColor3f(1.0f, 1.0f, 1.0f);
                glPushMatrix();
                    glTranslatef(0.0f, 0.0f, 0.0f);
                    glRotatef((GLfloat) forearm2, 1.0f, 0.0f, 0.0f);
                    gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
    // Left Hand
                    glColor3f(1.0f, 1.0f, 1.0f);
                    glPushMatrix();
                        glScalef(1.0f, 1.0f, 1.0f);
                        glTranslatef(0.0f, 0.0f, 0.6f);
                        glutSolidCube(0.2f);
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();
        
    glPopMatrix();
    // Restore the matrix state  
    glPopMatrix();  
    // Buffer swap  
    glutSwapBuffers();  
}


int main(int argc, char *argv[]){

    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);  
    glutInitWindowSize(900, 1000);  
    glutCreateWindow("ROBOT - Gabriel Anselmo e Luis Bertelli");  
    glutReshapeFunc(ChangeSize);  
    glutSpecialFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    glutKeyboardFunc(entrada);
    SetupRC();  
    glutMainLoop();  
      
    return 0; 
}