# Unit-3 Bussiness forecasting
# 3.1 Introduction to Business Forecasting

Business forecasting helps managers **predict future values/events for decision-making**.

The appropriate forecasting method depends mainly on:

* **Forecast time horizon**
* **Information available**
* Characteristics of the variable being forecast

### Major forecasting approaches

```text
Business Forecasting
       │
       ├── Qualitative / Judgmental
       ├── Statistical Time-Series
       └── Explanatory / Causal
```

---

## 3.1.1 Qualitative and Judgmental Forecasting

Used when:

* Historical data is **not available**
* Forecasting **far into the future**
* Expert knowledge or intuition is important
* Nonquantitative factors must be considered, such as **government regulations or competitor actions**

Examples include:

* Manager's opinion
* Jury of executive opinion
* Historical analogy
* **Delphi method**

### Delphi Method

A structured method using a **panel of experts** whose identities are usually kept confidential from one another.

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

---

## Indicators and Indexes

**Indicators** are measures believed to influence the variable being forecast.

By monitoring changes in indicators, analysts gain clues about the variable's future behavior.

### Example: Leading Economic Indicators

The Index of Leading Indicators uses measures such as:

* Manufacturing weekly hours
* Initial unemployment claims
* New orders for consumer goods/materials
* Vendor delivery performance
* New orders for nondefense capital goods
* Building permits
* Stock prices
* Money supply
* Interest-rate spread
* Consumer expectations

**Core idea:**

> Changes in an indicator can provide an early signal about future economic performance.

---

# 3.1.2 Statistical Forecasting Models

Statistical time-series models are especially useful for **short-range forecasting**.

## Time Series

A **time series** is a sequence of historical observations recorded over time.

Example:

```text
Weekly sales:
Week 1 → Week 2 → Week 3 → Week 4 → ...
```

Time-series forecasting assumes that forces affecting the recent past will continue into the near future, so past data is **extrapolated** to predict future values.

### Main components

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

Example: sales steadily increasing over several years.

### Seasonal Effect

A pattern that **repeats at fixed intervals** such as:

* Year
* Month
* Week
* Day

Example: grocery-store sales may be higher every weekend.

### Cyclical Effect

Longer-term rises and falls, typically over **several years**.

### Stationary Time Series

A time series with:

* No trend
* No seasonal effect
* No cyclical effect
* Relatively constant behavior
* Mainly random variation

---

# 3.1.3 Moving Average Models

A **simple moving average** smooths random fluctuations by averaging recent observations to reveal the underlying direction of the series.

**Basic idea:**

```text
Recent observations
        ↓
     Average
        ↓
Smoothed forecast
```

The number of observations included in the average is **k**.

Different values of **k** produce different forecasts.

---

## Error Metrics and Forecast Accuracy

Forecast accuracy is evaluated by comparing:

**Actual value vs Forecast value**

Common error measures:

### Mean Absolute Deviation (MAD)

Average of the absolute forecast errors:

[
MAD=\frac{\sum |A_t-F_t|}{n}
]

Where:

* (A_t) = actual value
* (F_t) = forecast
* (n) = number of forecast values

**MAD is less affected by extreme observations.**

---

### Mean Square Error (MSE)

Average of squared forecast errors:

[
MSE=\frac{\sum(A_t-F_t)^2}{n}
]

Because errors are squared, **large errors are penalized more heavily**.

---

### Root Mean Square Error (RMSE)

Square root of MSE:

[
RMSE=\sqrt{MSE}
]

Unlike MSE, RMSE is expressed in the **same units as the original data**.

---

### Mean Absolute Percentage Error (MAPE)

Average absolute error relative to the actual value:

[
MAPE=\frac{1}{n}\sum\left|\frac{A_t-F_t}{A_t}\right|\times100
]

Because it removes the measurement scale, MAPE is useful for **relative comparison**.

### Choosing an error measure

* **MAD** → less affected by extreme observations
* **MSE** → strongly penalizes large errors
* **RMSE** → same units as the original data
* **MAPE** → scale-free relative comparison

MAD and MSE depend on the measurement scale, so their values are mainly meaningful when **comparing models for the same data**.

There is **no universal agreement** on one best error measure.

---

# 3.1.4 Exponential Smoothing Models

## Simple Exponential Smoothing

A useful method for **short-range forecasting**.

[
F_{t+1}=\alpha A_t+(1-\alpha)F_t
]

Where:

* (F_{t+1}) = forecast for next period
* (F_t) = current forecast
* (A_t) = actual value
* (\alpha) = smoothing constant, (0<\alpha<1)

Initial setup:

[
F_1=F_2=A_1
]

### Meaning

The next forecast is a **weighted combination of the previous forecast and the latest actual observation**.

* Higher (\alpha) → more weight on recent actual data
* Lower (\alpha) → more weight on the previous smoothed forecast

The forecast therefore reflects **all past data with decreasing weights**.

---

## Double Exponential Smoothing

Used when the time series has a **trend**.

It smooths two components:

* **Level**
* **Trend**

Higher (\alpha) gives more weight to the current observed value; lower (\alpha) gives more weight to the smoothed prediction.

The trend estimate is based on changes in the estimated levels over time.

**Simple distinction:**

```text
Simple exponential smoothing → Level
Double exponential smoothing → Level + Trend
```

---

# Forecasting Time Series with Seasonality

When seasonality exists, specialized methods can provide better forecasts.

## Regression-Based Seasonal Forecasting

Linear regression can be used with **categorical variables** to represent seasonal periods.

---

## Holt-Winters Forecasting

Holt-Winters methods use smoothing constants to handle **level and seasonal patterns**.

For seasonality **without trend**, a Holt-Winters method can be used.

For seasonality **with trend**, Holt-Winters combines both components.

### Holt-Winters types

| Model              | Suitable when                                                       |
| ------------------ | ------------------------------------------------------------------- |
| **Additive**       | Seasonal variation is relatively stable                             |
| **Multiplicative** | Seasonal variation increases/decreases with the level of the series |

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

The three smoothing parameters are used for:

* **Level**
* **Trend**
* **Seasonality**

A chart of the time series should be examined first to choose the appropriate model.

---

# Regression Forecasting with Causal Variables

Sometimes variables other than time influence the value being forecast.

Examples:

* Economic indexes
* Demographic factors
* Hospital capital spending
* Percentage of population over age 65

These are **explanatory/causal models**, also called **econometric models**.

They usually use regression analysis to identify factors that statistically explain the forecast variable.

```text
Causal variables
      ↓
Regression analysis
      ↓
Explain relationship
      ↓
Forecast outcome
```

---

# The Practice of Forecasting

In practice, businesses use **both judgmental and quantitative methods**.

* Simple time-series models → commonly used for **short- and medium-range** forecasts
* Regression analysis → commonly used for **long-range** forecasting
* Judgmental methods → frequently used alongside quantitative forecasts

Statistical models may miss factors such as:

* Sales promotions
* Unusual environmental events
* New product introductions
* Large one-time orders
* Sudden changes in trends

Therefore, managers may:

```text
Statistical forecast
        ↓
Managerial judgment
        ↓
Adjusted forecast
```

Or they may create separate judgmental and statistical forecasts and combine them.

### No single best forecasting method

The appropriate method depends on:

* Presence/absence of **trend**
* Presence/absence of **seasonality**
* Amount of available data
* Forecast horizon
* Forecaster's experience and knowledge

Quantitative methods may miss sudden trend reversals, while qualitative methods—especially those using **indicators**—may identify such changes.

---

## Quick Revision Map

```text
BUSINESS FORECASTING
│
├── Qualitative / Judgmental
│   ├── Expert opinion
│   ├── Historical analogy
│   ├── Delphi
│   └── Indicators / Indexes
│
├── Statistical Time Series
│   ├── Stationary
│   ├── Trend
│   ├── Seasonal
│   ├── Cyclical
│   ├── Moving Average
│   └── Exponential Smoothing
│       ├── Simple → Level
│       └── Double → Level + Trend
│
└── Explanatory / Causal
    └── Regression + causal variables
```

### Forecast-error formulas to remember

[
\boxed{MAD=\frac{\sum|A_t-F_t|}{n}}
]

[
\boxed{MSE=\frac{\sum(A_t-F_t)^2}{n}}
]

[
\boxed{RMSE=\sqrt{MSE}}
]

[
\boxed{MAPE=\frac{1}{n}\sum\left|\frac{A_t-F_t}{A_t}\right|\times100}
]

[
\boxed{F_{t+1}=\alpha A_t+(1-\alpha)F_t}
]
---