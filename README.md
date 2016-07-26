
## 动态分类搜索

**效果图：**

![search_tag.jpg](/art/search_tag.jpg)

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

**AssortSearchView.java类的实现**


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


原理很简单，注释有比较清楚的描述。

**使用**

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile '.liujingsong:AssortedSearch:83c0839b04'
	}
