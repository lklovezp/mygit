
package com.hnjz.hzws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for authUserImpl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="authUserImpl">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.wf.horizon.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="agentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isclaim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authUserImpl", propOrder = {
    "agentId",
    "authId",
    "authType",
    "isclaim",
    "subjectionId",
    "subjectionType"
})
@XmlSeeAlso({
    DbTodo.class
})
public class AuthUserImpl
    extends BaseEntity
{

    protected String agentId;
    protected String authId;
    protected String authType;
    protected String isclaim;
    protected String subjectionId;
    protected String subjectionType;

    /**
     * Gets the value of the agentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * Sets the value of the agentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentId(String value) {
        this.agentId = value;
    }

    /**
     * Gets the value of the authId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * Sets the value of the authId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthId(String value) {
        this.authId = value;
    }

    /**
     * Gets the value of the authType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * Sets the value of the authType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthType(String value) {
        this.authType = value;
    }

    /**
     * Gets the value of the isclaim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsclaim() {
        return isclaim;
    }

    /**
     * Sets the value of the isclaim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsclaim(String value) {
        this.isclaim = value;
    }

    /**
     * Gets the value of the subjectionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjectionId() {
        return subjectionId;
    }

    /**
     * Sets the value of the subjectionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectionId(String value) {
        this.subjectionId = value;
    }

    /**
     * Gets the value of the subjectionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjectionType() {
        return subjectionType;
    }

    /**
     * Sets the value of the subjectionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectionType(String value) {
        this.subjectionType = value;
    }

}
