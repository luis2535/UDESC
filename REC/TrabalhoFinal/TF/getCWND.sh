#!/bin/bash

if [ $# -eq 0 ]
then
    echo "You need to inform the source port number and the output file name"
    exit 1
fi

PORT=$1
OUTPUT_FILE=$2

while true
do
    ss -i dport $PORT | egrep -i cwnd:[0-9]* -o | cat -n >> "$OUTPUT_FILE"
    sleep 1
done

