#!/bin/sh
echo "Aguardando banco subir..."
sleep 15
exec java -Dspring.profiles.active=prod -jar ifind.jar