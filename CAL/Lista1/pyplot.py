
from cProfile import label
import matplotlib.pyplot as plt
import math

with open("tempo.txt", 'r') as f:
    lines = f.read().splitlines()

xPoints = [1, 10, 20, 30, 40, 50]
yPointsInterative = lines[0].split(" ")
yPointsRecursive = lines[1].split(" ")


plt.plot(xPoints, yPointsInterative, label = "Interativa")
plt.plot(xPoints, yPointsRecursive, label = "Recursiva")
plt.title("Recursiva x Iterativa")
plt.xlabel("N")
plt.ylabel("Tempo(s)")
plt.legend(loc="upper left")
plt.show()
print(yPointsInterative)

print(yPointsRecursive)

