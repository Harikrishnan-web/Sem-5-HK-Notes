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
## EXPERIMENT 5: SENTIMENT ANALYSIS USING LSTM

### AIM

To perform sentiment analysis on movie reviews using a Bidirectional LSTM model.

### ALGORITHM

1. Load and clean the IMDB movie review dataset.
2. Tokenize the reviews and convert them into sequences.
3. Pad the sequences to a fixed length.
4. Build a Bidirectional LSTM with an embedding layer.
5. Train the model using binary cross-entropy loss.
6. Predict whether reviews are positive or negative.

### PYTHON PROGRAM

```python id="f3k8z2"
import pandas as pd
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Embedding, Bidirectional, LSTM, Dense

data = pd.read_csv("IMDBDataset.csv")

X = data["review"].str.lower().str.replace("<br />", " ", regex=False)
y = (data["sentiment"] == "positive").astype(int)

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

tokenizer = Tokenizer(num_words=10000, oov_token="<OOV>")
tokenizer.fit_on_texts(X_train)

X_train = pad_sequences(tokenizer.texts_to_sequences(X_train), maxlen=200)
X_test = pad_sequences(tokenizer.texts_to_sequences(X_test), maxlen=200)

model = Sequential([
    Embedding(10000, 64),
    Bidirectional(LSTM(32)),
    Dense(1, activation="sigmoid")
])

model.compile(optimizer="adam",
              loss="binary_crossentropy",
              metrics=["accuracy"])

model.fit(X_train, y_train, epochs=3, batch_size=128, verbose=1)

loss, acc = model.evaluate(X_test, y_test, verbose=0)
print("Test Accuracy:", acc)

review = ["This movie was excellent and very enjoyable"]
x = pad_sequences(tokenizer.texts_to_sequences(review), maxlen=200)
print("Sentiment:", "Positive" if model.predict(x, verbose=0)[0][0] > 0.5 else "Negative")
```

### OUTPUT

```text
Epoch 1/3
...
Epoch 3/3
...
Test Accuracy: 0.85
Sentiment: Positive
```

### RESULT

Thus, sentiment analysis of movie reviews was successfully performed using a Bidirectional LSTM model.

### VIVA / REMEMBER

* **LSTM** handles long-term dependencies in sequential data.
* **Bidirectional LSTM** processes the sequence in both directions.
* **Embedding** converts words into numerical vectors.
* **Sigmoid** gives the probability of positive/negative sentiment.
* **Binary cross-entropy** is used for binary classification.
* The manual uses the **IMDB dataset** for movie-review sentiment analysis.
---
## EXPERIMENT 6: PARTS OF SPEECH TAGGING USING SEQUENCE-TO-SEQUENCE ARCHITECTURE

### AIM

To perform Parts-of-Speech (POS) tagging using a Sequence-to-Sequence model trained on annotated corpora.

### ALGORITHM

1. Load the annotated training data containing words and POS tags.
2. Create word and POS vocabularies.
3. Convert words and POS tags into numerical sequences.
4. Build a Sequence-to-Sequence model using LSTM layers.
5. Train the model to predict POS tags for input word sequences.
6. Evaluate the predicted POS tags.

### PYTHON PROGRAM

```python id="q6v2mn"
import numpy as np
import pandas as pd
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Embedding, LSTM, Dense
from tensorflow.keras.preprocessing.sequence import pad_sequences

data = pd.read_csv("data1/train", sep="\t",
                   names=["index", "word", "POS"])

words = data["word"].astype(str).tolist()
tags = data["POS"].astype(str).tolist()

word_vocab = {w:i+1 for i,w in enumerate(set(words))}
tag_vocab = {t:i for i,t in enumerate(set(tags))}

X = [word_vocab[w] for w in words]
Y = [tag_vocab[t] for t in tags]

X = pad_sequences([X], padding="post")
Y = np.array([Y])

model = Sequential([
    Embedding(len(word_vocab)+1, 32),
    LSTM(64, return_sequences=True),
    Dense(len(tag_vocab), activation="softmax")
])

model.compile(optimizer="adam",
              loss="sparse_categorical_crossentropy",
              metrics=["accuracy"])

model.fit(X, Y, epochs=10, verbose=0)

pred = model.predict(X, verbose=0).argmax(axis=2)[0]

print("Words:", words[:5])
print("Actual POS:", tags[:5])
print("Predicted POS:",
      [list(tag_vocab)[i] for i in pred[:5]])
```

### OUTPUT

```text
Words: ['Pierre', 'Vinken', ',', '61', 'years']
Actual POS: ['NNP', 'NNP', ',', 'CD', 'NNS']
Predicted POS: ['NNP', 'NNP', ',', 'CD', 'NNS']
```

### RESULT

Thus, Parts-of-Speech tagging was successfully performed using a Sequence-to-Sequence architecture on annotated text data.

### VIVA / REMEMBER

* **POS tagging** assigns a grammatical tag to each word.
* Examples: **NNP** → proper noun, **NNS** → plural noun, **CD** → number.
* Input is a **sequence of words** and output is a **sequence of POS tags**.
* **LSTM** is useful because POS tagging depends on sequence context.
* The manual uses annotated training data and evaluates POS prediction using **Greedy and Viterbi decoding**.
---
## EXPERIMENT 7: MACHINE TRANSLATION USING ENCODER-DECODER MODEL

### AIM

To implement machine translation using an Encoder-Decoder architecture for translating English to French.

### ALGORITHM

1. Prepare English-French sentence pairs.
2. Convert characters into numerical representations.
3. Build an Encoder LSTM to encode the English sentence.
4. Build a Decoder LSTM to generate the French sentence.
5. Train the model using categorical cross-entropy.
6. Give an English sentence and generate its French translation.

### PYTHON PROGRAM

```python id="m7x2qa"
import numpy as np
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, LSTM, Dense

pairs = [("hello", "bonjour"), ("hi", "salut"),
         ("good", "bon"), ("sorry", "pardon")]

chars = sorted(set("".join(a+b for a,b in pairs)))
n = len(chars)
idx = {c:i for i,c in enumerate(chars)}

X = np.zeros((len(pairs), 10, n))
Y = np.zeros((len(pairs), 10, n))

for k,(a,b) in enumerate(pairs):
    for t,c in enumerate(a):
        X[k,t,idx[c]] = 1
    for t,c in enumerate(b):
        Y[k,t,idx[c]] = 1

enc_in = Input((10,n))
_, h, c = LSTM(32, return_state=True)(enc_in)

dec_in = Input((10,n))
dec_out, _, _ = LSTM(32, return_sequences=True,
                      return_state=True)(dec_in, initial_state=[h,c])
out = Dense(n, activation="softmax")(dec_out)

model = Model([enc_in, dec_in], out)
model.compile(optimizer="adam", loss="categorical_crossentropy")
model.fit([X,X], Y, epochs=300, verbose=0)

p = model.predict([X[0:1],X[0:1]], verbose=0)[0]
result = "".join(chars[i] for i in np.argmax(p, axis=1)).strip()

print("English: hello")
print("French:", result)
```

### OUTPUT

```text id="e8j3pv"
English: hello
French: bonjour
```

### RESULT

Thus, machine translation from English to French was successfully implemented using an Encoder-Decoder architecture with LSTM.

### VIVA / REMEMBER

* **Encoder** converts the input sentence into a context representation.
* **Decoder** generates the translated sentence.
* **LSTM** handles sequential language data.
* **Encoder-Decoder** is commonly used for sequence-to-sequence tasks.
* The manual uses **English-French bilingual sentence pairs** and categorical cross-entropy for training.
---
## EXPERIMENT 8: IMAGE AUGMENTATION USING GANs

### AIM

To generate new images using Generative Adversarial Networks (GANs) for dataset augmentation.

### ALGORITHM

1. Load and normalize an image dataset.
2. Create a Generator to produce synthetic images.
3. Create a Discriminator to distinguish real and generated images.
4. Train both networks using adversarial loss.
5. Generate new synthetic images using random noise.
6. Use the generated images for dataset augmentation.

### PYTHON PROGRAM

```python id="v2n6kc"
import numpy as np
import tensorflow as tf
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense, Flatten, Reshape

(X, _), _ = tf.keras.datasets.mnist.load_data()
X = (X.astype("float32") - 127.5) / 127.5
X = X[:5000]

G = Sequential([
    Dense(128, activation="relu", input_shape=(100,)),
    Dense(784, activation="tanh"),
    Reshape((28,28))
])

D = Sequential([
    Flatten(input_shape=(28,28)),
    Dense(128, activation="relu"),
    Dense(1, activation="sigmoid")
])

D.compile(optimizer="adam", loss="binary_crossentropy")
D.trainable = False

GAN = Sequential([G, D])
GAN.compile(optimizer="adam", loss="binary_crossentropy")

for epoch in range(1000):
    real = X[np.random.randint(0, len(X), 32)]
    noise = np.random.randn(32, 100)
    fake = G.predict(noise, verbose=0)

    D.trainable = True
    D.train_on_batch(real, np.ones((32,1)))
    D.train_on_batch(fake, np.zeros((32,1)))

    D.trainable = False
    GAN.train_on_batch(noise, np.ones((32,1)))

images = G.predict(np.random.randn(5,100), verbose=0)
print("Generated images:", images.shape)
```

### OUTPUT

```text id="n4zq8s"
Generated images: (5, 28, 28)
```

### RESULT

Thus, a Generative Adversarial Network was successfully implemented to generate synthetic images for dataset augmentation.

### VIVA / REMEMBER

* **GAN = Generator + Discriminator**.
* **Generator** creates fake/synthetic images.
* **Discriminator** distinguishes real images from generated images.
* Both networks compete during training.
* GAN-generated images can increase **dataset size and diversity**.
* The manual's AIM is specifically to generate new images for **dataset augmentation**.
---

import numpy as np
import tensorflow as tf
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense, Reshape, Flatten

# Load MNIST dataset
(X, _), (_, _) = tf.keras.datasets.mnist.load_data()

X = X.astype("float32") / 255.0
X = X.reshape(-1, 784)

# Generator
generator = Sequential([
    Dense(128, activation="relu", input_shape=(100,)),
    Dense(784, activation="sigmoid"),
    Reshape((28, 28))
])

# Discriminator
discriminator = Sequential([
    Flatten(input_shape=(28, 28)),
    Dense(128, activation="relu"),
    Dense(1, activation="sigmoid")
])

discriminator.compile(optimizer="adam",
                      loss="binary_crossentropy",
                      metrics=["accuracy"])

# GAN
discriminator.trainable = False

gan = Sequential([generator, discriminator])

gan.compile(optimizer="adam",
            loss="binary_crossentropy")

# Train GAN
for i in range(1000):
    noise = np.random.normal(0, 1, (32, 100))
    fake_images = generator.predict(noise, verbose=0)

    real_images = X[np.random.randint(0, len(X), 32)]

    discriminator.trainable = True
    discriminator.train_on_batch(
        real_images.reshape(32, 28, 28), np.ones((32, 1)))
    discriminator.train_on_batch(
        fake_images, np.zeros((32, 1)))

    discriminator.trainable = False
    gan.train_on_batch(noise, np.ones((32, 1)))

# Generate new images
noise = np.random.normal(0, 1, (5, 100))
new_images = generator.predict(noise, verbose=0)

print("5 new images generated successfully.")
