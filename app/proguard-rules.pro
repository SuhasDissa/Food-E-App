-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# Reflection access
-keep class com.budiyev.android.codescanner.CodeScanner {
    private com.budiyev.android.codescanner.DecoderWrapper mDecoderWrapper;
    private android.view.SurfaceHolder mSurfaceHolder;
}
-keep public enum com.google.zxing.BarcodeFormat