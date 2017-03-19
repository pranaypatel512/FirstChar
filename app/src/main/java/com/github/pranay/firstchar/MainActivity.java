package com.github.pranay.firstchar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.pranay.firstchar.adapter.AbstractRecyclerAdapter;
import com.github.pranay.firstchar.model.PersonModel;
import com.github.pranay.firstchar.viewholder.PersonDetailsViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_person_list)
    RecyclerView rvPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preparePersonList();

    }

    /**
     * Create temporary person list and fill in recycler view
     */
    private void preparePersonList() {
        String[] strings = getResources().getStringArray(R.array.array_temp_person_name);
        ArrayList<PersonModel> modelArrayList = new ArrayList<>();
        for (String personName : strings
                ) {
            modelArrayList.add(new PersonModel(personName));
        }

        AbstractRecyclerAdapter<PersonModel, PersonDetailsViewHolder> abstractRecyclerAdapter
                = new AbstractRecyclerAdapter<PersonModel, PersonDetailsViewHolder>(PersonModel.class, PersonDetailsViewHolder.class, R.layout.list_item_person_details) {
            @Override
            protected void populateViewHolder(PersonDetailsViewHolder viewHolder, PersonModel model, int position) {

                viewHolder.tvPersonName.setText(model.getPersonName());
                int color = R.color.colorPrimaryDark;

                if (position % 2 == 0) {
                    color = R.color.colorAccent;
                } else if (position % 3 == 0) {
                    color = R.color.colorPrimary;
                }

                viewHolder.ivPersonImage.setImageBitmap(createBitmap(MainActivity.this,
                        model.getPersonName(), color, Color.WHITE));

            }
        };
        abstractRecyclerAdapter.setItems(modelArrayList);
        rvPersonList.setLayoutManager(new LinearLayoutManager(this));
        rvPersonList.setAdapter(abstractRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            preparePersonList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @param context
     * @param name : string which use for getting first char
     * @param backGroundColor : image view background color
     * @param textColor : generate char textcolor
     * @return : return generated bitmap
     */
    public Bitmap createBitmap(Context context, String name, int backGroundColor, int textColor) {

        final char[] mFirstChar = new char[1];
        int size = 80;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        TextPaint paint = new TextPaint();
        Rect mBounds = new Rect();

        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);

        canvas.drawColor(backGroundColor);
        canvas.drawColor(ContextCompat.getColor(context, backGroundColor));

        mFirstChar[0] = Character.toUpperCase(name.charAt(0));

        paint.setTextSize(40f);
        paint.getTextBounds(mFirstChar, 0, 1, mBounds);
        canvas.drawText(mFirstChar, 0, 1, size / 2, size / 2
                + (mBounds.bottom - mBounds.top) / 2, paint);
        return bitmap;
    }

}
