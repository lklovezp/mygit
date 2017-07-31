
package com.hnjz.hzws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dbLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dbLog">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.wf.horizon.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actiontime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dotime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dotimemin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isover" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limittime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nextnodeids" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sxcomments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trackid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dbLog", propOrder = {
    "action",
    "actionname",
    "actiontime",
    "comments",
    "dotime",
    "dotimemin",
    "flowid",
    "flowname",
    "isover",
    "limittime",
    "memo",
    "nextnodeids",
    "nodeid",
    "nodename",
    "sxcomments",
    "title",
    "trackid",
    "userid",
    "username",
    "workid"
})
public class DbLog
    extends BaseEntity
{

    protected String action;
    protected String actionname;
    protected String actiontime;
    protected String comments;
    protected String dotime;
    protected String dotimemin;
    protected String flowid;
    protected String flowname;
    protected String isover;
    protected String limittime;
    protected String memo;
    protected String nextnodeids;
    protected String nodeid;
    protected String nodename;
    protected String sxcomments;
    protected String title;
    protected String trackid;
    protected String userid;
    protected String username;
    protected String workid;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the actionname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionname() {
        return actionname;
    }

    /**
     * Sets the value of the actionname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionname(String value) {
        this.actionname = value;
    }

    /**
     * Gets the value of the actiontime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiontime() {
        return actiontime;
    }

    /**
     * Sets the value of the actiontime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiontime(String value) {
        this.actiontime = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Gets the value of the dotime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDotime() {
        return dotime;
    }

    /**
     * Sets the value of the dotime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDotime(String value) {
        this.dotime = value;
    }

    /**
     * Gets the value of the dotimemin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDotimemin() {
        return dotimemin;
    }

    /**
     * Sets the value of the dotimemin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDotimemin(String value) {
        this.dotimemin = value;
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
     * Gets the value of the flowname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowname() {
        return flowname;
    }

    /**
     * Sets the value of the flowname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowname(String value) {
        this.flowname = value;
    }

    /**
     * Gets the value of the isover property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsover() {
        return isover;
    }

    /**
     * Sets the value of the isover property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsover(String value) {
        this.isover = value;
    }

    /**
     * Gets the value of the limittime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimittime() {
        return limittime;
    }

    /**
     * Sets the value of the limittime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimittime(String value) {
        this.limittime = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * Gets the value of the nextnodeids property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextnodeids() {
        return nextnodeids;
    }

    /**
     * Sets the value of the nextnodeids property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextnodeids(String value) {
        this.nextnodeids = value;
    }

    /**
     * Gets the value of the nodeid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeid() {
        return nodeid;
    }

    /**
     * Sets the value of the nodeid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeid(String value) {
        this.nodeid = value;
    }

    /**
     * Gets the value of the nodename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodename() {
        return nodename;
    }

    /**
     * Sets the value of the nodename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodename(String value) {
        this.nodename = value;
    }

    /**
     * Gets the value of the sxcomments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSxcomments() {
        return sxcomments;
    }

    /**
     * Sets the value of the sxcomments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSxcomments(String value) {
        this.sxcomments = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the trackid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackid() {
        return trackid;
    }

    /**
     * Sets the value of the trackid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackid(String value) {
        this.trackid = value;
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
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the workid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkid() {
        return workid;
    }

    /**
     * Sets the value of the workid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkid(String value) {
        this.workid = value;
    }

}
