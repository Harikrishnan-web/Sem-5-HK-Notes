# Unit-3 Bussiness forecasting
# Introduction to Business Forecasting and Predictive Analytics

## Business Forecasting

**Business forecasting** is the process of using available information to estimate what is likely to happen in the future so that better business decisions can be made.

A business may need to forecast:

* future sales
* demand
* revenue
* costs
* production requirements
* customer demand
* market conditions
* economic performance

The choice of forecasting method depends mainly on:

* **Forecast horizon** — how far into the future we want to predict.
* **Available information/data** — whether historical numerical data exists.
* **Pattern in the data** — whether there is a trend, seasonality, cycle, or mainly randomness.
* **Knowledge of the decision maker** — especially when important non-numerical information is involved.

### The three major forecasting approaches

```text
                    BUSINESS FORECASTING
                           │
          ┌────────────────┼─────────────────┐
          │                │                 │
          ▼                ▼                 ▼
   Qualitative /      Statistical       Explanatory /
    Judgmental        Time-Series          Causal
          │                │                 │
   Experience &       Historical data    Other factors
    intuition         over time          explain outcome
```

### Quick understanding

Imagine a company wants to predict **next year's sales**.

* If there is **little/no historical data**, managers may rely on expert opinions → **Qualitative/Judgmental**
* If the company has several years of sales data and wants a **short-term prediction**, it can analyze the historical pattern → **Statistical Time-Series**
* If sales depend on factors such as **income, population, interest rates, or advertising expenditure**, the company can model those factors → **Explanatory/Causal**

---

# 3.1 Introduction to Business Forecasting

Forecasting methods are not universally good or bad. The appropriate method depends on the characteristics of the forecasting problem.

A useful decision logic is:

```text
Do we have sufficient historical numerical data?
              │
       ┌──────┴──────┐
       │             │
      NO            YES
       │             │
       ▼             ▼
Qualitative /   Examine the time
 Judgmental      series pattern
                       │
          ┌────────────┼─────────────┐
          │            │             │
       Random       Trend /       Seasonality /
       behavior     pattern        cycle
          │            │             │
          ▼            ▼             ▼
   Time-series    Appropriate     Seasonal/
      models       smoothing      specialized
                                  methods
```

Another important question is:

> **Are factors other than time influencing the forecast?**

If yes, **causal/regression forecasting** may be appropriate.

---

# 3.1.1 Qualitative and Judgmental Forecasting

## Meaning

**Qualitative and judgmental forecasting** relies primarily on:

* experience
* intuition
* expert knowledge
* opinions
* non-quantitative information

It is particularly useful when:

* historical data is unavailable
* the forecast is far into the future
* major changes are expected
* important information cannot easily be represented numerically

### Real-world example

Suppose a company is developing a **completely new type of microprocessor**.

There is no historical sales data for that exact technology.

A statistical model cannot simply look at previous sales of the same product because the product does not exist yet.

The company may therefore ask technology experts:

> When will the technology become commercially available, and what capabilities will it have?

That is a **judgmental forecast**.

### Another important use

Judgment can also be added to a quantitative forecast when something important is not captured by historical numbers.

For example:

```text
Statistical forecast
        │
        ▼
Expected sales = ₹10 crore
        │
        │ Manager knows a major competitor
        │ is launching a cheaper product
        ▼
Judgmental adjustment
        │
        ▼
Final forecast = adjusted value
```

So judgmental forecasting does **not necessarily replace quantitative forecasting**. It can also supplement it.

---

# The Delphi Method

## Meaning

The **Delphi method** is a structured judgmental forecasting technique that uses a **panel of experts**.

The important feature is that the experts generally **do not know each other's identities**.

### How the Delphi method works

```text
        Select a panel of experts
                  │
                  ▼
        Experts answer questionnaire
                  │
                  ▼
       Responses are collected
                  │
                  ▼
 Responses are edited/anonymized
                  │
                  ▼
   Experts see the group opinions
                  │
                  ▼
       Experts revise estimates
                  │
                  ▼
          Another round
                  │
                  ▼
       Opinions begin to converge
```

The process is generally repeated for **two or three rounds**.

### Why keep identities confidential?

If experts know exactly who gave each opinion, a powerful or senior person's opinion may influence everyone else.

Keeping identities confidential encourages:

* more independent opinions
* less pressure
* unbiased exchange of ideas
* reconsideration of different viewpoints

### Real-world example

Suppose a company wants to predict:

> **When will fully autonomous commercial vehicles become widely available?**

There may not be sufficient historical data.

The company could ask experts in:

* automobile technology
* artificial intelligence
* regulation
* manufacturing
* transportation

Each expert gives an estimate.

The anonymous summary is sent back to them.

After seeing the range of opinions, experts reconsider their estimates and submit another response.

After a few rounds, the estimates may move closer together.

### Key idea

> **Delphi = Experts → Anonymous opinions → Feedback → Revision → Convergence**

### Best suited for

The PDF particularly identifies the Delphi method as a strong approach for forecasting **long-range trends and impacts**.

---

# Indicators and Indexes

## Indicators

An **indicator** is a measure believed to influence or provide information about the future behavior of the variable we want to forecast.

The basic logic is:

```text
Indicator changes
       │
       ▼
Provides information about
future conditions
       │
       ▼
Helps forecast the target variable
```

### Real-world example

Suppose you want to forecast **future housing activity**.

If building permits increase significantly, this may provide information about future construction activity.

So:

```text
More building permits
        ↓
Possible increase in construction
        ↓
Possible increase in demand for
construction-related goods/services
```

The indicator itself is not necessarily the final thing we want to predict. It gives us **clues about the future**.

---

## Indexes

An **index** combines or represents information from indicators to help understand broader economic or business conditions.

The PDF gives the example of the **Index of Leading Indicators**, which was designed to help predict future economic performance.

Its components included indicators such as:

* average weekly hours in manufacturing
* initial unemployment insurance claims
* new orders for consumer goods and materials
* vendor performance
* new orders for nondefense capital goods
* private housing building permits
* stock prices
* money supply
* interest-rate spread
* consumer expectations

### Easy way to remember

```text
Indicator
   ↓
Individual signal about future conditions

Index
   ↓
Collection/combination of indicators
   ↓
Broader picture of future conditions
```

---

# Example: Leading Economic Indicators

The idea of a **leading indicator** is particularly important.

A leading indicator provides information that may change **before** the broader economic variable being forecast changes.

### Simple example

Imagine:

```text
Building permits ↑
       ↓
Construction activity may ↑
       ↓
Demand for construction materials may ↑
       ↓
Future business activity may ↑
```

The building permit information can therefore provide an early signal.

### Important distinction

An indicator is useful because it contains information about the behavior of the variable we want to forecast.

The goal is not simply:

> "Find a number."

The goal is:

> **"Find information that gives us a useful signal about what is likely to happen."**

---

# 3.1.2 Statistical Forecasting Models

## Meaning

**Statistical time-series models** use historical numerical observations to forecast future values.

They are particularly useful for **short-range forecasting**.

A **time series** is a sequence/stream of historical observations collected over time.

For example:

| Month    |   Sales |
| -------- | ------: |
| January  | ₹80,000 |
| February | ₹85,000 |
| March    | ₹83,000 |
| April    | ₹90,000 |
| May      | ₹94,000 |

Here, sales observed at different points in time form a **time series**.

The general idea is:

```text
Historical data
      │
      ▼
Identify pattern
      │
      ▼
Assume recent forces continue
into the near future
      │
      ▼
Forecast future value
```

The basic assumption is:

> **The forces that affected the variable in the recent past will continue to influence it in the near future.**

Therefore, the model essentially **extrapolates the historical data into the future**.

---

# Components of a Time Series

A time series can contain one or more of these behaviors:

```text
                 TIME SERIES
                      │
       ┌──────────────┼───────────────┐
       │              │               │
       ▼              ▼               ▼
    Random         Trend          Seasonal
    behavior      movement         effects
                                       │
                                       ▼
                                   Cyclical
                                    effects
```

## Random behavior

The values fluctuate without a clear systematic pattern.

Example:

```text
100 → 103 → 98 → 101 → 99 → 102
```

There is no obvious sustained upward/downward movement or repeating pattern.

---

## Trend

A **trend** is a gradual upward or downward movement over time.

### Upward trend

```text
Sales
  │             ●
  │          ●
  │       ●
  │    ●
  │ ●
  └────────────────── Time
```

Example:

A growing company may experience:

```text
₹5L → ₹5.5L → ₹6L → ₹6.8L → ₹7.5L
```

The overall direction is upward.

### Downward trend

The same idea applies when the variable gradually decreases.

---

## Seasonal Effect

A **seasonal effect** is a pattern that repeats at a **fixed interval**.

It may repeat:

* yearly
* monthly
* weekly
* daily

### Real-world example

A grocery store may have:

```text
Monday      → moderate customers
Tuesday     → moderate customers
Wednesday   → moderate customers
Thursday    → moderate customers
Friday      → high customers
Saturday    → very high customers
Sunday      → very high customers
```

If this pattern repeats every week, it is **seasonality**.

Another example is higher natural-gas usage during particular parts of the year.

### Key distinction

> **Seasonality = predictable repetition at a fixed time interval.**

---

## Cyclical Effect

A **cyclical effect** describes longer-term ups and downs, generally occurring over a much longer period such as several years.

For example:

```text
        /\             /\
       /  \           /  \
______/    \_________/    \______
```

These movements may correspond to longer-term economic/business cycles.

### Seasonality vs Cyclicality

| Feature                  | Seasonal            | Cyclical                 |
| ------------------------ | ------------------- | ------------------------ |
| Repetition               | Fixed interval      | Longer-term fluctuations |
| Typical duration         | Day/week/month/year | Several years            |
| Example                  | Weekend sales       | Economic/business cycle  |
| Predictability of timing | More regular        | Less fixed               |

---

# Stationary Time Series

A **stationary time series** is a time series that:

* has no significant trend
* has no seasonal effect
* has no cyclical effect
* remains relatively constant
* mainly exhibits random behavior

### Visual idea

```text
Value
  │   ●     ●
  │      ●
  │ ●       ●    ●
  │    ●       ●
  │       ●
  └────────────────── Time
```

The observations fluctuate around a relatively stable level.

### Compare

```text
Stationary
──────────────
random fluctuations
around a stable level

Trend
──────────────
gradual movement upward/downward

Seasonal
──────────────
repeating pattern at fixed intervals

Cyclical
──────────────
longer-term rises and falls
```

### Why this matters

The forecasting technique should match the pattern in the time series.

A method that works well for a stable series may not be the best method when strong trend or seasonality exists.

---

# 3.1.3 Moving Average Models

## Simple Moving Average

The **simple moving average** is a **smoothing method**.

Its purpose is to reduce random fluctuations so that the underlying direction of the time series becomes easier to see.

### Core idea

Instead of relying heavily on one observation:

```text
One observation
      ↓
Forecast
```

we average several recent observations:

```text
Recent observations
       │
       ▼
   Average them
       │
       ▼
    Forecast
```

The number of observations included is represented by **k**.

Conceptually:

[
F_{t+1}=\frac{A_t+A_{t-1}+\cdots+A_{t-k+1}}{k}
]

where:

* (F_{t+1}) = forecast for the next period
* (A_t) = actual value in the current period
* (k) = number of recent observations included in the average

### Instant example

Suppose a shop's sales for the last three days were:

```text
Monday     = 100 units
Tuesday    = 120 units
Wednesday  = 110 units
```

Using a **3-period moving average**:

[
F=\frac{100+120+110}{3}=110
]

So the forecast for the next period is:

**110 units**

If the next actual value becomes 130, the oldest value is removed and the new moving average uses the latest three observations:

```text
120, 110, 130
```

### Why is it called "moving"?

Because the group of observations used for the average **moves forward through time**.

```text
100  120  110  130  140
 └─────────┘
   Average

       120  110  130  140
       └─────────┘
         Average

             110  130  140
             └─────────┘
               Average
```

### Main purpose

> **Moving average = smooth random fluctuations and reveal the underlying direction.**

---

# Error Metrics and Forecast Accuracy

A forecast is useful only if it predicts future values reasonably well.

We therefore compare:

```text
Actual value
     │
     ▼
   Compare
     ▲
     │
Forecast value
```

The difference between actual and forecast is the **forecast error**.

Conceptually:

[
e_t=A_t-F_t
]

where:

* (A_t) = actual observation
* (F_t) = forecast
* (e_t) = forecast error

The PDF identifies four commonly used measures:

* **MAD** — Mean Absolute Deviation
* **MSE** — Mean Square Error
* **RMSE** — Root Mean Square Error
* **MAPE** — Mean Absolute Percentage Error

---

# Mean Absolute Deviation (MAD)

## Meaning

**MAD** measures the average size of the forecasting errors while ignoring whether the error was positive or negative.

The absolute error is:

[
|A_t-F_t|
]

The MAD is:

[
MAD=\frac{\sum |A_t-F_t|}{n}
]

where:

* (A_t) = actual value
* (F_t) = forecast
* (n) = number of forecast values

### Why absolute value?

Suppose:

```text
Error 1 = +10
Error 2 = -10
```

If we simply average the errors:

[
\frac{10+(-10)}{2}=0
]

That incorrectly suggests there was no error.

MAD removes the negative sign:

```text
|+10| = 10
|-10| = 10
```

So:

[
MAD=\frac{10+10}{2}=10
]

### Real-world example

Suppose a store has:

| Actual | Forecast | Error | Absolute Error |
| -----: | -------: | ----: | -------------: |
|    100 |       90 |    10 |             10 |
|    120 |      130 |   -10 |             10 |
|    110 |      105 |     5 |              5 |

[
MAD=\frac{10+10+5}{3}=8.33
]

So the forecast is off by approximately **8.33 units on average**.

### Important point from the PDF

MAD is relatively **less affected by extreme observations** than MSE.

Therefore, when extreme observations are rare and do not have special meaning, MAD may be preferable to MSE.

---

# Mean Square Error (MSE)

## Meaning

**MSE** averages the **squared forecasting errors**.

[
MSE=\frac{\sum(A_t-F_t)^2}{n}
]

### Why square the errors?

Squaring makes large errors much more influential.

For example:

```text
Error = 2
Squared error = 4

Error = 10
Squared error = 100
```

The error of 10 becomes much more important than the error of 2.

### Instant example

Suppose errors are:

```text
5, -5, 10
```

Square them:

```text
25, 25, 100
```

Then:

[
MSE=\frac{25+25+100}{3}=50
]

### Key idea

> **MSE strongly penalizes large forecasting errors.**

This makes it useful when large errors are particularly undesirable.

---

# Root Mean Square Error (RMSE)

## Meaning

**RMSE** is simply the square root of MSE.

[
RMSE=\sqrt{MSE}
]

### Why use RMSE?

MSE is expressed in **squared units**.

For example, if the original data is measured in:

```text
₹
```

MSE is effectively in:

```text
₹²
```

RMSE brings the measure back to the **same unit as the original data**.

### Example

If:

[
MSE=50
]

then:

[
RMSE=\sqrt{50}\approx7.07
]

So the RMSE is approximately **7.07 units**.

### Key idea

```text
MSE
 ↓ square root
RMSE
 ↓
Same units as original data
```

---

# Mean Absolute Percentage Error (MAPE)

## Meaning

**MAPE** expresses forecast error relative to the actual value, usually as a percentage.

Conceptually:

[
MAPE=
\frac{1}{n}
\sum
\left|
\frac{A_t-F_t}{A_t}
\right|
\times100
]

### Why is MAPE different?

MAD and MSE depend on the **measurement scale**.

For example:

```text
Forecasting profit:
₹10,000,000
```

will naturally produce larger numerical errors than forecasting:

```text
Market share:
0.25
```

Even if the forecasting quality is similar.

MAPE converts the error into a **relative percentage**, making comparison easier.

### Instant example

Suppose:

```text
Actual sales = 200
Forecast = 180
```

Absolute error:

[
|200-180|=20
]

Percentage error:

[
\frac{20}{200}\times100=10%
]

So the forecast error is **10%**.

### Comparison of error measures

| Measure  | Main idea                | Strong effect of large errors? | Same unit as data? |
| -------- | ------------------------ | ------------------------------ | ------------------ |
| **MAD**  | Average absolute error   | Relatively low                 | Yes                |
| **MSE**  | Average squared error    | **Yes**                        | No                 |
| **RMSE** | Square root of MSE       | **Yes**                        | **Yes**            |
| **MAPE** | Average percentage error | Based on relative error        | Percentage         |

### Choosing between them

There is **no universal best error measure**.

The PDF emphasizes that the choice depends on the situation.

A useful way to remember the differences:

```text
MAD
"How far off am I on average?"

MSE
"How badly am I penalizing large errors?"

RMSE
"How large is the typical error in the original units?"

MAPE
"How large is the error relative to the actual value?"
```

Also remember:

> **Forecasting measures should generally be compared across models forecasting the same data.**

---

# 3.1.4 Exponential Smoothing Models

Exponential smoothing is another forecasting approach, particularly useful for **short-range forecasting**.

Unlike a simple moving average, exponential smoothing gives different weights to observations.

The basic idea is:

```text
Recent information
       ↓
More influence

Older information
       ↓
Less influence
```

This is why the method is called **exponential smoothing**: the influence of older observations decreases progressively.

---

# Simple Exponential Smoothing Model

## Core idea

Simple exponential smoothing produces the next forecast using:

* the previous forecast
* the most recent actual observation
* a smoothing constant

The standard equation described by the PDF is:

[
F_{t+1}=\alpha A_t+(1-\alpha)F_t
]

Equivalent form:

[
F_{t+1}=F_t+\alpha(A_t-F_t)
]

where:

* (F_{t+1}) = forecast for period (t+1)
* (F_t) = forecast for period (t)
* (A_t) = actual observation in period (t)
* (\alpha) = smoothing constant
* (0<\alpha<1)

> **Note:** The displayed equations themselves are missing from the extracted PDF text, but the PDF explicitly describes these two forms and the roles of (F_{t+1}, F_t, A_t,) and (\alpha). 

---

## Understanding the first form

[
F_{t+1}=\alpha A_t+(1-\alpha)F_t
]

This says:

> **Next forecast = weighted actual value + weighted previous forecast**

The weights are:

```text
Actual value       → α
Previous forecast  → 1 − α
```

Since:

[
\alpha+(1-\alpha)=1
]

the forecast is a weighted average.

---

## Understanding the second form

[
F_{t+1}=F_t+\alpha(A_t-F_t)
]

This gives an even more intuitive interpretation:

```text
New forecast
    =
Old forecast
    +
α × Forecast error
```

where:

[
A_t-F_t
]

is the forecast error.

So the model says:

> **Take the old forecast and move it toward the actual value by a fraction α of the error.**

---

# Instant Example: Simple Exponential Smoothing

Suppose:

```text
Previous forecast = 100
Actual value      = 120
α                 = 0.30
```

Forecast error:

[
120-100=20
]

Adjustment:

[
0.30(20)=6
]

New forecast:

[
100+6=106
]

So:

```text
Old forecast = 100
Actual       = 120
Difference   = 20
30% of error = 6
New forecast = 106
```

The forecast moves toward the actual value but **doesn't jump completely to 120**.

---

# Effect of the Smoothing Constant α

The value of (\alpha) determines how quickly the forecast reacts to new information.

## Large α

A large (\alpha) gives **more weight to the recent actual observation**.

```text
Large α
   ↓
Recent actual gets more importance
   ↓
Forecast reacts faster to changes
```

## Small α

A small (\alpha) gives **more weight to the previous/smoothed forecast**.

```text
Small α
   ↓
Previous forecast gets more importance
   ↓
Forecast changes more slowly
```

### Memory trick

> **α = Attention to the Actual**

Large α → pay more attention to what **actually happened recently**.

---

# Starting the Simple Exponential Smoothing Process

The PDF specifies that initially:

[
F_1=F_2=A_1
]

That means the initial forecasts are set equal to the first actual observation.

After that, the forecast is repeatedly updated using the previous forecast and actual observation.

```text
Initial actual
     │
     ▼
Initial forecast
     │
     ▼
Actual observation
     │
     ▼
Calculate new forecast
     │
     ▼
Next actual observation
     │
     ▼
Calculate next forecast
     │
     ▼
Repeat
```

---

# Why Exponential Smoothing Uses All Past Data

The PDF explains an important property:

Although the formula directly uses only:

* previous forecast
* current actual observation

the previous forecast itself contains information from earlier observations.

Therefore:

```text
Current actual
      +
Previous forecast
      ↓
Previous forecast already contains
older information
      ↓
New forecast indirectly reflects
many/all past observations
```

The weights of older observations become progressively smaller.

So the forecast is a **decreasingly weighted average of past time-series data**.

This holds when:

[
0<\alpha<1
]

---

# Double Exponential Smoothing

Simple exponential smoothing is useful when the series is relatively stable.

But what if the data has a **trend**?

This is where **double exponential smoothing** becomes important.

## Core idea

Double exponential smoothing smooths two components:

```text
Double Exponential Smoothing
            │
       ┌────┴────┐
       ▼         ▼
     Level      Trend
```

The PDF describes two estimates:

* (a_t) — **level**
* (b_t) — **trend**

The method therefore smooths both parameters of a **linear trend model**. 

### Level

The **level** represents the current underlying value of the series.

The estimate of the level is influenced by:

* the observed value
* the predicted/smoothed value

A larger smoothing constant places more weight on the observed value.

A lower value places more weight on the smoothed predicted value.

### Trend

The **trend** represents the direction/rate at which the level is changing.

So:

```text
Level  → "Where are we now?"

Trend  → "Which direction are we moving?"
```

### Instant example

Imagine monthly sales:

```text
January    100
February   110
March      120
April      130
```

The series is clearly moving upward.

A method that considers only the current level may not adequately capture the increasing direction.

Double exponential smoothing considers:

```text
Current level
      +
Underlying trend
      ↓
Better forecast for a trending series
```

---

# Forecasting Time Series with Seasonality

When a time series has **seasonality**, ordinary forecasting techniques may not be sufficient.

The PDF identifies different approaches for seasonal forecasting.

```text
Time series has seasonality
          │
          ▼
   Choose seasonal method
          │
      ┌───┴────┐
      │        │
      ▼        ▼
 Regression   Holt-Winters
 based        methods
 forecasting
```

---

# Regression-Based Seasonal Forecasting Models

One approach is **linear regression**.

For seasonal time series, **multiple linear regression with categorical variables** can be used.

### Simple idea

Suppose a store has different sales behavior depending on the month.

```text
January
February
March
...
December
```

The model can represent the seasonal periods using categorical variables and use regression to estimate their influence.

The key idea is:

> **Regression can explicitly represent seasonal differences between periods.**

---

# Holt-Winters Forecasting for Seasonal Time Series

**Holt-Winters models** use smoothing constants to smooth variations in:

* level
* seasonal pattern
* and, when applicable, trend

The PDF notes a Holt-Winters approach for time series with **seasonality but no trend**. 

### Think of it as

```text
Seasonal data
     │
     ├── Level
     │
     └── Seasonal pattern
            ↓
       Smooth both
            ↓
         Forecast
```

---

# Holt-Winters with Seasonality and Trend

Many real-world time series contain **both trend and seasonality**.

### Example

Imagine an ice-cream company's sales:

```text
Year 1:  100  120  150  130
Year 2:  120  145  180  155
Year 3:  140  170  210  180
```

There may be:

* an upward trend → sales are growing
* seasonal behavior → certain periods consistently have higher sales

Holt-Winters can combine both.

```text
             Time Series
                  │
          ┌───────┴────────┐
          ▼                ▼
        Trend          Seasonality
          │                │
          └───────┬────────┘
                  ▼
            Holt-Winters
                  │
                  ▼
              Forecast
```

The PDF identifies **two types** of Holt-Winters smoothing models:

* **Additive**
* **Multiplicative**

---

# Holt-Winters Additive Model

The **additive model** is appropriate when the seasonal variation is relatively **stable in size** over time.

### Example

Suppose normal sales increase:

```text
100 → 120 → 140 → 160
```

but the seasonal effect is consistently around:

```text
+20 units
```

The seasonal fluctuation is approximately constant.

```text
Trend increases
       +
Seasonal effect remains
roughly constant
       ↓
Additive model
```

### Memory trick

> **Additive = seasonal effect is added as a relatively fixed amount.**

---

# Holt-Winters Multiplicative Model

The **multiplicative model** is appropriate when the **size/amplitude of the seasonal effect changes with the level of the series**.

### Example

Suppose a seasonal product has:

```text
Low sales → small seasonal fluctuation
High sales → large seasonal fluctuation
```

For example:

```text
Year 1: seasonal difference ≈ 10 units
Year 2: seasonal difference ≈ 20 units
Year 3: seasonal difference ≈ 30 units
```

The seasonal amplitude increases as the series becomes larger.

```text
Growing level
      +
Growing seasonal amplitude
      ↓
Multiplicative model
```

### The key distinction

| Model              | When appropriate                                      |
| ------------------ | ----------------------------------------------------- |
| **Additive**       | Seasonal variation is relatively stable               |
| **Multiplicative** | Seasonal variation increases/decreases with the level |

The PDF specifically recommends looking at a **chart of the time series first** to help identify the appropriate model. 

---

# Holt-Winters Parameters

The PDF identifies three smoothing parameters:

* (\alpha) → **level**
* (\beta) → **trend**
* (\gamma) → **seasonal factor**

So remember:

```text
α → Level
β → Trend
γ → Seasonality
```

This is one of the most important things to remember about Holt-Winters.

---

# Selecting the Appropriate Time-Series Forecasting Model

The basic selection logic from the material can be condensed as:

| Data characteristic                                        | Suitable approach                                            |
| ---------------------------------------------------------- | ------------------------------------------------------------ |
| Mainly random/stable                                       | Simple forecasting/smoothing methods                         |
| Trend                                                      | Trend-sensitive methods such as double exponential smoothing |
| Seasonality                                                | Seasonal forecasting methods                                 |
| Trend + seasonality                                        | Holt-Winters                                                 |
| Seasonal effects represented through explanatory variables | Regression-based seasonal forecasting                        |
| Other variables influence the outcome                      | Causal/regression forecasting                                |

The central rule is:

> **Do not choose a forecasting method first and then force the data into it. Examine the characteristics of the time series first.**

---

# Regression Forecasting with Causal Variables

Sometimes the variable we want to forecast is affected by factors **other than time**.

These are called **causal/explanatory variables**.

### Example from the PDF

A manufacturer of hospital equipment may forecast future sales using variables such as:

* hospital capital spending
* changes in the proportion of people aged 65 and above

So instead of saying:

> "Sales next year will depend mainly on previous sales."

we ask:

> "What other factors explain changes in sales?"

### Basic structure

```text
Economic factors ──────┐
Demographic factors ───┤
Other relevant factors ┤
                       ▼
                 Regression model
                       │
                       ▼
                  Sales forecast
```

Explanatory/causal models are often called **econometric models**.

Their purpose is to statistically identify factors that explain patterns in the variable being forecast.

**Regression analysis** is commonly used for this purpose. 

---

# Time-Series vs Causal Forecasting

| Time-Series                                     | Causal/Explanatory                          |
| ----------------------------------------------- | ------------------------------------------- |
| Mainly uses historical behavior of the variable | Uses other variables that influence it      |
| Focuses on patterns over time                   | Focuses on relationships between variables  |
| Example: past sales → future sales              | Advertising/income/economic factors → sales |
| Useful for short-range forecasting              | Often useful when external factors matter   |

### Instant comparison

Suppose you're forecasting car sales.

**Time-series approach:**

```text
Past car sales
      ↓
Identify pattern
      ↓
Future car sales
```

**Causal approach:**

```text
Income
Interest rates
Population
Advertising
Economic conditions
       ↓
   Regression
       ↓
Future car sales
```

---

# The Practice of Forecasting

In real businesses, forecasting is **not purely judgmental or purely statistical**.

Companies often use a combination of both.

The PDF notes that:

* simple time-series models are commonly used for **short- and medium-range forecasts**
* regression analysis is popular for **long-range forecasting**
* judgmental methods are also widely used
* managers frequently adjust quantitative forecasts using judgment 

---

# Why Statistical Forecasts May Need Judgment

A statistical model primarily learns from available data.

But business reality can contain events that historical data does not adequately represent.

Examples include:

* sales promotions
* unusual environmental disturbances
* new product introductions
* large one-time orders
* sudden market changes

### Example

Suppose historical data predicts:

```text
Next month's sales = ₹10 crore
```

But management knows:

> A major competitor is about to launch a much cheaper product.

Historical sales data may not fully capture this new situation.

Management may therefore adjust the statistical forecast.

```text
Statistical forecast
        │
        ▼
    ₹10 crore
        │
        +
Management knowledge
        │
        ▼
Adjusted final forecast
```

This is why the PDF states that managers may begin with a statistical forecast and then adjust it for **intangible/non-quantitative factors**. 

---

# Combining Judgmental and Quantitative Forecasts

There are two broad possibilities:

```text
              Forecasting
                  │
        ┌─────────┴─────────┐
        ▼                   ▼
 Quantitative           Judgmental
   forecast              forecast
        │                   │
        └─────────┬─────────┘
                  ▼
             Combine them
                  │
          ┌───────┴────────┐
          ▼                ▼
      Objective        Subjective
      combination      combination
       (e.g. average)   (managerial
                         judgment)
```

The forecasts can be combined:

* objectively, such as by averaging
* subjectively, using managerial judgment

The important point is that a company should determine whether the combination actually **improves forecast accuracy**.

---

# There Is No Universal "Best" Forecasting Method

This is a major conclusion from the PDF.

The best forecasting approach depends on factors such as:

* whether a trend exists
* whether seasonality exists
* how many data points are available
* how far into the future the forecast goes
* the experience and knowledge of the forecaster

### Decision framework

```text
                 Forecasting Problem
                         │
        ┌────────────────┼────────────────┐
        │                │                │
   Data available?   Trend/seasonality?  Horizon?
        │                │                │
        ▼                ▼                ▼
   Historical        Pattern in data   Short/long
     data?              matters         range
        │                │                │
        └────────────────┼────────────────┘
                         ▼
                 Select appropriate
                  forecasting method
```

---

# The Most Important 20/80 Takeaways

## Forecasting foundations

> **Forecasting = using available information to estimate future outcomes for decision-making.**

Three major approaches:

```text
Qualitative/Judgmental
        ↓
Experience + intuition + expert knowledge

Statistical Time-Series
        ↓
Historical data + patterns over time

Causal/Explanatory
        ↓
Other variables that explain the outcome
```

## Time-series components

```text
Random       → unpredictable fluctuations

Trend        → gradual upward/downward movement

Seasonality  → repeats at fixed intervals

Cyclical     → longer-term rises and falls
```

## Forecasting methods

```text
Stable/random series
        ↓
Moving Average / Simple Exponential Smoothing

Trend
        ↓
Double Exponential Smoothing

Seasonality
        ↓
Seasonal regression / Holt-Winters

Trend + Seasonality
        ↓
Holt-Winters

External influencing variables
        ↓
Regression / Causal model
```

## Error measures

```text
MAD
→ Average absolute error

MSE
→ Squares errors → strongly penalizes large errors

RMSE
→ √MSE → returns to original units

MAPE
→ Error expressed relative to actual value (%)
```

## Exponential smoothing

```text
Simple Exponential Smoothing
        ↓
Level-focused forecasting

Double Exponential Smoothing
        ↓
Level + Trend

Holt-Winters
        ↓
Level + Trend + Seasonality
```

## Holt-Winters

```text
Additive
→ seasonal variation roughly stable

Multiplicative
→ seasonal variation changes with the level
```

## Smoothing parameters

```text
α → Level
β → Trend
γ → Seasonality
```

## Final conceptual picture

```text
                         BUSINESS FORECASTING
                                  │
          ┌───────────────────────┼────────────────────────┐
          │                       │                        │
          ▼                       ▼                        ▼
   JUDGMENTAL               TIME-SERIES                 CAUSAL
          │                       │                        │
          │                ┌──────┼──────┐                 │
          │                │      │      │                 │
          ▼                ▼      ▼      ▼                 ▼
       Delphi          Random   Trend  Seasonal       External
       Experts                    │      │             variables
          │                       │      │                 │
     Indicators                   │      │            Regression
          │                       ▼      ▼
          │                Moving Avg  Holt-Winters
          │                           │
          │                       Trend +
          │                      Seasonality
          │
          └──────────────┬─────────────────────────────────┘
                         ▼
                Compare forecast accuracy
                         │
                         ▼
                 Adjust with judgment
                 when business reality
                 contains new information
```

**Core principle to remember:**

> **First understand the data and forecasting situation; then choose the method. There is no single forecasting technique that is best for every business problem.** 
---