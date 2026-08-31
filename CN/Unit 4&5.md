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
# ROUTING INFORMATION PROTOCOL (RIP)

## ⭐ Definition

**RIP (Routing Information Protocol)** is an **intradomain routing protocol** based on the **Distance-Vector routing algorithm**.

It is used by routers to determine the best route to a destination network.

---

## ⭐ Main idea

RIP uses **hop count** as its routing metric.

> **Hop = one router crossed by the packet.**

The route with the **smallest number of hops** is selected.

### Example

```text
A → R1 → R2 → R3 → Destination
```

Number of router hops = **3**

If another route is:

```text
A → R4 → R5 → Destination
```

Number of hops = **2**

RIP chooses the **second route** because it has fewer hops.

---

## ⭐ RIP Routing Table

A RIP forwarding table contains **3 main columns**:

| Destination Network | Next Router | Cost |
| ------------------- | ----------- | ---: |
| Network A           | R2          |    2 |
| Network B           | R3          |    3 |
| Network C           | R4          |    1 |

### Meaning

**1. Destination Network**
The network where the destination host is located.

**2. Next Router**
The next router to which the packet should be forwarded.

**3. Cost**
The number of **hops** required to reach the destination.

---

## RIP Working

```text
Router starts
     ↓
Creates routing information
     ↓
Exchanges information with neighbours
     ↓
Receives routing updates
     ↓
Counts hops to destinations
     ↓
Chooses route with minimum hops
     ↓
Updates routing table
```

### Simple example

```text
       1          1
A ───────── R1 ───────── R2
                         |
                         | 1
                         |
                         D
```

From A to D:

$$
A \rightarrow R1 \rightarrow R2 \rightarrow D
$$

RIP determines the route based on the **number of hops**.

---

## ⭐ RIP Versions

RIP has two versions:

* **RIP-1**
* **RIP-2**

**RIP-2** is backward compatible with RIP-1 and allows additional information to be carried in RIP messages.

---

## ⭐ RIP Messages

RIP has **two main types of messages**:

### 1. Request Message

A router sends a **request** when:

* The router has just started.
* It has routing entries that have timed out.

A request can ask for:

* Specific routing entries
* All routing entries

---

### 2. Response / Update Message

A response contains routing information.

It can be:

**Solicited response**

* Sent in response to a request.

**Unsolicited response**

* Sent without receiving a specific request.
* Used for routing updates.

### Flow

```text
Router A                         Router B
   |                                |
   |-------- Request -------------->|
   |                                |
   |<------- Response --------------|
   |                                |
   |------ Routing Update --------->|
```

---

## ⭐ Important Points to Remember

| Feature       | RIP                            |
| ------------- | ------------------------------ |
| Full form     | Routing Information Protocol   |
| Type          | Intradomain                    |
| Algorithm     | Distance Vector                |
| Metric        | **Hop count**                  |
| Routing table | Destination, Next Router, Cost |
| Versions      | RIP-1, RIP-2                   |
| Messages      | Request, Response/Update       |

---

## 🧠 30-Second Revision

**RIP = Distance Vector + Hop Count**

Remember:

```text
RIP
 ↓
Distance Vector
 ↓
Count Hops
 ↓
Choose Minimum-Hop Route
```

**Most important exam points:**

* RIP is an **intradomain** routing protocol.
* It uses **Distance-Vector routing**.
* Its metric is **hop count**.
* Its forwarding table has **destination network, next router and cost**.
* It has **RIP-1 and RIP-2**.
* It uses **Request and Response/Update messages**.
---
## LINK-STATE ROUTING

### ⭐ Basic idea

**Link-State Routing** is a routing method in which each router builds a **complete map of the network** by learning the state and cost of links.

* Lower-cost links are preferred.
* Cost **∞** means the link is broken or does not exist.
* Each router creates its own **least-cost tree**. 

```text
Network information
       ↓
   LS Database
       ↓
  Dijkstra Algorithm
       ↓
 Least-cost tree
       ↓
 Routing table
```

---

## LINK-STATE DATABASE (LSDB)

**LSDB** = collection of information about the **state/cost of all links** in the network.

Each router sends greeting messages to its immediate neighbours to learn:

* Neighbour's identity
* Cost of the link

This information forms the **Link-State Packet (LSP)**. 

---

## FLOODING

After a router obtains link-state information, it sends that information through its links.

```text
        B
       ↗ ↘
      /   \
     A ─── C
```

The next router forwards the information through its other links, **except the link from which it received the information**. 

### Simple flow

```text
Router creates LSP
       ↓
Sends to neighbours
       ↓
Neighbour receives LSP
       ↓
Forwards to other links
       ↓
All routers receive information
       ↓
LSDB is formed
```

---

# DIJKSTRA ALGORITHM

Once the LSDB is available, each router uses **Dijkstra's algorithm** to create its **least-cost tree**. 

### ⭐ Main steps

**Step 1 — Select root**

The router itself becomes the **root** of the tree.

Set its cost as:

$$
\boxed{0}
$$

Set the initial costs of other nodes according to the LSDB.

---

**Step 2 — Select lowest-cost node**

Among nodes not yet in the tree, select the node having the **smallest cost**.

Add it to the tree.

---

**Step 3 — Update costs**

After adding the node, check whether the paths to other nodes can be improved through this newly added node.

If a smaller cost is found, update it.

---

**Step 4 — Repeat**

Continue selecting the lowest-cost node and updating costs until **all nodes are added**. 

---

## ⭐ Dijkstra Algorithm — Exam Flowchart

```text
          START
            ↓
   Select yourself as root
            ↓
     Set initial costs
            ↓
 Select lowest-cost node
            ↓
      Add to tree
            ↓
    Update other costs
            ↓
  All nodes added?
       ↙       ↘
     NO         YES
     ↓           ↓
   Repeat    Least-cost tree
                 ↓
                END
```

---

# FORWARD SEARCH ALGORITHM

The notes give a more detailed version of Dijkstra using **Confirmed** and **Tentative** lists. 

### Steps

1. Put yourself in the **Confirmed list** with cost **0**.
2. Select the LSP of the newly confirmed node (**Next**).
3. For every neighbour of Next:

   * Calculate its cost.
   * If it is not in either list → add it to **Tentative**.
   * If it is already Tentative and the new cost is smaller → **update it**.
4. Select the **lowest-cost entry** from Tentative.
5. Move it to Confirmed.
6. Repeat until the Tentative list is empty. 

### Cost calculation

$$
\boxed{\text{New Cost}=\text{Cost to Next}+\text{Cost from Next to Neighbour}}
$$

---

## 🧠 Ultra-short revision

```text
LINK STATE
     ↓
Learn link information
     ↓
Create LSP
     ↓
Flood LSP
     ↓
Build LSDB
     ↓
Run Dijkstra
     ↓
Create least-cost tree
```

### ⭐ Must remember

* **LSDB** → complete link-state information
* **LSP** → information about neighbour + link cost
* **Flooding** → distributes link-state information
* **Dijkstra** → creates least-cost tree
* **Lower cost = preferred route**
* **∞ = broken/non-existent link** 
---
## OPEN SHORTEST PATH FIRST (OSPF)

### Definition

**OSPF (Open Shortest Path First)** is an **intradomain routing protocol** based on **Link-State Routing**.

### Working

```text
Discover neighbours
       ↓
Exchange link-state information
       ↓
Build LSDB
       ↓
Run Dijkstra algorithm
       ↓
Find least-cost paths
       ↓
Update routing table
```

### Metric

OSPF assigns a **cost/weight** to each link.

The cost can be based on:

* Throughput
* Round-trip time
* Reliability
* Hop count, if the administrator chooses it

Different service types can also have different costs. 

### Link-State Advertisement

OSPF requires routers to **advertise the state of their links** to neighbours so that the LSDB can be formed. 

### ⭐ OSPF Messages

| Type | Message                        | Purpose                                  |
| ---- | ------------------------------ | ---------------------------------------- |
| 1    | **Hello**                      | Introduces router to neighbours          |
| 2    | **Database Description**       | Normally sent in response to Hello       |
| 3    | **Link-State Request**         | Requests specific link-state information |
| 4    | **Link-State Update**          | Main message used to build LSDB          |
| 5    | **Link-State Acknowledgement** | Provides reliability                     |



### 🧠 Remember

**H-D-R-U-A**

> **H**ello → **D**atabase Description → **R**equest → **U**pdate → **A**cknowledgement

### Other features

* Authentication of routing messages
* Routing areas for hierarchy
* Load balancing using multiple equal-cost routes 

### Important fact

OSPF operates at the **network layer** and uses IP for propagation. Its IP protocol field value is **89**. 

---

# PATH-VECTOR ROUTING

### Definition

**Path-Vector Routing** is a routing algorithm used for **interdomain routing**.

Its principle is similar to Distance Vector, but instead of advertising only the metric, it advertises the **complete path**. 

### Example

```text
AS1 → AS2 → AS3 → AS4
```

The routing information can contain:

```text
Destination: AS4
Path: AS1 → AS2 → AS3 → AS4
```

---

### Speaker Node

Each Autonomous System has a router that acts on behalf of the entire AS, called a **Speaker Node**.

```text
       AS1             AS2
   ┌─────────┐     ┌─────────┐
   │ Speaker │─────│ Speaker │
   └─────────┘     └─────────┘
        │               │
       AS3 ─────────── AS4
```

The speaker node:

1. Creates its routing information.
2. Advertises the **path** to neighbouring ASs.
3. Receives paths from neighbouring ASs.
4. Shares the information with its neighbours. 

---

### ⭐ Functions

**1. Loop prevention**

When a router receives a path, it checks whether its own AS is already present in that path.

If yes → a loop would occur → the path is ignored. 

**2. Policy routing**

A router can check the advertised path against its routing policy and reject paths that do not satisfy the policy. 

**3. Optimum path**

The selected path can be the best path according to the **organization's requirements/policy**. 

### 🧠 Remember

**Distance Vector → tells distance**
**Path Vector → tells path**

---

# BGP – BORDER GATEWAY PROTOCOL VERSION 4 (BGP4)

### ⭐ Definition

**BGP4 (Border Gateway Protocol Version 4)** is the **interdomain routing protocol used on the Internet**.

It is based on the **Path-Vector algorithm** and provides information about the reachability of networks. 

### Why BGP is needed

BGP exchanges routing information between different organizations/ISPs.

Unlike protocols that simply search for the shortest path, BGP considers **routing policies** and agreements between organizations. 

```text
        Internet
           |
   ┌───────┴───────┐
   ↓               ↓
  ISP 1 ←──BGP──→ ISP 2
   │               │
   ↓               ↓
 Network A       Network B
```

### ⭐ BGP Working

```text
BGP Speaker
    ↓
Advertises network/path
    ↓
Neighbouring AS
    ↓
Checks path + policy
    ↓
Accepts / rejects route
    ↓
Updates routing information
```

BGP routers are configured according to routing policies, and neighbouring ISPs use **peering agreements** for exchanging traffic information. 

---

## ⭐ BGP Messages

BGP uses **4 types of messages**:

| Message          | Purpose                                       |
| ---------------- | --------------------------------------------- |
| **OPEN**         | Establishes a relationship with a neighbour   |
| **UPDATE**       | Announces new routes or withdraws routes      |
| **KEEPALIVE**    | Maintains/confirms the connection             |
| **NOTIFICATION** | Reports an error and can close the connection |



### 🧠 Memory trick

**O-U-K-N**

> **O**pen → **U**pdate → **K**eepalive → **N**otification

---

## ⭐ Quick Revision

| Topic                     | Remember                                                |
| ------------------------- | ------------------------------------------------------- |
| **OSPF**                  | Intradomain + Link State + Dijkstra                     |
| **OSPF metric**           | Link cost/weight                                        |
| **OSPF messages**         | Hello, DB Description, Request, Update, Acknowledgement |
| **Path Vector**           | Interdomain + advertises path                           |
| **Speaker Node**          | Represents an AS                                        |
| **Path Vector functions** | Loop prevention + Policy routing                        |
| **BGP4**                  | Interdomain + Path Vector                               |
| **BGP messages**          | OPEN, UPDATE, KEEPALIVE, NOTIFICATION                   |
---
# MULTICAST ROUTING

### Definition

**Multicast routing** is **one-to-many communication**.

* One **source**
* Multiple destinations belonging to a **multicast group**
* Source uses a unicast address.
* Destination uses a **group address** representing the interested receivers. 

```text
             Receiver
                ↑
                |
Source ──── Router
             ↙   ↘
        Receiver  Receiver
```

### Two approaches

| Approach              | Main idea                                 | Number of trees |
| --------------------- | ----------------------------------------- | --------------: |
| **Source-Based Tree** | Separate tree for every source-group pair |           m × n |
| **Group-Shared Tree** | One tree for each multicast group         |               m |

### Source-Based Tree

The **source is the root** and group members are the leaves.

```text
          Source
         /      \
        R1       R2
       /          \
 Receiver       Receiver
```

If there are **m groups** and **n sources**:

$$
\boxed{m\times n\text{ trees}}
$$

### Group-Shared Tree

A **core/rendezvous point (RP)** acts as a representative source for the group.

```text
             Receiver
                ↑
                |
Source → Core/RP
                |
          ┌─────┴─────┐
          ↓           ↓
      Receiver    Receiver
```

Only **m trees** are needed for m groups. 

---

# DVMRP

### Definition

**DVMRP (Distance Vector Multicast Routing Protocol)** is an extension of **RIP** used for multicast routing.

It uses the **source-based tree approach**. 

### ⭐ Three main steps

```text
Multicast packet
       ↓
     RPF
       ↓
     RPB
       ↓
     RPM
       ↓
Multicast tree
```

### 1. Reverse Path Forwarding — RPF

RPF ensures that a multicast packet is accepted only if it arrives through the interface that lies on the **shortest path back to the source**.

The router checks its **unicast forwarding table** to determine this interface. 

```text
        Source
        ↓
       R1
        ↓
       R2
      /  \
     ↓    ↓
   R3    R4
```

R2 accepts the packet only from the interface corresponding to its reverse shortest path toward the source.

---

### 2. Reverse Path Broadcasting — RPB

RPF may cause duplicate copies on a network.

**RPB creates a broadcast/spanning tree** and removes branches that cause cycles, so each network receives only one copy. 

---

### 3. Reverse Path Multicasting — RPM

RPB still broadcasts to networks that may have **no interested multicast members**.

RPM removes/prunes branches that do not lead to active group members.

```text
Broadcast Tree
      ↓
Remove unnecessary branches
      ↓
Multicast Tree
```

Pruning can be performed **bottom-up**, from leaves toward the root. 

### ⭐ DVMRP memory

**RPF → RPB → RPM**

> **Forward correctly → Build tree → Prune unnecessary branches**

---

# PIM

### Definition

**PIM (Protocol Independent Multicast)** is a multicast routing protocol that uses the **forwarding table created by a unicast routing protocol**.

The unicast protocol can be either:

* Distance Vector
* Link State

PIM does not care how that forwarding table was created. 

### Two modes

```text
              PIM
             /   \
            ↓     ↓
         PIM-DM  PIM-SM
          Dense   Sparse
```

---

## PIM-DM — Dense Mode

Used when **many routers have attached multicast members**.

* Uses **source-based tree**
* Similar to DVMRP but simpler
* Uses **RPF + RPM**
* First packet is broadcast to all networks.
* Routers without members send **prune** messages.
* Later packets are sent only to required networks. 

```text
First packet
     ↓
Broadcast everywhere
     ↓
No-member routers
     ↓
PRUNE
     ↓
Future packets → only required branches
```

---

## PIM-SM — Sparse Mode

Used when **only a small number of routers have multicast members**.

It uses a **group-shared tree**.

The central router is called the:

$$
\boxed{\text{Rendezvous Point (RP)}}
$$



### Working

```text
           RP
          /  \
         /    \
    Receiver  Receiver
```

* Receivers **join** the multicast group.
* **Join** messages add branches.
* **Prune** messages remove unnecessary branches. 

---

## ⭐ DVMRP vs PIM

| Feature                          | DVMRP                                      | PIM                                       |
| -------------------------------- | ------------------------------------------ | ----------------------------------------- |
| Full form                        | Distance Vector Multicast Routing Protocol | Protocol Independent Multicast            |
| Approach                         | Source-based                               | Dense: source-based; Sparse: group-shared |
| Depends on unicast routing table | DVMRP itself extends RIP                   | **Yes**                                   |
| Main techniques                  | RPF, RPB, RPM                              | RPF, RPM / Join, Prune                    |
| Modes                            | —                                          | **PIM-DM, PIM-SM**                        |
| Sparse multicast                 | —                                          | **PIM-SM uses RP**                        |

## 🧠 1-Minute Revision

**Multicast:**
**1 source → many receivers**

**Two trees:**

* Source-based → **m × n**
* Group-shared → **m**

**DVMRP:**

$$
\boxed{RPF\rightarrow RPB\rightarrow RPM}
$$

**PIM:**

* **PIM-DM** → many members → broadcast first → prune
* **PIM-SM** → few members → **RP** → Join/Prune
---
