#include <jni_util.h>

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

jfieldID getJfieldIDInObj(JNIEnv * env, jobject obj, char const * id, char const * sig)
{
  jclass cls = env->GetObjectClass(obj);
  return env->GetFieldID(cls, id, "J");
}

jboolean getBoolValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetBooleanField(obj, getJfieldIDInObj(env, obj, id, "Z"));
}

jbyte getByteValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetByteField(obj, getJfieldIDInObj(env, obj, id, "B"));
}

jchar getCharValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetCharField(obj, getJfieldIDInObj(env, obj, id, "C"));
}

jshort getShortValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetShortField(obj, getJfieldIDInObj(env, obj, id, "S"));
}

jint getIntValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetIntField(obj, getJfieldIDInObj(env, obj, id, "I"));
}

jlong getLongValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetLongField(obj, getJfieldIDInObj(env, obj, id, "J"));
}

jfloat getFloatValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetFloatField(obj, getJfieldIDInObj(env, obj, id, "F"));
}

jdouble getDoubleValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetDoubleField(obj, getJfieldIDInObj(env, obj, id, "D"));
}

jobject getObjectValInObj(JNIEnv * env, jobject obj, char const * id)
{
  return env->GetObjectField(obj, getJfieldIDInObj(env, obj, id, "L"));
}


void setBoolValInObj(JNIEnv * env, jobject obj, char const * id, jboolean val)
{
  return env->SetBooleanField(obj, getJfieldIDInObj(env, obj, id, "Z"), val);
}

void setByteValInObj(JNIEnv * env, jobject obj, char const * id, jbyte val)
{
  return env->SetByteField(obj, getJfieldIDInObj(env, obj, id, "B"), val);
}

void setCharValInObj(JNIEnv * env, jobject obj, char const * id, jchar val)
{
  return env->SetCharField(obj, getJfieldIDInObj(env, obj, id, "C"), val);
}

void setShortValInObj(JNIEnv * env, jobject obj, char const * id, jshort val)
{
  return env->SetShortField(obj, getJfieldIDInObj(env, obj, id, "S"), val);
}

void setIntValInObj(JNIEnv * env, jobject obj, char const * id, jint val)
{
  return env->SetIntField(obj, getJfieldIDInObj(env, obj, id, "I"), val);
}

void setLongValInObj(JNIEnv * env, jobject obj, char const * id, jlong val)
{
  return env->SetLongField(obj, getJfieldIDInObj(env, obj, id, "J"), val);
}

void setFloatValInObj(JNIEnv * env, jobject obj, char const * id, jfloat val)
{
  return env->SetFloatField(obj, getJfieldIDInObj(env, obj, id, "F"), val);
}

void setDoubleValInObj(JNIEnv * env, jobject obj, char const * id, jdouble val)
{
  return env->SetDoubleField(obj, getJfieldIDInObj(env, obj, id, "D"), val);
}

void setObjectValInObj(JNIEnv * env, jobject obj, char const * id, jobject val)
{
  return env->SetObjectField(obj, getJfieldIDInObj(env, obj, id, "L"), val);
}
