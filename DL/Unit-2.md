# Unit-1
## INTRODUCTION TO DATA HANDLING

Data handling is the systematic process of acquiring, cleaning, organizing, transforming, and preparing data to train deep learning models effectively. The success of deep learning models depends heavily on the quality and quantity of data.

### Data Collection

Data collection gathers and measures information from various sources to obtain a complete dataset.

| Source | Description |
| --- | --- |
| **Sensors/IoT Devices** | Real-time data from devices like temperature, pressure, or ECG sensors.

 |
| **Web Scraping** | Extracting structured or unstructured data from websites.

 |
| **APIs** | Accessing external data via services like Twitter or Google Maps.

 |
| **Databases** | Structured data from SQL or NoSQL databases.

 |
| **Public Datasets** | Datasets provided by research institutions, such as MNIST, CIFAR-10, and ImageNet.

 |
| **Manual Collection** | Surveys, questionnaires, human labeling, and experiments.

 |

### Data Preprocessing

This process transforms raw data into a clean, machine-readable format before feeding it into a model.

* **Data Cleaning:** Handle missing values via imputation or deletion, remove duplicate records, and correct typos.


* **Noise Removal:** Eliminate outliers or errors, and apply smoothing techniques.


* **Feature Engineering:** Create new relevant features or remove irrelevant ones.


* **Encoding Categorical Variables:** Convert categorical data to numerical data using Label Encoding or One-Hot Encoding.


* **Data Formatting:** Standardize the format, such as resizing images or converting text to lowercase.


* **Data Shuffling:** Randomize data to prevent order-based bias.


* **Splitting the Dataset:** Divide into a Training Set, Validation Set, and Test Set.



---

## Normalization

Normalization scales individual input features to fall within a specific range or distribution. This speeds up convergence, reduces numerical instability, and improves model performance.

| Type | Formula | Range/Effect |
| --- | --- | --- |
| **Min-Max Normalization** | $x_{norm}=\frac{x - x_{min}}{x_{max} - x_{min}}$<br> | Scales values between 0 and 1

 |
| **Z-score Normalization** | $x_{norm}=\frac{x - \mu}{\sigma}$<br> | Mean = 0, Standard Deviation = 1

 |
| **Decimal Scaling** | $x_{norm}=\frac{x}{10^j}$<br> | Scales by powers of 10

 |
| **Unit Vector Scaling** | Normalize input vector to unit length

 | Norm = 1

 |

---

## Normalization in Image Data

Deep learning libraries provide specialized methods to normalize image tensors during the preprocessing pipeline.

### PyTorch

PyTorch applies zero-mean and unit-variance normalization channel-wise using standard deviation and mean.


$$x_{norm} = \frac{x - \text{mean}}{\text{std}}$$

```python
from torchvision import transforms

# Applies normalization to 3 channels (RGB)
transform = transforms.Normalize(
    mean=[0.5, 0.5, 0.5], 
    std=[0.5, 0.5, 0.5]
)

```

### TensorFlow

TensorFlow performs zero-mean and unit-variance normalization on the image tensor.

```python
import tensorflow as tf

# Zero-mean and unit-variance normalization
image = tf.image.per_image_standardization(image)

```

### Keras

Keras scales the pixel values from their original range directly to a target range.

```python
from tensorflow.keras.preprocessing.image import ImageDataGenerator

# Rescales pixel values from [0, 255] to [0, 1]
datagen = ImageDataGenerator(rescale=1./255)

```
Step 1: Download CIFAR-10 dataset
Step 2: Resize all images to 32x32
Step 3: Convert images to tensors
Step 4: Normalize pixel values using dataset-specific mean and standard deviation
Step 5: Shuffle and split into training/validation/test sets
---
## DATA PROCESSING AND NORMALIZATION USING DEEP LEARNING LIBRARIES

### Role of Deep Learning Libraries in Data Processing

Modern deep learning libraries simplify and accelerate the data pipeline through specialized modules that automate key tasks.

* **Key Functions**: These libraries support loading and transforming datasets, batching and shuffling data, applying preprocessing transformations, normalizing data, and executing data augmentation.


* **Benefits**:
* **Automation**: Drastically reduces manual coding effort.


* **Efficiency**: Optimized to run smoothly on GPU/TPU pipelines.


* **Consistency**: Standardized preprocessing steps ensure repeatable, reliable results.


* **Scalability**: Handles extremely large datasets seamlessly using batching and streaming techniques.





---

### Data Processing in PyTorch

PyTorch manages data processing using dedicated modules for pipelines, transformations, and loaders.

* **torchvision.datasets**: Used for loading pre-existing and standard datasets.


* **torchvision.transforms**: Contains functions for preprocessing and data augmentation.


* **DataLoader**: Responsible for efficient data batching and shuffling.



#### Code

```python
import torch
from torchvision import datasets, transforms
from torch.utils.data import DataLoader

# Define transform pipeline
transform = transforms.Compose([
    transforms.Resize((32, 32)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.5], std=[0.5]) # For grayscale
])

# Load dataset (using MNIST as a standard example)
dataset = datasets.MNIST(root='./data', train=True, download=True, transform=transform)

# Create DataLoader
loader = DataLoader(dataset, batch_size=64, shuffle=True)

# Fetch a single batch to verify processing
data_iter = iter(loader)
images, labels = next(data_iter)

print("PyTorch Processing Successful!")
print("Batch images shape :", images.shape)
print("Batch labels shape :", labels.shape)
print("Image pixel range  :", images.min().item(), "to", images.max().item())

```

#### Output

```text
PyTorch Processing Successful!
Batch images shape : torch.Size([64, 1, 32, 32])
Batch labels shape : torch.Size([64])
Image pixel range  : -1.0 to 1.0

```

---

### Data Processing in TensorFlow

TensorFlow utilizes high-performance modules to build input pipelines and perform image transformations.

* **tf.data**: High-performance API used to build efficient data pipelines.


* **tf.image**: Library specifically designed for image preprocessing operations.



#### Code

```python
import tensorflow as tf

# Simulate a batch of 4 raw images with values from 0 to 255
dummy_raw_images = tf.random.uniform(shape=[4, 28, 28, 3], minval=0, maxval=255, dtype=tf.int32)

# Custom processing function
def process_image(image):
    # Convert to tensor and scale pixel values to [0, 1]
    image = tf.image.convert_image_dtype(image, tf.float32)
    # Resize image
    image = tf.image.resize(image, [32, 32])
    return image

# Map processing over raw batch data
processed_images = tf.map_fn(process_image, tf.cast(dummy_raw_images, tf.float32))

print("TensorFlow Processing Successful!")
print("Processed batch shape:", processed_images.shape)
print("Image pixel range   :", tf.reduce_min(processed_images).numpy(), "to", tf.reduce_max(processed_images).numpy())

```

#### Output

```text
TensorFlow Processing Successful!
Processed batch shape: (4, 32, 32, 3)
Image pixel range   : 0.0 to 1.0

```

---

### Data Processing in Keras

Keras provides simple, high-level utilities to handle and preprocess real-time data.

* **ImageDataGenerator**: Tool used for real-time data loading, rescaling, and basic augmentation.



#### Code

```python
import numpy as np
from tensorflow.keras.preprocessing.image import ImageDataGenerator

# Simulate a batch of 2 synthetic RGB images of size 32x32 (0 to 255)
dummy_images = np.random.randint(0, 256, size=(2, 32, 32, 3)).astype('float32')

# Rescales pixel values from [0, 255] to [0, 1]
datagen = ImageDataGenerator(rescale=1./255)

# Fit generator on the mock array data
iterator = datagen.flow(dummy_images, batch_size=2)
processed_batch = next(iterator)

print("Keras Processing Successful!")
print("Processed batch shape:", processed_batch.shape)
print("Image pixel range   :", processed_batch.min(), "to", processed_batch.max())

```

#### Output

```text
Keras Processing Successful!
Processed batch shape: (2, 32, 32, 3)
Image pixel range   : 0.0 to 1.0

```

---

### Normalization in Practice

Deep learning libraries apply distinct mathematical approaches to normalize data during preprocessing.

* **PyTorch**: Normalizes data channel-wise using a user-specified mean and standard deviation.



$$\text{Pixel}_{\text{norm}} = \frac{\text{Pixel} - \text{mean}}{\text{std}}$$


```python
transforms.Normalize(mean=[0.5, 0.5, 0.5], std=[0.5, 0.5, 0.5])

```


* **TensorFlow**: Implements zero-mean and unit-variance normalization on the image tensor.


```python
image = tf.image.per_image_standardization(image)

```


* **Keras**: Performs straightforward division scaling to rescale the inputs.


```python
ImageDataGenerator(rescale=1./255)

```
---

