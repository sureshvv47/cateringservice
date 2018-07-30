//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.30 at 11:51:34 PM IST 
//


package in.benchresources.cdm.pwdquestionaries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pwdQuestionarieId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="question1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="answer1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="question2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="answer2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pwdQuestionarieId",
    "userId",
    "username",
    "email",
    "question1",
    "answer1",
    "question2",
    "answer2",
    "createdDate",
    "lastUpdatedDate"
})
@XmlRootElement(name = "pwdQuestionariesType")
public class PwdQuestionariesType {

    protected int pwdQuestionarieId;
    protected int userId;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String question1;
    @XmlElement(required = true)
    protected String answer1;
    @XmlElement(required = true)
    protected String question2;
    @XmlElement(required = true)
    protected String answer2;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastUpdatedDate;

    /**
     * Gets the value of the pwdQuestionarieId property.
     * 
     */
    public int getPwdQuestionarieId() {
        return pwdQuestionarieId;
    }

    /**
     * Sets the value of the pwdQuestionarieId property.
     * 
     */
    public void setPwdQuestionarieId(int value) {
        this.pwdQuestionarieId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the question1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestion1() {
        return question1;
    }

    /**
     * Sets the value of the question1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestion1(String value) {
        this.question1 = value;
    }

    /**
     * Gets the value of the answer1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * Sets the value of the answer1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswer1(String value) {
        this.answer1 = value;
    }

    /**
     * Gets the value of the question2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestion2() {
        return question2;
    }

    /**
     * Sets the value of the question2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestion2(String value) {
        this.question2 = value;
    }

    /**
     * Gets the value of the answer2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * Sets the value of the answer2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswer2(String value) {
        this.answer2 = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the lastUpdatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Sets the value of the lastUpdatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdatedDate(XMLGregorianCalendar value) {
        this.lastUpdatedDate = value;
    }

}
