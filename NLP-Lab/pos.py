import nltk
from nltk.corpus import treebank
from nltk.tag import hmm

nltk.download('treebank', quiet=True)

train_data = treebank.tagged_sents()[:3000]
test_data = treebank.tagged_sents()[3000:]

trainer = hmm.HiddenMarkovModelTrainer()
tagger = trainer.train_supervised(train_data)

print(f"Accuracy: {tagger.accuracy(test_data):.4f}")

sentence = "Natural language processing allows computers to understand human language ."
tagged_sentence = tagger.tag(sentence.split())

print("Tagged Sentence:")
print(tagged_sentence)
