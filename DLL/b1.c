#include <jni.h>
#include <stdio.h>
#include "B1.h" 

JNIEXPORT jint JNICALL Java_B1_add(JNIEnv *env, jobject obj, jint a, jint b) {
    printf("\nNative C Code: %d + %d = %d\n", a, b, (a + b));
    return a + b; 
}

JNIEXPORT jint JNICALL Java_B1_sub(JNIEnv *env, jobject obj, jint a, jint b) {
    printf("\nNative C Code: %d - %d = %d\n", a, b, (a - b));
    return a - b; 
}

JNIEXPORT jint JNICALL Java_B1_mult(JNIEnv *env, jobject obj, jint a, jint b) {
    printf("\nNative C Code: %d * %d = %d\n", a, b, (a * b));
    return a * b; // Return the result
}

JNIEXPORT jint JNICALL Java_B1_div(JNIEnv *env, jobject obj, jint a, jint b) {
    if (b == 0) {
        printf("Error: Cannot divide by zero.\n");
        return 0; 
    }
    printf("\nNative C Code: %d / %d = %d\n", a, b, (a / b));
    return a / b; 
}
