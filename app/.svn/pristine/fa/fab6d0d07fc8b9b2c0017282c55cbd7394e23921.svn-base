package com.hnjz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class JsonToString {
	  // json字符串转
    private static String diskListContentA =
        "[{\"n1\":\"asd\",\"n2\":22,\"n3\":\"45.40GB\"," + "\"n4\":\"qwerty\",\"n5\":\"asd\",}," + "{\"n1\":\"local\","
            + "\"n2\":1,\"n3\":\"279.40GB\",\"n4\":\"ST3300656SS\",\"n5\":\"\\/devm\\/d0\"}]";

    
    private static String  diskListContent="[{\"ARCHIVES_TIME_\":\"2017-07-17\",\"ARCHIVES_USER_\":\"4028e4484d65d677014d6b492e5f0297\",\"AREAID_\":\"4028e4484d656b9e014d6579282e0008\",\"BLRSFXYBC_\":\"\",\"CODE_\":\"\",\"CREATEBY_\":\"4028e4484d65d677014d6b492e5f0297\",\"CREATED_\":\"2017-07-17\",\"DJRID_\":\"4028e4484d65d677014d6b492e5f0297\",\"EMERGENCY_\":\"1\",\"END_TIME_\":\"2017-08-06\",\"EXTINFO_\":\"\",\"FLOWID_\":\"generalTask1\",\"ID_\":\"40288ac45d4e3f9d015d4f2eae9e0010\",\"ISACTIVE_\":\"Y\",\"ISXP_\":\"0\",\"IS_OVER_\":\"1\",\"JLR_ID_\":\"\",\"JSRIDS_\":\"\",\"JSR_\":\"4028e4484d65d677014d6b492e5f0297\",\"NEXT_ACTIONS_\":\"sh,\",\"NEXT_OPER_\":\"\",\"PID_\":\"\",\"PROCESS_ID_\":\"\",\"PSYJ_\":\"12312312\",\"PTR_IDS_\":\"\",\"PTR_NAMES_\":\"\",\"RWMCRQ_\":\"2017-07-17\",\"SBR_\":\"\",\"SBSJ_\":\"\",\"SECURITY_\":\"1\",\"SFDB_\":\"\",\"SHILIID_\":\"HZ288ac45d4dfd71015d4f2eb1bc0213\",\"SHRIDS_\":\"\",\"SJID_\":\"\",\"SOURCE_\":\"8\",\"START_TIME_\":\"2017-07-17\",\"STATE_\":\"09\",\"TASK_ID_\":\"\",\"THRIDS_\":\"4028e4484d65d677014d6b492e5f0297,\",\"TRACKID_\":\"HZ288ac45d4dfd71015d4f2eb1f20214\",\"UPDATEBY_\":\"4028e4484d65d677014d6b492e5f0297\",\"UPDATED_\":\"2017-07-17\",\"ZXTIME_\":\"2017-07-17\",\"WORKNAMESTYLE_\":\"0\",\"WORK_NAME_\":\"工业污染源12017年07月17日现场监察\",\"WORK_NOTE_\":\"工业污染源12017年07月17日现场监察\",\"XFBCJSR_\":\"\",\"XFDJBID_\":\"\",\"XPFILEIDS_\":\"\",\"XPPSYJIDS_\":\"\",\"XP_CITY\":\"\",\"ZFDX_TYPE_\":\"1 \",\"ZXR_ID_\":\"4028e4484d65d677014d6b492e5f0297\",\"ZXR_NAME_\":\"黄菁莲\",\"ZXSTARTTIME_\":\"2017-07-17\",\"VERSION_\":7,\"baseObjId\":17}]";
    
    /***
     * json字符串转java List
     * @param rsContent
     * @return
     * @throws Exception
     */
    private static List<Map<String, String>> jsonStringToList(String rsContent) throws Exception
    {
        JSONArray arry = JSONArray.fromObject(rsContent);

        System.out.println("json字符串内容如下");
        System.out.println(arry);
        List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < arry.size(); i++)
        {
            JSONObject jsonObject = arry.getJSONObject(i);
            System.out.println("arry字符串内容如下"+arry);
            Map<String, String> map = new HashMap<String, String>();
            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
            {
                String key = (String) iter.next();
                String value = jsonObject.get(key).toString();
                map.put(key, value);
            }
            rsList.add(map);
        }
        return rsList;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        List<Map<String, String>> list1 = jsonStringToList(diskListContent);
        System.out.println("json字符串成map");
        for (Map<String, String> m : list1)
        {
            System.out.println(m);
        }
       /* System.out.println("map转换成json字符串");
        for (Map<String, String> m : list1)
        {
            JSONArray jsonArray = JSONArray.fromObject(m);
            System.out.println(jsonArray.toString());

        }
        System.out.println("list转换成json字符串");
        JSONArray jsonArray2 = JSONArray.fromObject(list1);
        System.out.println(jsonArray2.toString());*/
    }
}
