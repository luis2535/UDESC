import matplotlib.pyplot as plt


def save_histogram(hist, name: str):
    for i in range(256):
        plt.bar(i, hist[i], color="#000000")
    plt.savefig(name)
    plt.clf()