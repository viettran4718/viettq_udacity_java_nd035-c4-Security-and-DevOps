#!/bin/bash

while read -r plugin; do
    /usr/local/bin/install-plugins.sh "$plugin"
done < "$1"
