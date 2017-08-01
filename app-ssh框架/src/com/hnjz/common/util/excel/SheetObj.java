package com.hnjz.common.util.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Excel文件读取属性和业务处理类
 * @author Administrator
 *
 */
public class SheetObj {

    private ConfigObj       configObj;                          //封装Xml文件属性类
    private String          key;
    private String          name;                               //sheet名称
    private int             beginx = 0;                         //行开始坐标
    private int             beginy = 0;                         //列开始坐标
    private int             endx   = 0;                         //行结束坐标
    private int             endy   = 0;                         //列结束坐标
    private int             span   = 0;                         //行间隔

    private List<ColnumObj> list   = new ArrayList<ColnumObj>(); //读取的行列表
    private String          clazz;                              //反射类名

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ConfigObj getConfigObj() {
        return configObj;
    }

    public void setConfigObj(ConfigObj configObj) {
        this.configObj = configObj;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<ColnumObj> getList() {
        return list;
    }

    public void setList(List<ColnumObj> list) {
        this.list = list;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    private int[] getNextxy(int index, int y) {
        int[] outInts = new int[] { 0, 0 };
        int span = 0;
        if (this.getSpan() > 0) {
            span = this.getSpan();
        }
        outInts[0] = this.getBeginx() + span * index;
        outInts[1] = this.getBeginy() + y;
        return outInts;
    }

    /**
     * 取指定列值
     * @param workbook
     * 		Excel工作表
     * @param index
     * 		索引值
     * @return Object
     * @throws Exception
     */
    public Object getObject(HSSFWorkbook workbook, int index) throws Exception {
        Object[] args = new Object[0];
        Object outObject = ReflectUtil.newInstance(this.clazz, args);
        boolean isnull = true;
        for (ColnumObj colnumObj : this.getList()) {
            int[] xy = colnumObj.getNextxy(index);
            String outStr = ExcelUtil.getValue(workbook, name, xy[0], xy[1]);
            if (StringUtils.isNotBlank(outStr)) {
                isnull = false;
            }
            //String outStr = "test";
            String methodName = "set" + colnumObj.getName().substring(0, 1).toUpperCase()
                                + colnumObj.getName().substring(1);
            Object[] args2 = new Object[1];
            args2[0] = outStr;

            ReflectUtil.invokeMethod(outObject, methodName, args2);
        }
        if (isnull) {
            outObject = null;
        }
        return outObject;
    }

    /**
     * 取指定列值
     * @param workbook
     * 		Excel工作表
     * @param index
     * 		索引值
     * @return Object
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean setObject(HSSFWorkbook workbook, Object obj, String sheetName, int index)
                                                                                            throws Exception {
        boolean outBoolean = true;
        if (this.getList() == null || this.getList().size() <= 0) {

            if ("Array".equals(this.clazz)) {
                Object[] objs = (Object[]) obj;
                int i = 0;
                for (Object o : objs) {
                    int[] xy = this.getNextxy(index, i);
                    Object value = o;
                    if (value != null) {
                        outBoolean &= ExcelUtil.setValue(workbook, value.toString(), sheetName,
                            xy[0], xy[1]);
                    }
                    i++;
                }
            } else {
                int cx = this.getBeginx() + span * index;
                outBoolean &= ExcelUtil.setValue(workbook, obj.toString(), name, cx,
                    this.getBeginy());
            }

        } else {

            if ("Array".equals(this.clazz)) {

                for (ColnumObj colnumObj : this.getList()) {
                    int[] xy = colnumObj.getNextxy(index);
                    //String outStr = "test";
                    String methodName = colnumObj.getName();
                    Object[] objs = (Object[]) obj;
                    Object value = objs[Integer.parseInt(methodName)];
                    if (value != null) {
                        outBoolean &= ExcelUtil.setValue(workbook, value.toString(), sheetName,
                            xy[0], xy[1]);
                    }
                }
            } else if ("Map".equals(this.clazz)) {
                for (ColnumObj colnumObj : this.getList()) {
                    int[] xy = colnumObj.getNextxy(index);
                    Map<String, Object> map = (Map<String, Object>) obj;
                    Object value = map.get(colnumObj.getName());
                    if (value != null) {
                        outBoolean &= ExcelUtil.setValue(workbook, value.toString(), name, xy[0],
                            xy[1]);
                    }

                }
            } else {
                for (ColnumObj colnumObj : this.getList()) {
                    int[] xy = colnumObj.getNextxy(index);
                    //String outStr = "test";
                    String methodName = "get" + colnumObj.getName().substring(0, 1).toUpperCase()
                                        + colnumObj.getName().substring(1);
                    Object[] args2 = new Object[0];
                    Object value = ReflectUtil.invokeMethod(obj, methodName, args2);
                    if (value != null) {
                        outBoolean &= ExcelUtil.setValue(workbook, value.toString(), name, xy[0],
                            xy[1]);
                    }

                }
            }

        }
        return outBoolean;
    }

    /**
     * 传入Excel工作表,返回读取列值
     * @param workbook
     * 		Excel工作表
     * @return List<Object>
     * 		返回读取值
     * @throws Exception
     */
    public boolean setObjectList(HSSFWorkbook workbook, String sheetName, List<Object> objList)
                                                                                               throws Exception {
        boolean outBoolean = true;

        if (objList == null) {
            return outBoolean;
        }
        int index = 0;
        if (this.getSpan() <= 0)//行间隔小于等于0
        {
            if (null != this.getList() && this.getList().size() > 0)//列表值不为空
            {

                Object object = objList.get(index);
                return this.setObject(workbook, object, sheetName, index);

            } else {
                Object object = (Object) objList.get(index);
                return this.setObject(workbook, object, sheetName, index);
            }

        } else//行间隔大于0
        {
            if (null != this.getList() && this.getList().size() > 0)//列表值不为空
            {
                boolean iscontinue = true;
                int objLength = objList.size();
                while (iscontinue) {
                    int cx = this.getBeginx() + span * index;

                    if (index >= objLength || cx > this.getEndx() && this.getEndx() > 0) {
                        iscontinue = false;
                    } else {

                        outBoolean &= this
                            .setObject(workbook, objList.get(index), sheetName, index);
                        index++;
                    }
                }
                return true;
            } else //当只填写了beginX和beginY坐标时直接读出
            {
                boolean iscontinue = true;
                int objLength = objList.size();
                while (iscontinue) {

                    int cx = this.getBeginx() + span * index;

                    if (index >= objLength || cx > this.getEndx() && this.getEndx() > 0) {
                        iscontinue = false;
                    } else {
                        outBoolean &= this
                            .setObject(workbook, objList.get(index), sheetName, index);
                        index++;
                    }
                }
                return outBoolean;
            }

        }
    }

    /**
     * 传入Excel工作表,返回读取列值
     * @param workbook
     * 		Excel工作表
     * @return List<Object>
     * 		返回读取值
     * @throws Exception
     */
    public List<Object> getObjectList(HSSFWorkbook workbook) throws Exception {
        List<Object> outList = new ArrayList<Object>();
        int index = 0;
        if (this.getSpan() <= 0)//行间隔小于等于0
        {
            if (null != this.getList() && this.getList().size() > 0)//列表值不为空
            {
                //当填写了beginX和beginY坐标和取值List<ColnumObj>时读出list中对应的值，不再读beginX beginY坐标的值了
                //这里要注意一点读list中的内容时会将beginX和beginY的值加到ColnumObj对象的x和y中在取值。
                Object object = this.getObject(workbook, index);
                outList.add(object);
                return outList;
            } else {
                //当只填写了beginX和beginY坐标时直接读出
                String outStr = ExcelUtil.getValue(workbook, name, this.getBeginx(),
                    this.getBeginy());
                outList.add(outStr);
                return outList;
            }

        } else//行间隔大于0
        {
            if (null != this.getList() && this.getList().size() > 0)//列表值不为空
            {
                boolean iscontinue = true;
                while (iscontinue) {
                    int cx = this.getBeginx() + span * index;
                    Object object = this.getObject(workbook, index);
                    if (object == null || cx > this.getEndx() && this.getEndx() > 0) {
                        iscontinue = false;
                    } else {
                        outList.add(object);
                        index++;
                    }
                }
                return outList;
            } else //当只填写了beginX和beginY坐标时直接读出
            {
                boolean iscontinue = true;
                while (iscontinue) {
                    int cx = this.getBeginx() + span * index;
                    String outStr = ExcelUtil.getValue(workbook, name, cx, this.getBeginy());

                    if (StringUtils.isBlank(outStr) || cx > this.getEndx() && this.getEndx() > 0) {
                        iscontinue = false;
                    } else {
                        outList.add(outStr);
                        index++;
                    }
                }
                return outList;
            }

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeginx() {
        return beginx;
    }

    public void setBeginx(int beginx) {
        this.beginx = beginx;
    }

    public int getBeginy() {
        return beginy;
    }

    public void setBeginy(int beginy) {
        this.beginy = beginy;
    }

    public int getEndx() {
        return endx;
    }

    public void setEndx(int endx) {
        this.endx = endx;
    }

    public int getEndy() {
        return endy;
    }

    public void setEndy(int endy) {
        this.endy = endy;
    }
}
