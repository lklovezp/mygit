package com.hnjz.app.work.po;

import java.util.Date;
import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKINVERECORD ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:03 CST 2015
 * ����������ѯ�ʱ�¼
 */ 

@SuppressWarnings("serial")
public class TBizTaskinverecord extends BaseObject {
	/** ����ID */
	private String taskid;
	/** ѯ�ʿ�ʼʱ�� */
	private Date starttime;
	/** ѯ�ʽ���ʱ�� */
	private Date endtime;
	/** ѯ�ʵص� */
	private String xwdd;
	/** ��ѯ�ʵ�λ���� */
	private String bxwdwmc;
	/** ���������� */
	private String fddbr;
	/** ���������˵绰 */
	private String fddbrdh;
	/** ��ѯ�������� */
	private String bxwrxm;
	/** ��ѯ�����Ա� */
	private String bxwrxb;
	/** ��ѯ�������� */
	private String bxwrnl;
	/** ��ѯ����ְ�� */
	private String bxwrzw;
	/** ��ѯ���˵绰 */
	private String bxwrdh;
	/** ��ѯ����Ȼ������ */
	private String bxwzrrxm;
	/** ��ѯ����Ȼ���Ա� */
	private String bxwzrrxb;
	/** ��ѯ����Ȼ������ */
	private String bxwzrrnl;
	/** ��ѯ����Ȼ�˵绰 */
	private String bxwzrrdh;
	/** ��ѯ����Ȼ�����ڵ�λ */
	private String bxwzrrszdw;
	/** ��ѯ����Ȼ��סַ */
	private String bxwzrrzz;
	/** ��ѯ����Ȼ���뱾����ϵ */
	private String bxwzrrybagx;
	/** ִ����Ա��λ���� */
	private String zfrydwmc;
	/** ִ����Ա���� */
	private String zfrynames;
	/** ִ��֤�� */
	private String zfzhs;
	
	/** ��������������֤�� */
	private String fddbrsfzh;
	/** ��ѯ��������֤�� */
	private String bxwrsfzh;
	/** ��ѯ����Ȼ������֤�� */
	private String bxwzrrsfzh;
	
	/** ���ѯ�ʵĴ� */
	private String lastans;

	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public void setStarttime(Date starttime){
		this.starttime = starttime;
	}
	public Date getStarttime(){
		return starttime;
	}
	public void setEndtime(Date endtime){
		this.endtime = endtime;
	}
	public Date getEndtime(){
		return endtime;
	}
	public void setXwdd(String xwdd){
		this.xwdd = xwdd;
	}
	public String getXwdd(){
		return xwdd;
	}
	public void setBxwdwmc(String bxwdwmc){
		this.bxwdwmc = bxwdwmc;
	}
	public String getBxwdwmc(){
		return bxwdwmc;
	}
	public void setFddbr(String fddbr){
		this.fddbr = fddbr;
	}
	public String getFddbr(){
		return fddbr;
	}
	public void setFddbrdh(String fddbrdh){
		this.fddbrdh = fddbrdh;
	}
	public String getFddbrdh(){
		return fddbrdh;
	}
	public void setBxwrxm(String bxwrxm){
		this.bxwrxm = bxwrxm;
	}
	public String getBxwrxm(){
		return bxwrxm;
	}
	public void setBxwrxb(String bxwrxb){
		this.bxwrxb = bxwrxb;
	}
	public String getBxwrxb(){
		return bxwrxb;
	}
	public void setBxwrnl(String bxwrnl){
		this.bxwrnl = bxwrnl;
	}
	public String getBxwrnl(){
		return bxwrnl;
	}
	public void setBxwrzw(String bxwrzw){
		this.bxwrzw = bxwrzw;
	}
	public String getBxwrzw(){
		return bxwrzw;
	}
	public void setBxwrdh(String bxwrdh){
		this.bxwrdh = bxwrdh;
	}
	public String getBxwrdh(){
		return bxwrdh;
	}
	public void setBxwzrrxm(String bxwzrrxm){
		this.bxwzrrxm = bxwzrrxm;
	}
	public String getBxwzrrxm(){
		return bxwzrrxm;
	}
	public void setBxwzrrxb(String bxwzrrxb){
		this.bxwzrrxb = bxwzrrxb;
	}
	public String getBxwzrrxb(){
		return bxwzrrxb;
	}
	public void setBxwzrrnl(String bxwzrrnl){
		this.bxwzrrnl = bxwzrrnl;
	}
	public String getBxwzrrnl(){
		return bxwzrrnl;
	}
	public void setBxwzrrdh(String bxwzrrdh){
		this.bxwzrrdh = bxwzrrdh;
	}
	public String getBxwzrrdh(){
		return bxwzrrdh;
	}
	public void setBxwzrrszdw(String bxwzrrszdw){
		this.bxwzrrszdw = bxwzrrszdw;
	}
	public String getBxwzrrszdw(){
		return bxwzrrszdw;
	}
	public void setBxwzrrzz(String bxwzrrzz){
		this.bxwzrrzz = bxwzrrzz;
	}
	public String getBxwzrrzz(){
		return bxwzrrzz;
	}
	public void setBxwzrrybagx(String bxwzrrybagx){
		this.bxwzrrybagx = bxwzrrybagx;
	}
	public String getBxwzrrybagx(){
		return bxwzrrybagx;
	}
	public void setZfrydwmc(String zfrydwmc){
		this.zfrydwmc = zfrydwmc;
	}
	public String getZfrydwmc(){
		return zfrydwmc;
	}
	public void setZfrynames(String zfrynames){
		this.zfrynames = zfrynames;
	}
	public String getZfrynames(){
		return zfrynames;
	}
	public void setZfzhs(String zfzhs){
		this.zfzhs = zfzhs;
	}
	public String getZfzhs(){
		return zfzhs;
	}
	public String getFddbrsfzh() {
		return fddbrsfzh;
	}
	public void setFddbrsfzh(String fddbrsfzh) {
		this.fddbrsfzh = fddbrsfzh;
	}
	public String getBxwrsfzh() {
		return bxwrsfzh;
	}
	public void setBxwrsfzh(String bxwrsfzh) {
		this.bxwrsfzh = bxwrsfzh;
	}
	public String getBxwzrrsfzh() {
		return bxwzrrsfzh;
	}
	public void setBxwzrrsfzh(String bxwzrrsfzh) {
		this.bxwzrrsfzh = bxwzrrsfzh;
	}
	public String getLastans() {
		return lastans;
	}
	public void setLastans(String lastans) {
		this.lastans = lastans;
	}
	
}
