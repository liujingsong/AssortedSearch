package com.bd.assortedsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        asv.getSearchBar().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    test(asv);
                    return true;
                }
                return false;
            }
        });

       // asv.clear(); /*清楚分类*/

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
        asv.addCategory(category);
        asv.addCategory(category2);
    }

}
