class Boundary {
  constructor(x1, y1, x2, y2, x3, y3) {
    this.a = createVector(x1, y1);
    this.b = createVector(x2, y2);
    this.c = createVector(x3, y3);
  }

  show() {
    stroke(255);
    line(this.a.x, this.a.y, this.b.x, this.b.y, this.c.x, this.c.y);
  }

  getPosA() {
    return this.a;
  }

  getPosB() {
    return this.b;
  }

  getPosC() {
    return this.c;
  }

  updateA(x, y) {
    this.a.set(x, y);
  }

  updateB(x, y) {
    this.b.set(x, y);
  }

  updateC(x, y) {
    this.c.set(x, y);
  }
}
