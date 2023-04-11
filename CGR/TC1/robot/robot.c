// gcc robot.c -lglut -lGL -lGLU -lm -o robot && ./robot

#include <GL/glut.h>
  
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
    if(key == 'c'){
        arm -= 10.0f;
        if(arm < - 160.0f)
            arm = 0.0f;
        bra -= 10.0f;
        if(bra < - 130.0f)
            bra = 0.0f;
    }
    if(key == 'v'){
        arm += 10.0f;
        if(arm >  160.0f)
            arm = 0.0f;
        bra += 10.0f;
        if(bra >  130.0f)
            bra = 0.0f;
    }
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

    // Right Feet
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glScalef(1.0f, 0.4f, 2.0f);
        glTranslatef(0.2f, -1.0f, 0.0f);
        glutSolidCube(0.2f);
    glPopMatrix();
    // Left Feet
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glScalef(1.0f, 0.4f, 2.0f);
        glTranslatef(-0.2f, -1.0f, 0.0f);
        glutSolidCube(0.2f);
    glPopMatrix();

    // Right Lower Leg
    glColor3f(0.0f, 0.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.2f, 0.20f, -0.1f);
        glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    glPopMatrix();
    // Left Lower Leg
    glColor3f(0.0f, 0.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-0.2f, 0.20f, -0.1f);
        glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    glPopMatrix();

    // Right Knee
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.2f, 0.27f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
    glPopMatrix();
    // Left Knee
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.2f, 0.27f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
    glPopMatrix();

    // Right Upper Leg
    glColor3f(0.0f, 0.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.2f, 0.90f, -0.1f);
        glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    glPopMatrix();
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.2f, 0.97f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
    glPopMatrix();
    // Left Upper Leg
    glColor3f(0.0f, 0.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-0.2f, 0.90f, -0.1f);
        glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.55f, 26, 13);
    glPopMatrix();
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.2f, 0.97f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
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
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.5f, 1.9f, -0.1f);
        //glRotatef(arm, 1.2f, 1.0f, 0.0f);
        glRotatef(90.0f, 1.2f, 1.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
    glPopMatrix();
    // Left Arm
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.45f, 1.9f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.5f, 1.9f, -0.1f);
        glRotatef(90.0f, 1.2f, -1.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
    glPopMatrix();

    // Right Elbow
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.85f, 1.48f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
    glPopMatrix();
    // Left Elbow
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.85f, 1.48f,-0.1f);
        gluSphere(pObj, 0.13f, 26, 13);
    glPopMatrix();

    // Right Forearm
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.85f, 1.48f, -0.05f);
        glRotatef(bra, 1.0f, 0.0f, 1.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
    glPopMatrix();
    // Left Forearm
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-0.85f, 1.48f, -0.05f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.5f, 26, 13);
    glPopMatrix();

    // Right Hand
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glScalef(1.0f, 1.0f, 1.0f);
        glTranslatef(0.85f, 1.48, 0.55f);
        glRotatef(bra, 1.0f, 0.0f, 0.0f);
        glutSolidCube(0.2f);
    glPopMatrix();
    // Left Hand
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glScalef(1.0f, 1.0f, 1.0f);
        glTranslatef(-0.85f, 1.48f, 0.55f);
        glutSolidCube(0.2f);
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
    glutInitWindowSize(600, 1000);  
    glutCreateWindow("ROBOT - Gabriel Anselmo e Luis Bertelli");  
    glutReshapeFunc(ChangeSize);  
    glutSpecialFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    glutKeyboardFunc(entrada);
    SetupRC();  
    glutMainLoop();  
      
    return 0; 
}