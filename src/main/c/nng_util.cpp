#include <nng_util.h>

nng_socket getNngSocket(JNIEnv * env, jobject obj)
{
  nng_socket sock;
  sock.id = getLongValInObj(env, obj, "id");
  return sock;
}

void setNngSocket(JNIEnv * env, jobject obj, nng_socket sock)
{
  setLongValInObj(env, obj, "id", sock.id);
}
