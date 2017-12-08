#!/bin/sh
apt update
apt upgrade
apt-get install mongodb
service mongodb start