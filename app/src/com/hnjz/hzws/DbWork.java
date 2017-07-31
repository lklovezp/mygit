
package com.hnjz.hzws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dbWork complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dbWork">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.wf.horizon.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleteVer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="flowid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flowver" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parentworkid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saveVer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="starttime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trackxml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trackxmlid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="workid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workxml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dbWork", propOrder = {
    "created",
    "creator",
    "deleteVer",
    "flowid",
    "flowname",
    "flowver",
    "parentworkid",
    "saveVer",
    "starttime",
    "title",
    "trackxml",
    "trackxmlid",
    "version",
    "workid",
    "workxml",
    "xmlid"
})
public class DbWork
    extends BaseEntity
{

    protected String created;
    protected String creator;
    protected boolean deleteVer;
    protected String flowid;
    protected String flowname;
    protected int flowver;
    protected String parentworkid;
    protected boolean saveVer;
    protected String starttime;
    protected String title;
    protected String trackxml;
    protected String trackxmlid;
    protected int version;
    protected String workid;
    protected String workxml;
    protected String xmlid;

    /**
     * Gets the value of the created property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreated(String value) {
        this.created = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreator(String value) {
        this.creator = value;
    }

    /**
     * Gets the value of the deleteVer property.
     * 
     */
    public boolean isDeleteVer() {
        return deleteVer;
    }

    /**
     * Sets the value of the deleteVer property.
     * 
     */
    public void setDeleteVer(boolean value) {
        this.deleteVer = value;
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
     * Gets the value of the flowver property.
     * 
     */
    public int getFlowver() {
        return flowver;
    }

    /**
     * Sets the value of the flowver property.
     * 
     */
    public void setFlowver(int value) {
        this.flowver = value;
    }

    /**
     * Gets the value of the parentworkid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentworkid() {
        return parentworkid;
    }

    /**
     * Sets the value of the parentworkid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentworkid(String value) {
        this.parentworkid = value;
    }

    /**
     * Gets the value of the saveVer property.
     * 
     */
    public boolean isSaveVer() {
        return saveVer;
    }

    /**
     * Sets the value of the saveVer property.
     * 
     */
    public void setSaveVer(boolean value) {
        this.saveVer = value;
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
     * Gets the value of the trackxml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackxml() {
        return trackxml;
    }

    /**
     * Sets the value of the trackxml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackxml(String value) {
        this.trackxml = value;
    }

    /**
     * Gets the value of the trackxmlid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackxmlid() {
        return trackxmlid;
    }

    /**
     * Sets the value of the trackxmlid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackxmlid(String value) {
        this.trackxmlid = value;
    }

    /**
     * Gets the value of the version property.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
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

    /**
     * Gets the value of the workxml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkxml() {
        return workxml;
    }

    /**
     * Sets the value of the workxml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkxml(String value) {
        this.workxml = value;
    }

    /**
     * Gets the value of the xmlid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlid() {
        return xmlid;
    }

    /**
     * Sets the value of the xmlid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlid(String value) {
        this.xmlid = value;
    }

}
