#!/bin/bash
set -x
tomee1/bin/catalina.sh stop
tomee2/bin/catalina.sh stop
rm -Rf tomee1
rm -Rf tomee2
