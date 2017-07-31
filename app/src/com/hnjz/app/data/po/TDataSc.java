package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.sys.po.BaseObject;

/**
 * 
 * 作者：XUYAXING
 * 生成日期：
 * 功能描述：
 *
 */
@SuppressWarnings("serial")
public class TDataSc extends BaseObject {



      
		private String  id;
       
        
        
        public String getId(){
          return id;
           }
        
          public void setId(String  id){
           this.id=id;
             }
        
       	private TDataLawobjf  lawobjf;


      		public TDataLawobjf getLawobjf() {
      			return lawobjf;
      		}

      		public void setLawobjf(TDataLawobjf lawobjf) {
      			this.lawobjf = lawobjf;
      		}
      
      
		private String  pwxkz;
       
        
        
        public String getPwxkz(){
          return pwxkz;
           }
        
          public void setPwxkz(String  pwxkz){
           this.pwxkz=pwxkz;
             }
        
      
      
		private String  wryfzsssl;
       
        
        
        public String getWryfzsssl(){
          return wryfzsssl;
           }
        
          public void setWryfzsssl(String  wryfzsssl){
           this.wryfzsssl=wryfzsssl;
             }
        
      
      
		private String  yzbm;
       
        
        
        public String getYzbm(){
          return yzbm;
           }
        
          public void setYzbm(String  yzbm){
           this.yzbm=yzbm;
             }
        
      
      
		private String  wsxkz;
       
        
        
        public String getWsxkz(){
          return wsxkz;
           }
        
          public void setWsxkz(String  wsxkz){
           this.wsxkz=wsxkz;
             }
        
          private Date updated;



      	public Date getUpdated() {
      		return updated;
      	}

      	public void setUpdated(Date updated) {
      		this.updated = updated;
      	}
      	
	
}












