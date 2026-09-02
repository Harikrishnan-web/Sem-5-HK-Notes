# Relationship Between Two Variables in Bivariate Analysis

Bivariate analysis studies the **relationship between two variables**. It helps determine whether the variables are related, how strongly they are related, and whether one variable changes with another.

### Methods for Analyzing Relationships Between Two Variables

* **Scatterplot** – shows the relationship between two numerical variables.
* **Correlation** – measures the strength and direction of a relationship. Pearson’s correlation coefficient **r** ranges from **−1 to +1**.
* **Regression** – describes or predicts the relationship between variables using an equation such as **y = mx + c**.
* **Crosstabulation / Chi-square** – analyzes relationships between categorical variables.
* **T-test** – compares means between groups.
* **ANOVA** – compares means of three or more groups.
* **Percentage tables** – show frequencies and percentages for categorical variables.

# Percentage Tables in Bivariate Analysis

Percentage tables help compare the distribution of one categorical variable across another.

### Types

1. **Frequency table** – shows the actual count in each category.
2. **Row percentage table** – percentages are calculated within each row; each row totals **100%**.
3. **Column percentage table** – percentages are calculated within each column; each column totals **100%**.
4. **Overall percentage table** – percentages are calculated from the total observations; the entire table totals **100%**.

### Example

Given:

| Gender | Product Preference |
| ------ | ------------------ |
| Male   | Product A          |
| Female | Product B          |
| Male   | Product A          |
| Female | Product C          |
| Male   | Product B          |
| Female | Product A          |
| Male   | Product C          |
| Female | Product B          |

### Frequency Table

| Gender | Product A | Product B | Product C |
| ------ | --------: | --------: | --------: |
| Male   |         2 |         1 |         1 |
| Female |         1 |         2 |         1 |

### Python Implementation

```python
import pandas as pd

data = {
    'Gender': ['Male', 'Female', 'Male', 'Female',
               'Male', 'Female', 'Male', 'Female'],
    'Product Preference': [
        'Product A', 'Product B', 'Product A', 'Product C',
        'Product B', 'Product A', 'Product C', 'Product B'
    ]
}

df = pd.DataFrame(data)

# Frequency table
frequency_table = pd.crosstab(
    df['Gender'], df['Product Preference']
)
print("Frequency Table:\n", frequency_table)

# Row percentage
row_percentage_table = (
    frequency_table.div(frequency_table.sum(axis=1), axis=0) * 100
)
print("Row Percentage Table:\n", row_percentage_table)

# Column percentage
column_percentage_table = (
    frequency_table.div(frequency_table.sum(axis=0), axis=1) * 100
)
print("Column Percentage Table:\n", column_percentage_table)

# Overall percentage
overall_percentage_table = (
    frequency_table / frequency_table.values.sum() * 100
)
print("Overall Percentage Table:\n", overall_percentage_table)
```

### Example Problem

**Question:** For the given Gender × Product Preference data, find the **row percentage** for Male choosing Product A.

Male total = 2 + 1 + 1 = **4**

Product A among males = **2**

$$
\text{Row Percentage}=\frac{2}{4}\times100=\boxed{50\%}
$$

**Answer: 50%**
---
# Analysis and Insights

### Frequency Table Analysis

* **Male:** Product A is most preferred (2/4); Products B and C have equal preference (1 each).
* **Female:** Product B is most preferred (2/4); Products A and C have 1 each.

### Row Percentage Analysis

* **Male:** A = 50%, B = 25%, C = 25%.
* **Female:** B = 50%, A = 25%, C = 25%. 

### Column Percentage Analysis

* **Product A:** Male = 66.67%, Female = 33.33%.
* **Product B:** Male = 33.33%, Female = 66.67%.
* **Product C:** Male = 50%, Female = 50%.

### Overall Percentage Analysis

* Male–A = 25%, Female–A = 12.5%.
* Male–B = 12.5%, Female–B = 25%.
* Male–C = 12.5%, Female–C = 12.5%. 

### Insights

* Males show greater preference for **Product A**.
* Females show greater preference for **Product B**.
* **Product C** is equally preferred by both genders.
* Marketing can target **Product A toward males** and **Product B toward females**. 

# Contingency Tables

* A **contingency table (crosstab)** is a matrix showing frequency distributions for two categorical variables.
* **Rows:** Categories of one variable.
* **Columns:** Categories of another variable.
* **Cells:** Frequency/count for each combination. 

### Creating a Contingency Table in SPSS

1. **File → Open → Data** → load the dataset.
2. **Analyze → Descriptive Statistics → Crosstabs**.
3. Put the required variables into **Rows** and **Columns**.
4. Under **Statistics**, select **Chi-square, Phi, Cramer's V** if required.
5. Under **Cells**, select **Observed, Expected, Row %, Column %** as needed.
6. Click **OK**.
7. Examine and interpret the output. 

### Example: Gender × Preference

| Gender    |      Yes |       No |   Total |
| --------- | -------: | -------: | ------: |
| Male      | 30 (60%) | 20 (40%) |      50 |
| Female    | 25 (50%) | 25 (50%) |      50 |
| **Total** |   **55** |   **45** | **100** |

### Analyzing a Contingency Table

1. **Observed frequencies:** Check the actual cell counts.
2. **Row/column percentages:** Examine distribution across categories.
3. **Chi-square test:** Tests whether the two categorical variables are significantly associated.
4. Compare **observed and expected frequencies**.
5. Check the **Pearson Chi-square and p-value**. 

**Given example:**

* Pearson Chi-square = **1.667**
* df = **1**
* p = **0.197**
* Since **p > 0.05**, there is **no significant association between Gender and Preference**. 

### Additional Measures

* **Phi and Cramer's V:** Measures of nominal association.

  * Near **0** → little/no association.
  * Near **1** → strong association.
* **Standardized residuals:** Values above **+1.96** or below **−1.96** are significant at the 0.05 level. 

# Handling Several Batches

Used when the same analysis must be performed on different data subsets or datasets.

### SPLIT FILE

1. Load the data.
2. **Data → Split File**.
3. Select **Compare groups** or **Organize output by groups**.
4. Place the batch/group variable in **Groups Based on**.
5. Run the analysis → separate results are produced for each group. 

### DO REPEAT

Repeats the same SPSS command for multiple variables.

```spss
DO REPEAT var = var1 var2 var3.
FREQUENCIES VARIABLES=var.
END REPEAT.
```

### Python Looping

* Enable Python through **Edit → Options → File Locations**.
* Use a loop to load each dataset and run the same analysis.

```python
BEGIN PROGRAM Python.
import spss, spssaux
datasets = ['data1.sav', 'data2.sav', 'data3.sav']

for dataset in datasets:
    spss.Submit(f'GET FILE="{dataset}".')
    spss.Submit('CROSSTABS /TABLES=var1 BY var2 /STATISTICS=CHISQ.')
END PROGRAM.
```

### Macros

SPSS macros automate repetitive analyses by accepting dataset and variable names.

```spss
DEFINE !runAnalysis
(dataset = !TOKENS(1) /var1 = !TOKENS(1) /var2 = !TOKENS(1))
GET FILE=!dataset.
CROSSTABS /TABLES=!var1 BY !var2 /STATISTICS=CHISQ.
!ENDDEFINE.
```

**Multiple-batch example:** For `batch1.sav`, `batch2.sav`, `batch3.sav`, combine them with a **Batch** variable, split by Batch, and run the crosstab. Alternatively, use Python to load each file and run `CROSSTABS`. 

# Scatterplots and Resistant Lines in EDA

### Scatterplot

* Graph showing the relationship between **two numerical variables**.
* Each point represents one observation.
* **X-axis:** independent variable.
* **Y-axis:** dependent variable.
* Used to identify **trends, patterns, correlations and outliers**. 

**Example:** Study hours vs test scores. Increasing study hours with increasing scores indicates a **positive correlation**. 

```python
import matplotlib.pyplot as plt

study_hours = [1,2,3,4,5,6,7,8,9,10]
test_scores = [50,55,60,65,70,75,80,85,90,95]

plt.scatter(study_hours, test_scores)
plt.title('Scatterplot of Study Hours vs Test Scores')
plt.xlabel('Study Hours')
plt.ylabel('Test Scores')
plt.show()
```

### Resistant Lines

* Also called **robust regression lines**.
* Less affected by outliers than ordinary least-squares lines.
* Better represent the **central trend** when extreme values, anomalies or non-normal data exist.
* Suitable for datasets containing outliers. 

```python
import numpy as np
import statsmodels.api as sm

x = np.array(study_hours)
y = np.array(test_scores)
x = sm.add_constant(x)

model = sm.RLM(y, x, M=sm.robust_norms.HuberT())
results = model.fit()
predicted = results.predict(x)

plt.scatter(study_hours, test_scores)
plt.plot(study_hours, predicted, label='Resistant Line')
plt.show()
```

### Importance in EDA

* **Scatterplots:** visual exploration, pattern recognition and outlier detection.
* **Resistant lines:** robust trend analysis and reduced influence of outliers. 

# Stratified Analysis

* Divides data into **subgroups/strata** based on categorical variables before analysis.
* Used to determine whether relationships differ between groups or whether a **confounding variable** affects the outcome.
* **Controls confounding** and reduces bias.
* **Explores heterogeneity** between groups.
* Improves accuracy within homogeneous subgroups.
* **Example:** A drug trial can divide patients into `<40`, `40–60`, and `>60` age groups to check whether drug effectiveness differs. 

# Regression and Trend Analysis

### Regression Analysis

* Models the relationship between a **dependent variable** and one or more **independent variables**.
* **Simple Linear Regression:**

  $$
  y=\beta_0+\beta_1x+\epsilon
  $$

  * `y` = dependent variable
  * `x` = independent variable
  * `β₀` = intercept
  * `β₁` = slope
  * `ε` = error term
* **Multiple Linear Regression:** uses multiple predictors.
* **Logistic Regression:** models probabilities when the outcome is categorical. 

### Trend Analysis

* Studies patterns or directions **over time**, mainly in time-series data.
* Identifies **increasing, decreasing or cyclic trends**.
* Can use regression/linear trend lines.
* Applications: **sales forecasting, stock-price prediction and weather analysis**. 

# Bivariate Data Visualization

### Pairplot

* Displays scatterplots for **every pair of variables**.
* Diagonal shows individual distributions using histograms/KDE.
* Helps identify **relationships, multicollinearity and clustering**.
* Useful for datasets with multiple features. 

### Joint Plot

* Shows the relationship between **two variables**.
* Combines a scatter/regression plot with **marginal histograms/KDEs**.
* Shows both their interaction and individual distributions. 

### Heatmap

* A **color-coded matrix** used to visualize values such as correlations.
* Correlation heatmaps show the strength and direction of relationships.
* Helps identify correlated features and their effect on **feature selection and model performance**. 

# Feature Selection and Importance Scores

### Feature Selection

Selecting a subset of relevant features for predictive modelling.

**Benefits:**

* Improves accuracy by reducing noise.
* Reduces computational cost and time.
* Reduces overfitting by removing irrelevant/redundant features.

**Methods:**

* **Filter:** statistical tests such as Chi-square and ANOVA F-test.
* **Wrapper:** uses a predictive model to evaluate feature subsets, e.g. **Recursive Feature Elimination**.
* **Embedded:** selection occurs during training, e.g. **Lasso with L1 regularization**, which can shrink coefficients to zero. 

### Feature Importance Scores

* **Random Forests** and **Gradient Boosting** can assign importance scores to features.
* High importance means the feature strongly affects predictions.
* Used for **model interpretation** and further feature selection. 
---
# Introduction to Hypothesis Testing

Hypothesis testing is a statistical method used to decide whether sample evidence is sufficient to reject **H₀ (null hypothesis)** in favour of **H₁ (alternative hypothesis)**.

### Steps

1. **State hypotheses**

   * **H₀:** No effect / no difference.
   * **H₁:** Effect / difference exists.
2. **Select significance level (α):** Usually **0.05**.
3. **Collect data and calculate test statistic** such as **t** or **F**.
4. **Find p-value:** Probability of obtaining the observed result assuming H₀ is true.
5. **Decision**

   * **p < α → Reject H₀**
   * **p ≥ α → Fail to reject H₀**

# T-Test

A t-test checks whether the **means differ significantly**.

### Types

* **One-sample:** Sample mean compared with a known value.
* **Independent two-sample:** Means of two independent groups compared.
* **Paired:** Means of two related measurements compared, e.g., before and after treatment.

### Example

**Question:** Does the average exam score differ between two classrooms?

Set:

* **H₀:** Mean scores of the two classrooms are equal.
* **H₁:** Mean scores are different.
* Choose **α = 0.05**.
* Calculate the appropriate **t-statistic and p-value**.
* If **p < 0.05**, reject H₀ and conclude that the classroom means differ significantly.

### Formulas

**One-sample:**

$$
t=\frac{\bar{x}-\mu}{s/\sqrt{n}}
$$

**Independent two-sample:**

$$
t=\frac{\bar{x}_1-\bar{x}_2}{SE(\bar{x}_1-\bar{x}_2)}
$$

**Paired sample:**

$$
t=\frac{\bar{d}}{s_d/\sqrt{n}}
$$

# ANOVA (Analysis of Variance)

ANOVA tests whether the means of **three or more groups** differ significantly.

It compares:

* **Between-group variability:** Variation among group means.
* **Within-group variability:** Variation inside each group.

### Key Points

* Produces an **F-statistic**.
* **Large F + small p-value → significant difference**.
* **One-way ANOVA:** One factor.
* **Two-way ANOVA:** Two factors and their interaction.

### Example

**Question:** Do average test scores differ among three schools?

| School | Mean Score |
| ------ | ---------: |
| A      |         65 |
| B      |         72 |
| C      |         80 |

* **H₀:** All school means are equal.
* **H₁:** At least one mean differs.
* Calculate **SST, SSB and SSW**, followed by mean squares and **F**.
* If **F > F-critical** at **α = 0.05**, reject H₀.
* Conclusion: **At least one school has a significantly different mean.** 

### One-Way ANOVA Calculations

For **k groups** and **N total observations**:

**1. Total Sum of Squares (SST)**
Measures total variation of observations around the grand mean.

**2. Between-Group Sum of Squares (SSB/SSA)**
Measures variation of group means around the grand mean.

**3. Within-Group Sum of Squares (SSW/SSE)**
Measures variation within the groups.

**4. Mean Squares**

$$
MSB=\frac{SSB}{k-1}
$$

**5. F-statistic**

$$
F=\frac{MSB}{MSW}
$$

**Decision:**

$$
F>F_{critical}\Rightarrow \text{Reject }H_0
$$

Therefore, **at least one group mean is significantly different**. 

### Source-Based Table Example: Hypothesis Test

A contingency-table example in the source demonstrates hypothesis testing using **Gender and Preference**:

| Gender    | Preference: Yes | Preference: No |   Total |
| --------- | --------------: | -------------: | ------: |
| Male      |        30 (60%) |       20 (40%) |      50 |
| Female    |        25 (50%) |       25 (50%) |      50 |
| **Total** |          **55** |         **45** | **100** |

**Chi-square test result:**

| Test               | Value | df | p-value |
| ------------------ | ----: | -: | ------: |
| Pearson Chi-square | 1.667 |  1 |   0.197 |

Since **0.197 > 0.05**, **fail to reject H₀**. Thus, the source concludes that there is **no significant association between Gender and Preference**. 
---