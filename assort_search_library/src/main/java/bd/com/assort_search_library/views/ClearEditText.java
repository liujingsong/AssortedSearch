package bd.com.assort_search_library.views;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

import bd.com.assort_search_library.R;

public class ClearEditText extends AutoCompleteTextView
		implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

	private Drawable mClearTextIcon;
	private OnFocusChangeListener mOnFocusChangeListener;
	private OnTouchListener mOnTouchListener;
	private TextWatcher mTextWatcher;

	public ClearEditText(final Context context) {
		super(context);
		init(context);
	}

	public ClearEditText(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ClearEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(final Context context) {
		final Drawable drawable = ContextCompat.getDrawable(context, R.drawable.abc_ic_clear_mtrl_alpha);
		final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
		DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
		mClearTextIcon = wrappedDrawable;
		mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());
		setClearIconVisible(false);
		super.setOnTouchListener(this);
		super.setOnFocusChangeListener(this);
		addTextChangedListener(this);
	}

	public void setTextWatcher(TextWatcher mTextWatcher) {
		this.mTextWatcher = mTextWatcher;
	}

	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener l) {
		mOnFocusChangeListener = l;
	}

	@Override
	public void setOnTouchListener(OnTouchListener l) {
		mOnTouchListener = l;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			setClearIconVisible(getText().length() > 0);
		} else {
			setClearIconVisible(false);
		}
		if (mOnFocusChangeListener != null) {
			mOnFocusChangeListener.onFocusChange(v, hasFocus);
		}
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		final int x = (int) motionEvent.getX();
		if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
			if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
				setError(null);
				setText("");
			}
			return true;
		}
		return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
	}

	@Override
	public final void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
		if (isFocused()) {
			setClearIconVisible(text.length() > 0);
		}

		if (null != this.mTextWatcher) {
			this.mTextWatcher.onTextChanged(text, start, lengthBefore, lengthAfter);
		}

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		if (null != this.mTextWatcher) {
			this.mTextWatcher.beforeTextChanged(s, start, count, after);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (null != this.mTextWatcher) {
			this.mTextWatcher.afterTextChanged(s);
		}
	}

	private void setClearIconVisible(final boolean visible) {
		mClearTextIcon.setVisible(visible, false);
		final Drawable[] compoundDrawables = getCompoundDrawables();
		setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], visible ? mClearTextIcon : null,
				compoundDrawables[3]);
	}
}
