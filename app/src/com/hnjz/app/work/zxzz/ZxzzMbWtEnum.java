package com.hnjz.app.work.zxzz;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

public enum ZxzzMbWtEnum {
	//101������ȾԴ�Զ������ʩ���м�������ö��
	FQPWSSJGYS("101", "10000001", "1�����ۿ�Ӧ����ȾԴ�Զ������ʩ��������һ��"),
	FQPWSSDJBA("101", "10000002", "2�����ۿ�Ӧ����ȾԴ�Զ������ʩ�ǼǱ���һ��"),
	FQPWZDJCSH("101", "10000003", "3�����ۿ�Ӧ�����һ����ȾԴ�Զ����������Ч�����һ��"),
	FSJLCYD("101", "10000004", "1������λ��λ����������ˮ����·�����룬�Ҳ����ڲ�ˮ��ǰ�����������ķ���"),
	FSHLCFCS("101", "10000005", "2������������ˮʱ���ں������ֻ�ϵĳ�����ˮ"),
	FQCYXZQY("101", "10000006", "1��������λӦѡ���ڴ�ֱ�ܶκ��̵���ѹ����"),
	FQCYDCD("101", "10000007", "2��������λӦ�ܿ��̵���ͷ�Ͷ��漱��仯�Ĳ�λ��������ѡ���������ȶ��Ķ��棬�Ҳ�����λǰֱ�ܶεĳ���Ӧ���ں�ֱ�ܶεĳ���"),
	FQCYDZPQG("101", "10000008", "3����һ���̶���ȾԴ������ͨ������̵������ù̶���ȾԴ����������ʱ��������λӦ�����ڸù̶���ȾԴ������������"),
	JCZFYQZC("101", "10000009", "���վ����Ӧ�пյ�������ϵ�Դ������豸������ˮ��ʩ��������������������豸����������Ҫ��"),
	SZXZGBJC("101", "10000010", "��ȾԴ�Զ������ʩ�Ƿ�δ������������׼��������ú͹ر�ͣ��"),
	BGQKJGYS("101", "10000011", "1����ȾԴ�Զ������ʩ���丨���豸���͡��ͺš�λ�á�������Ӧ����ȾԴ�Զ������ʩ��������һ��"),
	BGQKDJBA("101", "10000012", "2����ȾԴ�Զ������ʩ���丨���豸���͡��ͺš�λ�á�������Ӧ����ȾԴ�Զ������ʩ�ǼǱ���һ��"),
	BGQKJCSH("101", "10000013", "3����ȾԴ�Զ������ʩ���丨���豸���͡��ͺš�λ�á�������Ӧ�����һ����ȾԴ�Զ����������Ч�����һ��"),
	BGQKCYYS("101", "10000014", "4����ȾԴ�Զ������ʩ������λ��Ӧ������һ�� "),
	BGQKCYDJ("101", "10000015", "5����ȾԴ�Զ������ʩ������λ��Ӧ��ǼǱ���һ��"),
	BGQKCYSH("101", "10000016", "6����ȾԴ�Զ������ʩ������λ��Ӧ�����һ����Ч�����һ��"),
	YXJCGZZK("101", "10000017", "1���Զ������ʩ����ɲ��ִ������״̬��������ת"),
	YXJCSJZZ("101", "10000018", "2�����������������ĺ���Σ�շ���ķ�Һ��ר���ռ�װ��"),
	YXJCSJCS("101", "10000019", "1����ȾԴ�Զ������ʩӦ��Ҫ��������������������"),
	YXJCSJFX("101", "10000020", "2�������������ݡ����������ݡ������������Ӧһ��"),
	YXJCLSSJ("101", "10000021", "3����ʷ����������Ӧ����һ������"),
	YXJCFSJC("101", "10000022", "1����ˮ�Զ������ʩ����ά����������HJ/T 355���йع涨"),
	YXJCFQJC("101", "10000023", "2�������Զ������ʩ����ά����������HJ/T 75���йع涨"),
	YXJCSSJC("101", "10000024", "3���Զ������ʩ����ά����¼Ӧ����ͣ�˼�¼�����ϼ��䴦�����Ĳĸ��������"),
	YXJCSJYXJC("101", "10000025", "1���Զ��������Ӧͨ����Ч�����"),
	YXJCSJZGCS("101", "10000026", "2����δͨ��������Ч����ˣ�Ӧ��ʵ���Ĵ�ʩ"),
	YXJCYXCSJG("101", "10000027", "1���Զ������ʩ���в�������ȾԴ�Զ������ʩ��������һ��"),
	YXJCYXCSBA("101", "10000028", "2���Զ������ʩ���в�������ȾԴ�Զ������ʩ�ǼǱ���һ��"),
	YXJCYXCSSH("101", "10000029", "3���Զ������ʩ���в��������һ��������Ч�����һ��"),
	ZZJCDWFHGD("101", "10000030", "1����ữ���е�λ�Ƿ���ϡ�������Ⱦ������ʩ��Ӫ�������ɹ����취����ع涨"),
	ZZJCDWJYZZ("101", "10000031", "2����ữ���е�λ�Ƿ������ȾԴ�Զ������ʩ��Ӫ����"),
	ZZJCDWJXHD("101", "10000032", "3����ữ��Ӫ��λ�Ƿ�����Ч���ڲ������ʹ涨�������л"),
	ZZJCYWRYSG("101", "10000033", "������ȾԴ�Զ����ϵͳ����ά�����������ԱӦͨ����ѵ��ȡ����Ӧ�ϸ�֤�飬��֤�ϸ�"),
	ZZJCYQSBBG("101", "10000034", "1���߱���������������������������ල������ĳ��ߵĲ�Ʒ�����Լ��ϸ񱨸�"),
	ZZJCYQSBZS("101", "10000035", "2������������Ʒ��֤֤��"),
	ZZJCYQSBFH("101", "10000036", "3�������豸�����ơ��ͺ��Ƿ�����������֤�������"),
	ZDJCSJXGJC("101", "10000037", "��ҵ�������ɼ���������Ⱦ������ʩ����״�����Զ������ʩ��ʾ���ݱ仯������ԣ��ر�����仯����Ӧ�����߼�"),
	SJYCJCCWSJ("101", "10000038", "1�������������������Զ�������� "),
	SJYCJCCXBD("101", "10000039", "2���Զ�������ݳ�������������������������²�����"),
	SJYCJCCQBD("101", "10000040", "3���Զ�������ݱ仯���ȳ�����ĳһ�̶�ֵ����С��������"),
	SJYCJCYNBD("101", "10000041", "4���Զ�������ݱ仯���ȳ���������2%���ڲ�����"),
	SJYCJCWCCG("101", "10000042", "5���ල�Լ����ֵ��ͬʱ���Զ������ֵ������HJ/T 354��HJ/T 75�涨�ıȶԼ��ָ�귶Χ "),
	SJYCJCFXXY("101", "10000043", "6��������������������������ƫ��ӦС��1%"),
	SJYCJCSCXY("101", "10000044", "7������������������������ƫ��ӦС��1%"),
	SJYCJCGDBD("101", "10000045", "8���Զ�������ݱ仯�������������ɳ�����ĳһ�̶�ֵ���²���"),
	YQCSSZJCGD("101", "10000046", "1�������������ù���"),
	YQCSSZJCBA("101", "10000047", "2��ʵ�ʼ�����������仯����������δ��Ӧ������仯����δ���еǼǱ���"),
	YQCSSZJCGS("101", "10000048", "3���Զ�������ݻ��㹫ʽ���йع��Ҽ����涨��һ��"),
	YQCSSZJCDJ("101", "10000049", "4����׼���߷����ı�δ���еǼǱ���"),
	SSZTJCTYXZ("101", "10000050", "1����������ͣ�˻�����"),
	SSZTGZDJBA("101", "10000051", "2���������������仯δ���еǼǱ���"),
	SSZTYJRJBA("101", "10000052", "3���Զ������ʩӲ�������������仯δ���еǼǱ���"),
	
	//102������ȾԴ�Զ������ʩ������Ԫ�ص��������ö��ֵ
	FSCYYQJC01("102", "10000053", "1���������������������������ͨ"),
	FSCYYQJC02("102", "10000054", "2������ϴ��·�Ƿ���ڶԲɼ�ˮ����ϡ������"),
	FSCYYQJC03("102", "10000055", "3��ˮ��Ԥ����װ���������ļ����ǼǱ��������һ����Ч�����һ�£��޹��ȴ�������"),
	FQCYJCDY01("102", "10000056", "1�����Ȳ���̽ͷ�ڲ�����о�����ۺͶ�������������������¶ȷ�������˵����Ҫ��"),
	FQCYJCDY02("102", "10000057", "2���������ȹܵĳ���ͨ����76m���ڣ���������������б�ȴ���5�㣬��·�޵Ͱ���͹�𣬰��ȹ��¶�ͨ������120�棨ֱ�ӳ�ȡ����"),
	FQCYJCDY03("102", "10000058", "3������ϵͳ����������������ѹ������������"),
	FQCYJCDY04("102", "10000059", "4��ϡ�ͱȺ㶨,����ֵ��ǼǱ���һ�£�ϡ�ͳ�ȡ����"),
	FQCYJCDY05("102", "10000060", "5����ˮ��������������"),
	FSCYBZCYX01("102", "10000061", "1�����ڸ�ˮ����ˮ��·���������·"),
	FSCYBZCYX02("102", "10000062", "2������ϴ��·���ڶԲɼ�ˮ����ϡ������"),
	FSCYBZCYX03("102", "10000063", "3��ˮ��Ԥ����װ���������ļ����ǼǱ��������һ����Ч����˲�һ�£����ڹ��ȴ�������"),
	FQCYBZCYX01("102", "10000064", "1�����Ȳ���̽ͷ�ڲ�����о���ۺͶ���"),
	FQCYBZCYX02("102", "10000065", "2������̽ͷ�����������¶Ȳ���������˵����Ҫ��"),
	FQCYBZCYX03("102", "10000066", "3��Ŀ����ȵ��ܴ���ƽֱ�Ĺܶλ�����U�͹ܶ�"),
	FQCYBZCYX04("102", "10000067", "4�����ߴ���Ŧ�ᡢ���ƻ���ѵ�����"),
	FQCYBZCYX05("102", "10000068", "5�����ȹ��¶ȹ���"),
	FQCYBZCYX06("102", "10000069", "6���������ڡ�ʱ�䡢��ѹ����ͷѹ������������˵����Ҫ��"),
	FQCYBZCYX07("102", "10000070", "7��ϡ������������Ʒ���������ȶ�"),
	FQCYBZCYX08("102", "10000071", "8��ϡ�ͱȡ�������ǼǱ�����һ��"),
	FQCYBZCYX09("102", "10000072", "9����ˮ�������������¶���ǼǱ�����һ��"),
	FQCYBZCYX10("102", "10000073", "10����������о��ɫ"),
	FQCYBZCYX11("102", "10000074", "11��������������ˮ�ų�"),
	
	//103�̶���ȾԴ�����Զ������ʩCEMS�ص��������ö��ֵ
	SONOFXDY01("103", "10000075", "1��������������ɾ�"),
	SONOFXDY02("103", "10000076", "2�����ⷨ����ѧ���ⷨ��NO2ת�����������������¶���ǼǱ���һ��"),
	SONOFXDY03("103", "10000077", "3�������ڲ���·���ӽ��̣��ܱ��޻��Ҽ�����ˮ"),
	KLWFXDY01("103", "10000078", "1����ɨϵͳ�����������"),
	KLWFXDY02("103", "10000079", "2�������������ѧ̽ͷ�Ĳ����Ӵ���࣬������·׼ֱ"),
	KLWFXDY03("103", "10000080", "3����ɨϵͳ�Ĺܵ���������"),
	KLWFXDY04("103", "10000081", "4�� ��ɨ����ľ�������о���"),
	YQCSFXDY01("103", "10000082", "1��Ƥ�й��ޱ��Σ�������������ֱ�����̷������ɶ�"),
	YQCSFXDY02("103", "10000083", "2�� �����¶ȼƱ����޻���"),
	YQCSFXDY03("103", "10000084", "3�� ��������ϵ����Ƥ�й�ϵ��K ֵ���̵���������ٶȳ�ϵ����ǼǱ���һ��"),
	YQCSFXDY04("103", "10000085", "4�������ŷ�������̬��Ⱦ��Ũ�ȵȻ������HJ/T 397���й�Ҫ��"),
	GDFQJYBZ01("103", "10000086", "�̶���ȾԴ����CEMS���й�����Ӧ������HJ/T 75�����Ҫ�󣬿�չ����У׼�Ͷ��ڼ���"),
	SONOBZCYX01("103", "10000087", "1����������������ࡢ����"),
	SONOBZCYX02("103", "10000088", "2�������ڲ���·�����ɶ����ܱڴ��ڻ��Ҽ�����ˮ"),
	KLWBZCYX01("103", "10000089", "1����ɨϵͳ��������쳣��������"),
	KLWBZCYX02("103", "10000090", "2�������������ѧ̽ͷ�Ĳ����Ӵ����������������·ƫ��"),
	KLWBZCYX03("103", "10000091", "3����ɨϵͳ�Ĺܵ����ѷ죬�����ɶ�"),
	KLWBZCYX04("103", "10000092", "4����ɨ����ľ�������о����"),
	YQCSBZCYX01("103", "10000093", "1��Ƥ�йܱ��Ρ����������̵���������ƫ�룬����ֱ"),
	YQCSBZCYX02("103", "10000094", "2�������¶ȼƱ����и�ʴ������л���"),
	YQCSBZCYX03("103", "10000095", "3����������ϵ����Ƥ�й�ϵ��K ֵ���̵���������ٶȳ�ϵ����ǼǱ�����һ��"),
	YQCSBZCYX04("103", "10000096", "4�������ŷ�������̬��Ⱦ��Ũ�ȵȻ��㲻���ϵ����Ҫ��"),
	GDFQBZCYX01("103", "10000097", "1�����Ϳ��У׼Ƶ�κ�У��Ƶ�δﲻ��HJ/T 75��Ҫ��"),
	GDFQBZCYX02("103", "10000098", "2���ֳ�ͨ�������ͱ�׼������ԣ����Ư�ƺͿ��Ư�Ʒ���HJ/T 75�涨��ʧ��ָ�� "),
	GDFQBZCYX03("103", "10000099", "3���ֳ�ͨ���׼������ԣ�׼ȷ�Ȳ�����HJ/T 75�涨�Ĳαȷ������ռ���ָ��Ҫ��"),
	
	//104���ݲɼ����������ص��������ö��ֵ
	YQCSJCJG01("104", "10000100", "�Զ�������������ݲɼ��������������ݲɼ������������̵ȣ�����Ӧһ�£����������ļ����ǼǱ�������һ����Ч�����һ��"),
	XLLJJCJG01("104", "10000101", "�Զ�������������ݲɼ������������������·��������"),
	SJCSJCJG01("104", "10000102", "��λ�������ݲɼ���Ԫ�ɼ���ʵʱ��ֵӦһ��"),
	YQCSBZCQX01("104", "10000103", "1�����������������ļ����ǼǱ�������һ����Ч����˲�һ�� "),
	YQCSBZCQX02("104", "10000104", "2�����ݲɼ������������ù��ͻ�������ù���"),
	XLLJBZCYX01("104", "10000105", "1�����ݲɼ����������Զ�����������װ�в��������ݴ����豸����ɱ�̿����������źŴ����豸�����˲��������Ƶ���������Χ���豸��"),
	XLLJBZCYX02("104", "10000106", "2�����ݲɼ�����������ͨ���豸�����ƽ���������߷�����������ͨѶ�豸��֮���������������豸��"),
	XLLJBZCYX03("104", "10000107", "3���Զ������ʩֹͣ���������ݲɼ��������Բ������Զ�������ʵ���������������ݡ�"),
	SJCSBZCYX01("104", "10000108", "��װ�����������ݴ�С�͵�������"),
	
	//105�̶���ȾԴ�����������ϵͳ�ֳ��˲��������100�֣�����ö��
	JCYFYQAZ01("105", "10000109", "�������6m2"),
	JCYFYQAZ02("105", "10000110", "�䱸ר������Լ��յ�"),
	JCYFYQAZ03("105", "10000111", "���з���ϵͳ"),
	JCYFYQAZ04("105", "10000112", "������CEMS:�����ܰ�װ�����ٲ�����5m/s��λ������ѡ���ڴ�ֱ�ܶΣ��ܿ��̵���ͷ�Ͷ��漱��仯�Ĳ�λ�������ھ���ͷ�����š�������η���С��4��ֱ�����;������������η���С��2��ֱ����"),
	JCYFYQAZ05("105", "10000113", "��̬��Ⱦ��CEMS��λ����̬��Ⱦ���Ͼ��ȵ�λ�ã��ô��ܴ����̶���ȾԴ���ŷţ�ͬʱ�����ճ�ά��"),
	JCYFYQAZ06("105", "10000114", "������������ϵͳ����װλ��λ�ڿ��������̬��Ⱦ��CMES����>300mm����������Ӱ����������̬��Ⱦ��CMES�Ĳⶨ����������������ϵͳ�������ܰ�װ�����ٴ���5m/s��λ��"),
	JCYFYQAZ07("105", "10000115", "̽ͷ�İ�װ�淶�������������𣬹�������������б5��"),
	JCYFYQAZ08("105", "10000116", "�ڼ�������0.5m����Ԥ���ֹ�������"),
	JCYFYQAZ09("105", "10000117", "����ƽ̨�������5m2 ,ƽ̨���˸߶ȴ���1.2m"),
	JCYFYQAZ10("105", "10000118", "����б����"),
	CEMSWZXJC01("105", "10000119", "�п����SO2��NOX��Ⱦ����ϵͳ�粻��װ�����������λ������ű���"),
	CEMSWZXJC02("105", "10000120", "�����١��¶ȡ����������̵�ѹ����ʪ�ȼ��ϵͳ"),
	CEMSWZXJC03("105", "10000123", "�в������ȹܡ����������̳����˲���"),
	CEMSWZXJC04("105", "10000124", "����̽ͷ��Ƥ�йܲ�ѹ�ס��������ߡ��̳�������з�������ϴ����"),
	SBYXWHDF01("105", "10000125", "�������ĵƹ⡢ָʾ��״̬����"),
	SBYXWHDF02("105", "10000126", "��������裬��Ϊ3���ŷű�׼ֵ���粻���裬Ӧ�ܲ鿴"),
	SBYXWHDF03("105", "10000127", "�������ȹܿ��´���120��"),
	SBYXWHDF04("105", "10000128", "�������¶ȵ���5��"),
	SBYXWHDF05("105", "10000129", "������������ϴ�����������м�¼"),
	SBYXWHDF06("105", "10000130", "�������޸�ʴ����������������©��"),
	SBYXWHDF07("105", "10000131", "��׼����Ϊ�����ල������Ȩ��λ�������б�׼����֤���飬����Ч������Ũ����������ƥ��"),
	SBYXWHDF08("105", "10000132", "�ٶȳ�ϵ�� �����ݱȶԼ�¼�������Զ��������/�˹��������"),
	SBYXWHDF09("105", "10000133", "Ƥ�й�ϵ��K : 0.81��0.86"),
	SBYXWHDF10("105", "10000134", "��������ϵ������ȼú��¯65t/h����Ϊ1.8������Ϊ1.4����糧1997��ǰͶ��Ϊ1.7��֮��Ϊ1.4��ȼ��ȼ����¯Ϊ1.2"),
	SBYXWHDF11("105", "10000135", "SO2��NOX�����ֳ��ⶨ����Ҫ��"),
	SJCJXTZQ01("105", "10000136", "�ֳ��˲�ʱ��������"),
	SJCJXTZQ02("105", "10000137", "��������ʾ����������"),
	SJCJXTZQ03("105", "10000138", "�������ϵ��������Ӧ���ӵ�����������ͬ"),
	SJCJXTZQ04("105", "10000139", "�����SO2��NOX������һ���Ǳ������ɵ�����������2%������������5%"),
	SJCJXTZQ05("105", "10000140", "�������У׼��¼У��������ͬʱ��SO2��NOX����"),
	YXGLGF01("105", "10000141", "������վ�������ƶȡ���Ա��λְ��"),
	YXGLGF02("105", "10000142", "��ҵ��Ա�л������䷢����Ч�����֤��"),
	YXGLGF03("105", "10000143", "�����豸ʹ��˵���顢�ϸ�֤��������Ʒ��֤���������"),
	YXGLGF04("105", "10000144", "ά����¼�����ɲᣬ������������ƽ��ÿ��1��"),
	YXGLGF05("105", "10000145", "ά�޼�¼�����ɲᣬ���������1����Ӧ���޸�ʱ��С��3��"),
	YXGLGF06("105", "10000146", "У׼��¼�����ɲᣬ�������������ʿؼ�¼ÿ������1�Σ�ʵ���ȶԼ�¼ÿ������1��"),
	
	//201��ˮ��UV����ȾԴ�Զ������ʩ���м�������ö��
	UVFSPWSSJGYS("201", "10000147", "1�����ۿ�Ӧ����ȾԴ�Զ������ʩ��������һ��"),
	UVFSPWSSDJBA("201", "10000148", "2�����ۿ�Ӧ����ȾԴ�Զ������ʩ�ǼǱ���һ��"),
	UVFSPWZDJCSH("201", "10000149", "3�����ۿ�Ӧ�����һ����ȾԴ�Զ����������Ч�����һ��"),
	UVFSJLCYD("201", "10000150", "1������λ��λ����������ˮ����·�����룬�Ҳ����ڲ�ˮ��ǰ�����������ķ���"),
	UVFSHLCFCS("201", "10000151", "2������������ˮʱ���ں������ֻ�ϵĳ�����ˮ"),
	UVFSCYXZQY("201", "10000152", "1��������λӦѡ���ڴ�ֱ�ܶκ��̵���ѹ����"),
	UVFSCYDCD("201", "10000153", "2��������λӦ�ܿ��̵���ͷ�Ͷ��漱��仯�Ĳ�λ��������ѡ���������ȶ��Ķ��棬�Ҳ�����λǰֱ�ܶεĳ���Ӧ���ں�ֱ�ܶεĳ���"),
	UVFSCYDZPQG("201", "10000154", "3����һ���̶���ȾԴ������ͨ������̵������ù̶���ȾԴ����������ʱ��������λӦ�����ڸù̶���ȾԴ������������"),
	UVFSJCZFYQZC("201", "10000155", "���վ����Ӧ�пյ�������ϵ�Դ������豸������ˮ��ʩ��������������������豸����������Ҫ��"),
	UVFSSZXZGBJC("201", "10000156", "��ȾԴ�Զ������ʩ�Ƿ�δ������������׼��������ú͹ر�ͣ��"),
	UVFSBGQKJGYS("201", "10000157", "1����ȾԴ�Զ������ʩ���丨���豸���͡��ͺš�λ�á�������Ӧ����ȾԴ�Զ������ʩ��������һ��"),
	UVFSBGQKDJBA("201", "10000158", "2����ȾԴ�Զ������ʩ���丨���豸���͡��ͺš�λ�á�������Ӧ����ȾԴ�Զ������ʩ�ǼǱ���һ��"),
	UVFSBGQKJCSH("201", "10000159", "3����ȾԴ�Զ������ʩ���丨���豸���͡��ͺš�λ�á�������Ӧ�����һ����ȾԴ�Զ����������Ч�����һ��"),
	UVFSBGQKCYYS("201", "10000160", "4����ȾԴ�Զ������ʩ������λ��Ӧ������һ�� "),
	UVFSBGQKCYDJ("201", "10000161", "5����ȾԴ�Զ������ʩ������λ��Ӧ��ǼǱ���һ��"),
	UVFSBGQKCYSH("201", "10000162", "6����ȾԴ�Զ������ʩ������λ��Ӧ�����һ����Ч�����һ��"),
	UVFSYXJCGZZK("201", "10000163", "1���Զ������ʩ����ɲ��ִ������״̬��������ת"),
	UVFSYXJCSJZZ("201", "10000164", "2�����������������ĺ���Σ�շ���ķ�Һ��ר���ռ�װ��"),
	UVFSYXJCSJCS("201", "10000165", "1����ȾԴ�Զ������ʩӦ��Ҫ��������������������"),
	UVFSYXJCSJFX("201", "10000167", "2�������������ݡ����������ݡ������������Ӧһ��"),
	UVFSYXJCLSSJ("201", "10000168", "3����ʷ����������Ӧ����һ������"),
	UVFSYXJCFSJC("201", "10000169", "1����ˮ�Զ������ʩ����ά����������HJ/T 355���йع涨"),
	UVFSYXJCFQJC("201", "10000170", "2�������Զ������ʩ����ά����������HJ/T 75���йع涨"),
	UVFSYXJCSSJC("201", "10000171", "3���Զ������ʩ����ά����¼Ӧ����ͣ�˼�¼�����ϼ��䴦�����Ĳĸ��������"),
	UVFSYXJCSJYXJC("201", "10000172", "1���Զ��������Ӧͨ����Ч�����"),
	UVFSYXJCSJZGCS("201", "10000173", "2����δͨ��������Ч����ˣ�Ӧ��ʵ���Ĵ�ʩ"),
	UVFSYXJCYXCSJG("201", "10000174", "1���Զ������ʩ���в�������ȾԴ�Զ������ʩ��������һ��"),
	UVFSYXJCYXCSBA("201", "10000175", "2���Զ������ʩ���в�������ȾԴ�Զ������ʩ�ǼǱ���һ��"),
	UVFSYXJCYXCSSH("201", "10000176", "3���Զ������ʩ���в��������һ��������Ч�����һ��"),
	UVFSZZJCDWFHGD("201", "10000177", "1����ữ���е�λ�Ƿ���ϡ�������Ⱦ������ʩ��Ӫ�������ɹ����취����ع涨"),
	UVFSZZJCDWJYZZ("201", "10000178", "2����ữ���е�λ�Ƿ������ȾԴ�Զ������ʩ��Ӫ����"),
	UVFSZZJCDWJXHD("201", "10000179", "3����ữ��Ӫ��λ�Ƿ�����Ч���ڲ������ʹ涨�������л"),
	UVFSZZJCYWRYSG("201", "10000180", "������ȾԴ�Զ����ϵͳ����ά�����������ԱӦͨ����ѵ��ȡ����Ӧ�ϸ�֤�飬��֤�ϸ�"),
	UVFSZZJCYQSBBG("201", "10000181", "1���߱���������������������������ල������ĳ��ߵĲ�Ʒ�����Լ��ϸ񱨸�"),
	UVFSZZJCYQSBZS("201", "10000182", "2������������Ʒ��֤֤��"),
	UVFSZZJCYQSBFH("201", "10000183", "3�������豸�����ơ��ͺ��Ƿ�����������֤�������"),
	UVFSZDJCSJXGJC("201", "10000184", "��ҵ�������ɼ���������Ⱦ������ʩ����״�����Զ������ʩ��ʾ���ݱ仯������ԣ��ر�����仯����Ӧ�����߼�"),
	UVFSSJYCJCCWSJ("201", "10000185", "1�������������������Զ�������� "),
	UVFSSJYCJCCXBD("201", "10000186", "2���Զ�������ݳ�������������������������²�����"),
	UVFSSJYCJCCQBD("201", "10000187", "3���Զ�������ݱ仯���ȳ�����ĳһ�̶�ֵ����С��������"),
	UVFSSJYCJCYNBD("201", "10000188", "4���Զ�������ݱ仯���ȳ���������2%���ڲ�����"),
	UVFSSJYCJCWCCG("201", "10000189", "5���ල�Լ����ֵ��ͬʱ���Զ������ֵ������HJ/T 354��HJ/T 75�涨�ıȶԼ��ָ�귶Χ "),
	UVFSSJYCJCFXXY("201", "10000190", "6��������������������������ƫ��ӦС��1%"),
	UVFSSJYCJCSCXY("201", "10000191", "7������������������������ƫ��ӦС��1%"),
	UVFSSJYCJCGDBD("201", "10000192", "8���Զ�������ݱ仯�������������ɳ�����ĳһ�̶�ֵ���²���"),
	UVFSYQCSSZJCGD("201", "10000193", "1�������������ù���"),
	UVFSYQCSSZJCBA("201", "10000194", "2��ʵ�ʼ�����������仯����������δ��Ӧ������仯����δ���еǼǱ���"),
	UVFSYQCSSZJCGS("201", "10000195", "3���Զ�������ݻ��㹫ʽ���йع��Ҽ����涨��һ��"),
	UVFSYQCSSZJCDJ("201", "10000196", "4����׼���߷����ı�δ���еǼǱ���"),
	UVFSSSZTJCTYXZ("201", "10000197", "1����������ͣ�˻�����"),
	UVFSSSZTGZDJBA("201", "10000198", "2���������������仯δ���еǼǱ���"),
	UVFSSSZTYJRJBA("201", "10000199", "3���Զ������ʩӲ�������������仯δ���еǼǱ���"),
	
	//202��ˮ(UV)��ȾԴ�Զ������ʩ������Ԫ�ص��������ö��ֵ
	UVFSCYYQJC01("202", "10000200", "1���������������������������ͨ"),
	UVFSCYYQJC02("202", "10000201", "2������ϴ��·�Ƿ���ڶԲɼ�ˮ����ϡ������"),
	UVFSCYYQJC03("202", "10000202", "3��ˮ��Ԥ����װ���������ļ����ǼǱ��������һ����Ч�����һ�£��޹��ȴ�������"),
	UVFQCYJCDY01("202", "10000203", "1�����Ȳ���̽ͷ�ڲ�����о�����ۺͶ�������������������¶ȷ�������˵����Ҫ��"),
	UVFQCYJCDY02("202", "10000204", "2���������ȹܵĳ���ͨ����76m���ڣ���������������б�ȴ���5�㣬��·�޵Ͱ���͹�𣬰��ȹ��¶�ͨ������120�棨ֱ�ӳ�ȡ����"),
	UVFQCYJCDY03("202", "10000205", "3������ϵͳ����������������ѹ������������"),
	UVFQCYJCDY04("202", "10000206", "4��ϡ�ͱȺ㶨,����ֵ��ǼǱ���һ�£�ϡ�ͳ�ȡ����"),
	UVFQCYJCDY05("202", "10000207", "5����ˮ��������������"),
	UVFSCYBZCYX01("202", "10000208", "1�����ڸ�ˮ����ˮ��·���������·"),
	UVFSCYBZCYX02("202", "10000209", "2������ϴ��·���ڶԲɼ�ˮ����ϡ������"),
	UVFSCYBZCYX03("202", "10000210", "3��ˮ��Ԥ����װ���������ļ����ǼǱ��������һ����Ч����˲�һ�£����ڹ��ȴ�������"),
	UVFQCYBZCYX01("202", "10000211", "1�����Ȳ���̽ͷ�ڲ�����о���ۺͶ���"),
	UVFQCYBZCYX02("202", "10000212", "2������̽ͷ�����������¶Ȳ���������˵����Ҫ��"),
	UVFQCYBZCYX03("202", "10000213", "3��Ŀ����ȵ��ܴ���ƽֱ�Ĺܶλ�����U�͹ܶ�"),
	UVFQCYBZCYX04("202", "10000214", "4�����ߴ���Ŧ�ᡢ���ƻ���ѵ�����"),
	UVFQCYBZCYX05("202", "10000215", "5�����ȹ��¶ȹ���"),
	UVFQCYBZCYX06("202", "10000216", "6���������ڡ�ʱ�䡢��ѹ����ͷѹ������������˵����Ҫ��"),
	UVFQCYBZCYX07("202", "10000217", "7��ϡ������������Ʒ���������ȶ�"),
	UVFQCYBZCYX08("202", "10000218", "8��ϡ�ͱȡ�������ǼǱ�����һ��"),
	UVFQCYBZCYX09("202", "10000219", "9����ˮ�������������¶���ǼǱ�����һ��"),
	UVFQCYBZCYX10("202", "10000220", "10����������о��ɫ"),
	UVFQCYBZCYX11("202", "10000221", "11��������������ˮ�ų�"),
	
	//203��ˮ(UV)���л�̼��TOC����ȾԴ�Զ������ʩ�ص��������ö��ֵ
	UVTOCCJDY01("203", "10000222", "1��ȡ����·λ��Ӧ��ȷ����·Ӧ��ͨ"),
	UVTOCCJDY02("203", "10000223", "2����ˮ������ˮ���Ⱦ�������"),
	UVTOCSJDY01("203", "10000224", "1���������Լ�ƿ�ڣ��Լ����ܱ�֤����һ������"),
	UVTOCSJDY02("203", "10000225", "2���������Լ�ƿ���Լ��ڵǼǱ�����ʹ����Ч����"),
	UVTOCSJDY03("203", "10000226", "3��ʵ��ʹ�õ��Լ������ࡢŨ����ǼǱ������"),
	UVTOCFXDY01("203", "10000227", "1���������ÿ�������������������99.99%���ϣ����������ڵǼǱ����ķ�Χ�ڣ����ÿ���Ϊ����������ȥ��������̼�Ŀ�������װ�ã����õ���Ϊ�������ڹ�������������Ӧ��֮�����������������װ��"),
	UVTOCFXDY02("203", "10000228", "2����ʽ������Ӧ��ȼ�չ��¶���680��1000�棬���ڵǼǱ����ķ�Χ��"),
	UVTOCFXDY03("203", "10000229", "3����Һ������Ӧ��������״̬"),
	UVTOCCZDY01("203", "10000230", "1�������������ܹ�������ת�������Լ���ˮ��"),
	UVTOCCZDY02("203", "10000231", "2����������������ת�����ų���Һ"),
	UVTOCJYBZ01("203", "10000232", "1��ˮ��ȾԴ�Զ������Ƶ��ÿ48Сʱ�Զ�������������У׼��ÿ�����ٽ���һ��ʵ��ˮ���ȶ�������ʿ�������"),
	CJDYBZCYX01("203", "10000233", "1������������ȡ������ˮ�������·"),
	CJDYBZCYX02("203", "10000234", "2��ȡ����·������·"),
	CJDYBZCYX03("203", "10000235", "3��ȡ����·�𻵣���ȡ���ظɺԣ���ˮ��Ъ���ŷų��⣩����ʴ"),
	SJDYBZCYX01("203", "10000236", "1���Լ�ƿ�����Լ����Լ���δ�����Լ�Һλ��"),
	SJDYBZCYX02("203", "10000237", "2���Լ�����ʹ������"),
	SJDYBZCYX03("203", "10000238", "3��ʵ��ʹ�õ��Լ������ࡢŨ����ǼǱ��������"),
	FXDYBZCYX01("203", "10000239", "1��������������ʾ���������ǼǱ����ķ�Χ"),
	FXDYBZCYX02("203", "10000240", "2����������������Ӧ��֮������������װ�ã�����������װ����Ч"),
	FXDYBZCYX03("203", "10000241", "3���������ȵ���99.99%"),
	FXDYBZCYX04("203", "10000242", "4�����ÿ���Ϊ����ʱ��ȱ��ȥ��������̼�Ŀ�������װ�û�ʧЧ"),
	FXDYBZCYX05("203", "10000243", "5����ʽ������Ӧ��ȼ�չ��¶ȹ��ͳ����ǼǱ�����Χ��"),
	FXDYBZCYX06("203", "10000244", "6��ȼ�����ڴ߻������ס����������뱸����һ�¡�"),
	FXDYBZCYX07("203", "10000245", "7����Һ�����������������¶ȸ���¶���¶ȣ��򳬹��ǼǱ�����Χ"),
	FXDYBZCYX08("203", "10000246", "8����������ˮƿ����ˮ"),
	CZDYBZCYX01("203", "10000247", "1����������������ת��"),
	CZDYBZCYX02("203", "10000248", "2�������ڲ�������·���ɶ������������ӹ�·����Һ����©����"),
	CZDYBZCYX03("203", "10000249", "3�������������ڲ���Ʒ��·���Լ���·����Һ����������"),
	CZDYBZCYX04("203", "10000250", "4��������ʾ���ϻ򱨾��ź�"),
	JYBZBZCYX01("203", "10000251", "1��ˮ��ȾԴ�Զ��������㡢����У׼�ͱȶԵ�Ƶ�β�����HJ/T 355�����Ҫ��"),
	JYBZBZCYX02("203", "10000252", "2���ֳ��������У׼Һ������У׼Һ���飬��������Ư�Ʋ�����HJ/T 355�����Ҫ��"),
	JYBZBZCYX03("203", "10000253", "3���ֳ������ʿ������飬�ʿ����ⶨ����������ڱ�׼ֵ�ġ�10%"),
	
	//204��ˮ ���⣨UV������ˮ���Զ�������ص��������ö��ֵ
	UVUVCJDY01("204", "10000254", "1��ȡ����·λ����ȷ����·��ͨ"),
	UVUVCJDY02("204", "10000255", "2����ˮ������ˮ����������"),
	UVUVCZDY01("204", "10000256", "1�������������ܹ�������ת�������Լ���ˮ��"),
	UVUVCZDY02("204", "10000257", "2����������������ת�����ų���Һ"),
	UVUVCZDY03("204", "10000258", "3������������ϵ���뻯ѧ����������ԵȲ����������Ӧ��ǼǱ���һ��"),
	UVUVCZDY04("204", "10000259", "4�����ճؾ����Զ���ϴ���ܣ����Զ�������������ճر������ڵ���·������"),
	UVUVJYBZ01("204", "10000260", "1��ˮ��ȾԴ�Զ������Ƶ��ÿ48Сʱ�����Զ�������������У׼��ÿ�����ٽ���һ��ʵ��ˮ���ȶ�������ʿ�������"),
	UVCJDYBZCYX01("204", "10000261", "1������������ȡ������ˮ�������·"),
	UVCJDYBZCYX02("204", "10000262", "2��ȡ����·������·"),
	UVCJDYBZCYX03("204", "10000263", "3��ȡ����·�𻵣���ȡ���ظɺԣ���ˮ��Ъ���ŷų��⣩����ʴ"),
	UVCZDYBZCYX01("204", "10000264", "1����������������ת��"),
	UVCZDYBZCYX02("204", "10000265", "2�������ڲ�������·���ɶ������������ӹ�·����Һ����©����"),
	UVCZDYBZCYX03("204", "10000266", "3�������������ڲ���Ʒ��·���Լ���·����Һ����������"),
	UVCZDYBZCYX04("204", "10000267", "4������������ϵ���뻯ѧ����������ԵȲ������������ǼǱ�����һ��"),
	UVCZDYBZCYX05("204", "10000268", "5�����ճز��߱�����ϴ����"),
	UVCZDYBZCYX06("204", "10000269", "6�����ճر��������ڵ���·������"),
	UVCZDYBZCYX07("204", "10000270", "7��������ʾ���ϻ򱨾��ź�"),
	UVJYBZBZCYX01("204", "10000271", "1��ˮ��ȾԴ�Զ��������㡢����У׼�ͱȶԵ�Ƶ�β�����HJ/T 355�����Ҫ��"),
	UVJYBZBZCYX02("204", "10000272", "2���ֳ��������У׼Һ������У׼Һ���飬��������Ư�Ʋ�����HJ/T 355�����Ҫ��"),
	UVJYBZBZCYX03("204", "10000273", "3���ֳ������ʿ������飬�ʿ����ⶨ����������ڱ�׼ֵ�ġ�10%"),
	
	//205��ˮ ������ȾԴ�Զ������ʩ�ص��������ö��ֵ
	UVADCJDY01("205", "10000274", "1��ȡ����·λ����ȷ����·��ͨ"),
	UVADCJDY02("205", "10000275", "2����ˮ������ˮ����������"),
	UVADSJDY01("205", "10000276", "1���������Լ�ƿ�ڣ��Լ����ܱ�֤����һ������"),
	UVADSJDY02("205", "10000277", "2���������Լ�ƿ���Լ��ڵǼǱ�����ʹ����Ч����"),
	UVADSJDY03("205", "10000278", "3��ʵ��ʹ�õ��Լ������ࡢŨ����ǼǱ������"),
	UVADCZDY01("205", "10000279", "1�������������ܹ�������ת�������Լ���ˮ��"),
	UVADCZDY02("205", "10000280", "2����������������ת�����ų���Һ"),
	UVADCLDY01("205", "10000281", "1�����÷ֹ��ȷ��ⶨ����ɫ�ر������ڵ���·������"),
	UVADCLDY02("205", "10000282", "2�����õ缫���ⶨ���缫����������"),
	UVADCLDY03("205", "10000283", "3�����Զ���ϴ�缫���ɫϵͳ"),
	UVADJYBZ01("205", "10000284", "1��ˮ��ȾԴ�Զ������Ƶ��ÿ48Сʱ�����Զ�������������У׼��ÿ�����ٽ���һ��ʵ��ˮ���ȶ�������ʿ�������"),
	ADCJDYBZCYX01("205", "10000285", "1������������ȡ������ˮ�������·"),
	ADCJDYBZCYX02("205", "10000286", "2��ȡ����·������·"),
	ADCJDYBZCYX03("205", "10000287", "3��ȡ����·�𻵣���ȡ���ظɺԣ���ˮ��Ъ���ŷų��⣩����ʴ"),
	ADSJDYBZCYX01("205", "10000288", "1���Լ�ƿ�����Լ����Լ���δ�����Լ�Һλ��"),
	ADSJDYBZCYX02("205", "10000289", "2���Լ�����ʹ������"),
	ADSJDYBZCYX03("205", "10000290", "3��ʵ��ʹ�õ��Լ������ࡢŨ����ǼǱ��������"),
	ADCZDYBZCYX01("205", "10000291", "1����������������ת��"),
	ADCZDYBZCYX02("205", "10000292", "2�������ڲ�������·���ɶ������������ӹ�·����Һ����©����"),
	ADCZDYBZCYX03("205", "10000293", "3�������������ڲ��Լ���·����Һ����������"),
	ADCZDYBZCYX04("205", "10000294", "4��������ʾ���ϻ򱨾��ź�"),
	ADCLDYBZCYX01("205", "10000295", "1����ɫ�ر������ڵ���·������"),
	ADCLDYBZCYX02("205", "10000296", "2���缫��������"),
	ADJYBZBZCYX01("205", "10000297", "1��ˮ��ȾԴ�Զ��������㡢����У׼�ͱȶԵ�Ƶ�β�����HJ/T 355�����Ҫ��"),
	ADJYBZBZCYX02("205", "10000298", "2���ֳ��������У׼Һ������У׼Һ���飬��������Ư�Ʋ�����HJ/T 355�����Ҫ��"),
	ADJYBZBZCYX03("205", "10000299", "3���ֳ������ʿ������飬�ʿ����ⶨ����������ڱ�׼ֵ�ġ�10%"),
	
	//206��ˮ��UV�� �ؽ�����ȾԴ�Զ������ʩ�ص��������ö��ֵ
	UVJSCJDY01("206", "10000300", "1��ȡ����·λ����ȷ����·��ͨ"),
	UVJSCJDY02("206", "10000301", "2����ˮ������ˮ����������"),
	UVJSSJDY01("206", "10000302", "1���������Լ�ƿ�ڣ��Լ����ܱ�֤����һ������"),
	UVJSSJDY02("206", "10000303", "2���������Լ�ƿ���Լ��ڵǼǱ�����ʹ����Ч����"),
	UVJSSJDY03("206", "10000304", "3��ʵ��ʹ�õ��Լ������ࡢŨ����ǼǱ������"),
	UVJSXJDY01("206", "10000305", "1�����ⵥԪӦ��ʵ���Լ��Ŀ��ټ���"),
	UVJSXJDY02("206", "10000306", "2���ܱ��ֺ����������"),
	UVJSCZDY01("206", "10000307", "1�������������ܹ�������ת�������Լ���ˮ��"),
	UVJSCZDY02("206", "10000308", "2����������������ת�����ų���Һ"),
	UVJSCLDY01("206", "10000309", "1�����÷ֹ��ȷ��ⶨ����ɫ�ر������ڵ���·������"),
	UVJSCLDY02("206", "10000310", "2�����õ缫���ⶨ���缫����������"),
	UVJSCLDY03("206", "10000311", "3�����Զ���ϴ�缫���ɫϵͳ"),
	UVJSJYBZ01("206", "10000312", "1��ˮ��ȾԴ�Զ������Ƶ��ÿ48Сʱ�����Զ�������������У׼��ÿ�����ٽ���һ��ʵ��ˮ���ȶ�������ʿ�������"),
	JSCJDYBZCYX01("206", "10000313", "1������������ȡ������ˮ�������·"),
	JSCJDYBZCYX02("206", "10000314", "2��ȡ����·������·"),
	JSCJDYBZCYX03("206", "10000315", "3��ȡ����·�𻵣���ȡ���ظɺԣ���ˮ��Ъ���ŷų��⣩����ʴ"),
	JSSJDYBZCYX01("206", "10000316", "1���Լ�ƿ�����Լ�"),
	JSSJDYBZCYX02("206", "10000317", "2���Լ�����ʹ������"),
	JSSJDYBZCYX03("206", "10000318", "3��ʵ��ʹ�õ��Լ������ࡢŨ����ǼǱ��������"),
	JSXJDYBZCYX01("206", "10000319", "1�����������¶ȼ�����ʱ�䳬���ǼǱ����ķ�Χ"),
	JSXJDYBZCYX02("206", "10000320", "2������ƿ�ڷǹ���״̬���ڲ��нᾧ������"),
	JSXJDYBZCYX03("206", "10000321", "3������ƿ�²���©Һ����"),
	JSCZDYBZCYX01("206", "10000322", "1����������������ת��"),
	JSCZDYBZCYX02("206", "10000323", "2�������ڲ�������·���ɶ������������ӹ�·����Һ����©����"),
	JSCZDYBZCYX03("206", "10000324", "3�������������ڲ���Ʒ��·���Լ���·����Һ����������"),
	JSCZDYBZCYX04("206", "10000325", "4��������ʾ���ϻ򱨾��ź�"),
	JSCZDYBZCYX05("206", "10000326", "5������"),
	JSCLDYBZCYX01("206", "10000327", "1����ɫ�ر������ڵ���·������"),
	JSCLDYBZCYX02("206", "10000328", "2���缫��������"),
	JSJYBZBZCYX01("206", "10000329", "1��ˮ��ȾԴ�Զ��������㡢����У׼�ͱȶԵ�Ƶ�β�����HJ/T 355�����Ҫ��"),
	JSJYBZBZCYX02("206", "10000330", "2���ֳ��������У׼Һ������У׼Һ���飬��������Ư�Ʋ�����HJ/T 355�����Ҫ��"),
	JSJYBZBZCYX03("206", "10000331", "3���ֳ������ʿ������飬�ʿ����ⶨ����������ڱ�׼ֵ�ġ�10%"),
	
	//207��ˮ��UV�� �������ص��������ö��ֵ
	UVLLCSDY01("207", "10000332", "1���߲����ࡢ�߲۹��ת��ϵ���Ȳ���������������ա��ǼǱ��������һ����Ч�����һ�£������ڳ��������������ƣ�"),
	UVLLCSDY02("207", "10000333", "2���ܵ��ܾ���ת��ϵ���Ȳ������������ա��ǼǱ��������һ����Ч�����һ�£������ڳ���������Źܵ������ƣ�"),
	UVLLCLDY01("207", "10000334", "1��Һλ����Ӧ׼ȷ�����������ʱ�������ĭ�����̽ͷλ�ð�װ�ڹ涨�ĵ�λ���������ڳ��������������ƣ�"),
	UVLLCLDY02("207", "10000335", "2���ǽ����ܵ���װ�ı������ӵػ���������ӵ��߿�·�ӵ������������ڵ�Źܵ������ƣ�"),
	LLCSDYBZCYX01("207", "10000336", "1���߲����ࡢ�߲۹��ת��ϵ���Ȳ������������ա��ǼǱ��������һ����Ч����˲�һ�£������ڳ��������������ƣ�"),
	LLCSDYBZCYX02("207", "10000337", "2���ܵ��ܾ���ת��ϵ���Ȳ�������Ӧ�����ա��ǼǱ��������һ����Ч�����һ�£������ڳ���������Źܵ������ƣ�"),
	LLCLDYBZCYX01("207", "10000338", "1������Һλ���յǼǱ����Ĳ�������Ϊ����������������ʾ�����Ĳ�ֵ��������˵�����������ȵ�Ҫ��"),
	LLCLDYBZCYX02("207", "10000339", "2���ǽ����ܵ���װ�ı������ӵػ���������ӵ��߿�·�ӵص㸯ʴ�����ѻ���ѣ������ڵ�Źܵ������ƣ�"),
	
	//208��ˮ��UV�� ���ݲɼ����������ص��������ö��ֵ
	UVSJCSDY01("208", "10000340", "�Զ�������������ݲɼ��������������ݲɼ������������̵ȣ�����Ӧһ�£����������ļ����ǼǱ�������һ����Ч�����һ��"),
	UVSJXLLJ01("208", "10000341", "�Զ�������������ݲɼ������������������·��������"),
	UVSJSJCS01("208", "10000342", "��λ�������ݲɼ���Ԫ�ɼ���ʵʱ��ֵӦһ��"),
	UVSJCSBZCYX01("208", "10000343", "1�����������������ļ����ǼǱ�������һ����Ч����˲�һ��"),
	UVSJCSBZCYX02("208", "10000344", "2�����ݲɼ������������ù��ͻ�������ù���"),
	UVSJXLBZCYX01("208", "10000345", "1�����ݲɼ����������Զ�����������װ�в��������ݴ����豸����ɱ�̿����������źŴ����豸�����˲��������Ƶ���������Χ���豸��"),
	UVSJXLBZCYX02("208", "10000346", "2�����ݲɼ�����������ͨ���豸�����ƽ���������߷�����������ͨѶ�豸��֮���������������豸��"),
	UVSJXLBZCYX03("208", "10000347", "3���Զ������ʩֹͣ���������ݲɼ��������Բ������Զ�������ʵ���������������ݡ�"),
	UVSJSJBZCYX01("208", "10000348", "��װ�����������ݴ�С�͵�������"),
	
	//209��ˮ(UV) �̶���ȾԴ��ˮ�Զ����ϵͳ�ֳ��˲��������100�֣�����ö��
	UVJCYFYQAZ01("209", "10000349", "�������6m2"),
	UVJCYFYQAZ02("209", "10000350", "�䱸ר������Լ��յ�"),
	UVJCYFYQAZ03("209", "10000351", "���з���ϵͳ"),
	UVJCYFYQAZ04("209", "10000352", "�䱸����ˮ"),
	UVJCYFYQAZ05("209", "10000353", "��·����"),
	UVJCYFYQAZ06("209", "10000354", "����ɾ�����������Ʒ"),
	UVJCYFYQAZ07("209", "10000355", "��װ�Զ�����豸��ȡˮ������������λ��"),
	UVJCYFYQAZ08("209", "10000356", "���ۿڽ���淶����װ�б�׼�����߲�"),
	UVJCYFYQAZ09("209", "10000357", "�����ۿڱ�־�ơ���ż�������Ⱦ������"),
	UVJCYFYQAZ10("209", "10000358", "�����۰�װλ������˳ֱ�γ���Ӧ����ˮ�����5-10���� ���γ�ˮ������û��"),
	UVJCYFYQAZ11("209", "10000359", "̽ͷ��װ���߲۶����������ϣ���װ�ι̣������ƶ���  �������ˮλ���߲ۼ������һ��"),
	UVWWMSXTWZ01("209", "10000360", "��pH�ơ�������"),
	UVWWMSXTWZ02("209", "10000361", "��COD������������������ǣ�����������������İ����ص��ŷ���ҵ��ʡ��������ˮ������Ӧװ�������׷����ǣ�ʡ��������ˮ�����������粻��װ�����������λ������ű���"),
	UVWWMSXTWZ03("209", "10000362", "���г�ϴ�ͷ���ϴ����"),
	UVWWMSXTWZ04("209", "10000363", "�з�Һ����װ��"),
	UVSBYXWHDF01("209", "10000364", "��������TOCΪ2Сʱһ�Σ�COD�����������ס��ܵ�Ϊ4Сʱ1��"),
	UVSBYXWHDF02("209", "10000365", "�������ĵƹ⡢ָʾ��״̬����"),
	UVSBYXWHDF03("209", "10000367", "��������裬��Ϊ3���ŷű�׼ֵ���粻���裬Ӧ�ܲ鿴"),
	UVSBYXWHDF04("209", "10000368", "�������޸�ʴ����������������©ˮ"),
	UVSBYXWHDF05("209", "10000369", "COD��������׼��Һ����Ч������Ũ����������ƥ��"),
	UVSBYXWHDF06("209", "10000370", "���������Լ�����Ч����"),
	UVSBYXWHDF07("209", "10000371", "TOC/CODת��ϵ�����¼һ�£���TOC��ֵ������һ��"),
	UVSBYXWHDF08("209", "10000372", "pH�ֳ�У�顢COD��������׼��ҺУ�����Ҫ��"),
	UVSJCJXTZQ01("209", "10000373", "�ֳ��˲�ʱ��������"),
	UVSJCJXTZQ02("209", "10000374", "��������ʾ����������"),
	UVSJCJXTZQ03("209", "10000375", "�������ϵ��������Ӧ���ӵ�����������ͬ"),
	UVSJCJXTZQ04("209", "10000376", "һ���Ǳ������ɵ�����������2%������������5%"),
	UVSJCJXTZQ05("209", "10000377", "�������У׼��¼У��������ͬʱ������"),
	UVYXGLGF01("209", "10000378", "������վ�������ƶȡ���Ա��λְ��"),
	UVYXGLGF02("209", "10000379", "��ҵ��Ա�л������䷢����Ч�����֤��"),
	UVYXGLGF03("209", "10000380", "�����豸ʹ��˵���顢�ϸ�֤��������Ʒ��֤���������"),
	UVYXGLGF04("209", "10000381", "ά����¼�����ɲᣬ������������ƽ��ÿ��1��"),
	UVYXGLGF05("209", "10000382", "ά����¼���з�Һ���ü�¼"),
	UVYXGLGF06("209", "10000383", "ά�޼�¼�����ɲᣬ���������1����Ӧ���޸�ʱ��С��3��"),
	UVYXGLGF07("209", "10000384", "У׼��¼�����ɲᣬ�������������ʿؼ�¼ÿ������1�Σ�ʵ���ȶԼ�¼ÿ������1��"),
	
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
	 * �������ܣ������������ͱ�Ż�ø������ͼ��ϣ��ö��Ÿ���
	
	 * ���������
	
	 * ����ֵ��
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
	
	/** ģ����� */
	private String templatecode;
	/** ���� */
	private String code;
	/** ���� */
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