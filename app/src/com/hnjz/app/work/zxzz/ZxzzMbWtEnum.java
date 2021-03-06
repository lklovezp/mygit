package com.hnjz.app.work.zxzz;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

public enum ZxzzMbWtEnum {
	//101废气污染源自动监控设施例行检查表问题枚举
	FQPWSSJGYS("101", "10000001", "1、排污口应与污染源自动监控设施竣工验收一致"),
	FQPWSSDJBA("101", "10000002", "2、排污口应与污染源自动监控设施登记备案一致"),
	FQPWZDJCSH("101", "10000003", "3、排污口应与最近一次污染源自动监测数据有效性审核一致"),
	FSJLCYD("101", "10000004", "1、采样位置位于渠道计量水槽流路的中央，且采样口采水的前端设在下流的方向"),
	FSHLCFCS("101", "10000005", "2、测量合流排水时，在合流后充分混合的场所采水"),
	FQCYXZQY("101", "10000006", "1、采样点位应选择在垂直管段和烟道负压区域"),
	FQCYDCD("101", "10000007", "2、采样点位应避开烟道弯头和断面急剧变化的部位，尽可能选择在气流稳定的断面，且采样点位前直管段的长度应大于后直管段的长度"),
	FQCYDZPQG("101", "10000008", "3、若一个固定污染源排气先通过多个烟道后进入该固定污染源的总排气管时，采样点位应设置在该固定污染源的总排气管上"),
	JCZFYQZC("101", "10000009", "监测站房内应有空调、不间断电源、灭火设备、给排水设施。各项环境条件满足仪器设备正常工作的要求"),
	SZXZGBJC("101", "10000010", "污染源自动监控设施是否未经环保部门批准拆除、闲置和关闭停运"),
	BGQKJGYS("101", "10000011", "1、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与污染源自动监控设施竣工验收一致"),
	BGQKDJBA("101", "10000012", "2、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与污染源自动监控设施登记备案一致"),
	BGQKJCSH("101", "10000013", "3、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与最近一次污染源自动监测数据有效性审核一致"),
	BGQKCYYS("101", "10000014", "4、污染源自动监控设施采样点位置应与验收一致 "),
	BGQKCYDJ("101", "10000015", "5、污染源自动监控设施采样点位置应与登记备案一致"),
	BGQKCYSH("101", "10000016", "6、污染源自动监控设施采样点位置应与最近一次有效性审核一致"),
	YXJCGZZK("101", "10000017", "1、自动监控设施各组成部分处于完好状态，正常运转"),
	YXJCSJZZ("101", "10000018", "2、各分析仪器产生的含有危险废物的废液有专门收集装置"),
	YXJCSJCS("101", "10000019", "1、污染源自动监控设施应按要求正常工作并传输数据"),
	YXJCSJFX("101", "10000020", "2、分析仪器数据、数采仪数据、监控中心数据应一致"),
	YXJCLSSJ("101", "10000021", "3、历史数据完整，应保存一年以上"),
	YXJCFSJC("101", "10000022", "1、废水自动监控设施运行维护管理符合HJ/T 355的有关规定"),
	YXJCFQJC("101", "10000023", "2、废气自动监控设施运行维护管理符合HJ/T 75的有关规定"),
	YXJCSSJC("101", "10000024", "3、自动监控设施运行维护记录应包括停运记录、故障及其处理、耗材更换等情况"),
	YXJCSJYXJC("101", "10000025", "1、自动监测数据应通过有效性审核"),
	YXJCSJZGCS("101", "10000026", "2、如未通过数据有效性审核，应落实整改措施"),
	YXJCYXCSJG("101", "10000027", "1、自动监控设施运行参数与污染源自动监控设施竣工验收一致"),
	YXJCYXCSBA("101", "10000028", "2、自动监控设施运行参数与污染源自动监控设施登记备案一致"),
	YXJCYXCSSH("101", "10000029", "3、自动监控设施运行参数与最近一次数据有效性审核一致"),
	ZZJCDWFHGD("101", "10000030", "1、社会化运行单位是否符合《环境污染治理设施运营资质许可管理办法》相关规定"),
	ZZJCDWJYZZ("101", "10000031", "2、社会化运行单位是否具有污染源自动监控设施运营资质"),
	ZZJCDWJXHD("101", "10000032", "3、社会化运营单位是否在有效期内并按资质规定从事运行活动"),
	ZZJCYWRYSG("101", "10000033", "从事污染源自动监控系统的运维、化验分析人员应通过培训并取得相应合格证书，持证上岗"),
	ZZJCYQSBBG("101", "10000034", "1、具备环境保护部环境监测仪器质量监督检测中心出具的产品适用性检测合格报告"),
	ZZJCYQSBZS("101", "10000035", "2、环境保护产品认证证书"),
	ZZJCYQSBFH("101", "10000036", "3、仪器设备的名称、型号是否与上述各类证书相符合"),
	ZDJCSJXGJC("101", "10000037", "企业生产负荷及工况、污染治理设施运行状况与自动监控设施显示数据变化的相关性，特别是其变化趋势应符合逻辑"),
	SJYCJCCWSJ("101", "10000038", "1、长期无正当理由无自动监控数据 "),
	SJYCJCCXBD("101", "10000039", "2、自动监控数据长期在仪器分析方法检出限上下波动。"),
	SJYCJCCQBD("101", "10000040", "3、自动监控数据变化幅度长期在某一固定值上下小幅波动。"),
	SJYCJCYNBD("101", "10000041", "4、自动监控数据变化幅度长期在量程2%以内波动。"),
	SJYCJCWCCG("101", "10000042", "5、监督性监测数值与同时段自动监控数值的误差超过HJ/T 354及HJ/T 75规定的比对监测指标范围 "),
	SJYCJCFXXY("101", "10000043", "6、分析仪器数据与数采仪数据偏差应小于1%"),
	SJYCJCSCXY("101", "10000044", "7、数采仪数据与监控中心数据偏差应小于1%"),
	SJYCJCGDBD("101", "10000045", "8、自动监控数据变化幅度无正当理由长期在某一固定值上下波动"),
	YQCSSZJCGD("101", "10000046", "1、仪器量程设置过大"),
	YQCSSZJCBA("101", "10000047", "2、实际监测条件发生变化，仪器参数未相应调整或变化调整未进行登记备案"),
	YQCSSZJCGS("101", "10000048", "3、自动监控数据换算公式与有关国家技术规定不一致"),
	YQCSSZJCDJ("101", "10000049", "4、标准曲线发生改变未进行登记备案"),
	SSZTJCTYXZ("101", "10000050", "1、部分擅自停运或闲置"),
	SSZTGZDJBA("101", "10000051", "2、工作环境发生变化未进行登记备案"),
	SSZTYJRJBA("101", "10000052", "3、自动监控设施硬件、软件发生变化未进行登记备案"),
	
	//102废气污染源自动监控设施采样单元重点检查表问题枚举值
	FSCYYQJC01("102", "10000053", "1、采样点与分析仪器连接正常联通"),
	FSCYYQJC02("102", "10000054", "2、反冲洗管路是否存在对采集水样的稀释现象"),
	FSCYYQJC03("102", "10000055", "3、水样预处理装置与验收文件、登记备案或最近一次有效性审核一致，无过度处理现象"),
	FQCYJCDY01("102", "10000056", "1、加热采样探头内部及滤芯无玷污和堵塞现象，其过滤器加热温度符合仪器说明书要求。"),
	FQCYJCDY02("102", "10000057", "2、采样伴热管的长度通常在76m以内，且其走向向下倾斜度大于5°，管路无低凹或凸起，伴热管温度通常大于120℃（直接抽取法）"),
	FQCYJCDY03("102", "10000058", "3、反吹系统正常工作，反吹气压缩机正常工作"),
	FQCYJCDY04("102", "10000059", "4、稀释比恒定,其数值与登记备案一致（稀释抽取法）"),
	FQCYJCDY05("102", "10000060", "5、气水分离器工作正常"),
	FSCYBZCYX01("102", "10000061", "1、存在给水、排水管路外的其他旁路"),
	FSCYBZCYX02("102", "10000062", "2、反冲洗管路存在对采集水样的稀释现象"),
	FSCYBZCYX03("102", "10000063", "3、水样预处理装置与验收文件、登记备案或最近一次有效性审核不一致，存在过度处理现象"),
	FQCYBZCYX01("102", "10000064", "1、加热采样探头内部及滤芯玷污和堵塞"),
	FQCYBZCYX02("102", "10000065", "2、采样探头过滤器加热温度不符合仪器说明书要求"),
	FQCYBZCYX03("102", "10000066", "3、目测加热导管存在平直的管段或明显U型管段"),
	FQCYBZCYX04("102", "10000067", "4、管线存在纽结、缠绕或断裂的现象"),
	FQCYBZCYX05("102", "10000068", "5、伴热管温度过低"),
	FQCYBZCYX06("102", "10000069", "6、反吹周期、时间、空压机表头压力不符合仪器说明书要求"),
	FQCYBZCYX07("102", "10000070", "7、稀释气流量及样品气流量不稳定"),
	FQCYBZCYX08("102", "10000071", "8、稀释比、流量与登记备案不一致"),
	FQCYBZCYX09("102", "10000072", "9、气水分离器冷凝器温度与登记备案不一致"),
	FQCYBZCYX10("102", "10000073", "10、干燥器滤芯变色"),
	FQCYBZCYX11("102", "10000074", "11、冷凝器无冷凝水排出"),
	
	//103固定污染源烟气自动监控设施CEMS重点检查表问题枚举值
	SONOFXDY01("103", "10000075", "1、颗粒物过滤器干净"),
	SONOFXDY02("103", "10000076", "2、红外法及化学发光法的NO2转换器工作正常，其温度与登记备案一致"),
	SONOFXDY03("103", "10000077", "3、仪器内部管路连接紧固，管壁无积灰及冷凝水"),
	KLWFXDY01("103", "10000078", "1、吹扫系统电机正常工作"),
	KLWFXDY02("103", "10000079", "2、隔离烟气与光学探头的玻璃视窗清洁，仪器光路准直"),
	KLWFXDY03("103", "10000080", "3、吹扫系统的管道连接正常"),
	KLWFXDY04("103", "10000081", "4、 吹扫风机的净化风滤芯清洁"),
	YQCSFXDY01("103", "10000082", "1、皮托管无变形，并与气流方向垂直，紧固法兰无松动"),
	YQCSFXDY02("103", "10000083", "2、 热敏温度计表面无积尘"),
	YQCSFXDY03("103", "10000084", "3、 空气过量系数、皮托管系数K 值、烟道截面积、速度场系数与登记备案一致"),
	YQCSFXDY04("103", "10000085", "4、废气排放量、气态污染物浓度等换算符合HJ/T 397的有关要求"),
	GDFQJYBZ01("103", "10000086", "固定污染源烟气CEMS运行过程中应当按照HJ/T 75的相关要求，开展定期校准和定期检验"),
	SONOBZCYX01("103", "10000087", "1、颗粒物过滤器肮脏、积灰"),
	SONOBZCYX02("103", "10000088", "2、仪器内部管路连接松动，管壁存在积灰及冷凝水"),
	KLWBZCYX01("103", "10000089", "1、吹扫系统电机出现异常噪声、震动"),
	KLWBZCYX02("103", "10000090", "2、隔离烟气与光学探头的玻璃视窗表面积尘，仪器光路偏离"),
	KLWBZCYX03("103", "10000091", "3、吹扫系统的管道有裂缝，连接松动"),
	KLWBZCYX04("103", "10000092", "4、吹扫风机的净化风滤芯积灰"),
	YQCSBZCYX01("103", "10000093", "1、皮托管变形、堵塞，与烟道气流方向偏离，不垂直"),
	YQCSBZCYX02("103", "10000094", "2、热敏温度计表面有腐蚀情况，有积尘"),
	YQCSBZCYX03("103", "10000095", "3、空气过量系数、皮托管系数K 值、烟道截面积、速度场系数与登记备案不一致"),
	YQCSBZCYX04("103", "10000096", "4、废气排放量、气态污染物浓度等换算不符合的相关要求"),
	GDFQBZCYX01("103", "10000097", "1、零点和跨度校准频次和校验频次达不到HJ/T 75的要求"),
	GDFQBZCYX02("103", "10000098", "2、现场通入零气和标准气体测试，零点漂移和跨度漂移符合HJ/T 75规定的失控指标 "),
	GDFQBZCYX03("103", "10000099", "3、现场通入标准气体测试，准确度不符合HJ/T 75规定的参比方法验收技术指标要求。"),
	
	//104数据采集传输仪器重点检查表问题枚举值
	YQCSJCJG01("104", "10000100", "自动监控仪器和数据采集传输仪器中数据采集参数（如量程等）设置应一致，并与验收文件、登记备案或上一次有效性审核一致"),
	XLLJJCJG01("104", "10000101", "自动监控仪器与数据采集传输仪器间的数据线路正常连接"),
	SJCSJCJG01("104", "10000102", "上位机与数据采集单元采集到实时数值应一致"),
	YQCSBZCQX01("104", "10000103", "1、参数设置与验收文件、登记备案或上一次有效性审核不一致 "),
	YQCSBZCQX02("104", "10000104", "2、数据采集参数高限设置过低或低限设置过高"),
	XLLJBZCYX01("104", "10000105", "1、数据采集传输仪与自动监控仪器间加装有不明的数据处理设备（如可编程控制器）或信号处理设备（如滤波器等限制电流波动范围的设备）"),
	XLLJBZCYX02("104", "10000106", "2、数据采集传输仪器与通信设备（调制解调器、无线发射器、光纤通讯设备）之间连接其他不明设备。"),
	XLLJBZCYX03("104", "10000107", "3、自动监控设施停止工作后，数据采集传输仪仍产生并自动发送与实际情况不相符的数据。"),
	SJCSBZCYX01("104", "10000108", "加装软件限制数据大小和调整数据"),
	
	//105固定污染源烟气连续监测系统现场核查表（满分100分）问题枚举
	JCYFYQAZ01("105", "10000109", "面积大于6m2"),
	JCYFYQAZ02("105", "10000110", "配备专用配电以及空调"),
	JCYFYQAZ03("105", "10000111", "具有防雷系统"),
	JCYFYQAZ04("105", "10000112", "颗粒物CEMS:尽可能安装在流速不大于5m/s的位置优先选择在垂直管段，避开烟道弯头和断面急剧变化的部位，设置在距弯头、阀门、变管下游方向不小于4倍直径，和距上述部件上游方向不小于2倍直径处"),
	JCYFYQAZ05("105", "10000113", "气态污染物CEMS：位于气态污染物混合均匀的位置，该处能代表固定污染源的排放，同时便于日常维护"),
	JCYFYQAZ06("105", "10000114", "流速连续测量系统：安装位置位于颗粒物和气态污染物CMES下游>300mm处，但不得影响颗粒物和气态污染物CMES的测定烟气流速连续测量系统，尽可能安装在流速大于5m/s的位置"),
	JCYFYQAZ07("105", "10000115", "探头的安装规范，管线有无破损，管线走向向下倾斜5度"),
	JCYFYQAZ08("105", "10000116", "在监测孔下游0.5m左右预留手工采样孔"),
	JCYFYQAZ09("105", "10000117", "操作平台面积大于5m2 ,平台栏杆高度大于1.2m"),
	JCYFYQAZ10("105", "10000118", "采用斜爬梯"),
	CEMSWZXJC01("105", "10000119", "有颗粒物、SO2、NOX污染物监测系统如不需装，须已向责任环保部门备案"),
	CEMSWZXJC02("105", "10000120", "有流速、温度、含氧量、烟道压力、湿度监测系统"),
	CEMSWZXJC03("105", "10000123", "有采样伴热管、制冷器、烟尘过滤部分"),
	CEMSWZXJC04("105", "10000124", "采样探头、皮托管测压孔、采样管线、烟尘监测仪有反吹风清洗功能"),
	SBYXWHDF01("105", "10000125", "控制面板的灯光、指示器状态正常"),
	SBYXWHDF02("105", "10000126", "量程如可设，设为3倍排放标准值，如不可设，应能查看"),
	SBYXWHDF03("105", "10000127", "采样伴热管控温大于120度"),
	SBYXWHDF04("105", "10000128", "制冷器温度低于5度"),
	SBYXWHDF05("105", "10000129", "过滤器定期清洗、更换，并有记录"),
	SBYXWHDF06("105", "10000130", "采样泵无腐蚀、非正常的噪声或漏气"),
	SBYXWHDF07("105", "10000131", "标准气体为技术监督部门授权单位生产，有标准气体证明书，在有效期内其浓度与量程相匹配"),
	SBYXWHDF08("105", "10000132", "速度场系数 ：根据比对记录，计算自动监测流速/人工监测流速"),
	SBYXWHDF09("105", "10000133", "皮托管系数K : 0.81～0.86"),
	SBYXWHDF10("105", "10000134", "过量空气系数α：燃煤锅炉65t/h以下为1.8，以上为1.4；火电厂1997年前投运为1.7，之后为1.4；燃油燃气锅炉为1.2"),
	SBYXWHDF11("105", "10000135", "SO2、NOX标气现场测定符合要求"),
	SJCJXTZQ01("105", "10000136", "现场核查时正常联网"),
	SJCJXTZQ02("105", "10000137", "数采仪显示器正常运行"),
	SJCJXTZQ03("105", "10000138", "数采仪上的量程与对应因子的仪器量程相同"),
	SJCJXTZQ04("105", "10000139", "颗粒物、SO2、NOX、流速一次仪表和数采的数据误差不大于2%，不允许大于5%"),
	SJCJXTZQ05("105", "10000140", "用最近的校准记录校核数采仪同时刻SO2、NOX数据"),
	YXGLGF01("105", "10000141", "建立了站房管理制度、人员岗位职责"),
	YXGLGF02("105", "10000142", "企业人员有环保部颁发的有效性审核证书"),
	YXGLGF03("105", "10000143", "仪器设备使用说明书、合格证、环保产品认证、操作规程"),
	YXGLGF04("105", "10000144", "维护记录单独成册，完整、连续，平均每周1次"),
	YXGLGF05("105", "10000145", "维修记录单独成册，完整，最近1次响应至修复时间小于3天"),
	YXGLGF06("105", "10000146", "校准记录单独成册，完整、连续，质控记录每周至少1次，实样比对记录每月至少1次"),
	
	//201废水（UV）污染源自动监控设施例行检查表问题枚举
	UVFSPWSSJGYS("201", "10000147", "1、排污口应与污染源自动监控设施竣工验收一致"),
	UVFSPWSSDJBA("201", "10000148", "2、排污口应与污染源自动监控设施登记备案一致"),
	UVFSPWZDJCSH("201", "10000149", "3、排污口应与最近一次污染源自动监测数据有效性审核一致"),
	UVFSJLCYD("201", "10000150", "1、采样位置位于渠道计量水槽流路的中央，且采样口采水的前端设在下流的方向"),
	UVFSHLCFCS("201", "10000151", "2、测量合流排水时，在合流后充分混合的场所采水"),
	UVFSCYXZQY("201", "10000152", "1、采样点位应选择在垂直管段和烟道负压区域"),
	UVFSCYDCD("201", "10000153", "2、采样点位应避开烟道弯头和断面急剧变化的部位，尽可能选择在气流稳定的断面，且采样点位前直管段的长度应大于后直管段的长度"),
	UVFSCYDZPQG("201", "10000154", "3、若一个固定污染源排气先通过多个烟道后进入该固定污染源的总排气管时，采样点位应设置在该固定污染源的总排气管上"),
	UVFSJCZFYQZC("201", "10000155", "监测站房内应有空调、不间断电源、灭火设备、给排水设施。各项环境条件满足仪器设备正常工作的要求"),
	UVFSSZXZGBJC("201", "10000156", "污染源自动监控设施是否未经环保部门批准拆除、闲置和关闭停运"),
	UVFSBGQKJGYS("201", "10000157", "1、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与污染源自动监控设施竣工验收一致"),
	UVFSBGQKDJBA("201", "10000158", "2、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与污染源自动监控设施登记备案一致"),
	UVFSBGQKJCSH("201", "10000159", "3、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与最近一次污染源自动监测数据有效性审核一致"),
	UVFSBGQKCYYS("201", "10000160", "4、污染源自动监控设施采样点位置应与验收一致 "),
	UVFSBGQKCYDJ("201", "10000161", "5、污染源自动监控设施采样点位置应与登记备案一致"),
	UVFSBGQKCYSH("201", "10000162", "6、污染源自动监控设施采样点位置应与最近一次有效性审核一致"),
	UVFSYXJCGZZK("201", "10000163", "1、自动监控设施各组成部分处于完好状态，正常运转"),
	UVFSYXJCSJZZ("201", "10000164", "2、各分析仪器产生的含有危险废物的废液有专门收集装置"),
	UVFSYXJCSJCS("201", "10000165", "1、污染源自动监控设施应按要求正常工作并传输数据"),
	UVFSYXJCSJFX("201", "10000167", "2、分析仪器数据、数采仪数据、监控中心数据应一致"),
	UVFSYXJCLSSJ("201", "10000168", "3、历史数据完整，应保存一年以上"),
	UVFSYXJCFSJC("201", "10000169", "1、废水自动监控设施运行维护管理符合HJ/T 355的有关规定"),
	UVFSYXJCFQJC("201", "10000170", "2、废气自动监控设施运行维护管理符合HJ/T 75的有关规定"),
	UVFSYXJCSSJC("201", "10000171", "3、自动监控设施运行维护记录应包括停运记录、故障及其处理、耗材更换等情况"),
	UVFSYXJCSJYXJC("201", "10000172", "1、自动监测数据应通过有效性审核"),
	UVFSYXJCSJZGCS("201", "10000173", "2、如未通过数据有效性审核，应落实整改措施"),
	UVFSYXJCYXCSJG("201", "10000174", "1、自动监控设施运行参数与污染源自动监控设施竣工验收一致"),
	UVFSYXJCYXCSBA("201", "10000175", "2、自动监控设施运行参数与污染源自动监控设施登记备案一致"),
	UVFSYXJCYXCSSH("201", "10000176", "3、自动监控设施运行参数与最近一次数据有效性审核一致"),
	UVFSZZJCDWFHGD("201", "10000177", "1、社会化运行单位是否符合《环境污染治理设施运营资质许可管理办法》相关规定"),
	UVFSZZJCDWJYZZ("201", "10000178", "2、社会化运行单位是否具有污染源自动监控设施运营资质"),
	UVFSZZJCDWJXHD("201", "10000179", "3、社会化运营单位是否在有效期内并按资质规定从事运行活动"),
	UVFSZZJCYWRYSG("201", "10000180", "从事污染源自动监控系统的运维、化验分析人员应通过培训并取得相应合格证书，持证上岗"),
	UVFSZZJCYQSBBG("201", "10000181", "1、具备环境保护部环境监测仪器质量监督检测中心出具的产品适用性检测合格报告"),
	UVFSZZJCYQSBZS("201", "10000182", "2、环境保护产品认证证书"),
	UVFSZZJCYQSBFH("201", "10000183", "3、仪器设备的名称、型号是否与上述各类证书相符合"),
	UVFSZDJCSJXGJC("201", "10000184", "企业生产负荷及工况、污染治理设施运行状况与自动监控设施显示数据变化的相关性，特别是其变化趋势应符合逻辑"),
	UVFSSJYCJCCWSJ("201", "10000185", "1、长期无正当理由无自动监控数据 "),
	UVFSSJYCJCCXBD("201", "10000186", "2、自动监控数据长期在仪器分析方法检出限上下波动。"),
	UVFSSJYCJCCQBD("201", "10000187", "3、自动监控数据变化幅度长期在某一固定值上下小幅波动。"),
	UVFSSJYCJCYNBD("201", "10000188", "4、自动监控数据变化幅度长期在量程2%以内波动。"),
	UVFSSJYCJCWCCG("201", "10000189", "5、监督性监测数值与同时段自动监控数值的误差超过HJ/T 354及HJ/T 75规定的比对监测指标范围 "),
	UVFSSJYCJCFXXY("201", "10000190", "6、分析仪器数据与数采仪数据偏差应小于1%"),
	UVFSSJYCJCSCXY("201", "10000191", "7、数采仪数据与监控中心数据偏差应小于1%"),
	UVFSSJYCJCGDBD("201", "10000192", "8、自动监控数据变化幅度无正当理由长期在某一固定值上下波动"),
	UVFSYQCSSZJCGD("201", "10000193", "1、仪器量程设置过大"),
	UVFSYQCSSZJCBA("201", "10000194", "2、实际监测条件发生变化，仪器参数未相应调整或变化调整未进行登记备案"),
	UVFSYQCSSZJCGS("201", "10000195", "3、自动监控数据换算公式与有关国家技术规定不一致"),
	UVFSYQCSSZJCDJ("201", "10000196", "4、标准曲线发生改变未进行登记备案"),
	UVFSSSZTJCTYXZ("201", "10000197", "1、部分擅自停运或闲置"),
	UVFSSSZTGZDJBA("201", "10000198", "2、工作环境发生变化未进行登记备案"),
	UVFSSSZTYJRJBA("201", "10000199", "3、自动监控设施硬件、软件发生变化未进行登记备案"),
	
	//202废水(UV)污染源自动监控设施采样单元重点检查表问题枚举值
	UVFSCYYQJC01("202", "10000200", "1、采样点与分析仪器连接正常联通"),
	UVFSCYYQJC02("202", "10000201", "2、反冲洗管路是否存在对采集水样的稀释现象"),
	UVFSCYYQJC03("202", "10000202", "3、水样预处理装置与验收文件、登记备案或最近一次有效性审核一致，无过度处理现象"),
	UVFQCYJCDY01("202", "10000203", "1、加热采样探头内部及滤芯无玷污和堵塞现象，其过滤器加热温度符合仪器说明书要求。"),
	UVFQCYJCDY02("202", "10000204", "2、采样伴热管的长度通常在76m以内，且其走向向下倾斜度大于5°，管路无低凹或凸起，伴热管温度通常大于120℃（直接抽取法）"),
	UVFQCYJCDY03("202", "10000205", "3、反吹系统正常工作，反吹气压缩机正常工作"),
	UVFQCYJCDY04("202", "10000206", "4、稀释比恒定,其数值与登记备案一致（稀释抽取法）"),
	UVFQCYJCDY05("202", "10000207", "5、气水分离器工作正常"),
	UVFSCYBZCYX01("202", "10000208", "1、存在给水、排水管路外的其他旁路"),
	UVFSCYBZCYX02("202", "10000209", "2、反冲洗管路存在对采集水样的稀释现象"),
	UVFSCYBZCYX03("202", "10000210", "3、水样预处理装置与验收文件、登记备案或最近一次有效性审核不一致，存在过度处理现象"),
	UVFQCYBZCYX01("202", "10000211", "1、加热采样探头内部及滤芯玷污和堵塞"),
	UVFQCYBZCYX02("202", "10000212", "2、采样探头过滤器加热温度不符合仪器说明书要求"),
	UVFQCYBZCYX03("202", "10000213", "3、目测加热导管存在平直的管段或明显U型管段"),
	UVFQCYBZCYX04("202", "10000214", "4、管线存在纽结、缠绕或断裂的现象"),
	UVFQCYBZCYX05("202", "10000215", "5、伴热管温度过低"),
	UVFQCYBZCYX06("202", "10000216", "6、反吹周期、时间、空压机表头压力不符合仪器说明书要求"),
	UVFQCYBZCYX07("202", "10000217", "7、稀释气流量及样品气流量不稳定"),
	UVFQCYBZCYX08("202", "10000218", "8、稀释比、流量与登记备案不一致"),
	UVFQCYBZCYX09("202", "10000219", "9、气水分离器冷凝器温度与登记备案不一致"),
	UVFQCYBZCYX10("202", "10000220", "10、干燥器滤芯变色"),
	UVFQCYBZCYX11("202", "10000221", "11、冷凝器无冷凝水排出"),
	
	//203废水(UV)总有机碳（TOC）污染源自动监控设施重点检查表问题枚举值
	UVTOCCJDY01("203", "10000222", "1、取样管路位置应正确，管路应畅通"),
	UVTOCCJDY02("203", "10000223", "2、进水阀、排水阀等均正常打开"),
	UVTOCSJDY01("203", "10000224", "1、仪器各试剂瓶内，试剂量能保证运行一周以上"),
	UVTOCSJDY02("203", "10000225", "2、仪器各试剂瓶内试剂在登记备案的使用有效期内"),
	UVTOCSJDY03("203", "10000226", "3、实际使用的试剂的种类、浓度与登记备案相符"),
	UVTOCFXDY01("203", "10000227", "1、载气采用空气或氮气，氮气纯度在99.99%以上，载气流量在登记备案的范围内，采用空气为载气必须有去除二氧化碳的空气精制装置，采用氮气为载气，在供给器和氧化反应器之间必须设置氧气混入装置"),
	UVTOCFXDY02("203", "10000228", "2、干式氧化反应器燃烧管温度在680～1000℃，或在登记备案的范围内"),
	UVTOCFXDY03("203", "10000229", "3、气液分离器应处于正常状态"),
	UVTOCCZDY01("203", "10000230", "1、仪器启动后，能够正常运转，添加试剂和水样"),
	UVTOCCZDY02("203", "10000231", "2、仪器启动正常运转后，能排出废液"),
	UVTOCJYBZ01("203", "10000232", "1、水污染源自动监测仪频次每48小时自动进行零点和量程校准，每月至少进行一次实际水样比对试验和质控样试验"),
	CJDYBZCYX01("203", "10000233", "1、启动仪器后取样泵无水样进入管路"),
	CJDYBZCYX02("203", "10000234", "2、取样管路存在旁路"),
	CJDYBZCYX03("203", "10000235", "3、取样管路损坏，或取样池干涸（污水间歇性排放除外）、锈蚀"),
	SJDYBZCYX01("203", "10000236", "1、试剂瓶内无试剂，试剂管未插入试剂液位下"),
	SJDYBZCYX02("203", "10000237", "2、试剂超过使用期限"),
	SJDYBZCYX03("203", "10000238", "3、实际使用的试剂的种类、浓度与登记备案不相符"),
	FXDYBZCYX01("203", "10000239", "1、载气流量表显示流量超过登记备案的范围"),
	FXDYBZCYX02("203", "10000240", "2、供给器和氧化反应器之间无氧气混入装置，或氧气混入装置无效"),
	FXDYBZCYX03("203", "10000241", "3、氮气纯度低于99.99%"),
	FXDYBZCYX04("203", "10000242", "4、采用空气为载气时，缺少去除二氧化碳的空气精制装置或失效"),
	FXDYBZCYX05("203", "10000243", "5、干式氧化反应器燃烧管温度过低超过登记备案范围。"),
	FXDYBZCYX06("203", "10000244", "6、燃烧器内催化剂发白、破碎或外观与备案不一致。"),
	FXDYBZCYX07("203", "10000245", "7、气液分离器中冷凝器的温度高于露点温度，或超过登记备案范围"),
	FXDYBZCYX08("203", "10000246", "8、冷凝器排水瓶内无水"),
	CZDYBZCYX01("203", "10000247", "1、仪器启动后电机不转动"),
	CZDYBZCYX02("203", "10000248", "2、仪器内部连接线路有松动脱落现象，连接管路有渗液、滴漏现象"),
	CZDYBZCYX03("203", "10000249", "3、仪器启动后内部样品管路和试剂管路内无液体流动现象"),
	CZDYBZCYX04("203", "10000250", "4、仪器显示故障或报警信号"),
	JYBZBZCYX01("203", "10000251", "1、水污染源自动监测仪零点、量程校准和比对的频次不符合HJ/T 355的相关要求"),
	JYBZBZCYX02("203", "10000252", "2、现场采用零点校准液和量程校准液试验，零点和量程漂移不符合HJ/T 355的相关要求"),
	JYBZBZCYX03("203", "10000253", "3、现场采用质控样试验，质控样测定的相对误差大于标准值的±10%"),
	
	//204废水 紫外（UV）吸收水质自动监测仪重点检查表问题枚举值
	UVUVCJDY01("204", "10000254", "1、取样管路位置正确，管路畅通"),
	UVUVCJDY02("204", "10000255", "2、进水阀、排水阀等正常打开"),
	UVUVCZDY01("204", "10000256", "1、仪器启动后，能够正常运转，添加试剂和水样"),
	UVUVCZDY02("204", "10000257", "2、仪器启动正常运转后，能排出废液"),
	UVUVCZDY03("204", "10000258", "3、仪器光吸收系数与化学需氧量相关性等参数设置情况应与登记备案一致"),
	UVUVCZDY04("204", "10000259", "4、吸收池具有自动清洗功能，能自动清除附着在吸收池表面上遮挡光路的污物"),
	UVUVJYBZ01("204", "10000260", "1、水污染源自动监测仪频次每48小时进行自动进行零点和量程校准，每月至少进行一次实际水样比对试验和质控样试验"),
	UVCJDYBZCYX01("204", "10000261", "1、启动仪器后取样泵无水样进入管路"),
	UVCJDYBZCYX02("204", "10000262", "2、取样管路存在旁路"),
	UVCJDYBZCYX03("204", "10000263", "3、取样管路损坏，或取样池干涸（污水间歇性排放除外）、锈蚀"),
	UVCZDYBZCYX01("204", "10000264", "1、仪器启动后电机不转动"),
	UVCZDYBZCYX02("204", "10000265", "2、仪器内部连接线路有松动脱落现象，连接管路有渗液、滴漏现象"),
	UVCZDYBZCYX03("204", "10000266", "3、仪器启动后内部样品管路和试剂管路内无液体流动现象"),
	UVCZDYBZCYX04("204", "10000267", "4、仪器光吸收系数与化学需氧量相关性等参数设置情况与登记备案不一致"),
	UVCZDYBZCYX05("204", "10000268", "5、吸收池不具备自清洗功能"),
	UVCZDYBZCYX06("204", "10000269", "6、吸收池表面上有遮挡光路的污物"),
	UVCZDYBZCYX07("204", "10000270", "7、仪器显示故障或报警信号"),
	UVJYBZBZCYX01("204", "10000271", "1、水污染源自动监测仪零点、量程校准和比对的频次不符合HJ/T 355的相关要求"),
	UVJYBZBZCYX02("204", "10000272", "2、现场采用零点校准液和量程校准液试验，零点和量程漂移不符合HJ/T 355的相关要求"),
	UVJYBZBZCYX03("204", "10000273", "3、现场采用质控样试验，质控样测定的相对误差大于标准值的±10%"),
	
	//205废水 氨氮污染源自动监控设施重点检查表问题枚举值
	UVADCJDY01("205", "10000274", "1、取样管路位置正确，管路畅通"),
	UVADCJDY02("205", "10000275", "2、进水阀、排水阀等正常打开"),
	UVADSJDY01("205", "10000276", "1、仪器各试剂瓶内，试剂量能保证运行一周以上"),
	UVADSJDY02("205", "10000277", "2、仪器各试剂瓶内试剂在登记备案的使用有效期内"),
	UVADSJDY03("205", "10000278", "3、实际使用的试剂的种类、浓度与登记备案相符"),
	UVADCZDY01("205", "10000279", "1、仪器启动后，能够正常运转，添加试剂和水样"),
	UVADCZDY02("205", "10000280", "2、仪器启动正常运转后，能排出废液"),
	UVADCLDY01("205", "10000281", "1、采用分光光度法测定，比色池表面无遮挡光路的污物"),
	UVADCLDY02("205", "10000282", "2、采用电极法测定，电极表面无污物"),
	UVADCLDY03("205", "10000283", "3、能自动清洗电极或比色系统"),
	UVADJYBZ01("205", "10000284", "1、水污染源自动监测仪频次每48小时进行自动进行零点和量程校准，每月至少进行一次实际水样比对试验和质控样试验"),
	ADCJDYBZCYX01("205", "10000285", "1、启动仪器后取样泵无水样进入管路"),
	ADCJDYBZCYX02("205", "10000286", "2、取样管路存在旁路"),
	ADCJDYBZCYX03("205", "10000287", "3、取样管路损坏，或取样池干涸（污水间歇性排放除外）、锈蚀"),
	ADSJDYBZCYX01("205", "10000288", "1、试剂瓶内无试剂，试剂管未插入试剂液位下"),
	ADSJDYBZCYX02("205", "10000289", "2、试剂超过使用期限"),
	ADSJDYBZCYX03("205", "10000290", "3、实际使用的试剂的种类、浓度与登记备案不相符"),
	ADCZDYBZCYX01("205", "10000291", "1、仪器启动后电机不转动"),
	ADCZDYBZCYX02("205", "10000292", "2、仪器内部连接线路有松动脱落现象，连接管路有渗液、滴漏现象"),
	ADCZDYBZCYX03("205", "10000293", "3、仪器启动后内部试剂管路内无液体流动现象"),
	ADCZDYBZCYX04("205", "10000294", "4、仪器显示故障或报警信号"),
	ADCLDYBZCYX01("205", "10000295", "1、比色池表面有遮挡光路的污物"),
	ADCLDYBZCYX02("205", "10000296", "2、电极表面玷污"),
	ADJYBZBZCYX01("205", "10000297", "1、水污染源自动监测仪零点、量程校准和比对的频次不符合HJ/T 355的相关要求"),
	ADJYBZBZCYX02("205", "10000298", "2、现场采用零点校准液和量程校准液试验，零点和量程漂移不符合HJ/T 355的相关要求"),
	ADJYBZBZCYX03("205", "10000299", "3、现场采用质控样试验，质控样测定的相对误差大于标准值的±10%"),
	
	//206废水（UV） 重金属污染源自动监控设施重点检查表问题枚举值
	UVJSCJDY01("206", "10000300", "1、取样管路位置正确，管路畅通"),
	UVJSCJDY02("206", "10000301", "2、进水阀、排水阀等正常打开"),
	UVJSSJDY01("206", "10000302", "1、仪器各试剂瓶内，试剂量能保证运行一周以上"),
	UVJSSJDY02("206", "10000303", "2、仪器各试剂瓶内试剂在登记备案的使用有效期内"),
	UVJSSJDY03("206", "10000304", "3、实际使用的试剂的种类、浓度与登记备案相符"),
	UVJSXJDY01("206", "10000305", "1、消解单元应能实现试剂的快速加热"),
	UVJSXJDY02("206", "10000306", "2、能保持恒温消解控制"),
	UVJSCZDY01("206", "10000307", "1、仪器启动后，能够正常运转，添加试剂和水样"),
	UVJSCZDY02("206", "10000308", "2、仪器启动正常运转后，能排出废液"),
	UVJSCLDY01("206", "10000309", "1、采用分光光度法测定，比色池表面无遮挡光路的污物"),
	UVJSCLDY02("206", "10000310", "2、采用电极法测定，电极表面无污物"),
	UVJSCLDY03("206", "10000311", "3、能自动清洗电极或比色系统"),
	UVJSJYBZ01("206", "10000312", "1、水污染源自动监测仪频次每48小时进行自动进行零点和量程校准，每月至少进行一次实际水样比对试验和质控样试验"),
	JSCJDYBZCYX01("206", "10000313", "1、启动仪器后取样泵无水样进入管路"),
	JSCJDYBZCYX02("206", "10000314", "2、取样管路存在旁路"),
	JSCJDYBZCYX03("206", "10000315", "3、取样管路损坏，或取样池干涸（污水间歇性排放除外）、锈蚀"),
	JSSJDYBZCYX01("206", "10000316", "1、试剂瓶内无试剂"),
	JSSJDYBZCYX02("206", "10000317", "2、试剂超过使用期限"),
	JSSJDYBZCYX03("206", "10000318", "3、实际使用的试剂的种类、浓度与登记备案不相符"),
	JSXJDYBZCYX01("206", "10000319", "1、加热消解温度及消解时间超过登记备案的范围"),
	JSXJDYBZCYX02("206", "10000320", "2、消解瓶在非工作状态，内部有结晶、沉淀"),
	JSXJDYBZCYX03("206", "10000321", "3、消解瓶下部有漏液现象"),
	JSCZDYBZCYX01("206", "10000322", "1、仪器启动后电机不转动"),
	JSCZDYBZCYX02("206", "10000323", "2、仪器内部连接线路有松动脱落现象，连接管路有渗液、滴漏现象"),
	JSCZDYBZCYX03("206", "10000324", "3、仪器启动后内部样品管路和试剂管路内无液体流动现象"),
	JSCZDYBZCYX04("206", "10000325", "4、仪器显示故障或报警信号"),
	JSCZDYBZCYX05("206", "10000326", "5、其他"),
	JSCLDYBZCYX01("206", "10000327", "1、比色池表面有遮挡光路的污物"),
	JSCLDYBZCYX02("206", "10000328", "2、电极表面玷污"),
	JSJYBZBZCYX01("206", "10000329", "1、水污染源自动监测仪零点、量程校准和比对的频次不符合HJ/T 355的相关要求"),
	JSJYBZBZCYX02("206", "10000330", "2、现场采用零点校准液和量程校准液试验，零点和量程漂移不符合HJ/T 355的相关要求"),
	JSJYBZBZCYX03("206", "10000331", "3、现场采用质控样试验，质控样测定的相对误差大于标准值的±10%"),
	
	//207废水（UV） 流量计重点检查表问题枚举值
	UVLLCSDY01("207", "10000332", "1、堰槽种类、堰槽规格、转换系数等参数设置情况与验收、登记备案、最近一次有效性审核一致（适用于超声波明渠流量计）"),
	UVLLCSDY02("207", "10000333", "2、管道管径、转换系数等参数设置与验收、登记备案、最近一次有效性审核一致（适用于超声波及电磁管道流量计）"),
	UVLLCLDY01("207", "10000334", "1、液位测量应准确。被测量介质表面无泡沫、杂物。探头位置安装在规定的点位。（适用于超声波明渠流量计）"),
	UVLLCLDY02("207", "10000335", "2、非金属管道安装的变送器接地环与变送器接地线开路接地正常（适用于电磁管道流量计）"),
	LLCSDYBZCYX01("207", "10000336", "1、堰槽种类、堰槽规格、转换系数等参数设置与验收、登记备案、最近一次有效性审核不一致（适用于超声波明渠流量计）"),
	LLCSDYBZCYX02("207", "10000337", "2、管道管径、转换系数等参数设置应与验收、登记备案、最近一次有效性审核一致（适用于超声波及电磁管道流量计）"),
	LLCLDYBZCYX01("207", "10000338", "1、测量液位后按照登记备案的参数折算为流量，其与仪器显示流量的差值超过仪器说明书流量精度的要求"),
	LLCLDYBZCYX02("207", "10000339", "2、非金属管道安装的变送器接地环与变送器接地线开路接地点腐蚀、开裂或断裂（适用于电磁管道流量计）"),
	
	//208废水（UV） 数据采集传输仪器重点检查表问题枚举值
	UVSJCSDY01("208", "10000340", "自动监控仪器和数据采集传输仪器中数据采集参数（如量程等）设置应一致，并与验收文件、登记备案或上一次有效性审核一致"),
	UVSJXLLJ01("208", "10000341", "自动监控仪器与数据采集传输仪器间的数据线路正常连接"),
	UVSJSJCS01("208", "10000342", "上位机与数据采集单元采集到实时数值应一致"),
	UVSJCSBZCYX01("208", "10000343", "1、参数设置与验收文件、登记备案或上一次有效性审核不一致"),
	UVSJCSBZCYX02("208", "10000344", "2、数据采集参数高限设置过低或低限设置过高"),
	UVSJXLBZCYX01("208", "10000345", "1、数据采集传输仪与自动监控仪器间加装有不明的数据处理设备（如可编程控制器）或信号处理设备（如滤波器等限制电流波动范围的设备）"),
	UVSJXLBZCYX02("208", "10000346", "2、数据采集传输仪器与通信设备（调制解调器、无线发射器、光纤通讯设备）之间连接其他不明设备。"),
	UVSJXLBZCYX03("208", "10000347", "3、自动监控设施停止工作后，数据采集传输仪仍产生并自动发送与实际情况不相符的数据。"),
	UVSJSJBZCYX01("208", "10000348", "加装软件限制数据大小和调整数据"),
	
	//209废水(UV) 固定污染源废水自动监测系统现场核查表（满分100分）问题枚举
	UVJCYFYQAZ01("209", "10000349", "面积大于6m2"),
	UVJCYFYQAZ02("209", "10000350", "配备专用配电以及空调"),
	UVJCYFYQAZ03("209", "10000351", "具有防雷系统"),
	UVJCYFYQAZ04("209", "10000352", "配备上下水"),
	UVJCYFYQAZ05("209", "10000353", "管路清晰"),
	UVJCYFYQAZ06("209", "10000354", "整齐干净，无闲杂物品"),
	UVJCYFYQAZ07("209", "10000355", "安装自动监测设备的取水口在总排污渠位置"),
	UVJCYFYQAZ08("209", "10000356", "排污口建设规范并安装有标准计量堰槽"),
	UVJCYFYQAZ09("209", "10000357", "有排污口标志牌、编号及特征污染物名称"),
	UVJCYFYQAZ10("209", "10000358", "计量槽安装位置上游顺直段长度应大于水面宽度5-10倍； 下游出水口无淹没流"),
	UVJCYFYQAZ11("209", "10000359", "探头安装在堰槽断面中心线上，安装牢固，不易移动；  仪器零点水位与堰槽计量零点一致"),
	UVWWMSXTWZ01("209", "10000360", "有pH计、流量计"),
	UVWWMSXTWZ02("209", "10000361", "有COD监测仪器；氨氮分析仪（列入上年国控名单的氨氮重点排放企业、省控以上污水处理厂应装）；总磷分析仪（省控以上污水处理厂）；如不需装，须已向责任环保部门备案"),
	UVWWMSXTWZ03("209", "10000362", "具有冲洗和反冲洗功能"),
	UVWWMSXTWZ04("209", "10000363", "有废液回收装置"),
	UVSBYXWHDF01("209", "10000364", "分析周期TOC为2小时一次；COD、氨氮、总磷、总氮为4小时1次"),
	UVSBYXWHDF02("209", "10000365", "控制面板的灯光、指示器状态正常"),
	UVSBYXWHDF03("209", "10000367", "量程如可设，设为3倍排放标准值，如不可设，应能查看"),
	UVSBYXWHDF04("209", "10000368", "采样泵无腐蚀、非正常的噪声或漏水"),
	UVSBYXWHDF05("209", "10000369", "COD、氨氮标准溶液在有效期内其浓度与量程相匹配"),
	UVSBYXWHDF06("209", "10000370", "其他分析试剂在有效期内"),
	UVSBYXWHDF07("209", "10000371", "TOC/COD转换系数与记录一致，无TOC分值计入下一项"),
	UVSBYXWHDF08("209", "10000372", "pH现场校验、COD、氨氮标准溶液校验符合要求"),
	UVSJCJXTZQ01("209", "10000373", "现场核查时正常联网"),
	UVSJCJXTZQ02("209", "10000374", "数采仪显示器正常运行"),
	UVSJCJXTZQ03("209", "10000375", "数采仪上的量程与对应因子的仪器量程相同"),
	UVSJCJXTZQ04("209", "10000376", "一次仪表和数采的数据误差不大于2%，不允许大于5%"),
	UVSJCJXTZQ05("209", "10000377", "用最近的校准记录校核数采仪同时刻数据"),
	UVYXGLGF01("209", "10000378", "建立了站房管理制度、人员岗位职责"),
	UVYXGLGF02("209", "10000379", "企业人员有环保部颁发的有效性审核证书"),
	UVYXGLGF03("209", "10000380", "仪器设备使用说明书、合格证、环保产品认证、操作规程"),
	UVYXGLGF04("209", "10000381", "维护记录单独成册，完整、连续，平均每周1次"),
	UVYXGLGF05("209", "10000382", "维护记录中有废液处置记录"),
	UVYXGLGF06("209", "10000383", "维修记录单独成册，完整，最近1次响应至修复时间小于3天"),
	UVYXGLGF07("209", "10000384", "校准记录单独成册，完整、连续，质控记录每周至少1次，实样比对记录每月至少1次"),
	
	;
	private ZxzzMbWtEnum(String templatecode, String code, String name) {
		this.templatecode = templatecode;
		this.code = code;
		this.name = name;
	}

	public static List<Combobox> getTypeList(String code){
		List<Combobox> re = new ArrayList<Combobox>();
		for (ZxzzMbWtEnum ele : values()) {
			if (ele.getCode().length() == 4 && ele.getCode().substring(0, 2).equals(code)) {
				re.add(new Combobox(ele.getCode(), ele.getName()));
			}
		}
		
		return re;
	}
	
	public static List<Combobox> getTypeListByEnumName(String enumName){
		String code = "";
		for (ZxzzMbWtEnum ele : values()) {
			if (ele.name().equals(enumName)) {
				code = ele.getCode();
				break;
			}
		}
		return getTypeList(code);
	}
	
	public static String getEnumByCode(String code){
		String enumName = "";
		for (ZxzzMbWtEnum ele : values()) {
			if (ele.getCode().equals(code)) {
				enumName = ele.name();
				break;
			}
		}
		return enumName;
	}
	
	public static String getTypeByEnumName(String enumName){
		String code = "";
		for (ZxzzMbWtEnum ele : values()) {
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
		for (ZxzzMbWtEnum ele : values()) {
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
		for (ZxzzMbWtEnum ele : values()) {
			if (ele.getCode().length()==4 && ele.getCode().startsWith(taskCode)) {
				fileType += ele.getCode() + ",";
			}
		}
		return fileType;
	}
	
	/** 模板编码 */
	private String templatecode;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;

	public String getTemplatecode() {
		return templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

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
