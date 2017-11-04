import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * This class is responsible for the <code>Email</code> object. This class stores all the
 * information associated with that Email.
 *
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Email implements Serializable {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timeStamp;


    /**
     * This returns an instance of the <code>Email</code> object and sets all the properties
     * to the following parameters that are passed through.
     *
     * @param recipent
     *      The Recipient of the Email
     * @param CC
     *      The person that is getting a carbon copy of the email
     * @param bCC
     *      This person is getting a blind carbon copy.
     * @param subject
     *      This is teh subject of the email
     * @param body
     *      This is the main text of the email
     * @param tempTime
     *      This is the timestamp of when the email was sent. This is never null as the timestamp is
     *      always taken when this is called.
     */
    public Email(String recipent, String CC, String bCC, String subject, String body, GregorianCalendar tempTime){
        this.to = recipent;
        this.cc = CC;
        this.bcc = bCC;
        this.subject = subject;
        this.body = body;
        timeStamp = tempTime;
    }

    /**
     * This method returns the primary recipient of this email.
     *
     * @return
     *      The To
     */
    public String getTo() {
        return to;
    }


    /**
     * This method sets the recipient of the email
     * @param to
     *      The to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * This method gets the user that is receiving a carbon copy of the email
     * @return
     *      The CC
     */
    public String getCc() {
        return cc;
    }

    /**
     * This method sets the carbon copy recipient
     *
     * @param cc
     *      This is the CC recipient
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * This method returns who is the blind carbon copy recipient, if any
     * @return
     *      The Bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * This method sets the BCC recipient
     * @param bcc
     *      BCC recipient
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * This method gets the current subject of the email
     *
     * @return
     *      The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method sets the subject to the String passed
     * @param subject
     *      The subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method gets the body of the email object
     *
     * @return
     *      The body
     *
     */
    public String getBody() {
        return body;
    }

    /**
     * This method sets the body of the Email
     * @param body
     *      The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This returns the timestamp of when this email was initialized.
     *
     * @return
     *      The timestamp
     */
    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method sets the timestamp of the timestamp that is
     * @param timeStamp
     */
    public void setTimeStamp(GregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
    }
}
