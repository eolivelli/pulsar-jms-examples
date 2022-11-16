#/bin/bash

set -x -e


mkdir tomee1
mkdir tomee2
tar zxvf tomee.tar.gz -C tomee1 --strip-components 1
tar zxvf tomee.tar.gz -C tomee2 --strip-components 1

cp system.properties tomee1/conf
cp system.properties tomee2/conf
mkdir tomee1/rars
mkdir tomee2/rars

cp pulsarra.rar tomee1/rars
cp pulsarra.rar tomee2/rars

sed -i.bak 's/8080/8082/' tomee2/conf/server.xml
sed -i.bak 's/8005/8006/' tomee2/conf/server.xml

cp consumer/target/consumer*war tomee1/webapps
cp producer/target/producer*war tomee2/webapps


tomee1/bin/catalina.sh start
tomee2/bin/catalina.sh start

tail -f tomee1/logs/catalina.out tomee2/logs/catalina.out


