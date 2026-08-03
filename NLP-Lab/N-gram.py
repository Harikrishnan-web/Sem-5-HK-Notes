import nltk
from collections import Counter

nltk.download('punkt', quiet=True)

text = "Natural language processing allows computers to understand human language."
tokens = nltk.word_tokenize(text.lower())
bigrams = list(zip(tokens, tokens[1:]))

unigrams_count = Counter(tokens)
bigrams_count = Counter(bigrams)

print("Bigram Probabilities:")
for bigram, count in bigrams_count.items():
    prob = count / unigrams_count[bigram[0]]
    print(f"{bigram}: {prob:.4f}")

test_sentence = "Language processing allows understanding."
test_tokens = nltk.word_tokenize(test_sentence.lower())
test_bigrams = list(zip(test_tokens, test_tokens[1:]))

product = 1
vocab_size = len(unigrams_count)

for bigram in test_bigrams:
    count_bigram = bigrams_count.get(bigram, 0)
    count_unigram = unigrams_count.get(bigram[0], 0)
    prob = (count_bigram + 1) / (count_unigram + vocab_size)
    product *= prob

perplexity = (1 / product) ** (1 / len(test_tokens))
print(f"\nPerplexity: {perplexity:.4f}")
