package com.hnjz.app.common;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

public enum FileTypeEnums {
	
	RW("50", "����"),
	
	RCJC("10", "�ֳ����"),
	RCJCJCJL("1000", "�ֳ����.����¼"),
	RCJCJCJLSMJ("1001", "�ֳ����.����¼ɨ���"),
	RCJCJCBG("1002", "�ֳ����.��챨��"),
	RCJCXZWS("1003", "�ֳ����.��������"),
	RCJCQTZL("1004", "�ֳ����.��������"),
	RCJCCLYJS("1005", "�ֳ����.���������"),
	RCJCZBZL("1006","�ֳ����.׼������"),
	RCJCMOREJCBL("1007", "�ֳ����.��μ�����¼"),
	RCJCDBFJ("1008", "�ֳ����.�������"),
	RCJCSPZL("1009", "�ֳ����.��Ƶ����"),
	RCJCLYZL("1010", "�ֳ����.¼������"),
	RCJCZP("1011", "�ֳ����.��Ƭ"),
	RCJCHPPFWJ("1012", "�ֳ����.���������ļ�"),
	RCJCYSPFWJ("1013", "�ֳ����.���������ļ�"),
	
	
	NDHC("11", "��Ⱥ˲�"),
	NDHCJCJL("1100", "��Ⱥ˲�.����¼"),
	NDHCJCJLSMJ("1101", "��Ⱥ˲�.����¼ɨ���"),
	NDHCJCBG("1102", "��Ⱥ˲�.��챨��"),
	NDHCXZWS("1103", "��Ⱥ˲�.��������"),
	NDHCQTZL("1104", "��Ⱥ˲�.��������"),
	NDHCCLYJS("1105", "��Ⱥ˲�.���������"),
	NDHCZBZL("1106", "��Ⱥ˲�.׼������"),
	NDHCMOREJCBL("1107", "��Ⱥ˲�.��μ�����¼"),
	NDHCDBFJ("1108", "��Ⱥ˲�.�������"),
	NDHCSPZL("1109", "��Ⱥ˲�.��Ƶ����"),
	NDHCLYZL("1110", "��Ⱥ˲�.¼������"),
	NDHCZP("1111", "��Ⱥ˲�.��Ƭ"),
	NDHCHPPFWJ("1112", "��Ⱥ˲�.���������ļ�"),
	NDHCYSPFWJ("1113", "��Ⱥ˲�.���������ļ�"),
	
	
	HDC("12", "�󶽲�"),
	HDCJCJL("1200", "�󶽲�.����¼"),
	HDCJCJLSMJ("1201", "�󶽲�.����¼ɨ���"),
	HDCJCBG("1202", "�󶽲�.��챨��"),
	HDCXZWS("1203", "�󶽲�.��������"),
	HDCQTZL("1204", "�󶽲�.��������"),
	HDCCLYJS("1205", "�󶽲�.���������"),
	HDCZBZL("1206","�󶽲�.׼������"),
	HDCMOREJCBL("1207", "�󶽲�.��μ�����¼"),
	HDCDBFJ("1208", "�󶽲�.�������"),
	HDCSPZL("1209", "�󶽲�.��Ƶ����"),
	HDCLYZL("1210", "�󶽲�.¼������"),
	HDCZP("1211", "�󶽲�.��Ƭ"),
	HDCHPPFWJ("1212", "�󶽲�.���������ļ�"),
	HDCYSPFWJ("1213", "�󶽲�.���������ļ�"),
	

	XFTS("13", "�ŷ�Ͷ��"),
	XFTSJCJL("1300", "�ŷ�Ͷ��.����¼"),
	XFTSJCJLSMJ("1301", "�ŷ�Ͷ��.����¼ɨ���"),
	XFTSJCBG("1302", "�ŷ�Ͷ��.��챨��"),
	XFTSXZWS("1303", "�ŷ�Ͷ��.��������"),
	XFTSQTZL("1304", "�ŷ�Ͷ��.��������"),
	XFTSCLYJS("1305", "�ŷ�Ͷ��.���������"),
	XFTSZBZL("1306","�ŷ�Ͷ��.׼������"),
	XFTSMOREJCBL("1307", "�ŷ�Ͷ��.��μ�����¼"),
	XFTSBJDSMJ("1308","�ŷ�Ͷ��.��ᵥɨ���"),
	XFTSDBFJ("1309", "�ŷ�Ͷ��.�������"),
	XFTSSPZL("1310", "�ŷ�Ͷ��.��Ƶ����"),
	XFTSLYZL("1311", "�ŷ�Ͷ��.¼������"),
	XFTSZP("1312", "�ŷ�Ͷ��.��Ƭ"),
	XFTSHPPFWJ("1313", "�ŷ�Ͷ��.���������ļ�"),
	XFTSYSPFWJ("1314", "�ŷ�Ͷ��.���������ļ�"),
	
	
	PWXKZJC("14", "��������֤���"),
	PWXKZJCJCJL("1400", "��������֤���.����¼"),
	PWXKZJCJCJLSMJ("1401", "��������֤���.����¼ɨ���"),
	PWXKZJCJCBG("1402", "��������֤���.��챨��"),
	PWXKZJCXZWS("1403", "��������֤���.��������"),
	PWXKZJCQTZL("1404", "��������֤���.��������"),
	PWXKZJCCLYJS("1405", "��������֤���.���������"),
	PWXKZJCZBZL("1406","��������֤���.׼������"),
	PWXKZJCMOREJCBL("1407", "��������֤���.��μ�����¼"),
	PWXKZDBFJ("1408", "��������֤���.�������"),
	PWXKZSPZL("1409", "��������֤���.��Ƶ����"),
	PWXKZLYZL("1410", "��������֤���.¼������"),
	PWXKZZP("1411", "��������֤���.��Ƭ"),
	PWXKZHPPFWJ("1412", "��������֤���.���������ļ�"),
	PWXKZYSPFWJ("1413", "��������֤���.���������ļ�"),
	
	
	ZXXD("15", "ר���ж�"),
	ZXXDJCBG("1500", "ר���ж�.��챨��"),
	ZXXDQTZL("1501", "ר���ж�.��������"),
	ZXXDZRWJCJL("1502", "ר���ж�.������.����¼"),
	ZXXDZRWJCJLSMJ("1503", "ר���ж�.������.����¼ɨ���"),
	ZXXDZRWJCBG("1504", "ר���ж�.������.��챨��"),
	ZXXDZRWXZWS("1505", "ר���ж�.������.��������"),
	ZXXDZRWQTZL("1506", "ר���ж�.������.��������"),
	ZXXDZRWYSB("1507", "ר���ж�.������.ѹ����"),
	ZXXDZRWCLYJS("1508", "ר���ж�.������.���������"),
	ZXXDZBZL("1509","ר���ж�.׼������"),
	ZXXDZRWMOREJCBL("1510", "ר���ж�.������.��μ�����¼"),
	ZXXDZRWSPZL("1511", "ר���ж�.������.��Ƶ����"),
	ZXXDZRWLYZL("1512", "ר���ж�.������.¼������"),
	ZXXDZRWZP("1513", "ר���ж�.������.��Ƭ"),
	ZXXDZRWHPPFWJ("1514", "ר���ж�.������.���������ļ�"),
	ZXXDZRWYSPFWJ("1515", "ר���ж�.������.���������ļ�"),
	
	
	WFAJDC("16", "Υ����������"),
	WFAJDCLADJB("1600", "Υ����������.�����ǼǱ�"),
	WFAJDCLADJSMJ("1601", "Υ����������.�����Ǽ�ɨ���"),
	WFAJDCKCBL("1602", "Υ����������.�����¼"),
	WFAJDCKCBLSMJ("1603", "Υ����������.�����¼ɨ���"),
	WFAJDCXWBL("1604", "Υ����������.ѯ�ʱ�¼"),
	WFAJDCXWBLSMJ("1605", "Υ����������.ѯ�ʱ�¼ɨ���"),
	WFAJDCSZDZJZL("1606", "Υ����������.��֤��֤������"),
	WFAJDCSTZLTP("1607", "Υ����������.��������.ͼƬ"),
	WFAJDCYPZL("1608", "Υ����������.��������.��Ƶ����"),
	WFAJDCSTZLLX("1609", "Υ����������.��������.¼��"),
	//WFAJDCSTZLDYFJ("1610", "Υ����������.�������ϴ�ӡ����"),
	WFAJDCXZWS("1611", "Υ����������.��������"),
	WFAJDCQTZL("1612", "Υ����������.��������"),
	WFAJDCDCBG("1613", "Υ����������.���鱨��"),	
	WFAJDCZBZL("1614","Υ����������.׼������"),
	//��������������Ӧ�ĸ�������
	WFAJDCSXGZS("1615","Υ����������.�����������ȸ�֪��"),
	WFAJDCJTTLBL("1616","Υ����������.�����������۱�¼"),
	WFAJDCXZCFJDS("1617","Υ����������.��������������"),
	WFAJDCSDHZ("1618","Υ����������.�ʹ��֤"),
	WFAJDCJASPB("1619","Υ����������.�������������᰸������"),
	WFAJDCDBFJ("1620", "Υ����������.�������"),
	WFAJDCHPPFWJ("1624", "Υ����������.���������ļ�"),
	WFAJDCYSPFWJ("1625", "Υ����������.���������ļ�"),
	
	
	XQZL("17", "��������"),
	XQZLJCJL("1700", "��������.����¼"),
	XQZLJCJLSMJ("1701", "��������.����¼ɨ���"),
	XQZLJCBG("1702", "��������.��챨��"),
	XQZLXZWS("1703", "��������.��������"),
	XQZLQTZL("1704", "��������.��������"),
	XQZLCLYJS("1705", "��������.���������"),
	XQZLZBZL("1706","��������.׼������"),
	XQZLMOREJCBL("1707", "��������.��μ�����¼"),
	XQZLDBFJ("1708", "��������.�������"),
	XQZLSPZL("1709", "��������.��Ƶ����"),
	XQZLLYZL("1710", "��������.¼������"),
	XQZLZP("1711", "��������.��Ƭ"),
	XQZLHPPFWJ("1712", "��������.���������ļ�"),
	XQZLYSPFWJ("1713", "��������.���������ļ�"),
	XQZLHJWFXWXQGZTZ("1714", "��������.����Υ����Ϊ���ڸ���֪ͨ"),
	XQZLXZCFJDHSDHZ("1715", "��������.���������������ʹ��ִ"),
	XQZLTZGZSSDHZ("1716", "��������.����֤����֪���ʹ��֤"),
	
	
	GZJC("18","���ټ��"),
	GZJCJCJL("1800", "���ټ��.����¼"),
	GZJCJCJLSMJ("1801", "���ټ��.����¼ɨ���"),
	GZJCJCBG("1802", "���ټ��.��챨��"),
	GZJCXZWS("1803", "���ټ��.��������"),
	GZJCQTZL("1804", "���ټ��.��������"),
	GZJCCLYJS("1805", "���ټ��.���������"),
	GZJCZBZL("1806","���ټ��.׼������"),
	GZJCMOREJCBL("1807", "���ټ��.��μ�����¼"),
	GZJCDBFJ("1808", "���ټ��.�������"),
	GZJCSPZL("1809", "���ټ��.��Ƶ����"),
	GZJCLYZL("1810", "���ټ��.¼������"),
	GZJCZP("1811", "���ټ��.��Ƭ"),
	GZJCHPPFWJ("1812", "���ټ��.���������ļ�"),
	GZJCYSPFWJ("1813", "���ټ��.���������ļ�"),
	
	
	ZDJK("19","�Զ����"),
	ZDJKJCJL("1900", "�Զ����.����¼"),
	ZDJKJCJLSMJ("1901", "�Զ����.����¼ɨ���"),
	ZDJKJCBG("1902", "�Զ����.��챨��"),
	ZDJKXZWS("1903", "�Զ����.��������"),
	ZDJKQTZL("1904", "�Զ����.��������"),
	ZDJKCLYJS("1905", "�Զ����.���������"),
	ZDJKZBZL("1906","�Զ����.׼������"),
	ZDJKDBFJ("1908", "�Զ����.�������"),
	ZDJKSPZL("1909", "�Զ����.��Ƶ����"),
	ZDJKLYZL("1910", "�Զ����.¼������"),
	ZDJKZP("1911", "�Զ����.��Ƭ"),
	ZDJKHPPFWJ("1912", "�Զ����.���������ļ�"),
	ZDJKYSPFWJ("1913", "�Զ����.���������ļ�"),
	ZDJKHBWD("1914", "�Զ����.�ϲ��ĵ��ļ�"),
	
	WXFW("20", "Σ�շ���"),
	WXFWJCJL("2000", "Σ�շ���.����¼"),
	WXFWJCJLSMJ("2001", "Σ�շ���.����¼ɨ���"),
	WXFWJCBG("2002", "Σ�շ���.��챨��"),
	WXFWXZWS("2003", "Σ�շ���.��������"),
	WXFWQTZL("2004", "Σ�շ���.��������"),
	WXFWCLYJS("2005", "Σ�շ���.���������"),
	WXFWZBZL("2006", "Σ�շ���.׼������"),
	WXFWMOREJCBL("2007", "Σ�շ���.��μ�����¼"),
	WXFWDBFJ("2008", "Σ�շ���.�������"),
	WXFWSPZL("2009", "Σ�շ���.��Ƶ����"),
	WXFWLYZL("2010", "Σ�շ���.¼������"),
	WXFWZP("2011", "Σ�շ���.��Ƭ"),
	WXFWHPPFWJ("2012", "Σ�շ���.���������ļ�"),
	WXFWYSPFWJ("2013", "Σ�շ���.���������ļ�"),
	
	
	WXHXP("21", "Σ�ջ�ѧƷ"),
	WXHXPJCJL("2100", "Σ�ջ�ѧƷ.����¼"),
	WXHXPJCJLSMJ("2101", "Σ�ջ�ѧƷ.����¼ɨ���"),
	WXHXPJCBG("2102", "Σ�ջ�ѧƷ.��챨��"),
	WXHXPXZWS("2103", "Σ�ջ�ѧƷ.��������"),
	WXHXPQTZL("2104", "Σ�ջ�ѧƷ.��������"),
	WXHXPCLYJS("2105", "Σ�ջ�ѧƷ.���������"),
	WXHXPZBZL("2106", "Σ�ջ�ѧƷ.׼������"),
	WXHXPDBFJ("2107", "Σ�ջ�ѧƷ.�������"),
	WXHXPSPZL("2108", "Σ�ջ�ѧƷ.��Ƶ����"),
	WXHXPLYZL("2109", "Σ�ջ�ѧƷ.¼������"),
	WXHXPZP("2110", "Σ�ջ�ѧƷ.��Ƭ"),
	WXHXPHPPFWJ("2111", "Σ�ջ�ѧƷ.���������ļ�"),
	WXHXPYSPFWJ("2112", "Σ�ջ�ѧƷ.���������ļ�"),
	
	
	FSAQ("22","���䰲ȫ"),
	FSAQJCJL("2200", "���䰲ȫ.����¼"),
	FSAQJCJLSMJ("2201", "���䰲ȫ.����¼ɨ���"),
	FSAQJCBG("2202", "���䰲ȫ.��챨��"),
	FSAQXZWS("2203", "���䰲ȫ.��������"),
	FSAQQTZL("2204", "���䰲ȫ.��������"),
	FSAQCLYJS("2205", "���䰲ȫ.���������"),
	FSAQZBZL("2206","���䰲ȫ.׼������"),
	FSAQMOREJCBL("2207", "���䰲ȫ.��μ�����¼"),
	FSAQDBFJ("2208", "���䰲ȫ.�������"),
	FSAQSPZL("2209", "���䰲ȫ.��Ƶ����"),
	FSAQLYZL("2210", "���䰲ȫ.¼������"),
	FSAQZP("2211", "���䰲ȫ.��Ƭ"),
	FSAQHPPFWJ("2212", "���䰲ȫ.���������ļ�"),
	FSAQYSPFWJ("2213", "���䰲ȫ.���������ļ�"),
	
	
	WRSGXCDC("23", "��Ⱦ�¹��ֳ�����"),
	WRSGXCDCJCJL("2300", "��Ⱦ�¹��ֳ�����.����¼"),
	WRSGXCDCJCJLSMJ("2301", "��Ⱦ�¹��ֳ�����.����¼ɨ���"),
	WRSGXCDCJCBG("2302", "��Ⱦ�¹��ֳ�����.��챨��"),
	WRSGXCDCXZWS("2303", "��Ⱦ�¹��ֳ�����.��������"),
	WRSGXCDCQTZL("2304", "��Ⱦ�¹��ֳ�����.��������"),
	WRSGXCDCCLYJS("2305", "��Ⱦ�¹��ֳ�����.���������"),
	WRSGXCDCZBZL("2306", "��Ⱦ�¹��ֳ�����.׼������"),
	WRSGXCDCMOREJCBL("2307", "��Ⱦ�¹��ֳ�����.��μ�����¼"),
	WRSGXCDCDBFJ("2308", "��Ⱦ�¹��ֳ�����.�������"),
	WRSGXCDCSPZL("2309", "��Ⱦ�¹��ֳ�����.��Ƶ����"),
	WRSGXCDCLYZL("2310", "��Ⱦ�¹��ֳ�����.¼������"),
	WRSGXCDCZP("2311", "��Ⱦ�¹��ֳ�����.��Ƭ"),
	WRSGXCDCHPPFWJ("2312", "��Ⱦ�¹��ֳ�����.���������ļ�"),
	WRSGXCDCYSPFWJ("2313", "��Ⱦ�¹��ֳ�����.���������ļ�"),
	
	
	ZFWJGL("24", "ִ���ļ�����"),
	ZFWJGLZFWJ("2400", "ִ���ļ�����.ִ���ļ�"),
	
	
	RWGL("25", "�������"),
	RWGLPFFJ("2500", "�������.�ɷ�����"),
	RWGLZPFJ("2501", "�������.ת�ɸ���"),
	RWGLXPFJ("2502", "�������.���ɸ���"),
	
	GYWRY("31", "��ҵ��ȾԴ"),
	GYWRYZZJGDMSMJ("3100", "��ҵ��ȾԴ.��֯��������ɨ���"),
	GYWRYXCKCSYT("3101", "��ҵ��ȾԴ.�ֳ�����ʾ��ͼ"),
	GYWRYYYZZSMJ("3102", "��ҵ��ȾԴ.Ӫҵִ��ɨ���"),
	GYWRYPWXKZSMJ("3103", "��ҵ��ȾԴ.��������֤ɨ���"),
	GYWRYQT("3104", "��ҵ��ȾԴ.����"),
	//���ӹ�ҵ��ȾԴ�ĸ�������
		GYWRYGSYYZZ("3105", "1����Ӫҵִ��"),
		GYWRYQYZZJGDMZ("3106","2��ҵ��֯��������֤"),
		GYWRYQQZPMSYT("3107","3��ҵ��ƽ��ʾ��ͼ"),
		GYWRYQQSBQD("3108","4��ҵ�豸�嵥"),
		GYWRYQQYFWLXHQD("3109", "5��ҵԭ�����������嵥���������ܡ���ˮ�������ȣ�"),
		GYWRYQQSCGMYSCGY("3110", "6��ҵ������ģ����������"),
		GYWRYQYZBHJMGDFBT("3111", "7��ҵ�ܱ߻������е�ֲ�ͼ"),
		GYWRYQQWRWNDPFQK("3112", "8��ҵ��Ⱦ������ŷ����"),
		GYWRYHJYXPJBGWJ("3113", "9����Ӱ�����۱����ļ�"),
		GYWRYHBXZBMDJSXMPF("3114", "10�����������ŶԽ�����Ŀ�Ļ����ļ�����"),
		GYWRYQQJSXMJGHJBG("3115", "11��ҵ������Ŀ���������������ձ���"),
		GYWRYHBXZBMYSPF("3116", "12�����������Ŷ���ҵ������Ŀ�������������ļ�"),
		GYWRYQQJSXMSSCSQB("3117", "13��ҵ������Ŀ�����������"),
		GYWRYQQJSXMSSCPF("3118", "14��ҵ������Ŀ����������"),
		GYWRYYCSSCPF("3119", "15�ӳ�����������"),
		GYWRYQQJSXMHJJLBG("3120", "16����ҵ������Ŀ������������"),
		GYWRYFSCLJHFA("3121", "17���ˮ������Ʒ���"),
		GYWRYFQCLSJFA("3122", "18�����������Ʒ���"),
		GYWRYGFCZSJFA("3123", "19��̷ϴ�����Ʒ���"),
		GYWRYJPZRZHHBBMJPWJ("3124", "20��������״�򻷱������´�ļ��������ļ�"),
		GYWRYQQQZQJSCSHBG("3125", "21����ҵǿ�����������˱���"),
		GYWRYQQQZQZSCSHYSPF("3126", "22��ҵǿ��������������������"),
		GYWRYQQGFHPWKJBZPZP("3127", "23��ҵ�淶�����ۿڼ���־����Ƭ"),
		GYWRYGYGTFWCLLYB("3128", "24��ҵ������ﴦ�����ñ�"),
		GYWRYGYFCZHZHLYQQZZSM("3129", "25��ҵ�̷ϴ��û��ۺ�������ҵ����˵��"),
		GYWRYZPZWWCQKCL("3130", "26������������������"),
		GYWRYPWQJYZBHJYPZ("3131", "27��ҵͨ������Ȩ���׵�����ָ�����ļ�����Ȩ����ƾ֤"),
		GYWRYTGISOHBTXRZZS("3132", "28��ҵͨ��ISO14000�Ȼ�����ϵ��֤��֤��"),
		GYWRYZYWRZZFA("3133", "29����ҵ��Ⱦ���η���"),
		GYWRYQYWRWPFSQB("3134", "30��ҵ��Ⱦ���ŷ������"),
		GYWRYQYPWFJFSJ("3135", "31��ҵ���۷ѽɷ��վ�"),
		GYWRYHBBMHFWRWPFXKZ("3136", "32�������ź˷�����Ⱦ���ŷ�����֤"),
		GYWRYHBBMDQYJDXJCBG("3137", "33�������Ŷ���ҵ�ļල�Լ�ⱨ��"),
		GYWRYFSAQXKZ("3138", "34���䰲ȫ����֤"),
		GYWRYWXFWJYXKZ("3139", "35Σ�շ��ﾭӪ����֤"),
		GYWRYWXFWZYJHBPB("3140", "36Σ�շ���ת�Ƽƻ�������"),
		GYWRYWXFWAQCZWTXY("3141", "37Σ�շ��ﰲȫ����ί��Э�飨��ͬ�飩"),
		GYWRYWXFWJSFAQCZJYXKZ("3142", "38Σ�շ�����շ���Σ�շ��ﰲȫ���þ�Ӫ����֤��"),
		GYWRYWXFWZYD("3143", "39Σ�շ���ת����������Σ�շ������ת��������ӡ���£�"),
		GYWRYHBXZBMXDQYWRQXZLTZ("3144", "40�������������´���ҵ��Ⱦ����������֪ͨ"),
		GYWRHBBMXQZLYSBG("3145", "41�ﻷ�����������������ռ�ⱨ��"),
		GYWRYHBXZBMZLYSPF("3146", "42������������ͬ������������ʽ���յ�����"),
		GYWRYQYFWZXJCJKYSBG("3147", "43��ҵ��ˮ���������ؽ�����Ⱦ���ŷ����߼����ϵͳ���ձ���"),
		GYWRYWRWPFZXJCXTDBSH("3148", "44��Ⱦ���ŷ����߼��ϵͳ�ȶԽ������Ч�����"),
		GYWRYYJYA("3149", "45�ﾭ��ʽ��������������������ҵͻ�������¼�Ӧ��Ԥ������Σ�շ��������Ҫ��Σ�շ�����շ���ר�»�ר�ŵ�Σ�շ���Ӧ��Ԥ����"),
		GYWRYYJYABAB("3150", "46Ӧ��Ԥ��������"),
		GYWRYQYTFYJYAYLJL("3151", "47��ҵͻ�������¼�Ӧ��Ԥ��������¼�������ƻ�����������¼���ܽ�ȣ�"),
		GYWRYQYYJWZQDB("3152", "48��ҵ�¹�Ӧ���ء���ع�·��Ӧ��������Ƭ��Ӧ�������嵥��"),
		GYWRYQYXFTSSLJLCLJG("3153", "49��ҵ�ŷ�Ͷ�ߵ�������¼�ʹ������"),
		GYWRYHBJCBMXCJCJL("3154", "50������첿���ֳ�����¼"),
		GYWRYHBBMDHJWFCFJDS("3155", "51�������Ŷ���ҵ����Υ������������������"),
		GYWRYHJBHJGSZWJ("3156", "52��ҵ�����������������ļ�������Ա���������ݣ�"),
		GYWRYGLWRFZGLZD("3157", "53����ҵ������Ⱦ���εȻ��������ƶ�"),
		GYWRYHBSSYXTZJL("3158", "54����ҵ������ʩ����̨�˼�¼��ˮ�������������̷ϣ�"),
		GYWRYNBKZLXJCSJ("3159", "55��ҵ�ڲ���չ�����м������"),
		GYWRYWRZRBXXGWJ("3160", "56��ҵ��Ⱦ���α�������ļ�"),
		GYWRYTFXHJWRSGCZBG("3161", "57��ҵͻ���Ի�����Ⱦ�¹ʴ��ü�¼�ͱ���"),
		GYWRYWHPJCB("3163", "Σ��Ʒ����"),
		
	YY("32", "ҽԺ"),
	YYZZJGDMSMJ("3200", "ҽԺ.��֯��������ɨ���"),
	YYXCKCSYT("3201", "ҽԺ.�ֳ�����ʾ��ͼ"),
	YYFSAQXKZSMJ("3202", "ҽԺ.���䰲ȫ����֤ɨ���"),
	YYPWXKZSMJ("3203", "ҽԺ.��������֤ɨ���"),
	YYQT("3204", "ҽԺ.����"),
	
	
	GL("33", "��¯"),
	GLZZJGDMSMJ("3300", "��¯.��֯��������ɨ���"),
	GLXCKCSYT("3301", "��¯.�ֳ�����ʾ��ͼ"),
	GLYYZZSMJ("3302", "��¯.Ӫҵִ��ɨ���"),
	GLPWXKZSMJ("3303", "��¯.��������֤ɨ���"),
	GLQT("3304", "��¯.����"),
	
	
	JZGD("34", "��������"),
	JZGDZZJGDMSMJ("3400", "��������.��֯��������ɨ���"),
	JZGDXCKCSYT("3401", "��������.�ֳ�����ʾ��ͼ"),
	JZGDQT("3402", "��������.����"),
	
	
	XQYZ("35", "������ֳ"),
	XQYZZZJGDMSMJ("3500", "������ֳ.��֯��������ɨ���"),
	XQYZXCKCSYT("3501", "������ֳ.�ֳ�����ʾ��ͼ"),
	XQYYZZSMJ("3502", "������ֳ.Ӫҵִ��ɨ���"),
	XQPWXKZSMJ("3503", "������ֳ.��������֤ɨ���"),
	XQYZQT("3504", "������ֳ.����"),
	
	
	SC("36", "����"),
	SCXCKCSYT("3600", "����.�ֳ�����ʾ��ͼ"),
	SCYYZZSMJ("3601", "����.Ӫҵִ��ɨ���"),
	SCPWXKZSMJ("3602", "����.��������֤ɨ���"),
	SCWSXKZSMJ("3603", "����.��������֤ɨ���"),
	SCQT("3604", "����.����"),
	
	
	JSXM("37", "������Ŀ"),
	JSXMXCKCSYT("3700", "������Ŀ.�ֳ�����ʾ��ͼ"),
	JSXMQT("3701", "������Ŀ.����"),
	
	
	HPXX("38", "������Ϣ"),
	HPXXHPSPWH("3800", "������Ϣ.���������ĺ�"),
	HPXXSSCSPWH("3801", "������Ϣ.�����������ĺ�"),
	HPXXYQSSCSPWH("3802", "������Ϣ.���������������ĺ�"),
	HPXXSTSYSSPWH("3803", "������Ϣ.��ͬʱ���������ĺ�"),
	HPXXQJSCSPWH("3804", "������Ϣ.������������ĺ�"),
	
	
	TSXX("39", "Ͷ����Ϣ"),
	TSXXFJ("3900", "Ͷ����Ϣ.����"),
	
	
	YHXX("51", "�û���Ϣ"),
	YHXXTX("5101", "�û���Ϣ��Ƭ"),
	
	
	APP("52", "�����"),
	APPZD("5201", "�����.�ն�"),
	APPPC("5202", "�����.PC��"),
	APPPCDATA("5203", "�����.PC�����ݰ�"),
	APPWEBHELP("5204", "��̨�����ĵ�"),
	APPMOHELP("5205", "�ն˰����ĵ�"),
	APPMOBLIE("5206", "�����.�ֻ���"),
	
	
	JCMB("6000", "���ģ��"),
	
	FWY("61", "����ҵ"),
	FWYYYZZSMJ("6101", "����ҵ.Ӫҵִ��ɨ���"),
	FWYQT("6102", "����ҵ.����"),
	
	YSY("62", "��ʳҵ"),
	YSYYYZZSMJ("6201", "��ʳҵ.Ӫҵִ��ɨ���"),
	YSYQT("6202", "��ʳҵ.����"),
	
	ZZY("63", "����ҵ"),
	ZZYYYZZSMJ("6301", "����ҵ.Ӫҵִ��ɨ���"),
	ZZYQT("6302", "����ҵ.����"),
	
	YLY("64", "����ҵ"),
	YLYYYZZSMJ("6401", "����ҵ.Ӫҵִ��ɨ���"),
	YLYQT("6402", "����ҵ.����"),
	
	GJGL("65", "�������"),
	GJGLGJFJ("6500", "�������.�������"),
	
	RCBG("66", "�ճ��칫"),
	RCBGZBZL("6601", "�ճ��칫.׼������"),
	RCBGBLZL("6602", "�ճ��칫.��������"),
	RCBGDBFJ("6603", "�ճ��칫.�������"),
	
	XFDJ("67", "�ŷõǼ�"),
	XFDJXFDJB("6701", "�ŷõǼ�.�ŷõǼǱ�"),
	
	//���̸���
	HSFJ("68","���̸���"),
	HSFJFSHSFJ("6801","���̸���.���ͻ��̸���"),
	HSFJYJFJ("6802","���̸���.�������"),
	
	QT("69","����"),
	QTZZJGDMSMJ("6900", "��֯��������ɨ���"),
	QTXCKCSYT("6901", "�ֳ�����ʾ��ͼ"),
	QTYYZZSMJ("6902", "Ӫҵִ��ɨ���"),
	QTPWXKZSMJ("6903", "��������֤ɨ���"),
	;
	private FileTypeEnums(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static List<Combobox> getTypeList(String code){
		List<Combobox> re = new ArrayList<Combobox>();
		for (FileTypeEnums ele : values()) {
			if (ele.getCode().length() == 4 && ele.getCode().substring(0, 2).equals(code)) {
				re.add(new Combobox(ele.getCode(), ele.getName()));
			}
		}
		
		return re;
	}
	
	public static List<Combobox> getTypeListByEnumName(String enumName){
		String code = "";
		for (FileTypeEnums ele : values()) {
			if (ele.name().equals(enumName)) {
				code = ele.getCode();
				break;
			}
		}
		return getTypeList(code);
	}
	
	public static String getEnumByCode(String code){
		String enumName = "";
		for (FileTypeEnums ele : values()) {
			if (ele.getCode().equals(code)) {
				enumName = ele.name();
				break;
			}
		}
		return enumName;
	}
	
	public static String getTypeByEnumName(String enumName){
		String code = "";
		for (FileTypeEnums ele : values()) {
			if (ele.name().equals(enumName)) {
				code = ele.getCode();
				break;
			}
		}
		if ("".equals(code)){
			code = enumName;
		}
		return code;
	}
	
	public static String getNameByCode(String code){
		for (FileTypeEnums ele : values()) {
			if (ele.getCode().equals(code)) {
				return ele.getName().substring(ele.getName().lastIndexOf(".") + 1, ele.getName().length());
			}
		}
		return "";
	}
	
	/**
	 * 
	 * �������ܣ������������ͱ�Ż�ø������ͼ��ϣ��ö��Ÿ���
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static String getFileTypeByTaskCode(String taskCode){
		String fileType = "";
		for (FileTypeEnums ele : values()) {
			if (ele.getCode().length()==4 && ele.getCode().startsWith(taskCode)) {
				fileType += ele.getCode() + ",";
			}
		}
		return fileType;
	}
	
	/** ���� */
	private String code;
	/** ���� */
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return name.substring(name.lastIndexOf(".") + 1, name.length());
	}
	
	public void setName(String name) {
		this.name = name;
	}
}