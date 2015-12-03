package org.sunger.net.entity;

import java.io.Serializable;

/**
 * 首页栏目分类
 * Created by sunger on 2015/10/26.
 */

public class CategoryEntity  implements Serializable {
    private int id;

    private String name;

    private String icon;

    private String color;

    private int type;

    private int has_content_rank;

    private int has_banner;

    private int index;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public void setHas_content_rank(int has_content_rank) {
        this.has_content_rank = has_content_rank;
    }

    public int getHas_content_rank() {
        return this.has_content_rank;
    }

    public void setHas_banner(int has_banner) {
        this.has_banner = has_banner;
    }

    public int getHas_banner() {
        return this.has_banner;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
