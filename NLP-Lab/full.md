# Natural Language Processing Laboratory Manual

**Course Code:** AL23531  
**Department:** Artificial Intelligence & Machine Learning  
**Institution:** Rajalakshmi Institute of Technology  

---

## Experiment 1: Word Analysis
**Aim:** To analyze individual words in a text to understand their frequency, length, and distribution.

### Python Code
```python
def word_analysis(text):
    text = text.lower()
    words = [w.strip('.,?!-') for w in text.split()]
    word_freq = {}
    for word in words:
        word_freq[word] = word_freq.get(word, 0) + 1
    
    total_words = sum(word_freq.values())
    word_dist = {w: f / total_words for w, f in word_freq.items()}
    sorted_freq = sorted(word_freq.items(), key=lambda x: x[1], reverse=True)
    
    print("Word".ljust(15) + "Frequency	Distribution")
    print("-" * 45)
    for word, freq in sorted_freq:
        print(f"{word.ljust(15)}{freq}		{word_dist[word]:.2%}")

text = "This is a sample text for word analysis. Word analysis involves analyzing the frequency of each word in a given text."
word_analysis(text)
```

### Output
```text
Word           Frequency	Distribution
---------------------------------------------
word           3		14.29%
a              2		9.52%
text           2		9.52%
analysis       2		9.52%
this           1		4.76%
is             1		4.76%
sample         1		4.76%
for            1		4.76%
involves       1		4.76%
analyzing      1		4.76%
the            1		4.76%
frequency      1		4.76%
of             1		4.76%
each           1		4.76%
in             1		4.76%
given          1		4.76%
```

---

## Experiment 2: Word Generation
**Aim:** To generate new words/sentences based on certain linguistic rules or statistical transition probabilities.

### Python Code
```python
import random

def generate_word(word_length, transition_probs):
    word, current_state = "", "@"
    for _ in range(word_length):
        next_state = random.choices(
            list(transition_probs[current_state].keys()),
            weights=list(transition_probs[current_state].values())
        )[0]
        if next_state == "$":
            break
        word += next_state
        current_state = next_state
    return word

def generate_sentence(word_count, transition_probs):
    sentence = []
    for _ in range(word_count):
        word_length = random.randint(1, 10)
        sentence.append(generate_word(word_length, transition_probs))
    return " ".join(sentence)

word_transition_probs = {
    "@": {"The": 0.3, "A": 0.5, "$": 0.2},
    "The": {"cat": 0.7, "dog": 0.3, "$": 0},
    "A": {"quick": 0.6, "lazy": 0.4, "$": 0},
    "quick": {"brown": 0.8, "black": 0.2, "$": 0},
    "lazy": {"brown": 1.0, "$": 0},
    "cat": {"jumped": 1.0, "$": 0},
    "dog": {"ran": 1.0, "$": 0},
    "brown": {"fox": 1.0, "$": 0},
    "black": {"dog": 1.0, "$": 0},
    "jumped": {"over": 1.0, "$": 0},
    "ran": {"through": 1.0, "$": 0},
    "over": {"the": 1.0, "$": 0},
    "through": {"the": 1.0, "$": 0},
    "the": {"fence": 1.0, "$": 0},
    "fence": {"$": 1.0}
}

print("Generated Sentence:", generate_sentence(8, word_transition_probs))
```

### Output
```text
Generated Sentence: Thedogranthrough Alazybrownfox Aquickbrown Thecat A Alazybrown
```

---

## Experiment 3: Morphology
**Aim:** To study word structure by extracting prefixes and suffixes.

### Python Code
```python
def extract_prefix_suffix(word):
    prefixes = [word[:i] for i in range(1, len(word))]
    suffixes = [word[i:] for i in range(len(word) - 1, 0, -1)]
    return prefixes, suffixes

word = "prefixsuffix"
prefixes, suffixes = extract_prefix_suffix(word)
print("Prefixes:", prefixes)
print("Suffixes:", suffixes)
```

### Output
```text
Prefixes: ['p', 'pr', 'pre', 'pref', 'prefi', 'prefix', 'prefixs', 'prefixsu', 'prefixsuf', 'prefixsuff', 'prefixsuffi']
Suffixes: ['x', 'ix', 'fix', 'ffix', 'uffix', 'suffix', 'xsuffix', 'ixsuffix', 'fixsuffix', 'efixsuffix', 'refixsuffix']
```

---

## Experiment 4: N-Grams
**Aim:** To create sequences of N words, calculate bigram probabilities, perplexity, and train an HMM Tagger.

### Python Code
```python
import nltk
from nltk.util import ngrams
from collections import Counter
from nltk.corpus import treebank
from nltk.tag import hmm

text = "Natural language processing allows computers to understand human language."
tokens = nltk.word_tokenize(text.lower())
bigrams = list(ngrams(tokens, 2))

unigram_freq = Counter(tokens)
bigram_freq = Counter(bigrams)
bigram_prob = {b: bigram_freq[b] / unigram_freq[b[0]] for b in bigram_freq}

print("Bigram Probabilities:")
for bigram, prob in bigram_prob.items():
    print(f"  {bigram}: {prob:.4f}")

def perplexity(sentence, bigram_prob, unigram_freq):
    tokens = nltk.word_tokenize(sentence.lower())
    bigrams = list(ngrams(tokens, 2))
    N = len(tokens)
    p = 1.0
    for b in bigrams:
        if b in bigram_prob:
            p *= 1 / bigram_prob[b]
        else:
            p *= 1 / (unigram_freq[b[0]] + len(unigram_freq))
    return pow(p, 1 / N)

test_sentence = "Language processing allows understanding."
print(f"Perplexity of the sentence '{test_sentence}': {perplexity(test_sentence, bigram_prob, unigram_freq):.4f}")

# Part-of-Speech Tagging using NLTK
training_data = treebank.tagged_sents()[:3000]
testing_data = treebank.tagged_sents()[3000:]

trainer = hmm.HiddenMarkovModelTrainer()
hmm_tagger = trainer.train(training_data)

accuracy = hmm_tagger.evaluate(testing_data)
print(f"HMM Tagger Accuracy: {accuracy:.4f}")

sentence = "Natural language processing allows computers to understand human language.".split()
tagged_sentence = hmm_tagger.tag(sentence)
print("Tagged Sentence:")
print(tagged_sentence)
```

### Output
```text
Bigram Probabilities:
  ('natural', 'language'): 1.0000
  ('language', 'processing'): 0.5000
  ('processing', 'allows'): 1.0000
  ('allows', 'computers'): 1.0000
  ('computers', 'to'): 1.0000
  ('to', 'understand'): 1.0000
  ('understand', 'human'): 1.0000
  ('human', 'language'): 1.0000
  ('language', '.'): 0.5000
Perplexity of the sentence 'Language processing allows understanding.': 0.4670
HMM Tagger Accuracy: 0.3684
Tagged Sentence:
[('Natural', 'NNP'), ('language', 'NN'), ('processing', 'NNP'), ('allows', 'NNP'), ('computers', 'NNP'), ('to', 'NNP'), ('understand', 'NNP'), ('human', 'NNP'), ('language.', 'NNP')]
```

---

## Experiment 5: N-Grams Smoothing
**Aim:** To apply smoothing techniques (Laplace, Additive, Good-Turing, Kneser-Ney, Witten-Bell) to address unseen N-grams.

### Python Code
```python
import nltk
from collections import Counter
from nltk.lm import KneserNeyInterpolated, WittenBellInterpolated
from nltk.lm.preprocessing import padded_everygram_pipeline

text = "Natural language processing allows computers to understand human language."
tokens = nltk.word_tokenize(text.lower())
bigrams = list(nltk.bigrams(tokens))
unigram_freq, bigram_freq = Counter(tokens), Counter(bigrams)
V = len(unigram_freq)
example_bigram = ('language', 'processing')

# Laplace
laplace_prob = (bigram_freq[example_bigram] + 1) / (unigram_freq[example_bigram[0]] + V)
print(f"Laplace Smoothed Probability of {example_bigram}: {laplace_prob:.4f}")

# Additive
alpha = 0.5
add_prob = (bigram_freq[example_bigram] + alpha) / (unigram_freq[example_bigram[0]] + alpha * V)
print(f"Additive Smoothed Probability of {example_bigram} with alpha={alpha}: {add_prob:.4f}")

# Good-Turing
freq_of_freqs = Counter(bigram_freq.values())
c = bigram_freq[example_bigram]
c_star = (c + 1) * (freq_of_freqs[c + 1] / freq_of_freqs[c]) if freq_of_freqs[c] != 0 else 0
gt_prob = c_star / sum(bigram_freq.values())
print(f"Good-Turing Probability of {example_bigram}: {gt_prob:.4f}")

# Kneser-Ney
train_data = [['natural', 'language', 'processing', 'allows', 'computers', 'to', 'understand', 'human', 'language']]
train_data_kn, padded_sents_kn = padded_everygram_pipeline(2, train_data)
kn_model = KneserNeyInterpolated(2)
kn_model.fit(train_data_kn, padded_sents_kn)
print(f"Kneser-Ney Smoothed Probability: {kn_model.score('processing', ['language']):.4f}")

# Witten-Bell
train_data_wb, padded_sents_wb = padded_everygram_pipeline(2, train_data)
wb_model = WittenBellInterpolated(2)
wb_model.fit(train_data_wb, padded_sents_wb)
print(f"Witten-Bell Interpolated Probability: {wb_model.score('processing', ['language']):.4f}")
```

### Output
```text
Laplace Smoothed Probability of ('language', 'processing'): 0.1818
Additive Smoothed Probability of ('language', 'processing') with alpha=0.5: 0.2308
Good-Turing Probability of ('language', 'processing'): 0.0000
Kneser-Ney Smoothed Probability: 0.4600
Witten-Bell Interpolated Probability: 0.2955
```

---

## Experiment 6: POS Tagging - Hidden Markov Model
**Aim:** To assign universal POS tags using a trained Hidden Markov Model.

### Python Code
```python
import nltk
from nltk.corpus import treebank
from nltk.tag.hmm import HiddenMarkovModelTrainer

nltk.download('treebank', quiet=True)
nltk.download('universal_tagset', quiet=True)

train_data = treebank.tagged_sents(tagset='universal')[:3000]
test_data = treebank.tagged_sents(tagset='universal')[3000:]

trainer = HiddenMarkovModelTrainer()
hmm_tagger = trainer.train(train_data)

accuracy = hmm_tagger.evaluate(test_data)
print(f"HMM Tagger Accuracy: {accuracy:.4f}")

sentence = "Natural language processing allows computers to understand human language.".split()
tagged_sentence = hmm_tagger.tag(sentence)
print("Tagged Sentence:")
print(tagged_sentence)
```

### Output
```text
HMM Tagger Accuracy: 0.5160
Tagged Sentence:
[('Natural', 'NOUN'), ('language', 'NOUN'), ('processing', 'NOUN'), ('allows', 'NOUN'), ('computers', 'NOUN'), ('to', 'NOUN'), ('understand', 'NOUN'), ('human', 'NOUN'), ('language.', 'NOUN')]
```

---

## Experiment 7: POS Tagging - Viterbi Decoding
**Aim:** To find the most likely sequence of POS tags for a given text using Viterbi decoding via HMM.

### Python Code
```python
import nltk
from nltk.tag import hmm
from nltk.corpus import treebank

nltk.download('treebank', quiet=True)
nltk.download('universal_tagset', quiet=True)

train_data = treebank.tagged_sents(tagset='universal')
trainer = hmm.HiddenMarkovModelTrainer()
hmm_tagger = trainer.train(train_data)

def pos_tag_sentence(sentence):
    tokens = nltk.word_tokenize(sentence)
    return hmm_tagger.tag(tokens)

sentence = "The quick brown fox jumps over the lazy dog."
pos_tags = pos_tag_sentence(sentence)
print(pos_tags)
```

### Output
```text
[('The', 'DET'), ('quick', 'ADJ'), ('brown', 'NOUN'), ('fox', 'NOUN'), ('jumps', 'NOUN'), ('over', 'NOUN'), ('the', 'NOUN'), ('lazy', 'NOUN'), ('dog', 'NOUN'), ('.', 'NOUN')]
```

---

## Experiment 8: Building POS Tagger
**Aim:** To generate POS tags using pre-trained NLTK taggers.

### Python Code
```python
import nltk

nltk.download('averaged_perceptron_tagger', quiet=True)
nltk.download('punkt', quiet=True)

text = nltk.word_tokenize("and now for everything completely same")
print(nltk.pos_tag(text))
```

### Output
```text
[('and', 'CC'), ('now', 'RB'), ('for', 'IN'), ('everything', 'NN'), ('completely', 'RB'), ('same', 'JJ')]
```

---

## Experiment 9: Chunking
**Aim:** To group tokens into syntactically correlated chunks using regular expression parsing.

### Python Code
```python
import nltk

sentence = [("the", "DT"), ("little", "JJ"), ("yellow", "JJ"), ("dog", "NN"), ("barked", "VBD"), ("at", "IN"), ("the", "DT"), ("cat", "NN")]
grammar = "NP: {<DT>?<JJ>*<NN>}"
cp = nltk.RegexpParser(grammar)
result = cp.parse(sentence)
print(result)
```

### Output
```text
(S
  (NP the/DT little/JJ yellow/JJ dog/NN)
  barked/VBD
  at/IN
  (NP the/DT cat/NN))
```

---

## Experiment 10: Building Chunker
**Aim:** To create an automatic chunker using POS tagging and regular expression-based rules.

### Python Code
```python
import nltk
from nltk import pos_tag
from nltk.tokenize import word_tokenize
from nltk.chunk import RegexpParser

nltk.download('averaged_perceptron_tagger', quiet=True)
nltk.download('punkt', quiet=True)

sentence = "The quick brown fox jumps over the lazy dog"
tokens = word_tokenize(sentence)
tagged_tokens = pos_tag(tokens)
print("POS Tagged Tokens:", tagged_tokens)

chunk_grammar = r"NP: {<DT>?<JJ>*<NN>}"
chunk_parser = RegexpParser(chunk_grammar)
chunked_sentence = chunk_parser.parse(tagged_tokens)

print("Chunked Sentence:", chunked_sentence)
```

### Output
```text
POS Tagged Tokens: [('The', 'DT'), ('quick', 'JJ'), ('brown', 'NN'), ('fox', 'NN'), ('jumps', 'VBZ'), ('over', 'IN'), ('the', 'DT'), ('lazy', 'JJ'), ('dog', 'NN')]
Chunked Sentence: (S
  (NP The/DT quick/JJ brown/NN)
  (NP fox/NN)
  jumps/VBZ
  over/IN
  (NP the/DT lazy/JJ dog/NN))
```