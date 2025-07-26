package lecture.one.jninative;
// Visual studio 2019 code is used
// Launch x64 Native Tools command propt
// The compiler on my computer is located at: "C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC\Tools\MSVC\14.29.30133\bin\Hostx64\x64\cl.exe"
// java compiler is javac is located at: C:\Users\alieb\.jdks\openjdk-23.0.1\bin\javac.exe
// java source file is located at: D:\FINU\My Courses\Modern Programming Technologies\Modern-Programming-Technologies-Code-Examples\lecture1\src\main\java\lecture\one\jninative\HelloJNI.java
// launch the compiler with the -h option: C:\Users\alieb\.jdks\openjdk-23.0.1\bin\javac.exe -h "D:\FINU\My Courses\Modern Programming Technologies\Modern-Programming-Technologies-Code-Examples\lecture1\src\main\java\lecture\one\jninative" "D:\FINU\My Courses\Modern Programming Technologies\Modern-Programming-Technologies-Code-Examples\lecture1\src\main\java\lecture\one\jninative\HelloJNI.java"
/* Add native c code to "lecture_one_jninative_HelloJNI.c"

#include <jni.h>
#include <stdio.h>
#include "lecture_one_jninative_HelloJNI.h"
JNIEXPORT void JNICALL Java_lecture_one_jninative_HelloJNI_sayHello(JNIEnv *env, jobject obj) {
    printf("Привет из нативной библиотеки!\n");
}
 */
// launch the c copiler to create a dll library
// "C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC\Tools\MSVC\14.29.30133\bin\Hostx64\x64\cl.exe"  /I "C:\Users\alieb\.jdks\openjdk-23.0.1\include"  /I "C:\Users\alieb\.jdks\openjdk-23.0.1\include\win32"  /LD "D:\FINU\My Courses\Modern Programming Technologies\Modern-Programming-Technologies-Code-Examples\lecture1\src\main\java\lecture\one\jninative\lecture_one_jninative_HelloJNI.c"  /Fe"D:\FINU\My Courses\Modern Programming Technologies\Modern-Programming-Technologies-Code-Examples\lecture1\src\main\java\lecture\one\jninative\HelloJNI.dll"
public class HelloJNI {
    static {
        System.load("D:\\FINU\\My Courses\\Modern Programming Technologies\\Modern-Programming-Technologies-Code-Examples\\lecture1\\src\\main\\java\\lecture\\one\\jninative\\HelloJNI.dll");
    }

    public native void sayHello();

    public static void main(String[] args) {
        new HelloJNI().sayHello();
    }
}
