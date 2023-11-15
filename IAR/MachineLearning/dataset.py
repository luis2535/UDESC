import csv
import numpy as np
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
import tensorflow as tf
import matplotlib.pyplot as plt
from sklearn.metrics import roc_curve, auc, confusion_matrix

models_file = "training_results.txt"
filtered = []
with open("./dados/breast-cancer-wisconsin.csv", "r") as csvfile:
    csvr = csv.reader(
        filter(lambda l: l[6] != "?", csvfile),
        delimiter=",",
    )
    for l in csvr:
        if "?" not in l[6]:
            filtered.append(l)

np_array = np.array(filtered, dtype='int64')

np_normalized = preprocessing.MinMaxScaler().fit_transform(np_array)

X = np_normalized[:, 1:10]
Y = np_normalized[:, 10]

seed = 7
test_size = 0.3

X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=test_size, random_state=seed)

models = []

model1 = tf.keras.models.Sequential()
model1.add(tf.keras.layers.Dense(32, activation='relu', input_dim=9))
model1.add(tf.keras.layers.Dense(32, activation='relu'))
model1.add(tf.keras.layers.Dense(32, activation='relu'))
model1.add(tf.keras.layers.Dense(32, activation='relu'))
model1.add(tf.keras.layers.Dense(1, activation='sigmoid'))
models.append(model1)

model2 = tf.keras.models.Sequential()
model2.add(tf.keras.layers.Dense(32, activation='relu', input_dim=9))
model2.add(tf.keras.layers.Dense(16, activation='relu'))
model2.add(tf.keras.layers.Dense(8, activation='relu'))
model2.add(tf.keras.layers.Dense(4, activation='relu'))
model2.add(tf.keras.layers.Dense(2, activation='relu'))
model2.add(tf.keras.layers.Dense(1, activation='sigmoid'))
models.append(model2)

model3 = tf.keras.models.Sequential()
model3.add(tf.keras.layers.Dense(2, activation='relu', input_dim=9))
model3.add(tf.keras.layers.Dense(4, activation='relu'))
model3.add(tf.keras.layers.Dense(8, activation='relu'))
model3.add(tf.keras.layers.Dense(16, activation='relu'))
model3.add(tf.keras.layers.Dense(32, activation='relu'))
model3.add(tf.keras.layers.Dense(1, activation='sigmoid'))
models.append(model3)

for i, model in enumerate(models):
    custom_optimizer = tf.keras.optimizers.Adam(learning_rate=0.1)
    model.compile(optimizer=custom_optimizer, loss='binary_crossentropy', metrics=['accuracy'])

    history = model.fit(X_train, Y_train, epochs=50, batch_size=32, validation_data=(X_test, Y_test))

    # Evaluate the model on the test set
    test_loss, test_acc = model.evaluate(X_test, Y_test)
    print(f'\nTest Accuracy for Model {i + 1}: {test_acc}')
    print(f'\nTest Loss for Model {i + 1}: {test_loss}')
    print('\n')
    
    with open(models_file, "a") as f:
        f.write(f"Model {i} Learning Rate = 0.1\n")
        f.write(f"Test Accuracy {test_acc}\n")
        f.write(f"Test Loss {test_loss}\n\n")


    # Plot loss and accuracy curves
    plt.figure(figsize=(12, 4))
    plt.subplot(1, 2, 1)
    plt.plot(history.history['loss'], label='Training Loss')
    plt.plot(history.history['val_loss'], label='Validation Loss')
    plt.title(f'Training and Validation Loss - Model {i + 1}')
    plt.xlabel('Epochs')
    plt.ylabel('Loss')
    plt.legend()

    plt.subplot(1, 2, 2)
    plt.plot(history.history['accuracy'], label='Training Accuracy')
    plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
    plt.title(f'Training and Validation Accuracy - Model {i + 1}')
    plt.xlabel('Epochs')
    plt.ylabel('Accuracy')
    plt.legend()
    plt.tight_layout()
    # plt.show()
    plt.savefig(f'./resultados2/model{i + 1}_acclosslr01.png')

    # Plot ROC curve
    y_pred_prob = model.predict(X_test)
    fpr, tpr, thresholds = roc_curve(Y_test, y_pred_prob)
    roc_auc = auc(fpr, tpr)

    plt.figure()
    plt.plot(fpr, tpr, color='darkorange', lw=2, label=f'Model {i + 1} (AUC = {roc_auc:.3f})')
    plt.plot([0, 1], [0, 1], color='navy', lw=2, linestyle='--')
    plt.xlabel('False Positive Rate')
    plt.ylabel('True Positive Rate')
    plt.title(f'Receiver Operating Characteristic (ROC) Curve - Model {i + 1}')
    plt.legend(loc='lower right')
    plt.savefig(f'./resultados2/model{i + 1}_ROClr01.png')


    # Plot Confusion Matrix
    y_pred = (y_pred_prob > 0.5).astype(int)
    conf_matrix = confusion_matrix(Y_test, y_pred)

    plt.figure()
    plt.imshow(conf_matrix, interpolation='nearest', cmap=plt.cm.Blues)
    plt.title(f'Confusion Matrix - Model {i + 1}')
    plt.colorbar()

    classes = ['Negative', 'Positive']

    for k in range(conf_matrix.shape[0]):
        for j in range(conf_matrix.shape[1]):
            plt.text(j, k, format(conf_matrix[k, j], 'd'),
                    ha="center", va="center",
                    color="white" if conf_matrix[k, j] > (conf_matrix.max() / 2) else "black")

    tick_marks = np.arange(len(classes))
    plt.xticks(tick_marks, classes, rotation=45)
    plt.yticks(tick_marks, classes)
    plt.ylabel('True label')
    plt.xlabel('Predicted label')
    plt.savefig(f'./resultados2/model{i + 1}_matriz01.png')


# Plot the comparison ROC curve
plt.figure()
for i, model in enumerate(models):
    y_pred_prob = model.predict(X_test)
    fpr, tpr, thresholds = roc_curve(Y_test, y_pred_prob)
    roc_auc = auc(fpr, tpr)
    plt.plot(fpr, tpr, lw=2, label=f'Model {i + 1} (AUC = {roc_auc:.3f})')

plt.plot([0, 1], [0, 1], color='navy', lw=2, linestyle='--')
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Receiver Operating Characteristic (ROC) Curve - Comparison')
plt.legend(loc='lower right')
plt.savefig(f'./resultados2/lr01ACROC.png')

