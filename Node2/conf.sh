#!/bin/sh
apt update
apt upgrade
apt-get install python-pip
pip install eve
pip install eve_docs
pip install flask_bootstrap