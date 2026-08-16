import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from scipy.stats import pearsonr, ttest_ind
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score

df = pd.read_csv("2015_16_Statewise_Elementary.csv")

print("Dataset Shape:", df.shape)
print("\nFirst 5 Rows:")
print(df.head())

df.columns = (
    df.columns
    .str.strip()
    .str.lower()
    .str.replace(" ", "_")
    .str.replace("-", "_")
)

print("\nColumns:")
print(df.columns.tolist())

print("\nMissing Values:")
print(df.isnull().sum())

numeric_df = df.select_dtypes(include=np.number)

print("\nDescriptive Statistics:")
print(numeric_df.describe())

correlation = numeric_df.corr()

print("\nCorrelation Matrix:")
print(correlation.round(2))

plt.figure(figsize=(12, 8))
sns.heatmap(correlation, annot=True, cmap="coolwarm", fmt=".2f")
plt.title("Education Quality Correlation")
plt.tight_layout()
plt.show()

teacher_columns = [
    col for col in df.columns
    if "teacher" in col or "ratio" in col
]

print("\nTeacher Related Variables:")
print(teacher_columns)

for col in teacher_columns[:4]:
    plt.figure(figsize=(8, 5))
    plt.hist(df[col].dropna(), bins=20)
    plt.title(col)
    plt.xlabel(col)
    plt.ylabel("Frequency")
    plt.tight_layout()
    plt.show()

for col in numeric_df.columns[:6]:
    plt.figure(figsize=(8, 5))
    plt.hist(df[col].dropna(), bins=20)
    plt.title(col)
    plt.xlabel(col)
    plt.ylabel("Frequency")
    plt.tight_layout()
    plt.show()

if numeric_df.shape[1] >= 3:

    target = numeric_df.columns[-1]
    features = list(numeric_df.columns[:-1])

    regression_data = numeric_df[features + [target]].dropna()

    X = regression_data[features]
    y = regression_data[target]

    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.20, random_state=42
    )

    model = LinearRegression()
    model.fit(X_train, y_train)

    y_pred = model.predict(X_test)

    r2 = r2_score(y_test, y_pred)
    mse = mean_squared_error(y_test, y_pred)
    rmse = np.sqrt(mse)

    print("\nRegression Analysis:")
    print("Target:", target)
    print("R2 Score:", round(r2, 3))
    print("MSE:", round(mse, 3))
    print("RMSE:", round(rmse, 3))

    coefficients = pd.DataFrame({
        "Variable": features,
        "Coefficient": model.coef_
    })

    print("\nRegression Coefficients:")
    print(coefficients)

    plt.figure(figsize=(8, 5))
    plt.scatter(y_test, y_pred)
    plt.xlabel("Actual Values")
    plt.ylabel("Predicted Values")
    plt.title("Actual vs Predicted")
    plt.tight_layout()
    plt.show()

if numeric_df.shape[1] >= 2:

    variable1 = numeric_df.columns[0]
    variable2 = numeric_df.columns[1]

    test_data = numeric_df[[variable1, variable2]].dropna()

    correlation_value, p_value = pearsonr(
        test_data[variable1],
        test_data[variable2]
    )

    print("\nHypothesis Testing:")
    print("Variable 1:", variable1)
    print("Variable 2:", variable2)
    print("Correlation:", round(correlation_value, 3))
    print("P-value:", round(p_value, 4))

    if p_value < 0.05:
        print("Reject H0: Significant relationship exists.")
    else:
        print("Fail to reject H0: No significant relationship found.")

    median_value = test_data[variable1].median()

    group1 = test_data[
        test_data[variable1] <= median_value
    ][variable2]

    group2 = test_data[
        test_data[variable1] > median_value
    ][variable2]

    t_stat, t_pvalue = ttest_ind(
        group1,
        group2,
        equal_var=False
    )

    print("\nT-Test:")
    print("T-statistic:", round(t_stat, 3))
    print("P-value:", round(t_pvalue, 4))

    if t_pvalue < 0.05:
        print("Reject H0: Groups are significantly different.")
    else:
        print("Fail to reject H0: Groups are not significantly different.")

print("\nStrongest Correlations:")

corr_pairs = correlation.unstack().sort_values(ascending=False)
corr_pairs = corr_pairs[corr_pairs < 0.999]

print(corr_pairs.head(10))

print("\nProject Title:")
print("Education Quality in India")