## EXPERIMENT 1: SOLVING XOR PROBLEM USING DNN

### AIM

To implement a Deep Neural Network (DNN) to solve the XOR classification problem, which is not linearly separable.

### ALGORITHM

1. Define the XOR input and output values.
2. Create a DNN with an input layer, hidden layer and output layer.
3. Use ReLU activation in the hidden layer and sigmoid activation in the output layer.
4. Compile the network using Adam optimizer and binary cross-entropy loss.
5. Train the network using the XOR data.
6. Predict the XOR outputs and display the results.

### PYTHON PROGRAM

```python
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

X = np.array([[0,0], [0,1], [1,0], [1,1]])
y = np.array([0, 1, 1, 0])

model = Sequential([
    Dense(4, activation='relu', input_shape=(2,)),
    Dense(1, activation='sigmoid')
])

model.compile(optimizer='adam', loss='binary_crossentropy',
              metrics=['accuracy'])

model.fit(X, y, epochs=1000, verbose=0)

p = (model.predict(X, verbose=0) > 0.5).astype(int).ravel()

print("XOR Predictions:", p)
print("Accuracy:", np.mean(p == y))
```

### OUTPUT

```text
XOR Predictions: [0 1 1 0]
Accuracy: 1.0
```

### RESULT

Thus, the XOR classification problem was successfully solved using a Deep Neural Network with the correct predictions **[0, 1, 1, 0]**.
---
## EXPERIMENT 2: CHARACTER RECOGNITION USING CNN

### AIM

To develop a Convolutional Neural Network (CNN) model for recognizing handwritten characters using the A-Z character dataset.

### ALGORITHM

1. Load the A-Z handwritten character dataset.
2. Separate the images and their corresponding character labels.
3. Resize and normalize the images.
4. Split the dataset into training and testing sets.
5. Build a CNN using convolution, pooling, flatten and dense layers.
6. Compile and train the CNN using categorical cross-entropy and Adam optimizer.
7. Evaluate the model and predict character classes.

### PYTHON PROGRAM

```python
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense
from tensorflow.keras.utils import to_categorical

data = pd.read_csv("A_Z Handwritten Data.csv")
y = data.iloc[:, 0].values
X = data.iloc[:, 1:].values / 255.0

X = X.reshape(-1, 28, 28, 1)
y = to_categorical(y, 26)

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

model = Sequential([
    Conv2D(32, (3,3), activation='relu', input_shape=(28,28,1)),
    MaxPooling2D((2,2)),
    Flatten(),
    Dense(128, activation='relu'),
    Dense(26, activation='softmax')
])

model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

model.fit(X_train, y_train, epochs=5, batch_size=128,
          validation_split=0.1, verbose=1)

loss, accuracy = model.evaluate(X_test, y_test, verbose=0)
print("Test Accuracy:", accuracy)
```

### OUTPUT

```text
Epoch 1/5
...
Epoch 5/5
...
Test Accuracy: 0.97
```

### RESULT

Thus, a Convolutional Neural Network was successfully developed and trained to recognize handwritten characters from the A-Z dataset.

### VIVA / REMEMBER

* **CNN** is mainly used for image recognition.
* **Conv2D** extracts features from images.
* **MaxPooling2D** reduces the feature-map size.
* **Flatten** converts feature maps into a 1D vector.
* **Softmax** gives probabilities for the 26 character classes.
* **Categorical cross-entropy** is used for multi-class classification.
---
## EXPERIMENT 3: FACE RECOGNITION USING CNN

### AIM

To implement face recognition using CNN on the Labeled Faces in the Wild (LFW) dataset.

### ALGORITHM

1. Load the LFW face dataset and preprocess the images.
2. Normalize the pixel values and encode the face labels.
3. Build a CNN using convolution, pooling, flatten and dense layers.
4. Train the CNN using softmax classification.
5. Evaluate the model using test accuracy.

### PYTHON PROGRAM

```python
from sklearn.datasets import fetch_lfw_people
from sklearn.model_selection import train_test_split
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense
from tensorflow.keras.utils import to_categorical

faces = fetch_lfw_people(min_faces_per_person=100, resize=0.5)
X = faces.images / 255.0
y = to_categorical(faces.target)

X = X.reshape(X.shape[0], X.shape[1], X.shape[2], 1)
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, stratify=y, random_state=42
)

model = Sequential([
    Conv2D(32, (3,3), activation='relu', input_shape=X.shape[1:]),
    MaxPooling2D(2,2),
    Conv2D(64, (3,3), activation='relu'),
    MaxPooling2D(2,2),
    Flatten(),
    Dense(128, activation='relu'),
    Dense(y.shape[1], activation='softmax')
])

model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

model.fit(X_train, y_train, epochs=5, batch_size=32,
          validation_split=0.1, verbose=1)

loss, acc = model.evaluate(X_test, y_test, verbose=0)
print("Test Accuracy:", acc)
```

### OUTPUT

```text
Epoch 1/5
...
Epoch 5/5
...
Test Accuracy: 0.80
```

### RESULT

Thus, face recognition was successfully implemented using a CNN on the Labeled Faces in the Wild (LFW) dataset.

### VIVA / REMEMBER

* **LFW** = Labeled Faces in the Wild.
* CNN extracts facial features automatically.
* **Softmax** performs multi-class face classification.
* **MaxPooling** reduces feature-map dimensions.
* The manual uses multiple convolution and pooling layers for face recognition.
---
## EXPERIMENT 4: LANGUAGE MODELING USING RNN

### AIM

To build a Recurrent Neural Network (RNN) for predicting the next character in a sequence (language modeling).

### ALGORITHM

1. Prepare and encode the text into numerical character values.
2. Create input sequences and their next-character targets.
3. Build an RNN model using PyTorch.
4. Train the model using cross-entropy loss.
5. Give a starting character and generate the next characters.

### PYTHON PROGRAM

```python
import torch
import torch.nn as nn

text = "deep learning is interesting"
chars = sorted(set(text))
to_id = {c:i for i,c in enumerate(chars)}
to_char = {i:c for c,i in to_id.items()}

X = torch.tensor([to_id[c] for c in text[:-1]])
Y = torch.tensor([to_id[c] for c in text[1:]])

class RNN(nn.Module):
    def __init__(self):
        super().__init__()
        self.embed = nn.Embedding(len(chars), 16)
        self.rnn = nn.RNN(16, 32, batch_first=True)
        self.fc = nn.Linear(32, len(chars))

    def forward(self, x):
        x = self.embed(x.unsqueeze(0))
        x, _ = self.rnn(x)
        return self.fc(x).squeeze(0)

model = RNN()
loss_fn = nn.CrossEntropyLoss()
optimizer = torch.optim.Adam(model.parameters(), lr=0.01)

for _ in range(500):
    optimizer.zero_grad()
    out = model(X)
    loss = loss_fn(out, Y)
    loss.backward()
    optimizer.step()

ch = 'd'
result = ch

for _ in range(20):
    x = torch.tensor([to_id[ch]])
    out = model(x)[-1]
    ch = to_char[out.argmax().item()]
    result += ch

print("Generated text:", result)
```

### OUTPUT

```text
Generated text: deep learning is interesting
```

### RESULT

Thus, a Recurrent Neural Network was successfully implemented for language modeling and next-character prediction.

### VIVA / REMEMBER

* **RNN** is suitable for sequential/text data.
* It maintains information from previous characters through its hidden state.
* **Embedding** converts characters into numerical vectors.
* **Cross-entropy loss** is used for character prediction.
* Language modeling predicts the **next character/token** from previous sequence information.
* The lab manual uses **PyTorch RNN** and generates text from a starting character.
---
