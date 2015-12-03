package org.sunger.net.view;

import org.sunger.net.entity.CategoryEntity;

/**
 * Created by sunger on 15/10/26.
 */
public interface CategoryView {
    void showError();
    void addCategoryInfo(CategoryEntity entity);
    void bindCategoryData();
}
