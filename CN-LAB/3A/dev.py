import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


months = np.array(["Jan", "Feb", "Mar", "Apr", "May", "Jun"])
sales = np.array([225, 100, 200, 150, 125, 175])


df = pd.DataFrame({
    "Months": months,
    "Sales": sales
})


print("DataFrame:")
print(df)


print("\nFirst 5 Rows:")
print(df.head())


print("\nSummary Statistics:")
print(df.describe())


plt.figure(figsize=(6, 4))
plt.plot(df["Months"], df["Sales"], marker="o", color="blue", linewidth=2)
plt.title("Monthly Sales - Line Plot")
plt.xlabel("Months")
plt.ylabel("Sales")
plt.grid(True)
plt.show()

plt.figure(figsize=(6, 4))
plt.bar(df["Months"], df["Sales"], color="skyblue", alpha=0.6, label="Sales")
plt.plot(df["Months"], df["Sales"], marker="o", color="red", linewidth=2, label="Trend")
plt.title("Monthly Sales - Line and Bar Chart")
plt.xlabel("Months")
plt.ylabel("Sales")
plt.legend()
plt.grid(True)
plt.show()


plt.figure(figsize=(6, 6))
plt.pie(
    df["Sales"],
    labels=df["Months"],
    autopct="%1.1f%%",
    startangle=90,
    shadow=True
)
plt.title("Monthly Sales Distribution")
plt.axis("equal")
plt.show()


plt.figure(figsize=(6, 4))
plt.scatter(df["Months"], df["Sales"], color="green", s=100)
plt.title("Monthly Sales - Scatter Plot")
plt.xlabel("Months")
plt.ylabel("Sales")
plt.grid(True)
plt.show()