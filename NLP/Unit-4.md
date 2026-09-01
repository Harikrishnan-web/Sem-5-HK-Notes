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
# 4.3 DESCRIPTION LOGICS

**Description Logics (DL)** are formal knowledge representation languages used to describe **concepts, roles, and individuals** in a structured way.

* Less expressive than **FOL**
* Computationally tractable
* Mainly used for **ontology representation and reasoning**

## 4.3.1 Basic Concepts

1. **Concepts** – represent classes: `Student, Teacher, Course`
2. **Roles** – represent relations: `teaches, enrolledIn, worksUnder`
3. **Individuals** – represent specific instances: `John, Priya, AI_Lab`

## 4.3.2 Example

* `Student` → Concept
* `StudiesUnder` → Role
* `Ravi` → Individual

**“Ravi is a student”**

$$
\boxed{Ravi : Student}
$$

**“Ravi studies under Professor Kumar”** → represented using a **role assertion**.

## 4.3.3 Importance of DL

1. Formal basis for **ontologies**
2. Supports **classification and consistency checking**
3. Used in **Semantic Web technologies and knowledge bases**
4. Balances **expressiveness and computational efficiency**

## 4.3.4 DL vs FOL

* **FOL** → more expressive
* **DL** → more restricted but easier to reason with
* DL is suitable for **taxonomy-based knowledge representation and ontology engineering**.

## 4.3.5 Applications

1. Ontology construction
2. Semantic Web
3. Concept hierarchy modeling
4. Medical and domain knowledge representation
5. Knowledge-based NLP systems
---
# 4.4 SYNTAX-DRIVEN SEMANTIC ANALYSIS

**Syntax-driven semantic analysis** derives semantic representations from **syntactic structures** using the **parse tree and grammar rules**.

## 4.4.1 Basic Idea

**Syntax guides semantics.**

After parsing:

* Semantic rules are attached to syntactic rules.
* Meanings are combined **compositionally** to build the sentence meaning.

## 4.4.2 Importance of Syntax in Semantics

* Word **order and grouping** influence meaning.
* Semantic interpretation depends on the **syntactic structure**.
* Different syntactic structures can produce **different meanings**, even with the same words.

## 4.4.3 Steps

```text
Sentence
   ↓
1. Syntactic parsing
   ↓
2. Identify NP, VP, PP
   ↓
3. Attach semantic rules
   ↓
4. Combine meanings using parse tree
   ↓
5. Sentence-level semantic representation
```

## 4.4.4 Example

**Sentence:** “The dog chased the cat.”

**Syntax:**

```text
S → NP VP
VP → V NP
```

**Semantic representation:**

$$
\boxed{Chase(Dog,Cat)}
$$

Thus, syntactic arrangement determines the semantic relation between **subject, action, and object**.

## 4.4.5 Advantages

1. Supports **compositional meaning construction**.
2. Integrates naturally with **parsing**.
3. Provides systematic **sentence-level semantics**.
4. Helps translate **syntax into logic**.

## 4.4.6 Limitations

1. Depends heavily on **correct parsing**.
2. Syntactic ambiguity causes **semantic ambiguity**.
3. **Context and pragmatics** are not fully handled.
4. Complex language may require **richer semantic models**.
---
# 4.5 SEMANTIC ATTACHMENTS

**Semantic attachments** are semantic rules/procedures associated with **grammar productions**. When a syntactic rule is applied during parsing, the attachment contributes to the **meaning representation**.

## 4.5.1 Meaning of Semantic Attachments

They **connect syntax with meaning** by specifying how to interpret constituents produced by a grammar rule.

**Example:**

`S → NP VP` → combines the meanings of NP and VP to form a proposition.

## 4.5.2 Role in Semantic Analysis

1. Convert **parse structures → logical forms**
2. Enable **syntax-guided interpretation**
3. Support **compositional semantics**
4. Integrate **grammar and meaning representation**

## 4.5.3 Example

**Sentence:** “John eats an apple.”

```text
S → NP VP
VP → V NP
```

**Semantic result:**

$$
\boxed{Eats(John,Apple)}
$$

## 4.5.4 Advantages

1. **Modular design**
2. Direct **syntax-to-meaning mapping**
3. Easy integration with **rule-based parsers**
4. Useful in **educational and symbolic NLP systems**

## 4.5.5 Limitations

1. High **rule-engineering effort**
2. Complex language may require **many special rules**
3. Difficult to achieve coverage for **unrestricted language**
---
# 4.6 WORD SENSES

A **word sense** is a particular meaning of a word. Many words have **multiple senses**, and the intended sense depends on **context**.

## 4.6.1 Polysemy and Homonymy

* **Polysemy** – a word has multiple **related meanings**.
* **Homonymy** – the same word form has **completely different meanings**.

**Example:**

* `Bank` → financial institution
* `Bank` → river side

## 4.6.2 Importance of Word Senses

NLP systems need to identify the **correct meaning from context**.

Incorrect sense selection affects:

* Machine translation
* Question answering
* Information retrieval
* Semantic interpretation

## 4.6.3 Examples of Multiple Senses

* **Bat** → animal / cricket tool
* **Light** → illumination / not heavy

## 4.6.4 Sense Inventory

A **sense inventory** is a list of possible senses of a word.

It is usually provided by:

* Dictionary
* Thesaurus
* Lexical database such as **WordNet**
---
# 4.7 RELATIONS BETWEEN SENSES

Lexical semantics studies how **word senses are related**. These relations are useful in **lexical databases, semantic search, WSD, and word similarity**.

## 4.7.1 Synonymy

**Synonymy** is the relation between senses with **similar meanings**.

Example: **big – large**

> Synonymy is usually a relation between **senses**, not always entire words.

## 4.7.2 Antonymy

**Antonymy** is the relation of **oppositeness**.

Examples:

* **hot – cold**
* **high – low**
* **buy – sell**

## 4.7.3 Hyponymy and Hypernymy

**Hyponymy** → **IS-A** relation; one concept is a subtype of another.

**Hypernymy** → reverse relation.

```text
Animal
   ↑
  Dog
```

* Dog → **hyponym** of animal
* Animal → **hypernym** of dog

## 4.7.4 Meronymy

**Meronymy** is the **part-whole relation**.

Examples:

* Wheel → part of **car**
* Keyboard → part of **computer**

## 4.7.5 Importance of Sense Relations

They support:

1. **Lexical resource** building
2. **Semantic search**
3. **Word similarity** computation
4. **Knowledge representation**
5. NLP tasks such as **WSD and question answering**
---
# 4.8 THEMATIC ROLES

**Thematic roles (semantic roles)** describe the role an entity plays in an **event or action**.

## 4.8.1 Common Thematic Roles

1. **Agent** – doer of an action.
2. **Theme / Patient** – entity affected.
3. **Experiencer** – entity that feels or perceives.
4. **Instrument** – means used to perform an action.
5. **Goal** – endpoint/destination.
6. **Source** – starting point.
7. **Location** – place of the event.

## 4.8.2 Examples

**“Ravi cut the cake with a knife.”**

* Ravi → **Agent**
* Cake → **Theme/Patient**
* Knife → **Instrument**

**“Meena went to college from home.”**

* Meena → **Agent**
* College → **Goal**
* Home → **Source**

## 4.8.3 Importance

Thematic roles represent event meaning beyond **surface word order** and support:

* Semantic interpretation
* Information extraction
* Question answering
* Role labeling

---

# 4.9 SELECTIONAL RESTRICTIONS

**Selectional restrictions** are semantic constraints that words place on their **arguments**.

## 4.9.1 Meaning

They describe what types of entities can logically fill a particular role.

**Example:** `eat` usually requires an **animate subject** and an **edible object**.

## 4.9.2 Examples

* “The child ate rice.” → **Natural**
* “The stone ate rice.” → **Violates selectional restrictions**
* “The professor taught students.” → **Natural**
* “The idea taught students.” → **Usually anomalous**

## 4.9.3 Importance in NLP

1. Detecting **semantic anomaly**
2. Supporting **WSD**
3. Improving **parsing and interpretation**
4. Constraining **semantic role assignments**

## 4.9.4 Limitations

They are not absolute because of **metaphor, personification, and creative expressions**. Thus, they are **strong tendencies, not rigid laws**.

---

# 4.10 WORD SENSE DISAMBIGUATION

**Word Sense Disambiguation (WSD)** determines which **sense of a word** is intended in a given context.

## 4.10.1 Need for WSD

Ambiguous words require the correct meaning based on context.

Important for:

* Machine translation
* Information retrieval
* Question answering
* Semantic interpretation

## 4.10.2 Example

**“He deposited money in the bank.”**
→ Bank = **financial institution**

**“They sat on the bank of the river.”**
→ Bank = **river side**

## 4.10.3 Challenges

1. **Fine-grained senses**
2. **Sparse training data**
3. **Domain dependence**
4. **Context variation**
5. **Overlap among related senses**

## 4.10.4 Main Approaches

1. **Supervised methods**
2. **Dictionary-based methods**
3. **Thesaurus-based methods**
4. **Bootstrapping and semi-supervised methods**
5. **Distributional and embedding-based methods**
---

