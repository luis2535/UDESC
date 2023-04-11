// gcc castle.c -lglut -lGL -lGLU -lm -o castle && ./castle

#include <GL/glut.h>

static GLfloat yRot = 0.0f;

// Change viewing volume and viewport.  Called when window is resized  
void ChangeSize(int w, int h){

    glClearColor(0.7f,0.87f,0.9f,1.0f);
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
    gluPerspective(75.0f,fAspect,0.2f,200.0f);
  
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
  
    // Background color
    glClearColor(0.25f, 0.25f, 0.50f, 1.0f);  

}  
  
// Respond to arrow keys (rotate castle)
void SpecialKeys(int key, int x, int y){  

    if(key == GLUT_KEY_LEFT)  
        yRot -= 5.0f;  
  
    if(key == GLUT_KEY_RIGHT)  
        yRot += 5.0f; 

    yRot = (GLfloat)((const int)yRot % 360); 
  
    // Refresh the Window  
    glutPostRedisplay();  
}
void RenderScene(void){
     GLUquadricObj *pObj;    // Quadric Object  
      
    // Clear the window with current clearing color  
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    // Save the matrix state and do the rotations  
    glPushMatrix();

	// Move object back and do in place rotation  
	glTranslatef(0.0f, -4.0f, -17.0f);  
	glRotatef(yRot, 0.0f, 1.0f, 0.0f); 

	// Draw something  
	pObj = gluNewQuadric();  
	gluQuadricNormals(pObj, GLU_SMOOTH);

    //ground
    glColor3f(0.0f,1.0f,0.0f);
    glPushMatrix();
        glTranslatef(0.0f, -0.5f, 0.0f);
        glRotatef(90.0f, -0.5f, 0.0f, 0.0f);
        gluDisk(pObj, 0.0f, 15.0f, 26, 13);
    glPopMatrix();

    // front wall
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glScaled(9.0f,5.2f,1.0f);
        glTranslatef(0.0f, 0.0f, 6.0f);
        glutSolidCube(1.0f);
    glPopMatrix();
    // details
    glPushMatrix();
        glTranslatef(0.0f, 2.8f, 6.0f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(3.0f, 2.8f, 6.0f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-3.0f, 2.8f, 6.0f);
        glutSolidCube(0.75f);
    glPopMatrix();



    //left wall
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glScaled(1.0f, 5.2f, 11.0f);
        glTranslatef(-5.0f, 0.0f, 0.0f);
        glutSolidCube(1.0f);
    glPopMatrix();
    //details
    glPushMatrix();
        glTranslatef(-5.0f, 2.8f, 4.0f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-5.0f, 2.8f, 1.4f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-5.0f, 2.8f, -1.6f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-5.0f, 2.8f, -4.0f);
        glutSolidCube(0.75f);
    glPopMatrix();
    

    // right wall
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glScaled(1.0f, 5.2f, 11.0f);
        glTranslatef(5.0f, 0.0f, 0.0f);
        glutSolidCube(1.0f);
    glPopMatrix();
    //details
    glPushMatrix();
        glTranslatef(5.0f, 2.8f, 4.0f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(5.0f, 2.8f, 1.4f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(5.0f, 2.8f, -1.6f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(5.0f, 2.8f, -4.0f);
        glutSolidCube(0.75f);
    glPopMatrix();

    // back wall
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glScaled(9.0f,5.2f,1.0f);
        glTranslatef(0.0f, 0.0f, -6.0f);
        glutSolidCube(1.0f);
    glPopMatrix();
    // details
    glPushMatrix();
        glTranslatef(0.0f, 2.8f, -6.0f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(3.0f, 2.8f, -6.0f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-3.0f, 2.8f, -6.0f);
        glutSolidCube(0.75f);
    glPopMatrix();

    // tower 1
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glTranslatef(5.0f, -0.5f, 6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,0.75f, 0.75f, 4.0f, 26, 13);   
    glPopMatrix();
    glPushMatrix();
        glTranslatef(5.0f, 3.5f, 6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,1.0f, 1.0f, 1.0f, 26, 13);
    glPopMatrix();
    glColor3f(1.0f, 0.0f, 0.0f);    
    glPushMatrix();
        glTranslatef(5.0f, 4.4f, 6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 1.2f, 0.0f, 2.0f, 26, 13);
    glPopMatrix();

    // tower 2
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glTranslatef(-5.0f, -0.5f, 6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,0.75f, 0.75f, 4.0f, 26, 13);   
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-5.0f, 3.5f, 6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,1.0f, 1.0f, 1.0f, 26, 13);
    glPopMatrix();
    glColor3f(1.0f, 0.0f, 0.0f);    
    glPushMatrix();
        glTranslatef(-5.0f, 4.4f, 6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 1.2f, 0.0f, 2.0f, 26, 13);
    glPopMatrix();

    // tower 3
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glTranslatef(5.0f, -0.5f, -6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,0.75f, 0.75f, 4.0f, 26, 13);   
    glPopMatrix();
    glPushMatrix();
        glTranslatef(5.0f, 3.5f, -6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,1.0f, 1.0f, 1.0f, 26, 13);
    glPopMatrix();
    glColor3f(1.0f, 0.0f, 0.0f);    
    glPushMatrix();
        glTranslatef(5.0f, 4.4f, -6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 1.2f, 0.0f, 2.0f, 26, 13);
    glPopMatrix();

    // tower 4
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glTranslatef(-5.0f, -0.5f, -6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,0.75f, 0.75f, 4.0f, 26, 13);   
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-5.0f, 3.5f, -6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj,1.0f, 1.0f, 1.0f, 26, 13);
    glPopMatrix();
    glColor3f(1.0f, 0.0f, 0.0f);    
    glPushMatrix();
        glTranslatef(-5.0f, 4.4f, -6.0f);
        glRotatef(90.0f,-1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 1.2f, 0.0f, 2.0f, 26, 13);
    glPopMatrix();

    // middle construction
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
        glScalef(3.0f, 10.0f, 3.0f);
        glutSolidCube(1.0f);
    glPopMatrix();
    // details
    glPushMatrix();
        glTranslatef(1.1f, 5.2f, 1.1f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(1.1f, 5.2f, -1.1f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-1.1f, 5.2f, 1.1f);
        glutSolidCube(0.75f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-1.1f, 5.2f, -1.1f);
        glutSolidCube(0.75f);
    glPopMatrix();
 

    // Door
    glColor3f(0.8f, 0.5f, 0.1f);
    glPushMatrix();
        glScalef(1.9f, 3.0f, 1.9f);
        glTranslatef(0.0f, 0.0f, 3.0f);
        glutSolidCube(1.0f);
    glPopMatrix();

    // Window
    glColor3f(0.8f, 0.5f, 0.0f);
    glPushMatrix();
        glTranslatef(2.7f, 0.8f, 6.6f);
        gluDisk(pObj, 0.0f, 0.5f, 26, 13);
    glPopMatrix();
    glColor3f(0.8f, 0.5f, 0.0f);
    glPushMatrix();
        glTranslatef(-2.7f, 0.8f, 6.6f);
        gluDisk(pObj, 0.0f, 0.5f, 26, 13);
    glPopMatrix();
    
    // Restore the matrix state  
    glPopMatrix();  
  
    // Buffer swap  
    glutSwapBuffers(); 

}

int main(int argc, char *argv[]){

    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);  
    glutInitWindowSize(950,700);
    glutInitWindowPosition(50,0);
    glutCreateWindow("CASTLE - Gabriel Anselmo e Luis Bertelli");  
    glutReshapeFunc(ChangeSize);  
    glutSpecialFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    glRasterPos2d(0,1);
    SetupRC();  
    glutMainLoop();  
      
    return 0; 
}
