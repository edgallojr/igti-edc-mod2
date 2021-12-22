#! /usr/bin/env bash
aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 551489624358.dkr.ecr.us-east-2.amazonaws.com
docker build -t igti-repository .
docker tag igti-repository:latest 551489624358.dkr.ecr.us-east-2.amazonaws.com/igti-repository:latest
docker push 551489624358.dkr.ecr.us-east-2.amazonaws.com/igti-repository:latest