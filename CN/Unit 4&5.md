# Unit-4 Routing
## Routing and Protocols

**Routing** is the network-layer process of selecting paths across a network to forward traffic from a source to a destination node. Network routers rely on dynamic algorithms to discover paths, exchange topology state with neighbors, and build local forwarding tables.

```
                     Routing Protocols                       
                               |
               +---------------+---------------+
               |                               |
       Intra-Domain Routing           Inter-Domain Routing
     (Within an Autonomous System)    (Between Autonomous Systems)
               |                               |
    +----------+----------+                    |
    |                     |                    |
Distance Vector       Link State          Path Vector
  (e.g., RIP)        (e.g., OSPF)         (e.g., BGP)

```

### Domain Architecture

* **Autonomous System (AS):** A collection of routers under a single administrative authority.
* **Intra-Domain Routing:** Routing algorithms used strictly within a single Autonomous System (Interior Gateway Protocols - IGP).
* **Inter-Domain Routing:** Routing algorithms used between separate Autonomous Systems (Exterior Gateway Protocols - EGP).

---

## Unicast Routing

**Unicast Routing** delivers a network datagram from one specific source host to one specific destination host. The primary objective is **least-cost routing**—finding the optimal path according to a chosen metric like hop count, bandwidth, delay, or administrative cost.

### Core Unicast Routing Approaches

#### Distance Vector Routing

* Routers periodically share their entire routing table only with direct neighbors.
* Uses the **Bellman-Ford Algorithm** to compute shortest paths.
* Uses **Hop Count** as its primary metric.
* Example: **RIP (Routing Information Protocol)**, which has a maximum hop limit of 15 and sends periodic updates every 30 seconds.
* Vulnerable to slow convergence and the **Count-to-Infinity problem** (mitigated using Split Horizon or Poison Reverse).

#### Link State Routing

* Every router floods state information about its direct links (Link State Advertisements) to all routers in the domain.
* Every router constructs a complete topology map of the network.
* Uses **Dijkstra’s Algorithm** to compute the shortest-path tree with itself as the root.
* Example: **OSPF (Open Shortest Path First)**, which features fast convergence and uses event-triggered updates instead of periodic table dumps.

#### Path Vector Routing

* Designed for inter-domain routing where policy overrides pure distance metrics.
* Advertises the complete list of Autonomous Systems along a path to prevent loops.
* Example: **BGP (Border Gateway Protocol)**.

---

### Key Comparison

| Feature | Distance Vector | Link State | Path Vector |
| --- | --- | --- | --- |
| **Domain Scope** | Intra-Domain | Intra-Domain | Inter-Domain |
| **Algorithm** | Bellman-Ford | Dijkstra's | Path Policy |
| **Knowledge** | Neighbor-only updates | Full topology map | Inter-AS Path attributes |
| **Updates** | Periodic | Event-triggered | Policy / Event-triggered |
| **Convergence** | Slow | Fast | Policy-dependent |
---