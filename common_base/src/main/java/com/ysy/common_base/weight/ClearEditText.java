package com.ysy.common_base.weight;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ysy.common_base.R;

/**
 * author : YuShengyang
 * date   ：2020/5/19
 * desc   ：
 */
public class ClearEditText extends LinearLayout implements TextWatcher, View.OnClickListener {
    EditText editText;
    ImageButton ib_close;

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_clear_edit_text, this, true);
        editText = findViewById(R.id.et_content);
        ib_close = findViewById(R.id.iv_close);
        ib_close.setOnClickListener(this);
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ib_close.setVisibility(TextUtils.isEmpty(s.toString()) ? View.GONE : View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public String getText() {
        return editText.getText().toString();
    }

    @Override
    public void onClick(View v) {
        editText.setText("");
    }
}
