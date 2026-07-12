import nltk
from nltk.util import ngrams
from collections import Counter
from nltk.corpus import treebank
from nltk.tag import hmm
nltk.download('punkt')
nltk.download('treebank')
text = "Natural language processing allows computers to understand human language."
tokens = nltk.word_tokenize(text.lower())
bigrams = list(ngrams(tokens, 2))
unigram_freq = Counter(tokens)
bigram_freq = Counter(bigrams)
print("Bigram Probabilities:")
for bigram in bigram_freq:
    prob = bigram_freq[bigram] / unigram_freq[bigram[0]]
    print(f"{bigram}: {prob:.4f}")
test_sentence = "Language processing allows understanding."
test_tokens = nltk.word_tokenize(test_sentence.lower())
test_bigrams = list(ngrams(test_tokens, 2))

p = 1
for bigram in test_bigrams:
    if bigram in bigram_freq:
        p *= unigram_freq[bigram[0]] / bigram_freq[bigram]
    else:
        p *= len(unigram_freq)

perplexity = p ** (1 / len(test_tokens))
print("\nPerplexity:", round(perplexity, 4))
train_data = treebank.tagged_sents()[:3000]
trainer = hmm.HiddenMarkovModelTrainer()
tagger = trainer.train(train_data)
sentence = "Natural language processing allows computers to understand human language".split()
print("\nTagged Sentence:")
print(tagger.tag(sentence))