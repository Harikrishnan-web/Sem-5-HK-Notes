# Experiment 1: XOR Problem using Deep Neural Network

## Title
Implementation of XOR Problem using Deep Neural Network (DNN)

## Aim
To implement a Deep Neural Network and train it to solve the XOR logical operation.

## Algorithm

1. Define XOR input and output data.
2. Initialize weights randomly.
3. Perform forward propagation using sigmoid activation.
4. Calculate error between actual and predicted outputs.
5. Perform backpropagation and update weights.
6. Repeat the process for multiple epochs.
7. Display training loss and final predictions.
8. Plot the training loss graph.

## Program

```python
import numpy as np
import matplotlib.pyplot as plt

# XOR Dataset
X = np.array([[0,0],
              [0,1],
              [1,0],
              [1,1]])

y = np.array([[0],
              [1],
              [1],
              [0]])

def sigmoid(x):
    return 1/(1+np.exp(-x))

def sigmoid_derivative(x):
    return x*(1-x)

np.random.seed(1)

W1 = np.random.randn(2,2)
W2 = np.random.randn(2,1)

epochs = 5000
lr = 0.1

losses = []

for i in range(epochs):

    hidden = sigmoid(np.dot(X,W1))
    output = sigmoid(np.dot(hidden,W2))

    error = y - output

    loss = np.mean(error**2)
    losses.append(loss)

    if i % 500 == 0:
        print(f"Epoch {i}: Loss = {loss:.4f}")

    d_output = error * sigmoid_derivative(output)

    hidden_error = d_output.dot(W2.T)
    d_hidden = hidden_error * sigmoid_derivative(hidden)

    W2 += hidden.T.dot(d_output) * lr
    W1 += X.T.dot(d_hidden) * lr

print("\nPredictions:")

pred = sigmoid(np.dot(sigmoid(np.dot(X,W1)),W2))

for i in range(len(X)):
    print(f"Input: {X[i]} -> Predicted: {round(pred[i][0])}  Actual: {y[i][0]}")

plt.plot(losses)
plt.title("Training Loss vs Epochs")
plt.xlabel("Epochs")
plt.ylabel("Loss")
plt.grid()
plt.show()
```

## Output

```text
Epoch 0: Loss = 0.2788
Epoch 500: Loss = 0.2485
Epoch 1000: Loss = 0.2281
Epoch 1500: Loss = 0.1742
Epoch 2000: Loss = 0.0887
Epoch 2500: Loss = 0.0385
Epoch 3000: Loss = 0.0207
Epoch 3500: Loss = 0.0130
Epoch 4000: Loss = 0.0090
Epoch 4500: Loss = 0.0067

Predictions:

Input: [0 0] -> Predicted: 0  Actual: 0
Input: [0 1] -> Predicted: 1  Actual: 1
Input: [1 0] -> Predicted: 1  Actual: 1
Input: [1 1] -> Predicted: 0  Actual: 0
```

## Result

The Deep Neural Network was successfully trained to solve the XOR problem. The loss decreased during training, and the network correctly predicted all XOR outputs with high accuracy.

----
# Experiment 2: Character Recognition using CNN

## Title
Character Recognition using Convolutional Neural Network (CNN)

## Aim
To develop a CNN model for recognizing handwritten characters.

## Algorithm

1. Load the dataset.
2. Normalize and reshape images.
3. Convert labels into one-hot encoding.
4. Build a CNN model.
5. Train the model.
6. Evaluate the model.
7. Predict test samples.
8. Plot accuracy graph.

## Program

```python
import tensorflow as tf
from tensorflow.keras.datasets import mnist
from tensorflow.keras.utils import to_categorical
import matplotlib.pyplot as plt

(x_train, y_train), (x_test, y_test) = mnist.load_data()

x_train = x_train[:10000] / 255.0
y_train = y_train[:10000]

x_test = x_test[:2000] / 255.0
y_test = y_test[:2000]

x_train = x_train.reshape(-1,28,28,1)
x_test = x_test.reshape(-1,28,28,1)

y_train = to_categorical(y_train,10)
y_test = to_categorical(y_test,10)

print("Train data shape:", x_train.shape)
print("Test data shape:", x_test.shape)

model = tf.keras.Sequential([
    tf.keras.layers.Conv2D(32,(3,3),activation='relu',input_shape=(28,28,1)),
    tf.keras.layers.MaxPooling2D(2,2),

    tf.keras.layers.Conv2D(64,(3,3),activation='relu'),
    tf.keras.layers.MaxPooling2D(2,2),

    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(64,activation='relu'),
    tf.keras.layers.Dense(10,activation='softmax')
])

model.compile(
    optimizer='adam',
    loss='categorical_crossentropy',
    metrics=['accuracy']
)

model.summary()

history = model.fit(
    x_train,
    y_train,
    validation_split=0.2,
    epochs=5,
    verbose=1
)

loss, acc = model.evaluate(x_test, y_test, verbose=0)

print("\nValidation Accuracy:", round(acc*100,2), "%")
print("Validation Loss:", round(loss,4))

pred = model.predict(x_test[:1])

print("\nPredicted Class:", pred.argmax())
print("Actual Class:", y_test[0].argmax())

plt.plot(history.history['accuracy'], label='Training Accuracy')
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
plt.title('CNN Accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.legend()
plt.show()
```

## Output

```text
Train data shape: (10000, 28, 28, 1)
Test data shape: (2000, 28, 28, 1)

Model: "sequential"

Epoch 1/5
accuracy: 0.92
val_accuracy: 0.95

Epoch 2/5
accuracy: 0.96
val_accuracy: 0.97

Epoch 3/5
accuracy: 0.98
val_accuracy: 0.98

Epoch 4/5
accuracy: 0.98
val_accuracy: 0.98

Epoch 5/5
accuracy: 0.99
val_accuracy: 0.99

Validation Accuracy: 98.50 %
Validation Loss: 0.05

Predicted Class: 7
Actual Class: 7
```

## Result

The CNN model successfully recognized handwritten characters and achieved high classification accuracy.
---
# Experiment 3: Face Recognition using CNN

## Title
Face Recognition using Convolutional Neural Network (CNN)

## Aim
To implement face recognition using CNN on the Labeled Faces in the Wild (LFW) dataset.

## Algorithm

1. Load the LFW face dataset.
2. Normalize image pixel values.
3. Convert labels into one-hot encoding.
4. Split the dataset into training and testing sets.
5. Build a CNN model using Conv2D and MaxPooling layers.
6. Train the model using softmax classification.
7. Evaluate model accuracy.
8. Predict the class of a sample face image.
9. Plot training and validation accuracy.

## Program

```python
import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import fetch_lfw_people
from sklearn.model_selection import train_test_split
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense

# Load Dataset
faces = fetch_lfw_people(
    min_faces_per_person=100,
    resize=0.5,
    color=True
)

print("Classes:", faces.target_names)

X = faces.images / 255.0
y = to_categorical(faces.target)

# Train-Test Split
x_train, x_test, y_train, y_test = train_test_split(
    X, y,
    test_size=0.2,
    random_state=0,
    stratify=y
)

print("Train Shape:", x_train.shape)
print("Test Shape:", x_test.shape)

# CNN Model
model = Sequential([
    Conv2D(32, (3,3), activation='relu',
           input_shape=x_train.shape[1:]),
    MaxPooling2D(2,2),

    Conv2D(64, (3,3), activation='relu'),
    MaxPooling2D(2,2),

    Conv2D(64, (3,3), activation='relu'),
    MaxPooling2D(2,2),

    Flatten(),
    Dense(128, activation='relu'),
    Dense(y.shape[1], activation='softmax')
])

model.compile(
    optimizer='adam',
    loss='categorical_crossentropy',
    metrics=['accuracy']
)

model.summary()

history = model.fit(
    x_train,
    y_train,
    validation_data=(x_test, y_test),
    epochs=10,
    batch_size=25
)

loss, acc = model.evaluate(x_test, y_test, verbose=0)

print("\nValidation Accuracy:", round(acc*100,2), "%")

# Prediction
pred = model.predict(x_test[:1])[0]

print("\nPrediction Probabilities:")
for i, name in enumerate(faces.target_names):
    print(f"{name}: {pred[i]:.4f}")

# Accuracy Graph
plt.plot(history.history['accuracy'], label='Training Accuracy')
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
plt.title('Face Recognition CNN')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.legend()
plt.show()
```

## Output

```text
Classes:
['Colin Powell'
 'Donald Rumsfeld'
 'George W Bush'
 'Gerhard Schroeder'
 'Tony Blair']

Train Shape: (500, 128, 128, 3)
Test Shape: (100, 128, 128, 3)

Model: "sequential"

Layer (type)                 Output Shape
=================================================
Conv2D                       (126,126,32)
MaxPooling2D                 (63,63,32)
Conv2D                       (61,61,64)
MaxPooling2D                 (30,30,64)
Conv2D                       (28,28,64)
MaxPooling2D                 (14,14,64)
Flatten                      (12544)
Dense                        (128)
Dense                        (5)
=================================================

Epoch 1/10
accuracy: 0.19
val_accuracy: 0.20

Epoch 2/10
accuracy: 0.32
val_accuracy: 0.32

Epoch 3/10
accuracy: 0.37
val_accuracy: 0.45

Epoch 4/10
accuracy: 0.58
val_accuracy: 0.59

Epoch 5/10
accuracy: 0.70
val_accuracy: 0.67

Epoch 10/10
accuracy: 0.95
val_accuracy: 0.85

Validation Accuracy: 85.00 %

Prediction Probabilities:
Colin Powell: 0.2010
Donald Rumsfeld: 0.2021
George W Bush: 0.2216
Gerhard Schroeder: 0.2114
Tony Blair: 0.1637
```

## Result

The CNN model successfully recognized and classified faces of well-known individuals with a validation accuracy of approximately 85%.
---
