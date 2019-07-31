
#ifndef _Included_nng_util
#define _Included_nng_util
#ifdef __cplusplus
extern "C" {
#endif

#include <jni_util.h>

nng_socket getNngSocket(JNIEnv * env, jobject obj);

void setNngSocket(JNIEnv * env, jobject obj, nng_socket sock);

#ifdef __cplusplus
}
#endif
#endif
