package kr.co.sj.framework.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PagingUtilsFromMap extends BeanUtilsFromMap {
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    private int rowCount = 10; // 페이지에 표시할 데이터 count
    private int viewPage = 1; // 보여질페이지번호

    private int startRowNum = 1; // 읽어올 시작ROW값
    private int endRowNum = 10; // 읽어올 마지막ROW값

    private int listRowNum = 1; // 목록에서 보여줄 번호값 세팅

    private int totalDataCount = 0;

    private int totalPageCount = 0;
    private int startPageNum  = 0;
    private int endPageNum = 0;
    private int listPageCount = 10;

    private String sortField = "add_date"; // 검색용 : 정렬기준 필드
    private String sortType =  "DESC"; // 검색용 : 정렬방법(ASC,DESC)

    private int firstPageNum = 1; // 맨앞페이지 이미지
    private int lastPageNum = 0; // 맨뒤페이지 이미지

    private int prevPageNum = 0; // 이전페이지
    private int nextPageNum = 0; // 다음페이지

    private String search_type; // 검색구분
    private String search_text; // 검색어

    public PagingUtilsFromMap() {
        if (StringUtils.equals(getSortField(), "add_date")) {
            setSortField("TITLE");
        }
    }

    public PagingUtilsFromMap(PagingUtilsFromMap pagingUtilsFromMap) {
        setPagingVar(pagingUtilsFromMap);
    }

    public void setPagingUtils(PagingUtilsFromMap pagingUtilsFromMap) {
        setPagingVar(pagingUtilsFromMap);
    }

    public String getPagingParam() {
        return getPagingParam(null);
    }

    public String getPagingParam(String mode) {
        StringBuffer sb = new StringBuffer();
        sb.append("viewPage = " + getViewPage());
        sb.append("&rowCount = " + getRowCount());
        if (search_type != null && !search_type.equals("")) {
            sb.append("&search_type = " + search_type);
        }
        if (search_text != null && !search_text.equals("")) {
            sb.append("&search_text = " + search_text);
        }
        if (sortField != null && !sortField.equals("")) {
            sb.append("&sortField = " + sortField);
        }
        if (sortType != null && !sortType.equals("")) {
            sb.append("&sortType = " + sortType);
        }
        return sb.toString();
    }

    private void setPagingVar(PagingUtilsFromMap pagingUtilsFromMap) {
        this.rowCount = pagingUtilsFromMap.getRowCount();
        this.viewPage = pagingUtilsFromMap.getViewPage();
        this.startRowNum = pagingUtilsFromMap.startRowNum;
        this.endRowNum = pagingUtilsFromMap.endRowNum;
        this.listRowNum = pagingUtilsFromMap.listRowNum;
        this.totalDataCount =  pagingUtilsFromMap.totalDataCount;
        this.totalPageCount =  pagingUtilsFromMap.totalPageCount;
        this.startPageNum = pagingUtilsFromMap.startPageNum;
        this.endPageNum =  pagingUtilsFromMap.endPageNum;
        this.listPageCount = pagingUtilsFromMap.listPageCount;
        this.sortField = pagingUtilsFromMap.sortField;
        this.sortType = pagingUtilsFromMap.sortType;
        this.firstPageNum = pagingUtilsFromMap.firstPageNum;
        this.lastPageNum = pagingUtilsFromMap.lastPageNum;
        this.prevPageNum =  pagingUtilsFromMap.prevPageNum;
        this.nextPageNum = pagingUtilsFromMap.nextPageNum;
        this.search_type = pagingUtilsFromMap.search_type;
        this.search_text = pagingUtilsFromMap.search_text;

        if(!isEmpty(pagingUtilsFromMap.getMenu_url())) {
            this.setMenu_url(pagingUtilsFromMap.getMenu_url());
        }
        if(!isEmpty(pagingUtilsFromMap.getEditMode())) {
            this.setEditMode(pagingUtilsFromMap.getEditMode());
        }
        super.setMenu_idx(pagingUtilsFromMap.getMenu_idx());
    }

    private boolean isEmpty(String str) {
        if(str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void pagingLogic() {
        startRowNum = (getViewPage() - 1) * getRowCount() + 1;
        endRowNum = getViewPage() * getRowCount();

        totalPageCount = (int) Math.ceil(this.totalDataCount / (double) this.getRowCount());
        totalPageCount = totalPageCount == 0 ? 1 : totalPageCount;

        startPageNum = ((this.getViewPage() - 1) / this.listPageCount) * this.listPageCount + 1;
        endPageNum = startPageNum + this.listPageCount - 1;
        endPageNum = endPageNum == 0 ? 1 : endPageNum;
        endPageNum = endPageNum > totalPageCount ? totalPageCount : endPageNum;

        listRowNum = (totalDataCount - startRowNum) + 1;

        firstPageNum = ((this.getViewPage() - 1) / this.listPageCount) * this.listPageCount + 1;
        lastPageNum = firstPageNum + this.listPageCount - 1;
        lastPageNum = lastPageNum > totalPageCount ? totalPageCount : lastPageNum;

        prevPageNum = firstPageNum != 1 ? firstPageNum - 1 : 0;
        nextPageNum = totalPageCount > lastPageNum ? lastPageNum + 1 : 0;

        firstPageNum = firstPageNum != 1 ? 1 : 0;
    }

    /**
     * 페이징 처리시 상단부분 query
     * @return
     */
    public String getPagingTop() {
        StringBuffer sb = new StringBuffer();
        sb
            .append(" SELECT * FROM ( " )
            .append("   SELECT rownum AS rnum, Z.* FROM ( " );

        return sb.toString();
    }

    /**
     * 페이징 처리시 하단부분 query
     * @return
     */
    public String getPagingBottom() {
        StringBuffer sb = new StringBuffer();
        sb
            .append("   ) Z WHERE ROWNUM <= " + endRowNum )
            .append(" ) WHERE rnum >= " + startRowNum );

        return sb.toString();
    }

    public String getOrderByNO() {
        if(this.sortField == null || "".equals(this.sortField)) {
            return "ROWNUM NO,";
        } else {
            return "ROW_NUMBER() OVER(ORDER BY " + sortField + " " + sortType + ") AS NO,";
        }
    }

    public String getConnectPagingHead() {
        StringBuffer sb = new StringBuffer();
        sb
            .append("SELECT * FROM ( ")
            .append("  SELECT PAGING_TABLE.* FROM ( ");


        return " SELECT * FROM ( " +
            " 	SELECT * FROM ( " +
            "		SELECT ROWNUM NO, PAGING_TABLE.* " +
            "    	FROM( ";
    }


    public String getConnectPagingTail() {
        return "     	) PAGING_TABLE " +
            "     ) " +
            "     WHERE NO <= " + endRowNum + " ) " +
            " WHERE NO >= " + startRowNum;
    }

    public String getConnectOrderBy() {
        StringBuffer sb = new StringBuffer("CONNECT BY PRIOR BOARD_SEQ = PARENT_SEQ START WITH PARENT_SEQ = 0 ");
        sb.append("ORDER SIBLINGS BY ");
        if( this.sortField != null && !"".equals(this.sortField))
            sb.append( sortField + " " + sortType + ", ");
        sb.append("GROUP_SEQ DESC , BOARD_SEQ ASC ");
        return sb.toString();
    }

    public int getViewPage() {
        if (map.get("viewPage") != null) {
            return Integer.parseInt((String) map.get("viewPage"));
        }
        return viewPage;
    }

    public void setViewPage(int viewPage) {
        this.viewPage = viewPage;
        //setPagingQueryVar();
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public int getRowCount() {
        if (map.get("rowCount") != null) {
            return Integer.parseInt((String) map.get("rowCount"));
        }

        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalDataCount() {
        return totalDataCount;
    }

    public void setTotalDataCount(int totalDataCount) {
        this.totalDataCount = totalDataCount;
        pagingLogic();
        map.put("pagingTop", getPagingTop());
        map.put("pagingBottom", getPagingBottom());
    }

    public int getStartPageNum() {
        return startPageNum;
    }

    public void setStartPageNum(int startPageNum) {
        this.startPageNum = startPageNum;
    }

    public int getEndPageNum() {
        return endPageNum;
    }

    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }

    public int getListPageCount() {
        return listPageCount;
    }

    public void setListPageCount(int listPageCount) {
        this.listPageCount = listPageCount;
    }

    public int getFirstPageNum() {
        return firstPageNum;
    }

    public void setFirstPageNum(int firstPageNum) {
        this.firstPageNum = firstPageNum;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getPrevPageNum() {
        return prevPageNum;
    }

    public void setPrevPageNum(int prevPageNum) {
        this.prevPageNum = prevPageNum;
    }

    public int getListRowNum() {
        return listRowNum;
    }

    public void setListRowNum(int listRowNum) {
        this.listRowNum = listRowNum;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getSearch_text() {
        return search_text;
    }

    public void setSearch_text(String search_text) {
        this.search_text = search_text;
    }


    /**
     * 페이징 처리시 상단부분 query
     * @return
     */
    public String getPagingTopMySql() {
        StringBuffer sb = new StringBuffer();
        sb
            .append(" SELECT * FROM ( " );
        //.append("   SELECT rownum AS rnum, Z.* FROM ( " );

        return sb.toString();
    }

    /**
     * 페이징 처리시 하단부분 query
     * @return
     */
    public String getPagingBottomMySql() {
        StringBuffer sb = new StringBuffer();
        int startRowNumMysql = startRowNum -1;
        int endRowNumMysql = endRowNum -1;
        sb
            //.append("   ) Z WHERE ROWNUM <= " + endRowNum )
            //.append(" ) WHERE rnum >= " + startRowNum );
            .append(" ) as Z limit " + startRowNumMysql+","+ endRowNumMysql );

        return sb.toString();
    }

    public Map setPagingUtil() {
        return map;
    }
}
