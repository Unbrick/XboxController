
package com.unbrick.xbox.XboxUnityApi.Responses.GameListResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameListResponse {

    @SerializedName("Items")
    @Expose
    private List<Item> items = null;
    @SerializedName("Count")
    @Expose
    private Integer count;
    @SerializedName("Pages")
    @Expose
    private Integer pages;
    @SerializedName("Page")
    @Expose
    private Integer page;
    @SerializedName("Filter")
    @Expose
    private Object filter;
    @SerializedName("Category")
    @Expose
    private Object category;
    @SerializedName("Sort")
    @Expose
    private Object sort;
    @SerializedName("Direction")
    @Expose
    private Object direction;

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The Items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The Count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The Pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The Page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 
     * @return
     *     The filter
     */
    public Object getFilter() {
        return filter;
    }

    /**
     * 
     * @param filter
     *     The Filter
     */
    public void setFilter(Object filter) {
        this.filter = filter;
    }

    /**
     * 
     * @return
     *     The category
     */
    public Object getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The Category
     */
    public void setCategory(Object category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The sort
     */
    public Object getSort() {
        return sort;
    }

    /**
     * 
     * @param sort
     *     The Sort
     */
    public void setSort(Object sort) {
        this.sort = sort;
    }

    /**
     * 
     * @return
     *     The direction
     */
    public Object getDirection() {
        return direction;
    }

    /**
     * 
     * @param direction
     *     The Direction
     */
    public void setDirection(Object direction) {
        this.direction = direction;
    }

}
