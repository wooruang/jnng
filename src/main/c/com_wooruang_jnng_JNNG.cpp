#include "com_wooruang_jnng_JNNG.h"

JNIEXPORT jint JNICALL Java_com_wooruang_jnng_JNNG_nng_1listen
  (JNIEnv *, jobject, jlong, jstring, jlong, jint)
{
  printf("hello Java_com_wooruang_jnng_JNNG_nng_1listen");
}

JNIEXPORT jint JNICALL Java_com_wooruang_jnng_JNNG_nng_1recv
  (JNIEnv *, jobject, jlong, jlong, jlong, jint)
{
  printf("hello Java_com_wooruang_jnng_JNNG_nng_1recv");
}
