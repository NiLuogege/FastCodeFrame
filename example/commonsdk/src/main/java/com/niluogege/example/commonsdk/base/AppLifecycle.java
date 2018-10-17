/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.niluogege.example.commonsdk.base;

import android.app.Application;
import android.content.Context;

/**
 * 用于代理 {@link Application} 的生命周期
 */
public interface AppLifecycle {
    /**
     * 先于 onCreate 调用
     *
     * @param base
     */
    void attachBaseContext(Context base);

    void onCreate(Application application);

    /**
     * 内存不足时调用
     *
     * @param application
     * @param level
     */
    void onTrimMemory(Application application, int level);

    /**
     * 会在app关闭的时候调用,但是就像onDestroy()一样,不能保证一定会被调用
     *
     * @param application
     */
    void onTerminate(Application application);
}
