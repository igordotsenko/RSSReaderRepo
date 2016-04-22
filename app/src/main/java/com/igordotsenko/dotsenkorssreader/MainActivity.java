package com.igordotsenko.dotsenkorssreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.igordotsenko.dotsenkorssreader.syncadapter.ReaderSyncAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "rss_reader_log";


    private ChannelListFragment mChannelListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReaderSyncAdapter.initializeSyncAdapter(this);

        initializeImageLoader();

        mChannelListFragment = (ChannelListFragment) getSupportFragmentManager()
                .findFragmentByTag(ChannelListFragment.FRAGMENT_TAG);

        if (mChannelListFragment == null) {
            mChannelListFragment = new ChannelListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.channelList_fragment_container,
                            mChannelListFragment,
                            ChannelListFragment.FRAGMENT_TAG)
                    .commit();
        }
    }

    private void initializeImageLoader() {
        ImageLoaderConfiguration imageLoaderConfiguration =
                new ImageLoaderConfiguration.Builder(MainActivity.this)
                        .memoryCacheSize(2 * 1024 * 1024)
                        .diskCacheSize(50 * 1024 * 1024)
                        .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
}