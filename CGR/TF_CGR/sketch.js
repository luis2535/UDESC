let walls = [];
let ray;
let particles = [];
let r = 40;
let mouseDown = false;
let iParticle = -1;
let iObstacle = -1;
let obstacleA = true;
let bestDist = r;

function setup() {
  // createCanvas(500, 500);
  createCanvas(800, 500);

  walls.push(new Boundary(-1, -1, width, -1));
  walls.push(new Boundary(width, -1, width, height));
  walls.push(new Boundary(width, height, -1, height));
  walls.push(new Boundary(-1, height, -1, -1));

  for (let i = 0; i < 3; i++) {
    let x1 = random(width);
    let x2 = random(width);
    let x3 = random(width);
    let y1 = random(height);
    let y2 = random(height);
    let y3 = random(height);
    walls.push(new Boundary(x1, y1, x2, y2, x3, y3));
  }

  for (let i = 0; i < 1; i++) {
    particles.push(new Particle());
  }
}

function addParticle() {
  particles.push(new Particle(random(width), random(height)));
}
function centerParticle() {
  particles.push(new Particle(400, 250));
}

function removeParticle() {
  if (particles.length > 0)
    particles.splice(floor(random(particles.length)), 1);
}

function addObstacle() {
  let x1 = random(width);
  let x2 = random(width);
  let x3 = random(width);
  let y1 = random(height);
  let y2 = random(height);
  let y3 = random(height);
  walls.push(new Boundary(x1, y1, x2, y2, x3, y3));
}

function removeObstacle() {
  let pos = 4 + floor(random(walls.length - 4));
  console.log(pos);
  if (walls.length >= 4)
    walls.splice(pos, 1);
}

function createSquare(){
  let x1 = 300;
  let x2 = 500;
  let x3 = 100;
  let y1 = 150;
  let y2 = 150;
  let y3 = 100;
  walls.push(new Boundary(x1, y1, x2, y2, x3, y3));
  let x11 = 300;
  let x21 = 500;
  let x31 = 100;
  let y11 = 350;
  let y21 = 350;
  let y31 = 100;
  walls.push(new Boundary(x11, y11, x21, y21, x31, y31));
  let x12 = 300;
  let x22 = 300;
  let x32 = 100;
  let y12 = 150;
  let y22 = 350;
  let y32 = 100;
  walls.push(new Boundary(x12, y12, x22, y22, x32, y32));
  let x13 = 500;
  let x23 = 500;
  let x33 = 100;
  let y13 = 150;
  let y23 = 350;
  let y33 = 100;
  walls.push(new Boundary(x13, y13, x23, y23, x33, y33));
}
function createOpenSquare(){
  let x1 = 325;
  let x2 = 475;
  let x3 = 100;
  let y1 = 150;
  let y2 = 150;
  let y3 = 100;
  walls.push(new Boundary(x1, y1, x2, y2, x3, y3));
  let x11 = 325;
  let x21 = 475;
  let x31 = 100;
  let y11 = 350;
  let y21 = 350;
  let y31 = 100;
  walls.push(new Boundary(x11, y11, x21, y21, x31, y31));
  let x12 = 300;
  let x22 = 300;
  let x32 = 100;
  let y12 = 175;
  let y22 = 325;
  let y32 = 100;
  walls.push(new Boundary(x12, y12, x22, y22, x32, y32));
  let x13 = 500;
  let x23 = 500;
  let x33 = 100;
  let y13 = 175;
  let y23 = 325;
  let y33 = 100;
  walls.push(new Boundary(x13, y13, x23, y23, x33, y33));
}
function createTriangle(){
  let x1 = 300;
  let x2 = 400;
  let x3 = 100;
  let y1 = 300;
  let y2 = 100;
  let y3 = 100;
  walls.push(new Boundary(x1, y1, x2, y2, x3, y3 ));
  let x11 = 500;
  let x21 = 400;
  let x31 = 100;
  let y11 = 300;
  let y21 = 100;
  let y31 = 100;
  walls.push(new Boundary(x11, y11, x21, y21, x31, y31 ));
  let x12 = 300;
  let x22 = 500;
  let x32 = 100;
  let y12 = 300;
  let y22 = 300;
  let y32 = 100;
  walls.push(new Boundary(x12, y12, x22, y22, x32, y32 ));
}
function createOpenTriangle(){
  let x1 = 300;
  let x2 = 375;
  let x3 = 100;
  let y1 = 275;
  let y2 = 125;
  let y3 = 100;
  walls.push(new Boundary(x1, y1, x2, y2, x3, y3 ));
  let x11 = 500;
  let x21 = 425;
  let x31 = 100;
  let y11 = 275;
  let y21 = 125;
  let y31 = 100;
  walls.push(new Boundary(x11, y11, x21, y21, x31, y31 ));
  let x12 = 320;
  let x22 = 480;
  let x32 = 100;
  let y12 = 300;
  let y22 = 300;
  let y32 = 100;
  walls.push(new Boundary(x12, y12, x22, y22, x32, y32 ));
}
function draw() {
  background(0);
  for (let wall of walls) {
    wall.show();
  }
  for (let i = 0; i < particles.length; i++) {
    particles[i].showElipses();
    particles[i].showLines();
    particles[i].look(walls);
  }
}

/* LOPPP  DE LUZ */
/*https://rossener.com/como-criar-um-timer-com-resume-pause-e-reset-usando-javascript/#o-que-%C3%A9-um-timer*/

function animation() {
    time = 1000, delay = 500;
    setTimeout(addParticle, time);
    time += 300;
    setTimeout(addObstacle, time);
    time += 500;
    setTimeout(removeParticle, time);
    time += 300;
    setTimeout(addParticle, time);
    time += 300;
    setTimeout(addParticle, time);
    time += 300;
    setTimeout(addParticle, time);
    time += 300;
    setTimeout(addObstacle, time);
    time += 1000;
    setTimeout(removeParticle, time);
    time += 300;
    setTimeout(addParticle, time);
    time += 300;
    setTimeout(removeParticle, time);
    time += 300;
    setTimeout(removeParticle, time);
    time += 1000;
    setTimeout(addParticle, time);
    time += 1000;
    setTimeout(removeParticle, time);
    time += 300;
    setTimeout(addObstacle, time);
    time += 300;
    setTimeout(addObstacle, time);
    time += 400;
    setTimeout(addParticle, time);
    time += 400;
    setTimeout(addParticle, time);
    time += 400;
    setTimeout(addParticle, time);
    time += 500;
    setTimeout(removeObstacle, time);
    time += 300;
    setTimeout(removeParticle, time);
    time += 300;
    setTimeout(addParticle, time);
    time += 500;
    setTimeout(removeObstacle, time);
    time += 300;
    setTimeout(removeParticle, time);
    time += 500;
    setTimeout(removeObstacle, time);
    time += 300;
    setTimeout(removeParticle, time);
    time += 300;
    setTimeout(removeParticle, time);
}

function addParticleS() {
    time = 1000, delay = 500;
    setTimeout(addParticle, time);
    time += delay;
    setTimeout(addParticle, time);
    time += delay;
    setTimeout(addParticle, time);
    time += delay;
    setTimeout(addParticle, time);
    time += delay;
    setTimeout(removeParticle, time);
    time += delay;
    setTimeout(removeParticle, time);
    time += delay;
    setTimeout(addParticle, time);
    time += delay;
    setTimeout(addParticle, time);
    time += delay;
    setTimeout(removeParticle, time);
    time += delay;
    setTimeout(removeParticle, time);
    time += delay;
    setTimeout(removeParticle, time);
    time += delay;
    setTimeout(removeParticle, time);
}
