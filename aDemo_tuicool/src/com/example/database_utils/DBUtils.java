package com.example.database_utils;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.model.Article;
import com.example.model.upgradelog.Item;
import com.example.model.upgradelog.Upgradelog;

public final class DBUtils
{
	public DBUtils(){};
	//添加收藏
	public static void insert(Article mArticle)
	{
		// 创建一个新对象，与原对象相同的内容 ，为Model实现Cloneable接口,重写clone()方法。可以重复添加和取消收藏
		Article newArticle = mArticle.clone();
		newArticle.save();
	}
	// 取消收藏
	public static void delete(String param,Article mArticle)
	{
		new Delete().from(Article.class)
		.where(param, mArticle.getArticleId()).executeSingle();
	}
	// 查询是否有收藏
	public static boolean hasFavor(String param,Article mArticle)
	{
		Article article = new Select().from(Article.class)
				.where(param, mArticle.getArticleId()).executeSingle();
		return article != null;
	}
	//查询所有收藏
	public static List<Article> queryFavor()
	{
		return new Select().from(Article.class).execute();
	}
	
	// 插入更新日志表
	public static void insertUpgradeLog(Item item)
	{
		Item newItem = new Item();
		newItem.save();
	}
	//查询更新的日志
		public static List<Item> queryUpgradeLog()
		{
			return new Select().from(Item.class).execute();
		}
}
