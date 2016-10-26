package com.lakeel.altla.sample.draganddrop;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public final class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.image_view);
        final TextView textViewDropArea = (TextView) findViewById(R.id.text_view_drag_area);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                ClipData clipData = ClipData.newPlainText("SampleClipData", "SampleItem");
                view.startDrag(clipData, new View.DragShadowBuilder(view), null, 0);

                return true;
            }
        });

        imageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d(TAG, "imageView: ACTION_DRAG_STARTED");
                        // darkenss the icon to indicate dragging.
                        imageView.setColorFilter(Color.parseColor("#aa888888"), PorterDuff.Mode.MULTIPLY);
                        imageView.invalidate();
                        // returns true to accept a drag event.
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(TAG, "imageView: ACTION_DRAG_ENTERED");
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(TAG, "imageView: ACTION_DRAG_EXITED");
                        return true;
                    case DragEvent.ACTION_DROP:
                        Log.d(TAG, "imageView: ACTION_DROP");
                        // does not accept to drop here.
                        return false;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(TAG, "imageView: ACTION_DRAG_ENDED");
                        // restores the icon color.
                        imageView.setColorFilter(Color.parseColor("#ffffffff"), PorterDuff.Mode.MULTIPLY);
                        imageView.invalidate();
                        return true;
                }

                return false;
            }
        });

        textViewDropArea.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d(TAG, "textViewDropArea: ACTION_DRAG_STARTED");
                        // returns true to accept a drag event.
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(TAG, "textViewDropArea: ACTION_DRAG_ENTERED");
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        // http://stackoverflow.com/questions/14377498/android-drag-and-drop-for-textview-causing-exception
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(TAG, "textViewDropArea: ACTION_DRAG_EXITED");
                        return true;
                    case DragEvent.ACTION_DROP:
                        Log.d(TAG, "textViewDropArea: ACTION_DROP");
                        ClipData clipData = dragEvent.getClipData();
                        ClipData.Item item = clipData.getItemAt(0);
                        Toast.makeText(MainActivity.this, "Dropped: " + item.getText(), Toast.LENGTH_SHORT).show();
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(TAG, "textViewDropArea: ACTION_DRAG_ENDED");
                        return true;
                }

                return false;
            }
        });
    }
}
