import random
def gs(wc,tp):
    s=[]
    cw="@"
    for _ in range(wc):
        nw=random.choices(list(tp[cw].keys()), weights = list(tp[cw].values()))[0]
        if nw == "$":
            break
        s.append(nw)
        cw=nw
    return " ".join(s)
word_transition_probs = {
    "@": {"The": 0.3, "A": 0.5, "$": 0.2},
    "The": {"cat": 0.7, "dog": 0.3},
    "A": {"quick": 0.6, "lazy": 0.4},
    "quick": {"brown": 0.8, "black": 0.2},
    "lazy": {"brown": 1.0},
    "cat": {"jumped": 1.0},
    "dog": {"ran": 1.0},
    "brown": {"fox": 1.0},
    "black": {"dog": 1.0},
    "jumped": {"over": 1.0},
    "ran": {"through": 1.0},
    "over": {"the": 1.0},
    "through": {"the": 1.0},
    "the": {"fence": 1.0},
    "fence": {"$": 1.0},
    "fox":{"$":1.0}
}
ns=gs(10,word_transition_probs)
print(ns)
