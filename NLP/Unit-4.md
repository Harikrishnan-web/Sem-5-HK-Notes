# Unit-4
# 4.1 REQUIREMENTS FOR REPRESENTATION

**Knowledge representation in NLP** formally expresses the meaning of **words, phrases, sentences, and world knowledge** so that a machine can **interpret and reason** over language.

A good representation should support **precise meaning, inference, ambiguity resolution, and semantic interpretation**.

## 4.1.1 Need for Meaning Representation

Natural language is **ambiguous and context-dependent**, so NLP systems need a **machine-processable representation of meaning**.

Without it, systems may process words only as symbols without understanding **relationships, entities, events, and intentions**.

**Uses:**

* Question answering
* Machine translation
* Information extraction
* Dialogue systems
* Text summarization
* Inference and reasoning

## 4.1.2 Characteristics of Good Representation

A good representation should:

1. Clearly represent **objects, properties, events, and relations**.
2. Support **unambiguous interpretation**.
3. Allow **inference** to derive new facts.
4. Be **expressive** enough for different sentence meanings.
5. Support **compositionality** — sentence meaning is built from its parts.
6. Be **computationally manageable**.

## 4.1.3 Components Commonly Represented

* **Entities / Objects**
* **Attributes / Properties**
* **Actions / Events**
* **Relations**
* **Quantification** — all, some, none
* **Time and Modality**

## 4.1.4 Challenges in Representation

1. **Lexical ambiguity** — one word has many senses.
2. **Structural ambiguity** — one sentence has many possible parses.
3. **Implicit knowledge** — information is not directly stated.
4. **Context dependence**
5. **Variation in real-world language use**

## 4.1.5 Levels of Representation

```text
Word-level
    ↓
Sentence-level
    ↓
Discourse-level
    ↓
Knowledge-level
```

* **Word level** – meaning of individual words.
* **Sentence level** – meaning of a sentence.
* **Discourse level** – meaning across sentences.
* **Knowledge level** – representation used for reasoning.

These levels **interact in complete NLP systems**.
---
# 4.2 FIRST-ORDER LOGIC

**First-Order Logic (FOL)** is a formal system for representing **objects, properties, relations, and quantification**. It is important in computational semantics because it is **expressive** and supports **formal inference**.

## 4.2.1 Elements of FOL

1. **Constants** – specific objects: `John, Mary, Chennai`
2. **Variables** – unspecified objects: `x, y, z`
3. **Predicates** – properties/relations: `Student(x), Loves(x,y)`
4. **Functions** – map one or more entities to another entity.
5. **Logical connectives** – `AND, OR, NOT, IMPLIES`
6. **Quantifiers** – universal and existential.

## 4.2.2 Predicates and Arguments

A **predicate** describes a property/relation; **arguments** identify the participating entities.

* `Human(Ravi)`
* `Teaches(Professor, Student)`
* `Likes(Anita, Music)`

In NLP, predicates often represent the **main action or state** of a sentence.

## 4.2.3 Quantifiers in FOL

**Universal (∀)** → “for all”

$$
\forall x\;(Human(x)\rightarrow Mortal(x))
$$

**Existential (∃)** → “there exists”

$$
\exists x\;(Student(x)\land Reads(x))
$$

## 4.2.4 Logical Connectives

| Symbol  | Meaning      |
| ------- | ------------ |
| `P ∧ Q` | P and Q      |
| `P ∨ Q` | P or Q       |
| `¬P`    | Not P        |
| `P → Q` | If P, then Q |
| `P ↔ Q` | P iff Q      |

## 4.2.5 Sentence Representation

**Every student passed the exam:**

$$
\forall x\;(Student(x)\rightarrow Passed(x,Exam))
$$

**A boy saw a dog:**

$$
\exists x\exists y\;(Boy(x)\land Dog(y)\land Saw(x,y))
$$

## 4.2.6 Advantages

* **Expressive**
* **Clear semantics**
* Supports **inference and theorem proving**
* Represents **relations and quantification**

## 4.2.7 Limitations

* Complex for **natural language phenomena**
* Does not directly model **uncertainty**
* **Context and pragmatics** are difficult to fully encode
* Lexical and discourse phenomena may need **additional mechanisms**
---

