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