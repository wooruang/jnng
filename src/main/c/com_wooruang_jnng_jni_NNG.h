/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_wooruang_jnng_jni_NNG */

#ifndef _Included_com_wooruang_jnng_jni_NNG
#define _Included_com_wooruang_jnng_jni_NNG
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    new_nng_socket
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_wooruang_jnng_jni_NNG_new_1nng_1socket
  (JNIEnv *, jclass);

/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    nng_listen
 * Signature: (JLjava/lang/String;JI)I
 */
JNIEXPORT jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1listen
  (JNIEnv *, jclass, jlong, jstring, jlong, jint);

/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    nng_dial
 * Signature: (JLjava/lang/String;JI)I
 */
JNIEXPORT jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1dial
  (JNIEnv *, jclass, jlong, jstring, jlong, jint);

/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    nng_send
 * Signature: (J[BI)I
 */
JNIEXPORT jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1send
  (JNIEnv *, jclass, jlong, jbyteArray, jint);

/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    nng_recv
 * Signature: (J[B[JI)I
 */
JNIEXPORT jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1recv
  (JNIEnv *, jclass, jlong, jbyteArray, jlongArray, jint);

/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    nng_free
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1free
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    nng_close
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1close
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_wooruang_jnng_jni_NNG
 * Method:    nng_strerror
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1strerror
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif