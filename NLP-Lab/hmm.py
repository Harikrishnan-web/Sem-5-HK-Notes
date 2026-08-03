import nltk
from nltk.corpus import treebank
from nltk.tag.hmm import HiddenMarkovModelTrainer

# Download required datasets (Run only once)
nltk.download('treebank')
nltk.download('universal_tagset')

# Load Treebank corpus
train_data = treebank.tagged_sents(tagset='universal')[:3000]
test_data = treebank.tagged_sents(tagset='universal')[3000:]

# Train HMM Tagger
trainer = HiddenMarkovModelTrainer()
hmm_tagger = trainer.train(train_data)

# Evaluate the model
accuracy = hmm_tagger.evaluate(test_data)
print("HMM Tagger Accuracy:", round(accuracy, 4))

# Test sentence
sentence = "Natural language processing allows computers to understand human language.".split()

# POS Tagging
tagged_sentence = hmm_tagger.tag(sentence)

print("\nTagged Sentence:")
print(tagged_sentence)