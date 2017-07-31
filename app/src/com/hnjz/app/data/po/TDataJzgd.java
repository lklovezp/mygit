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
public class TDataJzgd extends BaseObject {


      
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
        
      
      
		private String  wryfzsssl;
       
        
        
        public String getWryfzsssl(){
          return wryfzsssl;
           }
        
          public void setWryfzsssl(String  wryfzsssl){
           this.wryfzsssl=wryfzsssl;
             }
        
      
      
		private String  jzgdzt;
       
        
        
        public String getJzgdzt(){
          return jzgdzt;
           }
        
          public void setJzgdzt(String  jzgdzt){
           this.jzgdzt=jzgdzt;
             }
        
      
      
		private Date  yjjgtrq;
       
        
        
        
        
      
      
		private Date  kgrq;
       
        
       
      
      
		public Date getYjjgtrq() {
			return yjjgtrq;
		}

		public void setYjjgtrq(Date yjjgtrq) {
			this.yjjgtrq = yjjgtrq;
		}

		public Date getKgrq() {
			return kgrq;
		}

		public void setKgrq(Date kgrq) {
			this.kgrq = kgrq;
		}



		private String  lspwxkzbm;
       
        
        
        public String getLspwxkzbm(){
          return lspwxkzbm;
           }
        
          public void setLspwxkzbm(String  lspwxkzbm){
           this.lspwxkzbm=lspwxkzbm;
             }
        
          private Date updated;



      	public Date getUpdated() {
      		return updated;
      	}

      	public void setUpdated(Date updated) {
      		this.updated = updated;
      	}
      	
	
}












