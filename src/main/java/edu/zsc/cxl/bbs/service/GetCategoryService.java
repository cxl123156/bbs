package edu.zsc.cxl.bbs.service;

import java.util.List;

import edu.zsc.cxl.bbs.container.GetCategory;
import edu.zsc.cxl.bbs.container.PageModel;

public interface GetCategoryService {

	public List<GetCategory> getAllCategory(PageModel<GetCategory> pm);
}
