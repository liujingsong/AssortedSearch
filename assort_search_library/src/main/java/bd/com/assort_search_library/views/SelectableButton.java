package bd.com.assort_search_library.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import bd.com.assort_search_library.R;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/26 14:07
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/26 14:07
 * @ModifyDescription : <Content>
 */
public class SelectableButton extends Button {
    private SelectListener mSelectListener;

    public SelectableButton(Context context) {
        super(context);
        init();
    }

    public SelectableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnSelectedListener(SelectListener selectListener){
        this.mSelectListener = selectListener;
    }


    public void init() {
        SelectableButton.this.setBackgroundResource(R.drawable.selector_tag);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectableButton.this.setSelected(!SelectableButton.this.isSelected());
                if (SelectableButton.this.isSelected()) {
                    if (null != mSelectListener) {
                        mSelectListener.onSelected();
                    }
                }else{
                    if (null != mSelectListener) {
                        mSelectListener.onUnSelected();
                    }
                }
            }
        });
    }

    public interface SelectListener {
        void onSelected();
        void onUnSelected();
    }
}
