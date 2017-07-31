package com.hnjz.app.data.enums;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法对象字段对外名称
 *
 */
public enum LawobjOutColunmEnum {
	//工业污染源
	gywry_dwmc("01","0101","单位名称","DWMC_"),
	gywry_ssxzq("01","0102","所属行政区","SSXZQ_"),
	gywry_hy("01","0103","行业","HY_"),
	gywry_fddbr("01","0104","法定代表人","FDDBR_"),
	gywry_fddbrlxdh("01","0105","法定代表人联系电话","FDDBRLXDH_"),
	gywry_hbfzr("01","0106","环保负责人","HBFZR_"),
	gywry_hbfzrlxdh("01","0107","环保负责人联系电话","HBFZRLXDH_"),
	gywry_jd("01","0108","经度","JD_"),
	gywry_wd("01","0109","纬度","WD_"),
	
	gywry_zzjgdm("01","0110","组织机构代码","ZZJGDM_"),
	gywry_dz("01","0111","单位地址","DZ_"),
	gywry_zt("01","0112","状态","ZT_"),
	gywry_yyzzzch("01","0113","营业执照注册号","YYZZZCH_"),
	gywry_pwxkz("01","0114","排污许可证","PWXKZ_"),
	gywry_fspfkgs("01","0115","废水排放口个数","FSPFKGS_"),
	gywry_fqpfkgs("01","0116","废气排放口个数","FQPFKGS_"),
	gywry_zsygs("01","0117","噪声源个数","ZSYGS_"),
	gywry_gfdfcgs("01","0118","固废堆放场个数","GFDFCGS_"),
	gywry_wrfzsssl("01","0119","污染防治设施数量","WRFZSSSL_"),
	gywry_wfcccgs("01","0120","危废储存场个数","WFCCCGS_"),
	gywry_fxygs("01","0121","风险源个数","FXYGS_"),
	gywry_kzlx("01","0122","控制类型","KZLX_"),
	gywry_wghzrr("01","0123","网格化责任人","WGHZRR_"),
	gywry_ssjgbm("01","0124","所属监管部门","SSJGBM_"),
	gywry_qyzt("01","0125","企业状态","QYZT_"),
	gywry_cjr("01","0126","创建人","CJR_"),
	gywry_qysczt("01","0127","企业生产状态","QYSCZT_"),
	gywry_tyshxydm("01","0128","统一社会信用代码","TYSHXYDM_"),
	gywry_tcrq("01","0129","投产日期","TCRQ_"),
	gywry_yb("01","0130","邮编","YB_"),
	gywry_qygm("01","0131","企业规模","QYGM_"),
	gywry_mcjp("01","0132","名称简拼","MCJP_"),
	gywry_bm("01","0133","别名","BM_"),
	gywry_cym("01","0134","曾用名","CYM_"),
	gywry_qybh("01","0135","企业编号","QYBH_"),
	gywry_sbdm("01","0136","申报代码","SBDM_"),
	gywry_zclx("01","0137","注册类型","ZCLX_"),
	gywry_gklx("01","0138","国控类型","GKLX_"),
	gywry_lsgx("01","0139","隶属关系","LSGX_"),
	gywry_ly("01","0140","流域","LY_"),
	gywry_zzdmj("01","0141","总占地面积","ZZDMJ_"),
	gywry_sfsfqy("01","0142","是否收费企业","SFSFQY_"),
	gywry_hyqgx("01","0143","和央企关系","HYQGX_"),
	gywry_zzhbrys("01","0144","专职环保人员数","ZZHBRYS_"),
	gywry_qyhbbm("01","0145","企业环保部门","QYHBBM_"),
	gywry_dzyj("01","0146","电子邮件","DZYJ_"),
	gywry_cz("01","0147","传真","CZ_"),
	gywry_khyh("01","0148","开户银行","KHYH_"),
	gywry_yhzh("01","0149","银行账号","YHZH_"),
	gywry_qywz("01","0150","企业网址","QYWZ_"),
	
	//建设项目
	jsxm_jsxmmc("02","0201","建设项目名称","JSXMMC_"),
	jsxm_ssxzq("02","0202","所属行政区","SSXZQ_"),
	jsxm_hylx("02","0203","行业类型","HYLX_"),
	jsxm_fddbr("02","0204","法定代表人","FDDBR_"),
	jsxm_fddbrlxdh("02","0205","法定代表人联系电话","FDDBRLXDH_"),
	jsxm_hbfzr("02","0206","环保负责人","HBFZR_"),
	jsxm_hbfzrlxdh("02","0207","环保负责人联系电话","HBFZRLXDH_"),
	jsxm_jd("02","0208","经度","JD_"),
	jsxm_wd("02","0209","纬度","WD_"),
	
	jsxm_jsdd("02","0210","建设地点","JSDD_"),
	jsxm_spjg("02","0211","审批机关","SPJG_"),
	jsxm_jsjdjsczt("02","0212","建设进度及生产状态","JSJDJSCZT_"),
	jsxm_jsxz("02","0213","建设性质","JSXZ_"),
	jsxm_jsnr("02","0214","建设内容","JSNR_"),
	jsxm_jsgm("02","0215","建设规模","JSGM_"),
	jsxm_cn("02","0216","产能","CN_"),
	jsxm_xmkgsj("02","0217","项目开工时间","XMKGSJ_"),
	jsxm_jcsj("02","0218","建成时间","JCSJ_"),
	jsxm_tcsj("02","0219","投产时间","TCSJ_"),
	jsxm_zt("02","0220","状态","ZT_"),
	jsxm_dwmc("02","0221","单位名称","DWMC_"),
	jsxm_dwdz("02","0222","地址","DWDZ_"),
	jsxm_jldw("02","0223","监理单位","JLDW_"),
	jsxm_lawobjid("02","0224","所属执法对象id","SSZFDXID_"),
	jsxm_wghzrr("02","0225","网格化责任人","WGHZRR_"),
	jsxm_ssjgbm("02","0226","所属监管部门","SSJGBM_"),
	jsxm_qyzt("02","0227","企业状态","QYZT_"),
	jsxm_cjr("02","0228","创建人","CJR_"),
	jsxm_qysczt("02","0229","企业生产状态","QYSCZT_"),

	//医院
	yy_dwmc("03","0301","单位名称","DWMC_"),
	yy_ssxzq("03","0302","所属行政区","SSXZQ_"),
	yy_hy("03","0303","行业","HY_"),
	yy_fddbr("03","0304","法定代表人","FDDBR_"),
	yy_fddbrlxdh("03","0305","法定代表人联系电话","FDDBRLXDH_"),
	yy_hbfzr("03","0306","环保负责人","HBFZR_"),
	yy_hbfzrlxdh("03","0307","环保负责人联系电话","HBFZRLXDH_"),
	yy_jd("03","0308","经度","JD_"),
	yy_wd("03","0309","纬度","WD_"),
	
	yy_zzjgdm("03","0310","组织机构代码","ZZJGDM_"),
	yy_dz("03","0311","地址","DZ_"),
	yy_zt("03","0312","状态","ZT_"),
	yy_fsaqxkz("03","0313","辐射安全许可证","FSAQXKZ_"),
	yy_pwxkz("03","0314","排污许可证","PWXKZ_"),
	yy_zyzgz("03","0315","职业资质证","ZYZGZ_"),
	yy_fspfkgs("03","0316","废水排放口个数","FSPFKGS_"),
	yy_fqpfkgs("03","0317","废气排放口个数","FQPFKGS_"),
	yy_zsygs("03","0318","噪声源个数","ZSYGS_"),
	yy_gfdfcgs("03","0319","固废堆放场个数","GFDFCGS_"),
	yy_wryfzsssl("03","0320","污染源防治设施数量","WRYFZSSSL_"),
	yy_cws("03","0321","床位数","CWS_"),
	yy_fsysl("03","0322","放射源数量","FSYSL_"),
	yy_sxzz("03","0323","射线装置","SXZZ_"),
	yy_wghzrr("03","0324","网格化责任人","WGHZRR_"),
	yy_ssjgbm("03","0325","所属监管部门","SSJGBM_"),
	yy_qyzt("03","0326","企业状态","QYZT_"),
	yy_cjr("03","0327","创建人","CJR_"),
	yy_qysczt("03","0328","企业生产状态","QYSCZT_"),
	yy_tcrq("03","0329","投产日期","TCRQ_"),
	yy_yb("03","0330","邮政编码","YB_"),
	yy_qygm("03","0331","企业规模","QYGM_"),
	
	//锅炉
	gl_dwmc("04","0401","单位名称","DWMC_"),
	gl_ssxzq("04","0402","所属行政区","SSXZQ_"),
	gl_hy("04","0403","行业","HY_"),
	gl_fddbr("04","0404","法定代表人","FDDBR_"),
	gl_fddbrlxdh("04","0405","法定代表人联系电话","FDDBRLXDH_"),
	gl_hbfzr("04","0406","环保负责人","HBFZR_"),
	gl_hbfzrlxdh("04","0407","环保负责人联系电话","HBFZRLXDH_"),
	gl_jd("04","0408","经度","JD_"),
	gl_wd("04","0409","纬度","WD_"),
	
	gl_dz("04","0410","地址","DZ_"),
	gl_zzjgdm("04","0411","组织机构代码","ZZJGDM_"),
	gl_zt("04","0412","状态","ZT_"),
	gl_yyzzzch("04","0413","营业执照注册号","YYZZZCH_"),
	gl_pwxkz("04","0414","排污许可证","PWXKZ_"),
	gl_fspfkgs("04","0415","废水排放口个数","FSPFKGS_"),
	gl_fqpfkgs("04","0416","废气排放口个数","FQPFKGS_"),
	gl_zsygs("04","0417","噪声源个数","ZSYGS_"),
	gl_gfdfcgs("04","0418","固废堆放场个数","GFDFCGS_"),
	gl_wryfzsssl("04","0419","污染源防治设施数量","WRYFZSSSL_"),
	gl_gls("04","0420","锅炉数（台）","GLS_"),
	gl_yt("04","0421","用途","YT_"),
	gl_zd("04","0422","蒸吨/小时","ZD_"),
	gl_wghzrr("04","0423","网格化责任人","WGHZRR_"),
	gl_ssjgbm("04","0424","所属监管部门","SSJGBM_"),
	gl_qyzt("04","0425","企业状态","QYZT_"),
	gl_cjr("04","0426","创建人","CJR_"),
	gl_qysczt("04","0427","企业生产状态","QYSCZT_"),
	
	
	//建筑工地
	jzgd_sgxmmc("05","0501","施工项目名称","SGXMMC_"),
	jzgd_ssxzq("05","0502","所属行政区","SSXZQ_"),
	jzgd_hy("05","0503","行业","HY_"),
	jzgd_fddbr("05","0504","法定代表人","FDDBR_"),
	jzgd_fddbrlxdh("05","0505","法定代表人联系电话","FDDBRLXDH_"),
	jzgd_hbfzr("05","0506","环保负责人","HBFZR_"),
	jzgd_hbfzrlxdh("05","0507","环保负责人联系电话","HBFZRLXDH_"),
	jzgd_jd("05","0508","经度","JD_"),
	jzgd_wd("05","0509","纬度","WD_"),
	
	jzgd_lspwxkzbm("05","0510","临时排污许可证编码","LSPWXKZBM_"),
	jzgd_gcdd("05","0511","工程地点","GCDD_"),
	jzgd_sgdwmc("05","0512","施工单位名称","SGDWMC_"),
	jzgd_dz("05","0513","单位地址","DZ_"),
	jzgd_zt("05","0514","状态","ZT_"),
	jzgd_kgrq("05","0515","开工日期","KGRQ_"),
	jzgd_yjjgrq("05","0516","预计竣工日期","YJJGRQ_"),
	jzgd_zsygs("05","0517","噪声源个数","ZSYGS_"),
	jzgd_wryfzsssl("05","0518","污染源防治设施数量","WRYFZSSSL_"),
	jzgd_wghzrr("05","0519","网格化责任人","WGHZRR_"),
	jzgd_ssjgbm("05","0520","所属监管部门","SSJGBM_"),
	jzgd_qyzt("05","0521","企业状态","QYZT_"),
	jzgd_cjr("05","0522","创建人","CJR_"),
	jzgd_qysczt("05","0523","企业生产状态","QYSCZT_"),
	jzgd_jzgdzt("05","0524","建筑工地状态","JZGDZT_"),

	//三产
	sc_dwmc("06","0601","单位名称","DWMC_"),
	sc_ssxzq("06","0602","所属行政区","SSXZQ_"),
	sc_hy("06","0603","行业","HY_"),
	sc_fddbr("06","0604","经营者","FDDBR_"),
	sc_fddbrlxdh("06","0605","经营者联系电话","FDDBRLXDH_"),
	sc_hbfzr("06","0606","环保负责人","HBFZR_"),
	sc_hbfzrlxdh("06","0607","环保负责人联系电话","HBFZRLXDH_"),
	sc_jd("06","0608","经度","JD_"),
	sc_wd("06","0609","纬度","WD_"),
	
	sc_dz("06","0610","地址","DZ_"),
	sc_zt("06","0611","状态","ZT_"),
	sc_yyzzzch("06","0612","营业执照注册号","YYZZZCH_"),
	sc_pwxkz("06","0613","排污许可证","PWXKZ_"),
	sc_fspfkgs("06","0614","废水排放口个数","FSPFKGS_"),
	sc_fqpfkgs("06","0615","废气排放口个数","FQPFKGS_"),
	sc_zsygs("06","0616","噪声源个数","ZSYGS_"),
	sc_gfdfcgs("06","0617","固废堆放场个数","GFDFCGS_"),
	sc_wryfzsssl("06","0618","污染源防治设施数量","WRYFZSSSL_"),
	sc_wsxkz("06","0619","卫生许可证","WSXKZ_"),
	sc_wghzrr("06","0620","网格化责任人","WGHZRR_"),
	sc_ssjgbm("06","0621","所属监管部门","SSJGBM_"),
	sc_qyzt("06","0622","企业状态","QYZT_"),
	sc_cjr("06","0623","创建人","CJR_"),
	sc_qysczt("06","0624","企业生产状态","QYSCZT_"),
	sc_yb("06","0625","邮政编码","YB_"),
	
	//畜禽养殖
	xqyz_xqyzcmc("07","0701","畜禽养殖场名称","XQYZCMC_"),					
	xqyz_ssxzq("07","0702","所属行政区","SSXZQ_"),
	xqyz_fddbr("07","0703","法定代表人","FDDBR_"),
	xqyz_fddbrlxdh("07","0704","法定代表人联系电话","FDDBRLXDH_"),
	xqyz_hbfzr("07","0705","环保负责人","HBFZR_"),
	xqyz_hbfzrlxdh("07","0706","环保负责人联系电话","HBFZRLXDH_"),
	xqyz_jd("07","0707","经度","JD_"),
	xqyz_wd("07","0708","纬度","WD_"),
	
	xqyz_dz("07","0709","地址","DZ_"),
	xqyz_zt("07","0710","状态","ZT_"),
	xqyz_yyzzzch("07","0711","营业执照注册号","YYZZZCH_"),
	xqyz_pwxkz("07","0712","排污许可证","PWXKZ_"),
	xqyz_fspfkgs("07","0713","废水排放口个数","FSPFKGS_"),
	xqyz_fqpfkgs("07","0714","废气排放口个数","FQPFKGS_"),
	xqyz_zsygs("07","0715","噪声源个数","ZSYGS_"),
	xqyz_gfdfcgs("07","0716","固废堆放场个数","GFDFCGS_"),
	xqyz_wryfzsssl("07","0717","污染源防治设施数量","WRYFZSSSL_"),
	xqyz_yzmj("07","0718","养殖面积","YZMJ_"),		
	xqyz_cls("07","0719","存栏数","CLS_"),					
	xqyz_dwmc("07","0720","单位名称","DWMC_"),
	xqyz_zzjgdm("07","0721","组织机构代码","ZZJGDM_"),				
	xqyz_wghzrr("07","0722","网格化责任人","WGHZRR_"),
	xqyz_ssjgbm("07","0723","所属监管部门","SSJGBM_"),
	xqyz_qyzt("07","0724","企业状态","QYZT_"),
	xqyz_cjr("07","0725","创建人","CJR_"),
	xqyz_qysczt("07","0726","企业生产状态","QYSCZT_"),
	xqyz_tyshxydm("07","0727","统一社会信用代码","TYSHXYDM_"),
	xqyz_cll("07","0728","出栏量","CLL_"),
	xqyz_xqyzzl("07","0729","畜禽养殖种类","XQYZZL_"),
	
	//服务业（洗车、洗浴）
	fwy_dwmc("08","0801","单位名称","DWMC_"),
	fwy_ssxzq("08","0802","所属行政区","SSXZQ_"),
	fwy_fddbr("08","0803","法定代表人","FDDBR_"),
	fwy_fddbrlxdh("08","0804","法定代表人联系电话","FDDBRLXDH_"),
	fwy_hbfzr("08","0805","环保负责人","HBFZR_"),
	fwy_hbfzrlxdh("08","0806","环保负责人联系电话","HBFZRLXDH_"),
	fwy_jd("08","0807","经度","JD_"),
	fwy_wd("08","0808","纬度","WD_"),
	
	fwy_dz("08","0809","地址","DZ_"),
	fwy_zt("08","0810","状态","ZT_"),
	fwy_yyzzzch("08","0811","营业执照注册号","YYZZZCH_"),
	fwy_fzsj("08","0812","发证时间","FZSJ_"),
	fwy_ds("08","0813","底商","DS_"),
	fwy_wyjc("08","0814","位于几层","WYJC_"),
	fwy_zz("08","0815","住宅","ZZ_"),
	fwy_sf("08","0816","商服","SF_"),
	fwy_zwhj("08","0817","周围环境","ZWHJ_"),
	fwy_mj("08","0818","面积㎡","MJ_"),
	fwy_fj("08","0819","房间","FJ_"),
	fwy_ry("08","0820","人员","RY_"),
	fwy_gz("08","0821","柜子","GZ_"),
	fwy_lyt("08","0822","淋浴头","LYT_"),
	fwy_trq("08","0823","天然气","TRQ_"),
	fwy_gl("08","0824","锅炉（台/吨）","GL_"),
	fwy_drsq("08","0825","电热水器","DRSQ_"),
	fwy_cw("08","0826","床位","CW_"),
	fwy_kt("08","0827","空调","KT_"),
	fwy_djgngl("08","0828","锅炉（冬季供暖）","DJGNGL_"),
	fwy_jzgr("08","0829","集中供热","JZGR_"),
	fwy_xyj("08","0830","洗车机","XYJ_"),
	fwy_pmj("08","0831","泡沫机","PMJ_"),
	fwy_sgj("08","0832","甩干机","SGJ_"),
	fwy_ssjgbm("08","0833","所属监管部门","SSJGBM_"),
	fwy_qyzt("08","0834","企业状态","QYZT_"),
	fwy_bz("08","0835","备注","BZ_"),
	fwy_cjr("08","0836","创建人","CJR_"),
	fwy_qysczt("08","0837","企业生产状态","QYSCZT_"),
	fwy_hy("08","0838","行业","HY_"),
	
	//饮食业
	ysy_dwmc("09","0901","单位名称","DWMC_"),
	ysy_ssxzq("09","0902","所属行政区","SSXZQ_"),
	ysy_fddbr("09","0903","法定代表人","FDDBR_"),
	ysy_fddbrlxdh("09","0904","法定代表人联系电话","FDDBRLXDH_"),
	ysy_hbfzr("09","0905","环保负责人","HBFZR_"),
	ysy_hbfzrlxdh("09","0906","环保负责人联系电话","HBFZRLXDH_"),
	ysy_jd("09","0907","经度","JD_"),
	ysy_wd("09","0908","纬度","WD_"),
	
	ysy_dz("09","0909","地址","DZ_"),
	ysy_zt("09","0910","状态","ZT_"),
	ysy_yyzzzch("09","0911","营业执照注册号","YYZZZCH_"),
	ysy_fzsj("09","0912","发证时间","FZSJ_"),
	ysy_ds("09","0913","底商","DS_"),
	ysy_wyjc("09","0914","位于几层","WYJC_"),
	ysy_zhz("09","0915","住宅","ZHZ_"),
	ysy_sf("09","0916","商服","SF_"),
	ysy_zwhj("09","0917","周围环境","ZWHJ_"),
	ysy_mj("09","0918","面积㎡","MJ_"),
	ysy_zz("09","0919","桌子","ZZ_"),
	ysy_yz("09","0920","椅子","YZ_"),
	ysy_ry("09","0921","人员","RY_"),
	ysy_bx("09","0922","包厢","BX_"),
	ysy_trq("09","0923","天然气","TRQ_"),
	ysy_yhq("09","0924","液化气","YHQ_"),
	ysy_m("09","0925","煤","M_"),
	ysy_zy("09","0926","灶眼","ZY_"),
	ysy_zbj("09","0927","制冰机","ZBJ_"),
	ysy_lnjz("09","0928","冷凝机组","LNJZ_"),
	ysy_hmj("09","0929","和面机","HMJ_"),
	ysy_djj("09","0930","豆浆机","DJJ_"),
	ysy_pqfj("09","0931","排气风机（台）","PQFJ_"),
	ysy_yypfqk("09","0932","油烟排放情况","YYPFQK_"),
	ysy_yrfzssazsj("09","0933","污染防治设施安装时间","YRFZSSAZSJ_"),
	ysy_sfzcyx("09","0934","是否正常运行","SFZCYX_"),
	ysy_pyygdsfccld("09","0935","排油烟管道是否超出楼顶","PYYGDSFCCLD_"),
	ysy_cj("09","0936","厂家","CJ_"),
	ysy_fl("09","0937","风量","FL_"),
	ysy_qxsj("09","0938","清洗时间","QXSJ_"),
	ysy_wxsj("09","0939","维修时间","WXSJ_"),
	ysy_ssjgbm("09","0940","所属监管部门","SSJGBM_"),
	ysy_qyzt("09","0941","企业状态","QYZT_"),
	ysy_bz("09","0942","备注","BZ_"),
	ysy_cjr("09","0943","创建人","CJR_"),
	ysy_qysczt("09","0944","企业生产状态","QYSCZT_"),
	
	//三产制造业
	zzy_dwmc("10","1001","单位名称","DWMC_"),
	zzy_ssxzq("10","1002","所属行政区","SSXZQ_"),
	zzy_fddbr("10","1003","法定代表人","FDDBR_"),
	zzy_fddbrlxdh("10","1004","法定代表人联系电话","FDDBRLXDH_"),
	zzy_hbfzr("10","1005","环保负责人","HBFZR_"),
	zzy_hbfzrlxdh("10","1006","环保负责人联系电话","HBFZRLXDH_"),
	zzy_jd("10","1007","经度","JD_"),
	zzy_wd("10","1008","纬度","WD_"),
	
	zzy_dzh("10","1009","地址","DZH_"),
	zzy_zt("10","1010","状态","ZT_"),
	zzy_yyzzzch("10","1011","营业执照注册号","YYZZZCH_"),
	zzy_fzsj("10","1012","发证时间","FZSJ_"),
	zzy_ds("10","1013","底商","DS_"),
	zzy_wyjc("10","1014","位于几层","WYJC_"),
	zzy_zz("10","1015","住宅","ZZ_"),
	zzy_sf("10","1016","商服","SF_"),
	zzy_zwhj("10","1017","周围环境","ZWHJ_"),
	zzy_mj("10","1018","面积㎡","MJ_"),
	zzy_qgj("10","1019","切割机","QGJ_"),
	zzy_jmj("10","1020","角磨机","JMJ_"),
	zzy_dz("10","1021","电钻","DZ_"),
	zzy_dj("10","1022","电锯","DJ_"),
	zzy_djq("10","1023","断剪钳","DJQ_"),
	zzy_qzj("10","1024","去籽机","QZJ_"),
	zzy_dbj("10","1025","打包机","DBJ_"),
	zzy_pmj("10","1026","平磨机","PMJ_"),
	zzy_dkj("10","1027","雕刻机","DKJ_"),
	zzy_zzj("10","1028","镯子机","ZZJ_"),
	zzy_pgj("10","1029","抛光机","PGJ_"),
	zzy_db("10","1030","电刨","DB_"),
	zzy_gsjzcs("10","1031","隔声降噪措施","GSJZCS_"),
	zzy_zzdd("10","1032","制作地点","ZZDD_"),
	zzy_psyq("10","1033","喷、刷油漆","PSYQ_"),
	zzy_ssjgbm("10","1034","所属监管部门","SSJGBM_"),
	zzy_qyzt("10","1035","企业状态","QYZT_"),
	zzy_bz("10","1036","备注","BZ_"),
	zzy_cjr("10","1037","创建人","CJR_"),
	zzy_qysczt("10","1038","企业生产状态","QYSCZT_"),
	
	//娱乐业
	yly_dwmc("11","1101","单位名称","DWMC_"),
	yly_ssxzq("11","1102","所属行政区","SSXZQ_"),
	yly_fddbr("11","1103","法定代表人","FDDBR_"),
	yly_fddbrlxdh("11","1104","法定代表人联系电话","FDDBRLXDH_"),
	yly_hbfzr("11","1105","环保负责人","HBFZR_"),
	yly_hbfzrlxdh("11","1106","环保负责人联系电话","HBFZRLXDH_"),
	yly_jd("11","1107","经度","JD_"),
	yly_wd("11","1108","纬度","WD_"),
	
	yly_dz("11","1109","地址","DZ_"),
	yly_zt("11","1110","状态","ZT_"),
	yly_yyzzzch("11","1111","营业执照注册号","YYZZZCH_"),
	yly_fzsj("11","1112","发证时间","FZSJ_"),
	yly_ds("11","1113","底商","DS_"),
	yly_wyjc("11","1114","位于几层","WYJC_"),
	yly_zz("11","1115","住宅","ZZ_"),
	yly_sf("11","1116","商服","SF_"),
	yly_zwhj("11","1117","周围环境","ZWHJ_"),
	yly_mj("11","1118","面积㎡","MJ_"),
	yly_bxs("11","1119","包厢数","BXS_"),
	yly_kts("11","1120","空调数","KTS_"),
	yly_yx("11","1121","音响","YX_"),
	yly_dn("11","1122","电脑","DN_"),
	yly_yxj("11","1123","游戏机","YXJ_"),
	yly_pqs("11","1124","排气扇","PQS_"),
	yly_pfk("11","1125","排放口","PFK_"),
	yly_ry("11","1126","人员","RY_"),
	yly_zw("11","1127","座位","ZW_"),
	yly_xftdckcs("11","1128","消防通道出口措施","XFTDCKCS_"),
	yly_gyjzcs("11","1129","窗户、墙面及屋顶是否采取有效隔音降噪措施","GYJZCS_"),
	yly_gyclzl("11","1130","所使用的隔音材料种类","GYCLZL_"),
	yly_ssjgbm("11","1133","所属监管部门","SSJGBM_"),
	yly_qyzt("11","1134","企业状态","QYZT_"),
	yly_bz("11","1135","备注","BZ_"),
	yly_cjr("11","1136","创建人","CJR_"),
	yly_qysczt("11","1137","企业生产状态","QYSCZT_"),
	;
	
	
	private LawobjOutColunmEnum(String type,String code,String name,String simpleName){
		this.type = type;
		this.code = code;
		this.name = name;
		this.simpleName = simpleName;
	}
	
	private String type;//执法对象类型编号
	private String code;
	private String name;
	private String simpleName;
	
	
	
	/**
	 * 
	 * 函数介绍：通过执法对象类型获得 执法对象字段对外名称列表
	
	 * 输入参数：执法对象类型
	
	 * 返回值：
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
	 * 函数介绍：通过编号获取对象
	
	 * 输入参数：
	
	 * 返回值：
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
