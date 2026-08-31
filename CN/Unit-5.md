# UNIT V DATA LINK AND PHYSICAL LAYERS
## DATA LINK LAYER

The **Data Link Layer** is the **2nd layer from the bottom** in the OSI model.

Its main job is to transfer data between **two directly connected nodes** over a link.

```text
Upper Layer
     ↓
[Data Link Layer]
     ↓
[Physical Layer]
     ↓
  Hardware
```

At the sender, it converts the data stream into signals/bits for transmission. At the receiver, it receives the signals, forms them into recognizable **frames**, and passes them to the upper layer. 

### Two sublayers

| Sublayer                       | Main function                              |
| ------------------------------ | ------------------------------------------ |
| **LLC – Logical Link Control** | Protocols, flow control, error control     |
| **MAC – Media Access Control** | Controls access to the transmission medium |

### Services of Data Link Layer

* **Framing**
* **Addressing**
* **Error Control**
* **Flow Control** 

---

# FRAMING

### ⭐ Definition

**Framing** is the process of dividing a continuous stream of bits into **recognizable blocks called frames**.

```text
Continuous bits
────────────────────────────
        ↓ Framing
┌────────┐ ┌────────┐ ┌────────┐
│ Frame 1│ │ Frame 2│ │ Frame 3│
└────────┘ └────────┘ └────────┘
```

It allows the receiver to identify where each data block starts and ends. The Data Link Layer handles both **dividing data into frames and reassembling it**. 

### Frame structure

```text
┌────────┬─────────┬─────────┬───────┐
│ Header │ Payload │ Trailer │ Flag  │
└────────┴─────────┴─────────┴───────┘
```

* **Header** → Source and destination addresses
* **Payload** → Actual message/data
* **Trailer** → Error detection/correction bits
* **Flag** → Marks beginning and end of frame 

---

# TYPES OF FRAMING

There are **two main types**:

```text
             FRAMING
                │
       ┌────────┴────────┐
       ↓                 ↓
 Fixed-size          Variable-size
                         │
                  ┌──────┴──────┐
                  ↓             ↓
             Length field   End delimiter
                                │
                         ┌──────┴──────┐
                         ↓             ↓
                   Bit-oriented   Byte-oriented
```

## Fixed-Size Framing

Every frame has the **same fixed size**.

The frame's length itself tells the receiver where the frame ends, so an additional delimiter is not required. 

### Problem

If the data is smaller than the frame size, unused space is created.

This is called **internal fragmentation**.

### Solution

**Padding** is added to fill the unused space.

---

## Variable-Size Framing

Frames can have **different sizes**.

Therefore, the receiver needs a way to identify the **beginning and ending boundaries** of each frame. 

Two methods are used:

### 1. Length Field

A **length field** specifies the size of the frame.

**Example:** Ethernet (IEEE 802.3). 

### 2. End Delimiter

A special pattern is used to indicate the frame boundary.

The notes mention its use in **Token Ring**. 

The problem is: **What if the delimiter pattern appears inside the actual data?**

Two techniques are used:

---

# BIT-ORIENTED FRAMING

A special **8-bit flag** is used:

$$
\boxed{01111110}
$$

It marks the **beginning and end** of the frame. 

### Bit Stuffing

If the sender finds **five consecutive 1s** in the data, it inserts a **0**.

```text
Original:
11111

After stuffing:
111110
```

The receiver removes the inserted `0`.

```text
Sender → Bit stuffing → Transmission
                         ↓
                    Receiver
                         ↓
                  Bit removal
```

This prevents the data from accidentally being interpreted as the flag pattern. 

### ⭐ Remember

**Bit-oriented → Bit stuffing → 5 ones → insert 0**

---

# BYTE-ORIENTED FRAMING

Also called **byte stuffing**.

If a **flag or escape character** occurs inside the actual data, an additional **escape byte (ESC)** is inserted.

```text
Data contains ESC
       ↓
Sender adds extra ESC
       ↓
Transmission
       ↓
Receiver removes extra ESC
       ↓
Original data
```

The receiver removes the extra byte to recover the original message. 

### ⭐ Remember

**Byte-oriented → Byte stuffing → ESC**

---

## QUICK REVISION

| Topic              | Remember                                         |
| ------------------ | ------------------------------------------------ |
| Data Link Layer    | 2nd layer from bottom                            |
| Sublayers          | LLC + MAC                                        |
| Main services      | Framing, Addressing, Error Control, Flow Control |
| Framing            | Bits → Frames                                    |
| Fixed-size         | Same frame size                                  |
| Fixed-size problem | Internal fragmentation                           |
| Solution           | Padding                                          |
| Variable-size      | Different frame sizes                            |
| Length field       | Frame length specifies size                      |
| End delimiter      | Special pattern marks boundary                   |
| Bit-oriented       | Flag `01111110`                                  |
| Bit stuffing       | Insert `0` after five `1`s                       |
| Byte-oriented      | Byte stuffing                                    |
| Byte stuffing      | Add ESC when needed                              |
---
## FLOW CONTROL

### ⭐ Definition

**Flow control** is a set of procedures that controls the **amount of data a sender can send before it must wait for an acknowledgment from the receiver**.

### Why is it needed?

A fast sender may send data faster than a slow receiver can process it.

**Flow control prevents the receiver from being overwhelmed.** 

```text
Fast Sender ─────────→ Slow Receiver
             ↓
        Too much data
             ↓
       Receiver overload
```

So, flow control controls the **data sending rate**.

---

## ⭐ Types of Flow Control

There are **two methods**:

1. **Stop-and-Wait**
2. **Sliding Window** 

```text
             FLOW CONTROL
                  │
          ┌───────┴───────┐
          ↓               ↓
   Stop-and-Wait     Sliding Window
```

---

## STOP-AND-WAIT

The sender sends **one frame** and then waits for an **ACK** before sending the next frame.

### Working

```text
Sender                    Receiver
  │                          │
  │────── Frame 1 ─────────→│
  │                          │
  │←──────── ACK ────────────│
  │                          │
  │────── Frame 2 ─────────→│
  │                          │
  │←──────── ACK ────────────│
```

### Steps

1. Sender sends a frame.
2. Sender **stops and waits**.
3. Receiver receives and processes the frame.
4. Receiver sends ACK.
5. Sender receives ACK.
6. Sender sends the next frame.
7. Process continues until **EoT (End of Transmission)**. 

### ⭐ Key point

**Only one frame is sent at a time.**

---

## SLIDING WINDOW

Sliding Window allows the sender to send **multiple frames before receiving ACKs**.

It is called "sliding window" because the sender's window **moves/slides forward when acknowledgments are received**. 

```text
Sender

┌────┬────┬────┬────┐
│ F1 │ F2 │ F3 │ F4 │
└────┴────┴────┴────┘
       Window
          ↓
     ACK received
          ↓
      Window slides
```

### Working

1. Sender and receiver maintain a **window** of frames.
2. Frames are assigned **sequence numbers**.
3. Sender sends as many frames as fit inside its window.
4. Receiver receives the frames.
5. Receiver sends an ACK containing the **number of the next expected frame**.
6. Sender receives ACK and **slides the window forward**.
7. New frames can then be sent. 

### Example

If the sender's window is:

```text
[ 1 ][ 2 ][ 3 ][ 4 ]
```

After ACK for frames 1 and 2:

```text
        [ 3 ][ 4 ][ 5 ][ 6 ]
```

The window has **slid forward**.

---

## ⭐ Stop-and-Wait vs Sliding Window

| Stop-and-Wait                  | Sliding Window                         |
| ------------------------------ | -------------------------------------- |
| Sends **one frame**            | Sends **multiple frames**              |
| Waits for ACK after each frame | Doesn't need to wait after every frame |
| Simple                         | More efficient                         |
| Lower throughput               | Higher throughput                      |

### 🧠 Last-minute memory

**Stop-and-Wait:**

> **Send → Wait → ACK → Send**

**Sliding Window:**

> **Send many → ACK → Window slides → Send more**

**Most important:**
**Flow Control = prevents a fast sender from overwhelming a slow receiver.**
---
## ERROR CONTROL

### ⭐ Definition

**Error control** is the process of **detecting and correcting lost or corrupted frames** during transmission.

The Data Link Layer uses **ARQ (Automatic Repeat Request)** to retransmit frames when an error or loss is detected. 

### Basic process

```text
Sender
  ↓
Send Frame
  ↓
Receiver checks frame
  ↓
Correct? ── Yes ──→ ACK
  │
  No
  ↓
NACK / Timeout
  ↓
Retransmit frame
```

### Three phases

1. **Error Detection** – detects whether transmission error occurred.
2. **Acknowledgment**

   * **ACK** → frame received correctly
   * **NACK** → frame is damaged/duplicate
3. **Retransmission** – sender retransmits when NACK is received or ACK does not arrive before timeout. 

---

## ERROR CONTROL TECHNIQUES

### 1. Stop-and-Wait ARQ

* Sender sends **one frame**.
* Starts a **timeout counter**.
* Waits for ACK.
* ACK received → sends next frame.
* Timeout/NACK → retransmits the same frame. 

```text
Sender                    Receiver
  │── Frame 1 ───────────→│
  │                       │
  │←────── ACK ───────────│
  │                       │
  │── Frame 2 ───────────→│
```

If ACK is lost:

```text
Sender                    Receiver
  │── Frame 1 ───────────→│
  │                       │
  │       ACK lost        X
  │                       │
  │──── Frame 1 again ───→│
```

---

### 2. Go-Back-N ARQ

The sender can send **multiple frames** using a sending window.

If one frame is lost/damaged, the sender retransmits that frame **and all frames after it** for which positive acknowledgment was not received. 

```text
Sent:
F1 → F2 → F3 → F4

Suppose F2 is lost:

F1 ✓
F2 ✗
F3 ?
F4 ?

Retransmit:
      F2 → F3 → F4
```

**Memory:**
**Go-Back-N = error → go back and resend multiple frames**

---

### 3. Selective Repeat ARQ

The sender and receiver both maintain windows.

* Multiple frames can be sent.
* Receiver stores correctly received frames.
* ACK is sent for correctly received frames.
* NACK is sent only for missing/damaged frames.
* Sender retransmits **only the frame that needs retransmission**. 

```text
Sent:
F1 → F2 → F3 → F4

Suppose F2 is damaged:

F1 ✓
F2 ✗
F3 ✓
F4 ✓

Retransmit only:
     F2
```

### ⭐ Comparison

| Stop-and-Wait        | Go-Back-N                              | Selective Repeat               |
| -------------------- | -------------------------------------- | ------------------------------ |
| One frame at a time  | Multiple frames                        | Multiple frames                |
| Waits for ACK        | Window-based                           | Window-based                   |
| Retransmit one frame | Retransmit affected + following frames | Retransmit only affected frame |
| Simple               | More efficient                         | Most efficient                 |

---

# DATA LINK LAYER PROTOCOLS

Data Link Layer protocols ensure that the bits/bytes received are the same as those transmitted.

The protocols listed in your notes are:

**SDLC, HDLC, SLIP, PPP, LCP, LAP and NCP.** 

### Quick structure

```text
Data Link Layer Protocols
          │
 ┌────────┼──────────────┐
 ↓        ↓              ↓
SDLC     HDLC           SLIP
                         ↓
                        PPP
                      /  |  \
                    LCP  AP  NCP
```

---

## SDLC

**SDLC = Synchronous Data Link Control**

* Used for system network architecture traffic.
* Connects remote devices to a central mainframe.
* Supports **point-to-point** and **point-to-multipoint** communication.
* Provides error detection/recovery and proper data flow. 

---

## HDLC

**HDLC = High-Level Data Link Control**

* **Bit-oriented**
* **Synchronous**
* Developed by ISO
* Supports point-to-point and multipoint communication.
* Provides connection-oriented and connectionless services. 

### HDLC frame

```text
┌──────┬─────────┬─────────┬─────────┬─────┐
│ Flag │ Address │ Control │ Payload │ FCS │
└──────┴─────────┴─────────┴─────────┴─────┘
```

* **Flag** → `01111110`, marks beginning/end
* **Address** → receiver address
* **Control** → flow and error control
* **Payload** → actual data
* **FCS** → error detection using CRC 

### HDLC modes

* **NRM (Normal Response Mode)** → primary station sends commands; secondary responds.
* **ABM (Asynchronous Balanced Mode)** → both stations can send and respond. 

---

## SLIP

**SLIP = Serial Line Interface Protocol**

* Used to transfer IP packets over serial/dial-up links.
* Adds framing bytes around the IP packet.
* Does **not provide error detection**.
* Relies on upper-layer protocols for error handling. 

**Remember:**
**SLIP = Simple, but no error detection**

---

## PPP

**PPP = Point-to-Point Protocol**

Used to transmit **multiprotocol data between two directly connected computers**.

It is a **byte-oriented** Data Link Layer protocol. 

### Components

```text
                 PPP
                  │
       ┌──────────┼──────────┐
       ↓          ↓          ↓
Encapsulation    LCP    Authentication
                              │
                         PAP / CHAP
                  ↓
                 NCP
```

* **Encapsulation** → encapsulates datagrams.
* **LCP** → establishes, configures, tests, maintains and terminates links.
* **Authentication** → PAP and CHAP.
* **NCP** → negotiates network-layer parameters. 

### PPP frame

```text
┌──────┬─────────┬─────────┬────────┬─────────┬─────┐
│ Flag │ Address │ Control │Protocol│ Payload │ FCS │
└──────┴─────────┴─────────┴────────┴─────────┴─────┘
```

Important:

* Flag = `01111110`
* Address = 1 byte
* Control = 1 byte
* Protocol = 1 or 2 bytes
* Payload = maximum **1500 bytes** in the notes
* FCS = 2 or 4 bytes, using CRC 

---

## LCP, LAP AND NCP

### LCP — Link Control Protocol

Part of PPP.

* Determines data transmission standards.
* Identifies linked devices.
* Checks whether packet size/parameters are acceptable.
* Can terminate the link if requirements are not satisfied. 

### LAP — Link Access Procedure

Used for:

* **Framing**
* Transferring data across point-to-point links

Types:

* LAPB
* LAPF
* LAPD 

### NCP — Network Control Protocol

Part of PPP.

Used to **negotiate parameters and facilities for the network layer**.

Examples include:

* IPCP
* DNCP
* OSINLCP
* IPXCP
* NBFCP
* IPV6CP 

### ⭐ Fast revision

**Error Control:**

> Detection → ACK/NACK → Retransmission

**ARQ:**

> Stop-and-Wait → Go-Back-N → Selective Repeat

**Protocols:**

> SDLC → HDLC → SLIP → PPP

**PPP:**

> **LCP = Link**
> **PAP/CHAP = Authentication**
> **NCP = Network layer parameters**
---
# 5.6 HDLC

### High-Level Data Link Control

**HDLC** is a **bit-oriented Data Link Layer protocol** used to transmit data between network nodes.

* Data is organized into **frames**
* Supports **point-to-point** communication
* Supports **multipoint** communication 

## Transfer Modes

### 1. Normal Response Mode (NRM)

* **Primary station** sends commands.
* **Secondary station** responds to commands.
* Used for **point-to-point and multipoint** communication. 

```text
Primary
  │
  ├── Secondary 1
  └── Secondary 2
```

### 2. Asynchronous Balanced Mode (ABM)

* Configuration is **balanced**.
* Each station can **send commands and respond to commands**.
* Used only for **point-to-point** communication. 

```text
Station A  ⇄  Station B
```

## HDLC Frame

HDLC frames contain up to **six fields**; the structure varies according to the frame type. 

```text
┌──────┬─────────┬─────────┬─────────┬─────┐
│ Flag │ Address │ Control │ Payload │ FCS │
└──────┴─────────┴─────────┴─────────┴─────┘
```

### Fields

| Field       | Purpose                            |
| ----------- | ---------------------------------- |
| **Flag**    | Marks beginning and end of frame   |
| **Address** | Address of receiver                |
| **Control** | Flow and error control information |
| **Payload** | Data from Network Layer            |
| **FCS**     | Error detection                    |

**Flag:**

$$
\boxed{01111110}
$$

* Flag = **8 bits**
* Address = **1 byte to several bytes**
* Control = **1 or 2 bytes**
* FCS = **2 or 4 bytes**
* FCS uses **CRC (Cyclic Redundancy Code)**. 

---

# 5.7 PPP

### Point-to-Point Protocol

**PPP** is a **byte-oriented Data Link Layer protocol** used to transmit **multiprotocol data between two directly connected computers**.

It is widely used in **high-speed, heavily loaded broadband communication**. 

```text
Computer A  ─────────  Computer B
                 PPP
```

## Components of PPP

```text
                    PPP
                     │
       ┌─────────────┼─────────────┐
       ↓             ↓             ↓
Encapsulation       LCP       Authentication
                                    │
                               PAP / CHAP
                     ↓
                    NCP
```

### 1. Encapsulation Component

Encapsulates the **datagram** so it can be transmitted over the specified physical layer.

### 2. LCP — Link Control Protocol

Responsible for:

* Establishing links
* Configuring links
* Testing links
* Maintaining links
* Terminating links
* Negotiating options between endpoints

### 3. Authentication Protocols

Used to authenticate endpoints.

* **PAP** — Password Authentication Protocol
* **CHAP** — Challenge Handshake Authentication Protocol

### 4. NCP — Network Control Protocols

Used to negotiate **Network Layer parameters and facilities**.

Examples:

* **IPCP**
* **OSINLCP**
* **IPXCP**
* **DNCP**
* **NBFCP**
* **IPV6CP** 

---

## PPP Frame

PPP is **byte-oriented**, so each field consists of one or more bytes. 

```text
┌──────┬─────────┬─────────┬────────┬─────────┬─────┐
│ Flag │ Address │ Control │Protocol│ Payload │ FCS │
└──────┴─────────┴─────────┴────────┴─────────┴─────┘
```

| Field        | Details                                |
| ------------ | -------------------------------------- |
| **Flag**     | 1 byte, `01111110`                     |
| **Address**  | 1 byte, `11111111` for broadcast       |
| **Control**  | 1 byte, `11000000`                     |
| **Protocol** | 1 or 2 bytes; identifies payload type  |
| **Payload**  | Network Layer data; maximum 1500 bytes |
| **FCS**      | 2 or 4 bytes; uses CRC                 |



## ⭐ Quick Revision

**HDLC:**
**Bit-oriented → NRM / ABM → Flag → Address → Control → Payload → FCS**

**PPP:**
**Byte-oriented → Encapsulation → LCP → Authentication → NCP → Frame**

**Important values:**

$$
\boxed{\text{Flag}=01111110}
$$

$$
\boxed{\text{PPP Payload Max}=1500\text{ bytes}}
$$
---
# 5.8 MEDIA ACCESS CONTROL

### MAC

**MAC (Medium Access Control)** is a **sublayer of the Data Link Layer**.

Main responsibilities:

* Flow control
* Multiplexing for the transmission medium
* Controls data transmission through shared channels
* Sends data through the **Network Interface Card (NIC)**

---

## 5.8.1 MAC Layer in OSI Model

The **Data Link Layer is the second-lowest layer** of the OSI model.

It is divided into:

```text
Data Link Layer
      │
 ┌────┴────┐
 ↓         ↓
LLC       MAC
```

* **LLC** → Logical Link Control
* **MAC** → Medium Access Control

---

# 5.8.2 MAC Addresses

A **MAC address** is a **unique identifier assigned to the NIC** of a device.

It is used for data transmission within a network segment such as:

* Ethernet
* Wi-Fi
* Bluetooth

The MAC address is assigned during manufacturing and is **hardwired/hard-coded in the NIC**.

### Format

A MAC address contains **six groups of two hexadecimal digits**.

Example:

```text
00:0A:89:5B:F0:11
```

Groups can be separated by:

* `:`
* `-`
* No separator

---

# 5.8.3 ALOHA

**ALOHA** is a **multiple-access protocol** at the Data Link Layer.

It provides a method for multiple terminals to access a **shared communication channel** while handling collisions.

### Problem

If two or more stations transmit at the same time:

```text
Station A ──┐
            ├──→ Shared Channel → Collision
Station B ──┘
```

The transmitted frames can be destroyed.

There are **two versions of ALOHA**:

1. **Pure ALOHA**
2. **Slotted ALOHA**

---

## Pure ALOHA

* Stations transmit **whenever they have data**.
* If two stations transmit simultaneously, a **collision** occurs.
* The frames are destroyed.
* The sender expects an **acknowledgement (ACK)**.
* If ACK is not received within the specified time, the sender assumes the frame/ACK was destroyed.
* The station waits for a **random amount of time** and retransmits.
* Random waiting helps prevent the same frames from colliding again.

### Working

```text
Transmit
   ↓
Wait for ACK
   ↓
ACK received?
 ┌─┴──┐
Yes   No
 ↓     ↓
Done  Random wait
        ↓
    Retransmit
```

### Important point

In Pure ALOHA, **frames can be transmitted at any time**, so the chance of collision is high.

---

## Slotted ALOHA

Slotted ALOHA improves the efficiency of Pure ALOHA.

### Main idea

The shared-channel time is divided into **equal time slots**.

```text
| Slot 1 | Slot 2 | Slot 3 | Slot 4 |
```

Rules:

* A station can transmit **only at the beginning of a slot**.
* Only **one frame is sent in each slot**.
* If a station misses the beginning of a slot, it waits for the **next slot**.
* Collision can still occur if two stations transmit at the beginning of the **same slot**.

```text
Slot 1       Slot 2       Slot 3
|------------|------------|------------|
     A             B
     ↓
  Collision
```

### Pure vs Slotted ALOHA

| Pure ALOHA              | Slotted ALOHA                   |
| ----------------------- | ------------------------------- |
| Transmit anytime        | Transmit only at slot beginning |
| Higher collision chance | Lower collision chance          |
| No time slots           | Uses time slots                 |
| Less efficient          | More efficient                  |

**Key point:** Slotted ALOHA reduces the chance of collision to **one-half compared with Pure ALOHA**.
---
# 5.9 ETHERNET BASICS

### Ethernet

**Ethernet** is a set of technologies and protocols mainly used in **LANs**. It can also be used in MANs and WANs.

* Standardized as **IEEE 802.3** in the 1980s.
* It has gone through **four generations**.

## Standard Ethernet — 10 Mbps

Four main physical-layer implementations:

| Type         | Name                       | Topology | Cable         |
| ------------ | -------------------------- | -------- | ------------- |
| **10Base5**  | Thick Ethernet / Thicknet  | Bus      | Thick coaxial |
| **10Base2**  | Thin Ethernet / Cheapernet | Bus      | Thin coaxial  |
| **10Base-T** | Twisted-pair Ethernet      | Star     | Twisted pair  |
| **10Base-F** | Fiber Ethernet             | Star     | Fiber optic   |

### 10Base5

* First Ethernet implementation.
* Uses **bus topology**.
* Thick coaxial cable.
* External transceiver connected through a tap.

```text
A ─── B ─── C ─── D
     Bus cable
```

### 10Base2

* Uses **bus topology**.
* Cable is thinner and more flexible.
* Transceiver is normally part of the **NIC**.

### 10Base-T

* Uses **star topology**.
* Stations connect to a **hub**.
* Uses two pairs of twisted cable.

```text
       A
       |
B ─── Hub ─── C
       |
       D
```

### 10Base-F

* Uses **star topology**.
* Stations connect to a hub.
* Uses **two fiber-optic cables**.

---

# Fast Ethernet — 100 Mbps

**Fast Ethernet / 100BASE-T** provides speeds up to:

$$
\boxed{100\ Mbps}
$$

It is typically used for **LAN backbone systems**.

Three specifications:

1. **100BASE-TX**
2. **100BASE-T4**
3. **100BASE-FX**

---

# Gigabit Ethernet — 1 Gbps

Provides:

$$
\boxed{1\ Gbps = 1000\ Mbps}
$$

Two categories:

### Two-wire implementation

Uses:

* **1000Base-SX** → short-wave fiber
* **1000Base-LX** → long-wave fiber
* **1000Base-CX** → STP

### Four-wire implementation

* **1000Base-T**
* Uses **Category 5 twisted-pair cable**

---

# 5.10 CSMA/CD

### Definition

**CSMA/CD = Carrier Sense Multiple Access with Collision Detection**

### Carrier Sense

Every node checks whether the medium is **idle or busy**.

* **Idle → transmit**
* **Busy → postpone transmission**

### Collision Detection

The node **listens while transmitting**.

If it detects that its frame collided with another frame, it stops transmission. 

```text
Sense medium
     ↓
  Idle?
  /   \
Yes    No
 ↓      ↓
Send   Wait
 ↓
Detect collision?
 /          \
No          Yes
↓            ↓
Done      Back-off
```

---

# TRANSMITTER ALGORITHMS

Three strategies are given:

1. **Non-Persistent**
2. **1-Persistent**
3. **P-Persistent**

---

## 1. Non-Persistent

* Sense the line.
* **Idle → transmit immediately.**
* **Busy → wait for a random time.**
* Sense again.

```text
Sense
 ↓
Idle? ── Yes → Send
 ↓ No
Random wait
 ↓
Sense again
```

**Advantage:** Reduces collision chance.

**Disadvantage:** Can reduce network efficiency because the medium may remain idle while stations wait.

---

## 2. Persistent

### 1-Persistent

* Sense the line.
* If idle → **send immediately** with probability **1**.
* Because multiple stations may transmit immediately, collision probability is high.

**Remember:**
**1-Persistent = Idle → Send immediately**

---

### P-Persistent

When the line is idle:

$$
\boxed{p = \text{probability of sending}}
$$

$$
\boxed{q = 1-p}
$$

* With probability **p** → send.
* With probability **q = 1 − p** → wait for the next time slot and check again.

Used when the channel has **time slots** whose duration is at least the maximum propagation time.

**Advantage:** Combines reduced collision probability with improved efficiency.

---

# EXPONENTIAL BACK-OFF

After detecting a collision:

```text
Collision
   ↓
Stop transmission
   ↓
Wait
   ↓
Try again
   ↓
Collision again?
   ↓
Double waiting time
   ↓
Try again
```

Each time transmission fails, the adaptor **doubles its waiting time** before trying again.

This doubling strategy is called **exponential back-off**.
---
# 5.11 Virtual LAN

### VLAN

A **Virtual Local Area Network (VLAN)** is a logical group of computers that appear to be on the **same LAN**, regardless of the underlying physical network configuration.

* Network administrators divide networks according to the **functional requirements** of VLANs.
* A VLAN can contain a subset of ports on **one or multiple switches/bridges**.
* Devices in a VLAN communicate as if they are on a **separate LAN**.

### Types of VLAN

**1. Protocol VLAN**

* Traffic is handled according to the **protocol used**.
* A switch/bridge **segregates, forwards, or discards frames** based on the traffic protocol.

**2. Port-based VLAN**

* Also called **Static VLAN**.
* Administrator assigns **switch/bridge ports** to form a virtual network.

**3. Dynamic VLAN**

* Network membership is defined according to **device characteristics**.

---

# 5.12 Wireless LAN (802.11)

**Wireless LAN (WLAN)** provides connectivity between devices **without cables**.

WLANs are commonly found in:

* College campuses
* Office buildings
* Public areas

## Advantages of WLAN / 802.11

1. **Flexibility** – Within radio coverage, nodes can access each other; radio waves can penetrate partition walls.
2. **Planning** – No prior planning is required for connectivity if devices follow standard conventions.
3. **Design** – Allows development of **mobile devices**.
4. **Robustness** – Communication can still be established if devices survive a disaster.

## Disadvantages of WLAN / 802.11

1. **Quality of Service** – Low bandwidth (**1–10 Mbps**), higher error rates due to interference, and delay due to error detection/correction.
2. **Cost** – Wireless LAN adapters are costly compared with wired adapters.
3. **Proprietary Solution** – Slow standardization can result in proprietary solutions, limiting homogeneous operation.
4. **Restriction** – Individual countries have different **radio spectrum policies**, restricting development.
5. **Safety and Security** – Radio waves may interfere with other devices, e.g. high-tech equipment in hospitals.

---

# Technology Used in WLAN / 802.11

WLAN uses **Spread Spectrum (SS)** technology.

### Spread Spectrum

The signal is spread over a **wider frequency band than normal** to minimize interference from other devices.

Two types:

```text
        Spread Spectrum
             │
       ┌─────┴─────┐
       ↓           ↓
     FHSS         DSSS
```

## FHSS — Frequency Hopping Spread Spectrum

* Signal is transmitted over a **random sequence of frequencies**.
* It transmits at one frequency, then another, then another, and so on.
* The random sequence is generated using a **pseudorandom number generator**.
* Receiver uses the **same algorithm and same seed**.
* Therefore, sender and receiver can **hop frequencies in sync** and correctly receive the frame.

```text
Frequency
   ↑
 F3 │        ●
 F2 │ ●
 F1 │              ●
   └────────────────→ Time
```

## DSSS — Direct Sequence Spread Spectrum

* Each data bit is represented by **multiple bits** in the transmitted signal.
* User data stream is combined using an **XOR operation** with a pseudorandom number.
* This pseudorandom number is called the **chipping sequence**.

---

# Topology in WLAN / 802.11

Two topologies/architectures:

1. **Infrastructure Network Topology**
2. **Ad-Hoc Network Topology**

## Infrastructure Topology — AP-based Topology

* Provides communication between **wireless clients and wired network resources**.
* Data moves from wireless to wired medium through a **Base Station called AP (Access Point)**.
* An AP and its associated wireless clients define the **coverage area**.

```text
Wireless Clients
   PC1 ──┐
   PC2 ──┼── AP ─── Wired Network
   PC3 ──┘
```

## Ad-Hoc Topology — Peer-to-Peer Topology

* Supports **mutual communication between wireless clients**.
* Usually created **spontaneously**.
* Does not support access to wired networks.
* **Does not require an AP**.

```text
       PC1
      /   \
    PC2 ── PC3
```

### Quick Revision

**VLAN:** Protocol VLAN | Port-based VLAN (Static) | Dynamic VLAN

**WLAN:** Advantages | Disadvantages | Spread Spectrum | Topologies

**FHSS:** Random frequencies → pseudorandom generator → same algorithm + seed

**DSSS:** Multiple bits → XOR → **chipping sequence**

**Infrastructure:** **AP required**

**Ad-Hoc:** **AP not required**
---
# 5.13 Physical Layer

### Definition

The **Physical Layer** is the lowest layer of the OSI model that deals with **physical connectivity and signaling**.

* Receives **frames** from the Data Link Layer.
* Converts them into **electrical pulses** representing binary data.
* Transmits binary data through **wired or wireless media**. 

## 5.13.1 Data and Signals

Both **data and signals** can be:

### Analog

* Continuous format
* Takes **continuous values**

### Digital

* Has discrete states
* Takes **discrete values**

Data can be converted between analog/digital forms to achieve efficient transmission. 

### Analog Signal

* Has many levels of intensity.
* Changes through an infinite number of values.

### Digital Signal

* Has a **definite set of values**.
* Vertical axis → **signal strength**
* Horizontal axis → **time** 

---

# 5.13.2 Transmission Media

A **transmission medium** is the physical path between the **transmitter and receiver** through which data travels.

```text
Transmission Media
       │
 ┌─────┴─────┐
 ↓           ↓
Guided     Unguided
(Wired)   (Wireless)
```

## 1. Guided Media

Also called **Wired / Bounded media**.

Signals are directed through a physical path.

**Features:**

* High speed
* Secure
* Comparatively shorter distances

### Types

#### (i) Twisted Pair Cable

Two separately insulated conductor wires are **twisted around each other**.

Two types:

**UTP — Unshielded Twisted Pair**

* Two insulated copper wires twisted together.
* No physical shield.
* Used in telephone applications.

**Advantages:** Least expensive, easy installation, high-speed capacity.

**Disadvantages:** Susceptible to external interference, lower performance than STP, short-distance transmission due to attenuation.

**Applications:** Telephone connections and LANs. 

**STP — Shielded Twisted Pair**

* Has a **copper braid or foil shield** to block external interference.
* Used in fast-data-rate Ethernet and telephone voice/data channels.

**Advantages:** Better performance at higher data rates, eliminates crosstalk, comparatively faster.

**Disadvantages:** Difficult to install/manufacture, expensive, bulky. 

#### (ii) Coaxial Cable

Contains:

* Outer plastic covering
* Insulation layer
* Two parallel conductors

Two transmission modes:

* **Baseband** → dedicated cable bandwidth
* **Broadband** → bandwidth divided into separate ranges

Used in **Cable TV and analog television networks**.

**Advantages:** High bandwidth, better noise immunity, easy installation/expansion, inexpensive.

**Disadvantage:** Single cable failure can disrupt the entire network. 

#### (iii) Optical Fiber Cable

Uses **refraction of light** through a glass/plastic **core**, surrounded by **cladding**.

Used for transmitting **large volumes of data**.

```text
┌───────────────────┐
│     Cladding      │
│   ┌───────────┐   │
│   │   Core    │   │
│   └───────────┘   │
└───────────────────┘
```

Can be:

* Unidirectional
* Bidirectional

**WDM (Wavelength Division Multiplexer)** supports both modes.

**Advantages:** High capacity/bandwidth, lightweight, low attenuation, immune to electromagnetic interference, resistant to corrosive materials.

**Disadvantages:** Difficult installation/maintenance, high cost, fragile. 

#### (iv) Stripline

* **TEM transmission line**.
* Uses conducting material between two **ground-plane layers**.
* Provides **EMI immunity**.
* Also called a **waveguide**.

#### (v) Microstripline

* Conducting material is separated from the **ground plane by a dielectric layer**. 

---

## 2. Unguided Media

Also called **Wireless / Unbounded media**.

* No physical medium is required.
* Electromagnetic signals are transmitted through air.

**Features:**

* Signal is broadcast through air
* Less secure
* Used for larger distances

### Types

| Type            | Key points                                            | Frequency           |
| --------------- | ----------------------------------------------------- | ------------------- |
| **Radio waves** | Can penetrate buildings; antennas need not be aligned | **3 KHz–1 GHz**     |
| **Microwaves**  | Line-of-sight; antennas must be aligned               | **1–300 GHz**       |
| **Infrared**    | Very short distance; cannot penetrate obstacles       | **300 GHz–400 THz** |

**Radio:** AM/FM radio, cordless phones
**Microwave:** Mobile communication, TV distribution
**Infrared:** TV remote, wireless mouse/keyboard, printer 

---

# 5.13.3 Switching

Switching decides the **best route for data transmission** when multiple paths exist.

Main techniques:

```text
Switching
   │
   ├── Circuit Switching
   ├── Message Switching
   └── Packet Switching
```

## Circuit Switching

* Establishes a **dedicated path** between sender and receiver.
* Path remains until connection is terminated.
* Similar to a telephone network.
* Used mainly for **voice transmission**.
* Fixed data can be transferred at a time.

### Three phases

```text
Circuit Establishment
        ↓
    Data Transfer
        ↓
   Circuit Disconnect
```

### Types

**Space Division Switching**

* Physically separate **crosspoints** create the transmission path.

**Time Division Switching**

* Incoming and outgoing signals are transmitted in **different time slots**. 

## Message Switching

* Entire message is transferred as a **complete unit**.
* No dedicated path is established.
* Message is **stored and forwarded** through intermediate nodes.
* Destination address is attached to the message.
* Each node stores the **entire message** before forwarding it.
* Provides dynamic routing.

**Key term:** **Store-and-forward network**. 

## Packet Switching

* Message is divided into smaller units called **packets**.
* Each packet gets a unique number.
* Header contains:

  * Source address
  * Destination address
  * Sequence number
* Packets may travel through the shortest available path.
* Receiver reassembles packets in the correct order.
* Missing/corrupted packets require retransmission.
* Correct reception results in an **acknowledgement**. 

### Types of Packet Switching

**1. Datagram Packet Switching**

* Each packet is an independent entity.
* Each packet contains destination information.
* Path is **not fixed**.
* Intermediate nodes make routing decisions.
* Also called **connectionless switching**.

**2. Virtual Circuit Switching**

* Also called **connection-oriented switching**.
* A preplanned route is established before data transmission.
* **Call request** and **call accept** packets establish the connection.
* Path remains fixed for the logical connection. 
---
# 5.13 Physical Layer

### Definition

The **Physical Layer** is the lowest layer of the OSI model that deals with **physical connectivity and signaling**.

* Receives **frames** from the Data Link Layer.
* Converts them into **electrical pulses** representing binary data.
* Transmits binary data through **wired or wireless media**. 

## 5.13.1 Data and Signals

Both **data and signals** can be:

### Analog

* Continuous format
* Takes **continuous values**

### Digital

* Has discrete states
* Takes **discrete values**

Data can be converted between analog/digital forms to achieve efficient transmission. 

### Analog Signal

* Has many levels of intensity.
* Changes through an infinite number of values.

### Digital Signal

* Has a **definite set of values**.
* Vertical axis → **signal strength**
* Horizontal axis → **time** 

---

# 5.13.2 Transmission Media

A **transmission medium** is the physical path between the **transmitter and receiver** through which data travels.

```text
Transmission Media
       │
 ┌─────┴─────┐
 ↓           ↓
Guided     Unguided
(Wired)   (Wireless)
```

## 1. Guided Media

Also called **Wired / Bounded media**.

Signals are directed through a physical path.

**Features:**

* High speed
* Secure
* Comparatively shorter distances

### Types

#### (i) Twisted Pair Cable

Two separately insulated conductor wires are **twisted around each other**.

Two types:

**UTP — Unshielded Twisted Pair**

* Two insulated copper wires twisted together.
* No physical shield.
* Used in telephone applications.

**Advantages:** Least expensive, easy installation, high-speed capacity.

**Disadvantages:** Susceptible to external interference, lower performance than STP, short-distance transmission due to attenuation.

**Applications:** Telephone connections and LANs. 

**STP — Shielded Twisted Pair**

* Has a **copper braid or foil shield** to block external interference.
* Used in fast-data-rate Ethernet and telephone voice/data channels.

**Advantages:** Better performance at higher data rates, eliminates crosstalk, comparatively faster.

**Disadvantages:** Difficult to install/manufacture, expensive, bulky. 

#### (ii) Coaxial Cable

Contains:

* Outer plastic covering
* Insulation layer
* Two parallel conductors

Two transmission modes:

* **Baseband** → dedicated cable bandwidth
* **Broadband** → bandwidth divided into separate ranges

Used in **Cable TV and analog television networks**.

**Advantages:** High bandwidth, better noise immunity, easy installation/expansion, inexpensive.

**Disadvantage:** Single cable failure can disrupt the entire network. 

#### (iii) Optical Fiber Cable

Uses **refraction of light** through a glass/plastic **core**, surrounded by **cladding**.

Used for transmitting **large volumes of data**.

```text
┌───────────────────┐
│     Cladding      │
│   ┌───────────┐   │
│   │   Core    │   │
│   └───────────┘   │
└───────────────────┘
```

Can be:

* Unidirectional
* Bidirectional

**WDM (Wavelength Division Multiplexer)** supports both modes.

**Advantages:** High capacity/bandwidth, lightweight, low attenuation, immune to electromagnetic interference, resistant to corrosive materials.

**Disadvantages:** Difficult installation/maintenance, high cost, fragile. 

#### (iv) Stripline

* **TEM transmission line**.
* Uses conducting material between two **ground-plane layers**.
* Provides **EMI immunity**.
* Also called a **waveguide**.

#### (v) Microstripline

* Conducting material is separated from the **ground plane by a dielectric layer**. 

---

## 2. Unguided Media

Also called **Wireless / Unbounded media**.

* No physical medium is required.
* Electromagnetic signals are transmitted through air.

**Features:**

* Signal is broadcast through air
* Less secure
* Used for larger distances

### Types

| Type            | Key points                                            | Frequency           |
| --------------- | ----------------------------------------------------- | ------------------- |
| **Radio waves** | Can penetrate buildings; antennas need not be aligned | **3 KHz–1 GHz**     |
| **Microwaves**  | Line-of-sight; antennas must be aligned               | **1–300 GHz**       |
| **Infrared**    | Very short distance; cannot penetrate obstacles       | **300 GHz–400 THz** |

**Radio:** AM/FM radio, cordless phones
**Microwave:** Mobile communication, TV distribution
**Infrared:** TV remote, wireless mouse/keyboard, printer 

---

# 5.13.3 Switching

Switching decides the **best route for data transmission** when multiple paths exist.

Main techniques:

```text
Switching
   │
   ├── Circuit Switching
   ├── Message Switching
   └── Packet Switching
```

## Circuit Switching

* Establishes a **dedicated path** between sender and receiver.
* Path remains until connection is terminated.
* Similar to a telephone network.
* Used mainly for **voice transmission**.
* Fixed data can be transferred at a time.

### Three phases

```text
Circuit Establishment
        ↓
    Data Transfer
        ↓
   Circuit Disconnect
```

### Types

**Space Division Switching**

* Physically separate **crosspoints** create the transmission path.

**Time Division Switching**

* Incoming and outgoing signals are transmitted in **different time slots**. 

## Message Switching

* Entire message is transferred as a **complete unit**.
* No dedicated path is established.
* Message is **stored and forwarded** through intermediate nodes.
* Destination address is attached to the message.
* Each node stores the **entire message** before forwarding it.
* Provides dynamic routing.

**Key term:** **Store-and-forward network**. 

## Packet Switching

* Message is divided into smaller units called **packets**.
* Each packet gets a unique number.
* Header contains:

  * Source address
  * Destination address
  * Sequence number
* Packets may travel through the shortest available path.
* Receiver reassembles packets in the correct order.
* Missing/corrupted packets require retransmission.
* Correct reception results in an **acknowledgement**. 

### Types of Packet Switching

**1. Datagram Packet Switching**

* Each packet is an independent entity.
* Each packet contains destination information.
* Path is **not fixed**.
* Intermediate nodes make routing decisions.
* Also called **connectionless switching**.

**2. Virtual Circuit Switching**

* Also called **connection-oriented switching**.
* A preplanned route is established before data transmission.
* **Call request** and **call accept** packets establish the connection.
* Path remains fixed for the logical connection. 
---