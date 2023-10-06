#! /bin/bash

if [ $# -ne 1 ]
then
	echo "uso: $0 num, onde num e' um numero de palavras aleatorias" >&2
	exit 1
fi

for i in `seq 1 $1`
do
	shuf -n 1 /usr/share/dict/words
	sleep 1
done

