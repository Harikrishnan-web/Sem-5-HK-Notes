def eps(w):
    p=[w[:i]for i in range(1, len(w))]
    s=[w[i:]for i in range(len(w)-1,0,-1)]
    return s,p
w="prefixsuffix"
p,s=eps(w)
print("Prefixes:",p)
print("suffixes:",s)
