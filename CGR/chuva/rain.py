from random import randint as rand
from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *

QTDFLOCOS = 10000
Floco = []

class Flocos:
    def __init__(self):
        self.viva = True
        self.vida = 1
        self.desaparecer = rand(1,999) * 0.0001
        self.xPos = rand(-30,20)
        self.yPos = rand(-30,20)
        self.zPos = rand(1,30)
        self.vel = -rand(1,5)/20
        self.gravidade = -0.00015

def init():
    global Floco
    Floco = [Flocos() for i in range(QTDFLOCOS)]

def chuva():
    global Floco
    for loop in range(0,QTDFLOCOS,2):
        if (Floco[loop].viva):
            x = Floco[loop].xPos
            y = Floco[loop].yPos
            z = Floco[loop].zPos - 25
            resto = z%2
            glBegin(GL_LINES)
            if resto == 0:
                glColor3f(0.8, 0.0, 0.0)
            else:
                glColor3f(2.0, 1.0, 0.2)
            glVertex3f(x , y, z)
            glVertex3f(x, y + 0.2, z)
            glEnd()
            Floco[loop].yPos += Floco[loop].vel
            Floco[loop].vel += Floco[loop].gravidade
            Floco[loop].vida -= Floco[loop].desaparecer
            if (Floco[loop].vida < 0.0):
                Floco[loop] = Flocos()

def desenha():
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
	glMatrixMode(GL_MODELVIEW)
	chuva()
	glutSwapBuffers()


def remodelar(w, h):
	glViewport(0, 0, w, h)
	glMatrixMode(GL_PROJECTION)
	glLoadIdentity()
	gluPerspective(55, w / h, .1, 200)
	glMatrixMode(GL_MODELVIEW)
	glLoadIdentity()

def idle():
	glutPostRedisplay()

glutInit()
glutInitDisplayMode(GLUT_DEPTH | GLUT_RGB | GLUT_DOUBLE)
glutInitWindowSize(640, 480)
glutCreateWindow("CHUVA")
init()
glutDisplayFunc(desenha)
glutReshapeFunc(remodelar)
glutIdleFunc(idle)
glutMainLoop()
