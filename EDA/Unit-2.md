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