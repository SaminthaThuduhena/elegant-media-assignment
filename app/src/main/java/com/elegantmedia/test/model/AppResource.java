package com.elegantmedia.test.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppResource<T> {

    @NonNull
    public final AppStatus status;
    @Nullable
    public final T data;
    @Nullable
    public final int code;
    @Nullable
    public final String message;

    public AppResource(@NonNull AppStatus status, @Nullable T data, @Nullable int code, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> AppResource<T> authenticated (@Nullable T data) {
        return new AppResource<>(AppStatus.AUTHENTICATED, data, 0, "Success");
    }

    public static <T> AppResource<T> success(@NonNull String msg, @Nullable T data) {
        return new AppResource<>(AppStatus.SUCCESS, data, 0, msg);
    }

    public static <T> AppResource<T> error(@NonNull String msg) {
        return new AppResource<>(AppStatus.ERROR, null, -1, msg);
    }

    public static <T> AppResource<T> error(@NonNull AppResponse response) {
        if (response.getRetCode() == 4) {
            return new AppResource<>(AppStatus.NO_INTERNET, null, response.getRetCode(), response.getRetMsg());
        }
        return new AppResource<>(AppStatus.ERROR, null, response.getRetCode(), response.getRetMsg());
    }

    public static <T> AppResource<T> loading(@Nullable T data) {
        return new AppResource<>(AppStatus.LOADING, data, -1, null);
    }

    public static <T> AppResource<T> logout() {
        return new AppResource<>(AppStatus.NOT_AUTHENTICATED, null, -1, null);
    }


    public enum AppStatus {AUTHENTICATED, SUCCESS, ERROR, LOADING, NOT_AUTHENTICATED, NO_INTERNET}
}
