#!/bin/bash

if [ $# -eq 0 ]
then
    echo "You need to inform the source port number"
    exit 1
fi

PORT=$1
while true
do
    ss -i dport $PORT | egrep -i cwnd:[0-9]* -o | cat -n >> 'relatorio.txt'
    sleep 1
done

