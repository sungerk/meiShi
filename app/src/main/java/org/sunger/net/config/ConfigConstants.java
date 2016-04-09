/*
 * Copyright 2015, 2016 Sungerk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sunger.net.config;

import com.facebook.common.util.ByteConstants;

import org.sunger.net.utils.SdcardUtils;

/**
 * Created by sunger on 16/4/9.
 */
public class ConfigConstants {
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_DISK_CACHE_SIZE = 100 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
    public static final String IMAGE_PIPELINE_BASE_DIR = SdcardUtils.getSdcardPath()+"meiShi/Cache/";
    public static final String IMAGE_PIPELINE_CACHE_DIR = SdcardUtils.getSdcardPath()+"imagepipeline_cache";



}
