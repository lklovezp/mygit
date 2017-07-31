
package com.hnjz.hzws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dbTask complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dbTask">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.wf.horizon.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="dooverpass" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="flowid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowlimitdate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="flowlimittime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowlimittype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowstatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nodeid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodelimitdate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nodelimittime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodelimittype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodetype" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="receivetime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redonenum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remsgnum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="starttime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trackid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waringdate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="waringtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "dbTask", propOrder = {
    "dooverpass",
    "flowid",
    "flowlimitdate",
    "flowlimittime",
    "flowlimittype",
    "flowname",
    "flowstatus",
    "nodeid",
    "nodelimitdate",
    "nodelimittime",
    "nodelimittype",
    "nodename",
    "nodetype",
    "receivetime",
    "redonenum",
    "remsgnum",
    "starttime",
    "status",
    "title",
    "trackid",
    "waringdate",
    "waringtype",
    "workid"
})
public class DbTask
    extends BaseEntity
{

    protected int dooverpass;
    protected String flowid;
    protected int flowlimitdate;
    protected String flowlimittime;
    protected String flowlimittype;
    protected String flowname;
    protected int flowstatus;
    protected String nodeid;
    protected int nodelimitdate;
    protected String nodelimittime;
    protected String nodelimittype;
    protected String nodename;
    protected int nodetype;
    protected String receivetime;
    protected int redonenum;
    protected int remsgnum;
    protected String starttime;
    protected int status;
    protected String title;
    protected String trackid;
    protected int waringdate;
    protected String waringtype;
    protected String workid;

    /**
     * Gets the value of the dooverpass property.
     * 
     */
    public int getDooverpass() {
        return dooverpass;
    }

    /**
     * Sets the value of the dooverpass property.
     * 
     */
    public void setDooverpass(int value) {
        this.dooverpass = value;
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
     * Gets the value of the flowlimitdate property.
     * 
     */
    public int getFlowlimitdate() {
        return flowlimitdate;
    }

    /**
     * Sets the value of the flowlimitdate property.
     * 
     */
    public void setFlowlimitdate(int value) {
        this.flowlimitdate = value;
    }

    /**
     * Gets the value of the flowlimittime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowlimittime() {
        return flowlimittime;
    }

    /**
     * Sets the value of the flowlimittime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowlimittime(String value) {
        this.flowlimittime = value;
    }

    /**
     * Gets the value of the flowlimittype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowlimittype() {
        return flowlimittype;
    }

    /**
     * Sets the value of the flowlimittype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowlimittype(String value) {
        this.flowlimittype = value;
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
     * Gets the value of the flowstatus property.
     * 
     */
    public int getFlowstatus() {
        return flowstatus;
    }

    /**
     * Sets the value of the flowstatus property.
     * 
     */
    public void setFlowstatus(int value) {
        this.flowstatus = value;
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
     * Gets the value of the nodelimitdate property.
     * 
     */
    public int getNodelimitdate() {
        return nodelimitdate;
    }

    /**
     * Sets the value of the nodelimitdate property.
     * 
     */
    public void setNodelimitdate(int value) {
        this.nodelimitdate = value;
    }

    /**
     * Gets the value of the nodelimittime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodelimittime() {
        return nodelimittime;
    }

    /**
     * Sets the value of the nodelimittime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodelimittime(String value) {
        this.nodelimittime = value;
    }

    /**
     * Gets the value of the nodelimittype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodelimittype() {
        return nodelimittype;
    }

    /**
     * Sets the value of the nodelimittype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodelimittype(String value) {
        this.nodelimittype = value;
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
     * Gets the value of the nodetype property.
     * 
     */
    public int getNodetype() {
        return nodetype;
    }

    /**
     * Sets the value of the nodetype property.
     * 
     */
    public void setNodetype(int value) {
        this.nodetype = value;
    }

    /**
     * Gets the value of the receivetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivetime() {
        return receivetime;
    }

    /**
     * Sets the value of the receivetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivetime(String value) {
        this.receivetime = value;
    }

    /**
     * Gets the value of the redonenum property.
     * 
     */
    public int getRedonenum() {
        return redonenum;
    }

    /**
     * Sets the value of the redonenum property.
     * 
     */
    public void setRedonenum(int value) {
        this.redonenum = value;
    }

    /**
     * Gets the value of the remsgnum property.
     * 
     */
    public int getRemsgnum() {
        return remsgnum;
    }

    /**
     * Sets the value of the remsgnum property.
     * 
     */
    public void setRemsgnum(int value) {
        this.remsgnum = value;
    }

    /**
     * Gets the value of the starttime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * Sets the value of the starttime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStarttime(String value) {
        this.starttime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
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
     * Gets the value of the waringdate property.
     * 
     */
    public int getWaringdate() {
        return waringdate;
    }

    /**
     * Sets the value of the waringdate property.
     * 
     */
    public void setWaringdate(int value) {
        this.waringdate = value;
    }

    /**
     * Gets the value of the waringtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaringtype() {
        return waringtype;
    }

    /**
     * Sets the value of the waringtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaringtype(String value) {
        this.waringtype = value;
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
