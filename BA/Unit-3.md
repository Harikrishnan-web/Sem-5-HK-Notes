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