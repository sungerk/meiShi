
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
package org.sunger.net.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;

import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;

/**
 * Created by sungerk on 2016/4/6.
 */
public class VideoView extends TextureView implements TextureView.SurfaceTextureListener {
    private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private static final int BUFFER_SEGMENT_COUNT = 256;
    private MediaCodecVideoTrackRenderer videoRenderer;
    private MediaCodecAudioTrackRenderer audioRenderer;
    private OnErrorListener errorListener = null;
    private OnBufferingUpdateListener onBufferingUpdateListener = null;
    private OnCompletionListener onCompletionListener = null;
    private OnPreparedListener onPreparedListener = null;
    private Handler handler;

    private Uri mUri;
    private ExoPlayer player;

    public VideoView(Context context) {
        super(context);
        init();
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        handler = new Handler(Looper.getMainLooper());
        player = ExoPlayer.Factory.newInstance(2);
        player.addListener(new ExoPlayer.Listener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                switch (playbackState) {


                    case ExoPlayer.STATE_ENDED:
                        if (onCompletionListener != null)
                            onCompletionListener.onCompletion(player);

                        break;
                    case ExoPlayer.STATE_BUFFERING:
                        if (onBufferingUpdateListener != null)
                            onBufferingUpdateListener.onBufferingUpdate(player, player.getBufferedPercentage());
                        break;

                    case ExoPlayer.STATE_READY:
                        if (onPreparedListener != null)
                            onPreparedListener.onPrepared(player);
                        break;
                }


            }

            @Override
            public void onPlayWhenReadyCommitted() {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                if (errorListener != null) {
                    errorListener.onError(player, error);
                }
            }
        });
        setSurfaceTextureListener(this);
    }


    public void resume() {
        player.setPlayWhenReady(true);
    }

    public void pause() {
        player.setPlayWhenReady(false);
    }

    private SampleSource createSource() {
        Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter(handler,
                null);
        DataSource dataSource = new DefaultUriDataSource(getContext(), bandwidthMeter, "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
        ExtractorSampleSource sampleSource = new ExtractorSampleSource(mUri, dataSource, allocator,
                BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);
        return sampleSource;
    }

    public void start() {
        SampleSource sampleSource = createSource();
        videoRenderer = new MediaCodecVideoTrackRenderer(getContext(),
                sampleSource, MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT, 5000,
                handler, null, 50);
        audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource,
                MediaCodecSelector.DEFAULT, null, true, handler, null,
                AudioCapabilities.getCapabilities(getContext()), AudioManager.STREAM_MUSIC);
        player.prepare(videoRenderer, audioRenderer);
        player.setPlayWhenReady(true);
        if(isAvailable()) {
            player.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, new Surface(getSurfaceTexture()));
        }
    }

    public void setVideoPath(String videoPath) {
        if (TextUtils.isEmpty(videoPath)) {
            throw new IllegalArgumentException("videoPath can't be null");
        }
        mUri = Uri.parse(videoPath);
    }


    public void stop() {
        player.stop();
    }

    public void release() {
        player.release();
    }

    public long getCurrentPosition() {
        return player.getCurrentPosition();
    }

    public void seekTo(long positionMs) {
        player.seekTo(positionMs);
    }



    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

        if (videoRenderer!=null)
         player.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, new Surface(surface));
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        player.blockingSendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, new Surface(surface));
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    public void setOnPreparedListener(OnPreparedListener listener) {
        this.onPreparedListener = listener;
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        this.onCompletionListener = listener;
    }

    public void setOnErrorListener(OnErrorListener listener) {
        this.errorListener = listener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        this.onBufferingUpdateListener = listener;
    }

    public interface OnPreparedListener {
        void onPrepared(ExoPlayer player);
    }


    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(ExoPlayer player, int percent);
    }

    public interface OnErrorListener {
        boolean onError(ExoPlayer player, Exception e);
    }

    public interface OnCompletionListener {
        void onCompletion(ExoPlayer player);
    }
}