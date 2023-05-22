#!/bin/bash

mongoimport --db XmemeDb --collection xmeme --drop --jsonArray --file ./sample-data.json