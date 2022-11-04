# Healenium-Appium Example

[![Docker Pulls](https://img.shields.io/docker/pulls/healenium/hlm-backend.svg?maxAge=25920)](https://hub.docker.com/u/healenium)
[![License](https://img.shields.io/badge/license-Apache-brightgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![@healenium](https://img.shields.io/badge/Telegram-%40healenium-orange.svg)](https://t.me/healenium)

### Table of Contents

[Overall information](#overall-information)

[Compatibility with OSs](#compatibility-with-oss)

[Healenium Appium installation](#healenium-appium-installation)


### Overall information
Healenium-Appium example demos java project to test both web app and native apps using Appium.

### Compatibility with OSs

Support: Android Web, Android native app, IOS Web.

NOT Support: IOS native app.

> Unfortunately, Healenium-Appium doesn't support IOS native apps at present.
> But we plan to release to support IOS apps in the near future.


### Healenium-Appium installation

Clone Healenium repository:
```sh
git clone https://github.com/healenium/healenium.git
```

Setup appium server and device. And install our android app: /resource/apps/login-form.apk

> Before run healenium you have to specify appium server host and port using appropriate environment variables of hlm-proxy container: APPIUM_SERVER_URL

Example setup hlm-proxy's env variables in case of local Appium server (specified by default):

```dockerfile
    - APPIUM_SERVER_URL=http://host.docker.internal:4723/wd/hub
```

Run Healenium with Appium only

```sh
docker-compose -f healenium/docker-compose-appium.yaml up -d
```

Run example tests

```sh
mvn clean test
```

## Community / Support

* [Telegram chat](https://t.me/healenium)
* [GitHub Issues](https://github.com/healenium/healenium/issues)
* [YouTube Channel](https://www.youtube.com/channel/UCsZJ0ri-Hp7IA1A6Fgi4Hvg)