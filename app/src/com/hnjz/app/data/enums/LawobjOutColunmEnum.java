package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ�������ֶζ�������
 *
 */
public enum LawobjOutColunmEnum {
	//��ҵ��ȾԴ
	gywry_dwmc("01","0101","��λ����","DWMC_"),
	gywry_ssxzq("01","0102","����������","SSXZQ_"),
	gywry_hy("01","0103","��ҵ","HY_"),
	gywry_fddbr("01","0104","����������","FDDBR_"),
	gywry_fddbrlxdh("01","0105","������������ϵ�绰","FDDBRLXDH_"),
	gywry_hbfzr("01","0106","����������","HBFZR_"),
	gywry_hbfzrlxdh("01","0107","������������ϵ�绰","HBFZRLXDH_"),
	gywry_jd("01","0108","����","JD_"),
	gywry_wd("01","0109","γ��","WD_"),
	
	gywry_zzjgdm("01","0110","��֯��������","ZZJGDM_"),
	gywry_dz("01","0111","��λ��ַ","DZ_"),
	gywry_zt("01","0112","״̬","ZT_"),
	gywry_yyzzzch("01","0113","Ӫҵִ��ע���","YYZZZCH_"),
	gywry_pwxkz("01","0114","��������֤","PWXKZ_"),
	gywry_fspfkgs("01","0115","��ˮ�ŷſڸ���","FSPFKGS_"),
	gywry_fqpfkgs("01","0116","�����ŷſڸ���","FQPFKGS_"),
	gywry_zsygs("01","0117","����Դ����","ZSYGS_"),
	gywry_gfdfcgs("01","0118","�̷϶ѷų�����","GFDFCGS_"),
	gywry_wrfzsssl("01","0119","��Ⱦ������ʩ����","WRFZSSSL_"),
	gywry_wfcccgs("01","0120","Σ�ϴ��泡����","WFCCCGS_"),
	gywry_fxygs("01","0121","����Դ����","FXYGS_"),
	gywry_kzlx("01","0122","��������","KZLX_"),
	gywry_wghzrr("01","0123","����������","WGHZRR_"),
	gywry_ssjgbm("01","0124","������ܲ���","SSJGBM_"),
	gywry_qyzt("01","0125","��ҵ״̬","QYZT_"),
	gywry_cjr("01","0126","������","CJR_"),
	gywry_qysczt("01","0127","��ҵ����״̬","QYSCZT_"),
	gywry_tyshxydm("01","0128","ͳһ������ô���","TYSHXYDM_"),
	gywry_tcrq("01","0129","Ͷ������","TCRQ_"),
	gywry_yb("01","0130","�ʱ�","YB_"),
	gywry_qygm("01","0131","��ҵ��ģ","QYGM_"),
	gywry_mcjp("01","0132","���Ƽ�ƴ","MCJP_"),
	gywry_bm("01","0133","����","BM_"),
	gywry_cym("01","0134","������","CYM_"),
	gywry_qybh("01","0135","��ҵ���","QYBH_"),
	gywry_sbdm("01","0136","�걨����","SBDM_"),
	gywry_zclx("01","0137","ע������","ZCLX_"),
	gywry_gklx("01","0138","��������","GKLX_"),
	gywry_lsgx("01","0139","������ϵ","LSGX_"),
	gywry_ly("01","0140","����","LY_"),
	gywry_zzdmj("01","0141","��ռ�����","ZZDMJ_"),
	gywry_sfsfqy("01","0142","�Ƿ��շ���ҵ","SFSFQY_"),
	gywry_hyqgx("01","0143","�������ϵ","HYQGX_"),
	gywry_zzhbrys("01","0144","רְ������Ա��","ZZHBRYS_"),
	gywry_qyhbbm("01","0145","��ҵ��������","QYHBBM_"),
	gywry_dzyj("01","0146","�����ʼ�","DZYJ_"),
	gywry_cz("01","0147","����","CZ_"),
	gywry_khyh("01","0148","��������","KHYH_"),
	gywry_yhzh("01","0149","�����˺�","YHZH_"),
	gywry_qywz("01","0150","��ҵ��ַ","QYWZ_"),
	
	//������Ŀ
	jsxm_jsxmmc("02","0201","������Ŀ����","JSXMMC_"),
	jsxm_ssxzq("02","0202","����������","SSXZQ_"),
	jsxm_hylx("02","0203","��ҵ����","HYLX_"),
	jsxm_fddbr("02","0204","����������","FDDBR_"),
	jsxm_fddbrlxdh("02","0205","������������ϵ�绰","FDDBRLXDH_"),
	jsxm_hbfzr("02","0206","����������","HBFZR_"),
	jsxm_hbfzrlxdh("02","0207","������������ϵ�绰","HBFZRLXDH_"),
	jsxm_jd("02","0208","����","JD_"),
	jsxm_wd("02","0209","γ��","WD_"),
	
	jsxm_jsdd("02","0210","����ص�","JSDD_"),
	jsxm_spjg("02","0211","��������","SPJG_"),
	jsxm_jsjdjsczt("02","0212","������ȼ�����״̬","JSJDJSCZT_"),
	jsxm_jsxz("02","0213","��������","JSXZ_"),
	jsxm_jsnr("02","0214","��������","JSNR_"),
	jsxm_jsgm("02","0215","�����ģ","JSGM_"),
	jsxm_cn("02","0216","����","CN_"),
	jsxm_xmkgsj("02","0217","��Ŀ����ʱ��","XMKGSJ_"),
	jsxm_jcsj("02","0218","����ʱ��","JCSJ_"),
	jsxm_tcsj("02","0219","Ͷ��ʱ��","TCSJ_"),
	jsxm_zt("02","0220","״̬","ZT_"),
	jsxm_dwmc("02","0221","��λ����","DWMC_"),
	jsxm_dwdz("02","0222","��ַ","DWDZ_"),
	jsxm_jldw("02","0223","������λ","JLDW_"),
	jsxm_lawobjid("02","0224","����ִ������id","SSZFDXID_"),
	jsxm_wghzrr("02","0225","����������","WGHZRR_"),
	jsxm_ssjgbm("02","0226","������ܲ���","SSJGBM_"),
	jsxm_qyzt("02","0227","��ҵ״̬","QYZT_"),
	jsxm_cjr("02","0228","������","CJR_"),
	jsxm_qysczt("02","0229","��ҵ����״̬","QYSCZT_"),

	//ҽԺ
	yy_dwmc("03","0301","��λ����","DWMC_"),
	yy_ssxzq("03","0302","����������","SSXZQ_"),
	yy_hy("03","0303","��ҵ","HY_"),
	yy_fddbr("03","0304","����������","FDDBR_"),
	yy_fddbrlxdh("03","0305","������������ϵ�绰","FDDBRLXDH_"),
	yy_hbfzr("03","0306","����������","HBFZR_"),
	yy_hbfzrlxdh("03","0307","������������ϵ�绰","HBFZRLXDH_"),
	yy_jd("03","0308","����","JD_"),
	yy_wd("03","0309","γ��","WD_"),
	
	yy_zzjgdm("03","0310","��֯��������","ZZJGDM_"),
	yy_dz("03","0311","��ַ","DZ_"),
	yy_zt("03","0312","״̬","ZT_"),
	yy_fsaqxkz("03","0313","���䰲ȫ����֤","FSAQXKZ_"),
	yy_pwxkz("03","0314","��������֤","PWXKZ_"),
	yy_zyzgz("03","0315","ְҵ����֤","ZYZGZ_"),
	yy_fspfkgs("03","0316","��ˮ�ŷſڸ���","FSPFKGS_"),
	yy_fqpfkgs("03","0317","�����ŷſڸ���","FQPFKGS_"),
	yy_zsygs("03","0318","����Դ����","ZSYGS_"),
	yy_gfdfcgs("03","0319","�̷϶ѷų�����","GFDFCGS_"),
	yy_wryfzsssl("03","0320","��ȾԴ������ʩ����","WRYFZSSSL_"),
	yy_cws("03","0321","��λ��","CWS_"),
	yy_fsysl("03","0322","����Դ����","FSYSL_"),
	yy_sxzz("03","0323","����װ��","SXZZ_"),
	yy_wghzrr("03","0324","����������","WGHZRR_"),
	yy_ssjgbm("03","0325","������ܲ���","SSJGBM_"),
	yy_qyzt("03","0326","��ҵ״̬","QYZT_"),
	yy_cjr("03","0327","������","CJR_"),
	yy_qysczt("03","0328","��ҵ����״̬","QYSCZT_"),
	yy_tcrq("03","0329","Ͷ������","TCRQ_"),
	yy_yb("03","0330","��������","YB_"),
	yy_qygm("03","0331","��ҵ��ģ","QYGM_"),
	
	//��¯
	gl_dwmc("04","0401","��λ����","DWMC_"),
	gl_ssxzq("04","0402","����������","SSXZQ_"),
	gl_hy("04","0403","��ҵ","HY_"),
	gl_fddbr("04","0404","����������","FDDBR_"),
	gl_fddbrlxdh("04","0405","������������ϵ�绰","FDDBRLXDH_"),
	gl_hbfzr("04","0406","����������","HBFZR_"),
	gl_hbfzrlxdh("04","0407","������������ϵ�绰","HBFZRLXDH_"),
	gl_jd("04","0408","����","JD_"),
	gl_wd("04","0409","γ��","WD_"),
	
	gl_dz("04","0410","��ַ","DZ_"),
	gl_zzjgdm("04","0411","��֯��������","ZZJGDM_"),
	gl_zt("04","0412","״̬","ZT_"),
	gl_yyzzzch("04","0413","Ӫҵִ��ע���","YYZZZCH_"),
	gl_pwxkz("04","0414","��������֤","PWXKZ_"),
	gl_fspfkgs("04","0415","��ˮ�ŷſڸ���","FSPFKGS_"),
	gl_fqpfkgs("04","0416","�����ŷſڸ���","FQPFKGS_"),
	gl_zsygs("04","0417","����Դ����","ZSYGS_"),
	gl_gfdfcgs("04","0418","�̷϶ѷų�����","GFDFCGS_"),
	gl_wryfzsssl("04","0419","��ȾԴ������ʩ����","WRYFZSSSL_"),
	gl_gls("04","0420","��¯����̨��","GLS_"),
	gl_yt("04","0421","��;","YT_"),
	gl_zd("04","0422","����/Сʱ","ZD_"),
	gl_wghzrr("04","0423","����������","WGHZRR_"),
	gl_ssjgbm("04","0424","������ܲ���","SSJGBM_"),
	gl_qyzt("04","0425","��ҵ״̬","QYZT_"),
	gl_cjr("04","0426","������","CJR_"),
	gl_qysczt("04","0427","��ҵ����״̬","QYSCZT_"),
	
	
	//��������
	jzgd_sgxmmc("05","0501","ʩ����Ŀ����","SGXMMC_"),
	jzgd_ssxzq("05","0502","����������","SSXZQ_"),
	jzgd_hy("05","0503","��ҵ","HY_"),
	jzgd_fddbr("05","0504","����������","FDDBR_"),
	jzgd_fddbrlxdh("05","0505","������������ϵ�绰","FDDBRLXDH_"),
	jzgd_hbfzr("05","0506","����������","HBFZR_"),
	jzgd_hbfzrlxdh("05","0507","������������ϵ�绰","HBFZRLXDH_"),
	jzgd_jd("05","0508","����","JD_"),
	jzgd_wd("05","0509","γ��","WD_"),
	
	jzgd_lspwxkzbm("05","0510","��ʱ��������֤����","LSPWXKZBM_"),
	jzgd_gcdd("05","0511","���̵ص�","GCDD_"),
	jzgd_sgdwmc("05","0512","ʩ����λ����","SGDWMC_"),
	jzgd_dz("05","0513","��λ��ַ","DZ_"),
	jzgd_zt("05","0514","״̬","ZT_"),
	jzgd_kgrq("05","0515","��������","KGRQ_"),
	jzgd_yjjgrq("05","0516","Ԥ�ƿ�������","YJJGRQ_"),
	jzgd_zsygs("05","0517","����Դ����","ZSYGS_"),
	jzgd_wryfzsssl("05","0518","��ȾԴ������ʩ����","WRYFZSSSL_"),
	jzgd_wghzrr("05","0519","����������","WGHZRR_"),
	jzgd_ssjgbm("05","0520","������ܲ���","SSJGBM_"),
	jzgd_qyzt("05","0521","��ҵ״̬","QYZT_"),
	jzgd_cjr("05","0522","������","CJR_"),
	jzgd_qysczt("05","0523","��ҵ����״̬","QYSCZT_"),
	jzgd_jzgdzt("05","0524","��������״̬","JZGDZT_"),

	//����
	sc_dwmc("06","0601","��λ����","DWMC_"),
	sc_ssxzq("06","0602","����������","SSXZQ_"),
	sc_hy("06","0603","��ҵ","HY_"),
	sc_fddbr("06","0604","��Ӫ��","FDDBR_"),
	sc_fddbrlxdh("06","0605","��Ӫ����ϵ�绰","FDDBRLXDH_"),
	sc_hbfzr("06","0606","����������","HBFZR_"),
	sc_hbfzrlxdh("06","0607","������������ϵ�绰","HBFZRLXDH_"),
	sc_jd("06","0608","����","JD_"),
	sc_wd("06","0609","γ��","WD_"),
	
	sc_dz("06","0610","��ַ","DZ_"),
	sc_zt("06","0611","״̬","ZT_"),
	sc_yyzzzch("06","0612","Ӫҵִ��ע���","YYZZZCH_"),
	sc_pwxkz("06","0613","��������֤","PWXKZ_"),
	sc_fspfkgs("06","0614","��ˮ�ŷſڸ���","FSPFKGS_"),
	sc_fqpfkgs("06","0615","�����ŷſڸ���","FQPFKGS_"),
	sc_zsygs("06","0616","����Դ����","ZSYGS_"),
	sc_gfdfcgs("06","0617","�̷϶ѷų�����","GFDFCGS_"),
	sc_wryfzsssl("06","0618","��ȾԴ������ʩ����","WRYFZSSSL_"),
	sc_wsxkz("06","0619","��������֤","WSXKZ_"),
	sc_wghzrr("06","0620","����������","WGHZRR_"),
	sc_ssjgbm("06","0621","������ܲ���","SSJGBM_"),
	sc_qyzt("06","0622","��ҵ״̬","QYZT_"),
	sc_cjr("06","0623","������","CJR_"),
	sc_qysczt("06","0624","��ҵ����״̬","QYSCZT_"),
	sc_yb("06","0625","��������","YB_"),
	
	//������ֳ
	xqyz_xqyzcmc("07","0701","������ֳ������","XQYZCMC_"),					
	xqyz_ssxzq("07","0702","����������","SSXZQ_"),
	xqyz_fddbr("07","0703","����������","FDDBR_"),
	xqyz_fddbrlxdh("07","0704","������������ϵ�绰","FDDBRLXDH_"),
	xqyz_hbfzr("07","0705","����������","HBFZR_"),
	xqyz_hbfzrlxdh("07","0706","������������ϵ�绰","HBFZRLXDH_"),
	xqyz_jd("07","0707","����","JD_"),
	xqyz_wd("07","0708","γ��","WD_"),
	
	xqyz_dz("07","0709","��ַ","DZ_"),
	xqyz_zt("07","0710","״̬","ZT_"),
	xqyz_yyzzzch("07","0711","Ӫҵִ��ע���","YYZZZCH_"),
	xqyz_pwxkz("07","0712","��������֤","PWXKZ_"),
	xqyz_fspfkgs("07","0713","��ˮ�ŷſڸ���","FSPFKGS_"),
	xqyz_fqpfkgs("07","0714","�����ŷſڸ���","FQPFKGS_"),
	xqyz_zsygs("07","0715","����Դ����","ZSYGS_"),
	xqyz_gfdfcgs("07","0716","�̷϶ѷų�����","GFDFCGS_"),
	xqyz_wryfzsssl("07","0717","��ȾԴ������ʩ����","WRYFZSSSL_"),
	xqyz_yzmj("07","0718","��ֳ���","YZMJ_"),		
	xqyz_cls("07","0719","������","CLS_"),					
	xqyz_dwmc("07","0720","��λ����","DWMC_"),
	xqyz_zzjgdm("07","0721","��֯��������","ZZJGDM_"),				
	xqyz_wghzrr("07","0722","����������","WGHZRR_"),
	xqyz_ssjgbm("07","0723","������ܲ���","SSJGBM_"),
	xqyz_qyzt("07","0724","��ҵ״̬","QYZT_"),
	xqyz_cjr("07","0725","������","CJR_"),
	xqyz_qysczt("07","0726","��ҵ����״̬","QYSCZT_"),
	xqyz_tyshxydm("07","0727","ͳһ������ô���","TYSHXYDM_"),
	xqyz_cll("07","0728","������","CLL_"),
	xqyz_xqyzzl("07","0729","������ֳ����","XQYZZL_"),
	
	//����ҵ��ϴ����ϴԡ��
	fwy_dwmc("08","0801","��λ����","DWMC_"),
	fwy_ssxzq("08","0802","����������","SSXZQ_"),
	fwy_fddbr("08","0803","����������","FDDBR_"),
	fwy_fddbrlxdh("08","0804","������������ϵ�绰","FDDBRLXDH_"),
	fwy_hbfzr("08","0805","����������","HBFZR_"),
	fwy_hbfzrlxdh("08","0806","������������ϵ�绰","HBFZRLXDH_"),
	fwy_jd("08","0807","����","JD_"),
	fwy_wd("08","0808","γ��","WD_"),
	
	fwy_dz("08","0809","��ַ","DZ_"),
	fwy_zt("08","0810","״̬","ZT_"),
	fwy_yyzzzch("08","0811","Ӫҵִ��ע���","YYZZZCH_"),
	fwy_fzsj("08","0812","��֤ʱ��","FZSJ_"),
	fwy_ds("08","0813","����","DS_"),
	fwy_wyjc("08","0814","λ�ڼ���","WYJC_"),
	fwy_zz("08","0815","סլ","ZZ_"),
	fwy_sf("08","0816","�̷�","SF_"),
	fwy_zwhj("08","0817","��Χ����","ZWHJ_"),
	fwy_mj("08","0818","����O","MJ_"),
	fwy_fj("08","0819","����","FJ_"),
	fwy_ry("08","0820","��Ա","RY_"),
	fwy_gz("08","0821","����","GZ_"),
	fwy_lyt("08","0822","��ԡͷ","LYT_"),
	fwy_trq("08","0823","��Ȼ��","TRQ_"),
	fwy_gl("08","0824","��¯��̨/�֣�","GL_"),
	fwy_drsq("08","0825","����ˮ��","DRSQ_"),
	fwy_cw("08","0826","��λ","CW_"),
	fwy_kt("08","0827","�յ�","KT_"),
	fwy_djgngl("08","0828","��¯��������ů��","DJGNGL_"),
	fwy_jzgr("08","0829","���й���","JZGR_"),
	fwy_xyj("08","0830","ϴ����","XYJ_"),
	fwy_pmj("08","0831","��ĭ��","PMJ_"),
	fwy_sgj("08","0832","˦�ɻ�","SGJ_"),
	fwy_ssjgbm("08","0833","������ܲ���","SSJGBM_"),
	fwy_qyzt("08","0834","��ҵ״̬","QYZT_"),
	fwy_bz("08","0835","��ע","BZ_"),
	fwy_cjr("08","0836","������","CJR_"),
	fwy_qysczt("08","0837","��ҵ����״̬","QYSCZT_"),
	fwy_hy("08","0838","��ҵ","HY_"),
	
	//��ʳҵ
	ysy_dwmc("09","0901","��λ����","DWMC_"),
	ysy_ssxzq("09","0902","����������","SSXZQ_"),
	ysy_fddbr("09","0903","����������","FDDBR_"),
	ysy_fddbrlxdh("09","0904","������������ϵ�绰","FDDBRLXDH_"),
	ysy_hbfzr("09","0905","����������","HBFZR_"),
	ysy_hbfzrlxdh("09","0906","������������ϵ�绰","HBFZRLXDH_"),
	ysy_jd("09","0907","����","JD_"),
	ysy_wd("09","0908","γ��","WD_"),
	
	ysy_dz("09","0909","��ַ","DZ_"),
	ysy_zt("09","0910","״̬","ZT_"),
	ysy_yyzzzch("09","0911","Ӫҵִ��ע���","YYZZZCH_"),
	ysy_fzsj("09","0912","��֤ʱ��","FZSJ_"),
	ysy_ds("09","0913","����","DS_"),
	ysy_wyjc("09","0914","λ�ڼ���","WYJC_"),
	ysy_zhz("09","0915","סլ","ZHZ_"),
	ysy_sf("09","0916","�̷�","SF_"),
	ysy_zwhj("09","0917","��Χ����","ZWHJ_"),
	ysy_mj("09","0918","����O","MJ_"),
	ysy_zz("09","0919","����","ZZ_"),
	ysy_yz("09","0920","����","YZ_"),
	ysy_ry("09","0921","��Ա","RY_"),
	ysy_bx("09","0922","����","BX_"),
	ysy_trq("09","0923","��Ȼ��","TRQ_"),
	ysy_yhq("09","0924","Һ����","YHQ_"),
	ysy_m("09","0925","ú","M_"),
	ysy_zy("09","0926","����","ZY_"),
	ysy_zbj("09","0927","�Ʊ���","ZBJ_"),
	ysy_lnjz("09","0928","��������","LNJZ_"),
	ysy_hmj("09","0929","�����","HMJ_"),
	ysy_djj("09","0930","������","DJJ_"),
	ysy_pqfj("09","0931","���������̨��","PQFJ_"),
	ysy_yypfqk("09","0932","�����ŷ����","YYPFQK_"),
	ysy_yrfzssazsj("09","0933","��Ⱦ������ʩ��װʱ��","YRFZSSAZSJ_"),
	ysy_sfzcyx("09","0934","�Ƿ���������","SFZCYX_"),
	ysy_pyygdsfccld("09","0935","�����̹ܵ��Ƿ񳬳�¥��","PYYGDSFCCLD_"),
	ysy_cj("09","0936","����","CJ_"),
	ysy_fl("09","0937","����","FL_"),
	ysy_qxsj("09","0938","��ϴʱ��","QXSJ_"),
	ysy_wxsj("09","0939","ά��ʱ��","WXSJ_"),
	ysy_ssjgbm("09","0940","������ܲ���","SSJGBM_"),
	ysy_qyzt("09","0941","��ҵ״̬","QYZT_"),
	ysy_bz("09","0942","��ע","BZ_"),
	ysy_cjr("09","0943","������","CJR_"),
	ysy_qysczt("09","0944","��ҵ����״̬","QYSCZT_"),
	
	//��������ҵ
	zzy_dwmc("10","1001","��λ����","DWMC_"),
	zzy_ssxzq("10","1002","����������","SSXZQ_"),
	zzy_fddbr("10","1003","����������","FDDBR_"),
	zzy_fddbrlxdh("10","1004","������������ϵ�绰","FDDBRLXDH_"),
	zzy_hbfzr("10","1005","����������","HBFZR_"),
	zzy_hbfzrlxdh("10","1006","������������ϵ�绰","HBFZRLXDH_"),
	zzy_jd("10","1007","����","JD_"),
	zzy_wd("10","1008","γ��","WD_"),
	
	zzy_dzh("10","1009","��ַ","DZH_"),
	zzy_zt("10","1010","״̬","ZT_"),
	zzy_yyzzzch("10","1011","Ӫҵִ��ע���","YYZZZCH_"),
	zzy_fzsj("10","1012","��֤ʱ��","FZSJ_"),
	zzy_ds("10","1013","����","DS_"),
	zzy_wyjc("10","1014","λ�ڼ���","WYJC_"),
	zzy_zz("10","1015","סլ","ZZ_"),
	zzy_sf("10","1016","�̷�","SF_"),
	zzy_zwhj("10","1017","��Χ����","ZWHJ_"),
	zzy_mj("10","1018","����O","MJ_"),
	zzy_qgj("10","1019","�и��","QGJ_"),
	zzy_jmj("10","1020","��ĥ��","JMJ_"),
	zzy_dz("10","1021","����","DZ_"),
	zzy_dj("10","1022","���","DJ_"),
	zzy_djq("10","1023","�ϼ�ǯ","DJQ_"),
	zzy_qzj("10","1024","ȥ�ѻ�","QZJ_"),
	zzy_dbj("10","1025","�����","DBJ_"),
	zzy_pmj("10","1026","ƽĥ��","PMJ_"),
	zzy_dkj("10","1027","��̻�","DKJ_"),
	zzy_zzj("10","1028","���ӻ�","ZZJ_"),
	zzy_pgj("10","1029","�׹��","PGJ_"),
	zzy_db("10","1030","����","DB_"),
	zzy_gsjzcs("10","1031","���������ʩ","GSJZCS_"),
	zzy_zzdd("10","1032","�����ص�","ZZDD_"),
	zzy_psyq("10","1033","�硢ˢ����","PSYQ_"),
	zzy_ssjgbm("10","1034","������ܲ���","SSJGBM_"),
	zzy_qyzt("10","1035","��ҵ״̬","QYZT_"),
	zzy_bz("10","1036","��ע","BZ_"),
	zzy_cjr("10","1037","������","CJR_"),
	zzy_qysczt("10","1038","��ҵ����״̬","QYSCZT_"),
	
	//����ҵ
	yly_dwmc("11","1101","��λ����","DWMC_"),
	yly_ssxzq("11","1102","����������","SSXZQ_"),
	yly_fddbr("11","1103","����������","FDDBR_"),
	yly_fddbrlxdh("11","1104","������������ϵ�绰","FDDBRLXDH_"),
	yly_hbfzr("11","1105","����������","HBFZR_"),
	yly_hbfzrlxdh("11","1106","������������ϵ�绰","HBFZRLXDH_"),
	yly_jd("11","1107","����","JD_"),
	yly_wd("11","1108","γ��","WD_"),
	
	yly_dz("11","1109","��ַ","DZ_"),
	yly_zt("11","1110","״̬","ZT_"),
	yly_yyzzzch("11","1111","Ӫҵִ��ע���","YYZZZCH_"),
	yly_fzsj("11","1112","��֤ʱ��","FZSJ_"),
	yly_ds("11","1113","����","DS_"),
	yly_wyjc("11","1114","λ�ڼ���","WYJC_"),
	yly_zz("11","1115","סլ","ZZ_"),
	yly_sf("11","1116","�̷�","SF_"),
	yly_zwhj("11","1117","��Χ����","ZWHJ_"),
	yly_mj("11","1118","����O","MJ_"),
	yly_bxs("11","1119","������","BXS_"),
	yly_kts("11","1120","�յ���","KTS_"),
	yly_yx("11","1121","����","YX_"),
	yly_dn("11","1122","����","DN_"),
	yly_yxj("11","1123","��Ϸ��","YXJ_"),
	yly_pqs("11","1124","������","PQS_"),
	yly_pfk("11","1125","�ŷſ�","PFK_"),
	yly_ry("11","1126","��Ա","RY_"),
	yly_zw("11","1127","��λ","ZW_"),
	yly_xftdckcs("11","1128","����ͨ�����ڴ�ʩ","XFTDCKCS_"),
	yly_gyjzcs("11","1129","������ǽ�漰�ݶ��Ƿ��ȡ��Ч���������ʩ","GYJZCS_"),
	yly_gyclzl("11","1130","��ʹ�õĸ�����������","GYCLZL_"),
	yly_ssjgbm("11","1133","������ܲ���","SSJGBM_"),
	yly_qyzt("11","1134","��ҵ״̬","QYZT_"),
	yly_bz("11","1135","��ע","BZ_"),
	yly_cjr("11","1136","������","CJR_"),
	yly_qysczt("11","1137","��ҵ����״̬","QYSCZT_"),
	;
	
	
	private LawobjOutColunmEnum(String type,String code,String name,String simpleName){
		this.type = type;
		this.code = code;
		this.name = name;
		this.simpleName = simpleName;
	}
	
	private String type;//ִ���������ͱ��
	private String code;
	private String name;
	private String simpleName;
	
	
	
	/**
	 * 
	 * �������ܣ�ͨ��ִ���������ͻ�� ִ�������ֶζ��������б�
	
	 * ���������ִ����������
	
	 * ����ֵ��
	 */
	public static List<Combobox> getListColumnByType(String type){
		List<Combobox> list = new ArrayList<Combobox>();
		 for(LawobjOutColunmEnum lawobjColumn :values()){
			 if(lawobjColumn.type.equals(type)){
				 list.add(new Combobox(lawobjColumn.code,lawobjColumn.name));
			 }
		 }
		return list;
	}
	
	/**
	 * 
	 * �������ܣ�ͨ����Ż�ȡ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static LawobjOutColunmEnum getColumnByCode(String code){
		for(LawobjOutColunmEnum lawobjColumn :values()){
			if(lawobjColumn.getCode().equals(code)){
				return lawobjColumn;
			}
		}
		return null;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	
}