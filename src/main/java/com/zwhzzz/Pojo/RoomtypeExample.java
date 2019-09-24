package com.zwhzzz.Pojo;

import java.util.ArrayList;
import java.util.List;

public class RoomtypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public RoomtypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andLivenumIsNull() {
            addCriterion("liveNum is null");
            return (Criteria) this;
        }

        public Criteria andLivenumIsNotNull() {
            addCriterion("liveNum is not null");
            return (Criteria) this;
        }

        public Criteria andLivenumEqualTo(Integer value) {
            addCriterion("liveNum =", value, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumNotEqualTo(Integer value) {
            addCriterion("liveNum <>", value, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumGreaterThan(Integer value) {
            addCriterion("liveNum >", value, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("liveNum >=", value, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumLessThan(Integer value) {
            addCriterion("liveNum <", value, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumLessThanOrEqualTo(Integer value) {
            addCriterion("liveNum <=", value, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumIn(List<Integer> values) {
            addCriterion("liveNum in", values, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumNotIn(List<Integer> values) {
            addCriterion("liveNum not in", values, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumBetween(Integer value1, Integer value2) {
            addCriterion("liveNum between", value1, value2, "livenum");
            return (Criteria) this;
        }

        public Criteria andLivenumNotBetween(Integer value1, Integer value2) {
            addCriterion("liveNum not between", value1, value2, "livenum");
            return (Criteria) this;
        }

        public Criteria andBednumIsNull() {
            addCriterion("bedNum is null");
            return (Criteria) this;
        }

        public Criteria andBednumIsNotNull() {
            addCriterion("bedNum is not null");
            return (Criteria) this;
        }

        public Criteria andBednumEqualTo(Integer value) {
            addCriterion("bedNum =", value, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumNotEqualTo(Integer value) {
            addCriterion("bedNum <>", value, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumGreaterThan(Integer value) {
            addCriterion("bedNum >", value, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bedNum >=", value, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumLessThan(Integer value) {
            addCriterion("bedNum <", value, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumLessThanOrEqualTo(Integer value) {
            addCriterion("bedNum <=", value, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumIn(List<Integer> values) {
            addCriterion("bedNum in", values, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumNotIn(List<Integer> values) {
            addCriterion("bedNum not in", values, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumBetween(Integer value1, Integer value2) {
            addCriterion("bedNum between", value1, value2, "bednum");
            return (Criteria) this;
        }

        public Criteria andBednumNotBetween(Integer value1, Integer value2) {
            addCriterion("bedNum not between", value1, value2, "bednum");
            return (Criteria) this;
        }

        public Criteria andRoomnumIsNull() {
            addCriterion("roomNum is null");
            return (Criteria) this;
        }

        public Criteria andRoomnumIsNotNull() {
            addCriterion("roomNum is not null");
            return (Criteria) this;
        }

        public Criteria andRoomnumEqualTo(Integer value) {
            addCriterion("roomNum =", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumNotEqualTo(Integer value) {
            addCriterion("roomNum <>", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumGreaterThan(Integer value) {
            addCriterion("roomNum >", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("roomNum >=", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumLessThan(Integer value) {
            addCriterion("roomNum <", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumLessThanOrEqualTo(Integer value) {
            addCriterion("roomNum <=", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumIn(List<Integer> values) {
            addCriterion("roomNum in", values, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumNotIn(List<Integer> values) {
            addCriterion("roomNum not in", values, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumBetween(Integer value1, Integer value2) {
            addCriterion("roomNum between", value1, value2, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumNotBetween(Integer value1, Integer value2) {
            addCriterion("roomNum not between", value1, value2, "roomnum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumIsNull() {
            addCriterion("avilableNum is null");
            return (Criteria) this;
        }

        public Criteria andAvilablenumIsNotNull() {
            addCriterion("avilableNum is not null");
            return (Criteria) this;
        }

        public Criteria andAvilablenumEqualTo(Integer value) {
            addCriterion("avilableNum =", value, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumNotEqualTo(Integer value) {
            addCriterion("avilableNum <>", value, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumGreaterThan(Integer value) {
            addCriterion("avilableNum >", value, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("avilableNum >=", value, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumLessThan(Integer value) {
            addCriterion("avilableNum <", value, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumLessThanOrEqualTo(Integer value) {
            addCriterion("avilableNum <=", value, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumIn(List<Integer> values) {
            addCriterion("avilableNum in", values, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumNotIn(List<Integer> values) {
            addCriterion("avilableNum not in", values, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumBetween(Integer value1, Integer value2) {
            addCriterion("avilableNum between", value1, value2, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andAvilablenumNotBetween(Integer value1, Integer value2) {
            addCriterion("avilableNum not between", value1, value2, "avilablenum");
            return (Criteria) this;
        }

        public Criteria andBooknumIsNull() {
            addCriterion("bookNum is null");
            return (Criteria) this;
        }

        public Criteria andBooknumIsNotNull() {
            addCriterion("bookNum is not null");
            return (Criteria) this;
        }

        public Criteria andBooknumEqualTo(Integer value) {
            addCriterion("bookNum =", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotEqualTo(Integer value) {
            addCriterion("bookNum <>", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumGreaterThan(Integer value) {
            addCriterion("bookNum >", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bookNum >=", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumLessThan(Integer value) {
            addCriterion("bookNum <", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumLessThanOrEqualTo(Integer value) {
            addCriterion("bookNum <=", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumIn(List<Integer> values) {
            addCriterion("bookNum in", values, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotIn(List<Integer> values) {
            addCriterion("bookNum not in", values, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumBetween(Integer value1, Integer value2) {
            addCriterion("bookNum between", value1, value2, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotBetween(Integer value1, Integer value2) {
            addCriterion("bookNum not between", value1, value2, "booknum");
            return (Criteria) this;
        }

        public Criteria andLivednumIsNull() {
            addCriterion("livedNum is null");
            return (Criteria) this;
        }

        public Criteria andLivednumIsNotNull() {
            addCriterion("livedNum is not null");
            return (Criteria) this;
        }

        public Criteria andLivednumEqualTo(Integer value) {
            addCriterion("livedNum =", value, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumNotEqualTo(Integer value) {
            addCriterion("livedNum <>", value, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumGreaterThan(Integer value) {
            addCriterion("livedNum >", value, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumGreaterThanOrEqualTo(Integer value) {
            addCriterion("livedNum >=", value, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumLessThan(Integer value) {
            addCriterion("livedNum <", value, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumLessThanOrEqualTo(Integer value) {
            addCriterion("livedNum <=", value, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumIn(List<Integer> values) {
            addCriterion("livedNum in", values, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumNotIn(List<Integer> values) {
            addCriterion("livedNum not in", values, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumBetween(Integer value1, Integer value2) {
            addCriterion("livedNum between", value1, value2, "livednum");
            return (Criteria) this;
        }

        public Criteria andLivednumNotBetween(Integer value1, Integer value2) {
            addCriterion("livedNum not between", value1, value2, "livednum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCustatusIsNull() {
            addCriterion("CuStatus is null");
            return (Criteria) this;
        }

        public Criteria andCustatusIsNotNull() {
            addCriterion("CuStatus is not null");
            return (Criteria) this;
        }

        public Criteria andCustatusEqualTo(Integer value) {
            addCriterion("CuStatus =", value, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusNotEqualTo(Integer value) {
            addCriterion("CuStatus <>", value, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusGreaterThan(Integer value) {
            addCriterion("CuStatus >", value, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("CuStatus >=", value, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusLessThan(Integer value) {
            addCriterion("CuStatus <", value, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusLessThanOrEqualTo(Integer value) {
            addCriterion("CuStatus <=", value, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusIn(List<Integer> values) {
            addCriterion("CuStatus in", values, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusNotIn(List<Integer> values) {
            addCriterion("CuStatus not in", values, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusBetween(Integer value1, Integer value2) {
            addCriterion("CuStatus between", value1, value2, "custatus");
            return (Criteria) this;
        }

        public Criteria andCustatusNotBetween(Integer value1, Integer value2) {
            addCriterion("CuStatus not between", value1, value2, "custatus");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}