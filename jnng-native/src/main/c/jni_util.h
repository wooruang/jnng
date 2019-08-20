
#ifndef _Included_jni_util
#define _Included_jni_util
#ifdef __cplusplus
extern "C" {
#endif

#include <jni.h>
#include <nng/nng.h>

// JNI signature.
// typedef union jvalue {
//     jboolean z; "Z"
//     jbyte    b; "B"
//     jchar    c; "C"
//     jshort   s; "S"
//     jint     i; "I"
//     jlong    j; "J"
//     jfloat   f; "F"
//     jdouble  d; "D"
//     jobject  l; "L"
// } jvalue;

jfieldID getJfieldIDInObj(JNIEnv * env, jobject obj, char const * id, char const * sig);
jboolean getBoolValInObj(JNIEnv * env, jobject obj, char const * id);
jbyte getByteValInObj(JNIEnv * env, jobject obj, char const * id);
jchar getCharValInObj(JNIEnv * env, jobject obj, char const * id);
jshort getShortValInObj(JNIEnv * env, jobject obj, char const * id);
jint getIntValInObj(JNIEnv * env, jobject obj, char const * id);
jlong getLongValInObj(JNIEnv * env, jobject obj, char const * id);
jfloat getFloatValInObj(JNIEnv * env, jobject obj, char const * id);
jdouble getDoubleValInObj(JNIEnv * env, jobject obj, char const * id);
jobject getObjectValInObj(JNIEnv * env, jobject obj, char const * id);

void setBoolValInObj(JNIEnv * env, jobject obj, char const * id, jboolean val);
void setByteValInObj(JNIEnv * env, jobject obj, char const * id, jbyte val);
void setCharValInObj(JNIEnv * env, jobject obj, char const * id, jchar val);
void setShortValInObj(JNIEnv * env, jobject obj, char const * id, jshort val);
void setIntValInObj(JNIEnv * env, jobject obj, char const * id, jint val);
void setLongValInObj(JNIEnv * env, jobject obj, char const * id, jlong val);
void setFloatValInObj(JNIEnv * env, jobject obj, char const * id, jfloat val);
void setDoubleValInObj(JNIEnv * env, jobject obj, char const * id, jdouble val);
void setObjectValInObj(JNIEnv * env, jobject obj, char const * id, jobject val);


#ifdef __cplusplus
}
#endif
#endif
