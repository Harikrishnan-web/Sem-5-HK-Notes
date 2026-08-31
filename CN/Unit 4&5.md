# Unit-4 Routing
## ROUTING AND PROTOCOLS

### Routing

**Routing** is the process of moving information from a **source to a destination** across an internetwork. Usually, at least one intermediary node/router is involved. 

```text
Source → Router → Router → Destination
```

A router checks its **routing table** when a packet needs to be forwarded. The table specifies the optimum path, which may be **static or dynamic**. Dynamic routing is important because routes need to be updated when network conditions change. 

### Routing Protocol

A **routing protocol** is a set of rules and procedures used by routers to:

* Exchange routing information
* Make routing decisions
* Update dynamic routing tables
* Combine information received from other routers 

### Routing Table

A routing table tells the router **where to forward a packet** to reach its destination.

```text
Packet arrives
      ↓
Check routing table
      ↓
Find best/optimum path
      ↓
Forward packet
```

---

## UNICAST ROUTING

In **unicast routing**, a router forwards a received packet through **only one of its ports/interfaces**. 

```text
             Router B
            /
Source → Router A ───→ Router C → Destination
            \
             Router D
```

Only **one path** is selected for forwarding the particular packet.

**Remember:**
**Unicast = One source → One destination**

---

### Metric

A **metric** is the cost assigned for passing through a network.

The total metric is the sum of the metrics of all networks/links in the route. The router chooses the route with the **smallest metric**. 

### Formula

$$
\boxed{\text{Total Metric}=\sum \text{Individual Link Metrics}}
$$

Example:

```text
A ──2── B ──3── C
```

$$
\text{Cost A→C}=2+3=5
$$

If another route has cost 8, the router chooses the route with cost **5**.

### Metrics used

| Routing Protocol | Metric / Criterion        |
| ---------------- | ------------------------- |
| **RIP**          | Hop count                 |
| **OSPF**         | Cost assigned to the link |
| **BGP**          | Policy                    |



---

### Autonomous System (AS)

An **Autonomous System** is a group of networks and routers under the control of a **single administration**. 

```text
       Autonomous System
   ┌─────────────────────┐
   │ R1 ── R2 ── R3      │
   │       │              │
   │       R4             │
   └─────────────────────┘
```

### Interior and Exterior Routing

**Interior routing**

* Routing **inside an AS**

**Exterior routing**

* Routing **between ASs** 

```text
      AS 1                    AS 2
  ┌──────────┐             ┌──────────┐
  │ R1─R2─R3 │ ←────────→  │ R4─R5─R6 │
  └──────────┘
       ↑                       ↑
   Interior                Interior
       
        ←── Exterior routing ──→
```

### Quick memory

**Interior = Inside one AS**
**Exterior = Between ASs**
---
## DISTANCE-VECTOR ROUTING

### ⭐ Basic idea

**Distance-Vector (DV) routing** is a routing method used to find the **best/least-cost route** to a destination.

Each router:

* Initially knows the cost to its **immediate neighbours**.
* Creates its own **least-cost information**.
* Exchanges this information with its neighbouring routers.
* Updates its routes using the information received.
* Repeats this process until the routing information becomes complete. 

### Simple flow

```text
Know immediate neighbours
          ↓
Create distance vector
          ↓
Send vector to neighbours
          ↓
Receive neighbours' vectors
          ↓
Calculate better routes
          ↓
Update distance vector
          ↓
Send updated vector
          ↓
Repeat
```

---

## BELLMAN-FORD EQUATION

### ⭐ Main idea

The **Bellman-Ford equation** is the heart of Distance-Vector routing. It is used to find the **least-cost/shortest distance** from a source to a destination through an intermediate neighbour. 

### ⭐ Formula

$$
\boxed{D_x(y)=\min_v\{c(x,v)+D_v(y)\}}
$$

### Meaning of symbols

| Symbol     | Meaning                                           |
| ---------- | ------------------------------------------------- |
| \(D_x(y)\) | Least cost from router **x** to destination **y** |
| \(v\)      | A neighbouring router                             |
| \(c(x,v)\) | Cost from **x to neighbour v**                    |
| \(D_v(y)\) | Cost from neighbour **v to destination y**        |
| min        | Select the **smallest** total cost                |

### 🧠 In simple words

> **My cost to destination = minimum [my cost to neighbour + neighbour's cost to destination].**

### Example

Suppose:

```text
       Cost = 2
   X ─────────→ V
                │
                │ Cost to Y = 5
                ↓
                Y
```

Then:

$$
D_X(Y)=2+5=7
$$

If another neighbour provides a route costing **4**, then:

$$
D_X(Y)=\min(7,4)=\boxed{4}
$$

So router X chooses the route with cost **4**.

---

## DISTANCE VECTORS

A **distance vector** is a **one-dimensional array** that represents the least-cost information from a router to different destinations. 

### Example

Suppose router A has:

| Destination | Least Cost |
| ----------- | ---------: |
| A           |          0 |
| B           |          2 |
| C           |          5 |
| D           |          ∞ |
| E           |          4 |

Here:

* **0** → cost to itself
* **2, 5, 4** → known least costs
* **∞** → destination currently unreachable/unknown

### How vectors are exchanged

```text
        Distance Vector
              ↓
        ┌───────────┐
        │ Router A  │
        └───────────┘
          ↙       ↘
         ↓         ↓
     Router B   Router C
         ↘       ↙
          Updated
       information
```

Each router sends a copy of its distance vector to its **immediate neighbours**. When a neighbour receives it, it recalculates its own distances using the Bellman-Ford equation. 

### ⭐ Update rule

$$
\boxed{D_x(y)=\min_v[c(x,v)+D_v(y)]}
$$

If the calculated distance is **better**, the router updates its vector.

If the vector changes, the updated vector is sent to the neighbouring routers. 

---

### ⭐ Exam answer — short version

**Distance-Vector Routing:**
A routing technique in which each router maintains a distance vector containing the least-cost routes to destinations. Routers exchange their vectors with immediate neighbours and update their routing information using the Bellman-Ford equation.

**Bellman-Ford:**

$$
D_x(y)=\min_v[c(x,v)+D_v(y)]
$$

**Distance Vector:**
A one-dimensional array representing the least-cost paths from one router to different destinations. 
---
## Example Problem – Distance Vector Routing

Consider the following network:

```text
       2
   A ------- B
   |         |
 1 |         | 3
   |         |
   C ------- D
       2
```

**Find the least-cost path from A to D using Distance Vector Routing.**

### Step 1: Initial Distance Vector of A

A knows the cost to its direct neighbours:

| Destination | Cost |
| ----------- | ---: |
| A           |    0 |
| B           |    2 |
| C           |    1 |
| D           |    ∞ |

A does not have a direct connection to D, so the cost is initially **∞**.

---

### Step 2: Find path through B

Using B:

$$
A \rightarrow B \rightarrow D
$$

$$
= Cost(A,B) + Cost(B,D)
$$

$$
= 2 + 3 = 5
$$

So:

$$
D_A(D)=5
$$

---

### Step 3: Find path through C

Using C:

$$
A \rightarrow C \rightarrow D
$$

$$
= Cost(A,C) + Cost(C,D)
$$

$$
= 1 + 2 = 3
$$

So:

$$
D_A(D)=3
$$

---

### Step 4: Choose the minimum

Compare both possible paths:

$$
\min(5,3)=3
$$

Therefore:

```text
A → C → D
```

is the shortest path.

### Final Distance Vector of A

| Destination | Minimum Cost | Next Hop |
| ----------- | -----------: | -------- |
| A           |            0 | —        |
| B           |            2 | B        |
| C           |            1 | C        |
| D           |        **3** | **C**    |

### ⭐ Final Answer

**Shortest path:** A → C → D

**Minimum cost:**

$$
\boxed{3}
$$

### 🧠 Formula to use in DVR problems

$$
\boxed{D_x(y)=\min_v\{c(x,v)+D_v(y)\}}
$$

In simple words:

**Cost to destination = Cost to neighbour + Neighbour's cost to destination → choose the minimum.**
---