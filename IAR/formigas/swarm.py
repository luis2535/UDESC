import pygame
import random
from PIL import Image, ImageDraw
import os


# Inicialização do Pygame
pygame.init()

# Constantes
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
GRID_SIZE = 15  # Tamanho do grid
ANT_COUNT = 40
ANT_SPEED = 0.001  # Movimento a cada segundo (em segundos)
OBJ_COUNT = 200
iteracao = 0

# Cores
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)  # Cor dos objetos imóveis (vermelho)
GREEN = (0, 255, 0)

ant_image = pygame.image.load("imgs/ant.png")
ant_image = pygame.transform.scale(ant_image, (GRID_SIZE, GRID_SIZE))
leaf_image = pygame.image.load("imgs/leaf.png")
leaf_image = pygame.transform.scale(leaf_image, (GRID_SIZE, GRID_SIZE))
ant_leaf_image = pygame.image.load("imgs/ant_leaf.png")
ant_leaf_image = pygame.transform.scale(ant_leaf_image, (GRID_SIZE, GRID_SIZE))

class Ant:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.next_move_time = pygame.time.get_ticks() + random.randint(0, 1000)
        self.is_carrying_object = False
        self.cooldown_end_time = 0

    def move(self):
        current_time = pygame.time.get_ticks()
        if current_time >= self.next_move_time:
            if not self.is_carrying_object:
                self.try_pickup()
            else:
                self.try_drop()
            new_x, new_y = self.generate_new_position()
            self.x = new_x
            self.y = new_y
            self.next_move_time = current_time + int(1000 * ANT_SPEED)
            if current_time >= self.cooldown_end_time:
                self.cooldown_end_time = 0  # Redefina o cooldown
    def try_pickup(self):
        if not self.is_carrying_object and self.cooldown_end_time ==0:
            for obj in objects:
                if(obj.x == self.x and obj.y == self.y):
                    nearby_count = self.nearby_objects()
                    pickup_probability = 1 - (nearby_count/8) # 8 é o numero de posições adjacentes

                    if random.random() < pickup_probability:
                        position = (self.x, self.y)
                        object_positions.remove(position)
                        self.is_carrying_object = True
                        objects.remove(obj)

            
    def nearby_objects(self):
        nearby_count = 0
        for obj in objects:
            if obj.x == self.x and obj.y == self.y - GRID_SIZE:
                nearby_count += 1  # Acima
            elif obj.x == self.x and obj.y == self.y + GRID_SIZE:
                nearby_count += 1  # Abaixo
            elif obj.x == self.x - GRID_SIZE and obj.y == self.y:
                nearby_count += 1  # À esquerda
            elif obj.x == self.x + GRID_SIZE and obj.y == self.y:
                nearby_count += 1  # À direita
            elif obj.x == self.x - GRID_SIZE and obj.y == self.y - GRID_SIZE:
                nearby_count += 1  # Diagonal superior esquerda
            elif obj.x == self.x + GRID_SIZE and obj.y == self.y - GRID_SIZE:
                nearby_count += 1  # Diagonal superior direita
            elif obj.x == self.x - GRID_SIZE and obj.y == self.y + GRID_SIZE:
                nearby_count += 1  # Diagonal inferior esquerda
            elif obj.x == self.x + GRID_SIZE and obj.y == self.y + GRID_SIZE:
                nearby_count += 1  # Diagonal inferior direita
        return nearby_count
    def try_drop(self):
        position = (self.x, self.y)
        if self.is_carrying_object and position not in object_positions:
            nearby_count = self.nearby_objects()
            drop_propability = nearby_count/8

            if random.random() < drop_propability:
                objects.append(Object(self.x, self.y))
                object_positions.add(position)
                self.is_carrying_object = False
                self.cooldown_end_time = pygame.time.get_ticks() + 1000

    def generate_new_position(self):
        while True:
            move_x = random.choice([-GRID_SIZE, GRID_SIZE, 0])
            move_y = random.choice([-GRID_SIZE, GRID_SIZE, 0])
            new_x = self.x + move_x
            new_y = self.y + move_y

            if 0 <= new_x < SCREEN_WIDTH and 0 <= new_y < SCREEN_HEIGHT and not any(
                ant.x == new_x and ant.y == new_y for ant in ants):
                return new_x, new_y
    def draw(self, screen):
        if self.is_carrying_object:
            screen.blit(ant_leaf_image, (self.x, self.y))
        else:
            screen.blit(ant_image, (self.x, self.y))
class Object:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    def draw(self, screen):
        screen.blit(leaf_image, (self.x, self.y))

screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption('Colonia de formigas')
ants = [Ant(random.randrange(0, SCREEN_WIDTH, GRID_SIZE), random.randrange(0, SCREEN_HEIGHT, GRID_SIZE)) for _ in range(ANT_COUNT)]

object_positions = set()
objects = []
while len(objects) < OBJ_COUNT:
    x = random.randrange(0, SCREEN_WIDTH, GRID_SIZE)
    y = random.randrange(0, SCREEN_HEIGHT, GRID_SIZE)
    position = (x, y)

    # Verifique se a posição já está ocupada por outro objeto
    if position not in object_positions:
        objects.append(Object(x, y))
        object_positions.add(position)

start_time = pygame.time.get_ticks()
end_time = start_time + 360000
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        
    for ant in ants:
        ant.move()

    screen.fill(WHITE)

    for x in range(0, SCREEN_WIDTH, GRID_SIZE):
        pygame.draw.line(screen, BLACK, (x, 0), (x, SCREEN_HEIGHT))
    for y in range(0, SCREEN_HEIGHT, GRID_SIZE):
        pygame.draw.line(screen, BLACK, (0, y), (SCREEN_WIDTH, y))

    # for ant in ants:
    #     ant.draw(screen)
    for object in objects:
        object.draw(screen)

    iteracao +=1

    pygame.display.flip()
    if pygame.time.get_ticks() >= end_time:
        break

output_folder = "results/"

if not os.path.exists(output_folder):
    os.makedirs(output_folder)

# ants.clear()

image_file = os.path.join(output_folder, "grid_final.png")
pygame.image.save(screen, image_file)

matrix_file = os.path.join(output_folder, "grid_matrix.txt")
with open(matrix_file, "w") as file:

    file.write(f'Número de iterações: {iteracao}')
    for y in range(0, SCREEN_HEIGHT, GRID_SIZE):
        row = []
        for x in range(0, SCREEN_WIDTH, GRID_SIZE):
            if any(obj.x == x and obj.y == y for obj in objects):
                row.append("1")
            else:
                row.append("0")
        file.write(" ".join(row) + "\n")
pygame.quit()

