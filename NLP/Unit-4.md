# Unit-4
# 4.1 REQUIREMENTS FOR REPRESENTATION

Meaning representation is the process of representing the **meaning of natural language** in a form that a computer can understand and process.

## 4.1.1 Need for Meaning Representation

Natural language is often **ambiguous and context-dependent**. Meaning representation helps computers:

* Understand meaning
* Resolve ambiguity
* Identify relationships
* Perform reasoning and inference

**Applications:** Question answering, machine translation, information extraction, dialogue systems and summarization.

---

## 4.1.2 Characteristics of Good Representation

A good representation should be:

* **Clear and precise** – meaning should be represented accurately.
* **Unambiguous** – should avoid multiple interpretations.
* **Expressive** – should represent different types of meanings.
* **Compositional** – meaning of a larger expression can be built from its parts.
* **Inferential** – should support deriving new information.
* **Computationally manageable** – should be practical for computers.

---

## 4.1.3 Components Commonly Represented

The following are commonly represented:

* **Entities / Objects** – people, places, things
* **Properties / Attributes** – characteristics of entities
* **Events / Actions** – activities or happenings
* **Relations** – connections between entities
* **Quantification** – all, some, none
* **Time and Modality** – when something happens; possibility or necessity

---

## 4.1.4 Challenges in Representation

Main challenges:

1. **Lexical ambiguity** – a word can have multiple meanings.

   * Example: *bank* → financial institution / river side
2. **Structural ambiguity** – a sentence can have more than one interpretation.
3. **Implicit knowledge** – some information is not directly stated.
4. **Context dependence** – meaning depends on the surrounding context.
5. **Real-world variation** – language can vary with situation and usage.

---

## 4.1.5 Levels of Representation

```text
Word
  ↓
Sentence
  ↓
Discourse
  ↓
Knowledge
```

* **Word level** – meaning of individual words.
* **Sentence level** – meaning of a complete sentence.
* **Discourse level** – meaning across multiple sentences.
* **Knowledge level** – represents knowledge for reasoning and inference.

### Quick Revision

**Need:** Ambiguity + Context → Meaning Representation

**Good representation:**
**Clear + Unambiguous + Expressive + Compositional + Inferential + Computationally manageable**

**Components:**
**Entities + Properties + Events + Relations + Quantification + Time/Modality**

**Challenges:**
**Lexical + Structural + Implicit + Context + Real-world variation**

**Levels:**
**Word → Sentence → Discourse → Knowledge**
---
# 4.2 FIRST-ORDER LOGIC

**First-Order Logic (FOL)** is a formal system used to represent **objects, properties, relations, and quantification**. It is important in computational semantics because it is **expressive and supports formal inference**.

## 4.2.1 Elements of First-Order Logic

1. **Constants** – represent specific objects.
   Example: `John, Mary, Chennai`

2. **Variables** – represent unspecified objects.
   Example: `x, y, z`

3. **Predicates** – represent properties or relations.
   Example: `Student(x), Loves(x,y)`

4. **Functions** – map one or more entities to another entity.

5. **Logical Connectives** – combine statements.
   `AND, OR, NOT, IMPLIES`

6. **Quantifiers** – specify the quantity of objects.
   `Universal (∀), Existential (∃)`

---

## 4.2.2 Predicates and Arguments

A **predicate** describes a property or relation, while its **arguments** specify the participating entities.

Examples:

* `Human(Ravi)`
* `Teaches(Professor, Student)`
* `Likes(Anita, Music)`

In NLP, predicates often represent the **main action or state** in a sentence.

---

## 4.2.3 Quantifiers in FOL

### 1. Universal Quantifier — ∀

Means **“for all”**.

**All humans are mortal:**

$$
\boxed{\forall x\;(Human(x) \rightarrow Mortal(x))}
$$

### 2. Existential Quantifier — ∃

Means **“there exists”**.

**There exists a student who reads:**

$$
\boxed{\exists x\;(Student(x) \land Reads(x))}
$$

---

## 4.2.4 Logical Connectives

| Connective    | Symbol  | Meaning                |
| ------------- | ------- | ---------------------- |
| Conjunction   | `P ∧ Q` | P **and** Q            |
| Disjunction   | `P ∨ Q` | P **or** Q             |
| Negation      | `¬P`    | **Not** P              |
| Implication   | `P → Q` | **If P, then Q**       |
| Biconditional | `P ↔ Q` | P **if and only if** Q |

---

## 4.2.5 Example of Sentence Representation

### “Every student passed the exam.”

$$
\boxed{\forall x\;(Student(x) \rightarrow Passed(x,Exam))}
$$

### “A boy saw a dog.”

$$
\boxed{\exists x\exists y\;(Boy(x)\land Dog(y)\land Saw(x,y))}
$$

---

## 4.2.6 Advantages of FOL

1. **Expressive**
2. Has **clear semantics**
3. Supports **inference and theorem proving**
4. Represents **relations and quantification**

---

## 4.2.7 Limitations of FOL

1. Can be complex for **natural language phenomena**.
2. Does not directly model **uncertainty**.
3. **Context and pragmatics** are difficult to encode fully.
4. Some **lexical and discourse phenomena** need additional mechanisms.

### Quick Revision

**FOL → Objects + Properties + Relations + Quantification**

**Elements:** Constants | Variables | Predicates | Functions | Connectives | Quantifiers

**Quantifiers:** `∀ = for all` | `∃ = exists`

**Key connectives:** `∧ ∨ ¬ → ↔`

**Advantages:** Expressive + Clear semantics + Inference

**Limitations:** Complexity + Uncertainty + Context/Pragmatics + Discourse
---
