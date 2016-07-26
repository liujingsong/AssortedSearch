package bd.com.assort_search_library.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import bd.com.assort_search_library.R;
import bd.com.assort_search_library.data.Category;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/26 10:07
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/26 10:07
 * @ModifyDescription : <Content>
 */
public class AssortSearchView extends FrameLayout {


    private View mView;
    private LinearLayout mParent;
    private ClearEditText mSearchBar;

    public AssortSearchView(Context context) {
        super(context);
        init();
    }

    public AssortSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AssortSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mView = inflate(getContext(), R.layout.assort_search, this);
        mSearchBar = (ClearEditText) mView.findViewById(R.id.search_bar);
        mParent = (LinearLayout) mView.findViewById(R.id.parent);
    }

    /*获取搜索框控件，对其进行监听*/
    public ClearEditText getSearchBar() {
        return this.mSearchBar;
    }

    private int columnCount = 3;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void addCategory(Category category) {
        final View item = inflate(getContext(), R.layout.category_item, null);
        final LinearLayout tagsContainer = (LinearLayout) item.findViewById(R.id.tags_container);
        final TextView title = (TextView) item.findViewById(R.id.category_title);
        title.setText(category.getCategoryId());
        List<String> tags = category.getTags();

        int size = tags.size();
        int rowCount = tags.size() / columnCount + 1;
        int tagNum = 0;
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setPadding(6, 0, 6, 20);
            //row.setGravity(Gravity.CENTER);
            /*每行标签个数*/
            tagNum = size - rowIndex * columnCount > columnCount ? columnCount : size - rowIndex * columnCount;
            for (int col = 0; col < tagNum; col++) {
                row.addView(generateTagButton(tags.get(rowIndex * columnCount + col)));
            }
            tagsContainer.addView(row);
        }

        mParent.addView(item);
    }

    /**
     * 重新搜索时，需要清楚之前的搜索结果
     */
    public void clear() {
        mParent.removeViews(2, mParent.getChildCount() - 2);
    }

    private Button generateTagButton(String tag) {
        final SelectableButton button = new SelectableButton(getContext());
        button.setText(tag);
        /*这里宽高要做适配*/
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300, 120);
        lp.leftMargin = 20;
        lp.rightMargin = 20;
        button.setLayoutParams(lp);
        /*如果需要记录选中的标签*/
       /* button.setOnSelectedListener(new SelectableButton.SelectListener() {
            @Override
            public void onSelected() {
                //TODO 放到选中的集合
            }

            @Override
            public void onUnSelected() {
                //TODO 从选中的集合中移除
            }
        });*/
        return button;
    }

}
