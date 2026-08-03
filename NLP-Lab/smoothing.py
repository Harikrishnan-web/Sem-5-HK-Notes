import nltk
from nltk.util import ngrams
from collections import Counter

# Input text
text = "Natural language processing allows computers to understand human language."

# Tokenize
tokens = nltk.word_tokenize(text.lower())

# Generate bigrams
bigrams = list(ngrams(tokens, 2))

# Count frequencies
unigram_freq = Counter(tokens)
bigram_freq = Counter(bigrams)

# Vocabulary size
V = len(unigram_freq)

# Example bigram
bigram = ('language', 'processing')

# Laplace Smoothing
prob = (bigram_freq[bigram] + 1) / (unigram_freq[bigram[0]] + V)

print("Laplace Smoothed Probability:")
print(f"{bigram} : {prob:.4f}")