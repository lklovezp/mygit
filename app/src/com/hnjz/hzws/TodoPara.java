
package com.hnjz.hzws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for todoPara complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="todoPara">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alluserid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oderby" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="para" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tenantid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="where" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "todoPara", propOrder = {
    "alluserid",
    "flowid",
    "identifier",
    "oderby",
    "pageNum",
    "pageSize",
    "para",
    "tenantid",
    "userid",
    "where"
})
public class TodoPara {

    protected String alluserid;
    protected String flowid;
    protected String identifier;
    protected String oderby;
    protected int pageNum;
    protected int pageSize;
    @XmlElement(nillable = true)
    protected List<String> para;
    protected String tenantid;
    protected String userid;
    protected String where;

    /**
     * Gets the value of the alluserid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlluserid() {
        return alluserid;
    }

    /**
     * Sets the value of the alluserid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlluserid(String value) {
        this.alluserid = value;
    }

    /**
     * Gets the value of the flowid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowid() {
        return flowid;
    }

    /**
     * Sets the value of the flowid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowid(String value) {
        this.flowid = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the oderby property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOderby() {
        return oderby;
    }

    /**
     * Sets the value of the oderby property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOderby(String value) {
        this.oderby = value;
    }

    /**
     * Gets the value of the pageNum property.
     * 
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Sets the value of the pageNum property.
     * 
     */
    public void setPageNum(int value) {
        this.pageNum = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the para property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the para property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPara().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPara() {
        if (para == null) {
            para = new ArrayList<String>();
        }
        return this.para;
    }

    /**
     * Gets the value of the tenantid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantid() {
        return tenantid;
    }

    /**
     * Sets the value of the tenantid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantid(String value) {
        this.tenantid = value;
    }

    /**
     * Gets the value of the userid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Sets the value of the userid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserid(String value) {
        this.userid = value;
    }

    /**
     * Gets the value of the where property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhere() {
        return where;
    }

    /**
     * Sets the value of the where property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhere(String value) {
        this.where = value;
    }

}
