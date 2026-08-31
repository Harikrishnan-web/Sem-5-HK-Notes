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
