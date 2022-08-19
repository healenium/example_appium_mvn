# healenium-appium-mvn
Java project with healenium-appium usage example.

It uses custom Login Form Applicaion on Android Device Emulator.

## How to start
### 1.Start Healenium backend from test/resources directory.

```cd src/test/resources```

```docker-compose up -d```

Verify that images ```healenium/hlm-backend``` and ```postgres:11-alpine``` are up and running

### 2.Set up and run Appium Server and Android Device Emulator.

### 3.Run JUnit5 test TestEmulatorLoginForm form src/test/java/healenium directory.
