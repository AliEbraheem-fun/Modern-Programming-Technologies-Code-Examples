#include <jni.h>
#include <stdio.h>
#include "lecture_one_jninative_HelloJNI.h"
JNIEXPORT void JNICALL Java_lecture_one_jninative_HelloJNI_sayHello(JNIEnv *env, jobject obj) {
    printf("Привет из нативной библиотеки!\n");
}