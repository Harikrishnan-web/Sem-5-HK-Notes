def word_analysis(text):
    words=text.split()
    wf={}
    for word in words:
        word=word.strip('.,?!-')
        wf[word]=wf.get(word,0)+1
    tw=sum(wf.values())
    sw=sorted(wf.items(),key=lambda x:x,reverse=True)
    for word,freq in sw:
        dist=freq/tw
        print(f"{word}\t{freq}\t{dist}")

t="""Hi hi hi hi, k k k kk """
word_analysis(t)

