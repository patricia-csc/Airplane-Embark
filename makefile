build:
	mkdir classes;
	javac AirplaneEmbark/*.java -d classes;
	javac -cp classes AirplaneEmbark/*.java -d classes;
run:
	touch queue.out
	touch queue.temp
	java -Xmx512m -cp classes AirplaneEmbark.PriorityQueue in_files/queue.in out_files/queue.out temp_filesqueue.temp
clean:
	rm classes/AirplaneEmbark/*.class
	rmdir classes/AirplaneEmbark
	rmdir classes