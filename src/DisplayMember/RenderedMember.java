package DisplayMember;

import javafx.beans.property.SimpleStringProperty;

public class RenderedMember {
    private final SimpleStringProperty name;
    private final SimpleStringProperty memberId;
    private final SimpleStringProperty mobile;
    private final SimpleStringProperty email;

    public RenderedMember(String name, int memberId, String mobile, String email) {
        this.name = new SimpleStringProperty(name);
        this.memberId = new SimpleStringProperty(String.valueOf(memberId));
        this.mobile = new SimpleStringProperty(mobile);
        this.email = new SimpleStringProperty(email);
    }

    public String getName() {
        return name.get();
    }

    public String getMemberId() {
        return memberId.get();
    }

    public String getMobile() {
        return mobile.get();
    }

    public String getEmail() {
        return email.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setMemberId(String memberId) {
        this.memberId.set(memberId);
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}