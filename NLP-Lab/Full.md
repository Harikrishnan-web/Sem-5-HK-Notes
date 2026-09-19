# EXPERIMENT 1 — WORD ANALYSIS

### AIM

To analyze words in a text based on their frequency and distribution.

### ALGORITHM

1. Convert text to lowercase.
2. Split text into words.
3. Remove punctuation.
4. Count word frequencies.
5. Calculate word distribution.
6. Sort and display the results.

### PROGRAM

```python
def word_analysis(text):
    words = text.lower().split()
    freq = {}

    for word in words:
        word = word.strip('.,?!-')
        freq[word] = freq.get(word, 0) + 1

    total = sum(freq.values())

    for word, count in sorted(freq.items(), key=lambda x: x[1], reverse=True):
        print(word, count, f"{count/total:.2%}")

text = "This is a sample text for word analysis. Word analysis involves analyzing the frequency of each word in a given text."
word_analysis(text)
```

### OUTPUT

```text
word 3 14.29%
a 2 9.52%
text 2 9.52%
analysis 2 9.52%
this 1 4.76%
...
```

### RESULT

Thus, word frequency and distribution were successfully analyzed.

---

# EXPERIMENT 2 — WORD GENERATION

### AIM

To generate new words or sentences using linguistic/statistical rules.

### ALGORITHM

1. Define transition probabilities.
2. Set word count and length.
3. Generate words using probabilities.
4. Continue until the required count is reached.
5. Display the generated sentence.

### PROGRAM

```python
import random

def generate_word(length, probs):
    word = ""
    state = "@"

    for _ in range(length):
        nxt = random.choices(
            list(probs[state]),
            weights=list(probs[state].values())
        )[0]

        if nxt == "$":
            break

        word += nxt
        state = nxt

    return word

probs = {
    "@": {"The": 0.3, "A": 0.5, "$": 0.2},
    "The": {"cat": 0.7, "dog": 0.3},
    "A": {"quick": 0.6, "lazy": 0.4},
    "quick": {"brown": 0.8, "black": 0.2},
    "lazy": {"brown": 1.0},
    "cat": {"jumped": 1.0},
    "dog": {"ran": 1.0},
    "brown": {"fox": 1.0},
    "black": {"dog": 1.0}
}

sentence = " ".join(generate_word(random.randint(1, 3), probs) for _ in range(5))
print("Generated Sentence:", sentence)
```

### OUTPUT

```text
Generated Sentence: Thecat Aquick Alazybrown Thedogran Aquickbrown
```

### RESULT

Thus, new words/sentences were successfully generated using transition probabilities.

---

# EXPERIMENT 3 — MORPHOLOGY

### AIM

To study the structure and formation of words using prefixes, suffixes and roots.

### ALGORITHM

1. Take a word as input.
2. Generate possible prefixes.
3. Generate possible suffixes.
4. Display the prefixes and suffixes.

### PROGRAM

```python
def extract_prefix_suffix(word):
    prefixes = [word[:i] for i in range(1, len(word))]
    suffixes = [word[i:] for i in range(len(word)-1, 0, -1)]
    return prefixes, suffixes

word = "prefixsuffix"
prefixes, suffixes = extract_prefix_suffix(word)

print("Prefixes:", prefixes)
print("Suffixes:", suffixes)
```

### OUTPUT

```text
Prefixes: ['p', 'pr', 'pre', 'pref', 'prefi', 'prefix', ...]
Suffixes: ['x', 'ix', 'fix', 'ffix', 'uffix', 'suffix', ...]
```

### RESULT

Thus, the prefixes and suffixes of the given word were successfully identified.

---

# EXPERIMENT 4 — N-GRAMS

### AIM

To create sequences of N words occurring together in a text and calculate their probabilities.

### ALGORITHM

1. Tokenize the text.
2. Generate N-grams.
3. Count unigram and N-gram frequencies.
4. Calculate N-gram probabilities.
5. Calculate perplexity.

### PROGRAM

```python
import nltk
from nltk.util import ngrams
from collections import Counter

nltk.download('punkt')

text = "Natural language processing allows computers to understand human language."
tokens = nltk.word_tokenize(text.lower())

bigrams = list(ngrams(tokens, 2))
unigram = Counter(tokens)
bigram = Counter(bigrams)

for bg, count in bigram.items():
    p = count / unigram[bg[0]]
    print(bg, f"{p:.4f}")
```

### OUTPUT

```text
('natural', 'language') 1.0000
('language', 'processing') 0.5000
('processing', 'allows') 1.0000
('allows', 'computers') 1.0000
...
```

### RESULT

Thus, bigrams and their probabilities were successfully generated.

---

# EXPERIMENT 5 — N-GRAM SMOOTHING

### AIM

To apply smoothing techniques to handle unseen N-grams.

### ALGORITHM

1. Generate N-grams.
2. Count frequencies.
3. Apply smoothing.
4. Recalculate probabilities.
5. Compare the smoothed probabilities.

### PROGRAM

```python
import nltk
from collections import Counter

nltk.download('punkt')

text = "Natural language processing allows computers to understand human language."
tokens = nltk.word_tokenize(text.lower())

bigrams = list(nltk.bigrams(tokens))
uni = Counter(tokens)
bi = Counter(bigrams)
V = len(uni)

bg = ('language', 'processing')

laplace = (bi[bg] + 1) / (uni[bg[0]] + V)
additive = (bi[bg] + 0.5) / (uni[bg[0]] + 0.5 * V)

print("Laplace:", round(laplace, 4))
print("Additive:", round(additive, 4))
```

### OUTPUT

```text
Laplace: 0.1818
Additive: 0.2308
```

### RESULT

Thus, smoothing was successfully applied to the N-gram model.

---

# EXPERIMENT 6 — POS TAGGING USING HIDDEN MARKOV MODEL

### AIM

To assign POS tags to words using a Hidden Markov Model.

### ALGORITHM

1. Load the Treebank corpus.
2. Prepare training and testing data.
3. Train the HMM tagger.
4. Tag the input sentence.
5. Evaluate the model.

### PROGRAM

```python
import nltk
from nltk.corpus import treebank
from nltk.tag.hmm import HiddenMarkovModelTrainer

nltk.download('treebank')
nltk.download('universal_tagset')

train = treebank.tagged_sents(tagset='universal')[:3000]
test = treebank.tagged_sents(tagset='universal')[3000:]

trainer = HiddenMarkovModelTrainer()
tagger = trainer.train(train)

print("Accuracy:", round(tagger.evaluate(test), 4))

sentence = "Natural language processing allows computers to understand human language.".split()
print(tagger.tag(sentence))
```

### OUTPUT

```text
Accuracy: 0.5160
[('Natural', 'NOUN'), ('language', 'NOUN'),
 ('processing', 'NOUN'), ...]
```

### RESULT

Thus, POS tagging was successfully performed using an HMM.

---

# EXPERIMENT 7 — POS TAGGING USING VITERBI DECODING

### AIM

To find the most likely sequence of POS tags using the Viterbi algorithm.

### ALGORITHM

1. Tokenize the sentence.
2. Prepare the trained HMM.
3. Initialize the Viterbi matrix.
4. Calculate probabilities for each state.
5. Backtrace the best sequence.
6. Output the POS tags.

### PROGRAM

```python
import nltk
from nltk.tag import hmm
from nltk.corpus import treebank

nltk.download('treebank')
nltk.download('universal_tagset')

data = treebank.tagged_sents(tagset='universal')

tagger = hmm.HiddenMarkovModelTrainer().train(data)

sentence = "The quick brown fox jumps over the lazy dog."
tokens = nltk.word_tokenize(sentence)

print(tagger.tag(tokens))
```

### OUTPUT

```text
[('The', 'DET'), ('quick', 'ADJ'), ('brown', 'NOUN'),
 ('fox', 'NOUN'), ('jumps', 'NOUN'), ...]
```

### RESULT

Thus, the most likely POS tag sequence was obtained using Viterbi decoding.

---

# EXPERIMENT 8 — BUILDING POS TAGGER

### AIM

To develop a program that automatically assigns POS tags to words.

### ALGORITHM

1. Collect labeled text data.
2. Extract POS-related features.
3. Select a POS tagging model.
4. Train the model.
5. Evaluate the model.
6. Tag new text.

### PROGRAM

```python
import nltk

nltk.download('averaged_perceptron_tagger')
nltk.download('punkt')

text = nltk.word_tokenize("and now for everything completely same")
result = nltk.pos_tag(text)

print(result)
```

### OUTPUT

```text
[('and', 'CC'),
 ('now', 'RB'),
 ('for', 'IN'),
 ('everything', 'NN'),
 ('completely', 'RB'),
 ('same', 'JJ')]
```

### RESULT

Thus, a POS tagger was successfully developed and applied to the given text.

---

# EXPERIMENT 9 — CHUNKING

### AIM

To identify and group words into syntactically related chunks such as noun phrases.

### ALGORITHM

1. Tokenize the sentence.
2. Assign POS tags.
3. Define chunk grammar.
4. Apply the grammar.
5. Identify the chunks.
6. Display the chunked sentence.

### PROGRAM

```python
import nltk

sentence = [
    ("the","DT"), ("little","JJ"), ("yellow","JJ"),
    ("dog","NN"), ("barked","VBD"), ("at","IN"),
    ("the","DT"), ("cat","NN")
]

grammar = "NP:{<DT>?<JJ>*<NN>}"
parser = nltk.RegexpParser(grammar)

result = parser.parse(sentence)
print(result)
```

### OUTPUT

```text
(S
 (NP the/DT little/JJ yellow/JJ dog/NN)
 barked/VBD
 at/IN
 (NP the/DT cat/NN))
```

### RESULT

Thus, syntactically related words were successfully grouped into chunks.

---

# EXPERIMENT 10 — BUILDING CHUNKER

### AIM

To create a program that automatically identifies and labels chunks in text.

### ALGORITHM

1. Collect and preprocess chunked data.
2. Extract relevant features.
3. Select a chunking model.
4. Train the model.
5. Evaluate the model.
6. Apply it to new text.

### PROGRAM

```python
import nltk
from nltk import pos_tag
from nltk.tokenize import word_tokenize
from nltk.chunk import RegexpParser

nltk.download('averaged_perceptron_tagger')
nltk.download('punkt')

sentence = "The quick brown fox jumps over the lazy dog"
tokens = word_tokenize(sentence)
tagged = pos_tag(tokens)

grammar = "NP:{<DT>?<JJ>*<NN>}"
parser = RegexpParser(grammar)

print("POS Tagged Tokens:", tagged)
print("Chunked Sentence:", parser.parse(tagged))
```

### OUTPUT

```text
POS Tagged Tokens:
[('The', 'DT'), ('quick', 'JJ'), ('brown', 'NN'),
 ('fox', 'NN'), ('jumps', 'VBZ'), ('over', 'IN'),
 ('the', 'DT'), ('lazy', 'JJ'), ('dog', 'NN')]

Chunked Sentence:
(S
 (NP The/DT quick/JJ brown/NN)
 (NP fox/NN)
 jumps/VBZ
 over/IN
 (NP the/DT lazy/JJ dog/NN))
```

### RESULT

Thus, a chunker was successfully created to identify and label noun phrases. 
