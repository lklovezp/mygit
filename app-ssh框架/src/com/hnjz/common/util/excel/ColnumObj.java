package com.hnjz.common.util.excel;

/**
 * 封装Excel文件读取坐标列和sheet名
 * @author Administrator
 *
 */
public class ColnumObj {
	
	private SheetObj sheetObj;//Excel文件读取属性和业务处理类
	private int x;//行坐标
	private int y;//列坐标
	private String name;//列名
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SheetObj getSheetObj() {
		return sheetObj;
	}
	public void setSheetObj(SheetObj sheetObj) {
		this.sheetObj = sheetObj;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 取当前坐标值
	 * @param index
	 * @return
	 */
	public int[] getNextxy(int index)
	{
		int[] outInts = new int[]{0,0};
		int span = 0;
		if(this.getSheetObj().getSpan()>0)
		{
			span = this.getSheetObj().getSpan();
		}
		outInts[0]=this.sheetObj.getBeginx()+x+span*index;
		outInts[1]=this.sheetObj.getBeginy()+y;
		return outInts;
	}
	
	
}
