package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import edu.zsc.cxl.bbs.entity.Category;
import edu.zsc.cxl.bbs.entity.Forum;

public interface CategoryService {

	public void saveCategory(Category category);
	public Category findCategoryByTittle(String Tittle);
	public void updateCategory(Category category);
	public void deleteCategory(Category category);
	public Category findCategoryById(Serializable id);
	public List<Category> findAllCategory();
	public int getCategoryNum();
	public HashMap<List<Category>, LinkedHashMap<Category, List<Forum>>> getForumAndCategory();
}
