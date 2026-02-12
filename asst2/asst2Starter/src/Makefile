
all:
	java -cp wrangLR.jar wrangLR.generator.main.Main -d parse MJGrammar.java -o MJGrammarParseTable.java
	javac -cp ".:wrangLR.jar" */*.java

clean:
	rm main/*.class
	rm visitor/*.class
	rm errorMsg/*.class
	rm syntaxtree/*.class
	rm parse/MJGrammar*.class
	rm parse/MJGrammarParseTable.java
	rm parse/MJGrammarParseTable.java.txt
