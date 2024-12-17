docker buildx create --use

docker buildx build --platform linux/arm64,linux/amd64 -t cedric10101980/basic-postgress-app:1.0.0 --push .
