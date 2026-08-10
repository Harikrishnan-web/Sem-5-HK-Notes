# Unit-3 Bussiness Forecasting
# 3.1 Introduction to Business Forecasting

Business forecasting helps managers **predict future values/events for decision-making**.

The appropriate forecasting method depends mainly on:

* **Forecast time horizon**
* **Information available**
* Characteristics of the variable being forecast

### Major Forecasting Approaches

```text
Business Forecasting
       │
       ├── Qualitative / Judgmental
       ├── Statistical Time-Series
       └── Explanatory / Causal
```

## 3.1.1 Qualitative and Judgmental Forecasting

Used when:

* Historical data is **not available**
* Forecasting **far into the future**
* Expert knowledge or intuition is important
* Nonquantitative factors must be considered, such as **government regulations or competitor actions**

### Delphi Method

A structured method using a **panel of experts** whose identities are usually kept confidential.

```text
Experts
   ↓
Questionnaire
   ↓
Anonymous responses
   ↓
Opinions shared
   ↓
Experts revise estimates
   ↓
Usually 2–3 rounds
   ↓
Convergence of opinion
```

**Best suited for:** long-range trends and impacts.

## Indicators and Indexes

**Indicators** are measures believed to influence the variable being forecast.

By monitoring changes in indicators, analysts gain clues about the variable's future behavior.

### Example: Leading Economic Indicators

Important indicators include:

* Manufacturing weekly hours
* Initial unemployment claims
* New orders
* Building permits
* Stock prices
* Money supply
* Interest-rate spread
* Consumer expectations

**Core idea:** Changes in an indicator can provide an early signal about future economic performance.

## 3.1.2 Statistical Forecasting Models

Statistical time-series models are especially useful for **short-range forecasting**.

### Time Series

A **time series** is a sequence of historical observations recorded over time.

```text
Week 1 → Week 2 → Week 3 → Week 4 → ...
 Sales     Sales     Sales     Sales
```

Time-series forecasting assumes that forces affecting the recent past will continue into the near future.

### Main Components

```text
Time Series
    │
    ├── Random behavior
    ├── Trend
    ├── Seasonal effects
    └── Cyclical effects
```

### Trend

A gradual **upward or downward movement** over time.

### Seasonal Effect

A pattern that **repeats at fixed intervals**, such as a year, month, week, or day.

**Example:** Grocery-store sales may be higher every weekend.

### Cyclical Effect

Longer-term rises and falls, usually occurring over **several years**.

### Stationary Time Series

A time series with:

* No trend
* No seasonal effect
* No cyclical effect
* Relatively constant behavior
* Mainly random variation

## 3.1.3 Moving Average Models

A **simple moving average** smooths random fluctuations by averaging recent observations to identify the underlying direction of the series.

```text
Recent observations
        ↓
      Average
        ↓
Smoothed forecast
```

The number of observations included is represented by **k**.

Different values of **k** produce different forecasts.

## Error Metrics and Forecast Accuracy

Forecast accuracy is evaluated by comparing:

**Actual value vs. Forecast value**

### Mean Absolute Deviation (MAD)

$$
MAD = \frac{\sum |A_t-F_t|}{n}
$$

Where:

* $A_t$ = actual value
* $F_t$ = forecast
* $n$ = number of forecast values

**MAD is less affected by extreme observations.**

### Mean Square Error (MSE)

$$
MSE = \frac{\sum(A_t-F_t)^2}{n}
$$

Because errors are squared, **large errors are penalized more heavily**.

### Root Mean Square Error (RMSE)

$$
RMSE = \sqrt{MSE}
$$

RMSE is expressed in the **same units as the original data**.

### Mean Absolute Percentage Error (MAPE)

$$
MAPE = \frac{1}{n}\sum\left|\frac{A_t-F_t}{A_t}\right|\times100
$$

MAPE removes the measurement scale, making it useful for **relative comparison**.

### Quick Comparison

| Measure  | Main idea                       |
| -------- | ------------------------------- |
| **MAD**  | Less affected by extreme errors |
| **MSE**  | Penalizes large errors strongly |
| **RMSE** | Same units as original data     |
| **MAPE** | Scale-free relative comparison  |

MAD and MSE are mainly meaningful when **comparing models for the same data**.

There is **no universal best error measure**.

## 3.1.4 Exponential Smoothing Models

### Simple Exponential Smoothing

Useful for **short-range forecasting**.

$$
F_{t+1}=\alpha A_t+(1-\alpha)F_t
$$

Where:

* $F_{t+1}$ = forecast for next period
* $F_t$ = current forecast
* $A_t$ = actual value
* $\alpha$ = smoothing constant, $0<\alpha<1$

Initial setup:

$$
F_1=F_2=A_1
$$

### Interpretation

The next forecast is a **weighted combination of the previous forecast and the latest actual observation**.

* Higher $\alpha$ → more weight on recent actual data
* Lower $\alpha$ → more weight on the previous forecast

```text
Higher α → More responsive to recent data
Lower α  → Smoother, more influenced by past forecasts
```

### Double Exponential Smoothing

Used when the time series has a **trend**.

It smooths:

* **Level**
* **Trend**

```text
Simple exponential smoothing → Level
Double exponential smoothing → Level + Trend
```

## Forecasting Time Series with Seasonality

When seasonality exists, specialized methods can provide better forecasts.

### Regression-Based Seasonal Forecasting

Linear regression can use **categorical variables** to represent seasonal periods.

### Holt-Winters Forecasting

Holt-Winters methods smooth **level, trend, and seasonal effects**.

| Model              | Suitable when                                         |
| ------------------ | ----------------------------------------------------- |
| **Additive**       | Seasonal variation is relatively stable               |
| **Multiplicative** | Seasonal variation increases/decreases with the level |

```text
Seasonality + Trend
        ↓
   Holt-Winters
      /      \
 Additive  Multiplicative
    ↓           ↓
Stable      Changing
seasonality amplitude
```

The three smoothing parameters correspond to:

* Level
* Trend
* Seasonality

A chart of the time series should be examined before choosing the model.

## Regression Forecasting with Causal Variables

Variables other than time may influence the value being forecast.

Examples:

* Economic indexes
* Demographic factors
* Hospital capital spending

**Explanatory/causal models**, also called **econometric models**, usually use regression analysis to identify factors that statistically explain the forecast variable.

```text
Causal variables
      ↓
Regression analysis
      ↓
Explain relationships
      ↓
Forecast outcome
```

## The Practice of Forecasting

Businesses commonly use **both judgmental and quantitative methods**.

* Simple time-series models → short- and medium-range forecasts
* Regression analysis → commonly used for long-range forecasting
* Judgment → often used to adjust quantitative forecasts

Statistical models may miss:

* Sales promotions
* Unusual events
* New product introductions
* Large one-time orders
* Sudden trend reversals

```text
Statistical forecast
        ↓
Managerial judgment
        ↓
Adjusted forecast
```

### Choosing a Forecasting Method

There is **no single best method**. The choice depends on:

* Trend
* Seasonality
* Amount of available data
* Forecast horizon
* Experience and knowledge of the forecaster

**Key point:** Quantitative methods may miss sudden changes, while qualitative methods—especially those using indicators—may detect them.
---
# 3.2 Logic and Data-Driven Models

Predictive models can be developed through **logic** or **data**.

```text
Predictive Modeling
       │
       ├── Logic-Driven Models
       │
       └── Data-Driven Models
```

## Logic-Driven Models

Logic-driven models are created using **inferences, existing conditions, business knowledge, and logical reasoning**.

They require:

* Understanding of the business area
* Logical reasoning
* Knowledge of business practices and research

### Example: Economic Value of a Customer

Suppose a restaurant customer:

* Visits **6 times/year**
* Spends **₹5,000 per visit**
* Generates a **40% profit margin**
* Has a **30% annual defection rate**

Annual gross profit:

$$
5000 \times 6 \times 0.40 = ₹12,000
$$

Average customer lifetime:

$$
\frac{1}{0.30}=3.33\text{ years}
$$

Economic value:

$$
12000\times3.33\approx₹40,000
$$

### General Model

$$
V=\frac{R\times F\times M}{D}
$$

Where:

| Symbol | Meaning                     |
| ------ | --------------------------- |
| **V**  | Economic value of customer  |
| **R**  | Revenue per purchase        |
| **F**  | Purchase frequency per year |
| **M**  | Gross profit margin         |
| **D**  | Defection rate              |

**Instant understanding:**
A customer becomes more valuable when they **spend more, visit more often, or generate a higher margin**. Value decreases when the **defection rate increases**. 

## Data-Driven Models

Data-driven models identify relationships between **input and output variables using existing data**, even when the underlying system relationships are not clearly understood.

The model is built from the **data itself**, rather than relying mainly on hand-picked variables or assumptions.

```text
Large amount of existing data
          ↓
Find patterns / relationships
          ↓
Build predictive model
          ↓
Predict future outcomes
```

Common data-driven approaches include:

* Sampling and estimation
* Regression analysis
* Correlation analysis
* Forecasting models
* Simulation

**Key difference:**

| Logic-driven                              | Data-driven                            |
| ----------------------------------------- | -------------------------------------- |
| Starts with reasoning/business knowledge  | Starts with available data             |
| Relationships are logically proposed      | Relationships are discovered from data |
| Useful when business rules are understood | Useful when relationships are unclear  |

Logic-driven models can also be used as a **first step toward establishing relationships for data-driven models**. 

# 3.3 Data Mining and Predictive Analysis Modelling

## Data Mining

Data mining is the process of finding **useful patterns, relationships, and characteristics in large databases** using statistical and analytical techniques.

It can use tools such as:

* Data visualization
* Data summarization
* PivotTables
* Correlation
* Regression analysis

The main goal is to uncover **relationships and hidden patterns** in large datasets. 

## Common Data Mining Approaches

### Data Exploration and Reduction

Identifies **similar groups** within data.

A common use is **customer segmentation**, where customers are divided into relatively homogeneous groups based on characteristics or behavior.

**Example:** A retailer may divide customers into groups such as traditional, contemporary, brand-focused, and fashion-focused customers.

**Purpose:** Better targeting of products, marketing, and business decisions. 

### Classification

Classification predicts **which category a new data item belongs to**.

**Example:** An email system examines the message and classifies it as:

```text
New Email
   ↓
Analyze characteristics
   ↓
Spam or Not Spam
```

Other uses:

* Detecting fraudulent credit-card transactions
* Identifying high-risk loan applicants
* Predicting customer response to advertisements 

### Association

Association identifies **natural relationships between variables/items** and uses them for recommendations or marketing.

**Examples:**

* Netflix → recommends movies based on viewing behavior
* Amazon → recommends products based on previous purchases
* Supermarkets → provide coupons based on current purchases 

### Cause-and-Effect Modeling

Examines relationships between factors that **drive business performance**.

Examples of performance measures:

* Profitability
* Customer satisfaction
* Employee satisfaction

Regression and correlation analysis are important tools for this type of modelling.

**Example:** A company may study whether higher customer satisfaction leads to higher contract-renewal rates. 

# 3.3.1 Predictive Modeling

Predictive modeling uses **historical and current data to predict future or unknown outcomes**.

```text
Historical + Current Data
          ↓
   Build statistical model
          ↓
       Prediction
          ↓
Validate / revise model
          ↓
     New data arrives
          ↓
   Update prediction
```

Common applications include:

* Fraud detection
* Customer behavior prediction
* Risk assessment
* Insurance decisions
* Credit decisions
* Sales forecasting

Predictive models may also operate in **real time**, such as when a bank evaluates a credit-card or loan application. 

### Predictive vs. Causal Modeling

**Predictive modeling** asks:

> "What is likely to happen?"

**Causal modeling** asks:

> "What actually causes it?"

A predictive model may use an indicator or proxy that helps predict an outcome without proving that the indicator causes it.

**Remember:** Correlation does not necessarily mean causation. 

# 3.3.2 What Is Predictive Modeling?

Predictive modeling combines **statistics, data mining, and machine learning** to forecast likely outcomes from historical and existing data.

It is **not a one-time prediction**. The model must be validated or revised as new data changes the underlying situation.

**Example:** A company can use historical sales and marketing expenditure to predict future revenue. If new marketing or sales data changes the pattern, the prediction should be recalculated. 

# 3.3.3 Types of Predictive Models

| Type               | What it does                  | Quick example               |
| ------------------ | ----------------------------- | --------------------------- |
| **Classification** | Assigns data to categories    | Fraud / Not fraud           |
| **Clustering**     | Groups similar items          | Customer segments           |
| **Forecast**       | Predicts numerical values     | Next week's sales           |
| **Outliers**       | Detects unusual observations  | Suspicious transaction      |
| **Time Series**    | Uses data sequences over time | Predict hospital admissions |

### Easy Memory Trick

```text
Classification → Which category?
Clustering    → Which group?
Forecast      → How much?
Outliers      → Is it unusual?
Time Series   → What happens next over time?
```

These models are commonly used to answer different types of business prediction problems. 

# 3.3.4 Predictive Algorithms

### Random Forest

Uses multiple **decision trees** that are independent of one another.

Used for:

* Classification
* Regression
* Large datasets

### Generalized Linear Model (GLM)

Helps identify the **best-fitting relationship** between variables and can handle categorical predictors and tipping points.

### Gradient Boosted Model

Uses related decision trees **sequentially**.

Each new tree attempts to correct weaknesses in previous trees.

**Simple difference:**

```text
Random Forest
Many independent trees
        ↓
Combine results

Gradient Boosting
Tree 1 → Tree 2 → Tree 3
          ↓
Each improves previous tree
```

### K-Means

Groups data points according to **similarity**.

Mainly associated with **clustering**.

**Example:** Grouping millions of customers according to similar shopping preferences.

### Prophet

Used for **time-series and forecasting**, particularly applications such as:

* Inventory planning
* Sales quotas
* Resource allocation

It is flexible and can incorporate useful assumptions. 

# 3.3.5 Steps for Predictive Modeling

```text
Clean Data
    ↓
Choose Modeling Approach
    ↓
Preprocess Data
    ↓
Create Training Data
    ↓
Train Model
    ↓
Test Model Performance
    ↓
Validate with Unused Data
    ↓
Use Model for Prediction
```

### What happens at each stage?

* **Clean:** Handle missing data and outliers.
* **Choose:** Select a suitable parametric or nonparametric approach.
* **Preprocess:** Convert data into a form suitable for the algorithm.
* **Training:** Select data for building the model.
* **Train:** Estimate model parameters.
* **Test:** Check model performance/goodness of fit.
* **Validate:** Test accuracy using data not used to build the model.
* **Predict:** Use the model if its performance is satisfactory. 
---
# 3.4 Machine Learning for Predictive Analytics

Machine learning is an **automated process that extracts patterns from data**.

For predictive analytics, **supervised machine learning** is commonly used.

Supervised learning learns the relationship between:

* **Descriptive features** → information used to make a prediction
* **Target feature** → the outcome we want to predict

```text
Historical labeled data
        ↓
Learn relationship
        ↓
Build prediction model
        ↓
New instance
        ↓
Predict target
```

## Supervised Machine Learning

A dataset contains historical **instances**.

Each instance has:

* Descriptive features
* A known target value

The dataset used to learn the model is called the **training dataset**, and each row is a **training instance**.

### Mortgage Example

A bank has historical mortgage applications.

| Feature               | Meaning                          |
| --------------------- | -------------------------------- |
| **OCCUPATION**        | Professional / Industrial        |
| **AGE**               | Applicant's age                  |
| **LOAN-SALARY RATIO** | Loan amount compared with salary |
| **OUTCOME**           | Default / Repay                  |

Here:

* **Descriptive features:** OCCUPATION, AGE, LOAN-SALARY RATIO
* **Target feature:** OUTCOME

### Simple Prediction Model

```text
If LOAN-SALARY RATIO > 3
        ↓
     DEFAULT

Otherwise
        ↓
      REPAY
```

If this rule correctly predicts every training instance, the model is **consistent with the dataset**.

The same model can then be applied to new mortgage applications to help make lending decisions.

## Why Machine Learning Is Needed

With a small dataset, a person may be able to manually create a prediction rule.

But as the dataset becomes larger and includes more features, manually finding the correct model becomes extremely difficult.

Additional mortgage features may include:

* Loan amount
* Salary
* Property type
* Mortgage type

For example:

```text
If LOAN-SALARY RATIO < 1.5
    → REPAY

Else if LOAN-SALARY RATIO > 4
    → DEFAULT

Else if AGE < 40
AND OCCUPATION = INDUSTRIAL
    → DEFAULT

Else
    → REPAY
```

Finding such rules manually in a large dataset is almost impossible.

**Machine learning automates this search.**

---

# 3.4.1 How Does Machine Learning Work?

Machine learning algorithms search through a set of possible prediction models and try to find a model that captures the relationship between the **descriptive features** and the **target feature**.

```text
Possible prediction models
          ↓
      Search models
          ↓
Compare models
          ↓
Choose suitable model
          ↓
Generalize to new data
```

Simply finding a model that is consistent with the training data, however, is **not enough**.

There are two major problems.

### Noise in Data

Large datasets can contain **noise**.

A model that tries to perfectly fit noisy data may learn incorrect patterns and make poor predictions on new data.

```text
Training data + Noise
        ↓
Model memorizes noise
        ↓
Poor new predictions
```

### Limited Training Data

The training dataset is usually only a **sample of all possible situations**.

Therefore, the training data may not contain every possible combination of feature values.

This makes machine learning an **ill-posed problem**.

### Ill-Posed Problem

An ill-posed problem is one where a **unique solution cannot be determined from the available information alone**.

---

## Example: Supermarket Customer Classification

Suppose a supermarket wants to classify households into:

* **Single**
* **Couple**
* **Family**

The available descriptive features are:

* **BBY** → buys baby food? Yes/No
* **ALC** → buys alcohol? Yes/No
* **ORG** → buys organic vegetables? Yes/No

The target feature is:

**GRP → Single / Couple / Family**

Because there are three binary descriptive features:

$$
2^3=8
$$

So there are **8 possible combinations** of feature values.

Each combination can have one of **3 possible target values**.

Therefore, the number of possible prediction models is:

$$
3^8=6,561
$$

```text
3 binary features
      ↓
2³ = 8 feature combinations
      ↓
3 possible customer groups
      ↓
3⁸ = 6,561 possible models
```

The machine learning algorithm starts with these possible models and eliminates models that disagree with the training data.

---

## After Training Data Is Applied

The training dataset does **not necessarily contain every possible feature combination**.

Therefore, even after removing models inconsistent with the training data, multiple models may remain.

In the example, there are **3 combinations whose correct target is unknown**.

Each has 3 possible target values:

$$
3^3=27
$$

So **27 models** can still be consistent with the training data.

Examples include models such as **M2, M4, and M5**.

### The Problem

Suppose a new customer:

* Buys baby food
* Buys alcohol
* Buys organic vegetables

This combination was not represented in the training data.

Different consistent models may give different predictions:

```text
Same new customer
        ↓
 ┌──────┼──────┐
 M2     M4     M5
 ↓      ↓      ↓
Single Family Couple
```

Therefore, simply choosing a model because it is **consistent with the training data** does not tell us which model is best.

---

# Generalization

A useful predictive model must be able to make correct predictions for **new instances that were not present in the training dataset**.

This ability is called **generalization**.

```text
Memorization
    ↓
Works mainly on training data
    ✗

Generalization
    ↓
Works on unseen data
    ✓
```

### Goal of Machine Learning

The goal is **not merely to memorize the training dataset**.

The goal is to find the model that **generalizes best beyond the dataset**.

This is why machine learning needs a criterion for choosing among candidate models.

---

# Model Selection

Different machine learning algorithms use different **model-selection criteria** to decide which candidate model is preferred.

Therefore:

```text
Choose machine learning algorithm
              ↓
Choose model-selection criterion
              ↓
Search candidate models
              ↓
Select preferred model
```

The assumptions used by an algorithm to guide this selection are called its **inductive bias**.

---

# Inductive Bias

**Inductive bias** is the set of assumptions a machine learning algorithm uses when deciding which models to consider or prefer.

Without inductive bias, the algorithm cannot learn beyond what is directly contained in the training data.

There are two types:

```text
Inductive Bias
      │
      ├── Restriction Bias
      │
      └── Preference Bias
```

## Restriction Bias

Restricts the **types of models** the algorithm is allowed to consider.

**Example:** An algorithm may only consider models based on a linear combination of features.

## Preference Bias

When several models are possible, it guides the algorithm to **prefer some models over others**.

**Example:** Prefer a simpler or shallower model over a more complex one.

### Difference

| Type                 | Main question                     |
| -------------------- | --------------------------------- |
| **Restriction bias** | Which models are allowed?         |
| **Preference bias**  | Which allowed model is preferred? |

---

# Examples of Inductive Bias

## Multivariable Linear Regression with Gradient Descent

This approach:

* Uses a **restriction bias** by considering prediction models based on a linear combination of descriptive features.
* Uses a **preference bias** through gradient descent to determine the preferred model within the weight space.

## ID3 Algorithm

ID3:

* Uses a **restriction bias** by considering tree-based prediction models.
* Each branch represents checks on individual descriptive features.
* Uses a **preference bias** by preferring **shallower, less complex trees** over larger trees.

```text
Machine Learning Algorithm
          ↓
   Inductive Bias
      /       \
Restriction   Preference
   ↓             ↓
What models    Which model
are allowed?   is preferred?
```

---

# Core Idea of Machine Learning

```text
Historical Data
      ↓
Descriptive Features + Target
      ↓
Generate / consider possible models
      ↓
Use inductive bias
      ↓
Select a suitable model
      ↓
Learn underlying relationship
      ↓
Generalize to unseen instances
      ↓
Make predictions
```

**The central idea:** Machine learning searches among possible prediction models and uses appropriate assumptions to select a model that **generalizes beyond the training data**, rather than simply memorizing the data.
---
# Business Forecasting & Predictive Analytics — Exam Revision

## 3.1 Business Forecasting

**Purpose:** Predict future values → support decisions.

**3 major approaches:**

```text
Business Forecasting
├── Qualitative / Judgmental
├── Statistical Time-Series
└── Explanatory / Causal
```

### Qualitative / Judgmental

* **When:** No historical data, long-term forecasting, expert knowledge, qualitative factors.
* **Delphi:** Anonymous experts → questionnaires → feedback → revise → convergence.
* **Indicators:** Variables believed to influence future outcome.
* **Indexes:** Combine indicators to signal economic/business conditions.
* **Leading indicators:** Give clues about future economic performance.

### Statistical Forecasting

* Mainly **short-range**
* **Time series:** Historical observations over time.
* Assumption → **future ≈ continuation/extrapolation of past**

**Time-series components:**

* **Random** → irregular variation
* **Trend** → long-term upward/downward movement
* **Seasonality** → fixed repeating pattern
* **Cyclical** → longer-term fluctuations

**Stationary:** No trend + no seasonality + no cycle → mainly random, relatively constant.

### Moving Average

**Purpose:** Smooth random fluctuations → identify underlying direction.

* **k** = number of recent observations averaged.
* Different **k** → different forecasts.

### Forecast Error Measures

Let:

* $A_t$ = Actual
* $F_t$ = Forecast

| Measure  | Key idea                                          |
| -------- | ------------------------------------------------- |
| **MAD**  | Average absolute error; less affected by extremes |
| **MSE**  | Squared error; strongly penalizes large errors    |
| **RMSE** | √MSE; same units as data                          |
| **MAPE** | Percentage error; scale-free                      |

```text
MAD → robust
MSE → large errors matter more
RMSE → same units
MAPE → relative comparison
```

### Exponential Smoothing

**Simple exponential smoothing:**

$$
F_{t+1}=\alpha A_t+(1-\alpha)F_t
$$

* $0<\alpha<1$
* High $\alpha$ → **more recent data**
* Low $\alpha$ → **smoother / past forecast more important**
* Short-range forecasting
* Initial: $F_1=F_2=A_1$

**Double exponential smoothing:**

* **Level + Trend**
* Used when trend exists.

### Seasonality Forecasting

**Regression-based:** Regression + categorical seasonal variables.

**Holt-Winters:**

* Smooths **level + trend + seasonality**
* **Additive:** Stable seasonal variation
* **Multiplicative:** Seasonal amplitude changes with level

### Causal / Regression Forecasting

* Uses variables other than time.
* Examples → economic indexes, demographics, capital spending.
* **Regression → identify explanatory relationships → forecast.**

### Practice of Forecasting

* Companies use **judgment + quantitative methods**.
* Time-series → short/medium range.
* Regression → commonly long range.
* Statistical forecasts may be **adjusted by managerial judgment**.
* No universally best method.
* Choice depends on → trend, seasonality, data available, forecast horizon, expertise.

---

# 3.2 Logic and Data-Driven Models

```text
Predictive Models
├── Logic-Driven
└── Data-Driven
```

### Logic-Driven

**Based on:** Business knowledge + inference + existing conditions + logical reasoning.

**Customer Economic Value:**

$$
V=\frac{R\times F\times M}{D}
$$

* **V** → Customer value
* **R** → Revenue per purchase
* **F** → Purchase frequency
* **M** → Profit margin
* **D** → Defection rate

**Customer lifetime:**

$$
\frac{1}{D}
$$

### Data-Driven

**Based on:** Existing data → discover relationships/patterns → predict outcomes.

Used when relationships between variables are **not clearly known**.

Includes:

* Sampling & estimation
* Regression
* Correlation
* Forecasting
* Simulation

**Easy difference:**

```text
Logic-driven → "What should happen based on reasoning?"
Data-driven  → "What does the data tell us?"
```

---

# 3.3 Data Mining & Predictive Analysis

### Data Mining

Finding **patterns, relationships, characteristics, hidden information** in large datasets.

Uses:

* Visualization
* Summarization
* PivotTables
* Correlation
* Regression
* Other analytical methods

### Main Data-Mining Approaches

**Data Exploration & Reduction**

→ Find similar groups → **segmentation**

**Classification**

→ Assign new data to a **category**

Example: Spam / Not Spam.

**Association**

→ Find relationships between items/variables.

Example: Netflix/Amazon recommendations.

**Cause-and-Effect Modeling**

→ Understand factors driving business performance.

Example: Satisfaction → contract renewal.

---

# Predictive Modeling

**Historical + current data → model → future/unknown outcome**

```text
Collect data
↓
Build model
↓
Predict
↓
Validate / revise
↓
New data → update model
```

### Predictive vs Causal

* **Predictive:** What is likely to happen?
* **Causal:** What causes it?
* **Correlation ≠ causation**

### 5 Predictive Model Types

| Model              | Keyword                |
| ------------------ | ---------------------- |
| **Classification** | Category               |
| **Clustering**     | Similar groups         |
| **Forecast**       | Numerical future value |
| **Outliers**       | Abnormal data          |
| **Time Series**    | Data over time         |

**Memory:**

```text
Classification → Which category?
Clustering → Which group?
Forecast → How much?
Outlier → Is it unusual?
Time Series → What happens over time?
```

### Predictive Algorithms

* **Random Forest** → many independent decision trees
* **GLM** → best-fit relationship
* **Gradient Boosting** → sequential trees; later trees correct earlier errors
* **K-Means** → similarity-based clustering
* **Prophet** → time-series forecasting

### Predictive Modeling Steps

```text
Clean
↓
Choose approach
↓
Preprocess
↓
Training data
↓
Train model
↓
Test performance
↓
Validate on unseen data
↓
Predict
```

---

# 3.4 Machine Learning for Predictive Analytics

### Machine Learning

**Automated extraction of patterns from data.**

For predictive analytics → mainly **Supervised Machine Learning**.

```text
Historical labeled data
↓
Learn relationship
↓
Prediction model
↓
New instance
↓
Prediction
```

### Key Terms

* **Descriptive features** → inputs
* **Target feature** → output to predict
* **Training instance** → one row/example
* **Training dataset** → collection of training instances
* **Labeled dataset** → includes target values

### Mortgage Example

**Inputs:**

* Occupation
* Age
* Loan-salary ratio

**Target:**

* Default / Repay

Simple rule:

```text
Loan-Salary Ratio > 3 → Default
Otherwise → Repay
```

More features → more complex relationships → **machine learning becomes useful**.

---

# How Machine Learning Works

Machine learning:

**Searches possible models → evaluates them → selects a suitable model → generalizes to new data.**

### Two Problems

**Noise**

→ Random/errors in data
→ Model may learn noise
→ Poor new predictions

**Limited Training Data**

→ Training data is only a sample
→ Doesn't cover every possible case
→ **Ill-posed problem**

### Ill-Posed Problem

**Available information cannot determine one unique solution.**

---

# Generalization

**Most important idea:**

> A good model must work on **unseen data**, not just training data.

```text
Memorization → Training data only ✗
Generalization → Unseen data ✓
```

---

# Inductive Bias

**Inductive bias = assumptions used to select useful models.**

Without inductive bias → machine learning cannot learn beyond the information directly present in the data.

```text
Inductive Bias
├── Restriction Bias
└── Preference Bias
```

### Restriction Bias

**Limits which models can be considered.**

Example → Only consider linear models.

### Preference Bias

**Chooses which model is preferred among allowed models.**

Example → Prefer simpler/shallower models.

### Algorithms & Bias

**Multivariable Linear Regression + Gradient Descent**

* Restriction → linear models
* Preference → gradient-descent-based selection

**ID3**

* Restriction → tree models
* Preference → shallower/less complex trees

---

# ⚡ Last-Minute Memory Map

```text
FORECASTING
│
├── Judgmental → Experts / Delphi / Indicators
├── Time-Series → Past data
│   ├── Trend
│   ├── Seasonal
│   ├── Cyclical
│   └── Random
├── Moving Average → Smoothing
├── Exponential Smoothing → Recent data
├── Holt-Winters → Level + Trend + Seasonality
└── Causal Regression → Other influencing variables

PREDICTIVE ANALYTICS
│
├── Logic-Driven → Reasoning
├── Data-Driven → Data patterns
├── Data Mining → Hidden patterns
├── Classification → Categories
├── Clustering → Groups
├── Association → Relationships
├── Outliers → Abnormal
├── Forecast → Numerical future
└── Time Series → Time-based prediction

MACHINE LEARNING
│
├── Supervised → Labeled data
├── Features → Inputs
├── Target → Output
├── Training → Learn
├── Generalization → Unseen data
├── Noise → Bad learning
├── Ill-posed → Multiple possible models
└── Inductive Bias
    ├── Restriction → What models allowed?
    └── Preference → Which model preferred?
```
