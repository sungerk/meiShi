package org.sunger.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.sunger.net.entity.CategoryEntity;
import org.sunger.net.model.CategoryModel;
import org.sunger.net.presenter.CategoryPresenter;
import org.sunger.net.support.okhttp.callback.ResultCallback;
import org.sunger.net.view.CategoryView;

import java.util.List;

/**
 * Created by sunger on 2015/10/27.
 */
public class CategoryPresenterImpl implements CategoryPresenter {

    private CategoryModel model;
    private CategoryView view;

    public CategoryPresenterImpl(CategoryView view) {
        this.view = view;
        model = new CategoryModel();
    }

    @Override
    public void getCategory() {
        model.getCategory(new ResultCallback<List<CategoryEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<CategoryEntity> response) {
                for (CategoryEntity entity : response) {
                    entity.setName(entity.getName().trim().replaceAll("#", ""));
                    view.addCategoryInfo(entity);
                }
                view.bindCategoryData();

            }
        });
    }

}
