from sat import ThreeSAT
from random_search import RandomSearch
from simulatedannealing import Annealing
import matplotlib.pyplot as plt
import numpy as np
from time import time
import pandas as pd
import seaborn as sns

if __name__ == "__main__":

    files = ["uf20-01.cnf", "uf100-01.cnf", "uf250-01.cnf"]
    annealing_file = "annealing_results.txt"
    random_search_file = "random_search_results.txt"

    for file in files:
        sat = ThreeSAT("./cases/" + file)

        for i in range(1, 11):
            testing_sa = Annealing(sat=sat, max_iterations=250000, cooling_schedule=9,temp=1, temp_min=0.0001)
            testing_sa.run()
            figure, axis = plt.subplots(2, 1)
            plt.subplots_adjust(hspace=0.5)

            num_clauses = sat.num_clauses
            unsatisfied_clauses = [num_clauses - score for score in testing_sa.score_list]

            axis[0].plot(unsatisfied_clauses) 
            axis[0].set_xlabel("Iteração")
            axis[0].set_ylabel("Clausulas não satisfeitas") 
            axis[0].set_title("Simulated Annealing", fontweight="bold")

            axis[1].plot(testing_sa.temp_list, color='red') 
            axis[1].set_xlabel("Iteração")
            axis[1].set_ylabel("Temperatura")

            plt.savefig(f'./sa/{file}_exec{i}.png')  

            """
                Testing Random Search
            """
            start_time = time()
            rs = RandomSearch(sat=sat, max_iterations=250000)
            print(rs.run())
            unsatisfied = [sat.num_clauses - rs.score[i] for i in range(len(rs.score))]
            print(rs.score.mean(), rs.score.std())
            plt.plot(unsatisfied)
            axis[1].set_ylabel("Clausulas não satisfeitas")
            plt.title("Random Search ",fontweight="bold")
            plt.savefig(f'./rs/{file}_exec{i}.png')

            fig = plt.figure(figsize=(10, 10))



            annealing_scores= testing_sa.score_list  # Substitua isso pelo valor real de score de Annealing
            random_search_scores = rs.score  # Substitua isso pelo valor real de score de RandomSearch
            

            data = [annealing_scores, random_search_scores]
            labels = ["Simulated annealing", "Random Search"]
            df = pd.DataFrame(data)

        # Crie um boxplot
            fig, ax = plt.subplots()

    # Crie boxplots lado a lado
            ax.boxplot(data, labels=labels)

            # Adicione rótulos ao eixo X
            ax.set_xlabel('Algoritmo')
            ax.set_ylabel('Clausulas satisfeitas')
            plt.title("Boxplot SA e RS", fontweight="bold")

            plt.savefig(f'./boxplot/{file}_exec{i}.png')  

        # Calcule a média e o desvio padrão dos scores
            annealing_mean = np.mean(annealing_scores)
            annealing_std = np.std(annealing_scores)
            annealing_median = np.median(annealing_scores)
            random_search_mean = np.mean(random_search_scores)
            random_search_std = np.std(random_search_scores)
            random_search_median = np.median(random_search_scores)

            # Salve os resultados de Annealing em um arquivo
            with open(annealing_file, "a") as f:
                f.write(f"Execução {i} {file}:\n")
                f.write(f"Mean {annealing_mean}\n")
                f.write(f"Std {annealing_std}\n")
                f.write(f"Median {annealing_median}\n")
                f.write("\n")

            # Salve os resultados de RandomSearch em um arquivo
            with open(random_search_file, "a") as f:
                f.write(f"Execução {i} {file}:\n")
                f.write(f"Mean {random_search_mean}\n")
                f.write(f"Std {random_search_std}\n")
                f.write(f"Median {random_search_median}\n")
                f.write("\n")
