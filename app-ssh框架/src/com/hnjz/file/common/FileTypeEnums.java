package com.hnjz.file.common;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

public enum FileTypeEnums {
	
	RW("50", "任务"),
	
	RCJC("10", "现场监察"),
	RCJCJCJL("1000", "现场监察.监察笔录"),
	RCJCJCJLSMJ("1001", "现场监察.监察笔录扫描件"),
	RCJCJCBG("1002", "现场监察.监察报告"),
	RCJCXZWS("1003", "现场监察.行政文书"),
	RCJCQTZL("1004", "现场监察.其他资料"),
	RCJCCLYJS("1005", "现场监察.处理意见书"),
	RCJCZBZL("1006","现场监察.准备资料"),
	RCJCMOREJCBL("1007", "现场监察.多次检查监察笔录"),
	RCJCDBFJ("1008", "现场监察.打包附件"),
	RCJCSPZL("1009", "现场监察.视频资料"),
	RCJCLYZL("1010", "现场监察.录音资料"),
	RCJCZP("1011", "现场监察.照片"),
	RCJCHPPFWJ("1012", "现场监察.环评批复文件"),
	RCJCYSPFWJ("1013", "现场监察.验收批复文件"),
	
	
	NDHC("11", "年度核查"),
	NDHCJCJL("1100", "年度核查.监察笔录"),
	NDHCJCJLSMJ("1101", "年度核查.监察笔录扫描件"),
	NDHCJCBG("1102", "年度核查.监察报告"),
	NDHCXZWS("1103", "年度核查.行政文书"),
	NDHCQTZL("1104", "年度核查.其他资料"),
	NDHCCLYJS("1105", "年度核查.处理意见书"),
	NDHCZBZL("1106", "年度核查.准备资料"),
	NDHCMOREJCBL("1107", "年度核查.多次检查监察笔录"),
	NDHCDBFJ("1108", "年度核查.打包附件"),
	NDHCSPZL("1109", "年度核查.视频资料"),
	NDHCLYZL("1110", "年度核查.录音资料"),
	NDHCZP("1111", "年度核查.照片"),
	NDHCHPPFWJ("1112", "年度核查.环评批复文件"),
	NDHCYSPFWJ("1113", "年度核查.验收批复文件"),
	
	
	HDC("12", "后督察"),
	HDCJCJL("1200", "后督察.监察笔录"),
	HDCJCJLSMJ("1201", "后督察.监察笔录扫描件"),
	HDCJCBG("1202", "后督察.监察报告"),
	HDCXZWS("1203", "后督察.行政文书"),
	HDCQTZL("1204", "后督察.其他资料"),
	HDCCLYJS("1205", "后督察.处理意见书"),
	HDCZBZL("1206","后督察.准备资料"),
	HDCMOREJCBL("1207", "后督察.多次检查监察笔录"),
	HDCDBFJ("1208", "后督察.打包附件"),
	HDCSPZL("1209", "后督察.视频资料"),
	HDCLYZL("1210", "后督察.录音资料"),
	HDCZP("1211", "后督察.照片"),
	HDCHPPFWJ("1212", "后督察.环评批复文件"),
	HDCYSPFWJ("1213", "后督察.验收批复文件"),
	

	XFTS("13", "信访投诉"),
	XFTSJCJL("1300", "信访投诉.监察笔录"),
	XFTSJCJLSMJ("1301", "信访投诉.监察笔录扫描件"),
	XFTSJCBG("1302", "信访投诉.监察报告"),
	XFTSXZWS("1303", "信访投诉.行政文书"),
	XFTSQTZL("1304", "信访投诉.其他资料"),
	XFTSCLYJS("1305", "信访投诉.处理意见书"),
	XFTSZBZL("1306","信访投诉.准备资料"),
	XFTSMOREJCBL("1307", "信访投诉.多次检查监察笔录"),
	XFTSBJDSMJ("1308","信访投诉.办结单扫描件"),
	XFTSDBFJ("1309", "信访投诉.打包附件"),
	XFTSSPZL("1310", "信访投诉.视频资料"),
	XFTSLYZL("1311", "信访投诉.录音资料"),
	XFTSZP("1312", "信访投诉.照片"),
	XFTSHPPFWJ("1313", "信访投诉.环评批复文件"),
	XFTSYSPFWJ("1314", "信访投诉.验收批复文件"),
	
	
	PWXKZJC("14", "排污许可证检查"),
	PWXKZJCJCJL("1400", "排污许可证检查.监察笔录"),
	PWXKZJCJCJLSMJ("1401", "排污许可证检查.监察笔录扫描件"),
	PWXKZJCJCBG("1402", "排污许可证检查.监察报告"),
	PWXKZJCXZWS("1403", "排污许可证检查.行政文书"),
	PWXKZJCQTZL("1404", "排污许可证检查.其他资料"),
	PWXKZJCCLYJS("1405", "排污许可证检查.处理意见书"),
	PWXKZJCZBZL("1406","排污许可证检查.准备资料"),
	PWXKZJCMOREJCBL("1407", "排污许可证检查.多次检查监察笔录"),
	PWXKZDBFJ("1408", "排污许可证检查.打包附件"),
	PWXKZSPZL("1409", "排污许可证检查.视频资料"),
	PWXKZLYZL("1410", "排污许可证检查.录音资料"),
	PWXKZZP("1411", "排污许可证检查.照片"),
	PWXKZHPPFWJ("1412", "排污许可证检查.环评批复文件"),
	PWXKZYSPFWJ("1413", "排污许可证检查.验收批复文件"),
	
	
	ZXXD("15", "专项行动"),
	ZXXDJCBG("1500", "专项行动.监察报告"),
	ZXXDQTZL("1501", "专项行动.其他资料"),
	ZXXDZRWJCJL("1502", "专项行动.子任务.监察笔录"),
	ZXXDZRWJCJLSMJ("1503", "专项行动.子任务.监察笔录扫描件"),
	ZXXDZRWJCBG("1504", "专项行动.子任务.监察报告"),
	ZXXDZRWXZWS("1505", "专项行动.子任务.行政文书"),
	ZXXDZRWQTZL("1506", "专项行动.子任务.其他资料"),
	ZXXDZRWYSB("1507", "专项行动.子任务.压缩包"),
	ZXXDZRWCLYJS("1508", "专项行动.子任务.处理意见书"),
	ZXXDZBZL("1509","专项行动.准备资料"),
	ZXXDZRWMOREJCBL("1510", "专项行动.子任务.多次检查监察笔录"),
	ZXXDZRWSPZL("1511", "专项行动.子任务.视频资料"),
	ZXXDZRWLYZL("1512", "专项行动.子任务.录音资料"),
	ZXXDZRWZP("1513", "专项行动.子任务.照片"),
	ZXXDZRWHPPFWJ("1514", "专项行动.子任务.环评批复文件"),
	ZXXDZRWYSPFWJ("1515", "专项行动.子任务.验收批复文件"),
	
	
	WFAJDC("16", "违法案件调查"),
	WFAJDCLADJB("1600", "违法案件调查.立案登记表"),
	WFAJDCLADJSMJ("1601", "违法案件调查.立案登记扫描件"),
	WFAJDCKCBL("1602", "违法案件调查.勘察笔录"),
	WFAJDCKCBLSMJ("1603", "违法案件调查.勘察笔录扫描件"),
	WFAJDCXWBL("1604", "违法案件调查.询问笔录"),
	WFAJDCXWBLSMJ("1605", "违法案件调查.询问笔录扫描件"),
	WFAJDCSZDZJZL("1606", "违法案件调查.书证等证据资料"),
	WFAJDCSTZLTP("1607", "违法案件调查.视听资料.图片"),
	WFAJDCYPZL("1608", "违法案件调查.视听资料.音频资料"),
	WFAJDCSTZLLX("1609", "违法案件调查.视听资料.录像"),
	//WFAJDCSTZLDYFJ("1610", "违法案件调查.视听资料打印附件"),
	WFAJDCXZWS("1611", "违法案件调查.行政文书"),
	WFAJDCQTZL("1612", "违法案件调查.其他资料"),
	WFAJDCDCBG("1613", "违法案件调查.调查报告"),	
	WFAJDCZBZL("1614","违法案件调查.准备资料"),
	//添加行政处罚对应的附件类型
	WFAJDCSXGZS("1615","违法案件调查.行政处罚事先告知书"),
	WFAJDCJTTLBL("1616","违法案件调查.案件集体讨论笔录"),
	WFAJDCXZCFJDS("1617","违法案件调查.行政处罚决定书"),
	WFAJDCSDHZ("1618","违法案件调查.送达回证"),
	WFAJDCJASPB("1619","违法案件调查.行政处罚案件结案审批表"),
	WFAJDCDBFJ("1620", "违法案件调查.打包附件"),
	WFAJDCHPPFWJ("1624", "违法案件调查.环评批复文件"),
	WFAJDCYSPFWJ("1625", "违法案件调查.验收批复文件"),
	
	
	XQZL("17", "限期治理"),
	XQZLJCJL("1700", "限期治理.监察笔录"),
	XQZLJCJLSMJ("1701", "限期治理.监察笔录扫描件"),
	XQZLJCBG("1702", "限期治理.监察报告"),
	XQZLXZWS("1703", "限期治理.行政文书"),
	XQZLQTZL("1704", "限期治理.其他资料"),
	XQZLCLYJS("1705", "限期治理.处理意见书"),
	XQZLZBZL("1706","限期治理.准备资料"),
	XQZLMOREJCBL("1707", "限期治理.多次检查监察笔录"),
	XQZLDBFJ("1708", "限期治理.打包附件"),
	XQZLSPZL("1709", "限期治理.视频资料"),
	XQZLLYZL("1710", "限期治理.录音资料"),
	XQZLZP("1711", "限期治理.照片"),
	XQZLHPPFWJ("1712", "限期治理.环评批复文件"),
	XQZLYSPFWJ("1713", "限期治理.验收批复文件"),
	XQZLHJWFXWXQGZTZ("1714", "限期治理.环境违法行为限期改正通知"),
	XQZLXZCFJDHSDHZ("1715", "限期治理.行政处罚决定书送达回执"),
	XQZLTZGZSSDHZ("1716", "限期治理.（听证）告知书送达回证"),
	
	
	GZJC("18","跟踪检查"),
	GZJCJCJL("1800", "跟踪检查.监察笔录"),
	GZJCJCJLSMJ("1801", "跟踪检查.监察笔录扫描件"),
	GZJCJCBG("1802", "跟踪检查.监察报告"),
	GZJCXZWS("1803", "跟踪检查.行政文书"),
	GZJCQTZL("1804", "跟踪检查.其他资料"),
	GZJCCLYJS("1805", "跟踪检查.处理意见书"),
	GZJCZBZL("1806","跟踪检查.准备资料"),
	GZJCMOREJCBL("1807", "跟踪检查.多次检查监察笔录"),
	GZJCDBFJ("1808", "跟踪检查.打包附件"),
	GZJCSPZL("1809", "跟踪检查.视频资料"),
	GZJCLYZL("1810", "跟踪检查.录音资料"),
	GZJCZP("1811", "跟踪检查.照片"),
	GZJCHPPFWJ("1812", "跟踪检查.环评批复文件"),
	GZJCYSPFWJ("1813", "跟踪检查.验收批复文件"),
	
	
	ZDJK("19","自动监控"),
	ZDJKJCJL("1900", "自动监控.监察笔录"),
	ZDJKJCJLSMJ("1901", "自动监控.监察笔录扫描件"),
	ZDJKJCBG("1902", "自动监控.监察报告"),
	ZDJKXZWS("1903", "自动监控.行政文书"),
	ZDJKQTZL("1904", "自动监控.其他资料"),
	ZDJKCLYJS("1905", "自动监控.处理意见书"),
	ZDJKZBZL("1906","自动监控.准备资料"),
	ZDJKDBFJ("1908", "自动监控.打包附件"),
	ZDJKSPZL("1909", "自动监控.视频资料"),
	ZDJKLYZL("1910", "自动监控.录音资料"),
	ZDJKZP("1911", "自动监控.照片"),
	ZDJKHPPFWJ("1912", "自动监控.环评批复文件"),
	ZDJKYSPFWJ("1913", "自动监控.验收批复文件"),
	ZDJKHBWD("1914", "自动监控.合并文档文件"),
	
	WXFW("20", "危险废物"),
	WXFWJCJL("2000", "危险废物.监察笔录"),
	WXFWJCJLSMJ("2001", "危险废物.监察笔录扫描件"),
	WXFWJCBG("2002", "危险废物.监察报告"),
	WXFWXZWS("2003", "危险废物.行政文书"),
	WXFWQTZL("2004", "危险废物.其他资料"),
	WXFWCLYJS("2005", "危险废物.处理意见书"),
	WXFWZBZL("2006", "危险废物.准备资料"),
	WXFWMOREJCBL("2007", "危险废物.多次检查监察笔录"),
	WXFWDBFJ("2008", "危险废物.打包附件"),
	WXFWSPZL("2009", "危险废物.视频资料"),
	WXFWLYZL("2010", "危险废物.录音资料"),
	WXFWZP("2011", "危险废物.照片"),
	WXFWHPPFWJ("2012", "危险废物.环评批复文件"),
	WXFWYSPFWJ("2013", "危险废物.验收批复文件"),
	
	
	WXHXP("21", "危险化学品"),
	WXHXPJCJL("2100", "危险化学品.监察笔录"),
	WXHXPJCJLSMJ("2101", "危险化学品.监察笔录扫描件"),
	WXHXPJCBG("2102", "危险化学品.监察报告"),
	WXHXPXZWS("2103", "危险化学品.行政文书"),
	WXHXPQTZL("2104", "危险化学品.其他资料"),
	WXHXPCLYJS("2105", "危险化学品.处理意见书"),
	WXHXPZBZL("2106", "危险化学品.准备资料"),
	WXHXPDBFJ("2107", "危险化学品.打包附件"),
	WXHXPSPZL("2108", "危险化学品.视频资料"),
	WXHXPLYZL("2109", "危险化学品.录音资料"),
	WXHXPZP("2110", "危险化学品.照片"),
	WXHXPHPPFWJ("2111", "危险化学品.环评批复文件"),
	WXHXPYSPFWJ("2112", "危险化学品.验收批复文件"),
	
	
	FSAQ("22","辐射安全"),
	FSAQJCJL("2200", "辐射安全.监察笔录"),
	FSAQJCJLSMJ("2201", "辐射安全.监察笔录扫描件"),
	FSAQJCBG("2202", "辐射安全.监察报告"),
	FSAQXZWS("2203", "辐射安全.行政文书"),
	FSAQQTZL("2204", "辐射安全.其他资料"),
	FSAQCLYJS("2205", "辐射安全.处理意见书"),
	FSAQZBZL("2206","辐射安全.准备资料"),
	FSAQMOREJCBL("2207", "辐射安全.多次检查监察笔录"),
	FSAQDBFJ("2208", "辐射安全.打包附件"),
	FSAQSPZL("2209", "辐射安全.视频资料"),
	FSAQLYZL("2210", "辐射安全.录音资料"),
	FSAQZP("2211", "辐射安全.照片"),
	FSAQHPPFWJ("2212", "辐射安全.环评批复文件"),
	FSAQYSPFWJ("2213", "辐射安全.验收批复文件"),
	
	
	WRSGXCDC("23", "污染事故现场调查"),
	WRSGXCDCJCJL("2300", "污染事故现场调查.监察笔录"),
	WRSGXCDCJCJLSMJ("2301", "污染事故现场调查.监察笔录扫描件"),
	WRSGXCDCJCBG("2302", "污染事故现场调查.监察报告"),
	WRSGXCDCXZWS("2303", "污染事故现场调查.行政文书"),
	WRSGXCDCQTZL("2304", "污染事故现场调查.其他资料"),
	WRSGXCDCCLYJS("2305", "污染事故现场调查.处理意见书"),
	WRSGXCDCZBZL("2306", "污染事故现场调查.准备资料"),
	WRSGXCDCMOREJCBL("2307", "污染事故现场调查.多次检查监察笔录"),
	WRSGXCDCDBFJ("2308", "污染事故现场调查.打包附件"),
	WRSGXCDCSPZL("2309", "污染事故现场调查.视频资料"),
	WRSGXCDCLYZL("2310", "污染事故现场调查.录音资料"),
	WRSGXCDCZP("2311", "污染事故现场调查.照片"),
	WRSGXCDCHPPFWJ("2312", "污染事故现场调查.环评批复文件"),
	WRSGXCDCYSPFWJ("2313", "污染事故现场调查.验收批复文件"),
	
	
	ZFWJGL("24", "执法文件管理"),
	ZFWJGLZFWJ("2400", "执法文件管理.执法文件"),
	
	
	RWGL("25", "任务管理"),
	RWGLPFFJ("2500", "任务管理.派发附件"),
	RWGLZPFJ("2501", "任务管理.转派附件"),
	RWGLXPFJ("2502", "任务管理.下派附件"),
	
	GYWRY("31", "工业污染源"),
	GYWRYZZJGDMSMJ("3100", "工业污染源.组织机构代码扫描件"),
	GYWRYXCKCSYT("3101", "工业污染源.现场勘查示意图"),
	GYWRYYYZZSMJ("3102", "工业污染源.营业执照扫描件"),
	GYWRYPWXKZSMJ("3103", "工业污染源.排污许可证扫描件"),
	GYWRYQT("3104", "工业污染源.其他"),
	//增加工业污染源的附件类型
		GYWRYGSYYZZ("3105", "1工商营业执照"),
		GYWRYQYZZJGDMZ("3106","2企业组织机构代码证"),
		GYWRYQQZPMSYT("3107","3企业总平面示意图"),
		GYWRYQQSBQD("3108","4企业设备清单"),
		GYWRYQQYFWLXHQD("3109", "5企业原辅物料消耗清单（包括用能、用水、用汽等）"),
		GYWRYQQSCGMYSCGY("3110", "6企业生产规模与生产工艺"),
		GYWRYQYZBHJMGDFBT("3111", "7企业周边环境敏感点分布图"),
		GYWRYQQWRWNDPFQK("3112", "8企业污染物年度排放情况"),
		GYWRYHJYXPJBGWJ("3113", "9环境影响评价报告文件"),
		GYWRYHBXZBMDJSXMPF("3114", "10环保行政部门对建设项目的环评文件批复"),
		GYWRYQQJSXMJGHJBG("3115", "11企业建设项目竣工环境保护验收报告"),
		GYWRYHBXZBMYSPF("3116", "12环保行政部门对企业建设项目环保验收批复文件"),
		GYWRYQQJSXMSSCSQB("3117", "13企业建设项目试生产申请表"),
		GYWRYQQJSXMSSCPF("3118", "14企业建设项目试生产批复"),
		GYWRYYCSSCPF("3119", "15延长试生产批复"),
		GYWRYQQJSXMHJJLBG("3120", "16★企业建设项目环境监理报告"),
		GYWRYFSCLJHFA("3121", "17★废水处理设计方案"),
		GYWRYFQCLSJFA("3122", "18★废气处理设计方案"),
		GYWRYGFCZSJFA("3123", "19★固废处置设计方案"),
		GYWRYJPZRZHHBBMJPWJ("3124", "20减排责任状或环保部门下达的减排任务文件"),
		GYWRYQQQZQJSCSHBG("3125", "21★企业强制清洁生产审核报告"),
		GYWRYQQQZQZSCSHYSPF("3126", "22企业强制清洁生产审核验收批复"),
		GYWRYQQGFHPWKJBZPZP("3127", "23企业规范化排污口及标志牌照片"),
		GYWRYGYGTFWCLLYB("3128", "24工业固体废物处理利用表"),
		GYWRYGYFCZHZHLYQQZZSM("3129", "25工业固废处置或综合利用企业资质说明"),
		GYWRYZPZWWCQKCL("3130", "26减排任务完成情况材料"),
		GYWRYPWQJYZBHJYPZ("3131", "27企业通过排污权交易的排污指标批文及排污权交易凭证"),
		GYWRYTGISOHBTXRZZS("3132", "28企业通过ISO14000等环保体系认证的证书"),
		GYWRYZYWRZZFA("3133", "29★企业污染整治方案"),
		GYWRYQYWRWPFSQB("3134", "30企业污染物排放申请表"),
		GYWRYQYPWFJFSJ("3135", "31企业排污费缴费收据"),
		GYWRYHBBMHFWRWPFXKZ("3136", "32环保部门核发的污染物排放许可证"),
		GYWRYHBBMDQYJDXJCBG("3137", "33环保部门对企业的监督性监测报告"),
		GYWRYFSAQXKZ("3138", "34辐射安全许可证"),
		GYWRYWXFWJYXKZ("3139", "35危险废物经营许可证"),
		GYWRYWXFWZYJHBPB("3140", "36危险废物转移计划报批表"),
		GYWRYWXFWAQCZWTXY("3141", "37危险废物安全处置委托协议（合同书）"),
		GYWRYWXFWJSFAQCZJYXKZ("3142", "38危险废物接收方的危险废物安全处置经营许可证书"),
		GYWRYWXFWZYD("3143", "39危险废物转移联单（或危险废物电子转移联单打印盖章）"),
		GYWRYHBXZBMXDQYWRQXZLTZ("3144", "40环保行政部门下达企业污染限期治理的通知"),
		GYWRHBBMXQZLYSBG("3145", "41★环保部门限期治理验收监测报告"),
		GYWRYHBXZBMZLYSPF("3146", "42环保行政部门同意限期治理正式验收的批复"),
		GYWRYQYFWZXJCJKYSBG("3147", "43企业废水、废气和重金属污染物排放在线监测监控系统验收报告"),
		GYWRYWRWPFZXJCXTDBSH("3148", "44污染物排放在线监测系统比对结果或有效性审核"),
		GYWRYYJYA("3149", "45★经正式发布、审批、备案的企业突发环境事件应急预案（有危险废物产生的要有危险废物风险防范专章或专门的危险废物应急预案）"),
		GYWRYYJYABAB("3150", "46应急预案备案表"),
		GYWRYQYTFYJYAYLJL("3151", "47企业突发环境事件应急预案演练记录（演练计划、方案、记录、总结等）"),
		GYWRYQYYJWZQDB("3152", "48企业事故应急池、相关管路、应急阀门照片，应急物资清单表"),
		GYWRYQYXFTSSLJLCLJG("3153", "49企业信访投诉的受理记录和处理结果"),
		GYWRYHBJCBMXCJCJL("3154", "50环保监察部门现场监察记录"),
		GYWRYHBBMDHJWFCFJDS("3155", "51环保部门对企业环境违法的行政处罚决定书"),
		GYWRYHJBHJGSZWJ("3156", "52企业环境保护机构设置文件（含定员、定岗内容）"),
		GYWRYGLWRFZGLZD("3157", "53★企业各类污染防治等环境管理制度"),
		GYWRYHBSSYXTZJL("3158", "54★企业环保设施运行台账记录（水、气、噪声、固废）"),
		GYWRYNBKZLXJCSJ("3159", "55企业内部开展的例行监测数据"),
		GYWRYWRZRBXXGWJ("3160", "56企业污染责任保险相关文件"),
		GYWRYTFXHJWRSGCZBG("3161", "57企业突发性环境污染事故处置记录和报告"),
		GYWRYWHPJCB("3163", "危化品检查表"),
		
	YY("32", "医院"),
	YYZZJGDMSMJ("3200", "医院.组织机构代码扫描件"),
	YYXCKCSYT("3201", "医院.现场勘查示意图"),
	YYFSAQXKZSMJ("3202", "医院.辐射安全许可证扫描件"),
	YYPWXKZSMJ("3203", "医院.排污许可证扫描件"),
	YYQT("3204", "医院.其他"),
	
	
	GL("33", "锅炉"),
	GLZZJGDMSMJ("3300", "锅炉.组织机构代码扫描件"),
	GLXCKCSYT("3301", "锅炉.现场勘查示意图"),
	GLYYZZSMJ("3302", "锅炉.营业执照扫描件"),
	GLPWXKZSMJ("3303", "锅炉.排污许可证扫描件"),
	GLQT("3304", "锅炉.其他"),
	
	
	JZGD("34", "建筑工地"),
	JZGDZZJGDMSMJ("3400", "建筑工地.组织机构代码扫描件"),
	JZGDXCKCSYT("3401", "建筑工地.现场勘查示意图"),
	JZGDQT("3402", "建筑工地.其他"),
	
	
	XQYZ("35", "畜禽养殖"),
	XQYZZZJGDMSMJ("3500", "畜禽养殖.组织机构代码扫描件"),
	XQYZXCKCSYT("3501", "畜禽养殖.现场勘查示意图"),
	XQYYZZSMJ("3502", "畜禽养殖.营业执照扫描件"),
	XQPWXKZSMJ("3503", "畜禽养殖.排污许可证扫描件"),
	XQYZQT("3504", "畜禽养殖.其他"),
	
	
	SC("36", "三产"),
	SCXCKCSYT("3600", "三产.现场勘查示意图"),
	SCYYZZSMJ("3601", "三产.营业执照扫描件"),
	SCPWXKZSMJ("3602", "三产.排污许可证扫描件"),
	SCWSXKZSMJ("3603", "三产.卫生许可证扫描件"),
	SCQT("3604", "三产.其他"),
	
	
	JSXM("37", "建设项目"),
	JSXMXCKCSYT("3700", "建设项目.现场勘查示意图"),
	JSXMQT("3701", "建设项目.其他"),
	
	
	HPXX("38", "环评信息"),
	HPXXHPSPWH("3800", "环评信息.环评审批文号"),
	HPXXSSCSPWH("3801", "环评信息.试生产审批文号"),
	HPXXYQSSCSPWH("3802", "环评信息.延期试生产审批文号"),
	HPXXSTSYSSPWH("3803", "环评信息.三同时验收审批文号"),
	HPXXQJSCSPWH("3804", "环评信息.清洁生产审批文号"),
	
	
	TSXX("39", "投诉信息"),
	TSXXFJ("3900", "投诉信息.附件"),
	
	
	YHXX("51", "用户信息"),
	YHXXTX("5101", "用户信息照片"),
	
	
	APP("52", "程序包"),
	APPZD("5201", "程序包.终端"),
	APPPC("5202", "程序包.PC版"),
	APPPCDATA("5203", "程序包.PC版数据包"),
	APPWEBHELP("5204", "后台帮助文档"),
	APPMOHELP("5205", "终端帮助文档"),
	APPMOBLIE("5206", "程序包.手机版"),
	
	
	JCMB("6000", "监察模板"),
	
	FWY("61", "服务业"),
	FWYYYZZSMJ("6101", "服务业.营业执照扫描件"),
	FWYQT("6102", "服务业.其他"),
	
	YSY("62", "饮食业"),
	YSYYYZZSMJ("6201", "饮食业.营业执照扫描件"),
	YSYQT("6202", "饮食业.其他"),
	
	ZZY("63", "制造业"),
	ZZYYYZZSMJ("6301", "制造业.营业执照扫描件"),
	ZZYQT("6302", "制造业.其他"),
	
	YLY("64", "娱乐业"),
	YLYYYZZSMJ("6401", "娱乐业.营业执照扫描件"),
	YLYQT("6402", "娱乐业.其他"),
	
	GJGL("65", "稿件管理"),
	GJGLGJFJ("6500", "稿件管理.稿件附件"),
	
	RCBG("66", "日常办公"),
	RCBGZBZL("6601", "日常办公.准备资料"),
	RCBGBLZL("6602", "日常办公.办理资料"),
	RCBGDBFJ("6603", "日常办公.打包附件"),
	
	XFDJ("67", "信访登记"),
	XFDJXFDJB("6701", "信访登记.信访登记表"),
	
	//会商附件
	HSFJ("68","会商附件"),
	HSFJFSHSFJ("6801","会商附件.发送会商附件"),
	HSFJYJFJ("6802","会商附件.意见附件"),
	
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
	 * 函数介绍：根据任务类型编号获得附件类型集合，用逗号隔开
	
	 * 输入参数：
	
	 * 返回值：
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
	
	/** 编码 */
	private String code;
	/** 名称 */
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
