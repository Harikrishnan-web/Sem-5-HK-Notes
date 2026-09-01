# Unit-5
# 5.1 Discourse Segmentation

### 5.1.1 Discourse vs. Sentence-Level Processing

* **Sentence-level NLP:** tokenization, POS tagging, syntactic parsing, semantic role labeling.
* **Discourse analysis:** studies how sentences/clauses combine into coherent text and captures **cause–effect, elaboration, contrast, temporal order**.
* **Discourse segmentation:** divides text/speech into meaningful units called **Discourse Segments / EDUs**.
* Unlike sentence splitting, it detects **boundaries inside sentences**, especially when discourse function/topic changes.

### 5.1.2 Elementary Discourse Units and RST

* **RST (Rhetorical Structure Theory):** represents text as a tree of **EDUs** connected by rhetorical relations.
* **Nucleus:** central information.
* **Satellite:** supporting information.
* Each EDU expresses a **single coherent proposition**.
* Common boundaries: **because, although, and, but, however, in conclusion**.
* Fixed rules are insufficient because some connectives are **intra-clausal** or do not indicate true boundaries.

### 5.1.3 Rule-Based and Feature-Based Approaches

* **Rule-based:** uses punctuation, syntax and lexical cues.
* Punctuation: **period, semicolon, colon** → candidate boundaries.
* Syntax identifies clause boundaries and discourse markers: **however, furthermore, on the other hand**.
* **Feature-based supervised models:** classify boundary positions using **POS tags, phrase labels, dependency relations, lexical cohesion, discourse markers**.
* **Lexical cohesion:** repetition, synonymy and shared topics help identify topic continuity.

### 5.1.4 Neural Discourse Segmentation

* Uses **BiLSTM–CRF** and **Transformer-based models**.
* Contextual embeddings encode word sequences; **CRF** predicts boundary/non-boundary.
* Enhancements: **syntax-guided encoders, graph neural networks, restricted self-attention**.
* Improves **EDU segmentation and RST parsing**.

### 5.1.5 Applications and Evaluation

**Applications:** extractive summarization, QA, information extraction, discourse parsing.

**Evaluation:** **Precision, Recall, F1** using gold EDU boundaries; also downstream summarization/discourse parsing quality.

---

# 5.2 Coherence and Reference Phenomena

### 5.2.1 Coherence vs. Cohesion

* **Cohesion:** explicit links between sentences — pronouns, repeated words, synonyms, connectives.
* **Coherence:** deeper **semantic and pragmatic connectedness** of ideas.
* Coherence can be based on **entity continuity, discourse relations, topic transitions**.

### 5.2.2 Entity-Based Coherence Models

Track **discourse entities** across sentences to measure how smoothly attention shifts.

**Centering Theory:**

* **Cf:** forward-looking centers
* **Cb:** backward-looking center
* Transitions: **CONTINUATION, RETAIN, SHIFT**

**Entity-Grid Model:**

* Matrix of **entities × sentences**
* Roles: **Subject, Object, Other, Absent**
* Used in **essay scoring, sentence ordering, text-generation evaluation**.

### 5.2.3 Reference Phenomena

Reference expressions point to **entities, events or propositions**.

* **Anaphora:** refers backward → “John came. **He** sat.”
* **Cataphora:** refers forward → “Before **he** arrived, **John** called.”
* **Deixis:** context-dependent → **this, that, here, now**
* **Bridging:** inferred from context → “a room → **the ceiling**”

Correct reference resolution maintains **coherence**. Failure affects **summarization, machine translation and QA**.
---
# 5.5 LEXICAL AND ANNOTATED RESOURCES

### 5.5.1 Role of Resources

Lexical databases and annotated corpora support **training and evaluation** of discourse, anaphora and coreference models.

They provide:

* **POS tags**
* Parse trees
* Semantic roles
* Word senses
* Usage examples

Important resources: **Porter Stemmer, Lemmatizer, Penn Treebank, Brill’s Tagger, WordNet, PropBank, FrameNet, Brown Corpus, BNC**. 

### 5.5.2 Porter Stemmer

* **Rule-based** algorithm that removes suffixes to obtain approximate stems.
* Example: `connected, connecting → connect`
* Used in **information retrieval** and text normalization.
* Does not always produce valid dictionary words. 

### 5.5.3 Lemmatizer

* Converts inflected words into their **canonical dictionary lemma**.
* Uses lexicons, morphological rules and often **POS tags**.
* More semantically accurate than stemming.
* Requires reliable tagging and lexical resources. 

### 5.5.4 Penn Treebank

Provides:

* **POS-tagged text**
* **Phrase-structure parse trees**

Used for training **parsers and taggers** and supports resources such as **PropBank** and algorithms such as Hobbs' anaphora resolution. 

### 5.5.5 Brill’s Tagger

* **Transformation-based, error-driven POS tagger**.
* Starts with a baseline and learns **corrective rules** from labeled data.
* Rules are compact and interpretable.
* Provides POS tags for parsing and discourse analysis. 

### 5.5.6 WordNet

Organizes English words into **synsets** and semantic relations such as:

* Hypernymy
* Meronymy
* Antonymy

Used for **WSD, semantic similarity and lexical cohesion**. 

### 5.5.7 PropBank

* Adds **predicate–argument annotations** to Penn Treebank parses.
* Labels core roles (**Arg0, Arg1, etc.**) and adjuncts.
* Supports **semantic role labeling** and tracking participants/events. 

### 5.5.8 FrameNet

* Represents typical situations as **frames**.
* Links lexical units to **frame elements/roles**.
* Provides deeper semantic information for **discourse understanding and relation classification**. 

### 5.5.9 Brown Corpus

* Balanced corpus of **written American English** from multiple genres.
* Used for **POS tagging, language modeling and genre/discourse studies**. 

### 5.5.10 British National Corpus (BNC)

* Large, balanced corpus of **modern British English**.
* Contains about **100 million words** of written and spoken text.
* Designed for varied **genres, domains and registers**. 

**Development:**

* Work began around **1991**, completed in **1994**.
* Later editions include **BNC World** and **BNC XML Edition**.
* Smaller versions: **BNC Sampler** and **BNC Baby**. 

**Characteristics:**

* **Monolingual**
* **Synchronic**
* General-purpose
* Contains **written + spoken** language
* About **90% written, 10% spoken**. 

**Annotation:**

* Uses **TEI (Text Encoding Initiative)** encoding.
* Contains metadata such as author, date, source and genre.
* Includes **POS tags, lemmas and structural markup**. 

**Uses:**

* **Lexicography:** meanings, frequency, collocations and examples.
* **Language teaching:** authentic vocabulary, grammar and register.
* **Discourse research:** topics, discourse markers, pronouns and coherence.
* **Syntax research:** passives, relative clauses, questions and clause combinations. 

**Query Tools:**

* **SARA, Xaira**
* **BNCweb**
* **Sketch Engine**

Used for concordances, collocations, frequency, word patterns and genre filtering. 

**Limitations:**

* Represents mainly **late-20th-century British English**.
* Does not fully capture modern social-media/21st-century language.
* Spoken component is relatively small.
* Newer resources include **BNC2014** and web-derived corpora. 
---