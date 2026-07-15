# Unit-2

# Data Manipulation using Pandas
---
# 1. Why Data Manipulation?

### Definition

Data Manipulation (Data Wrangling) is the process of cleaning, organizing, and transforming raw data into a usable format.

### Need

* Removes missing values (NaN, Null, NA)
* Removes unnecessary rows and columns
* Improves data quality
* Prepares data for analysis and Machine Learning

### Key Points

* Raw data is usually incomplete and inconsistent.
* Clean data gives accurate results.
* Pandas is mainly used for data manipulation. 

---

# 2. What is Pandas?

### Definition

Pandas is an open-source Python library built on top of NumPy for data manipulation and analysis.

### Features

* Fast and efficient
* Handles missing values
* Supports heterogeneous data
* Easy data analysis
* Similar to Excel tables and SQL operations

### Main Objects

1. **Series** – One-dimensional labeled array.
2. **DataFrame** – Two-dimensional table with rows and columns. 

---

# 3. Installing Pandas

### Command

```bash
pip install pandas
```

### Import

```python
import pandas as pd
```



---

# 4. Creating a DataFrame

### Definition

A **DataFrame** is a two-dimensional table consisting of rows and columns.

### Syntax

```python
pd.DataFrame()
```

### Code

```python
import pandas as pd

student = pd.DataFrame()

student["Name"] = ["Abhijit", "Smriti", "Akash"]
student["Age"] = [20, 19, 20]
student["Student"] = [False, True, True]

print(student)
```

### Output

```
      Name      Age  Student
0  Abhijit     20    False
1  Smriti      19    True
2  Akash       20    True
```

### Key Points

* Stores data in tabular form.
* Index starts from **0** by default.
* Index can be changed. 

---

# 5. Adding Data Using append()

### Definition

`append()` adds a new row (Series/DataFrame) to an existing DataFrame.

### Syntax

```python
df.append(new_row, ignore_index=True)
```

### Code

```python
new_person = pd.Series(
    ["Mansi", 19, True],
    index=["Name", "Age", "Student"]
)

student = student.append(new_person, ignore_index=True)

print(student)
```

### Key Points

* New row must be a **Series**.
* `ignore_index=True` creates new index values.
* Returns a new DataFrame. 

---

# 6. Data Exploration

### Definition

Data Exploration is the process of understanding the structure and summary of a dataset.

### Common Functions

| Function     | Purpose                             |
| ------------ | ----------------------------------- |
| `shape`      | Returns (rows, columns)             |
| `info()`     | Dataset summary                     |
| `corr()`     | Correlation between numeric columns |
| `describe()` | Statistical summary                 |

### Code

```python
print(student.shape)
print(student.info())
print(student.corr())
```

### Important Points

### `.shape`

* Returns number of rows and columns.

### `.info()`

Shows:

* Number of rows
* Number of columns
* Column names
* Data types
* Non-null values
* Memory usage

### `.corr()`

* Finds correlation between numerical columns.
* Correlation value ranges from **-1 to +1**. 

---

# 7. Statistical Summary with `describe()`

### Definition

`describe()` returns statistical information about numerical columns.

### Syntax

```python
df.describe()
```

### Code

```python
print(student.describe())
```

### Statistics Returned

| Function | Meaning             |
| -------- | ------------------- |
| count    | Number of values    |
| mean     | Average             |
| std      | Standard deviation  |
| min      | Minimum value       |
| 25%      | First Quartile (Q1) |
| 50%      | Median (Q2)         |
| 75%      | Third Quartile (Q3) |
| max      | Maximum value       |

### Uses

* Understand data distribution
* Detect outliers
* Summarize numerical data 

---

# 8. Dropping Columns and Rows

## Drop Column

### Syntax

```python
df.drop(column_name, axis=1)
```

### Code

```python
student = student.drop("Age", axis=1)
```

---

## Drop Row

### Syntax

```python
df.drop(row_index, axis=0)
```

### Code

```python
student = student.drop(2, axis=0)
```

### Meaning of `axis`

| Axis     | Meaning |
| -------- | ------- |
| `axis=0` | Rows    |
| `axis=1` | Columns |



---

# 9. Data Manipulation on Larger Datasets

### Purpose

Used to quickly understand large datasets before analysis.

### Common Functions

| Function     | Purpose                             |
| ------------ | ----------------------------------- |
| `shape`      | Dataset dimensions                  |
| `info()`     | Structure and data types            |
| `corr()`     | Correlation between numeric columns |
| `describe()` | Statistical summary                 |
| `drop()`     | Remove rows/columns                 |

### Benefits

* Understand dataset structure
* Find missing values
* Identify data types
* Analyze relationships
* Prepare data for Machine Learning 
---
# 2.Pandas Objects

Pandas mainly provides **two data structures (objects)**:

1. **Series** – One-dimensional labeled array.
2. **DataFrame** – Two-dimensional table made up of multiple Series. 

---

# 2. Pandas Series

### Definition

A **Series** is a one-dimensional labeled array that can store any data type.

### Features

* One-dimensional
* Has index labels
* More flexible than NumPy arrays
* Similar to a dictionary

---

## Creating a Series

### Syntax

```python
pd.Series(data)
```

### Code

```python
import pandas as pd

data = pd.Series([10, 20, 30, 40])
print(data)
```

---

## Accessing Series

```python
data[1]      # 20
data[1:3]    # 20,30
```

---

## Important Attributes

```python
data.values    # Returns values
data.index     # Returns index
```

---

## Creating Series with Custom Index

```python
data = pd.Series([10,20,30], index=["A","B","C"])
```

Access:

```python
data["B"]
```

---

## Creating Series from Dictionary

```python
population = pd.Series({
    "India":140,
    "USA":33,
    "Japan":12
})
```

---

## Ways to Create Series

### From List

```python
pd.Series([2,4,6])
```

### From Scalar

```python
pd.Series(5,index=[1,2,3])
```

### From Dictionary

```python
pd.Series({1:"A",2:"B"})
```

### Key Points

* Stores one column of data.
* Every value has an index.
* Supports slicing and indexing.
* Can be created from list, scalar or dictionary. 

---

# 3. Pandas DataFrame

### Definition

A **DataFrame** is a two-dimensional table consisting of multiple Series.

### Features

* Rows and columns
* Different data types
* Labeled axes
* Easy data manipulation

---

## Example

```python
population = pd.Series({"A":100,"B":200})
area = pd.Series({"A":50,"B":80})

states = pd.DataFrame({
    "Population":population,
    "Area":area
})

print(states)
```

---

## Important Attributes

```python
states.index
states.columns
states["Area"]
```

---

## Ways to Create DataFrame

### From Series

```python
pd.DataFrame(population)
```

### From List of Dictionaries

```python
data=[{"A":1,"B":2},{"A":3,"B":4}]
pd.DataFrame(data)
```

### From Dictionary

```python
pd.DataFrame({"Population":population,"Area":area})
```

### From NumPy Array

```python
pd.DataFrame(np.random.rand(3,2),
columns=["A","B"])
```

### Key Points

* Collection of Series.
* Two-dimensional.
* Supports row and column indexing.
* Different columns can have different data types. 

---

# 4. The Pandas Index Object

### Definition

Index is an immutable object used to label rows and columns.

---

## Creating Index

```python
import pandas as pd

ind = pd.Index([2,3,5,7,11])
```

---

## Accessing

```python
ind[1]
ind[::2]
```

---

## Index Attributes

```python
ind.size
ind.shape
ind.ndim
ind.dtype
```

---

## Immutability

```python
ind[1]=0
```

**Output**

```
TypeError
```

---

## Set Operations

```python
indA = pd.Index([1,3,5,7])
indB = pd.Index([3,5,7,9])

indA & indB    # Intersection
indA | indB    # Union
indA ^ indB    # Symmetric Difference
```

### Key Points

* Immutable (cannot be modified).
* Supports set operations.
* Similar to NumPy array but stores labels. 

---

# 5. Data Indexing and Selection

### Definition

Data Indexing and Selection means accessing specific rows or columns from Series or DataFrame.

### Supports

* Indexing
* Slicing
* Masking
* Fancy Indexing

Like NumPy but with additional features. 

---

# 6. Data Selection in Series

### Series as Dictionary

```python
data = pd.Series(
[10,20,30,40],
index=["a","b","c","d"]
)

data["b"]
```

---

## Dictionary Methods

```python
"a" in data

data.keys()

list(data.items())
```

---

## Adding New Value

```python
data["e"]=50
```

---

## Series as Array

### Slicing

```python
data["a":"c"]
```

### Position Slicing

```python
data[0:2]
```

### Masking

```python
data[data>20]
```

### Fancy Indexing

```python
data[["a","c"]]
```

### Note

* Label slicing includes last label.
* Position slicing excludes last position. 

---

# 7. loc, iloc and ix

## loc

Uses **label (explicit index).**

### Syntax

```python
data.loc[label]
```

Example

```python
data.loc[1]
data.loc[1:3]
```

---

## iloc

Uses **integer position (implicit index).**

### Syntax

```python
data.iloc[position]
```

Example

```python
data.iloc[1]
data.iloc[1:3]
```

---

## ix

Hybrid of loc and iloc.

**Note:** Deprecated (not used in latest Pandas).

### Best Practice

* Use **loc** for labels.
* Use **iloc** for positions. 

---

# 8. Data Selection in DataFrame

### Definition

A DataFrame behaves like:

* Dictionary of Series
* Two-dimensional array

---

## Accessing Columns

### Dictionary Style

```python
data["area"]
```

### Attribute Style

```python
data.area
```

---

## Adding New Column

```python
data["density"] = data["pop"]/data["area"]
```

---

## Raw Values

```python
data.values
```

---

## Transpose

```python
data.T
```

---

## iloc

```python
data.iloc[:3,:2]
```

---

## loc

```python
data.loc[:,"area":"pop"]
```

---

## Masking

```python
data.loc[data.density>100,
["pop","density"]]
```

---

## Modify Value

```python
data.iloc[0,2]=90
```

### Key Points

* `[]` selects columns.
* `loc` uses labels.
* `iloc` uses positions.
* Supports adding and modifying columns. 

---

# 9. Additional Indexing Conventions

## Row Label Slicing

```python
data["Florida":"Illinois"]
```

---

## Row Position Slicing

```python
data[1:3]
```

---

## Row Masking

```python
data[data.density>100]
```

### Key Points

* Row slicing by labels includes end label.
* Position slicing excludes last position.
* Boolean masking filters rows satisfying a condition. 

---

# **Difference: loc vs iloc**

| loc                           | iloc                   |
| ----------------------------- | ---------------------- |
| Uses labels                   | Uses integer positions |
| End label included in slicing | End position excluded  |
| Example: `df.loc["A"]`        | Example: `df.iloc[0]`  |
| Explicit indexing             | Implicit indexing      |

---

# **Difference: Series vs DataFrame**

| Series                      | DataFrame                      |
| --------------------------- | ------------------------------ |
| One-dimensional             | Two-dimensional                |
| Single column               | Multiple columns               |
| Has one index               | Has row and column indexes     |
| Created using `pd.Series()` | Created using `pd.DataFrame()` |
---
# 3. Pandas Data Operations

### Definition

Pandas Data Operations are used to manipulate, analyze, clean, and transform data.

### Operations

* Row & Column Selection
* Filtering
* Handling Missing Values
* String Operations
* Counting Values
* Plotting
* Universal Functions (UFuncs)
* DataFrame & Series Operations 

---

# 2. Row and Column Selection

### Select Column

```python
df["Name"]
```

### Select Multiple Columns

```python
df[["Name", "Age"]]
```

### Select Row

```python
df.loc[0]
```

### Select Multiple Rows

```python
df.loc[0:2]
```

### Select Specific Rows & Columns

```python
df.loc[0:2, ["Name", "Age"]]
```

---

# 3. Filtering Data

### Definition

Returns only rows satisfying a condition.

### Code

```python
df[df["Age"] > 18]
```

```python
df[df["Gender"] == "Male"]
```

```python
df[(df["Age"] > 18) & (df["Marks"] > 80)]
```

```python
df[(df["Age"] > 18) | (df["Marks"] > 80)]
```

---

# 4. String Operations

All string functions use **`.str`**

### Lowercase

```python
df["Name"].str.lower()
```

### Uppercase

```python
df["Name"].str.upper()
```

### Remove Spaces

```python
df["Name"].str.strip()
```

### Split

```python
df["Name"].str.split()
```

### Replace

```python
df["Name"].str.replace("a","A")
```

### Contains

```python
df["Name"].str.contains("a")
```

### Starts With

```python
df["Name"].str.startswith("A")
```

### Ends With

```python
df["Name"].str.endswith("n")
```

### Length

```python
df["Name"].str.len()
```

### Count Character

```python
df["Name"].str.count("a")
```

### Repeat

```python
df["Name"].str.repeat(2)
```

### Find Position

```python
df["Name"].str.find("a")
```

### Numeric Check

```python
df["Name"].str.isnumeric()
```

### Common Methods

| Method       | Purpose          |
| ------------ | ---------------- |
| lower()      | Lowercase        |
| upper()      | Uppercase        |
| strip()      | Remove spaces    |
| split()      | Split string     |
| contains()   | Check substring  |
| replace()    | Replace text     |
| repeat()     | Repeat string    |
| count()      | Count characters |
| startswith() | Check beginning  |
| endswith()   | Check ending     |
| find()       | Find position    |
| isnumeric()  | Numeric check    |



---

# 5. Count Values

### Definition

Counts occurrences of unique values.

### Code

```python
df["Gender"].value_counts()
```

```python
df["Department"].value_counts()
```



---

# 6. Plotting with Pandas

### Syntax

```python
df.plot()
```

### Line Plot

```python
df.plot()
```

### Bar Plot

```python
df.plot(kind="bar")
```

### Histogram

```python
df.plot(kind="hist")
```

### Pie Chart

```python
df["Gender"].value_counts().plot(kind="pie")
```



---

# 7. Universal Functions (UFuncs)

### Definition

NumPy functions that work directly on Pandas objects.

### Code

```python
import numpy as np

np.exp(df["Age"])
```

```python
np.sqrt(df["Marks"])
```

```python
np.sin(df["Marks"])
```

```python
np.log(df["Salary"])
```

### Arithmetic Methods

| Operator | Method     |
| -------- | ---------- |
| +        | add()      |
| -        | sub()      |
| *        | mul()      |
| /        | div()      |
| //       | floordiv() |
| %        | mod()      |
| **       | pow()      |



---

# 8. Operations Between DataFrame and Series

### Row-wise Operation

```python
df - df.iloc[0]
```

### Column-wise Operation

```python
df.subtract(df["Marks"], axis=0)
```

### Addition

```python
df.add(10)
```

### Multiplication

```python
df.mul(2)
```

### Division

```python
df.div(2)
```

### Power

```python
df.pow(2)
```



---

# 9. Element-wise Operation vs Broadcasting

## Element-wise

```python
a = np.array([1,2,3])
b = np.array([4,5,6])

a+b
```

## Broadcasting

```python
a = np.array([[1,2,3],
              [4,5,6]])

b = np.array([10,20,30])

a+b
```

| Element-wise         | Broadcasting          |
| -------------------- | --------------------- |
| Same shape           | Different shapes      |
| One-to-one operation | Smaller array expands |



---

# 10. Handling Missing Data

### Definition

Missing data means values are unavailable.

### Represented By

* None
* NaN

### Missing Data Functions

| Function      | Purpose                 |
| ------------- | ----------------------- |
| isnull()      | Detect missing values   |
| notnull()     | Detect available values |
| fillna()      | Replace missing values  |
| dropna()      | Remove missing values   |
| replace()     | Replace values          |
| interpolate() | Estimate missing values |

---

## Checking Missing Values

```python
df.isnull()
```

```python
df.notnull()
```

```python
df.isnull().sum()
```

```python
df[df.isnull().any(axis=1)]
```

---

## Filling Missing Values

### Replace with 0

```python
df.fillna(0)
```

### Replace with Mean

```python
df["Salary"].fillna(df["Salary"].mean())
```

### Replace with Median

```python
df["Salary"].fillna(df["Salary"].median())
```

### Replace Specific Value

```python
df.replace(np.nan,100)
```

### Interpolation

```python
df.interpolate()
```

---

## Dropping Missing Values

### Drop Rows

```python
df.dropna()
```

### Drop Columns

```python
df.dropna(axis=1)
```

### Drop Rows with All NaN

```python
df.dropna(how="all")
```

### Drop Rows Having Less Than Required Values

```python
df.dropna(thresh=3)
```

---

## None vs NaN

| None                  | NaN                    |
| --------------------- | ---------------------- |
| Python object         | Floating-point value   |
| Used with object type | Used with numeric data |
| Slower                | Faster                 |

### Key Points

* `isnull()` and `isna()` are equivalent.
* `fillna()` replaces missing values.
* `dropna()` removes missing values.
* `interpolate()` estimates missing values.
* Prefer **NaN** for numerical computations. 


---