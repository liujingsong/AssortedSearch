package bd.com.assort_search_library.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/26 9:16
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/26 9:16
 * @ModifyDescription : <Content>
 */
public class Category {
    /*可以类似：一级分类、二级分类等文本，但必须是主键唯一  primary-key-not-null*/
    String categoryId;
    /*文字标签*/
    List<String> tags;

    public Category(String categoryId) {
        this.categoryId = categoryId;
        this.tags = new ArrayList<>();
    }

    public String getCategoryId(){
        return this.categoryId;
    }


    public synchronized void add(String tag) {
        this.tags.add(tag);
    }

    public synchronized void add(int position, String tag) {
        this.tags.add(position, tag);
    }

    /*外部必须通过Category才能操控标签列表*/
    public synchronized List<String> getTags() {
        return Collections.unmodifiableList(this.tags);
    }

    public synchronized boolean removeTag(String tag) {
        return this.tags.remove(tag);
    }

    public synchronized String removeTag(int position) {
        return this.tags.remove(position);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", tags=" + tags +
                '}';
    }
}
