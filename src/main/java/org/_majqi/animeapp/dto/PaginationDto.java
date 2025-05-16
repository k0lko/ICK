package org._majqi.animeapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaginationDto {
    @JsonProperty("last_visible_page")
    private String lastVisiblePage;
    @JsonProperty("has_next_page")
    private boolean hasNextPage;
    @JsonProperty("current_page")
    private String currentPage;
    private Items items;

    public String getLastVisiblePage() {
        return lastVisiblePage;
    }

    public void setLastVisiblePage(String lastVisiblePage) {
        this.lastVisiblePage = lastVisiblePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public static class Items {
        private String count;
        private String total;
        @JsonProperty("per_page")
        private String perPage;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPerPage() {
            return perPage;
        }

        public void setPerPage(String perPage) {
            this.perPage = perPage;
        }
    }

}

