#!/bin/bash

if [[ -z "$1" ]]
then
    echo "Please supply a MAS name"
    exit
fi

jason mas stop "$1"

jason mas list

rm nohup.out

echo "done"
