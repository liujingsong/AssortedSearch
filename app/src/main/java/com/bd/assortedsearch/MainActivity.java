package com.bd.assortedsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import bd.com.assort_search_library.data.Category;
import bd.com.assort_search_library.views.AssortSearchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final  AssortSearchView asv = (AssortSearchView) findViewById(R.id.assort_view);
        /*执行搜索*/
        asv.getSearchBar().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /*替换成自己网络请求后封装结果*/
                    test(asv);
                    return true;
                }
                return false;
            }
        });
        /*清空搜索*/
        asv.getSearchBar().setTextWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    asv.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 模拟请求后封装的数据
     * @param asv
     */
    public void test(AssortSearchView asv){
        Category category = new Category("一级分类");
        category.add("制造业");
        category.add("销售业");
        category.add("服务业");
        category.add("IT业");
        Category category2 = new Category("二级分类");
        category2.add("制造业");
        category2.add("销售业");
        category2.add("服务业");
        category2.add("IT业");
        Category category3 = new Category("三级分类");
        category3.add("制造业");
        category3.add("销售业");
        category3.add("服务业");
        category3.add("IT业");
        Category category4 = new Category("四级分类");
        category4.add("制造业");
        category4.add("销售业");
        category4.add("服务业");
        category4.add("IT业");


        asv.addCategory(category);
        asv.addCategory(category2);
        asv.addCategory(category3);
        asv.addCategory(category4);
    }

}
