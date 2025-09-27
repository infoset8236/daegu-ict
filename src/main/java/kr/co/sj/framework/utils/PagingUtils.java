package kr.co.sj.framework.utils;

public class PagingUtils extends BeanUtils {

	@Override
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	private int rowCount; // 페이지에 표시할 데이터 count
	private int viewPage; // 보여질페이지번호

	private int startRowNum; // 읽어올 시작ROW값
	private int endRowNum; // 읽어올 마지막ROW값

	private int listRowNum = 1; // 목록에서 보여줄 번호값 세팅

	private int totalDataCount;

	private int totalPageCount = 0;
	private int startPageNum = 0;
	private int endPageNum = 0;
	private int listPageCount = 10;

	private int firstPageNum =1 ; // 맨앞페이지 이미지
	private int lastPageNum; // 맨뒤페이지 이미지

	private int prevPageNum; // 이전페이지
	private int nextPageNum; // 다음페이지

	private String search_type; // 검색구분
	private String search_text; // 검색어

	public PagingUtils() {
        // 기본 초기화 값 지정 가능
        this.viewPage = 1;
        this.rowCount = 10;
    }

	public PagingUtils(PagingUtils pagingUtils) {
		setPagingVar(pagingUtils);
	}

	public void setPagingUtils(PagingUtils pagingUtils) {
		setPagingVar(pagingUtils);
	}

	public String getPagingParam() {
		return getPagingParam(null);
	}

	public String getPagingParam(String mode) {
		StringBuffer sb = new StringBuffer();
		sb.append("viewPage = " + viewPage);
		sb.append("&rowCount = " + rowCount);
		if (search_type != null && !search_type.equals("")) {
			sb.append("&search_type = " + search_type);
		}
		if (search_text != null && !search_text.equals("")) {
			sb.append("&search_text = " + search_text);
		}
		return sb.toString();
	}

	private void setPagingVar(PagingUtils pagingUtils) {
		this.rowCount = pagingUtils.rowCount;
		this.viewPage = pagingUtils.viewPage;
		this.startRowNum = pagingUtils.startRowNum;
		this.endRowNum = pagingUtils.endRowNum;
		this.listRowNum = pagingUtils.listRowNum;
		this.totalDataCount =  pagingUtils.totalDataCount;
		this.totalPageCount =  pagingUtils.totalPageCount;
		this.startPageNum = pagingUtils.startPageNum;
		this.endPageNum =  pagingUtils.endPageNum;
		this.listPageCount = pagingUtils.listPageCount;
		this.firstPageNum = pagingUtils.firstPageNum;
		this.lastPageNum = pagingUtils.lastPageNum;
		this.prevPageNum =  pagingUtils.prevPageNum;
		this.nextPageNum = pagingUtils.nextPageNum;
		this.search_type = pagingUtils.search_type;
		this.search_text = pagingUtils.search_text;
	}

	private void pagingLogic() {
		startRowNum = ( viewPage - 1 ) * rowCount + 1;
		endRowNum 	= viewPage * rowCount;

		totalPageCount = (int)Math.ceil( this.totalDataCount / (double)this.rowCount );
		totalPageCount = totalPageCount==0?1:totalPageCount;

		startPageNum = ((this.viewPage-1) / this.listPageCount) * this.listPageCount + 1;
		endPageNum = startPageNum + this.listPageCount - 1;
		endPageNum = endPageNum==0?1:endPageNum;
		endPageNum = endPageNum > totalPageCount?totalPageCount:endPageNum;

		listRowNum = (totalDataCount - startRowNum)+1;

		firstPageNum = ((this.viewPage-1) / this.listPageCount) * this.listPageCount + 1;
		lastPageNum = firstPageNum + this.listPageCount - 1;
		lastPageNum = lastPageNum > totalPageCount?totalPageCount:lastPageNum;

		prevPageNum =  firstPageNum!=1?firstPageNum-1:0;
		nextPageNum = totalPageCount > lastPageNum?lastPageNum+1:0;

		firstPageNum = firstPageNum!=1?1:0;
	}

	public int getViewPage() {
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

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getRowCount() {
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
}
