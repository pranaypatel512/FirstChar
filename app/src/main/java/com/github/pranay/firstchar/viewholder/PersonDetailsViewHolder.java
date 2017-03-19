package com.github.pranay.firstchar.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.github.pranay.firstchar.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pranay.
 */

public class PersonDetailsViewHolder extends BaseViewHolder {

    @BindView(R.id.ivPersonImage)
    public CircleImageView ivPersonImage;

    @BindView(R.id.tvPersonName)
    public AppCompatTextView tvPersonName;

    public PersonDetailsViewHolder(View itemView) {
        super(itemView);
    }
}
