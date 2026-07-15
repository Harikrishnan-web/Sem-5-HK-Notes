# Unit-2

## Data manipulation using Pandas

---

## 1. Why data manipulation?

### Definition

Data Manipulation (Data Wrangling) is the process of cleaning, organizing, and transforming raw data into a usable format.

### Need

- Removes missing values (NaN, Null, NA)
- Removes unnecessary rows and columns
- Improves data quality
- Prepares data for analysis and Machine Learning

### Key Points

- Raw data is usually incomplete and inconsistent.
- Clean data gives accurate results.
- Pandas is mainly used for data manipulation.

---

## 2. What is Pandas?

### Definition

Pandas is an open-source Python library built on top of NumPy for data manipulation and analysis.

### Features

- Fast and efficient
- Handles missing values
- Supports heterogeneous data
- Easy data analysis
- Similar to Excel tables and SQL operations

### Main Objects

1. **Series** – One-dimensional labeled array.
2. **DataFrame** – Two-dimensional table with rows and columns.

---

## 3. Installing Pandas

### Command

```bash
pip install pandas
```

### Import

```python
import pandas as pd
```

---

## 4. Creating a DataFrame

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

- Stores data in tabular form.
- Index starts from **0** by default.
- Index can be changed.

---

## 5. Adding data using `append()`

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

- New row must be a **Series**.
- `ignore_index=True` creates new index values.
- Returns a new DataFrame.

---

## 6. Data Exploration

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

#### `.shape`

- Returns number of rows and columns.

#### `.info()`

Shows:

- Number of rows
- Number of columns
- Column names
- Data types
- Non-null values
- Memory usage

#### `.corr()`

- Finds correlation between numerical columns.
- Correlation value ranges from **-1 to +1**.

---

## 7. Statistical Summary with `describe()`

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

- Understand data distribution
- Detect outliers
- Summarize numerical data

---

## 8. Dropping Columns and Rows

### Drop Column

#### Syntax

```python
df.drop(column_name, axis=1)
```

#### Code

```python
student = student.drop("Age", axis=1)
```

---

### Drop Row

#### Syntax

```python
df.drop(row_index, axis=0)
```

#### Code

```python
student = student.drop(2, axis=0)
```

### Meaning of `axis`

| Axis     | Meaning |
| -------- | ------- |
| `axis=0` | Rows    |
| `axis=1` | Columns |

---

## 9. Data Manipulation on Larger Datasets

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

- Understand dataset structure
- Find missing values
- Identify data types
- Analyze relationships
- Prepare data for Machine Learning

---

## 2. Pandas Objects

Pandas mainly provides **two data structures (objects)**:

1. **Series** – One-dimensional labeled array.
2. **DataFrame** – Two-dimensional table made up of multiple Series.

---

## 2. Pandas Series

### Definition

A **Series** is a one-dimensional labeled array that can store any data type.

### Features

- One-dimensional
- Has index labels
- More flexible than NumPy arrays
- Similar to a dictionary

---

### Creating a Series

#### Syntax

```python
pd.Series(data)
```

#### Code

```python
import pandas as pd

data = pd.Series([10, 20, 30, 40])
print(data)
```

---

### Accessing Series

```python
data[1]      # 20
data[1:3]    # 20,30
```

---

### Important Attributes

```python
data.values    # Returns values
data.index     # Returns index
```

---

### Creating Series with Custom Index

```python
data = pd.Series([10,20,30], index=["A","B","C"])
```

Access:

```python
data["B"]
```

---

### Creating Series from Dictionary

```python
population = pd.Series({
    "India":140,
    "USA":33,
    "Japan":12
})
```

---

### Ways to Create Series

#### From List

```python
pd.Series([2,4,6])
```

#### From Scalar

```python
pd.Series(5,index=[1,2,3])
```

#### From Dictionary

```python
pd.Series({1:"A",2:"B"})
```

### Key Points

- Stores one column of data.
- Every value has an index.
- Supports slicing and indexing.
- Can be created from list, scalar or dictionary.

---

## 3. Pandas DataFrame

### Definition

A **DataFrame** is a two-dimensional table consisting of multiple Series.

### Features

- Rows and columns
- Different data types
- Labeled axes
- Easy data manipulation

---

### Example

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

### Important Attributes

```python
states.index
states.columns
states["Area"]
```

---

### Ways to Create DataFrame

#### From Series

```python
pd.DataFrame(population)
```

#### From List of Dictionaries

```python
data=[{"A":1,"B":2},{"A":3,"B":4}]
pd.DataFrame(data)
```

#### From Dictionary

```python
pd.DataFrame({"Population":population,"Area":area})
```

#### From NumPy Array

```python
pd.DataFrame(np.random.rand(3,2),
columns=["A","B"])
```

### Key Points

- Collection of Series.
- Two-dimensional.
- Supports row and column indexing.
- Different columns can have different data types.

---

## 4. The Pandas Index Object

### Definition

Index is an immutable object used to label rows and columns.

---

### Creating Index

```python
import pandas as pd

ind = pd.Index([2,3,5,7,11])
```

---

### Accessing

```python
ind[1]
ind[::2]
```

---

### Index Attributes

```python
ind.size
ind.shape
ind.ndim
ind.dtype
```

---

### Immutability

```python
ind[1]=0
```

**Output**

```
TypeError
```

---

### Set Operations

```python
indA = pd.Index([1,3,5,7])
indB = pd.Index([3,5,7,9])

indA & indB    # Intersection
indA | indB    # Union
indA ^ indB    # Symmetric Difference
```

### Key Points

- Immutable (cannot be modified).
- Supports set operations.
- Similar to NumPy array but stores labels.

---

## 5. Data Indexing and Selection

### Definition

Data Indexing and Selection means accessing specific rows or columns from Series or DataFrame.

### Supports

- Indexing
- Slicing
- Masking
- Fancy Indexing

Like NumPy but with additional features.

---

## 6. Data Selection in Series

### Series as Dictionary

```python
data = pd.Series(
[10,20,30,40],
index=["a","b","c","d"]
)

data["b"]
```

---

### Dictionary Methods

```python
"a" in data

data.keys()

list(data.items())
```

---

### Adding New Value

```python
data["e"]=50
```

---

### Series as Array

#### Slicing

```python
data["a":"c"]
```

#### Position Slicing

```python
data[0:2]
```

#### Masking

```python
data[data>20]
```

#### Fancy Indexing

```python
data[["a","c"]]
```

### Note

- Label slicing includes last label.
- Position slicing excludes last position.

---

## 7. loc, iloc and ix

### loc

Uses **label (explicit index).**

#### Syntax

```python
data.loc[label]
```

Example

```python
data.loc[1]
data.loc[1:3]
```

---

### iloc

Uses **integer position (implicit index).**

#### Syntax

```python
data.iloc[position]
```

Example

```python
data.iloc[1]
data.iloc[1:3]
```

---

### ix

Hybrid of loc and iloc.

**Note:** Deprecated (not used in latest Pandas).

### Best Practice

- Use **loc** for labels.
- Use **iloc** for positions.

---

## 8. Data Selection in DataFrame

### Definition

A DataFrame behaves like:

- Dictionary of Series
- Two-dimensional array

---

### Accessing Columns

#### Dictionary Style

```python
data["area"]
```

#### Attribute Style

```python
data.area
```

---

### Adding New Column

```python
data["density"] = data["pop"]/data["area"]
```

---

### Raw Values

```python
data.values
```

---

### Transpose

```python
data.T
```

---

### iloc

```python
data.iloc[:3,:2]
```

---

### loc

```python
data.loc[:,"area":"pop"]
```

---

### Masking

```python
data.loc[data.density>100,
["pop","density"]]
```

---

### Modify Value

```python
data.iloc[0,2]=90
```

### Key Points

- `[]` selects columns.
- `loc` uses labels.
- `iloc` uses positions.
- Supports adding and modifying columns.

---

## 9. Additional Indexing Conventions

### Row Label Slicing

```python
data["Florida":"Illinois"]
```

---

### Row Position Slicing

```python
data[1:3]
```

---

### Row Masking

```python
data[data.density>100]
```

### Key Points

- Row slicing by labels includes end label.
- Position slicing excludes last position.
- Boolean masking filters rows satisfying a condition.

---

## **Difference: loc vs iloc**

| loc                           | iloc                   |
| ----------------------------- | ---------------------- |
| Uses labels                   | Uses integer positions |
| End label included in slicing | End position excluded  |
| Example: `df.loc["A"]`        | Example: `df.iloc[0]`  |
| Explicit indexing             | Implicit indexing      |

---

## **Difference: Series vs DataFrame**

| Series                      | DataFrame                      |
| --------------------------- | ------------------------------ |
| One-dimensional             | Two-dimensional                |
| Single column               | Multiple columns               |
| Has one index               | Has row and column indexes     |
| Created using `pd.Series()` | Created using `pd.DataFrame()` |

---

## 3. Pandas Data Operations

### Definition

Pandas Data Operations are used to manipulate, analyze, clean, and transform data.

### Operations

- Row & Column Selection
- Filtering
- Handling Missing Values
- String Operations
- Counting Values
- Plotting
- Universal Functions (UFuncs)
- DataFrame & Series Operations

---

## 2. Row and Column Selection

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

## 3. Filtering Data

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

## 4. String Operations

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

## 5. Count Values

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

## 6. Plotting with Pandas

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

## 7. Universal Functions (UFuncs)

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

## 8. Operations Between DataFrame and Series

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

## 9. Element-wise Operation vs Broadcasting

### Element-wise

```python
a = np.array([1,2,3])
b = np.array([4,5,6])

a+b
```

### Broadcasting

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

## 10. Handling Missing Data

### Definition

Missing data means values are unavailable.

### Represented By

- None
- NaN

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

### Checking Missing Values

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

### Filling Missing Values

#### Replace with 0

```python
df.fillna(0)
```

#### Replace with Mean

```python
df["Salary"].fillna(df["Salary"].mean())
```

#### Replace with Median

```python
df["Salary"].fillna(df["Salary"].median())
```

#### Replace Specific Value

```python
df.replace(np.nan,100)
```

#### Interpolation

```python
df.interpolate()
```

---

### Dropping Missing Values

#### Drop Rows

```python
df.dropna()
```

#### Drop Columns

```python
df.dropna(axis=1)
```

#### Drop Rows with All NaN

```python
df.dropna(how="all")
```

#### Drop Rows Having Less Than Required Values

```python
df.dropna(thresh=3)
```

---

### None vs NaN

| None                  | NaN                    |
| --------------------- | ---------------------- |
| Python object         | Floating-point value   |
| Used with object type | Used with numeric data |
| Slower                | Faster                 |

### Key Points

- `isnull()` and `isna()` are equivalent.
- `fillna()` replaces missing values.
- `dropna()` removes missing values.
- `interpolate()` estimates missing values.
- Prefer **NaN** for numerical computations.

---

## 4. What is Hierarchical Indexing (MultiIndex)?

### Definition

Hierarchical Indexing (MultiIndex) allows a Series or DataFrame to have **multiple levels of indexing**.

### Uses

- Represents higher-dimensional data.
- Groups related data.
- Makes filtering and reshaping easier.

### Key Points

- Series → 1D
- DataFrame → 2D
- MultiIndex → 3D+ like structure
- Can be created for both rows and columns.

---

## 2. Creating a Hierarchical Index

### Syntax

```python
pd.MultiIndex.from_tuples()
```

### Code

```python
import pandas as pd

index = pd.MultiIndex.from_tuples(
[
    ('2023','Jan'),
    ('2023','Feb'),
    ('2024','Jan')
],
names=['Year','Month']
)

df = pd.DataFrame(
{
    'Sales':[1000,1200,1300],
    'Profit':[200,250,300]
},
index=index
)

print(df)
```

### Output

```
             Sales  Profit
Year Month
2023 Jan      1000     200
     Feb      1200     250
2024 Jan      1300     300
```

---

## 3. Selecting Data in Hierarchical Index

### Select all data of 2023

```python
print(df.loc['2023'])
```

### Output

```
       Sales  Profit
Month
Jan     1000    200
Feb     1200    250
```

---

### Select January 2023

```python
print(df.loc[('2023','Jan')])
```

### Output

```
Sales     1000
Profit     200
Name: (2023, Jan)
```

---

### Select Profit Column

```python
print(df["Profit"])
```

### Output

```
Year  Month
2023  Jan      200
      Feb      250
2024  Jan      300
```

---

## 4. Swap Levels (swaplevel)

### Definition

Interchanges two index levels.

### Syntax

```python
df.swaplevel(level1,level2)
```

### Code

```python
print(df.swaplevel(0,1))
```

### Output

```
             Sales  Profit
Month Year
Jan   2023    1000    200
Feb   2023    1200    250
Jan   2024    1300    300
```

### Key Point

- Does not modify the original DataFrame unless assigned.

---

## 5. Sorting Hierarchical Index

### Definition

Sorts MultiIndex based on a level.

### Syntax

```python
df.sort_index(level=n)
```

### Code

```python
print(df.sort_index(level=1))
```

### Output

```
             Sales Profit
Year Month
2023 Jan     1000   200
2024 Jan     1300   300
2023 Feb     1200   250
```

---

## 6. Summary Statistics by Index Level

### Syntax

```python
df.sum(level="Month")
```

### Code

```python
print(df.sum(level="Month"))
```

### Output

```
       Sales  Profit
Month
Jan     2300     500
Feb     1200     250
```

### Another Example

```python
print(df.sum(level="Year"))
```

### Output

```
      Sales Profit
Year
2023  2200   450
2024  1300   300
```

---

## 7. set_index()

### Definition

Moves one or more columns to the row index.

### Syntax

```python
df.set_index(columns)
```

### Code

```python
import pandas as pd

df = pd.DataFrame({
'A':[1,2,3],
'B':[4,5,6],
'C':[7,8,9]
})

print(df.set_index(['A','B']))
```

### Output

```
      C
A B
1 4   7
2 5   8
3 6   9
```

### Keep Columns

```python
print(df.set_index(['A','B'],drop=False))
```

---

## 8. reset_index()

### Definition

Moves index back into normal columns.

### Syntax

```python
df.reset_index()
```

### Code

```python
print(df.reset_index())
```

### Output

```
   index  A  B  C
0      0  1  4  7
1      1  2  5  8
2      2  3  6  9
```

---

## 9. MultiIndex in Columns

### Syntax

```python
pd.MultiIndex.from_arrays()
```

### Code

```python
import pandas as pd

arrays = [
['Sales','Sales','Profit','Profit'],
['Q1','Q2','Q1','Q2']
]

column_index = pd.MultiIndex.from_arrays(
arrays,
names=['Metric','Quarter']
)

data = [
[100,150,20,25],
[200,250,30,35]
]

df = pd.DataFrame(
data,
columns=column_index
)

print(df)
```

### Output

```
Metric   Sales      Profit
Quarter    Q1  Q2     Q1  Q2
0         100 150     20  25
1         200 250     30  35
```

---

## 10. stack()

### Definition

Moves column level to row index.

### Syntax

```python
df.stack()
```

### Code

```python
print(df.stack())
```

### Output

```
             Sales Profit
  Quarter
0 Q1        100    20
  Q2        150    25
1 Q1        200    30
  Q2        250    35
```

---

## 11. unstack()

### Definition

Moves row index level to columns.

### Syntax

```python
df.unstack(level="Month")
```

### Code

```python
print(df.unstack(level="Month"))
```

---

## 12. reset_index() on MultiIndex

### Code

```python
print(df.reset_index())
```

### Output

MultiIndex is converted into normal columns.

---

## Difference

| Method          | Purpose                  |
| --------------- | ------------------------ |
| `from_tuples()` | Create row MultiIndex    |
| `from_arrays()` | Create column MultiIndex |
| `loc[]`         | Select hierarchical data |
| `swaplevel()`   | Exchange index levels    |
| `sort_index()`  | Sort MultiIndex          |
| `set_index()`   | Columns → Index          |
| `reset_index()` | Index → Columns          |
| `stack()`       | Columns → Rows           |
| `unstack()`     | Rows → Columns           |

---

## 6. Combining Datasets with `append()` in Pandas

### Definition

`append()` is used to add rows from one DataFrame or Series to another.

> **Note:** `append()` is deprecated in newer Pandas versions. `pd.concat()` is recommended.

### Syntax

```python
df1.append(df2, ignore_index=True)
```

---

## 2. Appending DataFrames

### Code

```python
import pandas as pd

df1 = pd.DataFrame({
    "Name":["Hari","John"],
    "Age":[20,22]
})

df2 = pd.DataFrame({
    "Name":["David","Sam"],
    "Age":[25,21]
})

result = df1.append(df2, ignore_index=True)

print(result)
```

### Output

```
    Name  Age
0   Hari   20
1   John   22
2  David   25
3    Sam   21
```

---

## 3. Appending with Different Columns

### Code

```python
import pandas as pd

df1 = pd.DataFrame({
    "Name":["Hari","John"],
    "Age":[20,22]
})

df2 = pd.DataFrame({
    "Name":["David"],
    "Salary":[50000]
})

result = df1.append(df2, ignore_index=True)

print(result)
```

### Output

```
    Name   Age   Salary
0   Hari  20.0      NaN
1   John  22.0      NaN
2  David   NaN  50000.0
```

### Key Points

- Missing columns are filled with **NaN**.
- Column names are automatically aligned.

---

## 4. Appending a Series

### Code

```python
import pandas as pd

df = pd.DataFrame({
    "Name":["Hari","John"],
    "Age":[20,22]
})

new_row = pd.Series(
["David",25],
index=["Name","Age"]
)

df = df.append(new_row, ignore_index=True)

print(df)
```

### Output

```
    Name  Age
0   Hari   20
1   John   22
2  David   25
```

### Key Points

- Series index must match DataFrame columns.
- `ignore_index=True` creates a new index.

---

## 5. Combining Datasets: `merge()` and `join()`

### merge()

#### Definition

Combines DataFrames based on one or more common columns.

#### Syntax

```python
pd.merge(df1, df2, on="column")
```

#### Code

```python
import pandas as pd

student = pd.DataFrame({
    "ID":[1,2,3],
    "Name":["Hari","John","Sam"]
})

marks = pd.DataFrame({
    "ID":[1,2,3],
    "Marks":[90,85,88]
})

result = pd.merge(student, marks, on="ID")

print(result)
```

#### Output

```
   ID  Name  Marks
0   1  Hari     90
1   2  John     85
2   3   Sam     88
```

---

### Types of Merge

#### Inner Merge

```python
pd.merge(df1, df2, how="inner")
```

Returns common rows.

---

#### Left Merge

```python
pd.merge(df1, df2, how="left")
```

Returns all rows from left DataFrame.

---

#### Right Merge

```python
pd.merge(df1, df2, how="right")
```

Returns all rows from right DataFrame.

---

#### Outer Merge

```python
pd.merge(df1, df2, how="outer")
```

Returns all rows from both DataFrames.

---

### join()

#### Definition

Joins DataFrames using their indexes.

#### Syntax

```python
df1.join(df2)
```

#### Code

```python
import pandas as pd

df1 = pd.DataFrame({
    "Name":["Hari","John"]
})

df2 = pd.DataFrame({
    "Marks":[90,85]
})

print(df1.join(df2))
```

#### Output

```
   Name  Marks
0  Hari     90
1  John     85
```

### Difference

| merge()             | join()                |
| ------------------- | --------------------- |
| Joins using columns | Joins using index     |
| More flexible       | Simpler               |
| Uses `on=`          | Uses index by default |

---

## 6. Aggregation and Grouping

### Definition

Grouping divides data into groups and performs calculations on each group.

### Syntax

```python
df.groupby("column")
```

### Code

```python
import pandas as pd

df = pd.DataFrame({
    "Department":["IT","IT","HR","HR"],
    "Salary":[50000,60000,40000,45000]
})

print(df.groupby("Department").sum())
```

### Output

```
            Salary
Department
HR           85000
IT          110000
```

---

### Common Aggregate Functions

```python
df.groupby("Department").mean()
```

```python
df.groupby("Department").max()
```

```python
df.groupby("Department").min()
```

```python
df.groupby("Department").count()
```

```python
df.groupby("Department").sum()
```

```python
df.groupby("Department").std()
```

### Key Points

- `groupby()` splits data into groups.
- Aggregate functions perform calculations on each group.

---

## 7. Pivot Tables

### Definition

A Pivot Table summarizes data by grouping rows and columns.

### Syntax

```python
pd.pivot_table()
```

### Code

```python
import pandas as pd

df = pd.DataFrame({
    "Department":["IT","IT","HR","HR"],
    "Gender":["M","F","M","F"],
    "Salary":[50000,60000,40000,45000]
})

table = pd.pivot_table(
    df,
    values="Salary",
    index="Department",
    columns="Gender",
    aggfunc="mean"
)

print(table)
```

### Output

```
Gender            F        M
Department
HR          45000.0  40000.0
IT          60000.0  50000.0
```

### Key Points

- Summarizes data.
- Similar to Excel Pivot Table.
- Supports multiple aggregation functions.

---

## 8. Aggregating Multiple Functions with `aggfunc`

### Code

```python
import pandas as pd

table = pd.pivot_table(
    df,
    values="Salary",
    index="Department",
    aggfunc=["sum","mean","max","min","count"]
)

print(table)
```

### Output

```
               sum     mean     max     min   count
            Salary   Salary  Salary  Salary Salary
Department
HR           85000  42500.0   45000   40000      2
IT          110000  55000.0   60000   50000      2
```

### Common `aggfunc`

| Function | Purpose            |
| -------- | ------------------ |
| sum      | Total              |
| mean     | Average            |
| min      | Minimum            |
| max      | Maximum            |
| count    | Count              |
| std      | Standard Deviation |

---

## 9. Multi-Index in Pivot Table

### Definition

A Pivot Table can have multiple row or column indexes.

### Code

```python
import pandas as pd

df = pd.DataFrame({
    "Department":["IT","IT","HR","HR"],
    "Gender":["M","F","M","F"],
    "Year":[2023,2024,2023,2024],
    "Salary":[50000,60000,40000,45000]
})

table = pd.pivot_table(
    df,
    values="Salary",
    index=["Department","Year"],
    aggfunc="sum"
)

print(table)
```

### Output

```
                  Salary
Department Year
HR         2023    40000
           2024    45000
IT         2023    50000
           2024    60000
```

### Multiple Columns

```python
table = pd.pivot_table(
    df,
    values="Salary",
    index="Department",
    columns=["Gender","Year"],
    aggfunc="sum"
)

print(table)
```

### Key Points

- Multiple columns can be used in `index`.
- Multiple columns can be used in `columns`.
- Produces a MultiIndex Pivot Table.

---

## Vectorized String Operations in Pandas

### Definition

Vectorized String Operations allow string manipulation on an entire **Series** or **DataFrame column** at once using the **`.str`** accessor.

### Advantages

- Faster than loops.
- Works on the whole column.
- Automatically ignores **NaN** values.
- Easy to clean and process text data.

### Syntax

```python
Series.str.method()
```

---

## Creating a String Series

```python
import pandas as pd

data = pd.Series([
    "Hari Krishnan",
    "Python",
    "PANDAS",
    "Data Science",
    None
])

print(data)
```

---

## 1. Convert to Lowercase

### Syntax

```python
Series.str.lower()
```

### Code

```python
print(data.str.lower())
```

### Output

```
0    hari krishnan
1    python
2    pandas
3    data science
4    None
```

---

## 2. Convert to Uppercase

### Syntax

```python
Series.str.upper()
```

### Code

```python
print(data.str.upper())
```

### Output

```
0    HARI KRISHNAN
1    PYTHON
2    PANDAS
3    DATA SCIENCE
4    None
```

---

## 3. Convert First Letter to Capital

### Syntax

```python
Series.str.title()
```

### Code

```python
print(data.str.title())
```

---

## 4. Capitalize First Letter

### Syntax

```python
Series.str.capitalize()
```

### Code

```python
print(data.str.capitalize())
```

---

## 5. Remove Spaces

### Syntax

```python
Series.str.strip()
```

### Code

```python
data = pd.Series([" Hari "," Python "," Pandas "])

print(data.str.strip())
```

---

## 6. Remove Left Spaces

### Syntax

```python
Series.str.lstrip()
```

### Code

```python
print(data.str.lstrip())
```

---

## 7. Remove Right Spaces

### Syntax

```python
Series.str.rstrip()
```

### Code

```python
print(data.str.rstrip())
```

---

## 8. Find String Length

### Syntax

```python
Series.str.len()
```

### Code

```python
print(data.str.len())
```

---

## 9. Split String

### Syntax

```python
Series.str.split()
```

### Code

```python
print(data.str.split())
```

---

## 10. Join Strings

### Syntax

```python
Series.str.cat()
```

### Code

```python
print(data.str.cat(sep=", "))
```

---

## 11. Replace Text

### Syntax

```python
Series.str.replace(old,new)
```

### Code

```python
print(data.str.replace("Python","Java"))
```

---

## 12. Check Substring

### Syntax

```python
Series.str.contains()
```

### Code

```python
print(data.str.contains("Data"))
```

---

## 13. Starts With

### Syntax

```python
Series.str.startswith()
```

### Code

```python
print(data.str.startswith("P"))
```

---

## 14. Ends With

### Syntax

```python
Series.str.endswith()
```

### Code

```python
print(data.str.endswith("n"))
```

---

## 15. Find Position

### Syntax

```python
Series.str.find()
```

### Code

```python
print(data.str.find("a"))
```

---

## 16. Count Occurrences

### Syntax

```python
Series.str.count()
```

### Code

```python
print(data.str.count("a"))
```

---

## 17. Repeat String

### Syntax

```python
Series.str.repeat()
```

### Code

```python
print(data.str.repeat(2))
```

---

## 18. Check Lowercase

### Syntax

```python
Series.str.islower()
```

### Code

```python
print(data.str.islower())
```

---

## 19. Check Uppercase

### Syntax

```python
Series.str.isupper()
```

### Code

```python
print(data.str.isupper())
```

---

## 20. Check Numeric

### Syntax

```python
Series.str.isnumeric()
```

### Code

```python
num = pd.Series(["123","ABC","456"])

print(num.str.isnumeric())
```

---

## 21. Find All Matches

### Syntax

```python
Series.str.findall()
```

### Code

```python
print(data.str.findall("a"))
```

---

## 22. Extract Pattern

### Syntax

```python
Series.str.extract()
```

### Code

```python
email = pd.Series([
    "hari@gmail.com",
    "john@yahoo.com"
])

print(email.str.extract(r'(.+)@(.+)'))
```

---

## 23. Slice Strings

### Syntax

```python
Series.str.slice(start,stop)
```

### Code

```python
print(data.str.slice(0,4))
```

---

## 24. Swap Upper and Lower Case

### Syntax

```python
Series.str.swapcase()
```

### Code

```python
print(data.str.swapcase())
```

---

## Common Vectorized String Methods

| Method         | Purpose                     |
| -------------- | --------------------------- |
| `lower()`      | Convert to lowercase        |
| `upper()`      | Convert to uppercase        |
| `title()`      | Title case                  |
| `capitalize()` | First letter capital        |
| `strip()`      | Remove spaces               |
| `lstrip()`     | Remove left spaces          |
| `rstrip()`     | Remove right spaces         |
| `split()`      | Split string                |
| `cat()`        | Join strings                |
| `replace()`    | Replace text                |
| `contains()`   | Check substring             |
| `startswith()` | Check starting text         |
| `endswith()`   | Check ending text           |
| `find()`       | Find position               |
| `count()`      | Count occurrences           |
| `repeat()`     | Repeat string               |
| `len()`        | String length               |
| `islower()`    | Check lowercase             |
| `isupper()`    | Check uppercase             |
| `isnumeric()`  | Check numeric               |
| `findall()`    | Find all matches            |
| `extract()`    | Extract pattern using regex |
| `slice()`      | Slice string                |
| `swapcase()`   | Swap upper/lower case       |

### Key Points

- Use **`.str`** before every string method.
- Vectorized operations work on the **entire Series**.
- Handles **NaN** values automatically.
- Faster than using loops.
- Mainly used for **cleaning and preprocessing text data**.