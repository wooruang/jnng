# JNNG (JVM-Nanomsg-Next-Generation)

This project is a wrapper of [nng](https://github.com/nanomsg/nng)\(nanomsg-next-generation) by using [JNI](https://en.wikipedia.org/wiki/Java_Native_Interface) for [JVM](https://en.wikipedia.org/wiki/Java_virtual_machine)

## How to setup.

This project support Linux and MacOS. (Not windows yet.)

### 1. build nng.
```bash
$ ./build-nng.sh
```

### 2. Execute a server for test.
```bash
$ ./gradlew go-server -Purl="{protocol}://{ip}:{port}"

example
$ ./gradlew go-server -Purl="tcp://127.0.0.1:5050"
```

### 3. Execute a client for test.
```bash
$ ./gradlew go-client -Purl="{protocol}://{ip}:{port}"

example
$ ./gradlew go-client -Purl="tcp://127.0.0.1:5050"
```