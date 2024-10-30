#!/bin/bash


echo $1
if [[ -z $1 ]]
then
       echo "Please supply a MAS name"
       exit
fi


echo $2
if [[ -z $2 ]]
then
    echo "Please supply an agent source file"
    exit
fi

( bash -c "sleep 4s ; jason agent start bob --mas-name $1 --source $2" ) &


echo "starting MAS now"
jason mas start --console $1

