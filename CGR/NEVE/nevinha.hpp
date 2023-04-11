#ifndef NEVINHA_HPP
#define NEVINHA_HPP

#include <bits/stdc++.h>
#include <GL/freeglut.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <unistd.h>

using namespace std;

class Nevinha
{
public:
	double tempoVida;
    double pos_x;
    double pos_y; 
    double pos_z; 
    double velocidade;
    double gravidade;
    double raio;
    bool vivo;

};

#endif