package com.hnjz.common.util.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Excel�ļ���ȡ���Ժ�ҵ������
 * @author Administrator
 *
 */
public class SheetObj {

    private ConfigObj       configObj;                          //��װXml�ļ�������
    private String          key;
    private String          name;                               //sheet����
    private int             beginx = 0;                         //�п�ʼ����
    private int             beginy = 0;                         //�п�ʼ����
    private int             endx   = 0;                         //�н�������
    private int             endy   = 0;                         //�н�������
    private int             span   = 0;                         //�м��

    private List<ColnumObj> list   = new ArrayList<ColnumObj>(); //��ȡ�����б�
    private String          clazz;                              //��������

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
     * ȡָ����ֵ
     * @param workbook
     * 		Excel������
     * @param index
     * 		����ֵ
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
     * ȡָ����ֵ
     * @param workbook
     * 		Excel������
     * @param index
     * 		����ֵ
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
     * ����Excel������,���ض�ȡ��ֵ
     * @param workbook
     * 		Excel������
     * @return List<Object>
     * 		���ض�ȡֵ
     * @throws Exception
     */
    public boolean setObjectList(HSSFWorkbook workbook, String sheetName, List<Object> objList)
                                                                                               throws Exception {
        boolean outBoolean = true;

        if (objList == null) {
            return outBoolean;
        }
        int index = 0;
        if (this.getSpan() <= 0)//�м��С�ڵ���0
        {
            if (null != this.getList() && this.getList().size() > 0)//�б�ֵ��Ϊ��
            {

                Object object = objList.get(index);
                return this.setObject(workbook, object, sheetName, index);

            } else {
                Object object = (Object) objList.get(index);
                return this.setObject(workbook, object, sheetName, index);
            }

        } else//�м������0
        {
            if (null != this.getList() && this.getList().size() > 0)//�б�ֵ��Ϊ��
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
            } else //��ֻ��д��beginX��beginY����ʱֱ�Ӷ���
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
     * ����Excel������,���ض�ȡ��ֵ
     * @param workbook
     * 		Excel������
     * @return List<Object>
     * 		���ض�ȡֵ
     * @throws Exception
     */
    public List<Object> getObjectList(HSSFWorkbook workbook) throws Exception {
        List<Object> outList = new ArrayList<Object>();
        int index = 0;
        if (this.getSpan() <= 0)//�м��С�ڵ���0
        {
            if (null != this.getList() && this.getList().size() > 0)//�б�ֵ��Ϊ��
            {
                //����д��beginX��beginY�����ȡֵList<ColnumObj>ʱ����list�ж�Ӧ��ֵ�����ٶ�beginX beginY�����ֵ��
                //����Ҫע��һ���list�е�����ʱ�ὫbeginX��beginY��ֵ�ӵ�ColnumObj�����x��y����ȡֵ��
                Object object = this.getObject(workbook, index);
                outList.add(object);
                return outList;
            } else {
                //��ֻ��д��beginX��beginY����ʱֱ�Ӷ���
                String outStr = ExcelUtil.getValue(workbook, name, this.getBeginx(),
                    this.getBeginy());
                outList.add(outStr);
                return outList;
            }

        } else//�м������0
        {
            if (null != this.getList() && this.getList().size() > 0)//�б�ֵ��Ϊ��
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
            } else //��ֻ��д��beginX��beginY����ʱֱ�Ӷ���
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