#include <com_wooruang_jnng_jni_NNG.h>

#include <nng/nng.h>

#include <nng_util.h>

JNIEXPORT jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1listen
  (JNIEnv * env, jclass obj, jobject socket, jstring addr, jlong listener, jint flags)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * addr_str = env->GetStringUTFChars(addr, nullptr);

  int ret = nng_listen(soc, addr_str, (nng_listener *)listener, flags);

  setNngSocket(env, socket, soc);
  env->ReleaseStringUTFChars(addr, addr_str);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1dial
  (JNIEnv * env, jclass obj, jobject socket, jstring addr, jlong dialer, jint flags)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * addr_str = env->GetStringUTFChars(addr, nullptr);

  int ret = nng_dial(soc, addr_str, (nng_dialer *)dialer, flags);
  
  env->ReleaseStringUTFChars(addr, addr_str);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1send
  (JNIEnv * env, jclass obj, jobject socket, jbyteArray _buf, jint flags)
{
  nng_socket soc = getNngSocket(env, socket);

  jbyte * buf = env->GetByteArrayElements(_buf, nullptr);
  size_t buf_size = (size_t)env->GetArrayLength(_buf);

  int ret = nng_send(soc, buf, buf_size, 0);

  env->ReleaseByteArrayElements(_buf, buf, 0);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1recv
  (JNIEnv * env, jclass obj, jobject socket, jbyteArray _buf, jlongArray _len, jint flags)
{
  nng_socket soc = getNngSocket(env, socket);

  jbyte * buf = env->GetByteArrayElements(_buf, nullptr);
  size_t buf_size = (size_t)env->GetArrayLength(_buf);

  jlong * len = env->GetLongArrayElements(_len, nullptr);
  size_t len_size = (size_t)env->GetArrayLength(_len);

  if (len_size <= 0) {
    return nng_errno_enum::NNG_ENOARG;
  } 
  
  size_t size_t_len = buf_size;
  int ret = nng_recv(soc, buf, &size_t_len, flags);

  len[0] = (jlong)size_t_len;

  env->ReleaseByteArrayElements(_buf, buf, 0);
  env->ReleaseLongArrayElements(_len, len, 0);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1close
  (JNIEnv * env, jclass obj, jobject socket)
{
  nng_socket soc = getNngSocket(env, socket);
  return (jint)nng_close(soc);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jbyteArray _val, jlong valsz)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);

  jbyte * val = env->GetByteArrayElements(_val, nullptr);
  size_t val_size = (size_t)env->GetArrayLength(_val);
  
  if (val_size <= 0) {
    return nng_errno_enum::NNG_ENOARG;
  } 
  
  return (jint)nng_setopt(soc, opt_str, val, valsz);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt_1bool
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jboolean val)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);
  
  return (jint)nng_setopt_bool(soc, opt_str, val);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt_1int
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jint val)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);
  
  return (jint)nng_setopt_int(soc, opt_str, val);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt_1ms
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jint val)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);
  
  // printf("gogogogo!!\n");
  // printf("gogogogo %d\n", val);
  // printf("gogogogo %s\n", opt_str);
  // fflush(stdout);

  return (jint)nng_setopt_ms(soc, opt_str, val);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt_1ptr
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jlong ptr)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);
  
  return (jint)nng_setopt_ptr(soc, opt_str, (void *)ptr);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt_1size
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jlong size)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);
  
  return (jint)nng_setopt_size(soc, opt_str, size);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt_1string
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jstring str)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);
  const char * str_str = env->GetStringUTFChars(str, nullptr);
  
  return (jint)nng_setopt_string(soc, opt_str, str_str);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1setopt_1unit64
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jlong u64)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);
  
  return (jint)nng_setopt_uint64(soc, opt_str, u64);
}

jlong JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1getopt
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jbyteArray _buf, jlongArray _len)
{
  nng_socket soc = getNngSocket(env, socket);

  const char * opt_str = env->GetStringUTFChars(opt, nullptr);

  jbyte * buf = env->GetByteArrayElements(_buf, nullptr);
  size_t buf_size = (size_t)env->GetArrayLength(_buf);

  jlong * len = env->GetLongArrayElements(_len, nullptr);
  size_t len_size = (size_t)env->GetArrayLength(_len);

  if (len_size <= 0) {
    return nng_errno_enum::NNG_ENOARG;
  } 
  
  size_t size_t_len = buf_size;
  int ret = nng_getopt(soc, opt_str, buf, &size_t_len);

  len[0] = (jlong)size_t_len;

  env->ReleaseByteArrayElements(_buf, buf, 0);
  env->ReleaseLongArrayElements(_len, len, 0);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1getopt_1int
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jintArray _val)
{ 
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);

  jint * val = env->GetIntArrayElements(_val, nullptr);
  size_t val_size = (size_t)env->GetArrayLength(_val);
  
  if (val_size <= 0) {
    return nng_errno_enum::NNG_ENOARG;
  } 

  return nng_getopt_int(soc, opt_str, val);
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1getopt_1ms
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jintArray _val)
{ 
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);

  jint * val = env->GetIntArrayElements(_val, nullptr);
  size_t val_size = (size_t)env->GetArrayLength(_val);
  
  if (val_size <= 0) {
    return nng_errno_enum::NNG_ENOARG;
  } 

  jint tmp_val = 0;
  int ret = nng_getopt_ms(soc, opt_str, &tmp_val);

  val[0] = tmp_val;
  // printf("hohhoho!!\n");
  // printf("hohhoho %d\n", tmp_val);
  // fflush(stdout);

  env->ReleaseIntArrayElements(_val, val, 0);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1getopt_1size
  (JNIEnv * env, jclass obj, jobject socket, jstring opt, jlongArray _val)
{
  nng_socket soc = getNngSocket(env, socket);
  const char * opt_str = env->GetStringUTFChars(opt, nullptr);

  jlong * val = env->GetLongArrayElements(_val, nullptr);
  size_t val_size = (size_t)env->GetArrayLength(_val);
  
  if (val_size <= 0) {
    return nng_errno_enum::NNG_ENOARG;
  } 

  size_t size_t_len = 0;
  auto ret = nng_getopt_size(soc, opt_str, &size_t_len);
  val[0] = size_t_len;
  return ret;
}

jstring JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1strerror(JNIEnv * env, jclass obj, jint num)
{
  const char * buf = nng_strerror(num);
  return env->NewStringUTF(buf);
}
