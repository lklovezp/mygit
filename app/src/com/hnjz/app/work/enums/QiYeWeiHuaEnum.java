package com.hnjz.app.work.enums;


/**
 * ��ҵΣ��ö��
 * ���ߣ�����
 */
public enum QiYeWeiHuaEnum {
    //��ҵ��������������������������
	QYDQHJZL_YJ("1","һ��"),
	QYDQHJZL_EJ("2","����"),
	QYDQHJZL_SJ("3","����"),
	
	//Χ��
	TONGYONGYOU("0","��"),
	TONGYONGWU("1","��"),
//	//ר����й��
//	ZYPXGYOU("0","��"),
//	ZYPXGWU("1","��"),
//	//�������
//	DMFSYOU("0","��"),
//	DMFSWU("1","��"),
	TONGYONGSHI("Y","��"),
	TONGYONGFOU("N","��"),
	//�豸���������
	SBHWZLB_1("1","���˷���װ������"),
	SBHWZLB_2("2","������ʩ"),
	SBHWZLB_3("3","��©���ռ�����/�豸"),
	SBHWZLB_4("4","Ӧ������豸"),
	SBHWZLB_5("5","Ӧ����Ԯ����"),
	SBHWZLB_6("6","����"),
	
	//����״̬
	WLZT_QT("1","����"),
	QLZT_YT("2","Һ��"),
	WLZT_GT("3","����"),
	//���䷽ʽ
	YSFS_1("1","��·"),
	YSFS_2("2","��· "),
	YSFS_3("3","ˮ·"),
	YSFS_4("4","����"),
	
	//�豸״̬
	SBZT_1("1","�ܱ�ʽ"),
	SBZT_2("2","���ܱ�ʽ"),
	SBZT_3("3","����ʽ"),
	
	//������ʽ
	SCFS_1("1","����ʽ"),
	SCFS_2("2","��Ъʽ"),

	//��ˮ����ˮ�幦�����(�ر�ˮ��
	FSSNSTGNLXDBS_1("1","�� �� "),
	FSSNSTGNLXDBS_2("2","�� �� "),
	FSSNSTGNLXDBS_3("3","�� ��"),
	FSSNSTGNLXDBS_4("4","�� �� "),
	FSSNSTGNLXDBS_5("5","�� ��"),
	
	//��ˮ����ˮ�幦�����(��ˮ��
	FSSNSTGNLBHS_1("1","��һ��"),
	FSSNSTGNLBHS_2("2","�ڶ���"),
	FSSNSTGNLBHS_3("3","������ "),
	FSSNSTGNLBHS_4("4","������"),
	
	//�徻��ˮ����ˮ�幦�����(�ر�ˮ)
	QJXSSNSTGNLBDBS_1("1","�� �� "),
	QJXSSNSTGNLBDBS_2("2","�� ��"),
	QJXSSNSTGNLBDBS_3("3","�� ��"),
	QJXSSNSTGNLBDBS_4("4","�� ��"),
	QJXSSNSTGNLBDBS_5("5","�� ��"),
	
	//�徻��ˮ����ˮ�幦�����(��ˮ)
	QJXSSNSTGNLBHS_1("1","��һ�� "),
	QJXSSNSTGNLBHS_2("2","�ڶ��� "),
	QJXSSNSTGNLBHS_3("3","������"),
	QJXSSNSTGNLBHS_4("4","������"),
	;
		
	/** ���� */
	private String code;
	/** ���� */
	private String name;
	private QiYeWeiHuaEnum(String code,String name){
		this.code=code;
		this.name=name;
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
	public void setName(String name) {
		this.name = name;
	}
	
}