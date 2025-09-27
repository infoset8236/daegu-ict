package kr.co.sj.framework.base;

import kr.co.sj.framework.utils.PagingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

public abstract class BaseService {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	public void setPaging(Model model, int totalDataCount, PagingUtils pagingUtils) {
		pagingUtils.setTotalDataCount(totalDataCount);
		model.addAttribute("paging", pagingUtils);
	}
}
